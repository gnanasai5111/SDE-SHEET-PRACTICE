Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.

Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).


Brute Force :

Traverse through Linked List and add elements to set.If element repeats it has cycle

public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set=new HashSet<>();
        ListNode curr=head;
        while(curr!=null){
            if(set.contains(curr)){
                return true;
            }
            set.add(curr);
            curr=curr.next;
        }
        return false;
        
    }
}

Time complexity - o(N)
Space complexity - o(N)


Optimised approach :
Floyd's Cycle Finding Algorithm or Tortoise and Hare Algorithm

In a linked list with a loop, consider two pointers: 
one that moves one node at a time (slow) and another that moves two nodes at a time (fast). 
If we start moving these pointers with their defined speed they will surely enter the loop and 
might be at some distance 'd' from each other within the loop.

The key insight here is the relative speed between these pointers. 
The fast pointer, moving at double the speed of the slow one, closes the gap between them by one node in every iteration.
This means that with each step, the distance decreases by one node.

Imagine a race where one runner moves at twice the speed of another.
The faster runner covers the ground faster and closes the gap, resulting in a reduction in the distance between them. 
Similarly, the fast pointer catches up to the slow pointer in the looped linked list,closing in the gap between them until it reaches zero.

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                return true;
            }
        }
        return false;    
    }
}

Time complexity - o(N)
Space complexity - o(1)

 
