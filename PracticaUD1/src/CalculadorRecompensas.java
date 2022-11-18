import java.util.ArrayList;

public class CalculadorRecompensas {

    private ListaRecompensas recompensasL = new ListaRecompensas(true);


    private long crMaximoEncuentro;

    private int oroAEntregar;


    private enum rangos {
        ENTRE_0_Y_4,
        ENTRE_5_Y_10,
        ENTRE_11_Y_16,
        SUPERIOR_A_17
    }

    /**
     * Rangos y posibilidades de objetos:
     * <p>
     * 0-4 - (150-1.000 gp(gold pieces)) 2-4 objetos comunes
     * 5-10 - (2.000-7.000gp) 6 objetos comunes || 2-4 objetos poco comunes || 1-2 objetos raros
     * 11-16 - (20.000-50.000gp) 8 uncommon || 4-6 rare || 3-4 very rare || 1-3 legendary
     * +17 - (100.000-500.000gp) 8 rare || 4-6 very rare|| 3-4 legendary || 1 artifact
     * <p>
     * no tengo explicacion para esto, son unos rangos genericos que hay en el Dungeon Masters Guide, modificados un poco
     * con info que he buscado
     */

    /**
     * Rarezas de Objetos
     * Comun 1
     * PocoComun 2
     * Raro 3
     * MuyRaro 4
     * Legendario 5
     * Artefacto 6
     */

    /**
     * Constructor de la clase CalculadorRecompensas
     *
     * @param crMaximoEncuentro El desafio maximo de los monstruos en el encuentro a generar
     */
    public CalculadorRecompensas(long crMaximoEncuentro) {
        this.crMaximoEncuentro = crMaximoEncuentro;
    }

    /**
     * Metodo que devuelve una lista de recompensas llamando a metodos que hacen el calculo
     * @return ListaRecompensas con las recompensas del encuentro
     */
    public ListaRecompensas calc() {
        ListaRecompensas recompensas = new ListaRecompensas();
        hacerCalculosDeRecompensas(recompensas);
        return recompensas;
    }

    /**
     * Se calculan aleatoriamente los objetos y oro a recibir basandose en unos rangos
     * Rangos y posibilidades de objetos:
     *
     *       0-4 - (150-1.000 gp(gold pieces)) 2-4 objetos comunes
     *       5-10 - (2.000-7.000gp) 6 objetos comunes || 2-4 objetos poco comunes || 1-2 objetos raros
     *       11-16 - (20.000-50.000gp) 8 uncommon || 4-6 rare || 3-4 very rare || 1-3 legendary
     *       +17 - (100.000-500.000gp) 8 rare || 4-6 very rare|| 3-4 legendary || 1 artifact
     *
     * @param recompensas ListaRecompensas con las recompensas del encuentro
     */
    private void hacerCalculosDeRecompensas(ListaRecompensas recompensas) {
        //Esto va a ser un switch rarete
        int rareza;
        int cantidadObjetos;
        switch ((0 <= crMaximoEncuentro && crMaximoEncuentro <= 4) ? rangos.ENTRE_0_Y_4 :
                (5 <= crMaximoEncuentro && crMaximoEncuentro <= 10) ? rangos.ENTRE_5_Y_10 :
                        (11 <= crMaximoEncuentro && crMaximoEncuentro <= 16) ? rangos.ENTRE_11_Y_16 : rangos.SUPERIOR_A_17) {
            case ENTRE_0_Y_4:
                oroAEntregar = (int) ((Math.random() * (1000 - 150)) + 150);
                recompensas.setOroEntregable(oroAEntregar);
                //rareza = (int) ((Math.random() * (1000 - 150)) + 150);
                cantidadObjetos = (int) ((Math.random() * (4 - 2)) + 2);
                for (int x = 0; x < cantidadObjetos; x++) {
                    recompensas.add(objetoAleatorio(1));
                }
                break;

            case ENTRE_5_Y_10:// 6 objetos comunes || 2-4 objetos poco comunes || 1-2 objetos raros
                oroAEntregar = (int) ((Math.random() * (7000 - 2000)) + 7000);
                recompensas.setOroEntregable(oroAEntregar);
                rareza = (int) ((Math.random() * (3 - 1)) + 1);
                if (rareza == 1) {
                    cantidadObjetos = 6;
                } else if (rareza == 2) {
                    cantidadObjetos = (int) ((Math.random() * (4 - 2)) + 2);
                } else {
                    cantidadObjetos = (int) ((Math.random() * (2 - 1)) + 1);
                }
                for (int x = 0; x < cantidadObjetos; x++) {
                    recompensas.add(objetoAleatorio(rareza));
                }
                break;

            case ENTRE_11_Y_16://8 uncommon || 4-6 rare || 3-4 very rare || 1-3 legendary
                oroAEntregar = (int) ((Math.random() * (50000 - 10000)) + 10000);
                recompensas.setOroEntregable(oroAEntregar);
                rareza = (int) ((Math.random() * (5 - 2)) + 2);
                if (rareza == 2) {//uncommon
                    cantidadObjetos = 8;
                } else if (rareza == 3) {//rare
                    cantidadObjetos = (int) ((Math.random() * (6 - 4)) + 4);
                } else if (rareza == 4) {//very rare
                    cantidadObjetos = (int) ((Math.random() * (4 - 3)) + 3);
                } else {//legendary
                    cantidadObjetos = (int) ((Math.random() * (3 - 1)) + 1);
                }
                for (int x = 0; x < cantidadObjetos; x++) {
                    recompensas.add(objetoAleatorio(rareza));
                }
                break;

            case SUPERIOR_A_17://8 rare || 4-6 very rare|| 3-4 legendary || 1 artifact
                oroAEntregar = (int) ((Math.random() * (500000 - 100000)) + 100000);
                recompensas.setOroEntregable(oroAEntregar);
                rareza = (int) ((Math.random() * (6 - 3)) + 3);
                if (rareza == 3) {//rare
                    cantidadObjetos = 8;
                } else if (rareza == 4) {//very rare
                    cantidadObjetos = (int) ((Math.random() * (6 - 4)) + 4);
                } else if (rareza == 5) {//Legendary
                    cantidadObjetos = (int) ((Math.random() * (4 - 3)) + 3);
                } else {
                    cantidadObjetos = 1;
                }
                for (int x = 0; x < cantidadObjetos; x++) {
                    recompensas.add(objetoAleatorio(rareza));
                }
                break;
        }


    }

    private Recompensa objetoAleatorio(int rareza) {
        ArrayList<Recompensa> recompensasPorRareza = recompensasL.filtrarRareza(rareza);

        Recompensa objetoElegido = recompensasPorRareza.get((int) (1 + (Math.random() * (recompensasPorRareza.size() - 1))));

        return objetoElegido;

    }

}
