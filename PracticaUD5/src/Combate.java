import java.io.Serializable;

public class Combate implements Serializable {

    Encuentro en;
    Grupo gr;
    boolean llenar;
    boolean victoria;

    /**
     * Constructor de la clase Combate
     * @param llenar true/false
     */
    public Combate(boolean llenar) {
        this.llenar = llenar;

    }

    /**
     * Devuelve el encuentro asociado
     * @return
     */
    public Encuentro getEn() {
        return en;
    }

    /**
     * Asigna un Encuentro
     * @param en
     */
    public void setEn(Encuentro en) {
        this.en = en;
    }

    /**
     * Devuelve el Grupo asignado
     * @return
     */
    public Grupo getGr() {
        return gr;
    }

    /**
     * Asigna un Grupo
     * @param gr
     */
    public void setGr(Grupo gr) {
        this.gr = gr;
    }

    public boolean isLlenar() {
        return llenar;
    }

    public void setLlenar(boolean llenar) {
        this.llenar = llenar;
    }

    public boolean isVictoria() {
        return victoria;
    }

    public void setVictoria(boolean victoria) {
        this.victoria = victoria;
    }
}
