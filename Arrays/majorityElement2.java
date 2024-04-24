Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Example 1:

Input: nums = [3,2,3]
Output: [3]
  
Brute force :

Just Traverse the entire elements in array.
For element run an inner loop and check if element matches and increase the count and mark it as true.
So in Further visits you dont visit that value.

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        ArrayList<Integer> res=new ArrayList<>();
        boolean mark[]=new boolean[nums.length];
        for(int i=0;i<nums.length;i++){
            int count=0;
            if(mark[i]){
                continue;
            }
            for(int j=0;j<nums.length;j++){
                if(nums[i]==nums[j]){
                    count++;
                    mark[j]=true;
                }
            }
            if(count>nums.length/3){
                res.add(nums[i]);
            }
        }
        return res;
        
    }
}
Time complexity - o(N^2)
Space complexity - o(N)

Better approach :

Count the occurences of each number in hashmap and check if count is grater than num.length/3

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        ArrayList<Integer> res=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        for(Integer i:map.keySet()){
            if(map.get(i)>nums.length/3){
                res.add(i);
            }
        }
        return res;
        
    }
}

Time complexity - o(N)
Space complexity - o(N)


Optimised approach :  (Extended Boyer Moore’s Voting Algorithm)

If the array contains the majority of elements, their occurrence must be greater than the floor(N/3). 
Now, we can say that the count of minority elements and majority elements is equal up to a certain point in the array.
So when we traverse through the array we try to keep track of the counts of elements and the elements themselves for 
which we are tracking the counts. 

After traversing the whole array, we will check the elements stored in the variables. 
Then we need to check if the stored elements are the majority elements or not by manually checking their counts.


class Solution {
    public List<Integer> majorityElement(int[] nums) {
        ArrayList<Integer> res=new ArrayList<>();
        int ele1=Integer.MIN_VALUE;
        int ele2=Integer.MIN_VALUE;
        int c1=0;
        int c2=0;
        for(int i=0;i<nums.length;i++){
           if(c1==0 && ele2!=nums[i]){
             c1=1;
             ele1=nums[i];
           }
           else if(c2==0 && ele1!=nums[i]){
            c2=1;
            ele2=nums[i];
           }
           else if(ele1==nums[i]){
            c1++;
           }
           else if(ele2==nums[i]){
            c2++;
           }
           else{
            c1--;
            c2--;
           }
        }
        c1=0;
        c2=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==ele1){
                c1++;
            }
            if(nums[i]==ele2){
                c2++;
            }
        }
        if(c1>nums.length/3){
            res.add(ele1);
        }
        if(c2>nums.length/3){
            res.add(ele2);
        }     
        return res;      
    }
}

Time complexity - o(N)
Space complexity - o(1)
