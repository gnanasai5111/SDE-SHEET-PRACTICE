You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
Return the single element that appears only once.
Your solution must run in O(log n) time and O(1) space.

Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2

Brute force :

Use HashMap to measure the count of values and return the value which has count of 1

class Solution {
    public int singleNonDuplicate(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        for(Integer i:map.keySet()){
            if(map.get(i)==1){
                return i;
            }
        }
        return -1;
        
    }
}

Time complexity - o(N)
Space complexity - o(1)

Better approach :

Traverse the array by moving 2 places at a time and Compare two adjacent elements , if they are not equal return the element

class Solution {
    public int singleNonDuplicate(int[] nums) {
        for(int i=0;i<nums.length-1;i=i+2){
            if(nums[i]!=nums[i+1]){
                return nums[i];
            }
        }

        return nums[nums.length-1];
        
    }
}

Time complexity - o(N)
Space complexity - o(1)

Optimised approach :

In order to use binary search, we need to be able to look at the middle item and then determine whether the solution is the middle item, or to the left, or to the right.
The key observation to make is that the starting array must always have an odd number of elements (be odd-lengthed), because it has one element appearing once, 
and all the other elements appearing twice.



class Solution {
    public int singleNonDuplicate(int[] nums) {
        int l=0;
        int h=nums.length-1;
         while (l < h) {
            int mid = (l+h) / 2;
            
            // Check if mid is the unique element
            boolean halvesAreEven = (mid % 2 == 0);
            
            if (nums[mid] == nums[mid - 1]) {
                if (halvesAreEven) {
                    h = mid - 2; // Move left
                } else {
                    l = mid + 1; // Move right
                }
            } else if (nums[mid] == nums[mid + 1]) {
                if (halvesAreEven) {
                    l = mid + 2; // Move right
                } else {
                    h = mid - 1; // Move left
                }
            } else {
                return nums[mid]; // Unique element found
            }
        }
        
        return nums[l];
    }
}

Time complexity - o(logN)
Space complexity - o(1)
