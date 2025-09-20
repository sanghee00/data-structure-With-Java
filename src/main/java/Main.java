public class Main {
    public static void main(String[] args) {

        원형_큐_리스트 circleQueue = new 원형_큐_리스트();

        circleQueue.add(1);
        circleQueue.add(2);
        circleQueue.add(3);
        circleQueue.add(4);
        circleQueue.add(5);
        circleQueue.add(6);
        circleQueue.add(7);
        circleQueue.add(8);
        circleQueue.add(9);
        circleQueue.add(10);

        System.out.println("맨위 : " + circleQueue.peek());
        System.out.println(circleQueue.poll());
        System.out.println(circleQueue.poll());
        System.out.println(circleQueue.poll());
        System.out.println(circleQueue.poll());
        System.out.println(circleQueue.poll());
        System.out.println(circleQueue.poll());
        System.out.println(circleQueue.poll());
        System.out.println(circleQueue.poll());
        System.out.println(circleQueue.poll());
        System.out.println(circleQueue.poll());

    }
}
