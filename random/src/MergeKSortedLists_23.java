import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLists_23 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> bench = new PriorityQueue<>(Comparator.comparingInt(i -> i.val));
        ListNode dummy = new ListNode(); // return dummy.next
        for (ListNode first : lists)
            if (first != null)
                bench.add(first); // add the smallest nodes of each list onto priority queue

        if (!bench.isEmpty())
            dummy.next = bench.remove(); // start with the minimum node
        ListNode cur = dummy.next;

        while (!bench.isEmpty()) { // if bench is empty, all nodes already in the merged sorted list
            if (cur.next != null) {
                if (cur.next.val > bench.peek().val) { // need to replace the trailing nodes of cur with smaller nodes
                    ListNode toPQ = cur.next; // hold on to cur.next, put it back onto pq; otherwise it will be lost
                    cur.next = bench.remove();
                    bench.add(toPQ); // put back onto the bench
                }
                cur = cur.next; // update variable
            } else { // cur.next == null, so get next from the non-empty bench
                cur.next = bench.remove();
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode first = ListNode.createList(new int[]{1, 4, 5});
        ListNode second = ListNode.createList(new int[]{1, 3, 4});
        ListNode third = ListNode.createList(new int[]{2, 6});
        ListNode[] lists = {first, second, third};

        ListNode sorted = new MergeKSortedLists_23().mergeKLists(lists);
        System.out.println(sorted.entireList());
    }
}
