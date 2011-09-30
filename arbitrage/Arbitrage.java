import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.*;
import static java.lang.System.out;

public class Arbitrage {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("sample.in"));
        Scanner sc = new Scanner(new File("sample.in"));
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
    }

    static void solve(double[][] mat) {
        int size = mat.length;
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
        int[][] next = new int[size][size];
        OUTER:
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    double val = mat[i][k] * mat[k][j];
                    if (val > mat[i][j]) {
                        mat[i][j] = val;
                        next[i][j] = k;
                    }
                    //mat[i][j] = max ( mat[i][j], mat[i][k] * mat[k][j] );
                }
            }
            for (int i = 0; i < size; i++) {
                if (mat[i][i] >= 1.01) {
                    out.println("breaking k: " + k);
                    break OUTER;
                }
            }
        }
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(next[i]));
        }
    }


}
