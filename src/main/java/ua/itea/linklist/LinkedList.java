package ua.itea.linklist;

import java.util.ConcurrentModificationException;

public class LinkedList<E> {
    private int size;

    private Node<E> first;
    private Node<E> last;

    private Iterator<E> iterator;

    public void add(E element, int index) {
	if (iterator != null)
	    throw new ConcurrentModificationException();
	if (index > size || index < 0)
	    throw new IndexOutOfBoundsException();
	iterator = getIterator();
	while (iterator.getIndex() < index)
	    iterator.next();
	Node<E> current = new Node<E>(element);
	if (size == 0) {
	    first = current;
	    last = current;
	    iterator.setNext(current);
	    iterator.next();
	} else if (index == 0) {
	    current.setNext(first);
	    first.setPrevious(current);
	    first = current;
	    iterator.setNext(current);
	    iterator.next();
	} else if (index < size) {
	    Node<E> previous = iterator.getPrevious();
	    Node<E> next = iterator.getNext();

	    previous.setNext(current);
	    current.setPrevious(previous);
	    current.setNext(next);
	    next.setPrevious(current);

	    iterator.setNext(current);
	    iterator.next();
	} else {
	    last.setNext(current);
	    current.setPrevious(last);
	    last = current;
	    iterator.setNext(current);
	    iterator.next();
	}
	size++;
	iterator = null;
    }

    public void addFirst(E element) {
	if (iterator != null)
	    throw new ConcurrentModificationException();
	Node<E> current = new Node<E>(element);
	if (first == null) {
	    first = current;
	    last = current;
	} else {
	    current.setNext(first);
	    first.setPrevious(current);
	    first = current;
	}
	size++;
	iterator = null;
    }

    public void addLast(E element) {
	if (iterator != null)
	    throw new ConcurrentModificationException();
	Node<E> current = new Node<E>(element);
	if (last == null) {
	    first = current;
	    last = current;
	} else {
	    last.setNext(current);
	    current.setPrevious(last);
	    last = current;
	}
	size++;
	iterator = null;
    }

    public E remove(int index) {
	if (iterator != null)
	    throw new ConcurrentModificationException();
	if (index >= size || index < 0)
	    throw new IndexOutOfBoundsException();
	iterator = getIterator();
	while (iterator.getIndex() < index)
	    iterator.next();
	E element = null;
	if (size == 0) {
	    iterator = null;
	    return null;
	} else if (size == 1) {
	    element = first.getObject();
	    first = null;
	    last = null;
	    iterator.setNext(null);
	} else if (index == 0) {
	    element = first.getObject();
	    first = first.getNext();
	    first.setPrevious(null);
	    iterator.setNext(first);
	} else if (index < size - 1) {
	    Node<E> previous = iterator.getPrevious();
	    Node<E> next = iterator.getNext();
	    element = next.getObject();

	    previous.setNext(next.getNext());
	    next.getNext().setPrevious(previous);
	    iterator.setNext(next.getNext());

	    next.setNext(null);
	    next.setPrevious(null);
	} else {
	    element = last.getObject();
	    last.getPrevious().setNext(null);
	    last.setPrevious(null);
	    iterator.setNext(null);
	}
	size--;
	iterator = null;
	return element;
    }

    public E get(int index) {
	if (index >= size || index < 0)
	    throw new IndexOutOfBoundsException();
	Iterator<E> iterator = getIterator();
	while (iterator.getIndex() < index)
	    iterator.next();
	return iterator.getNext().getObject();
    }

    public Iterator<E> getIterator() {
	return new Iterator<E>(this);
    }

    public int getSize() {
	return size;
    }

    public Node<E> getFirst() {
	return first;
    }

    public void setFirst(Node<E> first) {
	this.first = first;
    }

    public Node<E> getLast() {
	return last;
    }

    public void setLast(Node<E> last) {
	this.last = last;
    }

    public String toString() {
	StringBuffer sb = new StringBuffer("{");
	Iterator<E> iterator = getIterator();
	while (iterator.hasNext()) {
	    sb.append(iterator.next().getObject().toString() + ", ");
	}
	int start = sb.lastIndexOf(",");
	sb.replace(start, start + 1, "}");
	return sb.toString();
    }

}
