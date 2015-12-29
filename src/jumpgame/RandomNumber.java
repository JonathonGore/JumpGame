
package jumpgame;

public class RandomNumber {
   
   public static int randomInt(int low, int high){
        double seed = Math.random();
        double L = (double)low;
        double H = (double)high;
        double random = (H - L + 1) * seed + L;
        return (int)random;
   }
}
