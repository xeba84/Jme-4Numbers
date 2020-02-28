package n4.ui.lsn;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import n4.IniciarMdlt;

/**
 *
 * @author ssanch
 */
public class PrincipalLsn implements CommandListener {

    private Command exitCommand;
    private IniciarMdlt iniciarMidlt;

    public Command getExitCommand() {
        return exitCommand;
    }

    public PrincipalLsn(IniciarMdlt iniMdlt){
        this.exitCommand = new Command("Salir", Command.EXIT, 0);
        this.iniciarMidlt = iniMdlt;
    }

    public void commandAction(Command c, Displayable s) {
        if (c == exitCommand) {
            this.iniciarMidlt.destroyApp(false);
        }
    }
}
