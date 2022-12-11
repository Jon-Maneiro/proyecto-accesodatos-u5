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


    public void add(Combate com){
        combates.add(com);
    }

    public void llenarListaCombates(){

    }

    public ArrayList<Combate> getCombates() {
        return combates;
    }

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
