package interview.misc;

// 123 -> "One Hundred Twenty Three"
//     12345 -> "Twelve Thousand Three Hundred Forty Five"
//     1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
//
//     Hint:
//     Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
//     Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000
// and convert just that chunk to words.
//     There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or
// 1000010? (middle chunk is zero and should not be printed out)
//
//     Test:
//     0
//     1000
//     1000010

public class IntegerToEngWords {

    public static void main(String[] args) {
        System.out.println(numberToWords(143));
        System.out.println(numberToWords(0));
        System.out.println(numberToWords(1000));
        System.out.println(numberToWords(1974));
        System.out.println(numberToWords(1000010));
        System.out.println(numberToWords(20010));
    }

    private static final String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
        "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
        "Nineteen" };

    private static final String[] TENS = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
        "Eighty", "Ninety" };

    private static final String[] THOUSANDS = { "", "Thousand", "Million", "Billion" };

    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        int i = 0;
        String res = "";
        while (num > 0) {
            if (num % 1000 != 0) // 3 digits a group
            {
                res = helper(num % 1000) + THOUSANDS[i] + " " + res;
            }
            num /= 1000;
            i++; // cannot put into THOUSANDS[i++] !!! e.x. 1000
        }
        return res.trim(); // important
    }

    private static String helper(int num) {
        if (num == 0) {
            return ""; // necessary! 50868
        } else if (num < 20) {
            return LESS_THAN_20[num] + " "; // 1 - 19
        } else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10); // 20,30,40,50,60,70,80,90
        } else {
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100); // > 100
        }
    }
}
