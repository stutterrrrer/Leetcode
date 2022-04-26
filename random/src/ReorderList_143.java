public class ReorderList_143 {
    public void reorderList(ListNode head) {
        int n = 0;
        for (ListNode node = head; node != null; node = node.next) n++;
        if (n < 2) return;

        ListNode endOf1st = head;
        for (int i = 0; i < (n + 1) / 2 - 1; i++) endOf1st = endOf1st.next;
        ListNode headOf2nd = endOf1st.next;
        endOf1st.next = null; // sever the 2 lists

        headOf2nd = reverseList(headOf2nd);
        mergeListsZigZag(head, headOf2nd);
    }

    private void mergeListsZigZag(ListNode h1, ListNode h2) {
        // at the end, h1 is the complete list.
        for (ListNode traversal = h1; h2 != null;) {
            ListNode origNext = traversal.next;
            // zig-zag
            traversal.next = h2;
            // update variables
            traversal = h2;
            h2 = origNext;
        }
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode origNext = head.next;
            // reverse the pointer
            head.next = pre;
            // update variables:
            pre = head;
            head = origNext;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        ReorderList_143 solver = new ReorderList_143();
        solver.reorderList(head);
        System.out.println(head.entireList());
    }
}
