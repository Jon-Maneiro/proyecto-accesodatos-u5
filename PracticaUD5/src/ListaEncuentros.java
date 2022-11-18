import java.io.Serializable;
import java.util.ArrayList;

public class ListaEncuentros implements Serializable {
    private ArrayList<Encuentro> encuentros = new ArrayList<>();

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
