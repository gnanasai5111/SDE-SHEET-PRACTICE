The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen 
and an empty space, respectively.

Example 1:

Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above



Brute force :

 Using the concept of Backtracking, we will place Queen at different positions of the chessboard and find the right arrangement
where all the n queens can be placed on the n*n grid.

class Solution {
    public boolean isValid(int r,int c,char board[][]){
        int dr=r;
        int dc=c;
        while(dr>=0 && dc>=0){
            if(board[dr][dc]=='Q'){
                return false;
            }
            dr--;
            dc--;
        }
        dr=r;
        dc=c;
        while(dc>=0){
            if(board[dr][dc]=='Q'){
                return false;
            }
            dc--;
        }
        dr=r;
        dc=c;
        while(dr<board.length && dc>=0 ){
            if(board[dr][dc]=='Q'){
                return false;
            }
            dr++;
            dc--;
        }
        return true;
    }
    public List<String> constructBoard(char board[][]){
        List<String> each=new ArrayList<>();
        for(int i=0;i<board.length;i++){
            String s = new String(board[i]);
            each.add(s);
        }
        return each;
    }
    public void dfs(int col,char board[][],List<List<String>> res){
        if(col==board.length){
            res.add(constructBoard(board));
            return;
        }
        for(int row=0;row<board.length;row++){
            if(isValid(row,col,board)){
                board[row][col]='Q';
                dfs(col+1,board,res);
                board[row][col]='.';
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        char[][] board=new char[n][n];
         for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                board[i][j] = '.';
            }
         }
        List<List<String>> res=new ArrayList<>();
        dfs(0,board,res);
        return res;    
    }
}

Time complexity - O(N! * N). (exponential)
Space complexity - o(N^2)
