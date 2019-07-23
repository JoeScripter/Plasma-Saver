package company;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyCanvas extends JPanel{

    private int x;
    private int y;
    private int w;
    private int h;
    private int offset = 1;
    private Color color;

    public MyCanvas(int x, int y, int width, int height, Color color){

        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        setBackground(new Color(0, 0, 0, 0));
        this.color = color;
    }

    public Dimension getPreferredSize(){
        return new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(color);
        g.fillRect(x-offset, y-offset, w+offset, h+offset);
    }
}
