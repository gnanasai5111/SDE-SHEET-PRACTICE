Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
You must do it in place.
  
Example 1:

Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Brute Force :

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
  
