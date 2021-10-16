public class Util {

    private Util(){
    }

     public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //пауза
    public static void sleep(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleepAnimation(int n, boolean on){
        n /= 500;
        for (int i = 0; i < n; i++) {
            if (on) {
                sleep(500);
                System.out.print(".");
            }
        }
    }

    public static void sleepAnimationLn(int n, boolean on){
        sleepAnimation(n, on);
        System.out.println();
    }


    public static int random(int min, int max) {
        if(min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }
        return (int) (Math.random() * (max - min)) + min;
    }

    public static int random(int max) {
        return random(0, max);
    }

    public static int random() {
        return random(0, 100);
    }




}
