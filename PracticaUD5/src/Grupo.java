import java.io.Serializable;

public class Grupo implements Serializable {

    ListadoPersonajes ls = new ListadoPersonajes(false);
    String nombre = "";
    int MedSTR = 0;
    int MedDEX = 0;
    int MedCON = 0;
    int MedINT = 0;
    int MedWIS = 0;
    int MedCHA = 0;

    public Grupo(String nombre) {
        this.nombre = nombre;
    }
    /**
     *
     * @param pj
     */
    public void addPJ(Personaje pj){
        this.ls.add(pj);
    }

    /**
     *
     */
    public void calcularMedias(){
        int STRs = 0;
        int DEXs = 0;
        int CONs = 0;
        int INTs = 0;
        int WISs = 0;
        int CHAs = 0;

        for(Personaje p: this.ls.getPersonajes()){
            STRs = STRs + p.getStr();
            DEXs = DEXs + p.getDex();
            CONs = CONs + p.getCon();
            INTs = INTs + p.getInt();
            WISs = WISs + p.getWis();
            CHAs = CHAs + p.getCha();
        }

        this.MedSTR = STRs/this.ls.getPersonajes().size();
        this.MedDEX = DEXs/this.ls.getPersonajes().size();
        this.MedCON = CONs/this.ls.getPersonajes().size();
        this.MedINT = INTs/this.ls.getPersonajes().size();
        this.MedWIS = WISs/this.ls.getPersonajes().size();
        this.MedCHA = CHAs/this.ls.getPersonajes().size();


    }

    public ListadoPersonajes getLs() {
        return ls;
    }

    public void setLs(ListadoPersonajes ls) {
        this.ls = ls;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMedSTR() {
        return MedSTR;
    }

    public void setMedSTR(int medSTR) {
        MedSTR = medSTR;
    }

    public int getMedDEX() {
        return MedDEX;
    }

    public void setMedDEX(int medDEX) {
        MedDEX = medDEX;
    }

    public int getMedCON() {
        return MedCON;
    }

    public void setMedCON(int medCON) {
        MedCON = medCON;
    }

    public int getMedINT() {
        return MedINT;
    }

    public void setMedINT(int medINT) {
        MedINT = medINT;
    }

    public int getMedWIS() {
        return MedWIS;
    }

    public void setMedWIS(int medWIS) {
        MedWIS = medWIS;
    }

    public int getMedCHA() {
        return MedCHA;
    }

    public void setMedCHA(int medCHA) {
        MedCHA = medCHA;
    }




}
