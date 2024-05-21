There is one meeting room in a firm. There are N meetings in the form of (start[i], end[i]) where start[i] is start time of meeting i and end[i] is finish time of meeting i.
What is the maximum number of meetings that can be accommodated in the meeting room when only one meeting can be held in the meeting room at a particular time?

Note: Start time of one chosen meeting can't be equal to the end time of the other chosen meeting.

Example 1:

Input:
N = 6
start[] = {1,3,0,5,8,5}
end[] =  {2,4,6,7,9,9}
Output: 
4
Explanation:
Maximum four meetings can be held with
given start and end timings.
The meetings are - (1, 2),(3, 4), (5,7) and (8,9)


Approach :

Say if you have two meetings, one which gets over early and another which gets over late.
Which one should we choose?  If our meeting lasts longer the room stays occupied and we lose our time.
On the other hand, if we choose a meeting that finishes early we can accommodate more meetings.
Hence we should choose meetings that end early and utilize the remaining time for more meetings.

class Meeting{
    int s;
    int e;
    int index;
    public Meeting(int s,int e,int index){
        this.s=s;
        this.e=e;
        this.index=index;
    }
}
class SortbyFinishTime implements Comparator<Meeting> {
    public int compare(Meeting a, Meeting b)
    {
        return a.e - b.e;
    }
} 
class Solution 
{
    //Function to find the maximum number of meetings that can
    //be performed in a meeting room.
    public static int maxMeetings(int start[], int end[], int n)
    {
        // add your code here
        ArrayList<Meeting> a=new ArrayList<>();
        for(int i=0;i<start.length;i++){
            a.add(new Meeting(start[i],end[i],i));
        }
        Collections.sort(a, new SortbyFinishTime());
        int max=1;
        int e=a.get(0).e;
        for(int i=1;i<a.size();i++){
            if(a.get(i).s>e){
                e=a.get(i).e;
                max++;
            }
        }
        return max;
    }
}

Time complexity - O(n) +O(n log n) + O(n) ~O(n log n)
Space compelxity - o(N)
