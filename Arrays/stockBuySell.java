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

Optmised approach :(Two ways one from start of array and other from end of array)

From End appraoch :

We start iterating from the second element from end,we will keep a track of max element towards right of it.
If current index is greater than maxRight then its loss and we make the current index as maxRight and decrement the index.
if current index is less than maxRight then we find the diff and repeat this process till we find max profit.

class Solution {
    public int maxProfit(int[] prices) {
        int max=0;
        int greatestNumOnRight=prices[prices.length-1];
        for(int j=prices.length-2;j>=0;j--){   
            if(greatestNumOnRight<prices[j]){
                greatestNumOnRight=prices[j];
            }
            else{
                max=Math.max(greatestNumOnRight-prices[j],max);
            }
        }
        return max;
        
    }
}

We start iterating from the second element from start,we will keep a track of min element towards left of it.
If current index is less than minLeft then its loss and we make the current index as minLeft and increment the index.
if current index is greater than minLeft then we find the diff and repeat this process till we find max profit.


class Solution {
    public int maxProfit(int[] prices) {
        int max=0;
        int smallestNumOnLeft=prices[0];
        for(int j=1;j<prices.length;j++){   
            if(smallestNumOnLeft>prices[j]){
               smallestNumOnLeft=prices[j];
            }
            else{
                max=Math.max(prices[j]-smallestNumOnLeft,max);
            }
        }
        return max;
        
    }
}

Time complexity - o(N)
Space complexity - o(1)


