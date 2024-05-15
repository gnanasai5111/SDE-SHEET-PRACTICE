

Brute Force :

Take an extra space of nums.length.
Initially for the first element push the elemnt to res.
Then next compare if element in res is equal to current element.
If they are equal move the current element else push the current element to res and increment res index

class Solution {
    public int removeDuplicates(int[] nums) {
        int res[]=new int[nums.length];
        int j=0;
        for(int i=0;i<nums.length;i++){
            if(i==0){
                res[i]=nums[i];
                j++;
            }
            else if(nums[i]!=res[j-1]){
                res[j]=nums[i];
                j++;
            }
        }
        
        for(int i=0;i<nums.length;i++){
            nums[i]=res[i];
        }
        return j;   
    }
}


Time compelxity - o(N)
Space complexity - o(N)


Optimised approach :

let j be count of distinct elements.Initially there always exists one unique element so assign j to 1.
Traverse the array and check if consective elements are not equal and move the elemnt to j and increment j
Repeat this process.


class Solution {
    public int removeDuplicates(int[] nums) {
        int j=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[i-1]){
                nums[j]=nums[i];
                j++;
            }
        }
        return j; 
    }
}

Time compelxity - o(N)
Space complexity - o(1)
