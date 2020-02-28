/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package n4.bl;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 *
 * @author ssanch
 */
public abstract class BaseBL {

    protected RecordStore recordstore;
    private String storeName;
    private int sizeOfByteArray;
    private RecordEnumeration recordEnumeration = null;
    private int[] lastIndexes;

    public BaseBL(String rstNombre, int sizeOfByteArray){
        this.recordstore = null;
        this.storeName = rstNombre;
        this.sizeOfByteArray = sizeOfByteArray;
    }

    public int[] getLastIndexes() {
        return this.lastIndexes;
    }

    protected void openRecordStore() throws RecordStoreException{
        try {
            this.recordstore = RecordStore.openRecordStore(this.storeName, true);
        } catch (RecordStoreException ex) {
            throw ex;
        }
    }

    protected int save(byte[] record) throws Exception{
        //System.out.println("INICIO: save");
        this.recordstore = RecordStore.openRecordStore(this.storeName, true);
        //System.out.println("\tbytes: " + record.toString());
        int res = this.recordstore.addRecord(record, 0, record.length);
        this.recordstore.closeRecordStore();
        //System.out.println("FIN: save");
        return res;
    }

    protected void delete(int rcrId)throws Exception{
        this.recordstore = RecordStore.openRecordStore(this.storeName, true);
        this.recordstore.deleteRecord(rcrId);
        this.recordstore.closeRecordStore();
    }

    protected void update(int rcrId, byte[] record) throws Exception{
        this.recordstore = RecordStore.openRecordStore(this.storeName, true);
        this.recordstore.setRecord(rcrId, record, 0, record.length);
        this.recordstore.closeRecordStore();
    }

    protected byte[][] getAll() throws Exception{
        //System.out.println("INICIO: getAll");
        int rcr = -1;
        this.recordstore = RecordStore.openRecordStore(this.storeName, true);
        this.recordEnumeration = this.recordstore.enumerateRecords(null, null, false);
        this.lastIndexes = new int[this.recordEnumeration.numRecords()];
        //System.out.println("\trcr: " + this.recordstore.getNumRecords());
        byte[][] res = new byte[this.recordEnumeration.numRecords()][sizeOfByteArray];
        for (int i = 0; i < this.recordEnumeration.numRecords(); i++) {            
            rcr = this.recordEnumeration.nextRecordId();
            //System.out.println("\trcr N°: " + rcr + "  -  i: " + i);
            this.lastIndexes[i] = rcr;
            this.recordstore.getRecord(rcr, res[i], 0);
        }
        this.recordstore.closeRecordStore();
        //System.out.println("INICIO: getAll");
        return res;
    }
}
