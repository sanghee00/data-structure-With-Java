import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        이중_연결_링크드_리스트<Integer> doubleLinkedList = new 이중_연결_링크드_리스트<>();
        doubleLinkedList.addLast(1);
        doubleLinkedList.addLast(2);
        doubleLinkedList.addLast(3);
        doubleLinkedList.addLast(4);
        doubleLinkedList.addLast(5);
        doubleLinkedList.add(6);

        for (int i = 0; i < doubleLinkedList.size(); i++) {
            System.out.println(doubleLinkedList.get(i));
        }
        System.out.println("--------------------------------------------------");
        /*doubleLinkedList.add(2, 100);
        for (int i = 0; i < doubleLinkedList.size(); i++) {
            System.out.println(doubleLinkedList.get(i));
        }*/
        /*System.out.println("삭제한 노드: " + doubleLinkedList.removeFirst());
        for (int i = 0; i < doubleLinkedList.size(); i++) {
            System.out.println(doubleLinkedList.get(i));
        }*/

       /* System.out.println("삭제한 노드: " + doubleLinkedList.remove(1));
        for (int i = 0; i < doubleLinkedList.size(); i++) {
            System.out.println(doubleLinkedList.get(i));
        }*/

        /*System.out.println("삭제한 노드: " + doubleLinkedList.removeData(5));
        for (int i = 0; i < doubleLinkedList.size(); i++) {
            System.out.println(doubleLinkedList.get(i));
        }*/

//        System.out.println("존재하는 노드: " + doubleLinkedList.contains(6));

        List<Integer> data = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        doubleLinkedList.addAll(2, data);
    }
}
