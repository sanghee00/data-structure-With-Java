/**
 * 행위
 * 1. 키를 추가한다.
 * 2. 키를 삭제한다.
 * 3. 키를 찾는다.
 * 4. 노드의 높이를 계산한다.
 * 5. 노드의 균형도를 계산한다.
 * 6. 노드의 높이를 변경한다.
 * 7. LL 회전을 한다.
 * 8. RR 회전을 한다.
 * 9. 균형을 맞추기 위해 회전한다.
 */
public class AVL_트리 {

    Node root;

    /**
     * 트리의 높이를 계산하는 방법
     * - leaf 노드는 높이는 0
     * - null인 경우는 -1
     * - 부모 노드의 높이를 구하는 공식
     *   - Max(왼쪽 자식 노드 높이, 오른쪽 자식 노드 높이) + 1
      */
    int getHeight(Node x) {
        // 왼쪽 높이를 가져온다. (null 아닌 경우, null 이면 -1을 가져와야 한다.)
        int leftChildHeight = (null != x.left) ? x.left.height  : -1;
        int rightChildHeight = (null != x.right) ? x.right.height : -1;

        return Math.max(leftChildHeight, rightChildHeight) + 1;
    }

    /**
     * 균형도를 계산하는 방법
     * - (왼쪽 노드의 높이 - 오른쪽 노드의 높이)
     * - 균형도의 절대값은 2미만
     * - 2이상이 될 경우 불균형으로 판단
     */
    int getBalance(Node x) {
        int leftChildHeight = (null != x.left) ? x.left.height : -1;
        int rightChildHeight = (null != x.right) ? x.right.height : -1;
        return leftChildHeight - rightChildHeight;
    }

    /**
     * 노드의 높이를 변경하는 메서드
     * 솔리드 - 분리 원칙
     */
    void changeNodeHeight(Node x) {
        x.height = getHeight(x);
    }

    /**
     * LL 불군형을 완화하는 메서드
     * - LL 불균형을 판단 후 실행되는 메서드
     * 1. 루트 노드를 좌측 자식 노드의 우측 노드로 이동
     * 2. 루트 노드의 좌측 노드의 우측 노드를 루트 노의 좌측 노드로 이동 시킨다.
     * P를 루트 노드로 판단
     */
    Node LL_rotate(Node P) {
        // 1. 루트 노드의 좌측 노드를 L에다가 저장
        Node L = P.left;
        // 2. 루트 노드의 좌측 노드의 우측 노드를 LR에다가 저장(추후 해당 노드를 루트 노드의 좌측에다가 연결)
        Node LR = L.right;
        // 3. 루트 노드를 좌측 자식 노드의 우측 노드로 이동 시킨다.
        L.right = P;
        // 4. 루트 노드의 왼쪽 자식 노드의 우측 노드를 루트의 노드의 왼쪽 노드에 연결
        P.left = LR;

        // 5. 이제 바뀐 노드를 값에 높이를 바꾼다.
        // 5-1. P(루트)는 현재 자식노드의 우측 노드로 변경이 되었기 때문에 높이를 재계산 한다.
        changeNodeHeight(P);
        // 5-2. L(루트가 될 노드) 루트노드가 L노드의 우측에 추가가 되었기 때문에 현재 노드의 높이도 재계산
        changeNodeHeight(L);

        // 루트가 된 노드를 반환
        return L;
    }

    /**
     * RR 불군형을 완화하는 메서드
     * - RR 불균형을 판단 후 실행되는 메서드
     * 1. 루트 노드를 우측 자식 노드의 좌측 노드로 이동
     * 2. 루트 노드의 자식 노드의 우측 노드의 좌측 노드를 루트 노드의 우측 노드로 이동 시킨다.
     * P를 루트 노드로 판단
     */
    Node RR_rotate(Node P) {
        Node R = P.right;
        Node RL = R.left;
        R.left = P;
        P.right = RL;

        // 바뀌 노드의 높이를 재계산
        changeNodeHeight(P);
        changeNodeHeight(R);

        return R;
    }

    Node rotate(Node x) {
        // x 노드의 균형도를 계산한다.
        int xNodeBalance = getBalance(x);

        // 절대값 2미만 인 경우 불균형으로 판단 후 회전 시작
        if (Math.abs(xNodeBalance) >= 2) {
            // 균형도가 1 초과 및 좌측 노드가 균형도 1인 경우(균형도가 일정하면 0임) LL 불군형으로 판단
            if (xNodeBalance > 1 && 0 <= getBalance(x.left)) {
                x = LL_rotate(x);
            } else if (xNodeBalance > 1 && -1 == getBalance(x.left)) {
                // 균형도가 1 초과 및 left의 노드가 -1일 경우 LR 불균형으로 판단
                x.left = RR_rotate(x.left); // 일단 먼저 좌측 회전으로 통해 LL 불균형으로 만든다.
                x = LL_rotate(x); // LL 불균형이 되었으니 이제 LL을 해결
            } else if (xNodeBalance < -1 && 0 >= getBalance(x.right)) {
                // 균형도가 -1 보다 미만 인경우 밑 우측 노드 균형도가 -1인 경우 RR 불군형으로 판단
                x = RR_rotate(x);
            } else if (xNodeBalance < -1 && 1 == getBalance(x.right)) {
                // 균형도가 -1 미만 좌측 노드가 1일 경우 RL 불균형 노드로 판단
                x.right = LL_rotate(x.right); // LL로 RR로 불균형으로 바꿈
                x = RR_rotate(x); // RR 불균형 해결 진행
            }
        }

        return x;
    }

    private Node insertNode(Node x, Node newNode) {
        // 노드를 연결한다.
        if (x == null) {
            return newNode;
        } else if (x.key > newNode.key) {
            x.left = insertNode(x.left, newNode);
        } else if (x.key < newNode.key) {
            x.right = insertNode(x.right, newNode);
        }

        // 연결이 끝나면 높이를 바꾼다.
        changeNodeHeight(x);
        // 바꾼 후 균형도를 계산을 한 후 불균형이 있을 시 불균형을 해결한다.
        return rotate(x);
    }

    public void add(int key) {
        Node newNode = new Node();
        newNode.key = key;
        if (null == root) {
            root = newNode;
        } else {
            root = insertNode(root, newNode);
        }
    }

    public void remove(int key) {
        root = removeNode(root, key);
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
        changeNodeHeight(x);
        return rotate(x);
    }

    public int search(int key) {
        return searchNode(root, key).key;
    }

    private Node searchNode(
        Node x,
        int key
    ) {
        Node node = x;
        if (null == node) {
            throw new RuntimeException("노드가 없음");
        } else if (node.key > key) {
            node = searchNode(node.left, key);
        } else if (node.key < key) {
            node = searchNode(node.right, key);
        }

        return node;
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


    public static class Node {
        int key;
        Node left;
        Node right;
        int height = 0;
    }

    private void printHelper(Node x, String indent, boolean last) {
        if (x != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            System.out.println(x.key + "(h:" + x.height + ")");
            printHelper(x.left, indent, false);
            printHelper(x.right, indent, true);
        }
    }

    public void traversal() {
        inorderTraversal(root);
        System.out.println("");
    }

    public void printTree() {
        printHelper(this.root, "", true);
    }

    private void inorderTraversal(Node node) {
        // TODO : 중위 순회
        if (null == node) {
            return;
        }
        inorderTraversal(node.left);
        System.out.printf("%d ", node.key);
        inorderTraversal(node.right);
    }
}
