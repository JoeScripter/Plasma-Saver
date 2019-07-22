package company;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PrivilegedCanvas extends JPanel{

    int x;
    int y;
    int w;
    int h;
    int offset = 1;

    public PrivilegedCanvas(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        setBackground(new Color(0, 0, 0, 0));
    }

    public Dimension getPreferredSize(){
        return new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(x-offset, y-offset, w+offset, h+offset);
    }
}
