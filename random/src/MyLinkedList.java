class MyLinkedList {

    class MyListNode {
        private int val;
        private MyListNode next;

        private MyListNode(int val, MyListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private int length;
    private MyListNode head;

    public MyLinkedList() {
        length = 0;
        head = null;
    }

    public int get(int index) {
        if (index < 0 || index >= length) return -1;
        MyListNode traversal = getNodeAt(index);
        return traversal.val;
    }

    private MyListNode getNodeAt(int index) {
        // index must be valid.
        MyListNode traversal = head;
        for (int i = 0; i < index; i++) traversal = traversal.next;
        return traversal;
    }


    public void addAtHead(int val) {
        head = new MyListNode(val, head);
        length++;
    }

    public void addAtTail(int val) {
        addAtIndex(length, val);
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > length) return;
        if (index == 0) addAtHead(val);
        else {
            MyListNode nodeBefore = getNodeAt(index - 1);
            nodeBefore.next = new MyListNode(val, nodeBefore.next);
            length++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= length) return;
        if (index == 0) head = head.next;
        else {
            MyListNode nodeBefore = getNodeAt(index - 1);
            nodeBefore.next = nodeBefore.next.next;
        }
        length--;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtTail(1);
        list.addAtTail(3);
        System.out.println(list.get(1));
    }
}
