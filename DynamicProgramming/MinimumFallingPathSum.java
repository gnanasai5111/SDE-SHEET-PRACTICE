Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below
or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col),
or (row + 1, col + 1).

Example 1:
Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.

First approach :Recursion

class Solution {
    public int getMinSum(int r,int c,int matrix[][],int n){
        if( c<0 || c>=n){
            return Integer.MAX_VALUE;
        }
        if(r==n-1){
            return matrix[r][c];
        }
        int ld=getMinSum(r+1,c-1,matrix,n);
        int md=getMinSum(r+1,c,matrix,n);
        int rd=getMinSum(r+1,c+1,matrix,n);
        return matrix[r][c]+Math.min(ld,Math.min(md,rd));
    }
    public int minFallingPathSum(int[][] matrix) {
        int minFallingSum = Integer.MAX_VALUE;
        for (int j = 0; j < matrix.length; j++) {
            minFallingSum = Math.min(minFallingSum, getMinSum(0,j,matrix,matrix.length));
        }
        return minFallingSum;      
    }
}

Time complexity - o(N*N)
Space complexity - o(N*N)

Approach 2 - tabulation 

class Solution {
    public int getMinSum(int r,int c,int matrix[][],int n,int dp[][]){
        if( c<0 || c>=n){
            return Integer.MAX_VALUE;
        }
        if(dp[r][c]!=0){
            return dp[r][c];
        }
        if(r==n-1){
            return matrix[r][c];
        }
        int ld=getMinSum(r+1,c-1,matrix,n,dp);
        int md=getMinSum(r+1,c,matrix,n,dp);
        int rd=getMinSum(r+1,c+1,matrix,n,dp);
        return dp[r][c]=matrix[r][c]+Math.min(ld,Math.min(md,rd));
    }
    public int minFallingPathSum(int[][] matrix) {
        int minFallingSum = Integer.MAX_VALUE;
        int n=matrix.length;
        int dp[][]=new int[n][n];
        for (int j = 0; j < n; j++) {
            minFallingSum = Math.min(minFallingSum, getMinSum(0,j,matrix,n,dp));
        }
        return minFallingSum;      
    }
}

Time complexity - o(N*N)
Space complexity -o(N)+ o(N*N)

Approach 3 - Tabulation

class Solution {

    public int minFallingPathSum(int[][] matrix) {
        int minFallingSum = Integer.MAX_VALUE;
        int n=matrix.length;
        int dp[][]=new int[n][n];
        for (int j = 0; j < n; j++) {
            dp[0][j]=matrix[0][j];
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                if(j==0){
                    dp[i][j]=matrix[i][j]+Math.min(dp[i-1][j],dp[i-1][j+1]);
                }
                else if(j==n-1){
                    dp[i][j]=matrix[i][j]+Math.min(dp[i-1][j],dp[i-1][j-1]);
                }
                else{
                    dp[i][j]=matrix[i][j]+Math.min(dp[i-1][j],Math.min(dp[i-1][j+1],dp[i-1][j-1]));
                }
            }
        }
        for(int j=0;j<n;j++){
            minFallingSum=Math.min(minFallingSum,dp[n-1][j]);
        }
        return minFallingSum;      
    }
}

Time complexity - o(N*N)
Space complexity - o(N*N)

Approach - 4 : space optimised

class Solution {

    public int minFallingPathSum(int[][] matrix) {
        int minFallingSum = Integer.MAX_VALUE;
        int n=matrix.length;
        int prev[]=new int[n];
        for (int j = 0; j < n; j++) {
            prev[j]=matrix[0][j];
        }
        for(int i=1;i<n;i++){
            int curr[]=new int[n];
            for(int j=0;j<n;j++){
                if(j==0){
                    curr[j]=matrix[i][j]+Math.min(prev[j],prev[j+1]);
                }
                else if(j==n-1){
                    curr[j]=matrix[i][j]+Math.min(prev[j],prev[j-1]);
                }
                else{
                    curr[j]=matrix[i][j]+Math.min(prev[j],Math.min(prev[j+1],prev[j-1]));
                }
            }
            prev=curr;
        }
        for(int j=0;j<n;j++){
            minFallingSum=Math.min(minFallingSum,prev[j]);
        }
        return minFallingSum;      
    }
}

Time complexity - o(N*N)
Space complexity - o(N)
