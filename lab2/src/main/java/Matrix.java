import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


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
    public Matrix() {
        this.rows = 0;
        this.cols = 0;
        this.data = new double[rows][cols];
    }
    // Method for build matrix rows x cols dimension
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }
    // Method for copying matrix
    public Matrix(Matrix Other_Matrix) {
        this.rows = Other_Matrix.rows; // Getting other matrix rows value
        this.cols = Other_Matrix.cols; // Getting other matrix columns value
        this.data = new double[rows][cols]; // Making the same dimension matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = Other_Matrix.data[i][j]; // Filling new matrix with same data
            }
        }
    }

    // Method for filling matrix with numbers
    public Matrix(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = Arrays.stream(data)
                .map(row -> Arrays.copyOf(row, row.length))
                .toArray(double[][]::new);
    }

    // Method for showing element of matrix
    public double ShowElement(int row, int col) {
        if (row < 0 || row >= rows + 1 || col < 0 || col >= cols + 1) {
            throw new IllegalArgumentException("Invalid element index"); // Error if invalid indexes inputted
        }
        System.out.println("Element:");
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

    public static String equalsMessage(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return "Objects are the same.";
        }

        if (obj1 == null || obj2 == null || obj1.getClass() != obj2.getClass()) {
            return "Objects are of different types or one of them is null.";
        }

        // Add additional checks based on your object's structure
        // For example, if the objects have fields, you might compare the fields here.

        // Using Objects.equals for null-safe comparison
        if (!Objects.equals(obj1, obj2)) {
            return "Objects are different.";
        }

        return "Objects are equal.";
    }

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

        Matrix resultMatrix = new Matrix(this.rows, this.cols); // Creating new result matrix

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                resultMatrix.data[i][j] = this.data[i][j] + otherMatrix.data[i][j]; // Summing each element than adding to result matrix
            }
        }
        return resultMatrix; // Returning result matrix
    }

    // Method for scalar multiplication
    public Matrix ScalarMultiply(double scalar) {
        Matrix resultMatrix = new Matrix(this.rows, this.cols); // Creating new result matrix

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

        Matrix resultMatrix = new Matrix(this.rows, otherMatrix.cols); // Creating new result matrix
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

    public Matrix TransposeMatrix() {
        Matrix transposedMatrix = new Matrix(this.rows, this.cols); // Creating new result matrix

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
        Matrix diagonalMatrix = new Matrix(n, n); // Creating new matrix

        for (int i = 0; i < n; i++) {
            diagonalMatrix.data[i][i] = diagonalValues[i]; // Add vector values as diagonal elements
        }
        return diagonalMatrix; // Return result matrix
    }

    // Method for creating an identity matrix
    public static Matrix IdentityMatrix(int size) {
        Matrix identityMatrix = new Matrix(size, size); // Creating new matrix

        for (int i = 0; i < size; i++) {
            identityMatrix.data[i][i] = 1.0; // Add 1.0 value as diagonal elements
        }
        return identityMatrix; // Return result matrix
    }

    // Method for creating a row matrix with random values within a specified range
    public static Matrix RandomRowMatrix(int size, double minValue, double maxValue) {
        Matrix randomRowMatrix = new Matrix(1, size); // Creating new matrix

        Random random = new Random();
        for (int j = 0; j < size; j++) {
            double randomValue = minValue + (maxValue - minValue) * random.nextDouble();
            randomRowMatrix.data[0][j] = randomValue; // Filling matrix with random double values within the specified range
        }
        return randomRowMatrix; // Return result matrix
    }

    // Method for creating a column matrix with random values within a specified range
    public static Matrix RandomColumnMatrix(int size, double minValue, double maxValue) {
        Matrix randomColumnMatrix = new Matrix(size, 1); // Creating new matrix

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            double randomValue = minValue + (maxValue - minValue) * random.nextDouble();
            randomColumnMatrix.data[i][0] = randomValue; // Filling matrix with random double values within the specified range
        }
        return randomColumnMatrix; // Return result matrix
    }

    private double determinant(double[][] matrix) {
        double det = 0;
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        for (int i = 0; i < matrix[0].length; i++) {
            double[][] subMatrix = new double[matrix.length - 1][matrix[0].length - 1];
            for (int m = 1; m < matrix.length; m++) {
                for (int n = 0; n < matrix[0].length; n++) {
                    if (n < i) {
                        subMatrix[m - 1][n] = matrix[m][n];
                    } else if (n > i) {
                        subMatrix[m - 1][n - 1] = matrix[m][n];
                    }
                }
            }
            det += matrix[0][i] * Math.pow(-1, i) * determinant(subMatrix);
        }
        return det;
    }

    public Matrix inverse() {
        double det = determinant(this.data);
        if (det == 0) {
            throw new IllegalStateException("Matrix is singular and cannot be inverted.");
        }
        double[][] adjoint = adjoint(this.data);
        Matrix inverseMatrix = new Matrix(rows, cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                inverseMatrix.data[i][j] = adjoint[i][j] / det;
            }
        }
        return inverseMatrix;
    }

    private double[][] adjoint(double[][] matrix) {
        double[][] adj = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                adj[i][j] = Math.pow(-1, i + j) * determinant(minor(matrix, i, j));
            }
        }
        return transpose(adj);
    }

    private double[][] minor(double[][] matrix, int row, int col) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != row && j != col) {
                    minor[i < row ? i : i - 1][j < col ? j : j - 1] = matrix[i][j];
                }
            }
        }
        return minor;
    }
    private double[][] transpose(double[][] matrix) {
        double[][] transposedMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
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

    // Method for filling matrix with random values within a specified range
    public void FillRandom(double minValue, double maxValue) {
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double randomValue = minValue + (maxValue - minValue) * random.nextDouble();
                this.data[i][j] = randomValue; // Filling matrix with random double values within the specified range
            }
        }
    }

    public double getElementIm(int row, int col) {
        return data[row - 1][col - 1]; // -1 for user comfort (because of indexes starts from 0)
    }

    public int getRowIm() {
        return rows;
    }

    public int getColIm() {
        return cols;
    }

}


