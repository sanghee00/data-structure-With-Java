public class Main {
    public static void main(String[] args) {

        스택_배열 stackArray = new 스택_배열(10);
        // System.out.println(stackArray.isEmpty());
        stackArray.push(1);
        stackArray.push(2);
        stackArray.push(3);
        stackArray.push(4);

        // System.out.println(stackArray.peek());
        System.out.println(stackArray.pop());
        System.out.println(stackArray.pop());
        System.out.println(stackArray.pop());
        System.out.println(stackArray.pop());

    }
}
