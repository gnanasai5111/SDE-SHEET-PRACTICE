Given the head of a linked list, rotate the list to the right by k places.

Example 1:

Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

Brute Force :

Intuition is for every k value, get the last element of linked list and attach it to head. Reapeat this process till k gets 0.

class Solution {
    public ListNode rotate(ListNode head){
        ListNode curr=head;
        ListNode prev=null;
        while(curr.next!=null){
            prev=curr;
            curr=curr.next;
        }
        curr.next=head;
        head=curr;
        if(prev!=null){
            prev.next=null;
        }
        return head;     
    }
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode ans=head;
        while(k>0){
            ans=rotate(ans);
            k--;
        }
        return ans;     
    }
}

Time complexity - o(N*K)
Space complexity - o(1)

Optimised approach :

class Solution {
    public ListNode rotate(ListNode head){
        ListNode curr=head;
        ListNode prev=null;
        while(curr.next!=null){
            prev=curr;
            curr=curr.next;
        }
        curr.next=head;
        head=curr;
        if(prev!=null){
            prev.next=null;
        }
        return head;     
    }
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode curr=head;
        int length=0;
        while(curr!=null){
            length++;
            curr=curr.next;
        }
        ListNode ans=head;
        int c=k%length;   
        while(c>0){
            ans=rotate(ans);
            c--;
        }
        return ans;     
    }
}

Time complexity - o(N*(k%length))- 0(N^2)
Space complexity - o(1)


Optimised approach :

Calculate the length of the list.
Connect the last node to the first node, converting it to a circular linked list.
Iterate to cut the link of the last node and start a node of k%length of the list rotated list.

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode curr=head;
        int length=1;
        while(curr.next!=null){
            length++;
            curr=curr.next;
        }
        curr.next=head;
        int diff=length-k%length;
        while(diff>0){
            curr=curr.next;
            diff--;
        }
        head=curr.next;
        curr.next=null;
        return head;
    }
}

Time complexity -  O(N) + O(N - (N%k))
Space complexity - o(1)
