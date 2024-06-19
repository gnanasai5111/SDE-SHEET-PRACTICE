There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that
the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Brute Force :

Traverse the array , if the element is found return index else return -1

class Solution {
    public int search(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            if(target==nums[i]){
                return i;
            }
        }
        return -1;
    }
}

Time compleixty - o(N)
Space complexity - o(1)

Optimised approach :

Use Binary Search 

Place the 2 pointers i.e. low and high: Initially, we will place the pointers like this: low will point to the first index, and high will point to the last index.
Calculate the ‘mid’: Now, inside a loop, we will calculate the value of ‘mid’ using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
Check if arr[mid] == target: If it is, return the index mid.
Identify the sorted half, check where the target is located, and then eliminate one half accordingly:
If arr[low] <= arr[mid]: This condition ensures that the left part is sorted.
If arr[low] <= target && target <= arr[mid]: It signifies that the target is in this sorted half. So, we will eliminate the right half (high = mid-1).
Otherwise, the target does not exist in the sorted half. So, we will eliminate this left half by doing low = mid+1.
Otherwise, if the right half is sorted:
If arr[mid] <= target && target <= arr[high]: It signifies that the target is in this sorted right half. So, we will eliminate the left half (low = mid+1).
Otherwise, the target does not exist in this sorted half. So, we will eliminate this right half by doing high = mid-1.
Once, the ‘mid’ points to the target, the index will be returned.
This process will be inside a loop and the loop will continue until low crosses high. If no index is found, we will return -1.

class Solution {
    public int binarySearch(int s,int e,int nums[],int t){
      
        while(s<=e){
              int mid=(s+e)/2;
            if(nums[mid]==t){
                return mid;
            }
            else if(nums[s]<=nums[mid]){
                if(t>=nums[s] && t<nums[mid]){
                    e=mid-1;
                }
                else{
                    s=mid+1;
                }
            }
            else if(nums[e]>=nums[mid]){
                if(t>nums[mid] && t<=nums[e]){
                    s=mid+1;
                }
                else{
                    e=mid-1;
                }

            }
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        return binarySearch(0,nums.length-1,nums,target);
    }
}

Time compleixty - o(logN)
Space complexity - o(1)
