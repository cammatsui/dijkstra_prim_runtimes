// An object for a node in a linked list.
public class ListNode<E> {

    // Since we're using a doubly linked list, each node points to a 
    //      previous and next node.
    private E contents;
    private ListNode<E> next;
    private ListNode<E> prev;

    public ListNode(E contents) {
        this.contents = contents;
        this.next = null;
        this.prev = null;
    }

    // Getters and setters. O(1).
    public void setNext(ListNode<E> newNext) {this.next = newNext;}
    public void setPrev(ListNode<E> newPrev) {this.prev = newPrev;}
    public void setContents(E newContents) {this.contents = newContents;}
    public ListNode<E> getNext() {return this.next;}
    public ListNode<E> getPrev() {return this.prev;}
    public E getContents() {return this.contents;}
    
}