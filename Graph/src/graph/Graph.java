package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final static String DEFAULT_TITLE = "Graph";
    private final int DEFAULT_WIDTH = 1024;
    private final int DEFAULT_HEIGHT = 680;
    private final int DEFAULT_BEZEL = 40;
    private final int DEFAULT_PTSIZE = 4;
    private final int DEFAULT_GRIDSIZE = 20;
    private final Color[] COLORS = new Color[] { Color.BLUE, Color.DARK_GRAY, Color.RED, Color.ORANGE, Color.MAGENTA };

    private int _width = DEFAULT_WIDTH;
    private int _height = DEFAULT_HEIGHT;
    private int _grid = _width / DEFAULT_GRIDSIZE;
    private int _colorIndex = 0;

    private String _title = DEFAULT_TITLE;
    private DrawingPanel _panel = null;
    private List<Dataset> _datasets = new ArrayList<Dataset>();

    private double _xScale = 1;
    private double _yScale = 1;

    private void updateScale() {
        double _xMaxRange = Double.MIN_VALUE;
        double _yMaxRange = Double.MIN_VALUE;

        for (Dataset ds : _datasets) {
            _xMaxRange = Math.max(ds.getXRange(), _xMaxRange);
            _yMaxRange = Math.max(ds.getYRange(), _yMaxRange);
        }

        _xScale = (_width - 2 * DEFAULT_BEZEL) / _xMaxRange;
        _yScale = (_height - 2 * DEFAULT_BEZEL) / _yMaxRange;
    }

    private Pixel pointToPixel(Point ptOffset) {
        int xPx = (int) (ptOffset.getX() * _xScale + DEFAULT_BEZEL);
        int yPx = (int) ((_height - DEFAULT_BEZEL) - ptOffset.getY() * _yScale);
        return new Pixel(xPx, yPx);
    }

    private void paint() {
        if (_panel == null || _panel.isClosed()) {
            _panel = new DrawingPanel(_title, _width, _height);
        } else {
            _panel.clear();
        }
        Graphics g = _panel.getGraphics();
        g.setColor(Color.CYAN);
        g.drawRect(DEFAULT_BEZEL, DEFAULT_BEZEL, _width - 2 * DEFAULT_BEZEL, _height - 2 * DEFAULT_BEZEL);
        paintGrid();
        g.setColor(Color.RED);
        g.setFont(g.getFont().deriveFont(18.0f));
        int labelOffset = DEFAULT_BEZEL;
        for (Dataset ds : _datasets) {
            g.setColor(ds.getColor());
            paintDataset(ds);
            g.drawString(ds.getLabel(), labelOffset, 2 * DEFAULT_BEZEL / 3);
            labelOffset += ds.getLabel().length() * 10;
        }
    }

    private void paintGrid() {
        Graphics g = _panel.getGraphics();
        for (int x = DEFAULT_BEZEL; x < _width - DEFAULT_BEZEL; x += _grid) {
            g.drawLine(x, DEFAULT_BEZEL, x, _height - DEFAULT_BEZEL);
        }
        for (int y = DEFAULT_BEZEL; y < _height - DEFAULT_BEZEL; y += _grid) {
            g.drawLine(DEFAULT_BEZEL, y, _width - DEFAULT_BEZEL, y);
        }
    }

    private void paintDataset(Dataset ds) {
        int step = Math.max(1, ds.getSize() / Dataset.DEFAULT_MAXSAMPLE);
        Pixel prevPx = null;
        for (int i = 0; i < ds.getSize(); i += step) {
            Pixel px = pointToPixel(ds.getOffset(i));
            paintPixel(px);
            if (prevPx != null) {
                paintLine(prevPx, px);
            }
            prevPx = px;
        }
    }

    private void paintPixel(Pixel px) {
        Graphics g = _panel.getGraphics();
        g.fillOval(px.getX() - DEFAULT_PTSIZE / 2, px.getY() - DEFAULT_PTSIZE / 2, DEFAULT_PTSIZE, DEFAULT_PTSIZE);
    }

    private void paintLine(Pixel fromPx, Pixel toPx) {
        Graphics g = _panel.getGraphics();
        g.drawLine(fromPx.getX(), fromPx.getY(), toPx.getX(), toPx.getY());
    }

    public Graph() {
        this(DEFAULT_TITLE);
    }
    
    public Graph(String title) {
        _title = title;
        _width = DEFAULT_WIDTH;
        _height = DEFAULT_HEIGHT;
    }

    public boolean add(String label, double[][] dataset) {
        boolean success = Dataset.isValid(dataset);

        if (success) {
            _datasets.add(new Dataset(label, COLORS[_colorIndex], dataset));
            _colorIndex = (_colorIndex + 1) % COLORS.length;
        }

        return success;
    }

    public boolean add(String label, double[] x, double[] y) {
        boolean success = Dataset.isValid(x, y);

        if (success) {
            _datasets.add(new Dataset(label, COLORS[_colorIndex], x, y));
            _colorIndex = (_colorIndex + 1) % COLORS.length;
        }

        return success;
    }

    public void plot() {
        updateScale();
        paint();
    }

    public void close() {
        if (_panel != null) {
            _panel.close();
            _panel = null;
        }
    }
}
