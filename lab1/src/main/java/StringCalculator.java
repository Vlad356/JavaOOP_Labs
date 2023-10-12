public class StringCalculator {
    public static int add(String numbers)
    {
        int number_sum = 0;
        if (numbers.isEmpty())
        {
            return 0;
        }
        String[] ArrayOfNums = numbers.split(",");
        for (String arrayOfNum : ArrayOfNums)
        {
            number_sum += Integer.parseInt(arrayOfNum);
        }
        return number_sum;
    }
    public static void main(String[] args) {
        // Test case
        String input1 = "";
        System.out.println("Result: " + StringCalculator.add(input1));
    }
}
