import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ListaEnemigos implements Serializable {

    private ArrayList<Enemigo> enemigos = new ArrayList<>();

    /**
     * Constructor de la clase ListaEnemigos con opcion a llenar la lista de enemigos
     * @param llenar boolean llenar o no la lista de enemigos
     */
    public ListaEnemigos(boolean llenar) {
        if (llenar == true) {
            llenarListaEnemigos();
        }
    }

    /**
     * Constructor vacio de ListaEnemigos
     */
    public ListaEnemigos(){}

    /**
     * Saca los enemigos de la instancia por pantalla
     */
    public void ListarEnemigos() {
        for(Enemigo en: enemigos){
            System.out.println("-----------");
            System.out.println("Id: " + en.getId() + "Nombre: " + en.getNombre() + "Tipo: " + en.getTipo()
            + "CR: " + en.getCr() + "XP: " + en.getXp());
            System.out.println("-----------");
        }
    }


    /**
     * Leer los datos de la lista de enemigos.dat.
     * Si el archivo de Enemigos.dat no existe, se creará en base al fichero excel proporcionado en la carpeta
     */
    public void llenarListaEnemigos() {
        try {
            //Primero comprobamos que el enemigos.dat existe, y si no existe
            //se crea y se rellena con los datos del excel
            File fEnemigos = new File("enemigos.dat");
            if (!fEnemigos.exists()) {
                fEnemigos = leerExcel(fEnemigos);
            }
            //Ahora se lee el fichero y se recuperan los datos
            /**
             * Id, int de 4 bytes
             * Nombre, cadena de 50 caracteres, 100 Bytes
             * Tipo, cadena de 11 caracteres, 22 bytes
             * CR, long de 8 bytes
             * XP, int de 4 bytes
             * Total, 138 bytes
             */
            RandomAccessFile fichero = new RandomAccessFile(fEnemigos, "rw");
            int id;
            char[] nombre;
            char[] tipo;
            long cr;
            int xp;
            long longitud = fichero.length();
            fichero.seek(0);
            //Leemos el fichero y llenamos el arrayList de enemigos para
            //poder trabajar con el
            while (fichero.getFilePointer() < longitud) {

                nombre = new char[50];
                tipo = new char[11];

                id = fichero.readInt();
                for (int x = 0; x < 50; x++) {
                    nombre[x] = fichero.readChar();
                }
                for (int x = 0; x < 11; x++) {
                    tipo[x] = fichero.readChar();
                }
                cr = fichero.readLong();
                xp = fichero.readInt();
                enemigos.add(new Enemigo(id, new String(nombre), new String(tipo), cr, xp));
            }


            fichero.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Se inserta un nuevo enemigo en el .dat y se añade al arraylist de la ejecucion
     * @param nombre nombre del enemigo
     * @param tipo tipo de criatura
     * @param cr clase de dificultad
     * @param xp experiencia que otorga
     * @throws IOException
     */
    public void insertarNuevoEnemigo(String nombre, String tipo, long cr, int xp) throws IOException {
        /**
         * Id, int de 4 bytes
         * Nombre, cadena de 50 caracteres, 100 Bytes
         * Tipo, cadena de 11 caracteres, 22 bytes
         * CR, long de 8 bytes
         * XP, int de 4 bytes
         * Total, 138 bytes
         */
        File file = new File("enemigos.dat");
        RandomAccessFile fichero = new RandomAccessFile(file, "rw");


        //Primero hay que sacar el ID correspondiente al monstruo
        long longitud = fichero.length();
        fichero.seek(longitud - 138);//Aqui hay una posible zona de bug, checkear
        int id = fichero.readInt() + 1;//Tenemos el id?¿


        fichero.seek(longitud);

        fichero.writeInt(id);
        fichero.writeChars(nombre);
        fichero.writeChars(tipo);
        fichero.writeLong(cr);
        fichero.writeInt(xp);

        fichero.close();

        enemigos.add(new Enemigo(id, nombre, tipo, cr,xp));
    }

    /**
     * Se lee el excel y se vuelcan los datos en el archivo enemigos.dat
     * Esto actua como primer llenado con datos del archivo para contar con una base
     * @param archivo El fichero enemigos.dat
     * @return El fichero enemigos.dat
     */
    private File leerExcel(File archivo) {
        File data = archivo;
        try {
            //Creamos una variable para llevar los ID al momento
            //Inicializada a 0 porque la primera linea no la queremos
            int contid = 0;

            //VARIABLES DEL .DAT

            RandomAccessFile fichero = new RandomAccessFile(archivo, "rw");

            //VARIABLES DEL EXCEL
            FileInputStream file = new FileInputStream(new File("Recursos.xlsx"));
            //Creamos la instancia del workbook que tiene la referencia al excel
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //Obtenemos la Hoja deseada del excel
            XSSFSheet sheet = workbook.getSheetAt(0);//Leemos de la hoja de Monstruos

            //Iteramos sobre las filas una a una
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //Por cada fila iteramos sobre las columnas
                Iterator<Cell> cellIterator = row.cellIterator();
                if (contid > 0) {
                    while (cellIterator.hasNext()) {//Si esto se repite mas de una vez, algo va mal
                        // En Orden son: Nombre, tipo y CR(Challenge Rating)
                        Cell cell = cellIterator.next();
                        //Comprobamos el tipo de la celda para leer
                        String nombre = cell.getStringCellValue();
                        cell = cellIterator.next();
                        String tipo = cell.getStringCellValue();
                        cell = cellIterator.next();
                        long cr = (long) cell.getNumericCellValue();
                        cell = cellIterator.next();
                        int xp = (int) cell.getNumericCellValue();
                        //DATOS A METER, EN ORDEN
                        /**
                         * Id, int de 4 bytes
                         * Nombre, cadena de 50 caracteres, 100 Bytes
                         * Tipo, cadena de 11 caracteres, 22 bytes
                         * CR, long de 8 bytes
                         * XP, int de 4 bytes
                         * Total, 138 bytes
                         */

                        fichero.writeInt(contid);
                        fichero.writeChars(obtenerStringCompleto(nombre, 50));
                        fichero.writeChars(obtenerStringCompleto(tipo, 11));
                        fichero.writeLong(cr);
                        fichero.writeInt(xp);


                    }
                }
                System.out.println("");
                contid++;
            }
            file.close();
            fichero.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Esto es mi punto de vergüenza, si ves esto, es un error que no he podido solucionar.");
            System.out.println("Solo debería salir si cargas los datos desde el excel. No he logrado encontrar porque es.");
            System.out.println("Si te interesa, ve a 'ListaEnemigos' en la linea 152");
            //e.printStackTrace();
            //System.out.println("A");
        }
        return archivo;
    }
    /**
     * Funcion de utilidad para fijar la longitud de un String
     * @param texto texto que se quiere tratar
     * @param longitud longitud maxima de ese texto
     * @return un String cortado o extendido con espacios en blanco hasta la longitud elegida
     */
    private String obtenerStringCompleto(String texto, int longitud) {
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

    /**
     * Añade un enemigo a la lista de la instancia
     * @param enemigo Enemigo a añadir
     */
    public  void add(Enemigo enemigo) {
        enemigos.add(enemigo);
    }

    /**
     * Devuelve la lista de enemigos de la instancia
     * @return ArrayList con los enemigos de la instancia
     */
    public  ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }

    /**
     * Fija la lista de enemigos de la instancia
     * @param enemigos Lista de enemigos a fijar
     */
    public  void setEnemigos(ArrayList<Enemigo> enemigos) {
        enemigos = enemigos;
    }
}
