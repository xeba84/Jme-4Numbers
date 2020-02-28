package n4.ent;

/**
 *
 * @author ssanch
 */
public class ResultadoEnt extends BaseEnt{
    private NumerosEnt numeros;
    private int cantBien;
    private int cantRegular;
    private int cantMal;

    public ResultadoEnt() {
        this(new NumerosEnt());
    }

    public ResultadoEnt(NumerosEnt num){
        this.numeros = num;
        this.cantBien = 0;
        this.cantRegular = 0;
        this.cantMal = 0;
    }

    public int getCantBien() {
        return cantBien;
    }
    public void setCantBien(int cantBien) {
        this.cantBien = cantBien;
    }
    public int getCantMal() {
        return cantMal;
    }
    public void setCantMal(int cantMal) {
        this.cantMal = cantMal;
    }
    public int getCantRegular() {
        return cantRegular;
    }
    public void setCantRegular(int cantRegular) {
        this.cantRegular = cantRegular;
    }
    public NumerosEnt getNumeros() {
        return numeros;
    }
    public void setNumeros(NumerosEnt numeros) {
        this.numeros = numeros;
    }

    public String toString() {
        return this.numeros.toString() + "|-| "+ this.getCantBien() + " | " +
                this.getCantRegular() + " | " + this.getCantMal() + "|";
    }

}
