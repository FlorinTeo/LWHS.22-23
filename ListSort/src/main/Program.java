package main;

public class Program {
    private static IntNode head = null;
    
    public static void insert(int number) {
        IntNode previous = null;
        IntNode current = head;
        IntNode newNode = new IntNode(number, null);
        
        while(current != null) {
            if (number < current.getData()) {
                break;
            }
            previous = current;
            current = current.getNext();
        }
        
        if (previous == null) {
            head = new IntNode(number, newNode);
        } else {
            previous.linkTo(newNode);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 0, -5, 7, 9, 0, 5, 3, -2};
        
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }
        
        System.out.println(head);;

    }

}
