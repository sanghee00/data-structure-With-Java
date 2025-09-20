public class 스택_리스트<E> {

    private Node<E> head;

    public 스택_리스트() {
        this.head = null;
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node() {
            this.data = null;
            this.next = null;
        }

        public Node(
            E data,
            Node<E> next
        ) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 행위
     * boolean isEmpty(): 데이터가 존재하는지 확인한다.
     * void push(E data): 데이터를 넣는다.
     * E pop(): 데이터를 꺼낸다.
     * E peek(): 맨 위의 데이터가 무엇인지 확인한다.
     */

    public boolean isEmpty() {

        return head == null;
    }

    public void push(E data) {
        Node<E> newNode = new Node<>();
        newNode.data = data;
        if (!isEmpty()) {
            newNode.next = head;
        }

        head = newNode;

    }

    public E pop() {

        if (isEmpty()) {
            throw new RuntimeException();
        }

        Node<E> removedNode = head;
        E tempData = removedNode.data;
        head = removedNode.next;


        removedNode.data = null;
        removedNode.next = null;
        return tempData;
    }

    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException();
        }

        return head.data;
    }



}
