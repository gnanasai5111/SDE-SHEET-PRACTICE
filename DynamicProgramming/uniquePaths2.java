You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). 
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). 
The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid.
A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The testcases are generated so that the answer will be less than or equal to 2 * 109.

First approach : Recursion 

class Solution {
    public int findPaths(int r,int c,int[][] obstacleGrid){
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        if(r>=m || c>=n){
            return 0;
        }
        if(obstacleGrid[r][c]==1){
            return 0;
        }
        if(r==m-1 && c==n-1){
            return 1;
        }
     
        return findPaths(r+1,c,obstacleGrid)+findPaths(r,c+1,obstacleGrid);
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

         return findPaths(0,0,obstacleGrid);  
    }
}

Time complexity - O(N*M)
Space complexity -  O(N*M)

Second approach : Memoization

class Solution {
    public int findPaths(int r,int c,int[][] obstacleGrid,int memo[][]){
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        if(r>=m || c>=n){
            return 0;
        }
        if(memo[r][c]!=0){
            return memo[r][c];
        }
        if(obstacleGrid[r][c]==1){
            return 0;
        }
        if(r==m-1 && c==n-1){
            return 1;
        }
     
        return memo[r][c]=findPaths(r+1,c,obstacleGrid,memo)+findPaths(r,c+1,obstacleGrid,memo);
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int memo[][]=new int[obstacleGrid.length][obstacleGrid[0].length];
         return findPaths(0,0,obstacleGrid,memo);  
    }
}

Time complexity - O(N*M)
Space complexity -  O((M-1)+(N-1)) + O(N*M)

Third approach - Tabulation 

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        int dp[][]=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(obstacleGrid[i][j]==1){
                    dp[i][j]=0;
                    continue;
                }
                if(i==0 && j==0){
                    dp[i][j]=1;
                    continue;
                }
                int up = 0;
                int left = 0;
                if (i > 0)
                    up = dp[i - 1][j];
                if (j > 0)
                    left = dp[i][j - 1];
                dp[i][j] = up + left;
            }
        }
       return dp[m-1][n-1];  
    }
}

Time complexity - O(N*M)
Space complexity - O(N*M)

Fourth approach - space optimised 

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        int prev[]=new int[n];
        for(int i=0;i<m;i++){
            int temp[] = new int[n];
            for(int j=0;j<n;j++){
                if(obstacleGrid[i][j]==1){
                    temp[j]=0;
                    continue;
                }
                if(i==0 && j==0){
                    temp[j]=1;
                    continue;
                }
                int up = 0;
                int left = 0;
                if (i > 0)
                    up = prev[j];
                if (j > 0)
                    left = temp[j - 1];
                temp[j] = up + left;
            }
            prev=temp;
        }
       return prev[n - 1];
    }
}

Time complexity - O(N*M)
Space complexity - O(N)
