package n4.ent;

/**
 *
 * @author ssanch
 */
public class NumerosEnt extends BaseEnt {

    private int[] nums;

    public NumerosEnt(){
        this(0,0,0,0);
    }
    public NumerosEnt(int n0, int n1, int n2, int n3){
        this.nums = new int[NumerosEnt.getCantNums()];
        this.nums[0] = n0;
        this.nums[1] = n1;
        this.nums[2] = n2;
        this.nums[3] = n3;
    }

    public static int getCantNums(){
        return 4;
    }

    public int getNum(int pos){
        return this.nums[pos];
    }
    public void setNum(int pos, int value){
        this.nums[pos] = value;
    }

    public boolean tieneRepetidos() {
        boolean res = false;
        for(int i = 0; i < NumerosEnt.getCantNums() && !res; i++){
            res = this.cantApariciones(this.nums[i]) > 1;
        }
        return res;
    }
    private int cantApariciones(int numero) {
        int res = 0;
        for(int i = 0; i < NumerosEnt.getCantNums(); i++){
            res += (numero == this.nums[i])? 1 : 0;
        }
        return res;
    }

    public String toString() {
        return "| " + this.getNum(0)  + " | " + this.getNum(1) +
                " | " + this.getNum(2) + " | " + this.getNum(3);
    }

}
