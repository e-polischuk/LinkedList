package ua.itea.linklist;

public class Node<E> {
    private E element;

    private Node<E> previous;
    private Node<E> next;

    Node(E element) {
	this.element = element;
    }

    E getObject() {
	return element;
    }

    Node<E> getPrevious() {
	return previous;
    }

    Node<E> getNext() {
	return next;
    }

    void setPrevious(Node<E> previous) {
	this.previous = previous;
    }

    void setNext(Node<E> next) {
	this.next = next;
    }

}
