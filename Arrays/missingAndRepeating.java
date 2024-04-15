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

Better approach - (Sorting and Math)

sort the array and check if arr[i]==arr[i+1] to find repeating value
Find actualSum i.e, sum of all elements from 1 to N and current Sum sum of all elements in array
To find missing =actualSum-(currentSum-repeating)

public class Solution {
    public static int[] missingAndRepeating(ArrayList<Integer> arr, int n) {

        // sorting the array
        Collections.sort(arr);

        int r=-1, m;

        for(int i = 0; i < n - 1; i += 1)
        {
            int x=arr.get(i),y=arr.get(i+1);
            // checking if two adjacent numbers are same
            if(x==y)
            {
                r = arr.get(i);
                break;
            }
        }

        // calculating the sum of the given array
        int currSum = 0;
        for(int i = 0; i < n; i += 1)
        {
            currSum += arr.get(i);
        }

        // sum of the numbers from 1 to n
        int actualSum = n*(n + 1)/2;

        m = actualSum - (currSum - r);

        int ans[]=new int[2];

        ans[0] = m;
        ans[1] = r;

        return ans;

    }

}

Time complexity: O(N*log(N))
Space complexity: O(1)

Optimised approach -Math


sum of N number - n*(n+1)/2 -y
sum of squares of N numbers - n*(n+1)*(2*n+1)/6
    x-y=sum of elements in array - sum of N elements
    x2-y2=sum of squares of elemts in array - sum of squares of N elements

    we can solve x and y by solving equation x and y are repeating and missing

public class Solution {
    public static int[] missingAndRepeating(ArrayList<Integer> arr, int N) {
        // Write your code here
        long n=N;
       long s1=0;
       long sn=(n*(n+1))/2;
       long s2=0;
       long s2n=(n*(n+1)*(2*n+1))/6;
        for(int i=0;i<arr.size();i++){
               s1=s1+arr.get(i);
               s2+=arr.get(i) * arr.get(i);
        }
        //S-Sn = X-Y:
        long val1 = s1-sn;

        // S2-S2n = X^2-Y^2:
        long val2 = s2-s2n;

        //Find X+Y = (X^2-Y^2)/(X-Y):
        val2 = val2 / val1;
        //Find X and Y: X = ((X+Y)+(X-Y))/2 and Y = X-(X-Y),
        // Here, X-Y = val1 and X+Y = val2:
        long x = (val1 + val2) / 2;
        long y = x - val1;

        int[] ans = {(int)y, (int)x};
        return ans;
    }
}

Time complexity - o(N)
Space complexity - o(1)
