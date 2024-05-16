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


Optimisec approach - Two Pointer

Take 2 pointers l(left pointer) and r(right pointer) pointing to 0th and (n-1)th index respectively. 
Take two variables leftMax and rightMax and initialize them to 0. 
If height[l] is less than or equal to height[r] then if leftMax is less than height[l] update leftMax to height[l]
else add leftMax-height[l] to your final answer and move the l pointer to the right i.e l++. 
If height[r] is less than height[l], then now we are dealing with the right block.
If height[r] is greater than rightMax, then update rightMax to height[r]
else add rightMax-height[r] to the final answer. 
Now move r to the left. Repeat these steps till l and r crosses each other.

We need a minimum of leftMax and rightMax.
So if we take the case when height[l]<=height[r] we increase l++,
so we can surely say that there is a block with a height more than height[l] to the right of l. 
And for the same reason when height[r]<=height[l] we can surely say that there is a block to the left of r 
which is at least of height[r]. So by traversing these cases and using two pointers approach the time complexity
can be decreased without using extra space.

Code:

class Solution {
    public int trap(int[] height) {
        int count=0;
        int l=0,r=height.length-1;
        int leftMax=0,rightMax=0;
        while(l<=r){
            if(height[l]<=height[r]){
                if(height[l]>leftMax){
                    leftMax=height[l];
                }
                else{
                    count+=leftMax-height[l];
                }
                l++;
            }
            else{
                if(height[r]>rightMax){
                    rightMax=height[r];
                }
                else{
                    count+=rightMax-height[r];
                }
                r--;
            }
        }
        return count;
    }
}

Time complexity - o(N)
Space compelxity - o(1)
