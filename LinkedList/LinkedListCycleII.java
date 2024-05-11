Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. 
Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). 
It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.

Example 1:
  
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Brute Force :

Traverse the linkedlist and Use a hashset to store the nodes,if node repeats return the node

public class Solution {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set=new HashSet<>();
        ListNode curr=head;
        while(curr!=null){
            if(set.contains(curr)){
                return curr;
            }
            set.add(curr);
            curr=curr.next;
        }
        return null;
        
    }
}

Time complexity - o(N)
Space complexity - o(N)


Optimised approach :

Step 1: Initialise two pointers, `slow` and `fast`, to the head of the linked list. `slow` will advance one step at a time,
while `fast` will advance two steps at a time. These pointers will move simultaneously.

Step 2: Traverse the linked list with the `slow` and `fast` pointers. 
While traversing, repeatedly move `slow` one step and `fast` two steps at a time.

Step 3: Continue this traversal until one of the following conditions is met:
`fast` or `fast.next` reaches the end of the linked list (i.e., becomes null).
In this case, there is no loop in the linked list, and the algorithm terminates by returning null.
`fast` and `slow` pointers meet at the same node. This indicates the presence of a loop in the linked list.
  
Step 4: Reset the `slow` pointer to the head of the linked list.
Move `fast` and `slow` one step at a time until they meet again.
The point where they meet again is the starting point.

You may be curious about the proof for this algorithm, and it hinges on the idea that the point where the slow and fast pointers
converge can be leveraged to determine the starting point of the loop.

In the "tortoise and hare" algorithm for detecting loops in a linked list, when the slow pointer (tortoise) reaches the
starting point of the loop, the fast pointer (hare) is positioned at a point that is twice the distance travelled by the slow pointer.
This is because the hare moves at double the speed of the tortoise.

If slow has travelled distance L1 then fast has travelled 2 x L1. 
Now that slow and fast have entered the loop, the distance fast will have to cover to catch up to slow is the total length of 
loop minus L1. Let this distance be d.
                            Distance travelled by slow = L1
                            Distance travelled by fast = 2 * L1
                            Total length of loop = L1 + d
                        
In this configuration, the fast pointer advances toward the slow pointer with two jumps per step, while the slow pointer moves
away with one jump per step. As a result, the gap between them decreases by 1 with each step.
Given that the initial gap is d, it takes exactly d steps for them to meet.
                            Total length of loop = L1 + d
                            Distance between slow and fast= d
                        
During these d steps, the slow pointer effectively travels d steps from the starting point within the loop and 
fast travels 2 x d and they meet a specific point. Based on our previous calculations, the total length of the loop is L1 + d. 
And since the distance covered by the slow pointer within the loop is d, the remaining distance within the loop is equal to L1.

Therefore, it is proven that the distance between the starting point of the loop and the point where the two pointers
meet is indeed equal to the distance between the starting point and head of the linked list.

public class Solution {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set=new HashSet<>();
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
           slow=slow.next;
           fast=fast.next.next;
           if(slow==fast){
            slow=head;
            while(slow!=fast){
                slow=slow.next;
                fast=fast.next;
            }
            return slow;
           }
        }
        return null;
        
    }
}

Time complexity - o(N)
Space complexity - o(1)
