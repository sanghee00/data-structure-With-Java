import java.lang.reflect.Array;
import java.util.*;

public class 이중_연결_링크드_리스트<E>{

    private Node<E> head;
    private Node<E> tail;

    private int size;

    public 이중_연결_링크드_리스트() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public static class Node<E> {
        private Node<E> left;
        private Node<E> right;
        private E value;


        public Node(
            E value,
            Node<E> left,
            Node<E> right
        ) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 추가 요소
     * addFirst(E value): 처음 요소에 추가
     * addLast(E value): 맨 마지막 요소에 추가
     * add(int index, E value): 중간 노드에 요소 추가
     * add(E data): 맨 뒤에 data를 추가한다.
     */
    public void addFirst(E value) {

        Node<E> headNode = head;

        // 헤드에 아무것도 없을 시: 처음 값을 넣을때
        if (headNode == null) {

            Node<E> newNode = new Node<>(value, null, null);
            head = new Node<>(null, null, newNode);
            tail = new Node<>(null, newNode, null);
            newNode.left = head;
            newNode.right = tail;
        } else {

            Node<E> tempNode = headNode.right; // 처음 노드를 저장
            // 1. 헤드를 새 노드에 연결
            // 2. 연결을 끊을 노드 다음 노드를 새 노드와 연결해준다.
            Node<E> newNode = new Node<>(value, head, tempNode);
            headNode.right = newNode;
            tempNode.left = newNode;
        }

        size++;

    }

    public void addLast(E value) {
        Node<E> tailNode = tail;

        if (tailNode == null) {

            Node<E> newNode = new Node<>(value, null, null);
            head = new Node<>(null, null, newNode);
            tail = new Node<>(null, newNode, null);
            newNode.left = head;
            newNode.right = tail;
        } else {

            Node<E> tempNode = tailNode.left;
            Node<E> newNode = new Node<>(value, tempNode, tail);
            tailNode.left = newNode;
            tempNode.right = newNode;
        }

        size++;

    }

    public void add(
        int index,
        E value
    ) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        // 첫번째 인덱스 일 경우
        if (index == 0) {
            addFirst(value);
            return;
        }

        // 마지막 인덱스 일경우
        if (index == size - 1) {
            addLast(value);
            return;
        }

        // 추가할려는 값이 앞쪽에 있을 경우
        if (index < size / 2) {

            Node<E> findNode = head.right;
            // 앞쪽에서 가져온다.
            for (int i = 0; i < index; i++) {
                findNode = findNode.right;
            }

            Node<E> backNode = findNode.left;
            Node<E> newNode = new Node<>(value, backNode, findNode);
            backNode.right = newNode;
            findNode.left = newNode;
        } else if (index >= size / 2) {

            // 뒤쪽에서 가져온다.
            Node<E> findNode = tail.left;
            for (int i = size - 1; i > index; i--) {
                findNode = findNode.left;
            }

            Node<E> backNode = findNode.left;
            Node<E> newNode = new Node<>(value, backNode, findNode);
            backNode.right = newNode;
            findNode.left = newNode;
        }

        size++;
    }

    public boolean add(E data) {
        addLast(data);
        return true;
    }

    public boolean addAll(
        int index,
        Collection<E> collection
    ) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Node<E> findNode = head.right;
        for (int i = 0; i < index; i++) {
            findNode = findNode.right;
        }

        Node<E> nextNode = findNode.right;

        for (E element : collection) {
            if (nextNode == tail) {
                addLast(element);
            } else {
                Node<E> newNode = new Node<>(element, findNode, nextNode);
                findNode.right = newNode;
                nextNode.left = newNode;
                findNode = newNode;
            }
        }

        size += collection.size();

        return true;
    }

    /**
     * 조회 기능
     * get(int index);
     */
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index == 0) {
            return head.right.value;
        }

        if (index == size - 1) {
            return tail.left.value;
        }

        Node<E> findNode = searchNode(index);

        return findNode.value;

    }

    /**
     * 삭제
     * removeFirst()
     * removeLast()
     * remove(int index)
     * remove(E value)
     */
    public E removeFirst() {
        E removeNode = head.right.value;
        Node<E> secondNode = head.right.right; // 두번째 노드
        head.right = secondNode;
        secondNode.left = head;

        size--;

        return removeNode;
    }

    public E removeLast() {
        E removenode = tail.left.value;
        Node<E> secondNode = tail.left.left;
        tail.left = secondNode;
        secondNode.right = tail;

        size--;

        return removenode;
    }

    public E remove(int index) {

        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeFirst();
        }

        if (index == size - 1) {
            return removeLast();
        }

        Node<E> removeNode = searchNode(index);
        Node<E> backNode = removeNode.left;
        Node<E> nextNode = removeNode.right;
        backNode.right = nextNode;
        nextNode.left = backNode;

        size--;

        E returnValue = removeNode.value;
        removeNode.left = null;
        removeNode.right = null;
        removeNode.value = null;

        return returnValue;
    }

    public boolean removeData(E value) {
        Node<E> firstNode = head.right;
        boolean existsNode = false;
        Node<E> findNode = head;

        if (firstNode.value.equals(value)) {
            removeFirst();
            return true;
        }

        for (int i = 0; i < size - 1; i++) {
            firstNode = firstNode.right;

            if (firstNode.value.equals(value)) {
                existsNode = true;
                findNode = firstNode;
                break;
            }
        }

        if (!existsNode) {
            return false;
        }

        if (findNode.right == tail) {
            removeLast();
            return true;
        }

        size--;

        return existsNode;
    }

    /**
     * 기타
     * size(): 크기 반환
     * isEmpty(): 비어있는지 확인
     * contains(E value): 값이 존재하는지 확인
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E value) {

        Node<E> firstNode = head.right;

        for (int i = 0; i < size; i++) {
            if (firstNode.value.equals(value)) {
                return true;
            }
            firstNode = firstNode.right;
        }

        return false;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    // index에 해당 데이터를 삽입( 덮어쓴다.)
    public E set(
        int index,
        E data
    ) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Node<E> findNode = searchNode(index);
        Node<E> backNode = findNode.left;
        Node<E> nextNode = findNode.right;

        Node<E> newNode = new Node<>(data, backNode, nextNode);

        backNode.right = newNode;
        nextNode.left = newNode;

        findNode.value = null;
        findNode.left = null;
        findNode.right = null;

        return newNode.value;
    }

    public Node<E> searchNode(int index) {
        Node<E> findNode;
        if (index < size / 2) {
            findNode = head.right;
            for (int i = 0; i < index; i++) {
                findNode = findNode.right;
            }
        } else {
            findNode = tail.left;
            for (int i = size - 1; i > index; i--) {
                findNode = findNode.left;
            }
        }

        if (findNode == null) {
            throw new NullPointerException();
        }

        return findNode;
    }

    public boolean retainAll(Collection<E> collection) {

        boolean existValue = false;
        List<E> valueList = new ArrayList<>();
        Set<E> hashSet = new HashSet<>(collection);
        Node<E> foundNode = head.right;

        for (int i = 0; i < size; i++) {
            if (hashSet.contains(foundNode.value)) {
                valueList.add(foundNode.value);
            }
            foundNode = foundNode.right;
        }

        // [1, 2, 3] , [1, 2, 3] 와 같이 똑같으면 감지 변경 X
        if (Objects.equals(valueList.size(), size)) {
            return false;
        }

        // 3. 없을 경우 false
        if (valueList.isEmpty()) {
            return existValue;
        } else {
            existValue = true;
        }

        // 4. 순서대로 연결한다.
        clear(); // 데이터 초기화
        for (E element : valueList) {
            addLast(element);
        }

        return existValue;
    }

    public boolean removeAll(Collection<E> collection) {

        boolean existValue = false;
        List<E> valueList = new ArrayList<>();
        Set<E> hashSet = new HashSet<>(collection);
        Node<E> removeNode = head.right;

        for (int i = 0; i < size; i++) {
            if (!hashSet.contains(removeNode.value)) {
                valueList.add(removeNode.value);
            }
            removeNode = removeNode.right;
        }

        // 3. 없을 경우 false
        if (valueList.isEmpty()) {
            return existValue;
        } else {
            existValue = true;
        }

        // 4. 순서대로 연결한다.
        clear(); // 데이터 초기화
        for (E element : valueList) {
            addLast(element);
        }

        return existValue;
    }

    public boolean containsAll(Collection<E> collection) {

        for (E element : collection) {

            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    public List<E> subList(int fromIndex, int toIndex) {

        if (fromIndex >= size || fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (toIndex >= size || toIndex < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        List<E> returnList = new ArrayList<>();
        Node<E> foundNode = searchNode(fromIndex);
        returnList.add(foundNode.value);

        for (int i = 0; i < toIndex - 1; i++) {

            foundNode = foundNode.right;
            returnList.add(foundNode.value);
        }

        return returnList;
    }

    public Object[] toArray() {
        Node<E> firstNode = head;
        Object[] array = new Object[this.size];

        for (int i = 0; i < size; i++) {
            firstNode = firstNode.right;
            array[i] = firstNode.value;
        }

        return array;
    }

    public <T> T[] toArray(T[] array) {

        Node<E> firstNode = head;
        // 내가 가진 데이터가 많을시
        if (array.length < this.size) {
            array = (T[]) Array.newInstance(array.getClass().getComponentType(), this.size);
        }

        for (int i = 0; i < size; i++) {
            firstNode = firstNode.right;
            array[i] = (T) firstNode.value;
        }

        return array;
    }

    public int indexOf(E data) {
        Node<E> foundNode = head;

        for (int i = 0; i < size; i++) {

            foundNode = foundNode.right;
            if (Objects.equals(data, foundNode.value)) {
                return i;
            }

        }

        return -1;
    }

    public int lastIndexOf(E data) {
        Node<E> foundNode = tail;

        for (int i = size; i > 0 ; i--) {

            foundNode = foundNode.left;
            if (Objects.equals(data, foundNode.value)) {
                return i - 1;
            }
        }

        return -1;
    }

    public Iterator<E> iterator() {
        return new MyIterator();
    }

    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }

    private class MyListIterator implements ListIterator<E> {

        private Node<E> nextNode;
        private Node<E> lastReturned;
        private int nextIndex;

        public MyListIterator(int index) {
            nextNode = (index == size) ? tail : searchNode(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = nextNode;
            nextNode = nextNode.right;
            nextIndex++;

            return lastReturned.value;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            nextNode = nextNode.left;
            lastReturned = nextNode;
            nextIndex--;

            return lastReturned.value;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {
            lastReturned.value = e;
        }

        @Override
        public void add(E e) {
            addLast(e);
        }
    }

    private class MyIterator implements Iterator<E> {

        private Node<E> nextNode;

        public MyIterator() {
            this.nextNode = head.right;
        }

        @Override
        public boolean hasNext() {
            return nextNode != tail;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E returnValue = nextNode.value;
            nextNode = nextNode.right;

            return returnValue;
        }
    }

}
