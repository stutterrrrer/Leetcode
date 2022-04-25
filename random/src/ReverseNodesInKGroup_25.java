public class ReverseNodesInKGroup_25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        for (ListNode node = head; node != null; node = node.next) n++;

        ListNode dummyHead = new ListNode(0, head);
        ListNode endOfPrevK = dummyHead;
        for (int remain = n; remain >= k; remain -= k) {
            // this loop has (n / k) iterations
            ListNode startOfCurK = endOfPrevK.next;
            ListNode cur = startOfCurK.next; // start from second element of current k-group
            ListNode pre = startOfCurK;

            for (int i = 0; i < k - 1; i++) {
                // flip each cur.next to point to original previous node
                ListNode origNext = cur.next;
                cur.next = pre;
                // update variables for next iteration
                pre = cur;
                cur = origNext;
            }

            endOfPrevK.next = pre;
            startOfCurK.next = cur;
            endOfPrevK = startOfCurK;
        }
        return dummyHead.next;
    }

	public static void main(String[] args) {
		ListNode head = ListNode.createList(new int[]{1, 2, 3, 4, 5});
		int k = 3;
		ReverseNodesInKGroup_25 solver = new ReverseNodesInKGroup_25();
		System.out.println(solver.reverseKGroup(head, k).entireList());
	}
}
