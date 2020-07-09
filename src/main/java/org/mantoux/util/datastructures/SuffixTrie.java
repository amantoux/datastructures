package org.mantoux.util.datastructures;

import java.util.Collection;

public class SuffixTrie<V> {

  private final AccumTST<V> tst;

  public SuffixTrie() {
    super();
    tst = new AccumTST<>();
  }

  public void put(String key, V val) {
    String[] suffices = buildSuffices(key);
    for (int i = 0; i < suffices.length - 1; i++) {
      tst.put(suffices[i], val);
    }
  }

  public void remove(String key, V val) {
    String[] suffices = buildSuffices(key);
    for (int i = 0; i < suffices.length - 1; i++) {
      tst.delete(suffices[i], val);
    }
  }

  public Collection<V> get(String query) {
    return tst.valuesWithPrefix(query);
  }

  private String[] buildSuffices(String text) {
    String[] res = new String[text.length() + 1];
    StringBuilder sb = new StringBuilder(text);
    res[0] = sb.toString();
    int index = 1;
    while (sb.length() > 0) {
      res[index++] = sb.deleteCharAt(0).toString();
    }
    return res;
  }
}
