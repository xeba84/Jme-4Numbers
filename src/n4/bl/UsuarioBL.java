package n4.bl;

import n4.ent.UsuarioEnt;

/**
 *
 * @author ssanch
 */
public class UsuarioBL extends BaseBL {

    private static UsuarioBL instance;

    private UsuarioBL(){
        super("usuarioRST", UsuarioEnt.getSizeOfByteArray());
    }

    public UsuarioEnt getByNombre(String nombre) throws Exception{
        //System.out.println("INICIAR: getByNombre");
        UsuarioEnt[] todos = this.obtenerTodos();
        UsuarioEnt res = null;        
        //System.out.println("\tNombre:" + nombre);
        //System.out.println("\tTodos:# " + todos.length);

        for(int i = 0; i < todos.length && res == null; i++){
            //System.out.println("\tindice: " + i);
            if (todos[i].getNombre().compareTo(nombre) == 0){
                //System.out.println("\tEncontre usuario");
                res = todos[i];
            }
        }
        //System.out.println("FIN: getByNombre");
        return res;
    }

    public UsuarioEnt crear(String nombre) throws Exception{
        UsuarioEnt res = null;
        if (this.getByNombre(nombre) == null){
            res = new UsuarioEnt();
            res.setNombre(nombre);
            this.save(res.toByteArray());
        }
        return res;
    }

    public void reemplazar(UsuarioEnt usuario)throws Exception{
        //this.delete(usuario.getRcdId());
        //return this.save(usuario.toByteArray());
        this.update(usuario.getRcdId(), usuario.toByteArray());
    }

    public UsuarioEnt[] obtenerTodos() throws Exception{
        //System.out.println("INICIO: generarTodos");
        byte[][] registros = this.getAll();
        //System.out.println("\tregistros:# " + registros.length);
        UsuarioEnt[] res = new UsuarioEnt[registros.length];
        for (int i = 0; i < registros.length; i++) {
            //System.out.println("\tindice: " + i);
            res[i] = new UsuarioEnt(registros[i]);
            //System.out.println("\t" + res[i].toString());
            res[i].setRcdId(this.getLastIndexes()[i]);
        }
        //System.out.println("FIN: generarTodos");
        return res;
    }

    public static UsuarioBL getInstance(){
        if (instance == null){
            instance = new UsuarioBL();
        }
        return instance;
    }
}
