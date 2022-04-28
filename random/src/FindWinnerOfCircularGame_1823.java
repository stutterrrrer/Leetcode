public class FindWinnerOfCircularGame_1823 {
    private class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public int findTheWinner(int n, int k) {
        Node first = new Node(1);
        Node beforeFirst = first;
        for (int i = 2; i <= n; i++) {
            beforeFirst.next = new Node(i);
            beforeFirst = beforeFirst.next;
        }
        beforeFirst.next = first; // creates circular linked list

        for (int rem = n; rem > 1; rem--) {
            int steps = k % rem == 0 ? rem : k % rem;
            Node beforeEliminated = beforeFirst;
            for (int i = 0; i < steps - 1; i++) beforeEliminated = beforeEliminated.next;
            beforeEliminated.next = beforeEliminated.next.next;
            beforeFirst = beforeEliminated;
        }
        return beforeFirst.val;
    }

    public static void main(String[] args) {
        int n = 5, k = 2;
        FindWinnerOfCircularGame_1823 solver = new FindWinnerOfCircularGame_1823();
        System.out.println(solver.findTheWinner(n, k));
    }
}
