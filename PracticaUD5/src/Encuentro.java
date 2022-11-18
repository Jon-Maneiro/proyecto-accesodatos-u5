import java.io.Serializable;

public class Encuentro implements Serializable {
    /**
     * Esta clase solo debería usarse para pasar los encuentros entre el programa y XML
     * todas las demás operaciones deberán hacerse instanciando un objeto
     * de la clase necesaria.
     */

    private int numeroPJ;
    private int nivelPJ;

    private ListaEnemigos enemigos;
    private ListaRecompensas recompensas;

    /**
     * Constructor de la clase encuentro
     * @param numeroPJ Numero de personajes que participan
     * @param nivelPJ Nivel de los personajes que participan
     * @param enemigos ListaEnemigos con los enemigos que participan
     * @param recompensas ListaRecompensas con las recompensas que se obtienen
     */
    public Encuentro(int numeroPJ, int nivelPJ, ListaEnemigos enemigos, ListaRecompensas recompensas) {
        this.numeroPJ = numeroPJ;
        this.nivelPJ = nivelPJ;
        this.enemigos = enemigos;
        this.recompensas = recompensas;
    }

    /**
     * Devuelve el numero de Jugadores
     * @return el numero de Jugadores
     */
    public int getNumeroPJ() {
        return numeroPJ;
    }

    /**
     * Fija el numero de jugadores
     * @param numeroPJ el numero de jugadores
     */
    public void setNumeroPJ(int numeroPJ) {
        this.numeroPJ = numeroPJ;
    }

    /**
     * Devuelve el nivel de los Personajes
     * @return el nivel de los personajes
     */
    public int getNivelPJ() {
        return nivelPJ;
    }

    /**
     * Fija el nivel de los personajes
     * @param nivelPJ el nivel de los personajes
     */
    public void setNivelPJ(int nivelPJ) {
        this.nivelPJ = nivelPJ;
    }

    /**
     * Devuelve una lista con los enemigos del encuentro
     * @return ListaEnemigos con los enemigos del encuentro
     */
    public ListaEnemigos getEnemigos() {
        return enemigos;
    }

    /**
     * Fija los enemigos del encuentro
     * @param enemigos ListaEnemigos con los enemigos del encuentro
     */
    public void setEnemigos(ListaEnemigos enemigos) {
        this.enemigos = enemigos;
    }

    /**
     * Devuelve una lista con las recompensas del encuentro
     * @return ListaRecompensas con las recompensas del encuentro
     */
    public ListaRecompensas getRecompensas() {
        return recompensas;
    }

    /**
     * Fija la lista de recompensas del encuentro
     * @param recompensas ListaRecompensas con las recompensas del encuentro
     */
    public void setRecompensas(ListaRecompensas recompensas) {
        this.recompensas = recompensas;
    }
}
