import java.util.Stack;

class MinStack {

    private class valMinPair {
        int val;
        int minWhenInserted;

        public valMinPair(int val, int minWhenInserted) {
            this.val = val;
            this.minWhenInserted = minWhenInserted;
        }
    }
    private Stack<valMinPair> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        int prevMin = stack.empty()? Integer.MAX_VALUE : stack.peek().minWhenInserted;
        stack.push(new valMinPair(val, Math.min(prevMin, val)));
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().minWhenInserted;
    }
}