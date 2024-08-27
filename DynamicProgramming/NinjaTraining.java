Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities.
(Running, Fighting Practice or Learning New Moves).
Each activity has some merit points on each day. As Ninja has to improve all his skills,
he can’t do the same activity in two consecutive days. Can you help Ninja find out the maximum merit points Ninja can earn?

You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity. 
Your task is to calculate the maximum number of merit points that Ninja can earn.

For Example
If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.

Sample Input 1:
2
3
1 2 5 
3 1 1
3 3 3
3
10 40 70
20 50 80
30 60 90
Sample Output 1:
11
210
Explanation of sample input 1:
For the first test case,
One of the answers can be:
On the first day, Ninja will learn new moves and earn 5 merit points. 
On the second day, Ninja will do running and earn 3 merit points. 
On the third day, Ninja will do fighting and earn 3 merit points. 
The total merit point is 11 which is the maximum. 
Hence, the answer is 11.

For the second test case:
One of the answers can be:
On the first day, Ninja will learn new moves and earn 70 merit points. 
On the second day, Ninja will do fighting and earn 50 merit points. 
On the third day, Ninja will learn new moves and earn 90 merit points. 
The total merit point is 210 which is the maximum. 
Hence, the answer is 210.

First approach - recursion

public class Solution {
    public static int calculateMaxMerit(int lastTask, int day, int[][] points) {
        if (day >= points.length) {
            return 0;
        }
        int maxMerit = 0;
        for (int task = 0; task < points[day].length; task++) {
            if (task != lastTask) {
                maxMerit = Math.max(maxMerit, points[day][task] + calculateMaxMerit(task, day + 1, points));
            }
        }
        return maxMerit;
    }
    public static int ninjaTraining(int n, int points[][]) {
        return calculateMaxMerit(-1,0,points);
    }

}

Time complexity - O(N*3*3)
Space complexity - O(N) + O(N*3)

Second approach - Memoization

public class Solution {
    public static int calculateMaxMerit(int lastTask, int day, int[][] points,int m[][]) {
        if (day >= points.length) {
            return 0;
        }
        if(lastTask != -1 && m[day][lastTask]!=0){
            return m[day][lastTask];
        }
        int maxMerit = 0;
        for (int task = 0; task < points[day].length; task++) {
            if (task != lastTask) {
                maxMerit = Math.max(maxMerit, points[day][task] + calculateMaxMerit(task, day + 1, points,m));
            }
        }
        if (lastTask != -1) {
            m[day][lastTask] = maxMerit;
        }
        return maxMerit;
    }
    public static int ninjaTraining(int n, int points[][]) {
        int m[][]=new int[points.length][3];
        return calculateMaxMerit(-1,0,points,m);
    }
}

Time complexity - O(N*3*3)
Space complexity - O(N) + O(N*3)


Approach 3 :

import java.util.*;
public class Solution {
    public static int ninjaTraining(int n, int points[][]) {
       int[][] dp = new int[n][3];
        dp[0][0] = Math.max(points[0][1],points[0][2]);
        dp[0][1] = Math.max(points[0][0],points[0][2]);
        dp[0][2] = Math.max(points[0][0],points[0][1]);
        
        // Fill the dp array iteratively
        for (int day = 1; day < n; day++) {
            for(int last=0;last<3;last++){
                for(int task=0;task<3;task++){
                    if(task!=last){
                        dp[day][last]=Math.max(dp[day][last],dp[day-1][task]+points[day][task]);
                    }
                }
            }
        }
        
        return Math.max(dp[n - 1][0], Math.max(dp[n - 1][1], dp[n - 1][2]));
    }
}

Time complexity - O(N*3*3)
Space complexity -  O(N*3)

Fourth approach - space optimised 

import java.util.*;
public class Solution {
    public static int ninjaTraining(int n, int points[][]) {
       int prev[] = new int[3];
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        
        // Fill the dp array iteratively
        for (int day = 1; day < n; day++) {
            int[] temp = new int[3];
            for(int last=0;last<3;last++){
                for(int task=0;task<3;task++){
                    if(task!=last){
                        temp[last]=Math.max(temp[last],prev[task]+points[day][task]);
                    }
                }
            }
            prev = temp;
        }
        
        return Math.max(prev[0], Math.max(prev[1], prev[2]));
    }
}

Time complexity - O(N*3*3)
Space complexity -  O(3)
