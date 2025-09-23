import java.util.LinkedList;
import java.util.Queue;

public class 이진_트리_너비우선탐색 {
    Node root;

    public static class Node {
        int key;
        Node left;
        Node right;
    }

    void visit(Node node) {
        System.out.println(node.key);
    }

    void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        if (null == root) {
            return;
        }

        queue.offer(root);

        while (!queue.isEmpty()) {
            Node parentNode = queue.poll();
            visit(parentNode);

            if (parentNode.left != null) {
                queue.offer(parentNode.left);
            }

            if (parentNode.right != null) {
                queue.offer(parentNode.right);
            }
        }

    }

    public void add(int key) {
        Node newNode = new Node();
        newNode.key = key;

        if (root == null) {
            root = newNode;
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                Node parentNode = queue.poll();

                if (parentNode.left != null) {
                    queue.offer(parentNode.left);
                } else {
                    parentNode.left = newNode;
                    break;
                }

                if (parentNode.right != null) {
                    queue.offer(parentNode.right);
                } else {
                    parentNode.right = newNode;
                    break;
                }
            }
        }


    }
}
