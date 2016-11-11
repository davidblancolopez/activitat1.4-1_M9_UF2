package activitat1.pkg4.pkg1_m9_uf2;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import static java.util.concurrent.ForkJoinTask.invokeAll;

public class ForkJoin extends RecursiveTask<Integer> {

    private final ArrayList<Integer> array;
    private final int inici, finale;

    @SuppressWarnings("unchecked")
        public ForkJoin(ArrayList arr, int ini, int fin) {
            this.array = arr;
            this.inici = ini;
            this.finale = fin;
        }

    /**
     * En el compute() es calcula el sou maxim emmagatzemat en l'array.
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