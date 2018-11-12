import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //Empty Constructer.
    }
    /**
     * Client function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int stringCount = Integer.parseInt(scan.nextLine());
        LSD lsd = new LSD();
        String[] strArray = new String[stringCount];
        for (int i = 0; i < stringCount; i++) {
            strArray[i] = scan.nextLine();
        }
        lsd.sort(strArray, strArray[0].length());
        System.out.println(Arrays.toString(strArray));
    }
}
