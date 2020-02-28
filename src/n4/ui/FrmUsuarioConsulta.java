package n4.ui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.List;
import n4.bl.UsuarioBL;
import n4.ent.UsuarioEnt;

/**
 *
 * @author ssanch
 */
public class FrmUsuarioConsulta extends List
        implements CommandListener {

    private FrmPrincipal menuPrincipal;
    private Command volverCmd;
    private UsuarioEnt[] usuarioLst;
    private Alert alerta;
    private FrmUsuarioOpciones frmUsuOpc;

    public FrmUsuarioConsulta(FrmPrincipal mnuPpal) {
        super("4Numeros - Elegir Usuario", IMPLICIT);
        this.menuPrincipal = mnuPpal;
        this.crearComandos();
        this.frmUsuOpc = new FrmUsuarioOpciones(this.menuPrincipal, this);
        //this.cargarUsuarios();
    }


    private void crearComandos(){
        this.volverCmd = new Command("Volver", Command.BACK, 0);
        this.addCommand(this.volverCmd);
        this.setCommandListener(this);
    }

    public void cargarUsuarios() {
        try{
            Font f = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM);
            this.deleteAll();
            this.usuarioLst = UsuarioBL.getInstance().obtenerTodos();
            for(int i = 0; i < this.usuarioLst.length; i++){
                this.append(this.usuarioLst[i].getNombre(), null);
                this.setFont(i, f);
            }
            this.setCommandListener(this);
        }
        catch(Exception ex){
            this.alerta = new Alert("USUARIOS", ex.getMessage(), null, AlertType.ERROR);
            this.alerta.setTimeout(Alert.FOREVER);
            this.menuPrincipal.ShowForm(this.alerta);
            ex.printStackTrace();
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == this.volverCmd){
            this.menuPrincipal.ShowForm(this.menuPrincipal);
        }else{
            this.frmUsuOpc.setUsuario(this.usuarioLst[this.getSelectedIndex()]);
            this.menuPrincipal.ShowForm(this.frmUsuOpc);
        }
    }   
}
