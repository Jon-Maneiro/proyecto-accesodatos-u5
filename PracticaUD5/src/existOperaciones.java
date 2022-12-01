import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import org.xmldb.api.base.Collection;
import org.xmldb.api.modules.XMLResource;

public class existOperaciones {

    //public static final String nombreFichero = "";

    static String driver = "org.exist.xmldb.DatabaseImpl";//Diver de exist

    static String URI = "xmldb:exist://localhost:8083/exist/xmlrpc/db/ProyectoUD5";//URI de la coleccion

    static String usu = "admin";

    static String pass = "12345Abcde";

    static Collection col = null;

    public existOperaciones() {
    }

    public static Collection conectar() {

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.getDeclaredConstructor().newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, pass);
            return col;
        } catch (XMLDBException e) {
            System.out.println("Error al inicializar la BD eXist.");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error en el driver.");
            //e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("Error al instanciar la BD.");
            //e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Error al instanciar la BD.");
            //e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sube los archivos xml del proyecto a la coleccion de eXist
     */
    public static void subirArchivos(){
        if(conectar() != null){
            try{

                XMLResource res = null;

                res = (XMLResource) col.createResource("Encuentros.xml","XMLResource");
                File file = new File("Encuentros.xml");
                res.setContent(file);
                col.storeResource(res);

                for(String x: col.listResources()){
                    System.out.println(x);
                }



                col.close();
            } catch (XMLDBException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
    }
}
