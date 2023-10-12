public class StringCalculator {
    public static int add(String numbers)
    {
        int number_sum = 0; // Var for number sum
        if (numbers.isEmpty()) // Condition for empty string
        {
            return 0; // Return 0 for empty string
        }
        if (numbers.contains(",\n")) // Throw error for ,\n structure between numbers
        {
            throw new Error("Unsupportable string input (it shouldn't contains structures (,\\n) or (\\n,)");
        }
        if (numbers.contains("\n,")) // Throw error for \n, structure between numbers
        {
            throw new Error("Unsupportable string input (it shouldn't contains structures (,\\n) or ('\\n',)");
        }
        String[] ArrayOfNums = numbers.split("[,|\n]"); // Split string by coma or \n
        for (String arrayOfNum : ArrayOfNums) // Count sum of split numbers
        {
            number_sum += Integer.parseInt(arrayOfNum);
        }
        return number_sum; // Return sum of split numbers
    }
    public static void main(String[] args) {
        // Test case
        String input1 = "";
        System.out.println("Result: " + StringCalculator.add(input1));
    }
}
