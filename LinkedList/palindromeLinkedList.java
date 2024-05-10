Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

Example 1:
  
Input: head = [1,2,2,1]
Output: true

Brute Force

Traverse the linkedlist and copy all elements to array List.
In order check if array is palindrome or not , use two pointer one at start and other at end , Compare if they are equal or not
If not equal , then return false .If equal increment first and decremnet second and repeat the process again.

class Solution {
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> a=new ArrayList<>();
        ListNode curr=head;
        while(curr!=null){
            a.add(curr.val);
            curr=curr.next;
        }
        int i=0;
        int j=a.size()-1;
        while(i<j){
            if(a.get(i)!=a.get(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
        
    }
}
Time complexity - o(N)
Space complexity - o(N)
