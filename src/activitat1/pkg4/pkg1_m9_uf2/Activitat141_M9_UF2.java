
package activitat1.pkg4.pkg1_m9_uf2;


public class Activitat141_M9_UF2 {


    public static void main(String[] args) {
        int sueldos [] = new int [20000];
        
        for (int i = 1; i < 20000; i++) {
            sueldos[i - 1] = (int) (Math.random() * (0-(50000+0))+(50000));
        }
    }
    
}
