Problem Statement: You are given a set of N jobs where each job comes with a deadline and profit.
The profit can only be earned upon completing the job within its deadline.
Find the number of jobs done and the maximum profit that can be obtained.
Each job takes a single unit of time and only one job can be performed at a time.

Examples

Example 1:
Input: N = 4, Jobs = {(1,4,20),(2,1,10),(3,1,40),(4,1,30)}
Output: 2 60
Explanation: The 3rd job with a deadline 1 is performed during the first unit of time .
The 1st job is performed during the second unit of time as its deadline is 4.
Profit = 40 + 20 = 60

Example 2:
Input: N = 5, Jobs = {(1,2,100),(2,1,19),(3,2,27),(4,1,25),(5,1,15)}
Output: 2 127
Explanation: The  first and third job both having a deadline 2 give the highest profit. 
Profit = 100 + 27 = 127


Approach :

The strategy to maximize profit should be to pick up jobs that offer higher profits. 
Hence we should sort the jobs in descending order of profit. Now say if a job has a deadline of 4 we can perform it anytime
between day 1-4, but it is preferable to perform the job on its last day.
This leaves enough empty slots on the previous days to perform other jobs.

class Solution
{
    //Function to find the maximum profit and the number of jobs done.
    int[] JobScheduling(Job arr[], int n)
    {
        // Your code here

        Arrays.sort(arr,(a,b)-> b.profit-a.profit);
       int max=0;
       for(int i=0;i<arr.length;i++){
           if(arr[i].deadline>max){
               max=arr[i].deadline;
           }
       }
       int jobs=0;
       int maxProfit=0;
       int res[]=new int[max+1];
       for(int i=0;i<arr.length;i++){
           int j=arr[i].deadline;
           while(j>0){
               if(res[j]==0){
                   res[j]=arr[i].profit;
                   jobs++;
                   maxProfit+=arr[i].profit;
                   break;
               }
               j--;
           }
       }
       return new int[]{jobs,maxProfit};
    }
}

Time complexity - o(NlogN)+o(N*M) where m is max deadline
Space complexity - o(M)
