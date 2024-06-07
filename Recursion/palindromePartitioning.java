Given a string s, partition s such that every  substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Approach :

The initial idea will be to make partitions to generate substring and check if the substring generated out of the partition will
be a palindrome. Partitioning means we would end up generating every substring and checking for palindrome at every step.
Since this is a repetitive task being done again and again, at this point we should think of recursion.
The recursion continues until the entire string is exhausted. After partitioning, every palindromic substring is inserted in
a data structure When the base case has reached the list of palindromes generated during that recursion call is inserted in a 
vector of vectors/list of list.

class Solution {
    public boolean isPalindrome(String s,int i,int j){
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public void validPalindromePartitions(int index,String s,List<List<String>> res,List<String> inner){
        if(index==s.length()){
            res.add(new ArrayList<>(inner));
            return;
        }
        for(int i=index;i<s.length();i++){
            if(isPalindrome(s,index,i)){
                inner.add(s.substring(index, i + 1));
                validPalindromePartitions(i+1,s,res,inner);
                inner.remove(inner.size()-1);
            }
        }
    }
    public List<List<String>> partition(String s) {
        List<List<String>> res=new ArrayList<>();
        validPalindromePartitions(0,s,res,new ArrayList<>());
        return res;
    }
}

Time Complexity: O( (2^n) *k*(n/2) )
Space Complexity: O(k * x)
