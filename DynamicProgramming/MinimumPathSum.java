Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers
along its path.

Note: You can only move either down or right at any point in time.

Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Approach 1 : Recursion

class Solution {
    public int getMinSum(int r,int c,int grid[][],int m,int n){
        if(r>=m || c>=n){
            return Integer.MAX_VALUE;
        }
        if(r==m-1 && c==n-1){
            return grid[r][c];
        }
        int f=getMinSum(r+1,c,grid,m,n);
        int s=getMinSum(r,c+1,grid,m,n);
        return grid[r][c]+Math.min(f,s);
    }
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        return getMinSum(0,0,grid,m,n);
    }
}

Time complexity -  O(N*M)
Space complexity -  O(N*M)

Approach 2 : Memoization

class Solution {
    public int getMinSum(int r,int c,int grid[][],int m,int n,int memo[][]){
        if(r>=m || c>=n){
            return Integer.MAX_VALUE;
        }
        if(memo[r][c]!=0){
            return memo[r][c];
        }
        if(r==m-1 && c==n-1){
            return grid[r][c];
        }
        int f=getMinSum(r+1,c,grid,m,n,memo);
        int s=getMinSum(r,c+1,grid,m,n,memo);
        return memo[r][c]=grid[r][c]+Math.min(f,s);
    }
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int memo[][]=new int[m][n];
        return getMinSum(0,0,grid,m,n,memo);
    }
}

Time complexity -  O(N*M)
Space complexity - O((M-1)+(N-1)) + O(N*M)

Approach - 3: Tabulation 

class Solution {
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int dp[][]=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0){
                    dp[i][j]=grid[i][j];
                    continue;
                }
                int l=grid[i][j];
                int u=grid[i][j];
                if(j>0){
                    l=dp[i][j-1];
                }
                if(i>0){
                    u=dp[i-1][j];
                }
                dp[i][j]=Math.min(l,u);
            }
        }
        return dp[m-1][n-1];
    }
}

Time complexity -  O(N*M)
Space complexity -  O(N*M)

Fourth approach : space optimised

class Solution {
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int prev[]=new int[n];
        for(int i=0;i<m;i++){
            int curr[]=new int[n];
            for(int j=0;j<n;j++){
                if(i==0 && j==0){
                    curr[j]=grid[i][j];
                    continue;
                }
                int l=Integer.MAX_VALUE;
                int u=Integer.MAX_VALUE;
                if(j>0){
                    l=curr[j-1]+grid[i][j];
                }
           
                if(i>0){
                    u=prev[j]+grid[i][j];
                }
                curr[j]=Math.min(l,u);
            }
            prev=curr;
        }
        return prev[n-1];
    }
}

Time complexity -  O(N*M)
Space complexity -  O(N)
