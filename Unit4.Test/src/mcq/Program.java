package mcq;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Program {
    
    public static void mystery(Set<String> set) {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        for(String s : set) {
            String key = s.substring(0,4);
            if (!map.containsKey(key)) {
                map.put(key, 0);
            }
            map.put(key, map.get(key) + 1);
        }
        
        System.out.println(map);
    }
    
    public static Map<String, Integer> mapToNum(Set<String> set) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String s : set) {
            int n = 0;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                n = n * 10 + (c - '0');
            }
            map.put(s, n);
        }
        
        return map;
    }

    public static boolean sameMaps(Map<Integer, String> m1, Map<Integer, String> m2) {
        for(Integer key : m1.keySet()) {
            String v1 = m1.get(key);
            String v2 = m2.get(key);
            if ((v1 == null && v2 != null) || (v1 != null && !v1.equals(v2))) {
                return false;
            }
        }
        return (m1.size() == m2.size());
    }
    
    public static void q3() {
        Set<String> set = new HashSet<String>(Arrays.asList("doggy", "donnor", "donut", "dodge"));
        System.out.println(set);
        mystery(set);
    }
    
    public static void q4() {
        Set<String> set = new HashSet<String>(Arrays.asList(
                "1234", "567", "6587", "98"));
        Map<String, Integer> map = mapToNum(set);
        System.out.println(map);
    }
    
    public static void q5() {
        Map<Integer, String> map1 = new HashMap<Integer, String>();
        map1.put(1, "abc");
        map1.put(2, "def");
        map1.put(3, "xyz");
        Map<Integer, String> map2 = map1;
        System.out.println(sameMaps(map1, map2));       
        map2 = new HashMap<Integer, String>();
        map2.put(1, "abc");
        map2.put(2, "def");
        map2.put(3, "xyz");
        System.out.println(sameMaps(map1, map2));
        map1.put(3, null);
        map2.put(3, null);
        System.out.println(sameMaps(map1, map2));
        map2.put(4, "wut");
        System.out.println(sameMaps(map1, map2));
        map1.put(4, "wut");
        System.out.println(sameMaps(map1, map2));
        map1.put(5, "wut2");
        System.out.println(sameMaps(map1, map2));
        map2.put(5, "wut2");
        System.out.println(sameMaps(map1, map2));
        map2.put(6, "wut3");
        System.out.println(sameMaps(map1, map2));
    }
    
    public static Map<Integer, Set<String>> mystery6(Set<String> set) {
        Map<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
        for (String s : set) {
            int n = 0;
            if (s != null && s.trim().length() > 0) {
                n = s.split(" ").length;
            }
            
            Set<String> group = map.get(n);
            if (group == null) {
                group = new HashSet<String>();
                map.put(n, group);
            }
            group.add(s);
        }
        
        return map;
    }
    
    public static void q6() {
        Set<String> text = new HashSet<String>(Arrays.asList(
                "abc def", "abracadabra",
                "this is it",
                "this is not",
                "this and that",
                "just wrong"));
        Map<Integer, Set<String>> map = mystery6(text);
        System.out.println(map);
        text.add(" ");
        text.add(null);
        text.add("");
        map = mystery6(text);
        System.out.println(map);
        
    }
    
    public static void main(String[] args) {
        //q3();
        //q4();
        //q5();
        q6();
    }

}
