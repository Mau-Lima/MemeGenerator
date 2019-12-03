package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {
    public static BufferedImage scaleImage(BufferedImage img, int width, int height,
                                           Color background) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        if (imgWidth*height < imgHeight*width) { //u know
            width = imgWidth*height/imgHeight;
        } else {
            height = imgHeight*width/imgWidth;
        }
        BufferedImage scaledImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledImage.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setBackground(background);
            g.clearRect(0, 0, width, height);
            g.drawImage(img, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return scaledImage;
    }

    public static void insertImage(BufferedImage bigPicture, BufferedImage smallPicture, float opacity, int posX, int posY){
            Graphics2D g2d = bigPicture.createGraphics();
            g2d.setComposite(
                    AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2d.drawImage(smallPicture, posX, posY, null);
            g2d.dispose();
    }
}
