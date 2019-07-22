package company;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;

public class Pixels {

    public static int[][] convertImage(BufferedImage image){

        BufferedImage convertedImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        convertedImg.getGraphics().drawImage(image, 0, 0, null);

        final byte[] pixels = ((DataBufferByte) convertedImg.getRaster().getDataBuffer()).getData();

        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];

        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }
        return result;
    }

    public static ArrayList<int[]> getSamePixelsCoordinates(BufferedImage[] images){

        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[][]> pixels = new ArrayList<>();

        for(int i = 0; i < images.length; i++){
            pixels.add(Pixels.convertImage(images[i]));
        }
        for (int i = 0; i < pixels.get(0).length; i++) {

            for (int j = 0; j < pixels.get(0)[i].length; j++) {

                for(int f = 0; f < images.length; f++) {
                    
                    if (pixels.get(0)[i][j] == pixels.get(f)[i][j]) {
                        if(f == images.length-1) {
                            int[] coordinates = new int[2];
                            coordinates[0] = i;
                            coordinates[1] = j;
                            result.add(coordinates);
                        }
                    }
                    else{
                        break;
                    }
                }
            }
        }
        return result;
    }
}
