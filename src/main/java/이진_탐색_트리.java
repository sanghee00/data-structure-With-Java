public class 이진_탐색_트리 {

    Node root;

    public static class Node {
        int key;
        Node left;
        Node right;
    }

    // add
    public void add(int key) {
        Node newNode = new Node();
        newNode.key = key;

        if (root == null) {
            root = newNode;
            return;
        }

        insertNode(root, newNode);
    }

    // 큰쪽은 오른쪽, 작은 쪽은 왼쪽
    private Node insertNode(Node x, Node newNode) {
        if (x == null) {
            return newNode;
        } else if (x.key > newNode.key) {
            x.left = insertNode(x.left, newNode);
        } else if (x.key < newNode.key) {
            x.right = insertNode(x.right, newNode);
        }

        return x;
    }

    private Node getLargestNode(Node x) {
        if (null == x.right) {
            return x;
        }

        return getLargestNode(x.right);
    }

    private Node getSmallestNode(Node x) {
        if (null == x.left) {
            return x;
        }

        return getSmallestNode(x.left);
    }

    private Node removeNode(Node x, int key) {
        if (null == x) {
            throw new RuntimeException("노드가 존재하지 않음");
        } else if (x.key > key) {
            x.left = removeNode(x.left, key);
        } else if (x.key < key) {
            x.right = removeNode(x.right, key);
        } else {
            // 왼쪽 노드가 존재한다면
            if (null != x.left) {
                Node predecessor = getLargestNode(x.left); // 왼쪽에서 가장 큰 노드를 가져온다.
                int removeKey = x.key; // 삭제할 노드 값
                x.key = predecessor.key;
                predecessor.key = removeKey;
                x.left = removeNode(x.left, key);
            } else if (null != x.right) { // 오른쪽 노드가 존재한다면
                Node successor = getSmallestNode(x.right); // 오른쪽에서 가장 작은 노드를 가져온다.
                int removeKey = x.key;
                x.key = successor.key;
                successor.key = removeKey;
                x.right = removeNode(x.right, key);
            } else {
                return null;
            }
        }
        return x;

    }

    public int search(int key) {
        return serachNode(root, key).key;
    }

    private Node serachNode(
        Node x,
        int key
    ) {
        Node node = x;
        if (null == node) {
            throw new RuntimeException("노드가 없음");
        } else if (node.key > key) {
            node = serachNode(node.left, key);
        } else if (node.key < key) {
            node = serachNode(node.right, key);
        }

        return node;
    }
}
