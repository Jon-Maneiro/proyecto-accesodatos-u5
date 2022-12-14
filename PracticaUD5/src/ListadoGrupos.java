import java.io.Serializable;
import java.util.ArrayList;

public class ListadoGrupos implements Serializable {

    ArrayList<Grupo> grupos = new ArrayList<>();

    public ListadoGrupos() {
    }

    /**
     * Agrega un grupo a la lista
     * @param g
     */
    public void add(Grupo g){
        this.grupos.add(g);
    }

    /**
     * Obtiene los grupos de la lista
     * @return
     */
    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    /**
     * Asigna grupos a la lista
     * @param grupos
     */
    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

}
