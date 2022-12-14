import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public class ListaEncuentros implements Serializable {
    private ArrayList<Encuentro> encuentros = new ArrayList<>();

    boolean llenar;

    /**
     * Constructor de la clase ListaEncuentros
     * @param encuentro ArrayList de encuentros
     */
    public ListaEncuentros(ArrayList<Encuentro> encuentro) {
        this.encuentros = encuentro;
    }

    /**
     * Constructor vacio de ListaEncuentros
     */
    public ListaEncuentros(){}

    public ListaEncuentros(boolean llenar){
        if(llenar){
            llenarListaEncuentros();
        }
    }


    public void llenarListaEncuentros(){

        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("Encuentros", ListaEncuentros.class);
        xstream.alias("encuentro", Encuentro.class);
        xstream.alias("Enemigos", ListaEnemigos.class);
        xstream.alias("enemigo", Enemigo.class);
        xstream.alias("Recompensas", ListaRecompensas.class);
        xstream.alias("recompensa", Recompensa.class);
        xstream.processAnnotations(ListaRecompensas.class);
        //xstream.addImplicitCollection(ListaEncuentros.class, "lista");

        FileInputStream fichero = null;
        try {
            fichero = new FileInputStream("Encuentros.xml");
            this.encuentros = ((ListaEncuentros) xstream.fromXML(fichero)).getEncuentros();
        } catch (FileNotFoundException e) {
            System.out.println("Error al acceder al fichero de Encuentros");
            //throw new RuntimeException(e);
        }



        //Leer esto con un Iterator

    }


    /**
     * Devuelve los encuentros almacenados en la instancia de la clase
     * @return ArrayList con los encuentros
     */
    public ArrayList<Encuentro> getEncuentros() {
        return encuentros;
    }

    /**
     * Fija los encuentros almacenados en la instancia de la clase
     * @param encuentro ArrayList de encuentros a fijar
     */
    public void setEncuentros(ArrayList<Encuentro> encuentro) {
        this.encuentros = encuentro;
    }

    /**
     * Añade un encuentro a la instancia de la clase
     * @param encuentro Encuentro a introducir
     */
    public void add(Encuentro encuentro){
        encuentros.add(encuentro);
    }

}
