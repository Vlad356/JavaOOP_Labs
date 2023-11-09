import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[][] values = {{1, 2, 3}, {4, 5, 6}};
        Matrix matrix1 = new Matrix();
        matrix1.Matrix(2,3);
        matrix1.FillMatrix(values);
        ImmutableMatrix matrix = new ImmutableMatrix(matrix1);
        Matrix matrix2 = new Matrix();
        matrix2.Imcoppy(matrix);
        matrix1.EmptyMatrix();
        matrix1.Matrix(2,3);
        matrix2 = matrix2.AddMatrix(matrix1);
        matrix2.PrintMatrix();








    }
}

