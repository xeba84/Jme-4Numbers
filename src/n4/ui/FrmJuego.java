package n4.ui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import n4.bl.JugadaBL;
import n4.bl.UsuarioBL;
import n4.ent.NumerosEnt;
import n4.ent.ResultadoEnt;
import n4.ent.UsuarioEnt;

/**
 *
 * @author ssanch
 */
public class FrmJuego extends Form
        implements CommandListener{

    private TextField txtN1;
    private TextField txtN2;
    private TextField txtN3;
    private TextField txtN4;
    private StringItem lblNumeros;
    private StringItem lblResCab;
    private StringItem lblResAct;
    private StringItem lblResDet;
    //private String separador;
    private Command pausaCmd;
    private Command jugarCmd;
    private ResultadoEnt[] resultadoLst;
    private static final int MAX_CANT_JUGADAS = 50;
    private int cantJugadas;
    private NumerosEnt numeroActual;
    private Alert alerta;
    private FrmPrincipal menuPrincipal;
    private UsuarioEnt usuario;
    private FrmPausa frmPausa;

    public FrmJuego(FrmPrincipal mnuPpal){
        super("4Numeros - En Juego");
        //this.separador = "\n_______________________";
        this.crearControles();
        this.crearComandos();
        this.menuPrincipal = mnuPpal;
        this.frmPausa = new FrmPausa(this.menuPrincipal, this);
    }

    public void setUsuario(UsuarioEnt usu){
        this.usuario = usu;
        this.frmPausa.setUsuario(usu);
    }

    private void crearComandos() {
        this.pausaCmd = new Command("Pausa", Command.BACK, 0);
        this.jugarCmd = new Command("Jugar", Command.OK, 0);
        this.addCommand(this.pausaCmd);
        this.addCommand(this.jugarCmd);
        this.setCommandListener(this);
    }

    public void iniciarJuego() {
        limpiarCampos();
        JugadaBL.getInstance().sortear();
        System.out.println(JugadaBL.getInstance().getSorteado().toString());
        this.resultadoLst = new ResultadoEnt[MAX_CANT_JUGADAS];
        this.cantJugadas = 0;
    }

    private void crearControles(){
        Font f = Font.getFont(Font.FACE_PROPORTIONAL, 
                Font.STYLE_BOLD | Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM);
        this.lblNumeros = new StringItem(null, "NUMERO:");
        this.lblNumeros.setFont(f);
        this.lblNumeros.setLayout(StringItem.LAYOUT_CENTER);
        this.append(this.lblNumeros);
        
        this.txtN1 = new TextField(null, null, 4, TextField.NUMERIC);
        //this.txtN1.setLayout(TextField.LAYOUT_2 | TextField.LAYOUT_NEWLINE_BEFORE | TextField.LAYOUT_SHRINK);
        this.txtN1.setLayout(TextField.LAYOUT_CENTER);
        this.append(this.txtN1);

        /*this.txtN2 = new TextField(null, null, 1, TextField.NUMERIC);
        this.txtN2.setLayout(TextField.LAYOUT_2 | TextField.LAYOUT_SHRINK);
        this.append(this.txtN2);

        this.txtN3 = new TextField(null, null, 1, TextField.NUMERIC);
        this.txtN3.setLayout(TextField.LAYOUT_2 | TextField.LAYOUT_SHRINK);
        this.append(this.txtN3);

        this.txtN4 = new TextField(null, null, 1, TextField.NUMERIC);
        this.txtN4.setLayout(TextField.LAYOUT_2 | TextField.LAYOUT_SHRINK);
        this.append(this.txtN4);*/

        this.lblResCab = new StringItem(null, "\n|1°|2°|3°|4°|-| B| R| M|\n");
                //+ this.separador);
        this.lblResCab.setFont(f);
        this.append(this.lblResCab);

        this.lblResAct = new StringItem(null, "");
        this.lblResAct.setFont(f);
        this.append(this.lblResAct);

        /*Font fd = Font.getFont(Font.FACE_PROPORTIONAL,
                Font.STYLE_BOLD | Font.STYLE_ITALIC | Font.STYLE_UNDERLINED,
                Font.SIZE_MEDIUM);*/
        this.lblResDet = new StringItem(null, "");
        //this.lblResDet.setFont(fd);
        this.lblResDet.setFont(f);
        this.append(this.lblResDet);        
    }

    public void commandAction(Command c, Displayable d) {
        if (c == this.jugarCmd){
            this.jugar();
        }else if (c == this.pausaCmd){
            this.menuPrincipal.ShowForm(this.frmPausa);
        }
    }

    private void jugar() {
        if (this.validarJugada()) { //Validar CAMPOS
            this.cantJugadas++;
            this.usuario.setTotalJugadas(this.usuario.getTotalJugadas() + 1);
            this.frmPausa.setUsuario(this.usuario);

            this.resultadoLst[this.cantJugadas - 1] =
                    JugadaBL.getInstance().jugar(this.numeroActual);

            String res = "";
            for (int i = this.cantJugadas - 2; i > -1; i--) {
                res = res + this.resultadoLst[i].toString();
                res = res + "\n";
            }

            this.limpiarCampos();
            //Jugada Actual!
            this.lblResAct.setText(this.resultadoLst[this.cantJugadas - 1].toString()+ "\n");
            this.lblResDet.setText(res);
            
            //GANADOR??
            if (this.resultadoLst[this.cantJugadas - 1].getCantBien() == NumerosEnt.getCantNums()){
                this.alerta = new Alert("JUEGO", 
                        "Ganador!!\n" + JugadaBL.getInstance().getSorteado()
                        + "\nJugadas:" + this.cantJugadas, null, null);
                this.alerta.setTimeout(Alert.FOREVER);
                this.menuPrincipal.ShowForm(this.alerta);
                this.usuario.setCantJuegosTerminados(this.usuario.getCantJuegosTerminados() + 1);
                this.iniciarJuego();                
            }
            try {
                //this.usuario.setRcdId(UsuarioBL.getInstance().reemplazar(this.usuario));
                UsuarioBL.getInstance().reemplazar(this.usuario);
            } catch (Exception ex) {
                this.alerta = new Alert("JUEGO", ex.getMessage(), null, AlertType.ERROR);
                this.alerta.setTimeout(Alert.FOREVER);
                this.menuPrincipal.ShowForm(this.alerta);
                ex.printStackTrace();
            }
        }
        this.alerta = null;
    }

    private boolean validarJugada(){
        boolean res = true;
        if (this.txtN1.getString().length() < 4
                /*||this.txtN2.getString().length() == 0
                ||this.txtN3.getString().length() == 0
                ||this.txtN4.getString().length() == 0*/
                ){
            this.alerta = new Alert("JUEGO", "Datos Incompletos.", null, AlertType.WARNING);
            this.alerta.setTimeout(Alert.FOREVER);
            this.menuPrincipal.ShowForm(this.alerta);
            res = false;
        }else if(this.cantJugadas + 1 > FrmJuego.MAX_CANT_JUGADAS){
            this.alerta = new Alert("JUEGO", "Se superó la cantidad máxima de jugadas. " +
                    "Inicie un Juego nuevo.", null, AlertType.WARNING);
            this.alerta.setTimeout(Alert.FOREVER);
            this.menuPrincipal.ShowForm(this.alerta);
            res = false;
        }else {
            this.numeroActual = new NumerosEnt(
                        Integer.parseInt(this.txtN1.getString().substring(0,1)),
                        Integer.parseInt(this.txtN1.getString().substring(1,2)),
                        Integer.parseInt(this.txtN1.getString().substring(2,3)),
                        Integer.parseInt(this.txtN1.getString().substring(3,4)));
            if (this.numeroActual.tieneRepetidos()){
                this.alerta = new Alert("JUEGO", "Los numeros deben ser distintos entre si. "
                        , null, AlertType.WARNING);
                this.alerta.setTimeout(Alert.FOREVER);
                this.menuPrincipal.ShowForm(this.alerta);
                res = false;
            }
        }        
        return res;
    }

    private void limpiarCampos(){        
        /*this.txtN2.setString("");
        this.txtN3.setString("");
        this.txtN4.setString("");*/
        this.lblResDet.setText("");
        this.lblResAct.setText("");
        this.txtN1.setString("");
    }
}
