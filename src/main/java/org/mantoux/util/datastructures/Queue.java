package org.mantoux.util.datastructures;

import java.util.Iterator;

/**
 * @param <Item>
 * @author Alan Mantoux
 */
public class Queue<Item> implements Iterable<Item> {

  private Node first = null;
  private Node last  = null;
  private int  size  = 0;

  public boolean isEmpty() {
    return size == 0;
  }

  public void enqueue(Item element) {
    if (!isEmpty()) {
      last.next = new Node(element, null);
      last = last.next;
    } else {
      addToEmptyList(element);
    }
    size++;
  }

  public Item dequeue() {
    if (!isEmpty()) {
      Item element = first.content;
      first = first.next;
      size--;
      return element;
    } else {
      return null;
    }
  }

  public int size() {
    return size;
  }

  private void addToEmptyList(Item element) {
    first = new Node(element, null);
    last = first;
  }

  public Iterator<Item> iterator() {
    return new QueueIterator();
  }


  private class Node {

    Item content;
    Node next;

    private Node(Item content, Node next) {
      this.content = content;
      this.next = next;
    }

  }


  private class QueueIterator implements Iterator<Item> {

    Node currentNode;

    private QueueIterator() {
      currentNode = first;
    }

    public boolean hasNext() {
      return currentNode != null;
    }

    public Item next() {
      Item next = currentNode.content;
      currentNode = currentNode.next;
      return next;
    }

  }

}
