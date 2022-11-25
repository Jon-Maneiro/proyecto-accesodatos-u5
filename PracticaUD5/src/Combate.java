import java.io.Serializable;

public class Combate implements Serializable {

    Encuentro en;
    Grupo gr;
    boolean llenar;
    boolean victoria;

    public Combate(boolean llenar) {
        this.llenar = llenar;

    }

    public Encuentro getEn() {
        return en;
    }

    public void setEn(Encuentro en) {
        this.en = en;
    }

    public Grupo getGr() {
        return gr;
    }

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
