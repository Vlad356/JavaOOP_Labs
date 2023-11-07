import java.util.Arrays;

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
    // Method for filling matrix with numbers
    public void FillMatrix(double[][] data) {
        if (data.length != rows || data[0].length != cols) {
            throw new IllegalArgumentException("Mismatched dimensions for input array. Matrix not filled."); // Throw error if wrong format inputed
        }
        // Filling each number
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = data[i][j];
            }
        }
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
        return String.format("Розмірність матриці: %dx%d", this.rows, this.cols);
    }
}