import java.util.LinkedList;
import java.util.Queue;

public class 이진_트리_깊이우선탐색 {

    Node root;

    public static class Node {
        int key;
        Node left;
        Node right;
    }

    public void addData(int key) {
        Node newNode = new Node();
        newNode.key = key;
        if (root == null) {
            root = newNode;
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                Node parentNode = queue.poll();

                // 왼쪽 노드가 비어있지 않다면
                if (parentNode.left != null) {
                    queue.offer(parentNode.left);
                } else {
                    parentNode.left = newNode;
                    break;
                }

                // 오른쪽 노드가 비어있지 않다면
                if (parentNode.right != null) {
                    queue.offer(parentNode.right);
                } else {
                    parentNode.right = newNode;
                    break;
                }

            }
        }
    }

    // 중위 순회
    public void inorderTraversal() {
        inOrder(root);
        System.out.println();
    }

    // 전위 순회
    public void preorderTraversal() {
        preOrder(root);
        System.out.println();
    }

    // 후위 순회
    public void postorderTraversal() {
        postOrder(root);
        System.out.println();
    }

    // 중위 순회
    private void inOrder(Node node) {
        if (null == node) {
            return;
        }
        inOrder(node.left);
        visit(node);
        inOrder(node.right);
    }


    // 후회 순회
    private void postOrder(Node node) {
        if (null == node) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        visit(node);
    }

    private void preOrder(Node node) {
        if (null == node) {
            return;
        }

        visit(node);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void visit(Node node) {
        System.out.printf("%d ", node.key);
    }
}
