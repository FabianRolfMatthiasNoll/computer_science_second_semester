package algorithm_course.VL1.Task5;

import java.util.NoSuchElementException;

public class Queue<T> {
    private T[] queue;
    private int head;
    private int tail;
    private int size;

    public Queue(){
        queue = (T[]) new Object[100];
        head = 0;
        tail = -1;
        size = 0;
    }
    public void enqueue(T item){
        tail++;
        queue[tail] = item;
        size++;
    }

    public T dequeue(){
        if (size==0){
            throw new NoSuchElementException();
        }

        T item = queue[head];
        head++;
        size--;
        if (size == 0){
            head = 0;
            tail = -1;
        }
        return item;
    }

    public int size(){
        return size;
    }

    public T peek(){
        if (size==0){
            throw new NoSuchElementException();
        }

        return queue[head];
    }

    public static void main(String[] args) {
        Queue<Object> myQueue = new Queue<>();

        myQueue.enqueue(new Object("Object 1", 1));
        myQueue.enqueue(new Object("Object 2", 2));
        myQueue.enqueue(new Object("Object 3", 3));

        Object obj = myQueue.dequeue();
        System.out.println(obj.getName() + " " + obj.getValue());

        obj = myQueue.peek();
        System.out.println(obj.getName() + " " + obj.getValue());
    }
}

