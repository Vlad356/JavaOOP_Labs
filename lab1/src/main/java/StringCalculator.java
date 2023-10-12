public class StringCalculator {
    public static int add(String numbers)
    {
        int number_sum = 0;
        if (numbers.isEmpty())
        {
            return 0;
        }
        String[] ArrayOfNums = numbers.split(",");
        for (int i = 0; i < ArrayOfNums.length; i++)
        {
            number_sum += Integer.parseInt(ArrayOfNums[i]);
        }
        if (ArrayOfNums.length > 2)
        {
            throw new Error("Inputted more than 2 numbers!");
        }
        return number_sum;
    }
    public static void main(String[] args) {
        // Test case
        String input1 = "";
        System.out.println("Result: " + StringCalculator.add(input1));
    }
}
