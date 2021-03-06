/**
 * Class for lsd.
 */
public class LSD {
    /**
     * { var_description }.
     */
    private static final int bits_PER_BYTE = 8;
    /**
     * Constructs the object.
     */
    protected LSD() {
        //empty constructor.
    }

    /**
      * Rearranges the array of W-character strings in ascending order.
      *
      * @param a the array to be sorted
      * @param w the number of characters per string
      */
    public static void sort(final String[] a, final int w) {
        int n = a.length;
        final int radix = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[radix + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            // compute cumulates
            for (int r = 0; r < radix; r++) {
                count[r + 1] += count[r];
            }
            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }

    /**
      * Rearranges the array of 32-bit integers in ascending order.
      * This is about 2-3x faster than Arrays.sort().
      *
      * @param a the array to be sorted
      */
    public static void sort(final int[] a) {
        final int bits = 32;                 // each int is 32 bits
        final int R = 1 << bits_PER_BYTE; // each bytes is between 0 and 255
        final int MASK = R - 1;              // 0xFF
        final int w = bits / bits_PER_BYTE;  // each int is 4 bytes

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> bits_PER_BYTE * d) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == w - 1) {
                int shift1 = count[R] - count[R / 2];
                int shift2 = count[R / 2];
                for (int r = 0; r < R / 2; r++) {
                    count[r] += shift1;
                }
                for (int r = R / 2; r < R; r++) {
                    count[r] -= shift2;
                }
            }

            // move data
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> bits_PER_BYTE * d) & MASK;
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
}
