package org.mantoux.util.datastructures;

import java.util.Iterator;
import java.util.Objects;

/**
 * @param <ITEM>
 * @author Alan Mantoux
 */
public class Stack<ITEM> implements Iterable<ITEM> {

  private Node first;
  private int  size;

  public Stack() {
    first = null;
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void push(ITEM element) {
    if (!isEmpty()) {
      Node oldFirst = first;
      first = new Node(element, oldFirst);
    } else {
      addToEmptyList(element);
    }
    size++;
  }

  public ITEM pop() {
    if (!isEmpty()) {
      ITEM popped = first.content;
      first = first.next;
      size--;
      return popped;
    } else {
      return null;
    }
  }

  public int size() {
    return size;
  }

  public ITEM peek() {
    if (first == null)
      return null;
    return first.content;
  }

  private void addToEmptyList(ITEM element) {
    first = new Node(element, null);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Stack))
      return false;

    Stack<?> other = (Stack<?>) o;
    if (first == null && other.first == null)
      return true;
    if (first == null || other.first == null)
      return false;
    if (!Objects.equals(first.content, other.first.content))
      return false;
    if (size != other.size)
      return false;
    Iterator<ITEM> it = iterator();
    Iterator<?> oIt = other.iterator();
    while (it.hasNext()) {
      if (!Objects.equals(it.next(), oIt.next()))
        return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder stackString = new StringBuilder();
    Iterator<ITEM> it = this.iterator();
    stackString.append("Stack elements:\n");
    while (it.hasNext()) {
      stackString.append(it.next().toString());
      stackString.append("\n");
    }
    return stackString.deleteCharAt(stackString.length() - 1).toString();
  }

  @Override
  public Iterator<ITEM> iterator() {
    return new StackIterator();
  }


  private class Node {

    ITEM content;
    Node next;

    private Node(ITEM content, Node next) {
      this.content = content;
      this.next = next;
    }

  }


  private class StackIterator implements Iterator<ITEM> {

    private Node currentNode = first;

    @Override
    public boolean hasNext() {
      return currentNode != null;
    }

    @Override
    public ITEM next() {
      ITEM next = currentNode.content;
      currentNode = currentNode.next;
      return next;
    }

  }

}
