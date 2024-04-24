Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:


Dynamic programming :

Pascal's triangle can be classified as dynamic programming because we
construct each row based on the previous row.

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> prev=new ArrayList<>();
        for(int i=1;i<=numRows;i++){
            List<Integer> inner=new ArrayList<>();
            for(int j=1;j<=i;j++){
                if(j==1 || j==i){
                    inner.add(1);
                }
                else{
                    inner.add(prev.get(j-2)+prev.get(j-1));
                }
            }
            res.add(inner);
            prev=inner;
        }
        return res;
    }
}

Time complexity - o(N^2)
Space complexity - o(N)


Optimised approach :

Here we use a formula based apprach ncr=n!/(n-r)!r!
we optimse this as suppose for expample 5c2=5*4*3*2*1/3*2*1*2*1=5*4/2*1
so basically we figured a formula to multiply only till r didgits 
so we generate each row based on the formula

class Solution {
    public List<Integer> generateRow(int row){
        int ans=1;
        List<Integer> inner=new ArrayList<>();
        inner.add(1);
        for(int col=1;col<row;col++){
            ans=(ans*(row-col))/col;
            inner.add(ans);
        }
        return inner;
    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res=new ArrayList<>();
        
        for(int row=1;row<=numRows;row++){
           res.add(generateRow(row));
        }
        return res;
    }
}

Time complexity - o(N^2)
Space complexity - o(1)
