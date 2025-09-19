public class 스택_배열 {
    private int top = -1;
    private Object[] stack;

    public 스택_배열(int count) {
        this.stack = new Object[count];
    }

    /**
     * 행위
     * boolean isEmpty(): 데이터가 존재하는지 확인한다.
     * boolean ifFull(): 데이터가 꽉 찼는지 확인한다.
     * void push(Object data): 데이터를 넣는다.
     * Object pop(): 데이터를 꺼낸다.
     * Object peek(): 맨 위의 데이터가 무엇인지 확인한다.
     */
    public boolean isEmpty()  {
        return top == -1;
    }

    public boolean isFull() {
        return stack.length - 1 == top;
    }

    public void push(Object data) {

        if (isFull()) {
            throw new StackOverflowError();
        }

        // 데이터가 존재하지 않을 경우
        stack[++top] = data;

    }

    public Object pop() {

        if (isEmpty()) {
            throw new StackOverflowError();
        }

        Object returnValue = stack[top];
        stack[top--] = null;

        return returnValue;
    }

    public Object peek() {

        if (isEmpty()) {
            return null;
        }

        return stack[top - 1];
    }



}
