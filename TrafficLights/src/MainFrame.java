import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainFrame implements Closeable, WindowListener {
    
    private static final String TITLE = "Traffic Flow Manager";
    private static final int PADDING = 4;
    
    private Frame _frame = null;
    private MapCanvas _mapCanvas = null;
    
    public MainFrame(String mapFilePath) throws IOException {
        File mapFile = new File(mapFilePath);
        
        if (!mapFile.exists()) {
            throw new IOException();
        }
        
        BufferedImage mapImage = ImageIO.read(mapFile);
        _frame = new Frame(TITLE);
        _frame.pack();
        Insets insets = _frame.getInsets();
        _frame.setSize(
                insets.left + PADDING + mapImage.getWidth() + PADDING + insets.right, 
                insets.top + PADDING + mapImage.getHeight() + PADDING + insets.bottom);
        _frame.setLayout(null);
        _frame.setLocationRelativeTo(null);
        _frame.setResizable(false);
        
        _mapCanvas = new MapCanvas(
            insets.left + PADDING,
            insets.top + PADDING, 
            mapImage);
        _frame.add(_mapCanvas);
        
        _frame.addWindowListener(this);
    }
    
    public void open() {
        _frame.setVisible(true);
    }

    @Override
    public void close() throws IOException {
        _frame.setVisible(false);
        _frame.dispose();
        _frame = null;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Opened");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Closing");
        try {
            this.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Closed");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Iconified");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Deiconified");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("Activated");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("Deactivated");
    }

}
