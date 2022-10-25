package graph;

import java.awt.Color;

class Dataset {
	public static final int DEFAULT_MAXSAMPLE = 100;
	
	Point [] _points;
	double _xMin = Double.MAX_VALUE;
	double _xMax = Double.MIN_VALUE;
	double _yMin = Double.MAX_VALUE;
	double _yMax = Double.MIN_VALUE;
	
	Color _color;
	String _label;

	public static boolean isValid(double[][] dataSet) {
		boolean valid = true;
		for (int i = 0; i < dataSet.length && valid; i++) {
			valid = (dataSet[0].length == 2);
		}
		return valid;
	}
	
	public static boolean isValid(double[] x, double[] y) {
		return (x.length == y.length);
	}
	
	public Dataset(String label, Color color, double[][] dataSet) {
		_label = label;
		_color = color;
		_points = new Point[dataSet.length];

		int step = Math.max(1, dataSet.length / DEFAULT_MAXSAMPLE);
		for (int i = 0; i < dataSet.length; i += step) {
			_points[i] = new Point(dataSet[i][0], dataSet[i][1]);
			_xMin = Math.min(dataSet[i][0], _xMin);
			_xMax = Math.max(dataSet[i][0], _xMax);
			_yMin = Math.min(dataSet[i][1], _yMin);
			_yMax = Math.max(dataSet[i][1], _yMax);
		}
	}
	
	public Dataset(String label, Color color, double[] x, double[] y) {
		_label = label;
		_color = color;
		_points = new Point[x.length];
		
		int step = Math.max(1, x.length / DEFAULT_MAXSAMPLE);
		for (int i = 0; i < x.length; i += step) {
			_points[i] = new Point(x[i], y[i]);
			_xMin = Math.min(x[i], _xMin);
			_xMax = Math.max(x[i], _xMax);
			_yMin = Math.min(y[i], _yMin);
			_yMax = Math.max(y[i], _yMax);
		}		
	}
	
	public String getLabel() {
		return _label;
	}
	
	public Color getColor() {
		return _color;
	}
	
	public double getXRange() {
		return (_xMax - _xMin);
	}
	
	public double getYRange() {
		return (_yMax - _yMin);
	}
	
	public int getSize() {
		return _points.length;
	}
	
	public Point getPoint(int iPoint) {
		return _points[iPoint];
	}
	
	public Point getOffset(int iPoint) {
		Point pt = _points[iPoint];
		Point ptOffset = new Point(pt.getX() - _xMin, pt.getY() - _yMin);
		return ptOffset;
	}
}
