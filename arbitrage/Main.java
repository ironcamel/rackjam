import java.util.*;
import java.io.*;
import static java.lang.System.out;

public class Main {
    public static void main(String args[]) throws Exception {
        //Scanner sc = new Scanner(new File("sample.in"));
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int dim = sc.nextInt();
            double[][] mat = new double[dim][];

            for (int i = 0; i < dim; i++) {
                double[] row = new double[dim];
                for (int j = 0; j < dim; j++) {
                    if (j == i)
                        row[j] = 1.0;
                    else
                        row[j] = sc.nextDouble();
                }
                mat[i] = row;
            }
            solve(mat);
            //out.println("-----------------");
        }
        System.exit(0);
    }

    static void solve(double[][] mat) {
        int size = mat.length;
        /*
        for (int i = 0; i < size; i++) {
            out.println(Arrays.toString(mat[i]));
        }
        */
        int[][] next = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                next[i][j] = -1;
            }
        }
        boolean foundIt = false;
        OUTER:
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    //if (i == k || j == k)
                        //continue;
                    double val = mat[i][k] * mat[k][j];
                    if (val > mat[i][j]) {
                        mat[i][j] = val;
                        next[i][j] = k;
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                if (mat[i][i] >= 1.01) {
                    foundIt = true;
                    //out.println("reached 1.01 k: " + k);
                    break OUTER;
                }
            }
        }
        /*
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(next[i]));
        }
        */

        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            if (mat[i][i] > mat[maxIndex][maxIndex])
                maxIndex = i;
        }
        //maxIndex = 0;
        //out.println("finding path for " + maxIndex);
        if (foundIt) {
            out.println(""
                + (maxIndex + 1)
                + genPath(next, maxIndex, maxIndex)
                + (maxIndex + 1)
            );
        } else {
            out.println("no arbitrage sequence exists");
        }
    }

    static String genPath(int[][] next, int i, int j) {
        int middle = next[i][j];
        if (middle == -1)
            return " ";
        return genPath(next, i, middle) + (middle+1) + genPath(next, middle, j);
    }

}
