import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;


public class Matrix {
    private int rows, cols; // int for rows and columns of matrix
    private double[][] data; // 2d array of matrix data
    //Method for printing matrix
    public void PrintMatrix() {
        System.out.println("Matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
        System.out.println("-------------------------");
    }
    // Method for building empty matrix
    public void EmptyMatrix() {
        this.rows = 0;
        this.cols = 0;
        this.data = new double[rows][cols];
    }
    // Method for build matrix rows x cols dimension
    public void Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }
    // Method for copying matrix
    public void MatrixCopy(Matrix Other_Matrix) {
        this.rows = Other_Matrix.rows; // Getting other matrix rows value
        this.cols = Other_Matrix.cols; // Getting other matrix columns value
        this.data = new double[rows][cols]; // Making the same dimension matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = Other_Matrix.data[i][j]; // Filling new matrix with same data
            }
        }
    }
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    // Method for filling matrix with numbers
    public Matrix FillMatrix(double[][] data) {
        if (data.length != rows || data[0].length != cols) {
            throw new IllegalArgumentException("Mismatched dimensions for input array. Matrix not filled."); // Throw error if wrong format inputted
        }
        // Filling each number
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = data[i][j];
            }
        }
        return null;
    }
    // Method for showing element of matrix
    public double ShowElement(int row, int col) {
        if (row < 0 || row >= rows + 1 || col < 0 || col >= cols + 1) {
            throw new IllegalArgumentException("Invalid element index"); // Error if invalid indexes inputted
        }
        System.out.println("Element:");
        return data[row - 1][col - 1]; // -1 for user comfort (because of indexes starts from 0)
    }
    public double getElement(int row, int col) {
        return data[row - 1][col - 1]; // -1 for user comfort (because of indexes starts from 0)
    }
    // Method for showing row of matrix
    public double[] ShowRow(int row) {
        if (row < 0 || row >= rows + 1) {
            throw new IllegalArgumentException("Invalid element index"); // Error if invalid indexes inputted
        }
        System.out.println("Row:");
        return Arrays.copyOf(data[row - 1], cols); // -1 for user comfort (because of indexes starts from 0)
    }
    // Method for showing column of matrix
    public double[] ShowColumn(int col) {
        if (col < 0 || col >= cols + 1) {
            throw new IllegalArgumentException("Invalid element index"); // Error if invalid index is inputted
        }

        System.out.println("Column:");
        double[] column = new double[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = data[i][col - 1]; // -1 for user comfort (because of indexes starts from 0)
        }
        return column;
    }
    // Method for showing matrix dimension
    public String MatrixDimension() {
        return String.format("Matrix dimension: %dx%d", this.rows, this.cols);
    }
    // Override equals method for comparing two matrices
    public String equalsMessage(Matrix otherMatrix) {
        if (this == otherMatrix) {
            return "Matrices are the same object."; // If we check the same matrix
        }
        if (otherMatrix == null || getClass() != otherMatrix.getClass()) {
            return "Matrices are of different types."; // If we check matrices of different classes
        }
        if (rows != otherMatrix.rows || cols != otherMatrix.cols) {
            return "Matrices have different dimensions."; // If matrices have different dimensions
        }
        for (int i = 0; i < rows; i++) {
            if (!Arrays.equals(data[i], otherMatrix.data[i])) { // Comparing each element
                return "Matrices are different."; // If matrices are different (with equal dimensions)
            }
        }
        return "Matrices are equal."; // If matrices are equal
    }

    // Override hashCode method
    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols); // Hash code for a matrix object
        result = 31 * result + Arrays.deepHashCode(data); // Hash code for a matrix with data hash code
        return result; // Return hash code (const 31 reduces collisions)
    }
    public Matrix AddMatrix(Matrix otherMatrix) {
        if (this.rows != otherMatrix.rows || this.cols != otherMatrix.cols) { // Check for equal dimension of matrices
            throw new IllegalArgumentException("Matrix dimensions must match for addition.");
        }

        Matrix resultMatrix = new Matrix(); // Creating new result matrix
        resultMatrix.Matrix(this.rows, this.cols); // Making same dimension for result matrix

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                resultMatrix.data[i][j] = this.data[i][j] + otherMatrix.data[i][j]; // Summing each element than adding to result matrix
            }
        }
        return resultMatrix; // Returning result matrix
    }

    // Method for scalar multiplication
    public Matrix ScalarMultiply(double scalar) {
        Matrix resultMatrix = new Matrix(); // Creating new result matrix
        resultMatrix.Matrix(this.rows, this.cols); // Making same dimension for result matrix

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                resultMatrix.data[i][j] = this.data[i][j] * scalar; // Multiplying each element by scalar than returning new matrix
            }
        }

        return resultMatrix; // Returning result matrix
    }
    // Method for matrix multiplication
    public Matrix MultiplyMatrix(Matrix otherMatrix) {
        if (this.cols != otherMatrix.rows) { // Check for matrices multiply rules
            throw new IllegalArgumentException("Number of columns in the first matrix must be equal to the number of rows in the second matrix for multiplication.");
        }

        Matrix resultMatrix = new Matrix(); // Creating new result matrix
        resultMatrix.Matrix(this.rows, otherMatrix.cols); // Making dimension for result matrix according to math rules
        // Multiplying matrices
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < otherMatrix.cols; j++) {
                for (int k = 0; k < this.cols; k++) {
                    resultMatrix.data[i][j] += this.data[i][k] * otherMatrix.data[k][j];
                }
            }
        }
        return resultMatrix; // Returning result matrix
    }
    // Method for matrix transposition
    public Matrix TransposeMatrix() {
        Matrix transposedMatrix = new Matrix(); // Creating new result matrix
        transposedMatrix.Matrix(this.cols, this.rows); // Making transposed dimension for result matrix

        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                transposedMatrix.data[i][j] = this.data[j][i]; // Adding data to result matrix
            }
        }
        return transposedMatrix; // Returning result matrix
    }
    // Method for creating a diagonal matrix from a vector
    public static Matrix DiagonalMatrix(double[] diagonalValues) {
        int n = diagonalValues.length; // Getting length of vector
        Matrix diagonalMatrix = new Matrix(); // Creating new matrix
        diagonalMatrix.Matrix(n, n); // Making n x n dimension of matrix

        for (int i = 0; i < n; i++) {
            diagonalMatrix.data[i][i] = diagonalValues[i]; // Add vector values as diagonal elements
        }
        return diagonalMatrix; // Return result matrix
    }
    // Method for creating an identity matrix
    public static Matrix IdentityMatrix(int size) {
        Matrix identityMatrix = new Matrix(); // Creating new matrix
        identityMatrix.Matrix(size, size); // Making size x size dimension of matrix

        for (int i = 0; i < size; i++) {
            identityMatrix.data[i][i] = 1.0; // Add 1.0 value as diagonal elements
        }
        return identityMatrix; // Return result matrix
    }
    // Method for creating a row matrix with random values
    public static Matrix RandomRowMatrix(int size) {
        Matrix randomRowMatrix = new Matrix(); // Creating new matrix
        randomRowMatrix.Matrix(1, size); // Making 1 x size dimension of matrix

        Random random = new Random();
        for (int j = 0; j < size; j++) {
            randomRowMatrix.data[0][j] = random.nextDouble(); // Filling matrix with random double values
        }
        return randomRowMatrix; // Return result matrix
    }
    // Method for creating a column matrix with random values
    public static Matrix RandomColumnMatrix(int size) {
        Matrix randomColumnMatrix = new Matrix(); // Creating new matrix
        randomColumnMatrix.Matrix(size, 1); // Making size x 1 dimension of matrix

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            randomColumnMatrix.data[i][0] = random.nextDouble(); // Filling matrix with random double values
        }
        return randomColumnMatrix; // Return result matrix
    }
    // Method for matrix inversion
    public Matrix InverseMatrix() {
        if (this.rows != this.cols) { // If matrix isn`t n x n dimension
            throw new IllegalArgumentException("Matrix must be square for inversion.");
        }

        try {
            RealMatrix realMatrix = MatrixUtils.createRealMatrix(this.data); // Creating matrix for method from user matrix data
            RealMatrix inverseMatrix = MatrixUtils.inverse(realMatrix); // Getting inverse matrix

            Matrix resultMatrix = new Matrix(); // Creating new matrix
            resultMatrix.Matrix(this.rows, this.cols); // Making n x n dimension of matrix

            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    // Round to the nearest multiple of 0.1
                    double roundedValue = Math.round(inverseMatrix.getEntry(i, j) * 10.0) / 10.0;
                    resultMatrix.data[i][j] = roundedValue; // Adding result values to result matrix
                }
            }
            return resultMatrix; // Return result matrix
        } catch (SingularMatrixException e) { // If matrix determinant = 0
            throw new IllegalArgumentException("Matrix is singular and cannot be inverted.");
        }
    }
    public void Imcoppy(ImmutableMatrix immutableMatrix) {
        this.rows = immutableMatrix.imgetRows();
        this.cols = immutableMatrix.imgetCols();
        this.data = new double[rows][cols];

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                this.data[i - 1][j - 1] = immutableMatrix.getElement(i, j);
            }
        }
    }
}


