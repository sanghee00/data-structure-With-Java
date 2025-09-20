public class 원형_큐_리스트 {

    private Node front;
    private Node rear;

    private static class Node {
        Object data;
        Node next;
    }

    public void add(Object data) {

        Node newNode = new Node();
        newNode.data = data;

        // 만약에 비어 있다면
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

    }

    public Object poll() {

        if (isEmpty()) {
            return null;
        }

        Node removedNode = front;
        Object removedData = removedNode.data;
        front = removedNode.next;

        if (removedNode == rear) {
            rear = null;
        }
        return removedData;
    }

    public Object peek() {

        if (isEmpty()) {
            return null;
        }

        return front.data;
    }

    public boolean isEmpty() {
        return null == front;
    }

}
