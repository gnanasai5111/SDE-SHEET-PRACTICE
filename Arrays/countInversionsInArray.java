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

Time complexity - o(N*2)
Space complexity - o(1)

Optimised approach

The steps are basically the same as they are in the case of the merge sort algorithm. 
The change will be just a one-line addition inside the merge() function.
Inside the merge(), we need to add the number of pairs to the count when a[left] > a[right].

public class Solution {
    public static long merge(long arr[],int low,int mid,int high){
        int left=low;
        int right=mid+1;
        ArrayList<Long> sub=new ArrayList<>();
        long count=0;
        while(left<=mid && right<=high){
            if(arr[left]>arr[right]){
                count+=mid-left+1;
                sub.add(arr[right]);
                right++;
            }
            else{
                sub.add(arr[left]);
                left++;
            }
        }
        while(left<=mid){
            sub.add(arr[left]);
            left++;
        }
        while(right<=high){
            sub.add(arr[right]);
            right++;
        }
        for(int i=low;i<=high;i++){
            arr[i]=sub.get(i-low);
        }
        return count;
    }
    public static long divide(long arr[],int low,int high){
        long count=0;
        if(low==high){
            return count;
        }
        int mid=(low+high)/2;
        count+=divide(arr,low,mid);
        count+=divide(arr,mid+1,high);
        count+=merge(arr,low,mid,high);
        return count;
    }
    public static long getInversions(long arr[], int n) {
        // Write your code here.
        return divide(arr,0,arr.length-1);
    }
} 

Time Complexity: O(N*logN)
Space complexity - o(N)

Time complexity - o(N*logN)
Space complexity - o(N)
