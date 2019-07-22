package company;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class RobotClass {

    private Robot robot;
    private Rectangle area;

    public RobotClass() throws Exception{

        robot = new Robot();
        area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public BufferedImage captureScreen(){
        return robot.createScreenCapture(area);
    }
}
