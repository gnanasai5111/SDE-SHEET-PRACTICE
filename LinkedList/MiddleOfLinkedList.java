Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.

Example 1:


Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.

Brute Force :

Traverse through the linked list and get the length
Traverse LinkedList again till half of length to get mid element

class Solution {
    public ListNode middleNode(ListNode head) {
        int count=0;
        ListNode curr=head;
        while(curr!=null){
            curr=curr.next;
            count++;
        }
        int mid=0;
        curr=head;
        while(curr!=null){
            if(mid==count/2){
                return curr;
            }
            curr=curr.next;
            mid++;
        }
        return null;
        
    }
}

Time Complexity: O(N+N/2)
Space Complexity : O(1)


Optimised approach :

The previous method requires the traversal of the linked list twice.
To enhance efficiency, the Tortoise and Hare Algorithm is introduced as an optimization where the middle node can be found in just one traversal.
The Tortoise and Hare algorithm leverages two pointers, 'slow' and 'fast', initiated at the beginning of the linked list.
The 'slow' pointer advances one node at a time, while the 'fast' pointer moves two nodes at a time.
The Tortoise and Hare algorithm works because the fast-moving hare reaches the end of the list in exactly the same time it takes for the slow-moving tortoise to reach the middle. 
When the hare reaches the end, the tortoise is guaranteed to be at the middle of the list.


class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
}

Time Complexity: O(N)
Space Complexity : O(1)
