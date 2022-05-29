public class CycleInLinkedList_142 {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode tortoise = head, hare = head;
        do {
            tortoise = tortoise.next;
            hare = hare.next.next;
        } while (hare != null && hare.next != null // if acyclic, hare would reach null first
                && hare != tortoise); // intersects: found cycle
        if (hare != tortoise) return null;
        while (head != tortoise) { // both traverse non-cyclical length to start of cycle
            head = head.next;
            tortoise = tortoise.next;
        }
        return tortoise;
    }
}
