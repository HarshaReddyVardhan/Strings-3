// Time Complexity : O(n), where n is the length of the string. We make a single left-to-right pass, doing O(1) work per character.
// Space Complexity : O(1), using a constant number of integer variables (calc, tail, curr, lastSign).
// Did this code successfully run on Leetcode : Yes

// Approach

// Strip the string and scan it once, building the current number curr from consecutive digits. 
//   Keep lastSign to remember which operation should be applied to curr once we hit the next operator (or the end). 
//   Maintain calc as the running total of fully resolved terms and tail as the value of the last appended term to handle operator precedence for * and /. 
//   When we see + or -, we commit curr directly to calc (as +curr or -curr) and set tail accordingly; for * or /, we undo the previously added tail from calc and replace it with the combined result (tail * curr or tail / curr). 
//   Reset curr and update lastSign, continuing until the end, then return calc.

class Solution {
    public int calculate(String s) {
        if(s == null) return 0;
        s = s.trim();
        if(s.length() == 0) return 0;
        char lastSign = '+';
        int calc = 0;
        int tail = 0;
        int curr =0;
        for(int i=0;i<s.length();++i){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                curr = curr*10 + c - '0';
            }
            if((!Character.isDigit(c) && c != ' ') || i == s.length()-1){
                if(lastSign == '+'){
                    calc = calc + curr;
                    tail = curr;
                } else if(lastSign == '-'){
                    calc = calc - curr;
                    tail = -curr;
                } else if(lastSign == '*'){
                    calc = calc - tail + (tail * curr);
                    tail = tail * curr;
                } else{
                    calc = calc - tail + (tail / curr);
                    tail = tail / curr; 
                }
                curr = 0;
                lastSign = c;
            }
        }
        return calc;
    }
}
