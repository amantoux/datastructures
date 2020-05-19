package org.mantoux.util.datastructures;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Ternary search tries that accumulates values in a list if they have the same key
 *
 * @param <V> the data type
 * @author Alan Mantoux
 */
public class AccumTST<V> {

  private Node<V> root;

  public void put(String key, V val) {
    key = key.toLowerCase();
    root = put(root, key, val, 0);
  }

  public Set<V> get(String key) {
    String lkey = key.toLowerCase();
    Node<V> x = get(root, lkey, 0);
    if (x == null)
      return Collections.emptySet();
    return x.val;
  }

  public boolean contains(String key) {
    return get(key) != null;
  }

  public void delete(String key, V val) {
    Node<V> x = get(root, key, 0);
    if (x != null) {
      x.val.remove(val);
      if (x.val.isEmpty())
        x.val = null;
    }
  }

  public Iterable<V> valuesWithPrefix(String prefix) {
    // Using Set to avoid duplicates in result
    Set<V> set = new HashSet<>();
    Node<V> x = get(root, prefix.toLowerCase(), 0);
    if (x == null)
      return set;
    if (x.val != null)
      set.addAll(x.val);
    collect(x.mid, new StringBuilder(prefix), set);
    return set;
  }

  private Node<V> put(Node<V> x, String key, V val, int d) {
    char c = key.charAt(d);
    if (x == null) {
      x = new Node<>();
      x.c = c;
    }
    if (c < x.c)
      x.left = put(x.left, key, val, d);
    else if (c > x.c)
      x.right = put(x.right, key, val, d);
    else if (d < key.length() - 1)
      x.mid = put(x.mid, key, val, d + 1);
    else {
      if (x.val == null)
        x.val = new HashSet<>();
      x.val.add(val);
    }
    return x;
  }

  private Node<V> get(Node<V> x, String key, int d) {
    char c = key.charAt(d);
    if (x == null)
      return null;
    if (c < x.c)
      return get(x.left, key, d);
    else if (c > x.c)
      return get(x.right, key, d);
    else if (d < key.length() - 1)
      return get(x.mid, key, d + 1);
    else
      return x;
  }

  private void collect(Node<V> x, StringBuilder prefix, Set<V> l) {
    if (x == null)
      return;
    collect(x.left, prefix, l);
    if (x.val != null)
      l.addAll(x.val);
    collect(x.mid, prefix.append(x.c), l);
    prefix.deleteCharAt(prefix.length() - 1);
    collect(x.right, prefix, l);
  }


  private static class Node<V> {
    private Set<V>  val;
    private char    c;
    private Node<V> left, mid, right;
  }

  Iterable<V> keys() {
    return null;
  }



}
