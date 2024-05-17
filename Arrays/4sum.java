Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

Brute Force 

 Here, we will check all possible quadruplets using 4 loops(as we did in the 3-sum problem) and among them,
we will consider the ones whose sum is equal to the given target. 
And before considering them as our answer we need to sort the quadruplets in ascending order.

class Solution {
    public List<Integer> sort(int a,int b,int c,int d){
        List<Integer> sortedValues=new ArrayList<>();
        sortedValues.add(a);
        sortedValues.add(b);
        sortedValues.add(c);
        sortedValues.add(d);
        Collections.sort(sortedValues);
        return sortedValues;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                    for(int l=k+1;l<nums.length;l++){
                        if(nums[i]+nums[j]+nums[k]+nums[l]==target){                          
                            res.add(sort(nums[i],nums[j],nums[k],nums[l]));
                        }
                    }
                }
            }
        }
        return new ArrayList<>(new HashSet<>(res));
        
    }
}

Time complexity - o(N^4)
Space compelxity - o(2* size of res) - beacause we are using set data structure


Optimised approach :

In the previous approach, we were using 4 loops but in this, we want to reduce that to 3 loops. We have to somehow manage to calculate nums[l] as we are planning to remove the fourth loop(i.e. l).
In order to calculate nums[l], we can derive a formula like the following:

nums[l] = target - (nums[i]+nums[j]+nums[k])
So, we will first calculate the triplets nums[i], nums[j], and nums[k] using 3 loops and for the fourth one
i.e. nums[l] we will not use another loop and instead we will look up the value target-(nums[i]+nums[j]+nums[k]) in the array.
Thus we can remove the fourth loop from the algorithm.

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> set = new HashSet<>(); // Use a set to store unique quadruplets.
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Set<Long> hash = new HashSet<>(); // Use a set to store unique sums.

                for (int k = j + 1; k < n; k++) {
                    long sum = (long) nums[i] + nums[j] + nums[k];
                    long forth = (long) target - sum;

                    if (hash.contains(forth)) {
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], (int) forth);
                        Collections.sort(temp);
                        set.add(temp);
                    }
                    hash.add((long) nums[k]);
                }
            }
        }

        return new ArrayList<>(set);
    }
}

Time complexity - o(N^3)
Space compelxity - o(2* size of res) +O(N)


Optimised approach -

First, we will sort the entire array.
We will use a loop(say i) that will run from 0 to n-1. This i will represent one of the fixed pointers.
In each iteration, this value will be fixed for all different values of the rest of the 3 pointers. 
Inside the loop, we will first check if the current and the previous element is the same and if it is we will do nothing and
continue to the next value of i.
After checking inside the loop, we will introduce another fixed pointer j(runs from i+1 to n-1) using another loop.
Inside this second loop, we will again check for duplicate elements and only perform any further operation if the elements are different.
Inside the second loop, there will be 2 moving pointers i.e. k(starts from j+1) and l(starts from the last index).
The pointer k will move forward and the pointer l will move backward until they cross each other while the value of i and j will 
be fixed.
Now we will check the sum i.e. nums[i]+nums[j]+nums[k]+nums[l].
If the sum is greater, then we need lesser elements and so we will decrease the value of l.
If the sum is lesser than the target, we need a bigger value and so we will increase the value of k. 
If the sum is equal to the target, we will simply insert the quad i.e. nums[i], nums[j], nums[k], and nums[l] into our answer and
move the pointers k and l skipping the duplicate elements(i.e. by checking the adjacent elements while moving the pointers).
Finally, we will have a list of unique quadruplets.


class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1;j<nums.length;j++){
                if(j>i+1 && nums[j]==nums[j-1]){
                    continue;
                }
                int k=j+1;
                int l=nums.length-1;
                while(k<l){
                    long sum=(long)nums[i]+(long)nums[j]+(long)nums[k]+(long)nums[l];
                    long t=(long)target;
                    if(sum<t){
                        k++;
                    }
                    else if(sum>t){
                        l--;
                    }
                    else{
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++;
                        l--;
                        while(k<l && nums[k]==nums[k-1]){
                            k++;
                        }
                        while(k<l && nums[l]==nums[l+1]){
                            l--;
                        }

                    }
                }
            }
        }
        return ans;
        
    }
}

Time complexity - o(N^3)
Space compelxity - o(2* size of res)
