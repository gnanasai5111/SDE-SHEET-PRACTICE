You are given a read-only array of N integers with values also in the range [1, N] both inclusive.
Each integer appears exactly once except A which appears twice and B which is missing. 
The task is to find the repeating and missing numbers A and B where A repeats twice and B is missing.

Example 1:
Input Format
:  array[] = {3,1,2,5,3}
Result
: {3,4)
Explanation
: A = 3 , B = 4 
Since 3 is appearing twice and 4 is missing

Brute Force approach :

Store the count of numbers in a hashmap or counter.
Traverse from 1 to N and check count is greater than 1 and if count doesnt exist and return them

public class Solution {

    public static int[] missingAndRepeating(ArrayList<Integer> arr, int n) {
        // Write your code here
       HashMap<Integer,Integer> map=new HashMap<>();
       int missing=-1;
       int repeating=-1;
        for(int i=0;i<arr.size();i++){
                map.put(arr.get(i),map.getOrDefault(arr.get(i), 0)+1);
        }
        for(int i=1;i<=n;i++){
            if(map.containsKey(i) && map.get(i)>1){
                repeating=i;
            }
            else if(!map.containsKey(i)){
                missing=i;
            }
        }

        return new int[]{missing,repeating};
    }
}

Time complexity - o(N)
Space complexity - o(N)
