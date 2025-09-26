import javax.swing.*;

/**
 * 행위
 * [키 관련]
 * 키를 추가한다.
 * 키를 삭제한다.
 * 키를 찾는다.
 * [색 확인]
 * 해당 노드가 빨강 노드인지 확인한다.
 * 해당 노드가 검정 노드인지 확인한다.
 * [스왑]
 * 두 노드의 색깔을 스왑한다.
 * [회전]
 * LL 회전을 한다.
 * RR 회전을 한다.
 * [재배치]
 * 삽입 시 노드를 재배치한다.
 */
public class RedBlackTree {
    Node root;

    public boolean isExist(Node x) {
        return null != x;
    }

    public boolean isRed(Node x) {
        return isExist(x) && Node.RED == x.color;
    }

    public boolean isBlack(Node x) {
        return !isExist(x) || Node.BLACK == x.color;
    }

    // 좌측으로 회전하는 RR회전
    public void RR_rotate(Node P) {
        Node GP = P.parent; // 루트 노드의 부모
        Node R = P.right;
        Node RL = R.left;
        R.left = P;
        P.right =  RL;

        // R의 왼쪽 노드에다가 자식노드가 되었으니 R이 부모가 된다.
        P.parent = R;
        // 만약 RL이 존재하면 RL의 부모는 P가 된다.
        if (isExist(RL)) {
            RL.parent = P;
        }

        // 루트가 된 노드는 그 전에 있던 부모에다가 연결
        R.parent = GP;

        // 하지만 부모가 없다면? 최상단 노드로 판단
        if (!isExist(GP)) {
            this.root = R;
        } else {
            // 부모가 존재
            // 기존 루트 노드가 부모의 오른쪽에 있는 노드라면 바뀔 노드를 부모의 오른쪽에 연결
            if (GP.right == P) {
                GP.right = R;
            } else if (GP.left == P) {
                GP.left = R;
            }
        }

    }

    public void LL_rotate(Node P) {
        Node GP = P.parent;
        Node L = P.left;
        Node LR = L.right;

        L.right = P;
        P.left = LR;

        P.parent = L;
        if (isExist(LR)) {
            LR.parent = P;
        }

        L.parent = GP;

        // GP 즉, 부모노드가 존재하지 않으면
        if (!isExist(GP)) {
            this.root = L; // L이 루트 노드가 된다.
        } else {
            if (GP.right == P) {
                GP.right = L;
            } else if (GP.left == P) {
                GP.left = L;
            }
        }
    }

    public void insertFixup(Node g) {
        // 삼촌 검정, 우측-좌측 Double Red
        if (isRed(g.right) && isRed(g.right.left) && isBlack(g.left)) {
            LL_rotate(g.right);
            swapColor(g, g.right);
            RR_rotate(g);
        }
        // 삼촌 검정, 우측-우측 Double Red
        else if (isRed(g.right) && isRed(g.right.right) && isBlack(g.left)) {
            swapColor(g, g.right);
            RR_rotate(g);
        }
        // 삼촌 빨강, 우측-우측 Double Red
        else if (isRed(g.right) && (isRed(g.right.right) || isRed(g.right.left))) {
            g.color = Node.RED;
            g.right.color = Node.BLACK;
            g.left.color = Node.BLACK;
        }
        // 삼촌 검정, 좌측-우측 Double Red
        else if (isRed(g.left) && isRed(g.left.right) && isBlack(g.right)) {
            RR_rotate(g.left);
            swapColor(g, g.left);
            LL_rotate(g);
        }
        // 삼촌 검정, 좌측-좌측 Double Red
        else if (isRed(g.left) && isRed(g.left.left) && isBlack(g.right)) {
            swapColor(g, g.left);
            LL_rotate(g);
        }
        // 삼촌 빨강, 좌측-좌측 Double Red
        else if (isRed(g.left) && ((isRed(g.left.left)) || isRed(g.left.right))) {
            g.color = Node.RED;
            g.left.color = Node.BLACK;
            g.right.color = Node.BLACK;
        }

    }

    private void swapColor(Node nodeA, Node nodeB) {
        int color = nodeA.color;
        nodeA.color = nodeB.color;
        nodeB.color = color;
    }

    public void add(int key) {
        Node newNode = new Node();
        newNode.key = key;
        if (null == root) {
            root = newNode;
        } else {
            insertNode(root, newNode);
        }
        root.color = Node.BLACK;
    }

    private void insertNode(Node x, Node newNode) {
        // isExist로 다음노드가 존재 할경우는 이 반복분이 타지 않고 재귀 함수를 탄다.
        if (x.key > newNode.key && !isExist(x.left)) {
            x.left = newNode;
            newNode.parent = x;
        } else if (x.key <= newNode.key && !isExist(x.right)) {
            x.right = newNode;
            newNode.parent = x;
        } else if (x.key > newNode.key) {
            insertNode(x.left, newNode);
        } else if (x.key < newNode.key) {
            insertNode(x.right, newNode);
        }

        // 연결이 완료되면 레드블랙 트리의 규칙에 어긋났는지 확인한다.
        insertFixup(x);
    }

    public int search(int key) {
        return searchNode(root, key).key;
    }

    private Node searchNode(Node parent, int key) {
        if (null == parent) {
            throw new RuntimeException("노드를 찾을 수 없습니다.");
        } else if (parent.key > key) {
            parent = searchNode(parent.left, key);
        } else if (parent.key < key) {
            parent = searchNode(parent.right, key);
        }
        return parent;
    }

    private void printHelper(Node root, String indent, boolean last) {
        if (root != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color == Node.RED ? "RED" : "BLACK";
            System.out.println(root.key + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    public void printTree() {
        printHelper(this.root, "", true);
    }

    public void traversal() {
        inorderTraversal(root);
        System.out.println("");
    }

    private void inorderTraversal(Node node) {
        if (null == node) {
            return;
        }
        inorderTraversal(node.left);
        System.out.printf("%d ", node.key);
        inorderTraversal(node.right);
    }

    // 부모의 값을 가지고 있는 값과 색을 가지고 있는 필드값이 추가가 되었다.
    public static class Node {
        int key;
        Node left, right, parent;
        int color = RED;

        static final int RED = 0;
        static final int BLACK = 1;
    }
}
