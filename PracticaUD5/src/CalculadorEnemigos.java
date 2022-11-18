import java.util.HashMap;

public class CalculadorEnemigos {
    /**
     * XP base por nivel encuentro facil (medio x2 dificil x3 mortal x4)
     * para 1 personaje (xNumero de personajes)
     * 1 - 25
     * 2 - 50
     * 3 - 75
     * 4 - 125
     * 5 - 250
     * 6 - 300
     * 7 - 350
     * 8 - 450
     * 9 - 550
     * 10 - 600
     * 11 - 800
     * 12 - 1000
     * 13 - 1100
     * 14 - 1250
     * 15 - 1400
     * 16 - 1600
     * 17 - 2000
     * 18 - 2100
     * 19 - 2400
     * 20 - 2800
     *
     * La explicacion del escalado raro, es la siguiente, al subir de nivel
     * los personajes obtienen diferentes mejoras, que los hacen mas fuertes
     * , y hay niveles en los que esas mejoras son por lo general
     * mas sustanciales que en otros, además de los niveles en los que
     * se obtienen mejoras de estadisticas
     * Y claro, los monstruos posibles son otra movida, no escalan directamente
     * con los niveles de los personajes
     */
    private static ListaEnemigos enemigosL = new ListaEnemigos(true);
    private HashMap<Integer, Integer> xpBase = new HashMap<Integer,Integer>();//Esto hay que rellenarlo siempre no queda otra
    private int dificultad;//1,2,3,4
    private int numeroJugadores;
    private int nivelJugadores;//1-20

    private long crMaximo;//0.

    /**
     * Constructor lleno de la clase CalculadorEnemigos
     * @param dificultad Dificultad del encuentro, de 1 a 4
     * @param numeroJugadores Numero de jugadores que participan en el encuentro
     * @param nivelJugadores Nivel de los jugadores que participan en el encuentro
     * @param crMaximo Desafio maximo de los monstruos
     */
    public CalculadorEnemigos(int dificultad, int numeroJugadores, int nivelJugadores, long crMaximo) {
        this.dificultad = dificultad;
        this.numeroJugadores = numeroJugadores;
        this.nivelJugadores = nivelJugadores;
        this.crMaximo = crMaximo;
        llenarMap();
    }

    /**
     * Metodo que devuelve una lista de enemigos calculada mediante otros metodos
     * @return
     */
    public ListaEnemigos calc () {
        ListaEnemigos enemigos = new ListaEnemigos();
        CalcularEncuentro(enemigos);
        return enemigos;
    }

    /**
     * Llenado del Map de valores por defecto de la experiencia por nivel de un personaje para un encuentro facil
     */
    private void llenarMap(){
        xpBase.put(1,25);
        xpBase.put(2,50);
        xpBase.put(3,75);
        xpBase.put(4,125);
        xpBase.put(5,250);
        xpBase.put(6,300);
        xpBase.put(7,350);
        xpBase.put(8,450);
        xpBase.put(9,550);
        xpBase.put(10,600);
        xpBase.put(11,800);
        xpBase.put(12,1000);
        xpBase.put(13,1100);
        xpBase.put(14,1250);
        xpBase.put(15,1400);
        xpBase.put(16,1600);
        xpBase.put(17,2000);
        xpBase.put(18,2100);
        xpBase.put(19,2400);
        xpBase.put(20,2800);
    }

    /**
     * Calculo del encuentro, eligiendo monstruos al azar segun calculos hechos dentro.
     * Se calcula la experiencia total del encuentro teniendo en cuenta, nivel y numero de jugadores,
     * además de la dificultad del encuentro
     * @param enemigos Lista de enemigos de la cual se extraen datos
     */
    private void CalcularEncuentro(ListaEnemigos enemigos){
        //Primero hay que calcular el pool de experiencia que tenemos disponible
        int xpTotal = xpBase.get(nivelJugadores) * dificultad * numeroJugadores;
        int xpRestante = xpTotal;

        //Ahora, basandonos en la experiencia que tenemos, hay que recorrer la lista de enemigos de forma aleatoria,
        //hasta quedarnos sin experiencia
        //Para que no sea infinita por el hecho de no encontrar un enemigo que nos de la experiencia exacta que falta,
        //el bucle se hará 1000 veces, que son más que suficientes

        int numeroMonstruosTotal = enemigosL.getEnemigos().size();

        //Se coge un monstruo al azar, se mira que por cantidad de experiencia pueda entrar en el encuentro,
        //si cabe, se mete, si no, a otro, asi 1000 veces
        for(int x = 0;x<1000;x++){
            Enemigo candidato = enemigosL.getEnemigos().get((int)(1 + (Math.random() * (numeroMonstruosTotal-1) )));
            if(candidato.getXp() <= xpRestante && candidato.getCr() <= crMaximo){
                enemigos.add(candidato);
                xpRestante-= candidato.getXp();
            }
        }

    }

}
