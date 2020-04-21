public class MinDijkstraHeap {
    private VertexPath[] backingArray;
    private int size;

    public MinDijkstraHeap() {
        this.size = 0;
        this.backingArray = new VertexPath[10];
    }

    // Get the left, right, and parents of a given index.
    private int left(int i) {return 2*i+1;}
    private int right(int i) {return 2*i+2;}
    private int parent(int i) {return (i-1)/2;}

    // Get the size of the min heap.
    public int size() {return size;}

    // Expand or shrink the idea if necessary.
    private void resize() {
        int newSize;
        if (this.size == backingArray.length) {newSize = 2 * backingArray.length;}
        else if (this.size < backingArray.length/2 && backingArray.length > 10) {newSize = backingArray.length/2;}
        else {return;}
        VertexPath[] newBacking = new VertexPath[newSize];
        for (int i = 0; i < size; i++) newBacking[i] = backingArray[i];
        backingArray = newBacking;
    }

    // Add an element to the min heap.
    public void add(VertexPath x) {
        resize();
        backingArray[size] = x;
        int p = parent(size);
        int i = size;
        size += 1;
        System.out.println("I = " + i);
        System.out.println("P = " + p);
        System.out.println("Last Index = " + (size-1));
        while (i > 0 && backingArray[i].compareTo(backingArray[p]) < 0) {
            
            swap(i,p);
            i = p;
            p = parent(i);
        }
    }

    // Remove the smallest element of the 
    public VertexPath remove() {
        resize();
        VertexPath x = backingArray[0];
        backingArray[0] = backingArray[size-1];
        size -= 1;
        int i = 0;
        while (true) {
            if (left(i) >= size && right(i) >= size) {
                System.out.println("Done trickling down because " + backingArray[i] + " has no children.");
                break;
            } // We are done if no children
            // If we only have one child and we're less than that child, we're done
            else if (left(i) >= size && backingArray[i].compareTo(backingArray[right(i)]) < 0) {
                System.out.println("Done trickling down because " + backingArray[i] + " less than its left child " + backingArray[left(i)] + " and it has no right child.");
                break;
            }
            else if (right(i) >= size && backingArray[i].compareTo(backingArray[left(i)]) < 0) {
                System.out.println("Done trickling down because " + backingArray[i] + " less than its left child " + backingArray[left(i)] + " and it has no right child.");
                break;
            }
            // If we're less than both our children, we're done
            else if (backingArray[i].compareTo(backingArray[right(i)]) < 0 && backingArray[i].compareTo(backingArray[left(i)]) < 0) {
                System.out.println("Done trickling down because " + backingArray[i] + " less than its left child " + backingArray[left(i)] + " and less than its right child " + backingArray[right(i)]);
                
                break;
            } 
            // Otherwise, we swap with the our smallest child
            else {
                boolean swapRight = false;
                if (backingArray[right(i)].compareTo(backingArray[left(i)]) < 0) swapRight = true;
                if (swapRight) {
                    System.out.println("Swapped " + backingArray[i] + " at index " + i + " with " + backingArray[right(i)] + " at index " + right(i));
                    swap(i, right(i));
                    i = right(i);
                } else {
                    System.out.println("Swapped " + backingArray[i] + " at index " + i + " with " + backingArray[left(i)] + " at index " + left(i));
                    swap(i, left(i));
                    i = left(i);
                }
            }
        }
        return x;
    }

    // Swap the elements at indices i and j in the backing array.
    private void swap(int i, int j) {
        VertexPath temp = backingArray[i];
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