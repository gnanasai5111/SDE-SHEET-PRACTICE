Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it can trap after raining.

Example 1:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped.


Brute Force 
  
For each index, we have to find the amount of water that can be stored and we have to sum it up.
If we observe carefully the amount the water stored at a particular index is the minimum of maximum elevation to the left and right of the index minus the elevation at that index.
  
class Solution {
    public int trap(int[] height) {
        int count=0;
        for(int i=0;i<height.length;i++){
            int maxLeft=height[i];
            int maxRight=height[i];
            int left=i-1;
            int right=i+1;
            while(left>=0){
                if(height[left]>maxLeft){
                    maxLeft=height[left];
                }
                left--;
            }
            while(right<height.length){
                if(height[right]>maxRight){
                    maxRight=height[right];
                }
                right++;
            }
            count+=Math.min(maxLeft,maxRight)-height[i];
        }
        return count;
    }
}

Time complexity - o(N^2)
Space compelxity - o(1)

Optimised approach 

We are taking O(N) for computing leftMax and rightMax at each index.
The complexity can be boiled down to O(1) if we precompute the leftMax and rightMax at each index.

Approach: Take 2 array prefix and suffix array and precompute the leftMax and rightMax for each index beforehand.
Then use the formula min(prefix[I], suffix[i])-arr[i] to compute water trapped at each index.

class Solution {
    public int trap(int[] height) {
        int count=0;
        int pref[]=new int[height.length];
        int suff[]=new int[height.length];
        for(int i=0;i<height.length;i++){
           if(i==0){
            pref[i]=height[i];
           }
           else{
            pref[i]=Math.max(pref[i-1],height[i]);
           }
        }
        for(int i=height.length-1;i>=0;i--){
           if(i==height.length-1){
            suff[i]=height[i];
           }
           else{
            suff[i]=Math.max(suff[i+1],height[i]);
           }
        }
        for(int i=0;i<height.length;i++){
            count+=Math.min(pref[i],suff[i])-height[i];
        }
        return count;
    }
}

Time complexity - o(N)
Space compelxity - o(N)

