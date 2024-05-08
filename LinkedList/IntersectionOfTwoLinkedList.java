Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:

The test cases are generated such that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns.

Custom Judge:

The inputs to the judge are given as follows (your program is not given these inputs):

intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
listA - The first linked list.
listB - The second linked list.
skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. 
If you correctly return the intersected node, then your solution will be accepted.

Example 1:


Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5].
There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are
different node references. In other words, they point to two different locations in memory, 
while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.


Brute Force approach :

Traverse through First linked List.
For each element in the first linked list traverse the second linked list and check if both addresses are equal.
If equal return element else return null

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curr1=headA;
        while(curr1!=null){
            ListNode curr2=headB;
            while(curr2!=null){
                if(curr1==curr2){
                    return curr1;
                }
                curr2=curr2.next;
            }
            
            curr1=curr1.next;
        }
        return null;        
    }
}

Time complexity - o(N^M)
Space complexity - o(1)

Better approach :

Traverse the first linkedlist and store all elements i  set.
Next,Traverse the second linkedlist and check if element exists in set or not .
If exists return the element else return null

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set=new HashSet<>();
        ListNode curr1=headA;
        while(curr1!=null){
            set.add(curr1);
            curr1=curr1.next;
        }
        ListNode curr2=headB;
        while(curr2!=null){
            if(set.contains(curr2)){
                return curr2;
            }
            curr2=curr2.next;
        }  
        return null;    
    }
}

Time complexity - o(M+N)
Space complexity - o(M)

Optimised approach :

We will reduce the search length. This can be done by searching the length of the shorter linked list. How? Let’s see the process.

Find the length of both lists.
Find the positive difference between these lengths.
Move the dummy pointer of the larger list by the difference achieved. This makes our search length reduced to a smaller list length.
Move both pointers, each pointing two lists, ahead simultaneously if both do not collide.

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int length1=0;
        int length2=0;
        ListNode curr1=headA;
        while(curr1!=null){
            length1++;
            curr1=curr1.next;
        }
        ListNode curr2=headB;
        while(curr2!=null){
            length2++;
            curr2=curr2.next;
        }  
        int diff=length1-length2;
        curr1=headA;
        curr2=headB;
        if(diff<0){
            while(diff<0){
                diff++;
                curr2=curr2.next;
            }
        }
        else{
            while(diff>0){
                diff--;
                curr1=curr1.next;
            }
        }
        while(curr1!=null && curr2!=null){
            if(curr1==curr2){
                return curr1;
            }
            curr1=curr1.next;
            curr2=curr2.next;
        } 
        return null;
    }
}

Time complexity - O(2max(M,N))+O(abs(M-N))+O(min(M,N))
Space complexity - o(1)


Optimised approach :

The difference of length method requires various steps to work on it.
Using the same concept of difference of length, a different approach can be implemented. The process is as follows:-

Take two dummy nodes for each list. Point each to the head of the lists.
Iterate over them. If anyone becomes null, point them to the head of the opposite lists and continue iterating until they collide.

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curr1=headA;
        ListNode curr2=headB;
        while(curr1!=curr2){
            curr1 = curr1 == null ? headB : curr1.next;
            curr2 = curr2 == null ? headA : curr2.next;
        }
        return curr1;
    }
}

Time complexity - O(2*max(M,N))
Space complexity - o(1)
