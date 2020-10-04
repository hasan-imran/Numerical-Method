
import java.util.Scanner;

//Main class
public class GaussJordanElimination {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter size of the matrix: ");
        int n = userInput.nextInt();

        //Coefficient matrix
        double[][] matrix = new double[n][n];

        System.out.println("Enter the coefficients of the matrix: ");
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                matrix[i][j] = userInput.nextInt();
            }
        }

        //Constants
        double[] constants = new double[n];

        System.out.println("Enter the constants: ");
        for(int i = 0; i<n; i++){
            constants[i] = userInput.nextInt();
        }

        double[] ans = gaussJordanElimination(matrix, constants);

        if(ans == null){
            System.out.println("There is no solution");
            return;
        }

        System.out.println("Solution");
        for(int i = 0; i<n; i++){
            System.out.println("x(" + (i+1) + ") = " + ans[i]);
        }
    }


    private static double[] gaussJordanElimination(double[][] matrix, double[] constants){

        int n = matrix.length;


        for(int k = 0; k<n; k++) {

            //Pivot can not be 0. So checking if pivot is zero or not.
            //If 0, then we have to interchange this row with some other row, in which pivot won't be 0
            if(matrix[k][k] == 0){
                for(int j = k+1; j<n; j++){
                    if(matrix[j][k] != 0){
                        //Interchanging rows
                        double[] temp = matrix[k];
                        matrix[k] = matrix[j];
                        matrix[j] = temp;

                        //As well as corresponding constants
                        double temp2 = constants[k];
                        constants[k] = constants[j];
                        constants[j] = temp2;
                        break;
                    }
                }
            }

            //Pivot (diagonal element)
            double pivot = matrix[k][k];

            if(pivot == 0) return null;

            //ROW(k) = ROW(k)/pivot (To make diagonal element 1)
            for (int j = k; j < n; j++) {
                matrix[k][j] = matrix[k][j] / pivot;
            }
            //Same operation on constants
            constants[k] = constants[k] / pivot;


            //To make other pivot column elements 0nan
            for (int i = 0; i < n; i++) {

                //Preventing pivot to be 0
                if (i == k) continue;

                //If an element is already 0, we don't need to make it 0
                if (matrix[i][k] == 0) continue;

                //Store the value of the pivot column element
                double colValue = matrix[i][k];

                //ROW(i) = ROW(i) - (colValue * ROW(k))
                for (int j = k; j < n; j++) {
                    matrix[i][j] = matrix[i][j] - (colValue * matrix[k][j]);
                }
                //Same operation on constants
                constants[i] = constants[i] - (colValue * constants[k]);

            }
        }
        //We got Identity matrix, so current constants are the solution
        return constants;
    }
}

