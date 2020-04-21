public class TestMinHeap {
    public static void main(String[] args) {
        MinDijkstraHeap myHeap = new MinDijkstraHeap();
        myHeap.add(new VertexPath(55));
        myHeap.add(new VertexPath(4));
        myHeap.add(new VertexPath(93));
        myHeap.add(new VertexPath(9));
        myHeap.add(new VertexPath(32));
        myHeap.add(new VertexPath(8));
        myHeap.add(new VertexPath(69));
        myHeap.add(new VertexPath(17));
        myHeap.add(new VertexPath(19));
        myHeap.add(new VertexPath(26));
        myHeap.add(new VertexPath(16));
        myHeap.add(new VertexPath(50));
        myHeap.print();
        while (myHeap.size() > 0) {
            System.out.println("Removed " + myHeap.remove());
        }
    }
}