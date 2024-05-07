

Brute Force :

Find the Total length.And interate till last nth node i.e,(totallength-n+1) and also keep track of prev node.
So when you reach that index you can do prev.next=curr.next 
But If you want to delete the first element then you can simply return curr.next;

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length=0;
        ListNode curr=head;
        while(curr!=null){
            length++;
            curr=curr.next;
        }
        int index=length-n+1;
        int count=0;
        curr=head;
        ListNode prev=null;
        while(curr!=null){
            count++;
            if(index==count){
                if(prev!=null){
                    prev.next=curr.next;
                }
                else{
                    return curr.next;
                }
                curr=curr.next;
                break;
            }
            prev=curr;
            curr=curr.next;
        }
        return head;
    }
}

Time complexity - o(2N)
Space complexity - o(1)

Optimised approach :

To enhance efficiency, we will involve two pointers, a fast pointer and a slow pointer. 
The fast-moving pointer will initially be exactly N nodes ahead of the slow-moving pointer.
After which, both of them will move one step at a time. 
When the fast pointer reaches the last node, i.e., the L-th node, the slow is guaranteed to be at the (L-N)-th node, 
where L is the total length of the linked list.

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow=head;
        ListNode fast=head;
       for(int i=0;i<n;i++){
            fast=fast.next;
       }
       ListNode prev=null;
       while(fast!=null){
            fast=fast.next;
            prev=slow;
            slow=slow.next;
       }
       if(prev==null){
            return head.next;
       }
       else{
            prev.next=slow.next;
            return head;
       }

    }
}

Time complexity - o(N)
Space complexity - o(1)
