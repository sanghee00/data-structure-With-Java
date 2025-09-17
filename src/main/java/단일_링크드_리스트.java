public class 단일_링크드_리스트<E>{

    private Node<E> head;
    private Node<E> tail;

    private int size;

    public 단일_링크드_리스트() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(
            E data,
            Node<E> next
        ) {
            this.data = data;
            this.next = next;
        }

    }

    // 추가 요소
    // addFirst
    public void addFirst(E data) {

        Node<E> first = head;
        Node<E> newNode = new Node<>(data, first);
        head = newNode;
        size++;

        if (first == null) {
            tail = newNode;
        }

    }


    // addLast
    public void addLast(E data) {
        Node<E> last = tail;
        Node<E> newNode = new Node<>(data, null);

        tail = newNode;
        size++;

        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }

    }

    // add
    public void add(E data, int index) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(data);
        }

        Node<E> backNode = head;

        // 전 노드를 찾음
        for (int i = 0; i < index - 1; i++) {
            backNode = backNode.next;
        }

        Node<E> newNode = new Node<>(data, backNode.next);
        backNode.next = newNode;
        size++;
    }

    // 요소 조회
    // get
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> indexNode = head;

        for (int i = 0; i < index; i++) {
            indexNode = indexNode.next;
        }

        return indexNode.data;
    }

    // getFirst
    public E getFirst() {
        return head.data;
    }
    // getLast
    public E getLast() {
        return tail.data;
    }

    // contains
    public boolean contains(E data) {
        Node<E> first = head;

        if (first.data == data) {
            return true;
        }

        for (int i = 0; i < size - 1; i++) {
            first = first.next;
            if (first.data == data) {
                return true;
            }
        }

        return false;
    }

    // 요소 수정
    // set(교체)
    public void set(int index, E data) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> first = head;

        for (int i = 0; i < index; i++) {
            first = first.next;
            if (i == index - 1) {
                first.data = data;
            }
        }

    }

    // 요소 삭제
    // removeFirst
    public E removeFirst() {
        Node<E> first = head;
        head = first.next;
        size--;

        return first.data;
    }
    // removeLast()
    public E removeLast() {
        Node<E> last = tail;
        tail = tail.next;
        size--;

        return last.data;
    }

    // remove 위치
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> backNode = head;
        for (int i = 0; i < index - 1; i++) {
            backNode = backNode.next;
        }


        Node<E> removeNode = backNode.next;
        backNode.next = removeNode.next;

        size--;

        return removeNode.data;
    }

    // remove 값
    public boolean removeData(E data) {
        // head에 삭제할께 있을 시 삭제
        if (head.data == data) {
            removeFirst();
            return true;
        }

        Node<E> removeIndex = head; // 삭제할 노드
        Node<E> backNode = head; // 삭제할 노드의 전 노드
        boolean valueExist = false;

        for (int i = 0; i < size; i++) {
            removeIndex = removeIndex.next; // 삭제할 노드

            if (removeIndex.data == data) {
                valueExist = true;
                break;
            }

            backNode = backNode.next;
        }

        // 삭제할 노드가 없을 때
        if (!valueExist) {
            return valueExist;
        }

        // tail에 삭제할 노드가 있을 경우
        if (removeIndex.next == null) {
            removeLast();
            return true;
        }

        backNode.next = removeIndex.next;

        size--;
        return true;
    }

    // 기타
    // size
    public int size() {
        return size;
    }
    // isEmpty
    public boolean isEmpty() {
        return size == 0;
    }
    // clear
    public void clear() {
        Node<E> first = head;
        while (first != null) {
            Node<E> nextNode = head.next;
            first.next = null;
            first.data = null;
            first = nextNode;
        }
        head = tail = null;
        size = 0;
    }


}
