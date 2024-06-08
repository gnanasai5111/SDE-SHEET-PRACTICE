The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Example 1:

Input: n = 3, k = 3
Output: "213"

Brute force :

class Solution {
    public void getAllPermutations(ArrayList<String> res,ArrayList<Integer> a,int count[],int k,boolean mark[],ArrayList<Integer> sub){
        if(sub.size()==a.size()){
            count[0]++;
            if(count[0] == k){
                String c = "";
                for(Integer i : sub){
                    c = c + i;
                }
                res.add(c);
            }
            return;
        }
        for(int i=0;i<a.size();i++){
            if(!mark[i]){
                mark[i]=true;
                sub.add(a.get(i));
                getAllPermutations(res,a,count,k,mark,sub);
                mark[i]=false;
                sub.remove(sub.size()-1);
            }
            
        }
    }
    public String getPermutation(int n, int k) {
        ArrayList<String> res=new ArrayList<>();
        ArrayList<Integer> a=new ArrayList();
        int i=0;
        boolean mark[]=new boolean[n];
        while(i<n){
            a.add(i+1);
            i++;
        }
        getAllPermutations(res,a,new int[1],k,mark,new ArrayList<>());
        return res.get(0);
    }
}

Time complexity: O(N! * N)
Space complexity : o(N)

Optimised approach :

Since this is a permutation we can assume that there are four positions that need to be filled using the four numbers of the sequence.
First, we need to decide which number is to be placed at the first index. Once the number at the first index is decided we have
three more positions and three more numbers. 
Now the problem is shorter. We can repeat the technique that was used previously until all the positions are filled. 
The technique is explained below.

class Solution {
    public String getPermutation(int n, int k) {
        int fact=1;
        ArrayList<Integer> a=new ArrayList<>();
        for(int i=1;i<n;i++){
            fact=fact*i;
            a.add(i);
        }
        a.add(n);
        k=k-1;
        String ans="";
        while(true){
            ans=ans+a.get(k/fact);
            a.remove(k/fact);
            if(a.size()==0){
                break;
            }
            k=k%fact;
            fact=fact/a.size();
        }
        return ans;
        
    }
}

Time complexity: O(N^2)
Space complexity : o(N)
