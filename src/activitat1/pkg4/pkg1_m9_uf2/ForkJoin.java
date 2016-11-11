package activitat1.pkg4.pkg1_m9_uf2;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import static java.util.concurrent.ForkJoinTask.invokeAll;

public class ForkJoin extends RecursiveTask<Integer> {

    //Declaració de variables.
    private final ArrayList<Integer> array;
    private final int inici, finale;

    //Amb això evitem que quan s'executi el programa ens mostri missatges d'erros.
    @SuppressWarnings("unchecked")
    /**
     * Constructor del ForkJoin
     */
        public ForkJoin(ArrayList arr, int ini, int fin) {
            this.array = arr;
            this.inici = ini;
            this.finale = fin;
        }

    /**
     * En el compute() es calcula el sou maxim emmagatzemat en l'array.
     * Divideix l'array i compara 2 resultats, els divideix per la meitat i si segueix sent massa
     * gran els torna a dividir.
     * @return 
     */
    @Override
    protected Integer compute() {
        int sueldoMax = 0;
        if (finale - inici <= 1) {
            
            sueldoMax = Math.max(array.get(inici), array.get(finale));
            
        return sueldoMax;
        }else {
        //Dividir el problema en parts més petites
         int mitat = inici + (finale - inici) / 2;
         ForkJoin forkJoin1 = new ForkJoin(array, inici, mitat);
         ForkJoin forkJoin2 = new ForkJoin(array, mitat + 1, finale);
         invokeAll(forkJoin1, forkJoin2);
         return Math.max(forkJoin1.join(), forkJoin2.join());
        }
    }




    /**
     * Metode Main on es crea l'array de sous que conté tots els sous de la població
     * Es creen el numero de fils segons el numero disponible i fa el invoke
     * que dura a terme la tasca que tornara el sou mes gran.
     * @param args 
     */
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> sueldos = new ArrayList<>();

        for (int i = 1; i < 20000; i++) {
            int sueldoAleatorio = random.nextInt(50001);
            sueldos.add(sueldoAleatorio);
        }


        int NumberOfProcessors = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(NumberOfProcessors);
        ForkJoin tasca = new ForkJoin(sueldos, 0, sueldos.size() - 1);
        Integer result = pool.invoke(tasca);
        
        System.out.println("Resultat és: " + result);
        
    }

}