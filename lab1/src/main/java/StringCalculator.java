public class StringCalculator {
    public static int add(String numbers)
    {
        int number_sum = 0; // Var for number sum
        String delimiter = ",|\n"; // Var for default delimiters as coma or \n
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
        if (numbers.startsWith("//")) // Condition for taking custom delimiter
        {
            int delimiterIndex = numbers.indexOf("\n"); // Getting index of the end of custom delimiter
            // Getting custom delimiter from string (2 is the start of delimiter (after "//" which indexes are 0 and 1)
            delimiter = numbers.substring(2,delimiterIndex);
            // Escape special characters in the custom delimiter
            delimiter = delimiter.replaceAll("([\\[\\]\\(\\)\\.\\*\\+\\?\\^\\$\\\\])", "\\\\$1");
            // Adding standard delimiters to the custom delimiter
            delimiter = ",|\n|" + delimiter;
            // Getting string with numbers split by custom delimiter (removing //[delimiter]\n structure)
            numbers = numbers.substring(delimiterIndex+1);
        }
        String[] ArrayOfNums = numbers.split(delimiter); // Split string by delimiter
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



