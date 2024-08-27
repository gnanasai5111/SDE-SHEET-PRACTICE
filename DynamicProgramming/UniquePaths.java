There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

First approach : (Recursion)

class Solution {
    public int findPaths(int r,int c,int m, int n){
        if(r>=m || c>=n){
            return 0;
        }
        if(r==m-1 && c==n-1){
            return 1;
        }
        return findPaths(r+1,c,m,n)+findPaths(r,c+1,m,n);
    }
    public int uniquePaths(int m, int n) {
       return findPaths(0,0,m,n);    
    }
}

Time complexity -  O(M*N)
Space complexity -  O(M*N)

Second approach - Memoization 

class Solution {
    public int findPaths(int r,int c,int m, int n,int memo[][]){
        if(r>=m || c>=n){
            return 0;
        }
        if(memo[r][c]!=0){
            return memo[r][c];
        }
        if(r==m-1 && c==n-1){
            return 1;
        }
        return memo[r][c]=findPaths(r+1,c,m,n,memo)+findPaths(r,c+1,m,n,memo);
    }
    public int uniquePaths(int m, int n) {
        int memo[][]=new int[m][n];
       return findPaths(0,0,m,n,memo);    
    }
}

Time complexity -  O(M*N)
Space complexity - O((N-1)+(M-1)) + O(M*N)

Approach 3 : Tabulation

class Solution {
    public int uniquePaths(int m, int n) {
        int dp[][]=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
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

Time complexity -  O(M*N)
Space complexity - O(M*N)

Fourth approach :

class Solution {
    public int uniquePaths(int m, int n) {
        int prev[]=new int[n];
        for(int i=0;i<m;i++){
            int temp[] = new int[n];
            for(int j=0;j<n;j++){
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

Time complexity -  O(M*N)
Space complexity - O(N)
