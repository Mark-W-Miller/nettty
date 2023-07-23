package com.moondance.nettty.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Wraps a Map&lt;S, List&lt;T&gt;&gt; with various convenience methods for
 * accessing the data. Implements a Map-like interface for easier transition.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 * @author chris
 */
public class MapOfLists<K,V>{

    /**
     * Our internal map.
     */
    protected final SortedMap<K, List<V>> map;

    /**
     * Creates a new, empty MapList.
     */
    public MapOfLists() {
        map = new TreeMap<>();
    }

    /**
     * Creates a new MapList with the values from the specified list.
     *
     * @param list The MapList whose values should be used
     */
    public MapOfLists(final MapOfLists<K, V> list) {
        map = list.map;
    }

    /**
     * Determines if this MapList is empty. An empty MapList is one that either
     * contains no keys, or contains only keys which have no associated values.
     *
     * @return True if this MapList is empty, false otherwise
     */
    public boolean isEmpty() {
        for (List<V> list : map.values()) {
            if (!list.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Determines if this MapList contains the specified key.
     *
     * @param key The key to look for
     * @return True if this MapList contains the specified key, false otherwise
     */
    public boolean containsKey(final K key) {
        return map.containsKey(key);
    }

    /**
     * Determines if this MapList contains the specified value as a child of
     * the specified key.
     *
     * @param key   The key to search under
     * @param value The value to look for
     * @return True if this MapList contains the specified key/value pair,
     * false otherwise
     */
    public boolean containsValue(final K key, final V value) {
        return map.containsKey(key) && map.get(key).contains(value);
    }

    /**
     * Retrieves the list of values associated with the specified key.
     *
     * @param key The key whose values are being retrieved
     * @return The values belonging to the specified key
     */
//    public List<T> get(final S key) {
//        return map.get(key);
//    }

//    public Entry<S,List<T>> entries(){
//    	return map.e
//    }

    /**
     * Retrieves the value at the specified offset of the specified key.
     *
     * @param key   The key whose values are being retrieved
     * @param index The index of the value to retrieve
     * @return The specified value of the key
     */
    public V get(final K key, final int index) {
        return map.get(key).get(index);
    }

    /**
     * Retrieves the list of values associated with the specified key, creating
     * the key if neccessary.
     *
     * @param key The key to retrieve
     * @return A list of the specified key's values
     */
    public List<V> safeGet(final K key) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<V>());
        }

        return map.get(key);
    }

    /**
     * Adds the specified key to the MapList.
     *
     * @param key The key to be added
     */
    public void add(final K key) {
        safeGet(key);
    }

    /**
     * Adds the specified value as a child of the specified key. If the key
     * didn't previous exist, it is created.
     *
     * @param key   The key to which the value is being added
     * @param value The value to be added
     */
    public void add(final K key, final V value) {
        safeGet(key).add(value);
    }

    /**
     * Adds the specified set of values to the specified key. If the key
     * didn't previous exist, it is created.
     *
     * @param key    The key to which the value is being added
     * @param values The values to be added
     */
    public void add(final K key, final Collection<V> values) {
        safeGet(key).addAll(values);
    }

    /**
     * Adds everything in one MapList to this. If the key
     * didn't previous exist, it is created.
     *
     * @param mapOfLists The mapList to add
     */
    public void add(MapOfLists<K, V> mapOfLists) {
        mapOfLists.getMap()
                .entrySet()
                .stream().forEach(e -> {
            add(e.getKey(), e.getValue());
        });
    }

    /**
     * Removes the specified key and all of its values.
     *
     * @param key The key to removeCard
     */
    public void remove(final K key) {
        map.remove(key);
    }

    /**
     * Removes the specified value from all keys.
     *
     * @param value The value to removeCard
     */
    public void removeFromAll(final V value) {
        for (List<V> list : map.values()) {
            list.remove(value);
        }
    }

    public K findKey(V value) {

        for (Map.Entry<K, List<V>> e : entrySet()) {
            if (e.getValue().contains(value)) {
                return e.getKey();
            }
        }
        ;
        return null;
    }

    /**
     * Removes the specified value from the specified key.
     *
     * @param key   The key whose value is being removed
     * @param value The value to be removed
     */
    public void remove(final K key, final V value) {
        if (map.containsKey(key)) {
            map.get(key).remove(value);
        }
    }

    /**
     * Entirely clears this MapList.
     */
    public void clear() {
        map.clear();
    }

    /**
     * Clears all values of the specified key.
     *
     * @param key The key to be cleared
     */
    public void clear(final K key) {
        safeGet(key).clear();
    }

    /**
     * Returns the set of all keys belonging to this MapList.
     *
     * @return This MapList's keyset
     */
    public Set<K> keySet() {
        return map.keySet();
    }

    /**
     * Returns a collection of all values belonging to the specified key.
     *
     * @param key The key whose values are being sought
     * @return A collection of values belonging to the key
     */
    public Collection<V> values(final K key) {
        return map.get(key);
    }

    /**
     * Retrieves the entry set for this MapList.
     *
     * @return This MapList's entry set
     */
    public Set<Map.Entry<K, List<V>>> entrySet() {
        return map.entrySet();
    }

    /**
     * Retrieves the map behind this maplist.
     *
     * @return This MapList's map.
     */
    public SortedMap<K, List<V>> getMap() {
        return map;
    }

    @Override
    public String toString() {
        return "[" + map + "]";
    }

    public int size() {
        return map.size() ;
    }
    public String format(String title, int tabs) {
        String kv = "%s = %s\n";
        String t = tabs(tabs);
        StringBuilder builder = new StringBuilder(title + "\n");

        map.entrySet().forEach(e -> {
            if (!e.getValue().isEmpty()) {
                builder.append(t + e.getKey()).append("\n");
                builder.append(formatList(e.getValue(), tabs));
            }
        });
        return builder.toString();
    }
    static public String tabs(int tabs) {
        char tab[] = new char[tabs];
        Arrays.fill(tab,'\t');
        return new String(tab);
    }

    private String formatList(List<V> list, int tabs) {
        String t = tabs(tabs + 1);
        StringBuilder builder = new StringBuilder();
        list.forEach(s -> {
            builder.append(t + s.toString()).append("\n");
        });
        return builder.toString();
    }


    public List<V> getAllValues() {
        return getMap()
                .values()
                .stream()
                .flatMap(List::stream)
                .sorted()
                .collect(Collectors.toList());
    }

    public void removeSmallerThan(int minSize) {
        List<K> remove = map.keySet().stream()
                .filter(s -> map.get(s).size() < minSize)
                .collect(Collectors.toList());
        remove.stream()
                .forEach(s -> map.remove(s));
    }


//    @AllArgsConstructor
//    @Getter
//    public class KV implements Comparable<KV> {
//        K key;
//        List<V> value;
//
//        @Override
//        public int compareTo(KV o) {
//            return value.size() - o.value.size();
//        }
//    }
}