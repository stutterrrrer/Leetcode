public class SwapNodesInPairs_24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        for (ListNode before = dummy, x = head; x != null;) {
            ListNode y = x.next;
            if (y == null) break;
            x.next = y.next;
            y.next = x;
            before.next = y;

            before = x;
            x = x.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode input = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        SwapNodesInPairs_24 solver = new SwapNodesInPairs_24();
        System.out.println(solver.swapPairs(input).entireList());
    }
}
