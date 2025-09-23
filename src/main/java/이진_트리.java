import java.util.LinkedList;
import java.util.Queue;

public class 이진_트리 {

    Node root;

    public static class Node {
        int key;
        Node left;
        Node right;
    }

    public void add(int key) {
        Node newNode = new Node();
        newNode.key = key;

        if (root == null) {
            root = newNode;
        } else {
            // 1. 큐 생성
            Queue<Node> queue = new LinkedList<>();
            // 2. root를 큐에 넣는다
            queue.offer(root);

            // 3. 큐가 빌때 까지 반복
            while (!queue.isEmpty()) {
                // 3-1. 루트 노드를 뺸다.
                Node rootNode = queue.poll();

                // 3-2. 루트 노드의 왼쪽이 비어 있으면 삽입
                if (rootNode.left == null) {
                    rootNode.left = newNode;
                    break;
                } else {
                    // 3-2-1. 해당 노드가 비어있지 않으면 큐에 넣는다.
                    queue.offer(rootNode.left);
                }

                // 3-3. 루트 노트의 오른쪽이 비어 있으면 삽입
                if (rootNode.right == null) {
                    rootNode.right = newNode;
                    break;
                } else {
                    // 3-3-1. 해당 노드가 비어있지 않으면 큐에 넣는다.
                    queue.offer(rootNode.right);
                }
            }

        }
    }

    public void levelOrder() {
        if (null == root) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node parenNode = queue.poll();
            System.out.println(parenNode.key);

            if (null != parenNode.left) {
                queue.offer(parenNode.left);
            }

            if (null != parenNode.right) {
                queue.offer(parenNode.right);
            }
        }
    }
}
