import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
        assertEquals("Розмірність матриці: 3x4", matrix.MatrixDimension());
    }
}
