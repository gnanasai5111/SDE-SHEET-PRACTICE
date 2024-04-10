Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
You must do it in place.
  
Example 1:

Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Brute Force :

Traverse the matrix.
If any cell (i,j) contains the value 0, we will mark all cells in row i and column j with -1 except those which contain 0.
Finally, we will mark all the cells containing -1 with 0.

class Solution {
    public void markRows(int[][] matrix,int row){
        for(int j=0;j<matrix[0].length;j++){
            if(matrix[row][j]!=0){
                matrix[row][j]=-1;
            }
        }
    }
    public void markCols(int[][] matrix,int col){
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][col]!=0){
                matrix[i][col]=-1;
            }
        }
    }
    public void setZeroes(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    markRows(matrix,i);
                    markCols(matrix,j);
                }
                
            }
        }
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){  
                if(matrix[i][j]==-1){
                    matrix[i][j]=0;
                }
            }
        }

    }
}

This approach works for positive values 

Time complexity - O((N*M)*(N + M)) + O(N*M)
Space complexity - o(1)


Better approach :

First, we will declare two arrays: a row array of size M and a col array of size N and both are initialized with 0.
Traverse the matrix.
If any cell (i,j) contains the value 0, we will mark ith index of row array i.e. row[i] and jth index of col array col[j] as 1.
It signifies that all the elements in the ith row and jth column will be 0 in the final matrix.
Finally, we will again traverse the entire matrix and we will put 0 into all the cells (i, j) for which either row[i] or col[j] is marked as 1.

class Solution {
    public void setZeroes(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int r[]=new int[m];
        int c[]=new int[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    r[i]=1;
                    c[j]=1;
                }             
            }
        }   
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){  
                if(r[i]==1 || c[j]==1){
                    matrix[i][j]=0;
                }
            }
        }
    }
}

Time complexity -o(2*(N*M))
Space compleity - o(n+m)


Optimised apprach :

Instead of taking extra space , here we consider first row and first column to mark.
Since we would have common cell for row and column i.e, matrix[0][0] we take an extra cell for column i.e, col0
If any cell (i,j) contains the value 0, we will mark those values in first row and first col.
After step 1 is completed, we will modify the cells from (1,1) to (m-1, n-1) using the values from the 1st row, 1st column, and col0 variable.
We will not modify the 1st row and 1st column of the matrix here as the modification of the rest of the matrix(i.e. From (1,1) to (m-1, n-1))
is dependent on that row and column.
Finally, we will change the 1st row and column using the values from matrix[0][0] and col0 variable.
Here also we will change the row first and then the column.
If matrix[0][0] = 0, we will change all the elements from the cell (0,1) to (0, m-1), to 0.
If col0 = 0, we will change all the elements from the cell (0,0) to (n-1, 0), to 0.

class Solution {
    
    public void setZeroes(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int col0=1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    if(j==0){
                        col0=0;
                    }
                    else{
                        matrix[0][j]=0;
                    }
                }
                
            }
        }
        
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){  
                if(matrix[i][j]!=0 && (matrix[i][0] ==0 || matrix[0][j]==0)){
                    matrix[i][j]=0;
                }
            }
        }
        if(matrix[0][0]==0){
            for(int j=0;j<n;j++){
                matrix[0][j]=0;
            }
        }
        if(col0==0){
            for(int i=0;i<m;i++){
                matrix[i][0]=0;
            }
        }
    }
}

Time complexity -o(2*(N*M))
Space compleity - o(1)
  
