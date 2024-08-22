Given a number of stairs and a frog, the frog wants to climb from the 0th stair to the (N-1)th stair.
At a time the frog can climb either one or two steps.
A height[N] array is also given.
Whenever the frog jumps from a stair i to stair j, the energy consumed in the jump is abs(height[i]- height[j]),
where abs() means the absolute difference. We need to return the minimum energy that can be used by the frog to jump
from stair 0 to stair N-1.

Sample Input 1:
4
10 20 30 10
Sample Output 1:
20
Explanation of sample input 1:
For the first test case,
The frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost).
Then a jump from the 2nd stair to the last stair (|10-20| = 10 energy lost).
So, the total energy lost is 20 which is the minimum. 
Hence, the answer is 20.

First approach : (Recursion)

  public class Solution {
    public static int minEnergy(int index,int heights[]){
        if(index==heights.length-1){
            return 0;
        }
        int firstJump=minEnergy(index+1,heights)+Math.abs(heights[index]-heights[index+1]);
        int secondJump=Integer.MAX_VALUE;
        if(index<heights.length-2){
            secondJump=minEnergy(index+2,heights)+Math.abs(heights[index]-heights[index+2]);
        }
        return Math.min(firstJump,secondJump);
        
    }
    public static int frogJump(int n, int heights[]) {
        return minEnergy(0,heights);
    }

}

Time complexity - o(N)
Space complexity - o(N)

Second approach (Memoization)

public class Solution {
    public static int minEnergy(int index,int heights[],int memoization[]){
        if(index==heights.length-1){
            return 0;
        }
        if(memoization[index]!=0){
            return memoization[index];
        }
        int firstJump=minEnergy(index+1,heights,memoization)+Math.abs(heights[index]-heights[index+1]);
        int secondJump=Integer.MAX_VALUE;
        if(index<heights.length-2){
            secondJump=minEnergy(index+2,heights,memoization)+Math.abs(heights[index]-heights[index+2]);
        }
        return memoization[index]=Math.min(firstJump,secondJump);
        
    }
    public static int frogJump(int n, int heights[]) {
        int memoization[]=new int[n+1];
        return minEnergy(0,heights,memoization);
    }

}

Time complexity - o(N)
Space complexity - o(N)

Third approach (Tabulation)


public class Solution {
          int dp[]=new int[n];
          Arrays.fill(dp,-1);
          dp[0]=0;
          for(int ind=1;ind<n;ind++){
              int jumpTwo = Integer.MAX_VALUE;
                int jumpOne= dp[ind-1] + Math.abs(heights[ind]-heights[ind-1]);
                if(ind>1)
                    jumpTwo = dp[ind-2] + Math.abs(heights[ind]-heights[ind-2]);
            
                dp[ind]=Math.min(jumpOne, jumpTwo);
          }
          return dp[n-1];
    }

}

Time complexity - o(N)
Space complexity - o(N)

Fourth approach (space optimised)


public class Solution {
    public static int frogJump(int n, int heights[]) {
       int prev=0;
       int prev2=0;
      for(int i=1;i<n;i++){    
          int jumpTwo = Integer.MAX_VALUE;
          int jumpOne= prev + Math.abs(heights[i]-heights[i-1]);
          if(i>1)
            jumpTwo = prev2 + Math.abs(heights[i]-heights[i-2]);
        
          int cur_i=Math.min(jumpOne, jumpTwo);
          prev2=prev;
          prev=cur_i;
            
      }
      return prev;
    }

}

Time complexity - o(N)
Space complexity - o(1)

