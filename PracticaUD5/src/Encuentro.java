import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class Encuentro implements Serializable {
    /**
     * Esta clase solo debería usarse para pasar los encuentros entre el programa y XML
     * todas las demás operaciones deberán hacerse instanciando un objeto
     * de la clase necesaria.
     */

    private int numeroPJ;
    private int nivelPJ;

    @XStreamAlias("id")
    @XStreamAsAttribute()
    int id;

    private ListaEnemigos enemigos;
    private ListaRecompensas recompensas;


    public Encuentro(int id,int numeroPJ, int nivelPJ, ListaEnemigos enemigos, ListaRecompensas recompensas) {
        this.id = id;
        this.numeroPJ = numeroPJ;
        this.nivelPJ = nivelPJ;
        this.enemigos = enemigos;
        this.recompensas = recompensas;
    }

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

    public void obtenerId(){


        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("Encuentros", ListaEncuentros.class);
        xstream.alias("encuentro", Encuentro.class);
        xstream.alias("Enemigos", ListaEnemigos.class);
        xstream.alias("enemigo", Enemigo.class);
        xstream.alias("Recompensas", ListaRecompensas.class);
        xstream.alias("recompensa", Recompensa.class);
        xstream.processAnnotations(ListaRecompensas.class);
        //xstream.addImplicitCollection(ListaEncuentros.class, "lista");

        FileInputStream fichero = null;
        try {
            fichero = new FileInputStream("Encuentros.xml");
        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el archivo Encuentros.xml");
            throw new RuntimeException(e);
        }
        ListaEncuentros encuentros = (ListaEncuentros) xstream.fromXML(fichero);

        this.id = encuentros.getEncuentros().size();

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
