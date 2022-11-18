public class GeneradorEncuentros {
    int dificultad, numeroJugadores,nivelJugadores;
    long crMaximoEncuentro;

    /**
     * Constructor de la clase GeneradorEncuentros
     * @param dificultad La dificultad del encuentro(1-4)
     * @param numeroJugadores Numero de jugadores
     * @param nivelJugadores Nivel de los jugadores
     * @param crMaximoEncuentro Clase de desafio maxima en el encuentro
     */
    public GeneradorEncuentros(int dificultad, int numeroJugadores, int nivelJugadores, long crMaximoEncuentro) {
        this.dificultad = dificultad;
        this.numeroJugadores = numeroJugadores;
        this.nivelJugadores = nivelJugadores;
        this.crMaximoEncuentro = crMaximoEncuentro;
    }

    /**
     * Llamando a metodos de las clases ListaEnemigos y ListaRecompensas, junta los datos en una clase Encuentro
     * @return Encuentro con los datos del encuentro generado
     */
    public Encuentro generarEncuentro(){

        //Se calculan los enemigos
        CalculadorEnemigos calcEnem = new CalculadorEnemigos(dificultad,numeroJugadores,nivelJugadores,crMaximoEncuentro);
        ListaEnemigos enemigos =  calcEnem.calc();

        CalculadorRecompensas calcRec = new CalculadorRecompensas(crMaximoEncuentro);
        ListaRecompensas recompensas = calcRec.calc();


        return new Encuentro(numeroJugadores,nivelJugadores,enemigos,recompensas);
    }

}
