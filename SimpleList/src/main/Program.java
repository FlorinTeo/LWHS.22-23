package main;

import java.util.ArrayList;

import graph.Graph;

public class Program {
    private static ArrayList<Integer> aList = new ArrayList<Integer>();
    private static SimpleArrayList sList = new SimpleArrayList();
    
    public static double prependToList(Object list, int count) {
        long start = System.nanoTime();
        for (int n = 1; n <= count; n++) {
            if (list == sList) {
                sList.add(0, n);
            }
            if (list == aList) {
                aList.add(0, n);
            }
        }
        return System.nanoTime() - start;
    }
    
    public static double appendToList(Object list, int count) {
        long start = System.nanoTime();
        for (int n = 1; n <= count; n++) {
            if (list == sList) {
                sList.add(n);
            }
            if (list == aList) {
                aList.add(n);
            }
        }
        return System.nanoTime() - start;
    }

    public static void main(String[] args) {
        System.out.println("Hello to SimpleList Perf Bench!");
        
        double[] countX = new double[100];
        double[] sListY = new double[100];
        double[] aListY = new double[100];
        
        for (int i = 0; i < countX.length; i++) {
            countX[i] = i * 10;
            sListY[i] = prependToList(sList, i * 200);
            aListY[i] = prependToList(aList, i * 200);
            sList.clear();
            aList.clear();
        }
        
        Graph graphPrepend = new Graph("Prepend Experiments");
        graphPrepend.add("sList", countX, sListY);
        graphPrepend.add("aList", countX, aListY);
        graphPrepend.plot();
        System.out.printf("sList max prepend = %f, aList max prepend = %f\n", sListY[99], aListY[99]);
        
        for (int i = 0; i < countX.length; i++) {
            countX[i] = i * 10;
            sListY[i] = appendToList(sList, i * 200);
            aListY[i] = appendToList(aList, i * 200);
            sList.clear();
            aList.clear();
        }
        
        Graph graphAppend = new Graph("Append Experiments");
        graphAppend.add("sList", countX, sListY);
        graphAppend.add("aList", countX, aListY);
        graphAppend.plot();
        System.out.printf("sList max append = %f, aList max append = %f", sListY[99], aListY[99]);
    }
}
