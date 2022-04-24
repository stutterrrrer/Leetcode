public class ListNode {
	public int val;
	public ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	public static ListNode createList(int[] vals) {
		ListNode dummyHead = new ListNode();
		ListNode traversal = dummyHead;
		for (int val : vals) {
			traversal.next = new ListNode();
			traversal = traversal.next;
			traversal.val = val;
		}
		return dummyHead.next;
	}

	public String entireList() {
		StringBuilder list = new StringBuilder();
		// detects self-referencing, but not loops
		for (ListNode traversal = this; traversal != null && traversal.next != traversal; traversal = traversal.next) {
			list.append(traversal.val).append(" -> ");
		}
		list.append("null");
		return list.toString();
	}
}
