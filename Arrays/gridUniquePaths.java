There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). 
The robot can only move either down or right at any point in time.
Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The test cases are generated so that the answer will be less than or equal to 2 * 109.

Example 1:

Input: m = 3, n = 7
Output: 28

Brute Force :

Generate all possible paths using recursion

class Solution {
    public int getUniquePaths(int r,int c,int m,int n){
        if(r==m-1 && c==n-1){
            return 1;
        }
        else if(r>=m || c>=n){
            return 0;
        }
        else{
           return getUniquePaths(r+1,c,m,n)+getUniquePaths(r,c+1,m,n);
        }
    }
    public int uniquePaths(int m, int n) {
        return getUniquePaths(0,0,m,n);
    }
}

Time complexity - o(2^(N+M))
Space complexity - o(N+M)


Better approach 

using dynamic programming 

use a visited array to keep track of count already visited cells. So that when you visit the cell again you can get the value of that cell

class Solution {
    public int getUniquePaths(int r,int c,int m,int n,int visited[][]){
        if(r==m-1 && c==n-1){
            return 1;
        }
        else if(r>=m || c>=n){
            return 0;
        }
        else if(visited[r][c]!=0){
            return visited[r][c];
        }
        else{
            return visited[r][c]=getUniquePaths(r+1,c,m,n,visited)+getUniquePaths(r,c+1,m,n,visited);
        }
    }
    public int uniquePaths(int m, int n) {
        int visited[][]=new int[m][n];
        return getUniquePaths(0,0,m,n,visited);
    }
}

Time complexity - o(N*M)
Space complexity - o(N*M)


Optimised approach (NcR formula)

If we observe examples there is a similarity in paths from start to end.
Each time we are taking an exactly m+n-2 number of steps to reach the end.
Since we need an m+n-2 number of steps to reach the end among those steps 
if we choose n-1 rightward direction or m-1 downward direction and calculate the combinations ( ie: m+n-2Cn-1 or m+n-2Cm-1)
we’ll get the total number of paths.

class Solution {
    
    public int uniquePaths(int m, int n) {
        int totalCombinations=m+n-2;
        int r=m-1;
        long ans=1;
        for(int i=1;i<=r;i++){
            ans=ans*(totalCombinations-r+i)/i;
        }
        return (int)ans;
    }
}

Time complexity - o(N) or o(M) based on what you select as r
Space complexity - o(1)
