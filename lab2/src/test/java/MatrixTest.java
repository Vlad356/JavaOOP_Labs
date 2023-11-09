import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MatrixTest {

    @Test
    public void testEmptyMatrix() {
        Matrix matrix = new Matrix();
        matrix.EmptyMatrix();
        assertEquals(0, matrix.ShowColumn(0).length);
    }

    @Test
    public void testMatrixCopy() {
        Matrix matrix1 = new Matrix();
        matrix1.Matrix(3, 3);
        matrix1.FillMatrix(new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

        Matrix matrix2 = new Matrix();
        matrix2.MatrixCopy(matrix1);

        assertArrayEquals(matrix1.ShowColumn(1), matrix2.ShowColumn(1));
        assertArrayEquals(matrix1.ShowRow(2), matrix2.ShowRow(2));
    }

    @Test
    public void testFillMatrix() {
        Matrix matrix = new Matrix();
        matrix.Matrix(2, 2);
        matrix.FillMatrix(new double[][] {{1, 2}, {3, 4}});
        assertArrayEquals(new double[] {1, 2}, matrix.ShowRow(1));
        assertArrayEquals(new double[] {2, 4}, matrix.ShowColumn(2));
    }

    @Test
    public void testShowElement() {
        Matrix matrix = new Matrix();
        matrix.Matrix(2, 2);
        matrix.FillMatrix(new double[][] {{1, 2}, {3, 4}});
        assertEquals(3, matrix.ShowElement(2, 1));
    }

    @Test
    public void testShowRow() {
        Matrix matrix = new Matrix();
        matrix.Matrix(2, 3);
        matrix.FillMatrix(new double[][] {{1, 2, 3}, {4, 5, 6}});
        assertArrayEquals(new double[] {4, 5, 6}, matrix.ShowRow(2));
    }

    @Test
    public void testShowColumn() {
        Matrix matrix = new Matrix();
        matrix.Matrix(3, 2);
        matrix.FillMatrix(new double[][] {{1, 2}, {3, 4}, {5, 6}});
        assertArrayEquals(new double[] {2, 4, 6}, matrix.ShowColumn(2));
    }
    @Test
    public void testMatrixDimension() {
        Matrix matrix = new Matrix();
        matrix.Matrix(3, 4);
        assertEquals("Matrix dimension: 3x4", matrix.MatrixDimension());
    }
    @Test
    public void testAddMatrix() {
        Matrix matrix1 = new Matrix();
        matrix1.Matrix(2, 2);
        matrix1.FillMatrix(new double[][] {{1, 2}, {3, 4}});

        Matrix matrix2 = new Matrix();
        matrix2.Matrix(2, 2);
        matrix2.FillMatrix(new double[][] {{5, 6}, {7, 8}});

        Matrix resultMatrix = matrix1.AddMatrix(matrix2);

        assertArrayEquals(new double[] {6, 8}, resultMatrix.ShowRow(1));
        assertArrayEquals(new double[] {10, 12}, resultMatrix.ShowRow(2));
    }

    @Test
    public void testScalarMultiply() {
        Matrix matrix = new Matrix();
        matrix.Matrix(2, 2);
        matrix.FillMatrix(new double[][] {{1, 2}, {3, 4}});

        Matrix resultMatrix = matrix.ScalarMultiply(2);

        assertArrayEquals(new double[] {2, 4}, resultMatrix.ShowRow(1));
        assertArrayEquals(new double[] {6, 8}, resultMatrix.ShowRow(2));
    }

    @Test
    public void testMultiplyMatrix() {
        Matrix matrix1 = new Matrix();
        matrix1.Matrix(2, 3);
        matrix1.FillMatrix(new double[][] {{1, 2, 3}, {4, 5, 6}});

        Matrix matrix2 = new Matrix();
        matrix2.Matrix(3, 2);
        matrix2.FillMatrix(new double[][] {{7, 8}, {9, 10}, {11, 12}});

        Matrix resultMatrix = matrix1.MultiplyMatrix(matrix2);

        assertArrayEquals(new double[] {58, 64}, resultMatrix.ShowRow(1));
        assertArrayEquals(new double[] {139, 154}, resultMatrix.ShowRow(2));
    }
    @Test
    public void testInverseMatrix() {
        Matrix matrix = new Matrix();
        matrix.Matrix(2, 2);
        matrix.FillMatrix(new double[][] {{1, 2}, {3, 4}});

        Matrix invertedMatrix = matrix.InverseMatrix();

        assertArrayEquals(new double[] {-2, 1}, invertedMatrix.ShowRow(1));
        assertArrayEquals(new double[] {1.5, -0.5}, invertedMatrix.ShowRow(2));
    }
    @Test
    public void testTransposeMatrix() {
        Matrix matrix = new Matrix();
        matrix.Matrix(2, 3);
        matrix.FillMatrix(new double[][] {{1, 2, 3}, {4, 5, 6}});

        Matrix transposedMatrix = matrix.TransposeMatrix();

        assertArrayEquals(new double[] {1, 4}, transposedMatrix.ShowRow(1));
        assertArrayEquals(new double[] {2, 5}, transposedMatrix.ShowRow(2));
        assertArrayEquals(new double[] {3, 6}, transposedMatrix.ShowRow(3));
    }

    @Test
    public void testDiagonalMatrix() {
        double[] diagonalValues = {2, 3, 4};
        Matrix diagonalMatrix = Matrix.DiagonalMatrix(diagonalValues);


        assertArrayEquals(new double[]{0, 3, 0}, diagonalMatrix.ShowRow(2));
        assertArrayEquals(new double[]{0, 0, 4}, diagonalMatrix.ShowRow(3));
    }

    @Test
    public void testIdentityMatrix() {
        int size = 3;
        Matrix identityMatrix = Matrix.IdentityMatrix(size);

        assertEquals(1, identityMatrix.ShowElement(1, 1));
        assertEquals(1, identityMatrix.ShowElement(2, 2));
        assertEquals(1, identityMatrix.ShowElement(3, 3));

        assertEquals(0, identityMatrix.ShowElement(1, 2));
        assertEquals(0, identityMatrix.ShowElement(2, 3));
        assertEquals(0, identityMatrix.ShowElement(3, 1));
    }

    @Test
    public void testMatrixEquality() {
        Matrix matrix1 = new Matrix();
        matrix1.Matrix(2, 2);
        matrix1.FillMatrix(new double[][] {{1, 2}, {3, 4}});

        Matrix matrix2 = new Matrix();
        matrix2.MatrixCopy(matrix1);

        assertEquals("Matrices are equal.", matrix1.equalsMessage(matrix2));
    }
}

