import java.util.Objects;

public class 원형_큐 {
    private final Object[] queue;
    private int front = 0;
    private int rear = 0;

    public 원형_큐(int size) {
        this.queue = new Object[size];
    }

    public void add(Object data) {
        if (Objects.equals(front, rear) && !isEmpty(rear)) {
            throw new RuntimeException();
        }


        queue[rear++] = data;
        // 값이 같을 시 처음 넣을 때, 값이 꽉 찼을 때
        rear %= queue.length;
    }

    public Object poll() {
        if (Objects.equals(front, rear) && isEmpty(front)) {
            return null;
        }

        Object returnValue = queue[front];
        queue[front++] = null; // 뺀 값은 null 처리
        front %= queue.length;

        return returnValue;
    }

    public Object peek() {

        if (isEmpty(front)) {
            throw new RuntimeException();
        }

        return queue[front];
    }

    public boolean isEmpty(int index) {
        return queue[index] == null;
    }
}
