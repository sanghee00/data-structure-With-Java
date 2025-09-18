import org.w3c.dom.CDATASection;

import java.util.ArrayList;
import java.util.Arrays;
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

/*        List<Integer> data = List.of(100, 1000, 10000, 100000, 1000000, 10000000);
        doubleLinkedList.addAll(1, data);

        for (int i = 0; i < doubleLinkedList.size(); i++) {
            System.out.println(doubleLinkedList.get(i));
        }*/

//        doubleLinkedList.remove(1);
//        doubleLinkedList.set(2, 100);


        /*List<Integer> data = List.of(2, 3, 4);
        System.out.println("데이터 변환 완료: " + doubleLinkedList.retainAll(data));
        for (int i = 0; i < doubleLinkedList.size(); i++) {
            System.out.println(doubleLinkedList.get(i));
        }*/

        /*List<Integer> data = List.of(2, 3, 4);
        System.out.println("데이터 변환 완료: " + doubleLinkedList.removeAll(data));
        for (int i = 0; i < doubleLinkedList.size(); i++) {
            System.out.println(doubleLinkedList.get(i));
        }*/

        /*List<Integer> data = List.of(2, 3, 4);
        System.out.println("데이터 변환 완료: " + doubleLinkedList.containsAll(data));*/

        /*List<Integer> integers = doubleLinkedList.subList(1, 3);
        integers.forEach(System.out::println);*/

        /*Object[] array = doubleLinkedList.toArray();
        System.out.println(Arrays.toString(array));*/

        /*Integer[] array = doubleLinkedList.toArray(new Integer[20]);
        for (Integer data : array) {
            System.out.println(data);
        }*/

        // System.out.println(doubleLinkedList.indexOf(2));
        // System.out.println(doubleLinkedList.lastIndexOf(100));

        System.out.println(doubleLinkedList.iterator());
    }
}
