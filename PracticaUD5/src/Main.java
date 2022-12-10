import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.crypto.spec.PSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    //variables globales
    static ListaRecompensas recompensas = new ListaRecompensas(true);
    static ListaEnemigos enemigos = new ListaEnemigos(true);

    static ListadoPersonajes personajes = new ListadoPersonajes(true);

    /**
     * Metodo principal de la ejecucion
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        System.out.println("--HOLA--");
        System.out.println("Bienvenido al generador de encuentros de Jon Maneiro García para D&D(Dungeons and Dragons)");
        System.out.println("Este generador generará encuentros aleatorios para tus partidas. ");
        System.out.println("No será muy util en casos reales, pero ha sido divertido de programar.");
        System.out.println("A continuacion habrá una serie de pasos a seguir, dependiendo de lo que quieras hacer.");
        System.out.println("Si se introduce 0 , volverá atras. Desde el menu principal, se sale de la ejecucion.");
        System.out.println("(Los de introduccion de datos no cuentan)");
        System.out.println("..Cargando..");
        //Thread.sleep(1500);//Si, he hecho esto para que parezca mas interesante
        boolean salir = false;
        int menuPrincipal = -1;
        int menuEncuentros = -1;
        int menuPersonajes = -1;
        int menuParty = -1;
        int menuCombate = -1;
        int menuConsultas = -1;

        while (!salir) {
            menuPrincipal = menuPrincipal();
            switch (menuPrincipal) {
                case 1://Encuentros
                    menuEncuentros = menuEncuentros();
                    switch (menuEncuentros) {
                        case 1://Generar encuentro
                            try {
                                escribirEncuentroAXML(generacionDeEncuentro());
                            } catch (FileNotFoundException e) {
                                System.out.println("No se ha encontrado el archivo, no debería de salir este error. ");
                                //throw new RuntimeException(e);
                            }
                            break;

                        case 2://Visualizar Encuentros
                            try {
                                presentarEncuentros(leerEncuentrosDeXML());
                            } catch (FileNotFoundException e) {
                                System.out.println("Ha ocurrido un error inesperado, por favor cierra el programa y" +
                                        "vuelvelo a intentar");
                                //throw new RuntimeException(e);
                            }
                            break;

                        case 3://Añadir Enemigo
                            try {
                                insertarEnemigo();
                            } catch (IOException e) {
                                System.out.println("Ha ocurrido un error inesperado, por favor, cierra el programa y " +
                                        "vuelvelo a intentar");
                                //throw new RuntimeException(e);
                            }
                            break;

                        case 4://Añadir Recompensa
                            try {
                                insertarRecompensa();
                            } catch (IOException e) {
                                System.out.println("Ha ocurrido un error inesperado, por favor, cierra el programa y " +
                                        "vuelvelo a intentar");
                                //throw new RuntimeException(e);
                            }
                            break;
                        case 5://Listar Enemigos
                            enemigos.ListarEnemigos();
                            break;
                        case 6://Listar Recompensas
                            recompensas.ListarRecompensas();
                            break;
                        case 0://Salir
                            break;
                    }
                    break;

                case 2://Personajes
                    menuPersonajes = menuPersonajes();
                    switch (menuPersonajes) {
                        case 1://Crear Personaje
                            crearPersonaje();
                            break;

                        case 2://Listar Personajes
                            personajes.listarPersonajes();
                            break;

                        case 3://Subir de nivel Personaje
                            subirNivel();
                            break;

                        case 0:
                            break;
                    }
                    break;

                case 3://Party
                    menuParty = menuParty();
                    switch (menuParty) {
                        case 1://Crear
                            crearGrupo();
                            break;
                        case 2://Borrar
                            break;
                        case 3://Listado
                            existOperaciones.leerGrupos();
                            break;
                        case 0:
                            break;
                    }
                    break;
                case 4://Combate
                    menuCombate = menuCombate();
                    switch (menuCombate) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 0:
                            break;
                    }
                    break;
                case 5://Consultas eXist
                    menuConsultas = menuConsultas();
                    switch (menuConsultas) {
                        case 1://Subir archivos a Exist !!IMPORTANTE!!
                            existOperaciones.subirArchivos();
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 0:
                            break;
                    }
                    break;
                case 0://Salir
                    salir = true;
                    break;
            }
        }
        System.out.println("--¡Suerte en tus aventuras!--");
        System.out.println("Si quieres saber más sobre D&D dejame recomendarte 5etools");
        System.out.println("https://5e.tools");
        System.out.println("..Cerrando programa..");
    }

    /**
     * Menu principal donde se escoje que se desea hacer; Encuentros , Personajes , Grupos, Combates o Consultas
     *
     * @return int indicador
     */
    public static int menuPrincipal() {
        boolean correcto = false;
        int selec = -1;
        while (!correcto) {
            System.out.println("--Bienvenido al Menu Principal--");
            System.out.println("¿Que deseas hacer?");
            System.out.println("1 - Encuentros");
            System.out.println("2 - Personajes");
            System.out.println("3 - Grupos");
            System.out.println("4 - Combates");
            System.out.println("5 - EXist");
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            if (isInt(respuesta)) {
                selec = Integer.parseInt(respuesta);
                if (selec >= 0 && selec <= 5) {
                    correcto = true;
                } else {
                    System.out.println("Numeros del 1 al 5(Con el 0 para salir) porfa");
                }
            } else {
                System.out.println("Parece que el dato introducido es incorrecto, vuelve a probar");
            }
        }
        return selec;
    }

    /**
     * Menu para escoger opcion a la hora de interactuar con las diferentes variables de los encuentros
     *
     * @return int indicador
     */
    public static int menuEncuentros() {
        boolean correcto = false;
        int selec = -1;
        while (!correcto) {
            System.out.println("--Bienvenido al Menu de Encuentros--");
            System.out.println("¿Que deseas hacer?");
            System.out.println("1 - Generar Encuentro");
            System.out.println("2 - Listar Encuentros generados");
            System.out.println("3 - Añadir un nuevo enemigo");
            System.out.println("4 - Añadir una nueva recompensa");
            System.out.println("5 - Listar Enemigos");
            System.out.println("6 - Listar Recompensas");
            System.out.println("Cualquiera de las listas es enorme y te vas a pasar el dia haciendo Scroll" +
                    ",creeme, funcionan");
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            if (isInt(respuesta)) {
                selec = Integer.parseInt(respuesta);
                if (selec >= 0 && selec <= 6) {
                    correcto = true;
                } else {
                    System.out.println("Numeros del 1 al 6(Con el 0 para salir) porfa");
                }
            } else {
                System.out.println("Parece que el dato introducido es incorrecto, vuelve a probar");
            }
        }
        return selec;
    }

    /**
     * Menu para escoger como interactuar con personajes
     *
     * @return int indicador
     */
    public static int menuPersonajes() {
        boolean correcto = false;
        int selec = -1;
        while (!correcto) {
            System.out.println("--Bienvenido al menu de Personajes--");
            System.out.println("1 - Crear Personaje");
            System.out.println("2 - Listado de Personajes");
            System.out.println("3 - Subir de nivel Personaje");
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            if (isInt(respuesta)) {
                selec = Integer.parseInt(respuesta);
                if (selec >= 0 && selec <= 3) {
                    correcto = true;
                } else {
                    System.out.println("Numeros del 1 al 3(Con el 0 para salir) porfa");
                }
            } else {
                System.out.println("Parece que el dato introducido es incorrecto, vuelve a probar");
            }
        }
        return selec;
    }

    public static int menuParty() {
        boolean correcto = false;
        int selec = -1;
        while(!correcto){
            System.out.println("--Bienvenido al menu de Grupo--");
            System.out.println("1 - Crear Grupo");
            System.out.println("2 - Borrar Grupo");
            System.out.println("3 - Listado Grupos");
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            if(isInt(respuesta)) {
                selec = Integer.parseInt(respuesta);
                if (selec >= 0 && selec <= 3) {
                    correcto = true;
                } else {
                    System.out.println("Numeros del 1 al 3(Con el 0 para salir) porfa");
                }
            }else{
                System.out.println("Parece que el dato introducido es incorrecto, vuelve a probar");
            }

        }
        return selec;
    }
    public static int menuCombate() {
        boolean correcto = false;
        int selec = -1;
        while(!correcto){
            System.out.println("--Bienvenido al menu de Combate--");
            System.out.println("Recuerda que antes de trabajar con Combates, tienes que subir los datos a la Coleccion" +
                    "\n mediante el metodo del menu Exist");
            System.out.println("1 - Crear Combate");
            System.out.println("2 - Borrar Combate");
            System.out.println("3 - Modificar Combate?¿?¿");
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            if(isInt(respuesta)) {
                selec = Integer.parseInt(respuesta);
                if (selec >= 0 && selec <= 3) {
                    correcto = true;
                } else {
                    System.out.println("Numeros del 1 al 3(Con el 0 para salir) porfa");
                }
            }else{
                System.out.println("Parece que el dato introducido es incorrecto, vuelve a probar");
            }

        }
        return selec;
    }
    public static int menuConsultas() {
        boolean correcto = false;
        int selec = -1;
        while(!correcto){
            System.out.println("--Bienvenido al menu de Exist--");
            System.out.println("Recordatorio de que para poder trabajar con datos actualizados, hay que subir los archivos" +
                    "\n cada vez que se cambie uno de los siguientes objetos:" +
                    "\n Encuentros, Grupos");
            System.out.println("1 - Subir archivos a Exist");
            System.out.println("2 - Borrar Grupo");
            System.out.println("3 - Modificar Grupo?¿?¿");
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            if(isInt(respuesta)) {
                selec = Integer.parseInt(respuesta);
                if (selec >= 0 && selec <= 3) {
                    correcto = true;
                } else {
                    System.out.println("Numeros del 1 al 3(Con el 0 para salir) porfa");
                }
            }else{
                System.out.println("Parece que el dato introducido es incorrecto, vuelve a probar");
            }

        }
        return selec;
    }

    /**
     * Se comprueba si la variable suministrada se puede convertir a int
     *
     * @param check String a comprobar
     * @return boolean yes/no
     */
    public static boolean isInt(String check) {
        try {
            Integer.parseInt(check);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Se comprueba si la variable suministrada se puede convertir a long
     *
     * @param check String a comprobar
     * @return boolean yes/no
     */
    public static boolean isLong(String check) {
        try {
            Long.parseLong(check);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Metodo para hacer preguntas que requieran un (Y)es / (N)o por parte del usuario
     *
     * @return true o false
     */
    public static boolean yesNo() {
        Scanner sc = new Scanner(System.in);
        String check = "";
        boolean resp = false;
        boolean correcto = false;
        while (!correcto) {
            check = sc.nextLine();
            if (check.equalsIgnoreCase("Y")) {
                resp = true;
                correcto = true;
            } else if (check.equalsIgnoreCase("N")) {
                resp = false;
                correcto = true;
            } else {
                System.out.println("Parece que no has introducido una opcion valida, vuelve a intentarlo");
            }
        }

        return resp;
    }

    /**
     * Metodo de utilidad para devolver el texto suministrado con la longitud requerida
     *
     * @param texto    el texto a modificar
     * @param longitud la longitud deseada
     * @return texto con longitud deseada
     */
    private static String obtenerStringCompleto(String texto, int longitud) {
        String modif = texto;
        if (modif.length() < longitud) {
            while (modif.length() < longitud) {
                modif = modif + " ";
            }
        } else if (modif.length() > longitud) {
            modif = modif.substring(0, (longitud - 1));
        }

        return modif;
    }

    //_------------------------------------------------------------------------------------------------------------_

    public static void crearGrupo(){
        Scanner sc = new Scanner(System.in);
        //Listamos los personajes primero
        personajes.listarPersonajes();
        String check = "";
        boolean correcto = false;
        int id = 0;


        String nombreGrupo = "";
        System.out.println("Introduce el nombre del grupo");
        nombreGrupo = sc.nextLine();

        Grupo grp = new Grupo(nombreGrupo);


        System.out.println("A continuacion se van a pedir ids de personajes para agruparlos en un grupo");
        correcto = false;
        while(!correcto){
            System.out.println("Introduce el ID del personaje que quieres agregar al grupo(Se permiten duplicados)");
            check = sc.nextLine();
            if(isInt(check)){
                id = Integer.parseInt(check);
                boolean existe = false;
                for(Personaje p: personajes.getPersonajes()){
                    if(p.getId() == id){
                        existe = true;
                        grp.addPJ(p);
                        break;
                    }
                }
                if(!existe){
                    System.out.println("El id introducido no corresponde a ningun personaje");
                }else{
                    System.out.println("Se ha agregado el personaje al grupo");
                    System.out.println("Deseas introducir mas personajes?");
                    boolean mas = yesNo();
                    if(!mas){
                        correcto = true;
                    }
                }
            }else{
                System.out.println("Parece que no has introducido un numero, prueba de nuevo");
            }
        }

        grp.calcularMedias();//Se calculas las medias de las estadisticas del grupo

        ///Ahora pasamos a la insercion en xml del grupo
        existOperaciones.insertarGrupo(grp);

    }


    /**
     * Se piden datos para posteriormente subir de nivel a un personaje con su propio metodo
     */
    @Deprecated
    public static void subirNivel() {

        Scanner sc = new Scanner(System.in);
        String check = "";
        boolean correcto = false;
        int id = 0;


        while (!correcto) {
            System.out.println("Introduce el id del personaje al que quieres subirle el nivel");
            check = sc.nextLine();
            if (isInt(check)) {
                boolean temp = false;
                id = Integer.parseInt(check);
                for (Personaje pj : personajes.getPersonajes()) {
                    if (pj.getId() == id) {
                        correcto = true;
                        temp = true;
                        break;
                    } else {
                        temp = false;
                    }
                }
                if (temp == false) {
                    System.out.println("Parece que el personaje que buscabas no existe");
                }
            } else {
                System.out.println("Parece que no has introducido un numero, prueba otra vez");
            }
        }

        if (correcto) {
            personajes.subirNivel(id);
        }

    }

    /**
     * Se introducen los datos necesarios para crear un personaje
     */
    public static void crearPersonaje() {

        Scanner sc = new Scanner(System.in);
        String check = "";
        boolean correcto = false;

        String nombre;//Max 50
        String clase;//Max 50
        String raza;//Max 50
        int hitDie = 0;
        int nivel = 0;
        int vida = 0;
        int Str;
        int Dex;
        int Con;
        int Int;
        int Wis;
        int Cha;


        System.out.println("Introduce el nombre del Personaje(Max 50 caracteres, el texto se cortará)");
        nombre = sc.nextLine();
        System.out.println("Introduce la clase del Personaje(Max 50 caracteres)");
        clase = sc.nextLine();
        System.out.println("Introduce la raza del Personaje(Max 50 caracteres)");
        raza = sc.nextLine();

        while (!correcto) {
            System.out.println("Introduce el dado de golpe que usa tu pj(6,8,10,12):");
            check = sc.nextLine();
            if (isInt(check)) {
                int temp = Integer.parseInt(check);
                if (temp == 6 || temp == 8 || temp == 10 || temp == 12) {
                    hitDie = temp;
                    correcto = true;
                } else {
                    System.out.println("Parece que no has introducido ningun numero esperado");
                    System.out.println("¿Deseas continuar con el dado elegido?(Y/N)");
                    boolean si = yesNo();
                    if (si) {
                        hitDie = temp;
                        correcto = true;
                    } else {
                        correcto = false;
                    }
                }
            } else {
                System.out.println("Parece que no has introducido un numero, prueba de nuevo");
                correcto = false;
            }
        }
        correcto = false;
        while (!correcto) {
            System.out.println("Introduce el nivel del personaje(Entre 1 y 20):");
            check = sc.nextLine();
            if (isInt(check)) {
                int temp = Integer.parseInt(check);
                if (temp > 0 && temp <= 20) {
                    nivel = temp;
                    correcto = true;
                } else {
                    System.out.println("El nivel que has introducido no es posible");
                    correcto = false;
                }
            } else {
                System.out.println("Parece que no has introducido un numero, prueba de nuevo");
                correcto = false;
            }
        }

        correcto = false;
        while (!correcto) {
            System.out.println("Introduce la vida maxima actual del personaje");
            check = sc.nextLine();
            if (isInt(check)) {
                vida = Integer.parseInt(check);
                correcto = true;
            } else {
                System.out.println("Parece que no has introducido un numero, prueba otra vez");
            }
        }

        System.out.println("Ahora tocan las estadisticas. Como minimo pueden ser 0, 10 es lo que rondaria una persona estandar" +
                "\n y el maximo normalmente es 20" +
                "\n (Lo que no quiere decir que no puedan ir más allá con magia)");
        Str = recibirEstadistica("Fuerza");
        Dex = recibirEstadistica("Destreza");
        Con = recibirEstadistica("Constitucion");
        Int = recibirEstadistica("Inteligencia");
        Wis = recibirEstadistica("Sabiduria");
        Cha = recibirEstadistica("Carisma");

        Personaje pj = new Personaje(nombre, nivel, clase, hitDie, raza, Str, Dex, Con, Int, Wis, Cha, vida);
        try {
            personajes.insertarNuevoPersonaje(pj);
        } catch (IOException e) {
            System.out.println("Ha ocurrido una excepcion inesperada, por favor, cierra el programa e intentalo de nuevo");
            //throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que se utiliza para no repetir codigo a la hora de introducir las estadisticas de un personaje
     *
     * @param nombre nombre de la estadistica a introducir
     * @return valor introducido
     */
    public static int recibirEstadistica(String nombre) {
        Scanner sc = new Scanner(System.in);
        String check = "";
        int stat = 0;
        boolean correcto = false;
        while (!correcto) {
            System.out.println("Introduce : " + nombre);
            check = sc.nextLine();
            if (isInt(check)) {
                int temp = Integer.parseInt(check);
                if (temp > -1) {
                    correcto = true;
                    stat = temp;
                } else {
                    System.out.println("Las estadisticas no pueden ser menores a 0");
                }
            } else {
                System.out.println("Parece que lo que has introducido no es un numero, vuelve a intentarlo");
            }
        }

        return stat;
    }

    /**
     * Se piden al usuario las variables necesarias para gestionar la creacion de un encuentro a medida
     *
     * @return Encuentro generado
     */
    public static Encuentro generacionDeEncuentro() {

        Scanner sc = new Scanner(System.in);
        String check = "";
        boolean correcto = false;

        int numeroJugadores = 0;
        int nivelJugadores = 0;
        int dificultad = 0;
        long crMaximoEncuentro = 0;

        while (!correcto) {
            System.out.println("Introduce el numero de jugadores(La media suele rondar los 4):");
            check = sc.nextLine();
            if (isInt(check)) {
                numeroJugadores = Integer.parseInt(check);
                correcto = true;
            } else {
                System.out.println("Parece que no has introducido un numero, prueba de nuevo");
                correcto = false;
            }
        }
        correcto = false;
        while (!correcto) {
            System.out.println("Introduce el nivel de los jugadores(Entre 1 y 20):");
            check = sc.nextLine();
            if (isInt(check)) {
                int temp = Integer.parseInt(check);
                if (temp > 0 && temp <= 20) {
                    nivelJugadores = temp;
                    correcto = true;
                } else {
                    System.out.println("El nivel que has introducido no es posible");
                    correcto = false;
                }
            } else {
                System.out.println("Parece que no has introducido un numero, prueba de nuevo");
                correcto = false;
            }
        }
        correcto = false;
        while (!correcto) {
            System.out.println("Introduce la dificultad del encuentro(1-facil,2-normal,3-dificil,4-mortal):");
            check = sc.nextLine();
            if (isInt(check)) {
                int temp = Integer.parseInt(check);
                if (temp <= 4 && temp >= 1) {
                    dificultad = temp;
                    correcto = true;
                } else {
                    System.out.println("El numero introducido no es valido");
                    correcto = false;
                }
            } else {
                System.out.println("Parece que no has introducido un numero, prueba de nuevo");
                correcto = false;
            }
        }

        correcto = false;
        while (!correcto) {
            System.out.println("Introduce el cr maximo de los monstruos para el encuentro:");
            System.out.println("(Los libros dicen que 4 personajes de nivel x pueden contra 1 monstruo de nivel x)");
            check = sc.nextLine();
            if (isLong(check)) {
                long temp = Long.parseLong(check);
                if (temp > -0.0009) {
                    crMaximoEncuentro = temp;
                    correcto = true;
                } else {
                    System.out.println("Parece que el numero que has introducido es demasiado pequeño");
                    correcto = false;
                }
            } else {
                System.out.println("Parece que no has introducido un numero, prueba de nuevo");
                correcto = false;
            }
        }

        GeneradorEncuentros gen = new GeneradorEncuentros(dificultad, numeroJugadores, nivelJugadores, crMaximoEncuentro);

        return gen.generarEncuentro();

    }


    /**
     * Plasma un encuentro al XML Encuentros.xml. Si ya existe un xml, primero lee todos, añade el nuevo, y vuelca la lista completa
     *
     * @param encuentro el encuentro a insertar
     * @throws FileNotFoundException
     */
    public static void escribirEncuentroAXML(Encuentro encuentro) throws FileNotFoundException {
        ListaEncuentros encuentros = new ListaEncuentros();
        encuentros.add(encuentro);
        File file = new File("Encuentros.xml");
        if (file.exists()) {
            ListaEncuentros temp = leerEncuentrosDeXML();

            for (Encuentro enc : temp.getEncuentros()) {
                encuentros.add(enc);
            }
        }
        try {

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

            xstream.toXML(encuentros, new FileOutputStream("Encuentros.xml"));

        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivo \"Encuentros.xml\n y/o no se puede crear");
            e.printStackTrace();
        }

    }

    public static void escribirGrupoAXML(Grupo grupo) throws FileNotFoundException {
        ListadoGrupos grupos = new ListadoGrupos(false);
        grupos.add(grupo);

        File file = new File("Grupos.xml");
        if(file.exists()){
            ListadoGrupos temp = leerGruposDeXML();//existOperaciones.leerGruposdeExist();//HAY QUE HACER ESTO AUAAAAAA
            for(Grupo grp: temp.getGrupos()){
                grupos.add(grp);
            }
        }
        try {
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("Grupos" , ListadoGrupos.class);
            xstream.alias("grupo" , Grupo.class);
            xstream.alias("Personajes" , ListadoPersonajes.class);
            xstream.alias("personaje" , Personaje.class);
            //xstream.useAttributeFor(Grupo.class,"nombre");
            //xstream.aliasField("nombre",Grupo.class, "nombre");
            xstream.processAnnotations(Grupo.class);
            xstream.addImplicitCollection(ListadoGrupos.class,"grupos");
            xstream.addImplicitCollection(ListadoPersonajes.class,"personajes");

            xstream.toXML(grupos, new FileOutputStream("Grupos.xml"));
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo \"Grupos.xml\n y/o no se puede crear");
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    /**
     * Devuelve una ListaEncuentros con los encuentros del XML
     *
     * @return Lista encuentros con los encuentros del XML
     * @throws FileNotFoundException
     */
    public static ListaEncuentros leerEncuentrosDeXML() throws FileNotFoundException {


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

        FileInputStream fichero = new FileInputStream("Encuentros.xml");
        ListaEncuentros encuentros = (ListaEncuentros) xstream.fromXML(fichero);


        //Leer esto con un Iterator
        return encuentros;
    }

    public static ListadoGrupos leerGruposDeXML() throws FileNotFoundException {

        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("Grupos" , ListadoGrupos.class);
        xstream.alias("grupo" , Grupo.class);
        xstream.alias("Personajes" , ListadoPersonajes.class);
        xstream.alias("personaje" , Personaje.class);
        //xstream.useAttributeFor(Grupo.class,"nombre");
        //xstream.aliasField("nombre",Grupo.class, "nombre");
        xstream.processAnnotations(Grupo.class);

        FileInputStream fichero = new FileInputStream("Grupos.xml");
        ListadoGrupos grupos = (ListadoGrupos) xstream.fromXML(fichero);

        //Leer esto con un iterator
        return grupos;
    }

    /**
     * Saca por pantalla la ListaEncuentros suministrada
     *
     * @param listaEncuentros ListaEncuentros a sacar por pantalla
     */
    public static void presentarEncuentros(ListaEncuentros listaEncuentros) {

        List<Encuentro> encuentros = new ArrayList<Encuentro>();
        encuentros = listaEncuentros.getEncuentros();

        Iterator iteradorEncuentros = encuentros.listIterator();

        while (iteradorEncuentros.hasNext()) {
            Encuentro encounter = (Encuentro) iteradorEncuentros.next();
            System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
            System.out.println("Numero de Jugadores: " + encounter.getNumeroPJ());
            System.out.println("Nivel de Jugadores: " + encounter.getNivelPJ());

            ListaEnemigos lE = new ListaEnemigos();
            List<Enemigo> enemigos = new ArrayList<Enemigo>();
            lE = encounter.getEnemigos();
            enemigos = lE.getEnemigos();

            Iterator iteradorEnemigos = enemigos.listIterator();
            System.out.println("Lista de enemigos");
            while (iteradorEnemigos.hasNext()) {
                System.out.println("-----------------------------");
                Enemigo enemy = (Enemigo) iteradorEnemigos.next();
                System.out.println("Id: " + enemy.getId());
                System.out.println("Nombre: " + enemy.getNombre());
                System.out.println("Tipo: " + enemy.getTipo());
                System.out.println("Cr: " + enemy.getCr());
                System.out.println("Xp: " + enemy.getXp());
            }

            ListaRecompensas lR = new ListaRecompensas();
            List<Recompensa> recompensas = new ArrayList<>();
            lR = encounter.getRecompensas();
            recompensas = lR.getRecompensas();
            System.out.println("Recompensas");
            System.out.println("-------------------");
            System.out.println("Oro otorgado:" + lR.getOroEntregable());
            Iterator iteradorRecompensas = recompensas.listIterator();
            while (iteradorRecompensas.hasNext()) {
                System.out.println("-----------------------------");
                Recompensa recompensa = (Recompensa) iteradorRecompensas.next();
                System.out.println("Id: " + recompensa.getId());
                System.out.println("Nombre: " + recompensa.getNombre());
                System.out.println("Tipo: " + recompensa.getTipo());
                System.out.println("Rareza: " + recompensa.getRareza());

            }
        }

    }

    /**
     * Pide datos por pantalla para introducir una recompensa
     *
     * @throws IOException
     */
    public static void insertarRecompensa() throws IOException {
        /**
         * Id, int de 4 bytes
         * Nombre, cadena de 50 caracteres , 100 bytes
         * Tipo, cadena de 15 caracteres , 30 bytes
         * rareza, int de 8 bytes
         * Total, 142 bytes
         *
         */
        Scanner sc = new Scanner(System.in);

        String nombre = "";
        String tipo = "";
        int rareza = 0;

        System.out.println("--Se va a proceder con la inserción de una nueva recompensa a la lista interna--");

        System.out.println("Introduce el nombre del objeto(MAX 50 chars, si sobrepasa, se trunca)");
        nombre = obtenerStringCompleto(sc.nextLine(), 50);

        System.out.println("Introduce el tipo del objeto(MAX 15 char)");
        tipo = obtenerStringCompleto(sc.nextLine(), 15);

        System.out.println("Por ultimo, introduce la rareza--->");
        System.out.println("1-Comun");
        System.out.println("2-Poco Comun");
        System.out.println("3-Raro");
        System.out.println("4-Muy Raro");
        System.out.println("5-Legendario");
        System.out.println("6-Artefacto");
        boolean correcto = false;//Usado para los bucles de insercion
        while (!correcto) {
            try {
                rareza = Integer.parseInt(sc.nextLine());
                if (rareza >= 1 && 6 >= rareza) {
                    correcto = true;
                } else {
                    System.out.println("Parece que no has introducido un numero valido");
                    System.out.println("Por favor introduce de nuevo el dato");
                    correcto = false;
                }

            } catch (NumberFormatException e) {
                System.out.println("Parece que lo que has introducido no es correcto");
                System.out.println("Por favor introduce de nuevo el dato");
                correcto = false;
            }
        }

        recompensas.insertarNuevaRecompensa(nombre, tipo, rareza);


    }

    /**
     * Pide datos por pantalla para introducir un enemigo
     *
     * @throws IOException
     */
    public static void insertarEnemigo() throws IOException {
        /**
         * Id, int de 4 bytes
         * Nombre, cadena de 50 caracteres, 100 Bytes
         * Tipo, cadena de 11 caracteres, 22 bytes
         * CR, long de 8 bytes
         * XP, int de 8 bytes
         * Total, 142 bytes
         */

        Scanner sc = new Scanner(System.in);

        String nombre = "";
        String tipo = "";
        long cr = 0;
        int xp = 0;

        System.out.println("--Se va a proceder con la inserción de un monstruo nuevo a la lista interna--");

        System.out.println("Introduce el nombre del monstruo(MAX 50 chars, si se sobrepasa, se trunca)");
        nombre = obtenerStringCompleto(sc.nextLine(), 50);

        System.out.println("Introduce el tipo del monstruo(MAX 11 chars)");
        tipo = obtenerStringCompleto(sc.nextLine(), 11);


        System.out.println("Introduce la Clase de Desafio(Challenge Rating) dle monstruo, de 0 a 20");
        System.out.println("Existen, del 0 al 1 los siguientes:");
        System.out.println("1/8 : 0.125");
        System.out.println("1/4 : 0.25");
        System.out.println("1/2 : 0.50");
        boolean correcto = false;
        while (!correcto) {
            try {
                cr = Long.parseLong(sc.nextLine());
                if (cr >= 0 && 20 >= cr) {
                    correcto = true;
                } else {
                    System.out.println("Parece que no has introducido un numero valido");
                    System.out.println("Por favor introduce de nuevo el dato");
                    correcto = false;
                }

            } catch (NumberFormatException e) {
                System.out.println("Parece que lo que has introducido no es correcto");
                System.out.println("Por favor introduce de nuevo el dato");
                correcto = false;
            }
        }

        System.out.println("Introduce la experiencia que otorga el monstruo al derrotarlo");
        System.out.println("Para hacerse una idea de cuanta xp puede otorgar, consultar el Excel");
        correcto = false;
        while (!correcto) {
            try {
                xp = Integer.parseInt(sc.nextLine());
                correcto = true;
            } catch (NumberFormatException e) {
                System.out.println("Parece que lo que has introducido no es correcto");
                System.out.println("Por favor introduce de nuevo el dato");
                correcto = false;
            }
        }

        enemigos.insertarNuevoEnemigo(nombre, tipo, cr, xp);

    }


}