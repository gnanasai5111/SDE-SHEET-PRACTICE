For a given integer array/list 'ARR' of size 'N' containing all distinct values, find the total number of 'Inversions' that may exist.
An inversion is defined for a pair of integers in the array/list when the following two conditions are met.
A pair ('ARR[i]', 'ARR[j]') is said to be an inversion when:
1. 'ARR[i] > 'ARR[j]' 
2. 'i' < 'j'
Where 'i' and 'j' denote the indices ranging from [0, 'N').

Sample Input 1 :
3
3 2 1
Sample Output 1 :
3
Explanation of Sample Output 1:
We have a total of 3 pairs which satisfy the condition of inversion. (3, 2), (2, 1) and (3, 1).

Brute Force :

First, we will run a loop(say i) from 0 to N-1 to select the first element in the pair.
As index j should be greater than index i, inside loop i, we will run another loop i.e. j from i+1 to N-1.
Inside this second loop, we will check if a[i] > a[j] i.e. if a[i] and a[j] can be a pair. 
If they satisfy the condition, we will increase the count by 1.
Finally, we will return the count i.e. the number of such pairs.

public class Solution {
    public static long getInversions(long arr[], int n) {
        // Write your code here.
        long count=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    count++;
                }
            }
        }
        return count;
    }
}

Time complexity - o(N*N)
Space complexity - o(1)
