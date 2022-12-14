import java.io.Serializable;
import java.util.ArrayList;

public class ListaCombates implements Serializable {

    ArrayList<Combate> combates = new ArrayList<>();

    boolean llenar;

    public ListaCombates(boolean llenar) {
        this.llenar = llenar;
    }

    public ListaCombates() {
    }

    /**
     * Añade un combate a la Lista de Combates
     * @param com combate a insertar
     */
    public void add(Combate com){
        combates.add(com);
    }

    /**
     * Obtiene una lista de los combates de esta clase
     * @return
     */
    public ArrayList<Combate> getCombates() {
        return combates;
    }

    /**
     * Asigna combates a la lista de combates
     * @param combates
     */
    public void setCombates(ArrayList<Combate> combates) {
        this.combates = combates;
    }

    public boolean isLlenar() {
        return llenar;
    }

    public void setLlenar(boolean llenar) {
        this.llenar = llenar;
    }
}
