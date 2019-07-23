package company;

import javax.swing.JWindow;
import java.awt.Toolkit;
import java.awt.Color;
import java.util.ArrayList;

public class Window extends JWindow{

    private ArrayList<int[]> coordinates;
    private int flickTime = 1000;

    public Window(){

        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setFocusable(false);
        requestFocus();
        setAlwaysOnTop(true);
        setBackground(new Color(0, 0, 0, 0));
        getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        setVisible(true);
    }

    public void updateCanvas(){

        boolean isMinCoordinatesSet = false;
        int numberOfCurrentCanvas = 0;
        int numberOfCanvases = 0;
        int numberOfCoordinates = coordinates.size();
        int previousColumn = coordinates.get(0)[1];
        int row;
        int column = previousColumn;

        ArrayList<Integer> minXs = new ArrayList<>();
        ArrayList<Integer> minRows = new ArrayList<>();
        ArrayList<Integer> maxXs = new ArrayList<>();

        for(int i = 0; i < numberOfCoordinates; i++) {

            previousColumn = column;
            row = coordinates.get(i)[0];
            column = coordinates.get(i)[1];

                if(!isMinCoordinatesSet) {
                    minXs.add(numberOfCurrentCanvas, column);
                    minRows.add(numberOfCurrentCanvas, row);
                    maxXs.add(numberOfCurrentCanvas, column);
                    isMinCoordinatesSet = true;
                }
                if((column > (maxXs.get(numberOfCurrentCanvas))) && (column <= maxXs.get(numberOfCurrentCanvas)+10)){
                    maxXs.set(numberOfCurrentCanvas, maxXs.get(numberOfCurrentCanvas)+column-previousColumn);
                    continue;
                }
                else if(column != minXs.get(numberOfCurrentCanvas)){
                    numberOfCurrentCanvas++;
                    isMinCoordinatesSet = false;
                    i--;
                    continue;
                }
                numberOfCanvases = minXs.size();
        }

        setVisible(false);

        for(int i = 0; i < numberOfCanvases; i++){

            add((new MyCanvas(minXs.get(i), minRows.get(i), maxXs.get(i)-minXs.get(i)+1, 4, Color.BLACK)));
            revalidate();
            repaint(minXs.get(i), minRows.get(i), maxXs.get(i)-minXs.get(i)+1, 4);
        }
        setVisible(true);
        Wait.waiting(flickTime/2);
        getContentPane().removeAll();
        revalidate();
        repaint();
        setVisible(false);

        for(int i = 0; i < numberOfCanvases; i++){

            add((new MyCanvas(minXs.get(i), minRows.get(i), maxXs.get(i)-minXs.get(i)+1, 4, Color.WHITE)));
            revalidate();
            repaint(minXs.get(i), minRows.get(i), maxXs.get(i)-minXs.get(i)+1, 4);
        }
        setVisible(true);
        Wait.waiting(flickTime/2);
        getContentPane().removeAll();
        revalidate();
        repaint();

    }
    public void setCoordinates(ArrayList<int[]> coordinates){
        this.coordinates = coordinates;
    }
    public void setFlickTime(int flickTime){this.flickTime = flickTime;}
}
