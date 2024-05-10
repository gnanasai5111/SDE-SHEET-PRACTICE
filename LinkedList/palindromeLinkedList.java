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


Optimised approach :

Using Fast and slow pointer find the mid element,and get the reverse of the half from that mid.
Now keep two pointers one at the start of head and other at reverse of the half from mid.
Now compare elements and check if they are equal or not

class Solution {
    public ListNode reverse(ListNode node){
        ListNode curr=node;
        ListNode prev=null;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
    public boolean isPalindrome(ListNode head) {
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode p1=head;
        ListNode p2=reverse(slow);
        while(p1!=null && p2!=null){
            if(p1.val!=p2.val){
                return false;
            }
            p1=p1.next;
            p2=p2.next;
        }
        return true;
    }
}

Time complexity - o(N)
Space complexity - o(1)
