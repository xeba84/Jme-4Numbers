package n4.ui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import n4.IniciarMdlt;

/**
 *
 * @author ssanch
 */
public class FrmVolverMenu extends Alert
        implements CommandListener {

    private Command siCmd;
    private Command noCmd;
    private FrmPrincipal menuPrincipal;
    private FrmJuego frmJuego;

    public FrmVolverMenu(FrmPrincipal mnuPpal, FrmJuego frmJuego){
        super("VOLVER", "Desea Salir del juego Actual", null, AlertType.CONFIRMATION);
        this.menuPrincipal = mnuPpal;
        this.frmJuego = frmJuego;
        this.crearComandos();
    }

    private void crearComandos() {
        this.siCmd = new Command("SI", Command.OK, 0);
        this.noCmd = new Command("NO", Command.BACK, 0);
        this.addCommand(siCmd);
        this.addCommand(noCmd);
        this.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == this.siCmd) {
            this.menuPrincipal.ShowForm(this.menuPrincipal);
        } else if (c == this.noCmd) {
            this.menuPrincipal.ShowForm(this.frmJuego);
        }
    }
}
