You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

Example 1:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Brute Force :

Traverse through all the elements in matrix and if target exists return true else return false

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]==target){
                    return true;
                }
            }
        }
        
        return false;
    }
}

Time complexity - o(M*N)
Space complexity - o(1)

Better approach :

Firstly we will check in which row the element lies , since all the rows are sorted , we just need to check whether target lies between 
start and end of each row.
Then we gonna perform binary search for that row.

class Solution {
    public boolean binarySearch(int inner[],int target){
        int left=0;
        int right=inner.length-1;
        while(left<=right){
            int mid=(left+right)/2;
            if(inner[mid]>target){
                right=mid-1;
            }
            else if(inner[mid]<target){
                left=mid+1;
            }
            else{
                return true;
            }
        }
        return false;
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        for(int i=matrix.length-1;i>=0;i--){
            if(matrix[i][0]<=target && target<=matrix[i][matrix[i].length-1]){
                if(binarySearch(matrix[i],target)){
                    return true;
                }
            }
        }
        
        return false;
    }
}

Time complexity - o(N*logM)
Space complexity - o(1)

Optimised approach :

If we flatten the given 2D matrix to a 1D array, the 1D array will also be sorted.
By utilizing binary search on this sorted 1D array to locate the 'target' element, we can further decrease the time complexity.
The flattening will be like the following:


How to apply binary search on the 1D array without actually flattening the 2D matrix:

We will use the following formula:

If index = i, and no. of columns in the matrix = m, the index i corresponds to the cell with
row = i / m and col = i % m. More formally, the cell is (i / m, i % m)(0-based indexing).

class Solution {
    public boolean binarySearch(int matrix[][],int target){
        int left=0;
        int m=matrix.length;
        int n=matrix[0].length;
        int right=m*n-1;
        while(left<=right){
            int mid=(left+right)/2;
            int row=mid/n;
            int col=mid%n;
            if(matrix[row][col]>target){
                right=mid-1;
            }
            else if(matrix[row][col]<target){
                left=mid+1;
            }
            else{
                return true;
            }
        }
        return false;
    }
    public boolean searchMatrix(int[][] matrix, int target) {  
        return binarySearch(matrix,target);
    }
}

Time complexity - o(log(N*M))
Space complexity - o(1)
