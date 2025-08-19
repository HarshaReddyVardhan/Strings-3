// Time Complexity : O(log10 n) — we process the number in chunks of three digits (thousands groups) and each chunk is handled in constant time; for a 32-bit int, this is effectively O(1).
// Space Complexity : O(1) auxiliary space — recursion depth is constant (at most a few calls per 3‑digit chunk) and arrays are fixed; ignoring the output string size.
// Did this code successfully run on Leetcode : Yes

// Approach 

// Split the integer into groups of three digits (ones, thousands, millions, billions) and process each nonzero group from least significant to most. 
//   For each 3‑digit group, use a helper that spells numbers <20 directly, tens (20–90) via a tens array, and hundreds as “X Hundred {rest}”. 
//   Append the appropriate scale word (“”, “Thousand”, “Million”, “Billion”) after converting each group. 
//   Concatenate groups with spaces, allowing the helper to add trailing spaces and trimming the final result at the end. 
//   Handle the special case num == 0 by returning “Zero”.

class Solution {
    String[] below_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen","Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

  String[] hundreds = {"","","Twenty","Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

  String[] thousands = {"", "Thousand", "Million", "Billion"};
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        String word = "";
        int i=0;
        while(num > 0){
            if(num % 1000 != 0 )
                word  = helper(num % 1000) + thousands[i] + " " + word;
            num = num/1000;
            i++;
        }
        return word.trim();
    }

    private String helper(int num){
        if(num == 0)
            return "";
        // when number is lesser than 20
        else if(num < 20){
            return below_20[num%20] +" ";
        }
        // when number is lesser than 100 but greater than 19
        else if(num < 100){
            return hundreds[num/10] + " "+helper(num%10);
        }
        else
            return below_20[num/100] + " Hundred "+helper(num%100);
    }
}
