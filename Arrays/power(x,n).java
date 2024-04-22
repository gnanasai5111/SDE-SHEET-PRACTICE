Implement pow(x, n), which calculates x raised to the power n (i.e., xn).

Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000

Brute Force :

just calculate till 0 for postive numbers 
and for negative numbers 1/x^-n=x^n so we calculated x^n first to determine 1/x^n


using recursion

class Solution {
    public double myPow(double x, int n) {
        if(n==0){
            return 1;
        }
        if(n<0){
            return 1/myPow(x,-n);                    
        }
        return x*myPow(x,n-1);        
    }
}

Time complexity - o(N)
Space complexity - o(N)


using interative approach :

class Solution {
    public double myPow(double x, int n) {
        long N=n<0?-n:n;
        double ans=1;
        while(N>0){
            ans=ans*x;
            N--;
        }     
        return n<0?1.0/ans:ans;
    }
}

Time complexity - o(N)
Space complexity - o(1)

Optmised approach :

Intuition for this be like 

  if power is even 2^10 =(2*2)^5=(4)^5
  if power is odd 4^5=4*4^4

class Solution {
    public double myPow(double x, int n) {      
        long N=n;
        if(N<0){
            N=-1*N;
        }
        double ans=1.0;
        while(N>0){
           if(N%2==1){
            ans=ans*x;
            N=N-1;
           }
           else{
            x=x*x;
            N=N/2;
            
           }
        }     
         if (n < 0) ans = (double)(1.0) / (double)(ans);
         return ans;
    }
}

Time complexity - o(logN)
Space complexity - o(1)
