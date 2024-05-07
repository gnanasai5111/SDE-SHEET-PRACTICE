You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

 Example 1:


Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]


BruteForce :

Copy Two linkedLists into a arrayList. And then use merge Algorithm and make a new linkedList

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ArrayList<Integer> a=new ArrayList<>();
        ArrayList<Integer> b=new ArrayList<>();
        ListNode curr=list1;
        while(curr!=null){
            a.add(curr.val);
            curr=curr.next;
        }
        curr=list2;
        while(curr!=null){
            b.add(curr.val);
            curr=curr.next;
        }
        int i=0;
        int j=0;
        ListNode head=null;
        curr=null;
        while(i<a.size() && j<b.size()){
            if(a.get(i)<b.get(j)){
                ListNode temp=new ListNode(a.get(i));
                if(curr==null){
                    head=temp;
                    curr=temp;
                }
                else{
                    curr.next=temp;
                    curr=temp;
                }
                i++;
            }
            else{
                ListNode temp=new ListNode(b.get(j));
                if(curr==null){
                    head=temp;
                    curr=temp;
                }
                else{
                    curr.next=temp;
                    curr=temp;
                }
                j++;
            }
        }
        while(i<a.size()){
            ListNode temp=new ListNode(a.get(i));
                if(curr==null){
                    head=temp;
                    curr=temp;
                }
                else{
                    curr.next=temp;
                    curr=temp;
                }
                i++;
        }
        while(j<b.size()){
            ListNode temp=new ListNode(b.get(j));
                if(curr==null){
                    head=temp;
                    curr=temp;
                }
                else{
                    curr.next=temp;
                    curr=temp;
                }
                j++;
        }
        return head;
        
    }
}

Time complexity - o(2(M+N))
Space complexity - o(M+N)

Optmised approach :

Same as first approach we use merge algorithm but we dont use extra space .Simply iterate through linked list and merge the list

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode curr1=list1;
        ListNode curr2=list2;
        ListNode head=null;
        ListNode curr=null;
        while(curr1!=null && curr2!=null){
            if(curr1.val<curr2.val){
                if(curr==null){
                    head=curr1;
                    curr=curr1;
                }
                else{
                    curr.next=curr1;
                    curr=curr1;
                }
                curr1=curr1.next;
            }
            else{
                if(curr==null){
                    head=curr2;
                    curr=curr2;
                }
                else{
                    curr.next=curr2;
                    curr=curr2;
                }
                curr2=curr2.next;
            }
        }
        while(curr1!=null){
            if(curr==null){
                head=curr1;
                curr=curr1;
            }
            else{
                curr.next=curr1;
                curr=curr1;
            }
            curr1=curr1.next;
        }
        while(curr2!=null){
            if(curr==null){
                head=curr2;
                curr=curr2;
            }
            else{
                curr.next=curr2;
                curr=curr2;
            }
            curr2=curr2.next;
        }
        return head;
    }
}


Time complexity - o(M+N)
Space complexity - o(1)
