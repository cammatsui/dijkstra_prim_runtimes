// A min heap to store VertexPaths in a graph during Dijkstra's algorithm. 
public class MinVertexPathHeap {
    private VertexPath[] backingArray;
    private int size;

    public MinVertexPathHeap() {
        this.size = 0;
        this.backingArray = new VertexPath[10];
    }

    // Get the left, right, and parents of a given index. O(1).
    private int left(int i) {return 2*i+1;}
    private int right(int i) {return 2*i+2;}
    private int parent(int i) {return (i-1)/2;}

    // Get the size of the min heap. O(1).
    public int size() {return size;}

    // Expand or shrink the idea if necessary. O(n).
    private void resize() {
        int newSize;
        if (this.size == backingArray.length) {newSize = 2 * backingArray.length;}
        else if (this.size < backingArray.length/2 && backingArray.length > 10) {newSize = backingArray.length/2;}
        else {return;}
        VertexPath[] newBacking = new VertexPath[newSize];
        for (int i = 0; i < size; i++) newBacking[i] = backingArray[i];
        backingArray = newBacking;
    }

    // Add an element to the min heap. O(logn)
    public void add(VertexPath x) {
        resize();
        backingArray[size] = x;
        x.setIndex(size);
        int i = size;
        size += 1;
        // Bubble up; swap with our parent if we're smaller than it.
        bubbleUp(i);
    }

    // Bubble Up to maintain heap property.
    public void bubbleUp(int i) {
        int p = parent(i);
        while (i > 0 && backingArray[i].compareTo(backingArray[p]) < 0) {
            swap(i,p);
            i = p;
            p = parent(i);
        }
    }

    // Update and relocate a vertexpath.
    public void update(VertexPath x, double newPath, int newPrev) {
        int xIndex = x.getIndex();
        if (newPath < x.getPath()) {
            x.setPath(newPath);
            bubbleUp(xIndex);
        } else {
            x.setPath(newPath);
            trickleDown(xIndex);
        }
        x.setPrev(newPrev);
    }

    // Remove the smallest element of the priority queue. O(logn).
    public VertexPath remove() {
        if (this.size == 0) return null;
        resize();
        VertexPath x = backingArray[0];
        backingArray[0] = backingArray[size-1];
        size -= 1;
        int i = 0;
        trickleDown(i);
        return x;
    }

    // Tricle down to maintain heap property.
    public void trickleDown(int i) {
        while (true) {
            // If we have no children, we're done.
            if (left(i) >= size && right(i) >= size) {break;}
            // If we only have one child and we're less than that child, we're done
            else if (left(i) >= size && backingArray[i].compareTo(backingArray[right(i)]) < 0) {break;}
            else if (right(i) >= size && backingArray[i].compareTo(backingArray[left(i)]) < 0) {break;}
            // If we're less than both our children, we're done
            else if (backingArray[i].compareTo(backingArray[right(i)]) < 0 && backingArray[i].compareTo(backingArray[left(i)]) < 0) {break;} 
            // Otherwise, we swap with the our smallest child
            else {
                boolean swapRight = false;
                if (backingArray[right(i)].compareTo(backingArray[left(i)]) < 0) swapRight = true;
                if (swapRight) {
                    //System.out.println("Swapped " + backingArray[i] + " at index " + i + " with " + backingArray[right(i)] + " at index " + right(i));
                    swap(i, right(i));
                    i = right(i);
                } else {
                    //System.out.println("Swapped " + backingArray[i] + " at index " + i + " with " + backingArray[left(i)] + " at index " + left(i));
                    swap(i, left(i));
                    i = left(i);
                }
            }
        }
    }

    // Swap the elements at indices i and j in the backing array. O(1).
    private void swap(int i, int j) {
        VertexPath temp = backingArray[i];
        backingArray[i].setIndex(j);
        backingArray[j].setIndex(i);
        backingArray[i] = backingArray[j];
        backingArray[j] = temp;
    }

    // Print the min heap.
    public void print() {
        String str = "[";
        for (int i = 0; i < this.size-1; i++) {
            str += backingArray[i] + ", ";
        }
        str += backingArray[size-1] + "]";
        System.out.println(str);
    }

}