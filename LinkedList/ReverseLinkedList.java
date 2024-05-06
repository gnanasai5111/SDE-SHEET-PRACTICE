Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Brute Force :

A straightforward approach to reversing a singly linked list requires an additional data structure to temporarily store the values. 
We can use a stack for this. By pushing each node onto the stack as we move through the list, we effectively reverse the order of the nodes.
Once all the nodes are stored in the stack, we rebuild the reversed linked list by popping nodes from the stack and assigning them to the nodes.
The result is a new linked list with the elements in the opposite order of the original list.

class Solution {
    public ListNode reverseList(ListNode head) {
        Stack<Integer> s=new Stack<>();
        ListNode curr=head;
        while(curr!=null){
            s.push(curr.val);
            curr=curr.next;
        }
        curr=head;
        while(curr!=null){
            curr.val=s.pop();
            curr=curr.next;
        }
        return head;
        
        
    }
}

Time complexity - o(N)
Space complexity - o(N)

Optimised appproach :(Iterative)

The main idea is to flip the order of connections in the linked list, which changes the direction of the arrows. When this happens, 
the last element becomes the new first element of the list. 
This in-place reversal allows us to efficiently transform the original list without using extra space.

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode curr=head;
        ListNode prev=null;
        while(curr!=null){
            ListNode temp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=temp;
        }
        return prev;
        
    }
}


Time complexity - o(N)
Space complexity - o(1)

Optimised approach :(Recursion)
The core of the algorithm lies in implementing a recursive function responsible for reversing the linked list. 
This function operates based on the following principle:

If the base case conditions are not met, the function invokes itself recursively.
This recursion continues until it reaches the base case, gradually reversing the linked list starting from the second node (node after it) onward.
Following the recursion, the function returns the new head of the reversed linked list. 
This head marks the last node of the original list before reversal, now the first node in the reversed sequence.

class Solution {
    public ListNode reverseList(ListNode head) {
       if(head==null || head.next==null){
        return head;
       }
       ListNode newHead=reverseList(head.next);
       ListNode front=head.next;
       front.next=head;
       head.next=null;
       return newHead;
         
    }
}

Time complexity - o(N)
Space complexity - o(N)
