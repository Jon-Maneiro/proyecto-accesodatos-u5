import java.io.Serializable;
import java.util.HashMap;

public class Personaje implements Serializable {
    /**
     * 3 Strings de 50 chars - 100 cada uno, 300 bytes
     * 10 ints - 4 cada uno - 40 bytes
     * TOTAL 340 Bytes
     */
    private int id;
    private String nombre;//Max 50
    private String clase;//Max 50
    private String raza;//Max 50
    private int hitDie;
    private int nivel;
    private int Str;
    private int Dex;
    private int Con;
    private int Int;
    private int Wis;
    private int Cha;
    private int vida;

    /**
     * Constructor de la clase Personaje
     * @param nombre Nombre del Personaje
     * @param nivel Nivel
     * @param clase Clase
     * @param hitDie Dado de Golpe(Es con lo que se calcula la vida)
     * @param raza Raza
     * @param str Fuerza
     * @param dex Destreza
     * @param con Constitucion
     * @param Int Inteligencia
     * @param wis Sabiduria
     * @param cha Carisma
     * @param vida Vida
     */
    public Personaje(String nombre, int nivel, String clase, int hitDie, String raza, int str, int dex, int con, int Int, int wis, int cha, int vida) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.clase = clase;
        this.hitDie = hitDie;
        this.raza = raza;
        Str = str;
        Dex = dex;
        Con = con;
        Int = Int;
        Wis = wis;
        Cha = cha;
        this.vida = vida;
    }

    /**
     * Constructor vacio de la clase Personaje
     */
    public Personaje(){}

    /**
     * Devuelve el nombre del personaje
     * @return Nombre del personaje
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Fija el nombre del personaje
     * @param nombre nombre a fijar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nivel del personaje
     * @return nivel del personaje
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Fija el nivel del personaje
     * @param nivel nivel a fijar
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * Devuelve la clase del personaje
     * @return la clase del personaje
     */
    public String getClase() {
        return clase;
    }

    /**
     * Fija la clase del personaje
     * @param clase la clase a fijar
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * Devuelve el dado de golpe del personaje
     * @return dado de golpe del personaje
     */
    public int getHitDie() {
        return hitDie;
    }

    /**
     * Fija el dado de golpe del personaje
     * @param hitDie Dado de golpe a fijar
     */
    public void setHitDie(int hitDie) {
        this.hitDie = hitDie;
    }

    /**
     * Devuelve la raza del personaje
     * @return raza del personaje
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Fija la raza del personaje
     * @param raza raza a fijar
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * Devuelve la fuerza del personaje
     * @return fuerza
     */
    public int getStr() {
        return Str;
    }

    /**
     * Fija la fuerza del personaje
     * @param str fuerza
     */
    public void setStr(int str) {
        Str = str;
    }

    /**
     * Devuelve la destreza del personaje
     * @return destreza
     */
    public int getDex() {
        return Dex;
    }

    /**
     * Fija la destreza del personaje
     * @param dex destreza
     */
    public void setDex(int dex) {
        Dex = dex;
    }

    /**
     * Devuelve la constitucion del personaje
     * @return constitucion
     */
    public int getCon() {
        return Con;
    }

    /**
     * Fija la constitucion del personaje
     * @param con constitucion
     */
    public void setCon(int con) {
        Con = con;
    }

    /**
     * Devuelve la inteligencia del personaje
     * @return inteligencia
     */
    public int getInt() {
        return Int;
    }

    /**
     * Fija la inteligencia del personaje
     * @param Int inteligencia
     */
    public void setInt(int Int) {
        Int = Int;
    }

    /**
     * Devuelve la sabiduria del personaje
     * @return sabiduria
     */
    public int getWis() {
        return Wis;
    }

    /**
     * Fija la sabiduria del personaje
     * @param wis sabiduria
     */
    public void setWis(int wis) {
        Wis = wis;
    }

    /**
     * Devuelve el carisma del personaje
     * @return carisma
     */
    public int getCha() {
        return Cha;
    }

    /**
     * Fija el carisma del personaje
     * @param cha carisma
     */
    public void setCha(int cha) {
        Cha = cha;
    }

    /**
     * Devuelve la vida del personaje
     * @return vida del personaje
     */
    public int getVida() {
        return vida;
    }

    /**
     * Fija la vida del personaje
     * @param vida vida del personaje
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Devuelve el id del personaje
     * @return id del personaje
     */
    public int getId() {
        return id;
    }

    /**
     * Fija el id del personaje, NO UTILIZAR PARA CAMBIAR UN PERSONAJE YA EXISTENTE, INCONSISTENCIAS EN ARCHIVOS
     * @param id el id a fijar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Subir el nivel del personaje instanciado
     */
    public void subirNivel(){
        //X + ((Y/2+1) + CPorNivel(Se aplica Retroactivamente))
        this.nivel++;
        this.vida = vida + (((hitDie/2) + 1) + Con);
    }
}
