import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
public final class ImmutableMatrix {
    private final int rows, cols; // int for rows and columns of matrix
    private final double[][] data; // 2d array of matrix data

    public void printMatrix() {
        System.out.println("Matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    // Constructor for an empty matrix
    public ImmutableMatrix() {
        this.rows = 0;
        this.cols = 0;
        this.data = new double[0][0];
    }

    // Constructor for a matrix with specified dimensions
    public ImmutableMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    // Constructor for copying a matrix
    public ImmutableMatrix(ImmutableMatrix otherMatrix) {
        this.rows = otherMatrix.rows;
        this.cols = otherMatrix.cols;
        this.data = Arrays.copyOf(otherMatrix.data, otherMatrix.data.length);
    }

    // Constructor for filling a matrix with data
    public ImmutableMatrix(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = Arrays.stream(data)
                .map(row -> Arrays.copyOf(row, row.length))
                .toArray(double[][]::new);
    }

    // Method to get a specific element from the matrix
    public double getElement(int row, int col) {
        if (row < 0 || row >= rows+1 || col < 0 || col >= cols+1) {
            throw new IllegalArgumentException("Invalid element index");
        }
        return data[row-1][col-1];
    }

    // Method to get a specific row from the matrix
    public double[] getRow(int row) {
        if (row < 0 || row >= rows+1) {
            throw new IllegalArgumentException("Invalid row index");
        }
        return Arrays.copyOf(data[row-1], cols);
    }

    // Method to get a specific column from the matrix
    public double[] getColumn(int col) {
        if (col < 0 || col >= cols+1) {
            throw new IllegalArgumentException("Invalid column index");
        }

        double[] column = new double[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = data[i][col-1];
        }
        return column;
    }

    // Getter for matrix dimension
    public String matrixDimension() {
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

    // Override hashCode method
    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols);
        result = 31 * result + Arrays.deepHashCode(data);
        return result;
    }

    // Method for matrix addition (returns a new matrix)
    public ImmutableMatrix addMatrix(ImmutableMatrix otherMatrix) {
        if (this.rows != otherMatrix.rows || this.cols != otherMatrix.cols) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition.");
        }

        double[][] resultData = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                resultData[i][j] = this.data[i][j] + otherMatrix.data[i][j];
            }
        }
        return new ImmutableMatrix(resultData);
    }

    // Method for scalar multiplication (returns a new matrix)
    public ImmutableMatrix scalarMultiply(double scalar) {
        double[][] resultData = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                resultData[i][j] = this.data[i][j] * scalar;
            }
        }

        return new ImmutableMatrix(resultData);
    }

    // Method for matrix multiplication (returns a new matrix)
    public ImmutableMatrix multiplyMatrix(ImmutableMatrix otherMatrix) {
        if (this.cols != otherMatrix.rows) {
            throw new IllegalArgumentException("Number of columns in the first matrix must be equal to the number of rows in the second matrix for multiplication.");
        }

        double[][] resultData = new double[this.rows][otherMatrix.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < otherMatrix.cols; j++) {
                for (int k = 0; k < this.cols; k++) {
                    resultData[i][j] += this.data[i][k] * otherMatrix.data[k][j];
                }
            }
        }
        return new ImmutableMatrix(resultData);
    }

    // Method for matrix transposition (returns a new matrix)
    public ImmutableMatrix transposeMatrix() {
        double[][] transposedData = new double[this.cols][this.rows];
        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                transposedData[i][j] = this.data[j][i];
            }
        }
        return new ImmutableMatrix(transposedData);
    }

    // Method for creating a diagonal matrix from a vector (returns a new matrix)
    public static ImmutableMatrix diagonalMatrix(double[] diagonalValues) {
        int n = diagonalValues.length;
        double[][] diagonalData = new double[n][n];

        for (int i = 0; i < n; i++) {
            diagonalData[i][i] = diagonalValues[i];
        }

        return new ImmutableMatrix(diagonalData);
    }

    // Method for creating an identity matrix (returns a new matrix)
    public static ImmutableMatrix identityMatrix(int size) {
        double[][] identityData = new double[size][size];

        for (int i = 0; i < size; i++) {
            identityData[i][i] = 1.0;
        }

        return new ImmutableMatrix(identityData);
    }

    // Method for creating a row matrix with random values (returns a new matrix)
    public static ImmutableMatrix randomRowMatrix(int size) {
        double[][] randomRowData = new double[1][size];
        Random random = new Random();

        for (int j = 0; j < size; j++) {
            randomRowData[0][j] = random.nextDouble();
        }

        return new ImmutableMatrix(randomRowData);
    }

    // Method for creating a column matrix with random values (returns a new matrix)
    public static ImmutableMatrix randomColumnMatrix(int size) {
        double[][] randomColumnData = new double[size][1];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            randomColumnData[i][0] = random.nextDouble();
        }

        return new ImmutableMatrix(randomColumnData);
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

    public void fillWithRandomValues(double min, double max, int decimalPlaces) {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double value = min + (max - min) * random.nextDouble();
                BigDecimal bd = new BigDecimal(value).setScale(decimalPlaces, RoundingMode.HALF_UP);
                data[i][j] = bd.doubleValue();
            }
        }
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

    public ImmutableMatrix(Matrix mutableMatrix) {
        this.rows =  mutableMatrix.getRowIm();
        this.cols = mutableMatrix.getColIm();
        this.data = new double[rows][cols];

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                this.data[i-1][j-1] = mutableMatrix.getElementIm(i, j);
            }
        }
    }

    public int imgetRows() {
        return rows;
    }

    public int imgetCols() {
        return cols;
    }

    // Method for filling the matrix with random values in a specified range
    public void fillRandomValues(double minValue, double maxValue) {
        Random random = new Random();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                double randomValue = minValue + (maxValue - minValue) * random.nextDouble();
                this.data[i][j] = randomValue;
            }
        }
    }
    public void setValue(int row, int col, double value) {
        throw new UnsupportedOperationException("Cannot modify values in an ImmutableMatrix.");
    }



}

