// The list interface.
public interface List<E> {
    public int size();
    public E get(int i);
    public void set(int i, E x);
    public void add(int i, E x);
    public void remove(int i);
}