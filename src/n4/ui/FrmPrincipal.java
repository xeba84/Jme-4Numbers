package n4.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Screen;
import n4.IniciarMdlt;

/**
 *
 * @author ssanch
 */
public class FrmPrincipal extends List
        implements CommandListener {

    private String[] items;
    private IniciarMdlt midlet;
    private FrmUsuarioAlta frmUsuarioAlta;
    private FrmUsuarioConsulta frmUsuarioConsulta;
    private FrmSalir frmSalir;
    private FrmAcercaDe frmAcercaDe;
    private Display display;

    public FrmPrincipal(IniciarMdlt m, Display d) {
        super("4Numeros", IMPLICIT);
        this.crearItems();
        this.midlet = m;
        this.frmUsuarioAlta = new FrmUsuarioAlta(this);
        this.frmUsuarioConsulta = new FrmUsuarioConsulta(this);
        this.frmSalir = new FrmSalir(this.midlet, this);
        this.frmAcercaDe = new FrmAcercaDe(this);
        this.display = d;
        //this.cargarUsuariosPrueba();
    }

    private void cargarUsuariosPrueba() {
        try{
            System.out.println("**********************************");
            System.out.println("CARGANDO USUARIOS DE PRUEBA......");
            n4.bl.UsuarioBL.getInstance().crear("SEBA");
            n4.bl.UsuarioBL.getInstance().crear("EZE");
            n4.bl.UsuarioBL.getInstance().crear("FACU");
            n4.bl.UsuarioBL.getInstance().crear("NORBERTO");
            n4.bl.UsuarioBL.getInstance().crear("PELU");
            n4.bl.UsuarioBL.getInstance().crear("ALE");
            n4.bl.UsuarioBL.getInstance().crear("CHUK");
            n4.bl.UsuarioBL.getInstance().crear("ROCKO");
            n4.bl.UsuarioBL.getInstance().crear("CONNAN");
            n4.bl.UsuarioBL.getInstance().crear("PEPE");
            n4.bl.UsuarioBL.getInstance().crear("MAQUINA");
            System.out.println("**********************************");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void crearItems() {
        Font f = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE);
        items = new String[4];
        items[0] = "(°) Crear Usuario";
        items[1] = "(°) Elegir Usuario";
        items[2] = "(°) Acerca De";
        items[3] = "(°) Salir";
        for (int i = 0; i < items.length; i++) {
            this.append(items[i], null);
            this.setFont(i, f);
        }
        this.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        switch (this.getSelectedIndex()) {
            case 0:
                this.ShowForm(this.frmUsuarioAlta);
                break;
            case 1:
                this.frmUsuarioConsulta.cargarUsuarios();
                this.ShowForm(this.frmUsuarioConsulta);
                break;
            case 2:
                this.ShowForm(this.frmAcercaDe);
                break;
            case 3:
                //this.midlet.destroyApp(true);
                this.ShowForm(this.frmSalir);
                break;
        }
        //System.out.println(this.items[this.getSelectedIndex()]);
    }

    public void ShowForm(Screen scr){
        this.display.setCurrent(scr);
    }
}
