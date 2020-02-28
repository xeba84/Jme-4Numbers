package n4.ui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import n4.bl.UsuarioBL;

/**
 *
 * @author ssanch
 */
public class FrmUsuarioAlta extends Form
        implements CommandListener {
    
    private Command volverCmd;
    private Command guardarCmd;
    private FrmPrincipal menuPrincipal;
    private TextField txtUsuario;
    private Alert alerta;


    public FrmUsuarioAlta(FrmPrincipal mnuPpal) {
        super("4Numeros - Crear Usuario");
        this.menuPrincipal = mnuPpal;
        this.crearComandos();
        this.crearControles();
    }

    private void crearComandos(){
        this.volverCmd = new Command("Volver", Command.BACK, 0);
        this.guardarCmd = new Command("Guardar", Command.OK, 0);
        this.addCommand(this.volverCmd);
        this.addCommand(this.guardarCmd);
        this.setCommandListener(this);
    }

    private void crearControles(){
        this.txtUsuario = new TextField("Usuario:", "", 8, TextField.ANY);
        this.txtUsuario.setInitialInputMode("MIDP_UPPERCASE_LATIN");
        this.append(this.txtUsuario);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == this.guardarCmd){
            if (this.guardar()){
                this.alerta = new Alert("USUARIO", "Usuario Generado.", null, AlertType.INFO);
                this.alerta.setTimeout(Alert.FOREVER);
                this.menuPrincipal.ShowForm(this.alerta);
                this.txtUsuario.setString("");
            }
        }else if (c == this.volverCmd){
            this.menuPrincipal.ShowForm(this.menuPrincipal);
        }
    }

    private boolean guardar(){
        boolean res = false;
        String msg = null;
        String nombre = this.txtUsuario.getString();
        try{            
            if(nombre.length() == 0){
                msg = "Ingrese un Nombre.";
            }else if (UsuarioBL.getInstance().getByNombre(nombre) != null){
                msg = "Usuario Existente.";
            }else{
                UsuarioBL.getInstance().crear(nombre);
                res = true;
            }
            if(!res){
                this.alerta = new Alert("USUARIO", msg, null, AlertType.WARNING);
                this.alerta.setTimeout(Alert.FOREVER);
                this.menuPrincipal.ShowForm(this.alerta);
            }
        }
        catch(Exception ex){
            msg = ex.getMessage();
            this.alerta = new Alert("USUARIO", msg, null, AlertType.ERROR);
            this.alerta.setTimeout(Alert.FOREVER);
            this.menuPrincipal.ShowForm(this.alerta);
            ex.printStackTrace();
        }
        finally{
            this.alerta = null;
            return res;
        }
    }
}
