package com.moondance.nettty.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("serial")
public class MapOfLists<K, V> extends LinkedHashMap<K, List<V>> {

	public MapOfLists() {
		super();
	}
	
	public MapOfLists(Map<? extends K, ? extends List<V>> m) {
		super(m);
	}
	/**
	 * Looks for a list that is mapped to the given key. If there is not one then a
	 * new one is created mapped and has the value added to it.
	 * 
	 * @param key
	 * @param value
	 * @return true if the list has already been created, false if a new list is
	 *         created.
	 */
	
	public boolean putOne(K key, V value) {
		if (this.containsKey(key)) {
			this.get(key).add(value);
			return true;
		} else {
			List<V> values = new ArrayList<>();
			values.add(value);
			this.put(key, values);
			return false;
		}
	}
		
	public boolean putKey(K key) {
		if (this.containsKey(key)) {
			return true;
		} else {
			List<V> values = new ArrayList<>();
			this.put(key, values);
			return false;
		}
	}

	public List<Integer> sizeMap(){
		return values().stream().map(List::size).collect(Collectors.toList());
	}
	public boolean putOneUnique(K key, V value) {
		if (this.containsKey(key)) {
			if(!this.get(key).contains(value))
				this.get(key).add(value);
			return true;
		} else {
			List<V> values = new ArrayList<>();
			values.add(value);
			this.put(key, values);
			return false;
		}
	}
	
	public boolean putAll(K key, List<V> values) {
		if (this.containsKey(key)) {
			this.get(key).addAll(values);
			return true;
		} else {
			this.put(key, values);
			return false;
		}
	}
	
	public MapOfLists<K, V> transform(Consumer<? super List<V>> mapper) {
		for(K key: keySet()) {
			List<V> list = get(key);
			if(list != null) {
				mapper.accept(list);
			}
		}
		return this;
	}
	
	public List<V> allValues(){
		return values().stream()
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}
	
	public List<V> allValues(List<K> keyOrder){
		return keyOrder.stream()
				.map(key->this.get(key))
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}
	
	public List<V> allValuesReversed(List<K> keyOrder){
		List<V> finalList = new ArrayList<>();
		for(K key: keyOrder) {
			List<V> list = new ArrayList<>(this.get(key)) ;
			Collections.reverse(list);
			finalList.addAll(list);
		}
		return finalList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortAllLists() {
		 values().stream().forEach(list->{
			List listV = list;
			 Collections.sort(listV);
		 });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortAllLists(Comparator<V> comparator) {
		 values().stream().forEach(list->{
			 List listV = list;
			 if(list.size()>1) {
				 listV.sort(comparator);
			 }
		 });
	}
	
	@SuppressWarnings("unchecked")
	public void ensure(K... keys){
		
		for(K key: keys) {
			if(!containsKey(key)) {
				put(key,new ArrayList<V>());				
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(" MOL:\n\t");
		this.entrySet().stream().forEach(entry -> {
			builder.append("\n").append(entry.getKey());
			builder.append(dumpList(entry.getValue()));
		});
		return builder.toString();
	}
	
	@SuppressWarnings("unchecked")
	public String toString(K... keys) {
		StringBuilder builder = new StringBuilder(" MOL (Partial):" + Arrays.asList(keys).toString() + "\n\t");
		this.entrySet().stream().forEach(entry -> {
			
			if(Arrays.asList(keys).stream().anyMatch(k->entry.getKey().toString().contains(k.toString()))){
				builder.append("\n").append(entry.getKey());
				builder.append(dumpList(entry.getValue()));
			}
		});
		return builder.toString();
		
	}
	
	public String toStringListInteresting(String[] keys) {
		StringBuilder builder = new StringBuilder("\n\tMOL (Partial):" + Arrays.asList(keys).toString() + "\n\t");
		this.entrySet().stream().forEach(entry -> {
//			builder.append(ModelDebug.dumpList(entry.getKey() + "=",entry.getValue(), keys));
		});
		return builder.toString();
		
	}

	private <T> String dumpList(List<T> list) {
		StringBuilder builder = new StringBuilder("=");
		list.stream().forEach(item->builder.append((item instanceof Integer) ? "," : "\n\t").append(item));
		return builder.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V> get(Object key) {
		if(!containsKey(key)) {
			List<V> list = new ArrayList<>();
			put((K) key,list);
			return list ;
		}
		return super.get(key);
	}

	public void populateEmpty(K key, int size) {
		List<V> list = new ArrayList<>(size);
		IntStream.range(0, size).forEach(i->list.add(null));
		put(key,list);
	}
	
	public static MapOfLists<String,String> fromStringMap(Map<String, String[]> map){
		MapOfLists<String,String> ret = new MapOfLists<>();
		if(map != null) {
			map.entrySet().stream().forEach(e->{
				if(e.getValue() != null) {
					ret.put(e.getKey(), Arrays.asList(e.getValue()));
				} else {
					ret.put(e.getKey(), new ArrayList<>());				
				}
			});
		}
		return ret ;
	}
	
	public static Map<String,String[]> toStringMap(MapOfLists<String, String> map){
		Map<String,String[]> ret = new LinkedHashMap<>();
		map.entrySet().stream().forEach(e->{
			if(e.getValue() != null) {
				ret.put(e.getKey(), e.getValue().toArray(new String[0]));
			} else {
				ret.put(e.getKey(), new String[0]);				
			}
		});
		return ret ;
	}
}