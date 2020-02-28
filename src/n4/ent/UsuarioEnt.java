package n4.ent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author ssanch
 */
public class UsuarioEnt extends BaseEnt {

    private String nombre;
    private int cantJuegosTerminados;
    private int totalJugadas;
    private long tiempoDeJuego;

    public UsuarioEnt() {
        this.setRcdId(-1);
        this.nombre = "";
        this.cantJuegosTerminados = 0;
        this.totalJugadas = 0;
        this.tiempoDeJuego = 0;
    }
    public UsuarioEnt(byte[] input) throws Exception{
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input);
        DataInputStream inputDataStream = new DataInputStream(inputStream);
        this.setNombre(inputDataStream.readUTF());
        this.setCantJuegosTerminados(inputDataStream.readInt());
        this.setTotalJugadas(inputDataStream.readInt());
        this.setTiempoDeJuego(inputDataStream.readLong());
        inputStream.close();
        inputDataStream.close();
    }

    public byte[] toByteArray() throws Exception{
        //System.out.println("INICIO: toByteArray");
        byte[] res;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream outputDataStream = new DataOutputStream(outputStream);
        //System.out.println("\t" + this.toString());
        outputDataStream.writeUTF(this.nombre);
        outputDataStream.writeInt(this.cantJuegosTerminados);
        outputDataStream.writeInt(this.totalJugadas);
        outputDataStream.writeLong(this.tiempoDeJuego);
        res = outputStream.toByteArray();
        outputStream.close();
        outputDataStream.close();
        //System.out.println("\tbytes: " + res.toString());
        //System.out.println("FIN: toByteArray");
        return res;
    }
    
    public int getCantJuegosTerminados() {
        return cantJuegosTerminados;
    }
    public void setCantJuegosTerminados(int cantJuegosTerminados) {
        this.cantJuegosTerminados = cantJuegosTerminados;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public long getTiempoDeJuego() {
        return tiempoDeJuego;
    }
    public void setTiempoDeJuego(long tiempoDeJuego) {
        this.tiempoDeJuego = tiempoDeJuego;
    }
    public int getTotalJugadas() {
        return totalJugadas;
    }
    public void setTotalJugadas(int totalJugadas) {
        this.totalJugadas = totalJugadas;
    }

    public static int getSizeOfByteArray() {
        return 100;
    }

    public String toString() {
        return "U:" + this.nombre + "|" + this.cantJuegosTerminados  +  "|" +
                this.totalJugadas + "|" + this.tiempoDeJuego +  "|";
    }
}
