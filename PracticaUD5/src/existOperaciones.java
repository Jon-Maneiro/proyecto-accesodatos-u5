import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

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


                /*res = (XMLResource) col.createResource("Grupos.xml","XMLResource");
                file = new File("Grupos.xml");
                res.setContent(file);
                col.storeResource(res);*/

                for(String x: col.listResources()){
                    System.out.println(x);
                }

                System.out.println("Se han subido los archivos satisfactoriamente");

                col.close();
            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al intentar subir los datos");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
    }

    public static void insertarGrupo(Grupo grupo){
        String nuevoGrupo = "<Grupo nombre=\""+grupo.nombre+"\"> <Personajes>";

        for(Personaje p:grupo.ls.getPersonajes()){
            nuevoGrupo = nuevoGrupo + "<personaje>" +
                    "<nombre>"+p.getNombre()+"</nombre>" +
                    "<clase>"+p.getClase()+"</clase>" +
                "<raza>"+p.getRaza()+"</raza>" +
                "<nivel>"+p.getNivel()+"</nivel>" +
                "<Estadisticas>" +
                "<STR>"+p.getStr()+"</STR>" +
                "<DEX>"+p.getDex()+"</DEX>" +
                "<CON>"+p.getCon()+"</CON>" +
                "<INT>"+p.getInt()+"</INT>" +
                "<WIS>"+p.getWis()+"</WIS>" +
                "<CHA>"+p.getCha()+"</CHA>" +
                "</Estadisticas></personaje>";
        }

        nuevoGrupo = nuevoGrupo + "</Personajes><MediaEstadisticas>" +
                "<MedSTR>"+grupo.getMedSTR()+"</MedSTR>" +
                "<MedDEX>"+grupo.getMedDEX()+"</MedDEX>" +
                "<MedCON>"+grupo.getMedCON()+"</MedCON>" +
                "<MedINT>"+grupo.getMedINT()+"</MedINT>" +
                "<MedWIS>"+grupo.getMedWIS()+"</MedWIS>" +
                "<MedCHA>"+grupo.getMedCHA()+"</MedCHA></MediaEstadisticas></Grupo>";

        if(conectar() != null){
            try{
                XPathQueryService srv = (XPathQueryService) col.getService("XPathQueryService","1.0");

                ResourceSet result = srv.query("update insert " + nuevoGrupo + "into /Grupos");
                col.close();
                System.out.println("Se ha introducido el nuevo grupo");

            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al insertar el grupo");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }

    }

    public static void insertarCombate(int idEncuentro, String nombreGrupo, boolean victoria){

            String nuevoCombate = "update insert \n" +
                    "<combate id=\""+leerCombates(false)+"\">\n" +
                    "<resultado>"+(victoria == true ? "victoria" : "derrota" )+"</resultado>\n" +
                    "{for $grupo in /Grupos/Grupo[@nombre=\""+nombreGrupo+"\"]\n" +
                    "return $grupo}"+
                    "{for $encuentro in /Encuentros/encuentros/encuentro[@id = \""+idEncuentro+"\"]\n" +
                    "return $encuentro}\n" +
                    "</combate>\n" +
                    "into /Combates";


        if(conectar() != null){
            try{
                XPathQueryService srv = (XPathQueryService) col.getService("XPathQueryService" , "1.0");
                 ResourceSet result = srv.query(nuevoCombate);
                 col.close();
                System.out.println("Se ha introducido el nuevo combate");
            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al insertar el grupo");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
    }

    public static void deleteGrupo(String nombreGrupo){

        if(conectar()!=null){
            try{
                XPathQueryService srv = (XPathQueryService)  col.getService("XPathQueryService" , "1.0");
                ResourceSet result = srv.query("update delete /Grupos/Grupo[@nombre=\""+nombreGrupo+"\"]");
                col.close();
            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al eliminar el grupo");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }


    }

    public static void updateGrupo(String nombreViejo, String nombreNuevo){
        if(conectar() != null){
            try{

                //Las comillas son un posible error
                XPathQueryService srv = (XPathQueryService)  col.getService("XPathQueryService" , "1.0");
                ResourceSet result = srv.query("for $grupo in /Grupos/Grupo[@nombre = \""+nombreViejo+"\"]" +
                        "return update value $grupo/@nombre with '"+nombreNuevo+"' ");
                col.close();
            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un problema al actualizar el grupo");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
    }

    public static void updateCombate(int id,boolean victoria){
        if(conectar() != null){
            try{
                XPathQueryService srv = (XPathQueryService)  col.getService("XPathQueryService" , "1.0");
                ResourceSet result = srv.query("for $combate in /Combates/combate[@id=\""+id+"\"]" +
                        "return update value $combate/resultado with '"+(victoria == true ? "victoria" : "derrota" )+"' ");
                col.close();
            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un problema al actualizar el combate");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
    }

    public static void deleteCombate(int idCombate){

        if(conectar() != null){
            try{
                XPathQueryService srv = (XPathQueryService)  col.getService("XPathQueryService" , "1.0");
                ResourceSet result = srv.query("update delete /Combates/combate[@id="+idCombate+"]");
                col.close();
            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al eliminar el combate");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }

    }

    public static void leerGrupos(){
        if(conectar() != null){
            try{
                XPathQueryService srv;
                srv = (XPathQueryService) col.getService("XPathQueryService", "1.0");

                ResourceSet result = srv.query("for $grupo in /Grupos/Grupo return $grupo");

                ResourceIterator i;
                i = result.getIterator();
                if(!i.hasMoreResources()){
                    System.out.println("No se encuentran datos. Si existen datos en la BBDD es que hay error");
                }

                while(i.hasMoreResources()){
                    Resource r = i.nextResource();
                    System.out.println("-----------------------------------------------------------");
                    System.out.println((String) r.getContent());
                }
                col.close();

            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al intentar crear el servicio");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
    }

    public static void consultar(String consulta){
        if(conectar() != null){
            try{
                XPathQueryService srv = (XPathQueryService)  col.getService("XPathQueryService" , "1.0");

                ResourceSet result = srv.query(consulta);
                ResourceIterator i;
                i = result.getIterator();
                if(!i.hasMoreResources()){
                    System.out.println("No se encuentran datos. Si existen datos en la BBDD es que hay error");
                }

                while(i.hasMoreResources()){
                    Resource r = i.nextResource();
                    System.out.println("------------------------------------------------------------");
                    System.out.println((String) r.getContent());
                }
                col.close();

            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al intentar crear el servicio");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
    }


    public static int leerCombates(boolean mostrar){
        int numeroCombates = 0;
        if(conectar() != null) {
            try{
                XPathQueryService srv;
                srv = (XPathQueryService) col.getService("XPathQueryService", "1.0");

                ResourceSet result = srv.query("for $combate in /Combates/combate return $combate");

                ResourceIterator i;
                i = result.getIterator();
                if(!i.hasMoreResources()){
                    System.out.println("No se encuentran datos. Si existen datos en la BBDD es que hay error");
                }
                numeroCombates = (int)result.getSize();
                if(mostrar) {
                    while (i.hasMoreResources()) {
                        //numeroCombates++;

                        Resource r = i.nextResource();
                        System.out.println("-----------------------------------------------------------");
                        System.out.println((String) r.getContent());

                    }
                }
                col.close();
            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al intentar crear el servicio");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
        return numeroCombates;
    }

    public static boolean grupoExiste(String nombre){
        if(conectar() != null){
            try{
                XPathQueryService srv;
                srv = (XPathQueryService) col.getService("XPathQueryService", "1.0");

                ResourceSet result = srv.query("for $nombre in /Grupos/Grupo/data([@nombre]) return $nombre");

                ResourceIterator i;
                i = result.getIterator();
                if(!i.hasMoreResources()){
                    System.out.println("No se encuentran datos. Si existen datos en la BBDD es que hay error");
                }

                boolean existe = false;
                while(i.hasMoreResources()){
                    Resource r = i.nextResource();
                    if(((String)r.getContent()).equalsIgnoreCase(nombre)){
                        existe = true;
                    }
                }

                if(existe){
                    return true;
                }else{
                    return false;
                }

            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al intentar crear el servicio");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
        return false;//Si se devuelve esto es por errores
    }

    public static boolean combateExiste(int id){
        if(conectar() != null){
            try{
                XPathQueryService srv = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                ResourceSet result = srv.query("for $combate in /Combates/combate/data(@id) return $combate");

                ResourceIterator i;
                i = result.getIterator();
                if(!i.hasMoreResources()){
                    System.out.println("No se encuentran datos. Si existen datos en la BBDD es que hay error");
                }
                boolean existe = false;
                while(i.hasMoreResources()){
                    Resource r = i.nextResource();
                    if(Integer.parseInt((String)r.getContent()) == id){
                        existe = true;
                    }
                }

                boolean bool = existe == true ? true : false;
                return bool;

            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al intentar crear el servicio");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
        return false;
    }
    public static boolean encuentroExiste(int id){
        if(conectar() != null){
            try{
                XPathQueryService srv;
                srv = (XPathQueryService) col.getService("XPathQueryService" , "1.0");

                ResourceSet result = srv.query("for $id in /Encuentros/encuentros/encuentro/data([@id]) return $id");

                ResourceIterator i;
                i = result.getIterator();
                if(!i.hasMoreResources()){
                    System.out.println("No se encuentran datos. Si existen datos en la BBDD es que hay error");
                }

                boolean existe = false;
                while(i.hasMoreResources()){
                    Resource r = i.nextResource();
                    if(Integer.parseInt((String)r.getContent()) == id){
                        existe = true;
                    }
                }

                if(existe){
                    return true;
                }else{
                    return false;
                }

            } catch (XMLDBException e) {
                System.out.println("Ha ocurrido un error al intentar crear el servicio");
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Se ha producido un error en la conexion.\n Comprueba las variables de conexion");
        }
        return false;
    }



}
