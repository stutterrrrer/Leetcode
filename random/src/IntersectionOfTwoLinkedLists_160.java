import java.util.HashSet;

public class IntersectionOfTwoLinkedLists_160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // add all nodes in one list to sets instead of traversing both and adding both lists - less space
        HashSet<ListNode> nodesInA = new HashSet<>();
        for (ListNode cur = headA; cur != null; cur = cur.next) nodesInA.add(cur);
        for (ListNode cur = headB; cur != null; cur = cur.next) if (nodesInA.contains(cur)) return cur;
        return null;
    }

    public ListNode getIntersectionNodeConstantSpace(ListNode headA, ListNode headB) {
        int a = 0, b = 0;
        for (ListNode curA = headA; curA != null; curA = curA.next) a++;
        for (ListNode curB = headB; curB != null; curB = curB.next) b++;
        ListNode shorterListHead = a < b ? headA : headB;
        ListNode longerListPtr = shorterListHead == headA ? headB : headA;

        for (int i = 0; i < Math.abs(a - b); i++) longerListPtr = longerListPtr.next;
        for (ListNode x = longerListPtr, y = shorterListHead; x != null; x = x.next, y = y.next)
            if (x == y) return x;
        return null;
    }
}
