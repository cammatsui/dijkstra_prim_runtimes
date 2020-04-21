public class MinHeap<E extends Comparable<E>> {
    private E[] backingArray;
    private int size;

    public MinHeap() {
        this.size = 0;
        this.backingArray = (E[])new Comparable[10];
    }

    private int left(int i) {return 2*i+1;}
    private int right(int i) {return 2*i+2;}
    private int parent(int i) {return (i-1)/2;}

    private void resize() {
        int newSize;
        if (this.size == backingArray.length) {newSize = 2 * backingArray.length;}
        else if (this.size < backingArray.length/2 && backingArray.length > 10) {newSize = backingArray.length/2;}
        else {return;}
        E[] newBacking = (E[])new Comparable[newSize];
        for (int i = 0; i < size; i++) newBacking[i] = backingArray[i];
        backingArray = newBacking;
    }

    public void add(E x) {
        resize();
        backingArray[size+1] = x;
        int p = parent(size);
        int i = size;
        size += 1;
        while (i > 0 && backingArray[i].compareTo(backingArray[p]) < 0) {
            swap(i,p);
            i = p;
            p = parent(i);
        }
    }

    public E remove() {
        resize();
        E x = backingArray[0];
        backingArray[0] = backingArray[size-1];
        size -= 1;
        int i = 0;
        while (true) {
            if (left(i) >= size && right(i) >= size) {break;} // We are done if no children
            // If we only have one child and we're less than that child, we're done
            else if (left(i) >= size && backingArray[i].compareTo(backingArray[right(i)]) < 0) {break;}
            else if (right(i) >= size && backingArray[i].compareTo(backingArray[left(i)]) < 0) {break;}
            // If we're less than both our children, we're done
            else if (backingArray[i].compareTo(backingArray[right(i)]) < 0 && backingArray[i].compareTo(backingArray[left(i)]) < 0) {break;}
            // Otherwise, we swap with the our smallest child
            else {
                boolean swapRight = false;
                if (backingArray[right(i)].compareTo(backingArray[right(i)]) < 0) swapRight = true;
                if (swapRight) {
                    swap(i, right(i));
                    i = right(i);
                } else {
                    swap(i, left(i));
                    i = left(i);
                }
            }
        }
        return x;
    }

    private void swap(int i, int j) {
        E temp = backingArray[i];
        backingArray[i] = backingArray[j];
        backingArray[j] = temp;
    }

    public void print() {
        String str = "[";
        for (int i = 0; i < this.size-1; i++) {
            str += backingArray[i] + ", ";
        }
        str += backingArray[size-1] + "]";
        System.out.println(str);
    }

}