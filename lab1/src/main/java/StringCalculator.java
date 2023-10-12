import java.util.ArrayList;
import java.util.List;
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
            throw new IllegalArgumentException("Unsupportable string input (it shouldn't contains structures (,\\n) or (\\n,)");
        }
        if (numbers.contains("\n,")) // Throw error for \n, structure between numbers
        {
            throw new IllegalArgumentException("Unsupportable string input (it shouldn't contains structures (,\\n) or ('\\n',)");
        }
        if (numbers.startsWith("//")) // Condition for taking custom delimiter
        {
            int delimiterIndex = numbers.indexOf("\n"); // Getting index of the end of custom delimiter
            // Getting custom delimiter from string (2 is the start of delimiter (after "//[" which indexes are 0,1,2 to delimiter-1 (]\n) struct)
            delimiter = numbers.substring(3,delimiterIndex-1);
            // Support for multiple delimiters
            delimiter = delimiter.replaceAll("\\]\\[", "|");;
            // Escape special characters in the custom delimiter
            delimiter = delimiter.replaceAll("([\\[\\]\\(\\)\\.\\*\\+\\?\\^\\$\\\\])", "\\\\$1");
            // Adding standard delimiters to the custom delimiter
            delimiter = ",|\n|" + delimiter;
            // Getting string with numbers split by custom delimiter (removing //[delimiter]\n structure)
            numbers = numbers.substring(delimiterIndex+1);
        }
        String[] ArrayOfNums = numbers.split(delimiter); // Split string by delimiter
        List<Integer> negative_numbers = new ArrayList<>(); // Creating list for catching negative integers
        for (String arrayOfNum: ArrayOfNums)
        {
            int check_num = Integer.parseInt(arrayOfNum); // Converting every num from string to integer
            if (check_num<0) // Condition for negative numbers
            {
                negative_numbers.add(check_num); // Adding negative number to list
            }
        }
        if (!negative_numbers.isEmpty()) // Throwing exception if list contains any negative numbers
        {
            throw new IllegalArgumentException("Negatives not allowed: " + negative_numbers);
        }
        for (String arrayOfNum : ArrayOfNums) // Count sum of split numbers
        {
            if (Integer.parseInt(arrayOfNum)<= 1000) // Summing only numbers which are less than 1001
            {
                number_sum += Integer.parseInt(arrayOfNum);
            }
        }
        return number_sum; // Return sum of split numbers
    }
    public static void main(String[] args) {
        // Test case
        String input1 = "";
        System.out.println("Result: " + StringCalculator.add(input1));
    }
}





