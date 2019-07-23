package company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame implements ActionListener {

    private JLabel label, label2, label3, label4, copyright;
    private JButton button;
    private JTextField fieldTimeOfWait;
    private JTextField fieldTimeOfCanvas;
    private JTextField fieldPower;

    private boolean running;
    private boolean shutDown;
    private int mainTime = 180;
    private int flickTime = 8000;
    private int power = 4;

    public MainWindow(){

        running = false;
        shutDown = false;

        setTitle("Plasma saver");

        label = new JLabel("exit window to stop the application");
        label.setBounds(40, 10, 220, 40);

        label2 = new JLabel("repetition time (s)");
        label2.setBounds(100, 100, 120, 40);

        label3 = new JLabel("flick time (ms)");
        label3.setBounds(100, 150, 100, 40);

        label4 = new JLabel("scan power");
        label4.setBounds(100, 200, 120, 40);

        copyright = new JLabel("© All rights reserved to: JoeWood");
        copyright.setBounds(40, 310, 220, 40);

        button = new JButton("APPLY");
        button.setBounds(30, 270, 100, 30);
        button.addActionListener(this);

        fieldTimeOfWait = new JTextField(String.valueOf(mainTime));
        fieldTimeOfWait.setEditable(true);
        fieldTimeOfWait.setBounds(30, 100, 60, 40);

        fieldTimeOfCanvas = new JTextField(String.valueOf(flickTime));
        fieldTimeOfCanvas.setEditable(true);
        fieldTimeOfCanvas.setBounds(30, 150, 60, 40);

        fieldPower = new JTextField(String.valueOf(power));
        fieldPower.setEditable(true);
        fieldPower.setBounds(30, 200, 60, 40);

        add(label);add(fieldTimeOfCanvas);add(fieldTimeOfWait);add(label2);add(label3);add(button);add(copyright);
        add(fieldPower);add(label4);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                running = false;
                shutDown = true;
            }
        });

        setSize(300, 390);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(fieldTimeOfWait.getText().length() > 0){
            if(Double.valueOf(fieldTimeOfWait.getText()) > 0 && Double.valueOf(fieldTimeOfWait.getText()) <= 3600) {
                try {
                    mainTime = Integer.valueOf(fieldTimeOfWait.getText());
                    if(!running) {
                        running = true;
                    }
                } catch (Exception x) {
                    fieldTimeOfWait.setText(String.valueOf(mainTime));
                }
            }
            else{
                fieldTimeOfWait.setText(String.valueOf(mainTime));
            }
        }
        if(fieldTimeOfCanvas.getText().length() > 0){
            if(Double.valueOf(fieldTimeOfCanvas.getText()) > 0 && Double.valueOf(fieldTimeOfCanvas.getText()) <= 60000) {
                try {
                    flickTime = Integer.valueOf(fieldTimeOfCanvas.getText());
                } catch (Exception x) {
                    fieldTimeOfCanvas.setText(String.valueOf(flickTime));
                }
            }
            else{
                fieldTimeOfCanvas.setText(String.valueOf(flickTime));
            }
        }
        if(fieldPower.getText().length() > 0){
            if(Double.valueOf(fieldPower.getText()) > 0 && Double.valueOf(fieldPower.getText()) <= 100){
                try {
                    power = Integer.valueOf(fieldPower.getText());
                }catch(Exception x){
                    fieldPower.setText(String.valueOf(power));
                }
            }
        }
        else{
            fieldPower.setText(String.valueOf(power));
        }
    }

    public int getMainTime(){return mainTime;}

    public int getFlickTime(){return flickTime;}

    public boolean getRunning(){return running;}

    public int getPower(){return power; }

    public boolean getShutDown(){return shutDown;}
}
