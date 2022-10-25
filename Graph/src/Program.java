import graph.*;

public class Program {
	
	private static double[][] linearSample(int size, double slope, double offset) {
		double[][] sample = new double[size][2];
		
		for (int i = 0; i < size; i++) {
			sample[i][0] = i;
			sample[i][1] = slope * i + offset;
		}
		
		return sample;
	}
	
	private static double[][] quadraticSample(int size, double a, double b, double c) {
		double[][] sample = new double[size][2];
		
		for (int i = 0; i < size; i++) {
			sample[i][0] = i;
			sample[i][1] = a * i * i + b * i + c;
		}
		
		return sample;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.add("linear1", linearSample(1000, 20, 20));
		graph.add("linear2", linearSample(1000, -30, 100));
		graph.add("quadratic", quadraticSample(1000, .02, 14, -3));
		graph.plot();
	}
}