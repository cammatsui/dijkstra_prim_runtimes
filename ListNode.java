public class ListNode<E> {
    private E contents;
    private ListNode<E> next;
    private ListNode<E> prev;

    public ListNode(E contents) {
        this.contents = contents;
        this.next = null;
        this.prev = null;
    }

    public void setNext(ListNode<E> newNext) {this.next = newNext;}

    public void setPrev(ListNode<E> newPrev) {this.prev = newPrev;}

    public void setContents(E newContents) {this.contents = newContents;}

    public ListNode<E> getNext() {return this.next;}

    public ListNode<E> getPrev() {return this.prev;}

    public E getContents() {return this.contents;}
}