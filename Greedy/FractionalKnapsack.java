Problem Statement: The weight of N items and their corresponding values are given.
We have to put these items in a knapsack of weight W such that the total value obtained is maximized.

Note: We can either take the item as a whole or break it into smaller units.

Example:
Input: N = 3, W = 50, values[] = {100,60,120}, weight[] = {20,10,30}.
Output: 240.00
Explanation: The first and second items  are taken as a whole  while only 20 units of the third item is taken.
Total value = 100 + 60 + 80 = 240.00


Approach :

The greedy method to maximize our answer will be to pick up the items with higher values. 
So we sort the array based on weight/value in asc so that we have max value at front.

class Solution {
    // Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int w, Item arr[], int n) {
        // Your code here
        Arrays.sort(arr,(a,b)->{
            double x=(double)a.weight/(double)a.value;
            double y=(double)b.weight/(double)b.value;
            if(x>y){
                return 1;
            }
            else if(x<y){
                return -1;
            }
            return 0;
        });
        double count=0;
        
        for(int i=0;i<arr.length;i++){
            if(w>=arr[i].weight){
                count=count+arr[i].value;
                 w=w-arr[i].weight;
            }
            else{
                count=count+((double)(arr[i].value*(double)w)/(double)arr[i].weight);
                break;
            }
        }
        return count;
    }
}

Time complexity - o(NlogN)+o(N)
Space compelxity - o(1)
