You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

Brute Force :

Traverse through array and at any particular index check if there are any elements to the right that are greater and has max difference

class Solution {
    public int maxProfit(int[] prices) {
        int max=0;
        for(int i=0;i<prices.length;i++){
            for(int j=i+1;j<prices.length;j++){
                if(prices[j]>prices[i]){
                    max=Math.max(max,prices[j]-prices[i]);
                }
            }
        }
        return max;
        
    }
}

Time complexity - o(N*N) - Time limit exceeded
Space complexity - o(1)
