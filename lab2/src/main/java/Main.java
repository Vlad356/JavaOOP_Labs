import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[][] values = {{1, 2}, {3, 4}};
        Matrix mat1 = new Matrix(values);
        mat1.FillRandom(-4,4);
        mat1.PrintMatrix();
        ImmutableMatrix mat = new ImmutableMatrix(mat1);
        ImmutableMatrix matim = new ImmutableMatrix(2,2);
        matim.fillRandomValues(1,2);
        matim = mat.scalarMultiply(2);
        matim.printMatrix();
        mat = mat.scalarMultiply(2);
        mat.printMatrix();





    }
}

