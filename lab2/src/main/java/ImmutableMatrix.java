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

    // Override equals method for comparing two matrices
    public String equalsMessage(ImmutableMatrix otherMatrix) {
        if (this == otherMatrix) {
            return "Matrices are the same object.";
        }
        if (otherMatrix == null || getClass() != otherMatrix.getClass()) {
            return "Matrices are of different types.";
        }
        if (rows != otherMatrix.rows || cols != otherMatrix.cols) {
            return "Matrices have different dimensions.";
        }
        for (int i = 0; i < rows; i++) {
            if (!Arrays.equals(data[i], otherMatrix.data[i])) {
                return "Matrices are different.";
            }
        }
        return "Matrices are equal.";
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

    // Method for matrix inversion (returns a new matrix)
    public ImmutableMatrix inverseMatrix() {
        if (this.rows != this.cols) {
            throw new IllegalArgumentException("Matrix must be square for inversion.");
        }

        try {
            RealMatrix realMatrix = MatrixUtils.createRealMatrix(this.data);
            RealMatrix inverseMatrix = MatrixUtils.inverse(realMatrix);

            double[][] resultData = new double[this.rows][this.cols];
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    double roundedValue = Math.round(inverseMatrix.getEntry(i, j) * 10.0) / 10.0;
                    resultData[i][j] = roundedValue;
                }
            }

            return new ImmutableMatrix(resultData);
        } catch (SingularMatrixException e) {
            throw new IllegalArgumentException("Matrix is singular and cannot be inverted.");
        }
    }
    public ImmutableMatrix(Matrix mutableMatrix) {
        this.rows =  mutableMatrix.getRows();
        this.cols = mutableMatrix.getCols();
        this.data = new double[rows][cols];

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                this.data[i-1][j-1] = mutableMatrix.getElement(i, j);
            }
        }
    }
    public int imgetRows() {
        return rows;
    }

    public int imgetCols() {
        return cols;
    }



}

