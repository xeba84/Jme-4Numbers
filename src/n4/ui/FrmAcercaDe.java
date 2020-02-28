/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package n4.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author ssanch
 */
public class FrmAcercaDe extends Form 
            implements CommandListener{

    private Command volverCmd;
    private FrmPrincipal menuPrincipal;
    private StringItem lblTexto;

    public FrmAcercaDe(FrmPrincipal mnuPpal) {
        super("4Numeros - Acerca De");
        this.menuPrincipal = mnuPpal;
        this.volverCmd = new Command("Volver", Command.BACK, 0);
        this.addCommand(this.volverCmd);
        this.setCommandListener(this);
        this.lblTexto = new StringItem(null, "T@tisoft 2012");
        Font f = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE);
        this.lblTexto.setFont(f);
        this.append(this.lblTexto);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == this.volverCmd){
            this.menuPrincipal.ShowForm(this.menuPrincipal);
        }
    }

}
