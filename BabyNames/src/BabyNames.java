import java.awt.Graphics;
import java.awt.Color;
import java.io.*;
import java.util.*;


public class BabyNames{
   public static String STATS;
   
   public static String MEANING;
   
   public static void main(String[] args)throws FileNotFoundException{
      System.out.println("This program allows you to search through the");
      System.out.println("data from the Social Security Administration");
      System.out.println("to see how popular a particular name has been");
      System.out.println("since 1890.");
      System.out.println();
      
      Scanner console = new Scanner(System.in);
      
      
      
      String name = promptName(console);
      
      Scanner search = new Scanner(new File("names.txt"));
      Scanner search2 = new Scanner(new File("names2.txt"));
      Scanner search3 = new Scanner(new File("meanings.txt"));
      
      searchName(search, search2, search3, name);
      
      DrawingPanel panel = new DrawingPanel(780, 560);
      Graphics g = panel.getGraphics();
      
      panel(g);
      
      graph(g);
   }
   
   public static String promptName(Scanner console){
      String input;
      System.out.print("Name: ");
      input = console.next();
      input = input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
      
      return input;
   }
   
   public static void searchName(Scanner search, Scanner search2, Scanner search3, String name){
      int length = name.length();
      String text = search.nextLine();
      //searches through first names list
      while(search.hasNextLine() && !text.substring(0,length).equals(name)){
         text = search.nextLine();
      }
      if(!text.substring(0,length).equals(name)){//if not first names list
         text = search2.nextLine();
          //search through names2
         while(search2.hasNextLine() && !text.substring(0,length).equals(name)){
            text = search2.nextLine();
         }
         if(!text.substring(0,length).equals(name)){//if still not found
            System.out.println("Name not found");
         }   
      }
      else{ //if name is finnally found
         System.out.println(text);
         searchMeaning(search3, name);
         STATS = text;
      }
   }
   
   public static void searchMeaning(Scanner search, String name){ 
      name = name.toUpperCase();
      int length = name.length();
      
      String text = search.nextLine();//search through meanings
      while(search.hasNextLine() && !text.substring(0,length).equals(name)){
         text = search.nextLine();
      }
      System.out.println(text);
      
      MEANING = text;      
   }
   
   public static void panel(Graphics g){     
      g.drawRect(0, 0, 779, 30);
      g.drawRect(0, 530, 779, 30); 
      g.drawRect(0, 0, 779, 559); 
            
      g.setColor(Color.LIGHT_GRAY);
      g.fillRect(1, 1, 778, 29);
      g.fillRect(1, 531, 778, 29); 
      
      
      g.setColor(Color.BLACK);
      
      g.drawString("" + MEANING, 0, 16);
      
      int j = 1890;
      for(int i = 0;  i <= 12; i++){
         g.drawString("" + j, i*60, 552);
         j += 10;
      }
   }
   
   public static void graph(Graphics g){
      Scanner line = new Scanner(STATS);
      
      line.next();
      String gender = line.next();
      int rank = 0;
      int i = 0;
      int yPos = 530;
      while(line.hasNextInt()){
         rank = line.nextInt();
         yPos = (rank/2) + 30;
         if(rank == 0){
            yPos = 530;
         }
         
         g.drawString("" + rank, i*60, yPos);
         if(gender.equals("m")){
            g.setColor(Color.BLUE);
         } 
         else {
            g.setColor(Color.PINK);
         }
         
         g.fillRect(i*60, yPos, 30, 530 - (yPos));
         
         
         i++;
         
         g.setColor(Color.BLACK);
      }
   }
}