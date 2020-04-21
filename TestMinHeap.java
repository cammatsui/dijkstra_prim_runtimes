public class TestMinHeap {
    public static void main(String[] args) {
        MinHeap<Integer> myHeap = new MinHeap<Integer>();
        myHeap.add(new Integer(55));
        myHeap.add(new Integer(4));
        myHeap.add(new Integer(93));
        myHeap.add(new Integer(9));
        myHeap.add(new Integer(32));
        myHeap.add(new Integer(8));
        myHeap.add(new Integer(69));
        myHeap.add(new Integer(17));
        myHeap.add(new Integer(19));
        myHeap.add(new Integer(26));
        myHeap.add(new Integer(16));
        myHeap.add(new Integer(50));
        myHeap.print();
    }
}