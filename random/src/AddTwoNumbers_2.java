public class AddTwoNumbers_2 {
    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        // use a dummy head to avoid creating an unnecessary tail node
        ListNode dummyHead = new ListNode(), curNode = dummyHead;
        while (l1 != null || l2 != null) {
            curNode.next = new ListNode();
            curNode = curNode.next;

            int x1 = l1 == null ? 0 : l1.val;
            int x2 = l2 == null ? 0 : l2.val;
            int curSum = x1 + x2 + carry;
            curNode.val = curSum % 10;
            carry = curSum / 10;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry != 0) curNode.next = new ListNode(carry);
        return dummyHead.next;
    }
}

