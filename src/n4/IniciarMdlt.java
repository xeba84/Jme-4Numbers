package n4;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import n4.ui.FrmPrincipal;
//import n4.ui.lsn.PrincipalLsn;

public class IniciarMdlt extends MIDlet {

    private FrmPrincipal frmMenu;
    private Display display;     // The display for this MIDlet
    //private PrincipalLsn principalLsn;


    public IniciarMdlt() {        
        display = Display.getDisplay(this);
        frmMenu = new FrmPrincipal(this, display);
        //principalLsn = new PrincipalLsn(this);
    }

    public void startApp() {
        //frmMenu.setCommandListener(this.principalLsn);
        display.setCurrent(frmMenu);
    }

    public void destroyApp(boolean unconditional){
        display = null;
        this.frmMenu = null;
        notifyDestroyed();
    }
    protected void pauseApp() {}
}
