package algorithm_course.VL1.Task6;

public class ChainedList<T> {

    private class Cell<T>{
        T data;
        Cell<T> next;

        public Cell(T o){
            data = o;
        }
    }

    private Cell<T> anchor; // points to the first Cell Element

    public void add(T o){
        Cell<T> newCell = new Cell<T>(o);
        if (anchor == null){
            anchor = newCell;
            newCell.next = null;
        } else {
            newCell.next = anchor;
            anchor = newCell;
        }
    }

    public void printAll() {
        Cell<T> currentCell = anchor;
        while (currentCell != null) {
            System.out.println(currentCell.data);
            currentCell = currentCell.next;
        }
    }

    public void delete (T o) {
        if (anchor == null){
            return;
        }

        if (anchor.data == o){
            anchor = anchor.next;
            return;
        }

        Cell<T> previousCell = anchor;
        Cell<T> currentCell = anchor.next;

        while (currentCell != null) {
            if(currentCell.data == o){
                previousCell.next = currentCell.next;
                return;
            }
            previousCell = currentCell;
            currentCell = currentCell.next;
        }
    }

    public static void main(String[] args) {
        ChainedList<String> list = new ChainedList<>();
        list.add("hello");
        list.add("world");
        list.add("!");

        list.delete("world");

        list.printAll();
    }
}
