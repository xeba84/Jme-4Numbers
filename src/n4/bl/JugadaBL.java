package n4.bl;

import java.util.Random;
import n4.ent.NumerosEnt;
import n4.ent.ResultadoEnt;

/**
 *
 * @author ssanch
 */
public class JugadaBL {
    private NumerosEnt sorteado;
    private Random generadorRnd;

    private JugadaBL(){
        this.sorteado = new NumerosEnt();
        this.generadorRnd = new Random();
    }

    public NumerosEnt getSorteado(){
        return this.sorteado;
    }

    public ResultadoEnt jugar(NumerosEnt numeros){
        ResultadoEnt res = new ResultadoEnt(numeros);
        boolean bien = false;
        boolean regular = false;
        //BIEN.
        for(int i = 0; i < NumerosEnt.getCantNums(); i++){
            bien = false;
            regular = false;
            for(int j = 0; j < NumerosEnt.getCantNums() && !bien; j++){
                if (numeros.getNum(i) == this.sorteado.getNum(j)){
                    bien = (i == j);
                    regular = !bien;
                }
            }
            if (bien){
                res.setCantBien(res.getCantBien() + 1);
            }else if (regular){
                res.setCantRegular(res.getCantRegular() + 1);
            }else{
                res.setCantMal(res.getCantMal() + 1);
            }
        }
        return res;
    }


    public void sortear() {
        do {
            this.sorteado.setNum(0, ((this.generadorRnd.nextInt() % 10) + 10) % 10);
            this.sorteado.setNum(1, ((this.generadorRnd.nextInt() % 10) + 10) % 10);
            this.sorteado.setNum(2, ((this.generadorRnd.nextInt() % 10) + 10) % 10);
            this.sorteado.setNum(3, ((this.generadorRnd.nextInt() % 10) + 10) % 10);
        } while (this.sorteado.tieneRepetidos());
    }
    
    private static JugadaBL instance;
    public static JugadaBL getInstance(){
        if (instance == null){
            instance = new JugadaBL();
        }
        return instance;
    }
}
