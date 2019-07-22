package company;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        RobotClass robot = new RobotClass();

        MainWindow mainWindow = new MainWindow();
        Window window = new Window();

        BufferedImage mainScreen = robot.captureScreen();
        BufferedImage secondaryScreen;

        BufferedImage[] start = new BufferedImage[1];
        start[0] = mainScreen;

        ArrayList<int[]> mainCoordinates = Pixels.getSamePixelsCoordinates(start);

        while (true) {
            Wait.waiting(500);

            while (mainWindow.getRunning()) {

                int power = mainWindow.getPower();
                window.setFlickTime(mainWindow.getFlickTime());
                window.setCoordinates(mainCoordinates);

                window.updateCanvas();

                Wait.waiting(100);

                mainScreen = robot.captureScreen();

                BufferedImage[] secondaryImages = new BufferedImage[power+1];
                secondaryImages[0] = mainScreen;

                for (int i = 1; i <= power; i++) {

                    System.out.println("i = " + i);
                    Wait.waiting(mainWindow.getMainTime() * 1000 / power);

                    secondaryScreen = robot.captureScreen();
                    secondaryImages[i] = secondaryScreen;
                }

                mainCoordinates = Pixels.getSamePixelsCoordinates(secondaryImages);
            }
        }
    }
}
