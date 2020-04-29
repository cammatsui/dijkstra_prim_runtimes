public class MyList<E extends Comparable<E>> implements List<E> {

    private int size;
    private ListNode<E> first;
    private ListNode<E> last;

    public MyList() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    // Return the number of elements in the list O(1).
    public int size() {return this.size;}

    // Return the element at index i in the list. O(n).
    public E get(int i) {
        if (i >= this.size || i < 0) {throwIndexException(i);}
        return getNode(i).getContents();
    }
    
    // Set the element at index i to x O(n).
    public void set(int i, E x) {
        if (i >= this.size || i < 0) {throwIndexException(i);}
        getNode(i).setContents(x);
    }

    // Add a new element at index i; push the previous element at index i back. O(n).
    public void add(int i, E x) {
        if (i >= this.size || i < 0) {throwIndexException(i);}
        ListNode<E> toDisplace = getNode(i);
        ListNode<E> toInsert = new ListNode<>(x);
        if (toDisplace == first) {
            toDisplace.setPrev(toInsert);
            toInsert.setNext(toDisplace);
            first = toInsert;
        } else {
            ListNode<E> temp = toDisplace.getPrev();
            temp.setNext(toInsert);
            toDisplace.setPrev(toInsert);
            toInsert.setNext(toDisplace);
            toInsert.setPrev(temp);
        }
        this.size += 1;
    }

    // Remove (and do not return) the element at index i. O(n).
    public void remove(int i) {
        if (i >= this.size || i < 0) {throwIndexException(i);}
        pop(i);
    }

    // Remove the node with value x if it is in the list. Otherwise, do nothing. O(n).
    public void removeValue(E x) {
        int i = index(x);
        if (i != -1) {
            remove(i);
        }
    }

    // Returns the index of the first element equal to x. If x is not in the list, returns -1. O(n).
    public int index(E x) {
        ListNode<E> temp = this.first;
        for (int i = 0; i < this.size; i++) {
            if (temp.getContents().equals(x)) {
                return i;
            }
            temp = temp.getNext();
        }
        return -1;
    }

    // Add an element x to the end of the list. O(1).
    public void append(E x) {
        ListNode<E> toAppend = new ListNode<E>(x);
        if(this.size == 0) {
            this.first = toAppend;
        } else {
            toAppend.setPrev(this.last);
            this.last.setNext(toAppend);
        }
        this.last = toAppend;
        this.size += 1;
    }

    // Remove and return the element at index i. O(n).
    public E pop(int i) {
        if (i >= this.size || i < 0) {throwIndexException(i);}
        ListNode<E> toPop = getNode(i);
        return pop(toPop);
    }

    // Remove the given node and return its contents. O(n).
    public E pop(ListNode<E> toPop) {
        if (toPop == this.first) {
            ListNode<E> newFirst = toPop.getNext();
            if (newFirst != null) newFirst.setPrev(null);
            toPop.setNext(null);
            this.first = newFirst;
        } else if (toPop == this.last) {
            ListNode<E> newLast = toPop.getPrev();
            newLast.setNext(null);
            toPop.setPrev(null);
            this.last = newLast;
        } else {
            toPop.getPrev().setNext(toPop.getNext());
            toPop.getNext().setPrev(toPop.getPrev());
            toPop.setNext(null);
            toPop.setPrev(null);
        }
        size -= 1;
        return toPop.getContents();
    }

    // Remove and return the last element in the list. O(n).
    public E pop() {
        return this.pop(this.size-1);
    }

    // Throw an out of bounds exception for my list.
    private void throwIndexException(int i) {
        throw new IndexOutOfBoundsException("Index " + i + " is out of bounds in list of size " + size + ".");
    }

    // return the ListNode<E> at a given index i. O(n).
    private ListNode<E> getNode(int i) {
        if (i == 0) return this.first;
        if (i == this.size-1) return this.last;
        if (i < size/2) {
            ListNode<E> temp = this.first;
            for (int c = 0; c < i; c++) temp = temp.getNext();
            return temp;
        } else {
            ListNode<E> temp = this.last;
            for (int c = this.size-1; c > i; c--) temp = temp.getPrev();
            return temp;
        }
    }

    // Returns a string representing the list, e.g. "[1,2,3]".
    public String toString() {
        if (this.size == 0) return "[]";
        String str = "[";
        ListNode<E> temp = this.first;
        for(int c = 0; c < this.size-1; c++) {
            str += temp.getContents() + ", ";
            temp = temp.getNext();
        }
        str += get(this.size-1) + "]";
        return str;
    }

    // Returns true if the given element is in the list; false otherwise. O(n).
    public boolean in(E x) {
        if (index(x) == -1) return false;
        return true;
    }
}