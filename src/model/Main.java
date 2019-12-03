package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("holus");
        BufferedImage template;
        Random random = new Random();
        try{

            /*
            =================
            template picking
            =================
            */
            File dirTemplates = new File("templates");
            File[] templateFiles = dirTemplates.listFiles();
            File chosenTemplate = templateFiles[random.nextInt(templateFiles.length)];
            template = ImageIO.read(chosenTemplate);
            System.out.println("Template chosen: "+chosenTemplate.getName());

            /*
            =====================
            template part finding
            =====================
            */
            List<Rectangle> rectangles = RectangleFinder.getRectangles(template);
            System.out.println("There are "+rectangles.size()+" empty spots to fill");

            /*
            ============
            hole filling
            ============
            */
            File dirPictures = new File("pictures");
            File[] pictureFiles = dirPictures.listFiles();
            for(Rectangle r : rectangles){
                File chosenPicture = pictureFiles[random.nextInt(pictureFiles.length)];
                BufferedImage newPicture = ImageIO.read(chosenPicture);
                System.out.println("Picture chosen: "+chosenPicture.getName());

                newPicture = ImageUtils.scaleImage(newPicture,r.getWidth(),r.getHeight(),Color.WHITE);
                ImageUtils.insertImage(template,newPicture,1,r.a.x,r.a.y);
            }

            /*
            =================
            saving to the HDD
            =================
            */
            String format = "png";
            String outFilename = chosenTemplate.getName()+GregorianCalendar.getInstance().getTimeInMillis()+"."+format;
            File output = new File("output"+File.separator+outFilename);
            System.out.println(output);
            output.createNewFile();
            ImageIO.write(template,format,output);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
