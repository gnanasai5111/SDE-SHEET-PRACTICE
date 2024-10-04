You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
"1" -> 'A'
"2" -> 'B'
...
"25" -> 'Y'
"26" -> 'Z'

However, while decoding the message, you realize that there are many different ways you can decode the message because some codes
are contained in other codes ("2" and "5" vs "25").
For example, "11106" can be decoded into:
"AAJF" with the grouping (1, 1, 10, 6)
"KJF" with the grouping (11, 10, 6)
The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
Note: there may be strings that are impossible to decode.
Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way,
return 0.
The test cases are generated so that the answer fits in a 32-bit integer.
  
Example 1:
Input: s = "12"
Output: 2
Explanation:
"12" could be decoded as "AB" (1 2) or "L" (12).


First Approach : 

- This solution is for decoding a string where each character represents a digit from 1 to 26, corresponding to letters of the
  alphabet (1 -> 'A', 2 -> 'B', ..., 26 -> 'Z'). The function recursively counts all possible ways to decode the string, 
  starting from index 0.
- In the findAllWays function, the base case checks if the index has reached or exceeded the length of the string.
- If it has, it returns 1, indicating that this is a valid decoding path. If the character at the current index is '0',
  it returns 0, since '0' cannot be decoded.
- The function then tries two options: decoding one character (using findAllWays(index + 1, s)) or decoding two characters if
  valid (using findAllWays(index + 2, s)), as long as the current character and the next one form a valid two-digit number 
  (either '10' to '26').
- Finally, the function returns the sum of the two options, which gives the total number of ways to decode the string from the 
  current index.
- In the numDecodings method, the recursive decoding function is called starting at index 0

class Solution {
    public int findAllWays(int index, String s){
        if(index>=s.length()){
            return 1;
        }
        if(s.charAt(index)=='0'){
            return 0;
        }
        int n1=findAllWays(index+1,s);
        int n2=0;
        if(index + 1 < s.length() && (s.charAt(index) == '1' || (s.charAt(index) == '2' && s.charAt(index + 1) >= '0'
                                                                && s.charAt(index + 1) <= '6'))){
            n2 = findAllWays(index + 2, s);  
        }
        return n1+n2;
    }
    public int numDecodings(String s) {   
        return findAllWays(0,s);
    }
}

Time complexity - o(2^N)
Space complexity - o(N)

Approach 2 :

- This solution aims to find the number of ways to decode a string where each character represents a digit from 1 to 26, 
   corresponding to letters of the alphabet.
- It uses a recursive approach with memoization to store the results of subproblems, preventing repeated calculations and optimizing
  performance.
- The findAllWays function begins by checking if the index has reached or exceeded the length of the string, in which case it returns 1,
  indicating that a valid decoding has been found. If the character at the current index is '0', it returns 0, as '0' can't be decoded.
- If the current index has already been computed and stored in the memoization array, it returns the stored result.
- The function explores two possibilities: decoding a single character by calling findAllWays with index + 1, or decoding two characters
  (if valid) by checking if the character at the current index is '1' or '2', and if the next character is valid.
- If so, it calls findAllWays with index + 2.
- The memoization array stores the results for each index to avoid recalculating them, and the final result is returned from the
  first call at index 0.
- In the numDecodings method, a memoization array is initialized and the recursive function is called starting from index 0.
- The memoization ensures that the solution runs efficiently by avoiding repeated computations.

class Solution {
    public int findAllWays(int index, String s,int memo[]){
        if(index>=s.length()){
            return 1;
        }
        if(s.charAt(index)=='0'){
            return 0;
        }
        if(memo[index]!=0){
            return memo[index];
        }
        int n1=findAllWays(index+1,s,memo);
        int n2=0;
        if(index + 1 < s.length() && (s.charAt(index) == '1' || (s.charAt(index) == '2' && s.charAt(index + 1) >= '0' &&
                                                                 s.charAt(index + 1) <= '6'))){
            n2 = findAllWays(index + 2, s,memo);  
        }
        return memo[index]=n1+n2;
    }
    public int numDecodings(String s) {   
        int memo[]=new int[s.length()];
        return findAllWays(0,s,memo);
    }
}

Time complexity - o(N)
Space complexity - o(N)

Approach 3 :

- This solution decodes a string of digits, where each number corresponds to a letter, by using a dynamic programming approach. 
- It initializes a dp array, where each index i represents the number of ways to decode the substring of length i. 
- The base cases set dp[0] to 1 (representing an empty string) and handle whether the first character is '0' or not.
- The loop iterates from the second position onward, checking two conditions:
- If the current character is not '0', it takes the value from the previous index (dp[i-1]), since it can be decoded as a single 
  character.
- It checks if the two-character substring ending at the current index forms a valid number (either starting with '1' or starting with
  '2' and followed by a number between 0 and 6). If so, it adds the value from dp[i-2], since it can be decoded as two characters.
- At the end, the last element of the dp array contains the total number of valid decoding ways for the entire string.

class Solution {

    public int numDecodings(String s) {   
        int dp[]=new int[s.length()+1];
        dp[0]=1;
        dp[1]=s.charAt(0)=='0'?0:1;
        for(int i=2;i<dp.length;i++){
            if(s.charAt(i-1)!='0'){
                dp[i]=dp[i-1];
            }
            if(s.charAt(i-2) == '1' || (s.charAt(i-2) == '2' && s.charAt(i -1) >= '0' && s.charAt(i-1) <= '6')){
            dp[i] += dp[i - 2];  
        }


        }
        return dp[s.length()];
    }
}

Time complexity - o(N)
Space complexity - o(N)


Fourth approach :

- This solution decodes a string of digits where each digit or pair of digits represents a letter. 
- The goal is to determine how many valid ways the string can be decoded into letters. 
- It starts by checking if the string begins with '0' because any string starting with '0' is invalid and returns 0 in that case.
- Two variables, a and b, are initialized to track the decoding possibilities for previous characters.
- a holds the value for two steps back, and b holds the value for one step back.
- The loop iterates through the string starting from the second character. For each character, it calculates the number of
  valid decodings by considering both single and two-character decoding options:
- If the current character is not '0', b is added to the result since it can be decoded as a single character.
- If the previous character is '1', or if it's '2' followed by a digit between '0' and '6', it can form a valid two-character 
  number (10-26), so a is added to the result.
- Finally, a and b are updated to shift the calculations forward for the next iteration. After the loop, b contains the
  total number of decoding ways for the string.

class Solution {

    public int numDecodings(String s) {   
        if (s.charAt(0) == '0') {
            return 0;
        }
        int a=1;
        int b=s.charAt(0)=='0'?0:1;
        for(int i=1;i<s.length();i++){
            int c=0;
            if(s.charAt(i)!='0'){
                c=b;
            }
            if(s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) >= '0' && s.charAt(i) <= '6')){
            c += a; 
        }
        a=b;
        b=c;
        }
        return b;
    }
}

Time complexity - o(N)
Space complexity - o(1)
