Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, 
and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

First sort the array based on start interval.
And Traverse the array and check if elements overlap and add it to array.

class Solution {
    public int[][] merge(int[][] intervals) {
        List<List<Integer>> res=new ArrayList<>();
         Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
            
        for(int i=0;i<intervals.length;i++){
            int start=intervals[i][0];
            int end=intervals[i][1];
            if(res.size()>0 && start<=res.get(res.size()-1).get(1)){
                continue;
            }
            for(int j=i+1;j<intervals.length;j++){
                if(intervals[j][0]<=end){
                    end=Math.max(end,intervals[j][1]);
                }
                else{
                    break;
                }
            }
            List<Integer> inner=new ArrayList<>();
            inner.add(start);
            inner.add(end);
            res.add(inner);
        }     
        int out[][]=new int[res.size()][2];
        for(int i=0;i<res.size();i++){
            out[i][0]=res.get(i).get(0);
            out[i][1]=res.get(i).get(1);
        }
        return out;
        
    }
}

Time complexity - O(N*logN) + O(2*N)
Space complexity - o(N)

Optimised approach 

Same approach as Brute force only difference is we use a single loop in traversing
If they overlap just update the start and end else make the new one as start and end and reapeat the process.

class Solution {
    public int[][] merge(int[][] intervals) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        int start=intervals[0][0];
        int end=intervals[0][1];
        res.add(Arrays.asList(start, end));
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]<=end){
                res.get(res.size()-1).set(1,Math.max(end,intervals[i][1]));
                end=Math.max(end,intervals[i][1]);
            }
            else{
                start=intervals[i][0];
                end=intervals[i][1];
                res.add(Arrays.asList(start, end));
            }
        } 
        int out[][]=new int[res.size()][2];
        for(int i=0;i<res.size();i++){
            out[i][0]=res.get(i).get(0);
            out[i][1]=res.get(i).get(1);
        }
        return out;
        
    }
}

Time complexity - O(N*logN) + O(N)
Space complexity - o(N)
