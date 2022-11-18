import java.io.Serializable;

public class Recompensa implements Serializable {

    private int id;
    private String nombre;//Max 50 chars
    private String tipo;//Max 20 chars (Objeto magico, gema, pergamino.. etc)
    private int rareza;//la rareza, comun, poco comun, raro, muy raro, legendario o artefacto

    /**
     * Constructor de la clase Recompensa
     * @param id id de la recompensa
     * @param nombre nombre de la recompensa
     * @param tipo tipo de recompensa
     * @param rareza Rareza de la recompensa(1-6)
     */
    public Recompensa(int id, String nombre, String tipo, int rareza) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rareza = rareza;
    }

    /**
     * Devuelve el id de la recompensa
     * @return id de la recompensa
     */
    public int getId() {
        return id;
    }

    /**
     * Fija el id de la recompensa. NO UTILIZAR PARA CAMBIAR EL DE UNA YA EXISTENTE, INCONSISTENCIAS EN ARCHIVOS
     * @param id id a fijar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la recompensa
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Fija el nombre de la recompensa
     * @param nombre nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el tipo de la recompensa
     * @return tipo de la recompensa
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Fija el tipo de recompesa
     * @param tipo tipo de recompensa
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve la rareza de la recompnesa
     * @return
     */
    public int getRareza() {
        return rareza;
    }

    /**
     * Fija la rareza de la recompensa
     * @param rareza
     */
    public void setRareza(int rareza) {
        this.rareza = rareza;
    }
}
