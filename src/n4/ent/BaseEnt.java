/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package n4.ent;

/**
 *
 * @author ssanch
 */
public abstract class BaseEnt {    
    private int rcdId;

    public int getRcdId() {
        return rcdId;
    }
    public void setRcdId(int rcdId) {
        this.rcdId = rcdId;
    }
}
