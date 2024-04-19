It follows divide and merge technique.
Where you divide the array till it reaches a single element and backtrack and merge it to a sorted array

class Solution {
    public void merge(int nums[],int low,int mid,int high){
        ArrayList<Integer> sub=new ArrayList<>();
        int left=low;
        int right=mid+1;
        while(left<=mid && right<=high){
            if(nums[left]>nums[right]){
                sub.add(nums[right]);
                right++;
            }
            else{
                sub.add(nums[left]);
                left++;
            }
        }
        while(left<=mid){
            sub.add(nums[left]);
            left++;
        }
        while(right<=high){
            sub.add(nums[right]);
            right++;
        }

        for(int k=low;k<=high;k++){
            nums[k]=sub.get(k-low);
        }
    }
    public void divide(int nums[],int low,int high){
        if(low==high){
            return;
        }
        int mid=(low+high)/2;
        divide(nums,low,mid);
        divide(nums,mid+1,high);
        merge(nums,low,mid,high);
    }
    public int[] sortArray(int[] nums) {
        divide(nums,0,nums.length-1);
        return nums;      
    }
}

Time complexity - o(N*logN)
Space ecomplexity - o(N)
