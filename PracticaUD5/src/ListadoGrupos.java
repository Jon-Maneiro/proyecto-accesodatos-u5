import java.io.Serializable;
import java.util.ArrayList;

public class ListadoGrupos implements Serializable {

    ArrayList<Grupo> grupos = new ArrayList<>();
    boolean llenar;

    public ListadoGrupos(boolean llenar) {
        this.llenar = llenar;
        if(this.llenar){
            llenarGrupos();
        }
    }

    public void llenarGrupos(){
        //grupos = existOperaciones.llenarGrupos();
    }

    public void add(Grupo g){
        this.grupos.add(g);
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

    public boolean isLlenar() {
        return llenar;
    }

    public void setLlenar(boolean llenar) {
        this.llenar = llenar;
    }


}
