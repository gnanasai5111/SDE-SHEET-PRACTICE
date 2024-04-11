Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.

Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Brute force :

Sorting the array

Time complexity - o(NlogN)
Space complexity - o(1)

Better approach :

Count number of 0s,1s and 2s.And store them by iterating through counts

class Solution {
    public void sortColors(int[] nums) {
        int c0=0,c1=0,c2=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                c0++;
            }
            else if(nums[i]==1){
                c1++;
            }
            else{
                c2++;
            }
        }
        int i=0;
        while(c0>0){
            nums[i]=0;
            i++;
            c0--;
        }
        while(c1>0){
            nums[i]=1;
            i++;
            c1--;
        }
        while(c2>0){
            nums[i]=2;
            i++;
            c2--;
        }
        
    }
}  
  
Time complexity - o(N)
Space complexity - o(1)

Optimised approach :

Dutch National Flag algorithm

Intuition

0 to low-1 -> contains 0
low to mid-1 -> contains 1
mid to high->contains 0/1/2 (unsorted)
high+1 to n-1 -> contains 2

so initailly we start mid at 0 and high at end , so everything is unsorted at start , then we move pointers .
The loop Runs till mid crosses high i.e, everything is sorted.

class Solution {
    public void sortColors(int[] nums) {
        int low=0,mid=0,high=nums.length-1;
        while(mid<=high){
            if(nums[mid]==0){
                int temp=nums[low];
                nums[low]=nums[mid];
                nums[mid]=temp;
                low++;
                mid++;
            }
            else if(nums[mid]==1){
                mid++;
            }
            else{
                int temp=nums[mid];
                nums[mid]=nums[high];
                nums[high]=temp;
                high--;
            }

        }
        
    }
}

Time complexity - o(N)
Space complexity - o(1)
