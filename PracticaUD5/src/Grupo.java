import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;

public class Grupo implements Serializable {

    ListadoPersonajes ls = new ListadoPersonajes(false);
    @XStreamAlias("nombre")
    @XStreamAsAttribute()
    String nombre = "";
    int MedSTR = 0;
    int MedDEX = 0;
    int MedCON = 0;
    int MedINT = 0;
    int MedWIS = 0;
    int MedCHA = 0;

    /**
     * Constructor de grupo con nombre
     * @param nombre
     */
    public Grupo(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Añade un personaje a la lista de personajes del grupo
     * @param pj
     */
    public void addPJ(Personaje pj){
        this.ls.add(pj);
    }

    /**
     *Calcula la media de las estadisticas de todos los personajes del grupo
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

    /**
     * Devuelve el listado de personajes perteneciente a este grupo
     * @return
     */
    public ListadoPersonajes getLs() {
        return ls;
    }

    /**
     * Asigna un listado de personajes al grupo
     * @param ls
     */
    public void setLs(ListadoPersonajes ls) {
        this.ls = ls;
    }

    /**
     * Obtiene el nombre del grupo
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del grupo
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la media de la Fuerza
     * @return
     */
    public int getMedSTR() {
        return MedSTR;
    }

    /**
     * Asigna la media de la Fuerza
     * @param medSTR
     */
    public void setMedSTR(int medSTR) {
        MedSTR = medSTR;
    }

    /**
     * Obtiene la media de la Destreza
     * @return
     */
    public int getMedDEX() {
        return MedDEX;
    }

    /**
     * Asigna la media de la Destreza
     * @param medDEX
     */
    public void setMedDEX(int medDEX) {
        MedDEX = medDEX;
    }

    /**
     * Obtiene la media de la Constitucion
     * @return
     */
    public int getMedCON() {
        return MedCON;
    }

    /**
     * Asigna la media de la Constitucion
     * @param medCON
     */
    public void setMedCON(int medCON) {
        MedCON = medCON;
    }

    /**
     * Obtiene la media de la Inteligencia
     * @return
     */
    public int getMedINT() {
        return MedINT;
    }

    /**
     * Asigna la media de la Inteligencia
     * @param medINT
     */
    public void setMedINT(int medINT) {
        MedINT = medINT;
    }

    /**
     * Obtiene la media de la Sabiduria
     * @return
     */
    public int getMedWIS() {
        return MedWIS;
    }

    /**
     * Asigna la media de la Sabiduria
     * @param medWIS
     */
    public void setMedWIS(int medWIS) {
        MedWIS = medWIS;
    }

    /**
     * Obtiene la media del Carisma
     * @return
     */
    public int getMedCHA() {
        return MedCHA;
    }

    /**
     * Asigna la media de la carisma
     * @param medCHA
     */
    public void setMedCHA(int medCHA) {
        MedCHA = medCHA;
    }




}
