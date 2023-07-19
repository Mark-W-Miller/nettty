package com.moondance.nettty.utils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Handy {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static boolean debug = false ;
	static public void toggle(){debug = !debug;}
	static public void trace(Object s) {if(debug) System.out.println(ANSI_YELLOW + s.toString());}
	static public void dout(Object s) {if(debug) System.out.println(ANSI_GREEN + s.toString());}
	static public void out(Object s) {System.out.println(ANSI_RESET + s.toString());}
	static public void err(Object s) {
        System.out.println(ANSI_RED + s.toString());
    }

    static public void warn(Object s) {
        System.out.println(ANSI_YELLOW + s.toString());
    }

	static public String divider(String title){
		return ("------ " + title + " -----------------------------------------------------------");
	}
	static public String divider(String prefix,String title){
		return (prefix+ "------ " + title + " -----------------------------------------------------------");
	}
	static public String tabs(int tabs) {
		char tab[] = new char[tabs];
		Arrays.fill(tab,'\t');
		return new String(tab);
	}

	public static String stripQuotes(String line){
		line = line.trim();
		if(line.isEmpty())
			return line;
		if(line.charAt(0) == '"'){
			line = line.substring(1);
		}
		if(line.isEmpty()){
			return line ;
		}
		if(line.charAt(line.length()-1) == '"'){
			line = line.substring(0,line.length()-1);
		}
		return line ;
	}

	static public String empty(Object v){ return v==null ? "" : v.toString() + " ";}
	static public String quote(String v){ return v.trim().isEmpty() ? "" : "'" + v.trim() + "'";}

	static public int parseInt(String token){
		if(token.trim().isEmpty()){
			return 1;
		} else if(token.trim().equals(".")){
			return 1000000;
		} else {
			return Integer.parseInt(token);
		}
	}

	static public Point parsePoint(String line){
		String bits[] = line.split(",");
		int x = parseInt(bits[0]);
		int y = parseInt((bits[1]));
		return new Point(x,y);
	}

	public static <E extends Enum <E>> E find(Class<E> elemType, String list) {
		for (E e : java.util.EnumSet.allOf(elemType)) {
			if(Pattern.compile(",")
					.splitAsStream(list)
					.filter(s->s.equals(e.name()))
							.findFirst().isPresent()){
				return e ;
			}
		}
		return null ;
	}

	public static <E extends Enum <E>> E set(E value, E defaultValue) {
		return (value != null) ? value : defaultValue ;
	}

	public static void pause(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static String point2Str(Point d){
		return d.x + "," + d.y;
	}

	public static Point addPoint(Point d1, Point d2){
		return new Point(d1.x + d2.x, d1.y + d2.y);
	}

	public static Point scalePoint(Point d1, double scale){
		if(d1 != null) {
			return new Point((int) (d1.x * scale), (int) (d1.y * scale));
		} else {
			return new Point();
		}
	}

	public static Point fromScreen(Point d1, double scale){
		return scalePoint(d1,1/scale);
	}

	public static Point centerOf(Point d){
		return new Point(d.x/2,d.y/2);
	}

	public static Point getCenteredOffset(Point source, Point destination){
		int widthOff = (destination.x - source.x)/2;
		int heightOff = (destination.y - source.y)/2;
		return new Point(widthOff,heightOff);
	}

	public static Point imageSize(Image image){
		return new Point(image.getWidth(null),image.getHeight(null));
	}

	public static String splitCamelCase(String s) {
		return s.replaceAll(
				String.format("%s|%s|%s",
						"(?<=[A-Z])(?=[A-Z][a-z])",
						"(?<=[^A-Z])(?=[A-Z])",
						"(?<=[A-Za-z])(?=[^A-Za-z])"
				),
				" "
		);
	}

	public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
		if (c != null && string != null) {
			try {
				return Enum.valueOf(c, string.trim());
			} catch (IllegalArgumentException ex) {
				err(c.getName() + ": Bad Enum Value:" + string);
			}
		}
		return null;
	}

	public static <T> String formatList(List<T> list) {
		String result = list.stream().map(o -> o.toString() + "\n").collect(Collectors.joining());
		return result;
	}

	public static <K, V extends Map> String formatMapOfMaps(Map<K, V> map, int tabs) {

		String t = tabs(tabs);
		StringBuilder b = new StringBuilder();
		map.entrySet().stream().forEach(e ->
				b.append(t + e.getKey() + "\n")
						.append(formatMap(e.getValue(), tabs + 1) + "\n"));
		return b.toString();
	}

	public static <K,V> String formatMap(Map<K,V> map, int tabs){

		String t = tabs(tabs);
		StringBuilder b = new StringBuilder();
		map.entrySet().stream().forEach(e->
				b.append(t + e.getKey() +"\n")
						.append(tabs(tabs+1) + e.getValue()+"\n") );
		return b.toString();
	}

	public static <X, Y, Z> Map<X, Z> transform(Map<X, Y> input,
												Function<Y, Z> function) {
		return input
				.entrySet()
				.stream()
				.collect(
						Collectors.toMap((entry) -> entry.getKey(),
								(entry) -> function.apply(entry.getValue())));
	}

	public static String cavernId(String id) {
		if (id.contains(".")) {

			return id.substring(0, id.indexOf("."));
		} else {

			return id;
		}
	}

    public static String posId(String id) {
        if (id.contains(".")) {

            return id.substring(id.indexOf(".") + 1);
        } else {

            return id;
        }
    }

    public static List<Character> convertStringToCharList(String str) {
        return new AbstractList<Character>() {

            @Override
            public Character get(int index) {
                return str.charAt(index);
            }

            @Override
            public int size() {
                return str.length();
            }
        };
    }
}
