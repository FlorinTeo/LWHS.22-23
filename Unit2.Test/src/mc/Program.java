package mc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Program {

    public static void q2(String label) {
        System.out.printf("\n----%s----\n", label);
        Stack<String> s = new Stack<String>();
        s.push("A");
        s.push("B");
        System.out.print(s.peek());
        s.push("C");
        System.out.print(s.peek());
        System.out.print(s.pop());
        s.push("D");
        System.out.print(s.pop());
        System.out.print(s.peek());
        System.out.print(" : " + s.size());
    }
    
    public static void q3(String label) {
        System.out.printf("\n----%s----\n", label);
        Queue<String> q = new LinkedList<String>();
        q.add("A");
        q.add("B");
        System.out.print(q.peek());
        q.add("C");
        System.out.print(q.peek());
        System.out.print(q.remove());
        q.add("D");
        System.out.print(q.remove());
        System.out.print(q.peek());
        System.out.print(" : " + q.size());
    }
    
    public static String mysteryS(String text) {
        System.out.printf("\n----%s----\n", text);
        text = "Some Text";
        Stack<Character> s = new Stack<Character>();
        for (char c : text.toCharArray()) {
            s.push(Character.toUpperCase(c));
        }
        String output = "";
        while (!s.empty()) {
            output += s.pop();
        }
        System.out.println(output);
        return output;
    }
    
    public static void q5(String text) {
        System.out.printf("\n----%s----\n", text);
        text = "This is a puzzle!";
        Queue<String> q = new LinkedList<String>();
        Stack<Character> s = new Stack<Character>();
        for (char c : text.toCharArray()) {
            if (c != ' ') {
                s.push(c);
            } else {
                String word = "";
                while(!s.empty()) {
                    word += s.pop();
                }
                q.add(word);
            }
        }
        String word = "";
        while(!s.empty()) {
            word += s.pop();
        }
        q.add(word);
        System.out.print(q);
        System.out.print(" : " + s.size());
    }
    
    public static int sumStack(Stack<Integer> s) {
        int sum = 0;
        Stack<Integer> s2 = new Stack<Integer>();
        
        for (int i = 0; i < 2; i++) {
            while(!s.isEmpty()) {
                sum += s.peek();
                s2.add(s.pop());
            }
            Stack<Integer> temp = s;
            s = s2;
            s2 = temp;
        }
        
        return sum/2;
    }
    
    public static void q78(String text) {
        System.out.printf("\n----%s----\n", text);
        Stack<Integer> s = new Stack<Integer>();
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(sumStack(s));
        System.out.println(s);
    }
    
    public static void main(String[] args) {
        q2("q2");
        q3("q3");
        mysteryS("q4");
        q5("q5");
        q78("q7 & q8");
    }
}
