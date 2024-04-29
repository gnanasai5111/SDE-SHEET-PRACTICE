Given an array having both positive and negative integers. The task is to compute the length of the largest subarray with sum 0.

Example 1:

Input:
N = 8
A[] = {15,-2,2,-8,1,7,10,23}
Output: 5
Explanation: The largest subarray with
sum 0 will be -2 2 -8 1 7

Brute force :

Traverse through all subarrays and find sum .If sum equal to zero find the maxLength 

class GfG
{
    int maxLen(int arr[], int n)
    {
        // Your code here
        int max=0;
        for(int i=0;i<arr.length;i++){
            int sum=0;
            for(int j=i;j<arr.length;j++){
                sum=sum+arr[j];
                if(sum==0){
                    max=Math.max(j-i+1,max);
                }
            }
        }
        return max;
    }
}

Time complexity - o(N^2)
Space complexity - o(1)

Better approach :

Now let’s say we know that the sum of subarray(i, j) = S, and we also know that the sum of subarray(i, x) = S where i < x < j.
We can conclude that the sum of subarray(x+1, j) = 0.

Let us understand the above statement clearly,

So in this problem, we’ll store the prefix sum of every element, and if we observe that 2 elements have the same prefix sum,
we can conclude that the 2nd part of this subarray sums to zero

class GfG
{
    int maxLen(int arr[], int n)
    {
        // Your code here
        HashMap<Integer,Integer> map=new HashMap<>();
        int sum=0,max=0;
        for(int i=0;i<arr.length;i++){
            sum=sum+arr[i];
            if(sum==0){
                max=Math.max(max,i+1);
            }
            else{
                if(map.get(sum)!=null){
                    max=Math.max(max,i-map.get(sum));
                }
                else{
                    map.put(sum,i);
                }
            }
        }
        return max;
    }
}

Time complexity - o(N)
Space complexity - o(N)
