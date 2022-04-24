public class RemoveDuplicatesSortedList2_82 {
    public ListNode deleteDuplicates(ListNode head) {
        // since the constraint states all nodes' val are > -101.
        ListNode dummyHead = new ListNode(-101, head);
        ListNode prevDistinct = dummyHead;

        for (ListNode prev = dummyHead, cur = head; cur != null; ) {
            ListNode next = cur.next;
            int nextVal = next == null ? cur.val - 1 : next.val;
            if (cur.val != prev.val && cur.val != nextVal) {
                prevDistinct.next = cur;
                prevDistinct = prevDistinct.next;
            } else prevDistinct.next = null;
            prev = cur;
            cur = next;
        }

        return dummyHead.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode prevDistinct = dummyHead;

        // treat (consecutive duplicates) or (1 unique value) as 1 sub-list
        // while there is a next sub-list:
        while (head != null) {
            // if this sublist contains duplicates: move to end of this sub-list
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val)
                    head = head.next;
                // prevDistinct.next is either null (end of result) or start of new sub-list
                prevDistinct.next = head.next;
            } else { // otherwise, this sub-list has only 1 distinct number (no duplicates)
                prevDistinct = prevDistinct.next;
            }

            // move to next sublist
            head = head.next;
        }
        return dummyHead.next;

    }

    public static void main(String[] args) {
        ListNode head = ListNode.createList(new int[]{1, 2, 3, 3, 4, 4, 5});
        RemoveDuplicatesSortedList2_82 solver = new RemoveDuplicatesSortedList2_82();
        System.out.println(solver.deleteDuplicates2(head).entireList());
    }
}
