package mc;

public class q2 {

    public static void main(String[] args) {
        int[] arr = {30, 20, 10, 0, -10};
        String result = "";
        for (int i = 0; i < arr.length-1; i++) {
            result += "_";
            try {
                result += arr[i] / arr[i+1];
            } catch (Exception e) {
                result += "?";
            }
        }
        System.out.println(result);
    }
    
}
