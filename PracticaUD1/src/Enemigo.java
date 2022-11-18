import java.io.Serializable;

public class Enemigo implements Serializable {

    private int id;//Llevar control de las listas
    private String nombre;//Max 50caracteres
    private String tipo;//Max 20 caracteres
    private long cr;//va de 0 - 0.125 hasta ... 30?¿
    private int xp;//La experiencia que da el bicho

    /**
     * Constructor de la clase Enemigo
     * @param id Id del enemigo -> Organizacion
     * @param nombre Nombre del enemigo(Max 50 chars)
     * @param tipo Tipo del enemigo(Max 50 chars)
     * @param cr Clase de desafio del enemigo
     * @param xp Experiencia que otorga al ser derrotado
     */
    public Enemigo(int id, String nombre, String tipo, long cr, int xp) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cr = cr;
        this.xp = xp;
    }

    /**
     * Devuelve el ID del enemigo
     * @return int con el Id del enemigo
     */
    public int getId() {
        return id;
    }

    /**
     * Fija el Id del enemigo
     * @param id int con el Id del enemigo
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del enemigo
     * @return nombre del enemigo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Fija el nombre del enemigo
     * @param nombre el nombre del enemigo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el tipo de enemigo
     * @return tipo de enemigo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Fija el tipo de enemigo
     * @param tipo Tipo de enemigo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve la clase de desafio del enemigo
     * @return Long la clase de desafio del enemigo
     */
    public long getCr() {
        return cr;
    }

    /**
     * Fijar la clase de desafio del enemigo
     * @param cr Long la clase de desafio
     */
    public void setCr(long cr) {
        this.cr = cr;
    }

    /**
     * Devuelve la experiencia que otorga el enemigo
     * @return Int la experiencia que otorga el enemigo
     */
    public int getXp() {
        return xp;
    }

    /**
     * Fija la experiencia que otorga el enemigo
     * @param xp int la experiencia que otorga el enemigo
     */
    public void setXp(int xp) {
        this.xp = xp;
    }
}