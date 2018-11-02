/**
 * Class for solution.
 */
public class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //Empty constructer.
    }
    /**
     * client function.
     *
     * @param      args  The arguments
     */
    public static void main(String[] args) {
        /**
         * Synsets File name.
         */
        String synsetsFname = StdIn.readString();
        /**
         * Hypernym File name.
         */
        String hypernymFname = StdIn.readString();
        /**
         * to read a string.
         */
        String str  = StdIn.readString();
        /**
         * Switch case.
         */
        switch (str) {
            case "Graph":
            try {
                WordNet wordnet = new WordNet(synsetsFname, hypernymFname);
                wordnet.print();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                // System.out.println("IllegalArgumentException");
            }
            break;
            case "Queries":
                try {
                    WordNet wordnet1 = new WordNet(synsetsFname, hypernymFname);
                    
                    while (StdIn.hasNextLine()) {
                        String line = StdIn.readLine();
                        String[] stringArray = line.split(" ");
                        if (stringArray[0].equals("null")) {
                            throw new IllegalArgumentException("IllegalArgumentException");
                        }
                        System.out.println(
                            "distance = " + wordnet1.distance(stringArray[0],
                        stringArray[1]) + ", ancestor = " + wordnet1.sap(stringArray[0],
                        stringArray[1]));
                }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
            break;
        }
    }
}
