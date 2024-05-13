Given a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
(i) a next pointer to the next node,
(ii) a bottom pointer to a linked list where this node is head.
Each of the sub-linked-list is in sorted order.
Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order. 

Note: The flattened list will be printed using the bottom pointer instead of the next pointer.
For more clarity have a look at the printList() function in the driver code.

Brute Force 

Traverse through the linkedList and for each item also traverse bottom till the end and store all elements in a list
Sort the list. Traverse the list and convert to linked list

Example 1:

Input:
5 -> 10 -> 19 -> 28
|     |     |     | 
7     20    22   35
|           |     | 
8          50    40
|                 | 
30               45
Output:  5-> 7-> 8- > 10 -> 19-> 20->
22-> 28-> 30-> 35-> 40-> 45-> 50.
Explanation:
The resultant linked lists has every 
node in a single level.
(Note: | represents the bottom pointer.)

class GfG
{
    Node flatten(Node root)
    {
	// Your code here
	Node curr=root;
	ArrayList<Integer> a=new ArrayList<>();
	while(curr!=null){
	    Node currDown=curr;
	    while(currDown!=null){
	        a.add(currDown.data);
	        currDown=currDown.bottom;
	        
	    }
	    curr=curr.next;
	}
	Collections.sort(a);
	curr=new Node(a.get(0));
	Node head=curr;
	for(int i=1;i<a.size();i++){
	    Node node=new Node(a.get(i));
	   curr.bottom=node;
	   curr=node;
	}
	return head;
    }
}
Time complexity - o((m+n)log(m+n))+o(2(m+n))
Space complexity - o(m+n)


Optimised approach :

Since each list, followed by the bottom pointer, are in sorted order. 
Our main aim is to make a single list in sorted order of all nodes. So, we can think of a merge algorithm of merge sort.
The process to flatten the given linked list is as follows:-

We will recurse until the head pointer moves null. The main motive is to merge each list from the last.
Merge each list chosen using the merge algorithm. The steps are
Create a dummy node. Point two pointers, i.e, temp and res on dummy node. res is to keep track of dummy node and temp pointer
is to move ahead as we build the flattened list.
We iterate through the two chosen. Move head from any of the chosen lists ahead whose current pointed node is smaller. 
Return the new flattened list found.

class GfG
{
    public Node merge(Node p1,Node p2){
        Node temp=new Node(0);
        Node root=temp;

        while(p1!=null && p2!=null){
            if(p1.data>p2.data){
                temp.bottom=p2;
                temp=p2;
                p2=p2.bottom;
            }
            else{
                temp.bottom=p1;
                temp=p1;
                p1=p1.bottom;
            }
        }
        if(p1!=null){
            temp.bottom=p1;
        }
        else{
            temp.bottom=p2;
        }
        return root.bottom;
        
    }
    Node flatten(Node root)
    {
	if(root==null || root.next==null){
	    return root;
	}
	root.next=flatten(root.next);
	root=merge(root,root.next);
	return root;
    }
}

Time complexity - o(N)
Space complexity - o(1)
