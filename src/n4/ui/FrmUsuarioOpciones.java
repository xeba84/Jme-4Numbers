/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package n4.ui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.List;
import n4.ent.UsuarioEnt;

/**
 *
 * @author ssanch
 */
public class FrmUsuarioOpciones extends List
        implements CommandListener {

    private FrmPrincipal menuPrincipal;
    private FrmUsuarioConsulta frmUsuConsulta;
    private UsuarioEnt usuario;
    private FrmJuego frmJuego;
    private Alert alerta;

    public FrmUsuarioOpciones(FrmPrincipal frmPrin, FrmUsuarioConsulta frmCons) {
        super("4Numeros - Menu Usuario", IMPLICIT);
        this.menuPrincipal = frmPrin;
        this.frmUsuConsulta = frmCons;
        this.frmJuego = new FrmJuego(this.menuPrincipal);
        this.frmJuego.setUsuario(this.usuario);
        this.setCommandListener(this);
        this.crearItems();
    }

    public void setUsuario(UsuarioEnt usu){
        this.usuario = usu;
    }

    private void crearItems() {
        Font f = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD | Font.STYLE_ITALIC, Font.SIZE_LARGE);
        this.append("(°) Jugar", null);
        this.append("(°) Estadisticas", null);
        this.append("(°) Volver", null);
        this.setFont(0, f);
        this.setFont(1, f);
        this.setFont(2, f);
    }

    public void commandAction(Command c, Displayable d) {
        switch (this.getSelectedIndex()){
            case 0:
                this.frmJuego.setUsuario(this.usuario);
                this.frmJuego.iniciarJuego();
                this.menuPrincipal.ShowForm(this.frmJuego);
                break;
            case 1:
                int promedio = this.usuario.getTotalJugadas();
                if (this.usuario.getCantJuegosTerminados() > 0){
                    promedio = this.usuario.getTotalJugadas() /
                                this.usuario.getCantJuegosTerminados();
                }
                this.alerta = new Alert("ESTADISTICAS", 
                        "Usuario: " + this.usuario.getNombre() +
                        "\n   * Juegos Fin.:" + this.usuario.getCantJuegosTerminados() +
                        "\n   * Jugadas:" + this.usuario.getTotalJugadas() +
                        "\n   * Promedio:" + promedio,
                        null, null);
                this.alerta.setTimeout(Alert.FOREVER);
                this.menuPrincipal.ShowForm(this.alerta);
                break;
            case 2:
                this.menuPrincipal.ShowForm(this.frmUsuConsulta);
                break;
        }
    }
}
