/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package n4.ui;

import javax.microedition.lcdui.Alert;
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
public class FrmPausa extends List
        implements CommandListener {

    private FrmPrincipal menuPrincipal;
    private FrmJuego frmJuego;
    private FrmVolverMenu frmVolver;
    private Alert alerta;
    private UsuarioEnt usuario;

    public FrmPausa(FrmPrincipal frmPrin, FrmJuego frmJuego) {
        super("4Numeros - Juego en Pausa", IMPLICIT);
        this.menuPrincipal = frmPrin;
        this.frmJuego = frmJuego;
        this.frmVolver = new FrmVolverMenu(this.menuPrincipal, this.frmJuego);
        this.crearItems();
    }

    public void setUsuario(UsuarioEnt usu) {
        this.usuario = usu;
    }

    private void crearItems() {
        Font f = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD | Font.STYLE_ITALIC, Font.SIZE_LARGE);
        this.append("(°) Continuar", null);
        this.append("(°) Estadisticas", null);
        this.append("(°) Guardar", null);
        this.append("(°) Menu Principal", null);
        this.setFont(0, f);
        this.setFont(1, f);
        this.setFont(2, f);
        this.setFont(3, f);
        this.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        switch (this.getSelectedIndex()) {
            case 0:
                this.menuPrincipal.ShowForm(this.frmJuego);
                break;
            case 1:
                int promedio = this.usuario.getTotalJugadas();
                if (this.usuario.getCantJuegosTerminados() > 0) {
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
                this.alerta = new Alert("GUARDAR", "Proximamente... :D", null, null);
                this.menuPrincipal.ShowForm(this.alerta);
                break;
            case 3:
                this.menuPrincipal.ShowForm(this.frmVolver);
                break;
        }
    }
}
