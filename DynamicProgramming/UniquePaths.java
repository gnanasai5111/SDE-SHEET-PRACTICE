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

- This solution calculates the number of unique paths from the top-left corner of a grid to the bottom-right corner, where movement is restricted to either right or down. 
- The method uses recursion to explore all possible paths.
- The findPaths method is called recursively with two parameters r and c, which represent the current position (row and column) in the grid. 
- If the current position exceeds the grid boundaries (r >= m or c >= n), it returns 0 because it is out of bounds. 
- If the position reaches the bottom-right corner (r == m-1 && c == n-1), it returns 1, representing a valid path.
- At each step, the method recursively explores two options: moving down (r+1, c) and moving right (r, c+1).
- The results of these two recursive calls are summed to calculate the total number of paths.
- Finally, the uniquePaths function initiates the recursive exploration by starting from the top-left corner (0, 0) and passing the dimensions of the grid (m and n).

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

- This solution computes the number of unique paths in a grid using recursion with memoization. 
- The goal is to find how many ways there are to move from the top-left corner to the bottom-right corner, only allowing movements either down or right.
- The findPaths method is called recursively to explore all possible paths. It takes in the current position (r, c) and checks if it is out of bounds (r >= m || c >= n).
- If so, it returns 0, indicating that no valid path exists.
- Memoization is used to avoid redundant calculations. The memo array stores results of previously computed subproblems.
- If the result for the current cell has already been computed (memo[r][c] != 0), it is returned directly.
- If the current position is the bottom-right corner (r == m-1 && c == n-1), the method returns 1, as it represents a valid path.
- The method recursively calculates the total number of paths by summing up the results of moving either down (r+1, c) or right (r, c+1).
- The result is stored in the memo array for future reference.
- Finally, the uniquePaths function initializes the memoization array and starts the recursive process from the top-left corner (0, 0)

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

- This solution uses dynamic programming to calculate the number of unique paths in a grid.
- The objective is to determine how many ways there are to move from the top-left corner to the bottom-right corner, with movements restricted to either right or down.
- A 2D array dp of size m x n is initialized to store the number of unique paths to reach each cell in the grid.
- The loops iterate over each cell in the grid. If the cell is at the top-left corner (i == 0 && j == 0), it is set to 1, as there is only one way to reach the starting point.
- For other cells, two possible paths are considered:
- From the cell directly above (dp[i-1][j]), if it exists.
- From the cell directly to the left (dp[i][j-1]), if it exists.
- The value of the current cell dp[i][j] is computed by summing these two possible paths. 
- After processing all cells, the value at the bottom-right corner (dp[m-1][n-1]) gives the total number of unique paths from the start to the end.

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

- This solution optimizes space by using a 1D array to calculate the number of unique paths in a grid. 
- Instead of using a full 2D array, it maintains two arrays: prev to store the previous row's values and temp for the current row.
- The outer loop iterates through the rows, and the inner loop processes each column.
- At the start of each row, a new temp array is initialized to hold the current row’s results.
- If the cell is the top-left corner (i == 0 && j == 0), it's set to 1, representing one way to reach the starting point.
For other cells:
- If there is a row above, the value from prev[j] (the above cell) is considered.
- If there is a column to the left, the value from temp[j-1] (the left cell) is considered.
- The current cell temp[j] is set as the sum of these two possible paths.
- At the end of each row, prev is updated to store the current row’s values (temp), and this process continues. The value at prev[n-1] gives the total number of unique paths.

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

Fifth apprach :

- The solution calculates the number of unique paths in an m x n grid using a combinatorial approach.
- The robot can move either right or down, and the task is to compute how many distinct sequences of moves will lead from the 
  top-left corner to the bottom-right corner.
- In this approach, the total number of moves the robot makes is m + n - 2 (since it makes m-1 down moves and n-1 right moves). 
- The problem reduces to finding how many ways we can choose m-1 moves (down) from the total m + n - 2 moves, which is represented 
 by the combination formula C(N, r) where N = m + n - 2 and r = m - 1.
- The loop iterates to calculate the combination formula without calculating large factorials directly.
- It does this efficiently by multiplying terms one by one and dividing at each step to avoid overflow.
- Finally, the result is cast to an integer and returned, representing the number of unique paths.

class Solution {
    public int uniquePaths(int m, int n) {
        int N=m+n-2;
        int r=m-1;
        double res=1;
        for(int i=1;i<=r;i++){
            res=res*(N-r+i)/i;
        }
        return (int)res;
        
    }
}

Time complexity -  O(M)
Space complexity - O(1)
