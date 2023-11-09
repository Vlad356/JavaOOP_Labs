import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Immutable Matrix Tests
        ImmutableMatrix matrica = new ImmutableMatrix();
        ImmutableMatrix newmat = new ImmutableMatrix();
        ImmutableMatrix ober = new ImmutableMatrix();
        double[][] values = {{1, 2, 3}, {4, 5, 6}};
        double[][] values1 = {{1, 2}, {3, 4}, {5, 6}};
        double[][] values2 = {{1, 2}, {3, 4}};
        matrica = new ImmutableMatrix(2, 3);
        newmat = new ImmutableMatrix(3, 2);
        ober = new ImmutableMatrix(2, 2);
        ober = new ImmutableMatrix(values2);
        matrica = new ImmutableMatrix(values);
        newmat = new ImmutableMatrix(values1);
        matrica.printMatrix();
        newmat.printMatrix();
        System.out.println(newmat.matrixDimension()); // Output: [2, 3]

        System.out.println(matrica.equalsMessage(newmat));

        // Adding matrices
        ImmutableMatrix sumMatrix = newmat.addMatrix(newmat);
        sumMatrix.printMatrix();

        // Scalar multiplication
        ImmutableMatrix scalarMultipliedMatrix = newmat.scalarMultiply(2.0);
        scalarMultipliedMatrix.printMatrix();

        // Multiplying matrices
        ImmutableMatrix multipliedMatrix = matrica.multiplyMatrix(newmat);
        multipliedMatrix.printMatrix();

        ImmutableMatrix transposedMatrix = matrica.transposeMatrix();
        transposedMatrix.printMatrix();

        // Creating a diagonal matrix
        double[] diagonalValues = {1.0, 2.0, 3.0};
        ImmutableMatrix diagonalMatrix = ImmutableMatrix.diagonalMatrix(diagonalValues);
        diagonalMatrix.printMatrix();

        // Creating an identity matrix
        int sizeIdnt = 3; // Change the size as needed
        ImmutableMatrix identityMatrix = ImmutableMatrix.identityMatrix(sizeIdnt);
        identityMatrix.printMatrix();

        // Creating a row matrix with random values
        int size = 5; // Change the size as needed
        ImmutableMatrix randomRowMatrix = ImmutableMatrix.randomRowMatrix(size);
        randomRowMatrix.printMatrix();

        // Creating a column matrix with random values
        int size1 = 5; // Change the size as needed
        ImmutableMatrix randomColumnMatrix = ImmutableMatrix.randomColumnMatrix(size1);
        randomColumnMatrix.printMatrix();

        // Inverting a matrix
        ImmutableMatrix invertedMatrix = ober.inverseMatrix();
        invertedMatrix.printMatrix();


        // Test immutability after matrix addition
        System.out.println("Testing immutability after matrix addition:");
        System.out.println("Original Matrix:");
        newmat.printMatrix();
        System.out.println("Summed Matrix:");
        sumMatrix.printMatrix(); // Display the summed matrix
        System.out.println("Original Matrix (unchanged):");
        newmat.printMatrix(); // Ensure the original matrix is unchanged

        // Test immutability after scalar multiplication
        System.out.println("\nTesting immutability after scalar multiplication:");
        System.out.println("Original Matrix:");
        newmat.printMatrix();
        System.out.println("Scalar Multiplied Matrix:");
        scalarMultipliedMatrix.printMatrix(); // Display the scalar multiplied matrix
        System.out.println("Original Matrix (unchanged):");
        newmat.printMatrix(); // Ensure the original matrix is unchanged

        // Test immutability after matrix multiplication
        System.out.println("\nTesting immutability after matrix multiplication:");
        System.out.println("Original Matrix:");
        matrica.printMatrix();
        System.out.println("Multiplied Matrix:");
        multipliedMatrix.printMatrix(); // Display the multiplied matrix
        System.out.println("Original Matrix (unchanged):");
        matrica.printMatrix(); // Ensure the original matrix is unchanged

        // Test immutability after transposition
        System.out.println("\nTesting immutability after matrix transposition:");
        System.out.println("Original Matrix:");
        matrica.printMatrix();
        System.out.println("Transposed Matrix:");
        transposedMatrix.printMatrix(); // Display the transposed matrix
        System.out.println("Original Matrix (unchanged):");
        matrica.printMatrix(); // Ensure the original matrix is unchanged

        System.out.println("\nGetting specific elements, row, and column:");

        // Get a specific element
        int elementRow = 1;
        int elementCol = 2;
        System.out.println("Element at row " + elementRow + ", column " + elementCol + ": " + matrica.getElement(elementRow, elementCol));

        // Get a specific row
        int rowToGet = 1;
        System.out.println("Row " + rowToGet + ": " + Arrays.toString(matrica.getRow(rowToGet)));

        // Get a specific column
        int colToGet = 2;
        System.out.println("Column " + colToGet + ": " + Arrays.toString(matrica.getColumn(colToGet)));


        System.out.println("\nAll tests completed successfully.");

        // Mutable Matrix Tests
        Matrix mutableMatrica = new Matrix();
        Matrix mutableMat = new Matrix();
        Matrix mutableMatricasqr = new Matrix();
        double[][] mutableValues = {{1, 2, 3}, {4, 5, 6}};
        double[][] mutableValues1 = {{1, 2}, {3, 4}, {5, 6}};
        double[][] mutableValues2 = {{1, 2}, {3, 4}};
        mutableMatrica.EmptyMatrix();
        mutableMatrica.Matrix(2, 3);
        mutableMat.Matrix(3,2);
        mutableMatricasqr.Matrix(2,2);
        mutableMatrica.FillMatrix(mutableValues);
        mutableMat.FillMatrix(mutableValues1);
        mutableMatricasqr.FillMatrix(mutableValues2);

        mutableMatrica.PrintMatrix();
        System.out.println(mutableMatrica.MatrixDimension());

        System.out.println(mutableMatrica.ShowElement(2, 3));
        System.out.println(Arrays.toString(mutableMatrica.ShowRow(2)));
        System.out.println(Arrays.toString(mutableMatrica.ShowColumn(2)));

        // Adding matrix
        Matrix sumMutableMatrix = mutableMatrica.AddMatrix(mutableMatrica);
        sumMutableMatrix.PrintMatrix();

        // Scalar multiplication
        Matrix scalarMultipliedMutableMatrix = mutableMatrica.ScalarMultiply(2.0);
        scalarMultipliedMutableMatrix.PrintMatrix();

        // Multiplying matrices
        Matrix multipliedMutableMatrix = mutableMatrica.MultiplyMatrix(mutableMat);
        multipliedMutableMatrix.PrintMatrix();

        // Transposing matrix
        Matrix transposedMutableMatrix = mutableMatrica.TransposeMatrix();
        transposedMutableMatrix.PrintMatrix();

        // Diagonal Matrix
        double[] mutableDiagonalValues = {1.0, 2.0, 3.0};
        Matrix mutableDiagonalMatrix = Matrix.DiagonalMatrix(mutableDiagonalValues);
        mutableDiagonalMatrix.PrintMatrix();

        // Identity Matrix
        int mutableSizeIdnt = 3;
        Matrix mutableIdentityMatrix = Matrix.IdentityMatrix(mutableSizeIdnt);
        mutableIdentityMatrix.PrintMatrix();

        // Random Row Matrix
        int mutableSize = 5;
        Matrix mutableRandomRowMatrix = Matrix.RandomRowMatrix(mutableSize);
        mutableRandomRowMatrix.PrintMatrix();

        // Random Column Matrix
        int mutableSize1 = 5;
        Matrix mutableRandomColumnMatrix = Matrix.RandomColumnMatrix(mutableSize1);
        mutableRandomColumnMatrix.PrintMatrix();

        // Inverting Matrix
        Matrix mutableInvertedMatrix = mutableMatricasqr.InverseMatrix();
        mutableInvertedMatrix.PrintMatrix();


    }
}

