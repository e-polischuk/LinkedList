package ua.itea.linklist;

public class Iterator<E> {
    private LinkedList<E> list;
    private int index;

    private Node<E> previous;
    private Node<E> next;

    Iterator(LinkedList<E> list) {
	this.list = list;
	next = list.getFirst();

    }

    public boolean hasNext() {
	if (next != null)
	    return true;
	else
	    return false;
    }

    public boolean hasPrevious() {
	if (previous != null)
	    return true;
	else
	    return false;
    }

    public Node<E> next() {
	if (next == null)
	    return null;
	previous = next;
	next = next.getNext();
	index++;
	return previous;
    }

    public Node<E> previous() {
	if (previous == null)
	    return null;
	next = previous;
	previous = previous.getPrevious();
	index--;
	return next;
    }

    public Node<E> restart() {
	previous = null;
	next = list.getFirst();
	index = 0;
	return next;
    }

    public Node<E> getPrevious() {
	return previous;
    }

    public void setPrevious(Node<E> previous) {
	this.previous = previous;
    }

    public Node<E> getNext() {
	return next;
    }

    public void setNext(Node<E> next) {
	this.next = next;
    }

    public int getIndex() {
	return index;
    }

}
