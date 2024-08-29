Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below.
More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

Example 1:

Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).

Approach 1 : (Recursion)

class Solution {
    public int getMinPathSum(int r,int c,List<List<Integer>> triangle,int n){
        if(r==n-1){
            return triangle.get(r).get(c);
        }
        int f=triangle.get(r).get(c)+getMinPathSum(r+1,c,triangle,n);
        int s=triangle.get(r).get(c)+getMinPathSum(r+1,c+1,triangle,n);
        return Math.min(f,s);
    }
    public int minimumTotal(List<List<Integer>> triangle) {      
        return getMinPathSum(0,0,triangle,triangle.size());
    }
}

Time complexity - O(N*N)
Space complexity - O(N*N)

Approach 2 : Memoization

class Solution {
    public int getMinPathSum(int r,int c,List<List<Integer>> triangle,int n,Map<String, Integer> map){
        if(r==n-1){
            return triangle.get(r).get(c);
        }
        String key=r+":"+c;
        if(map.containsKey(key)){
            return map.get(key);
        }
        int f=triangle.get(r).get(c)+getMinPathSum(r+1,c,triangle,n,map);
        int s=triangle.get(r).get(c)+getMinPathSum(r+1,c+1,triangle,n,map);
        map.put(key,Math.min(f,s));
        return Math.min(f,s);
    }
    public int minimumTotal(List<List<Integer>> triangle) {  
        Map<String, Integer> memoTable=new HashMap<>(); 
        return getMinPathSum(0,0,triangle,triangle.size(),memoTable);
    }
}

Time complexity - O(N*N)
Space complexity - O(N*N)

Approach 3 - Tabulation

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {  
        int n=triangle.size();
        int dp[][] = new int[n][n]; 
        for(int i=0;i<n;i++){
            for(int j=0;j<triangle.get(i).size();j++){
                if(i==0 && j==0){
                    dp[i][j]=triangle.get(i).get(j);
                    continue;
                }
                if(j==0){
                     dp[i][j]=triangle.get(i).get(j)+dp[i-1][j];
                }
                else if(j==i){
                     dp[i][j]=triangle.get(i).get(j)+dp[i-1][j-1];
                }
                else{
                    dp[i][j]=triangle.get(i).get(j)+Math.min(dp[i-1][j-1],dp[i-1][j]);
                }
                
            }
        }
         int minPathSum = dp[n-1][0];
        for (int j = 1; j < n; j++) {
            minPathSum = Math.min(minPathSum, dp[n-1][j]);
        }
        
        return minPathSum;
    }
}

Time complexity - O(N*N)
Space complexity - O(N*N)

Fourth apprach -space optimised

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {  
        int n=triangle.size();
        int[] front = new int[n];
        int[] cur = new int[n];  

        for (int j = 0; j < n; j++) {
            front[j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down =triangle.get(i).get(j) + front[j];
                int diagonal = triangle.get(i).get(j) + front[j + 1];
                cur[j] = Math.min(down, diagonal);
            }
            

            front = cur.clone();
        }


        return front[0];
    }
}

Time complexity - O(N*N)
Space complexity - O(N)
