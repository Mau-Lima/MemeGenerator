package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.*;

public class RectangleFinder {
    private static final int minColor = 220;
    private static final double minAreaFraction = 0.05; //0-1, min square area percentage a rectangle must occupy to be considered a template hole

    public static List<Rectangle> getRectangles(BufferedImage template) { //could use some optimization on the finding false areas part!
        List rectList = new ArrayList();
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        BufferedImage image1 = template;
        double templateArea = image1.getHeight() * image1.getWidth();

        for (int y = 0; y < image1.getHeight(); y++) {
            for (int x = 0; x < image1.getWidth(); x++) {

                int c = image1.getRGB(x, y);
                Color color = new Color(c);
                if (isWhite(color) && !contains(rectList, new Coordinate(x, y))) {
                    x1 = x;
                    y1 = y;

                    int j = x;
                    while (j < image1.getWidth() && isWhite(new Color(image1.getRGB(j, y))))
                        j++;
                    x2 = j;
                    /*for (int i = x; i < image1.getWidth(); i++) //branch rightwards until you find the end, which is stored at x2
                    {
                        c = image1.getRGB(i, y);
                        color = new Color(c);
                        if (!isWhite(color) || i == image1.getWidth()) {
                            x2 = i;
                            break;
                        }
                    }*/

                    int i = y;
                    while (i < image1.getHeight() && isWhite(new Color(image1.getRGB(x, i))))
                        i++; //oh god come on if this works idk
                    y2 = i;

                    /*for (int i = y; i < image1.getHeight(); i++) //branch downwards until you find the end, stored at y2
                    {
                        c = image1.getRGB(x, i);//something fucky's going on rivght here
                        color = new Color(c);
                        if (!isWhite(color) || i == image1.getHeight()) {
                            y2 = i; //NOT ENTERIGNG HERe
                            break;
                        }
                    }*/


                    Rectangle actual = new Rectangle(new Coordinate(x1, y1), new Coordinate(x2, y2));

                    if (actual.getArea() >= templateArea * RectangleFinder.minAreaFraction) {
                        System.out.println("Found a template empty area! Coordinates:");
                        System.out.println(actual);
                        rectList.add(actual);
                    }
                }
            }
        }
        System.out.println("Number of rectangles = " + rectList.size() + "\n");
        return rectList;
    }


    static boolean isWhite(Color color) {
        return color.getRed() > minColor && color.getGreen() > minColor && color.getBlue() > minColor;
    }

    static boolean contains(List<Rectangle> rectList, Coordinate c) {

        for(Rectangle r: rectList){
            if (c.x >= r.a.x && c.x <= r.b.x && c.y >= r.a.y && c.y <= r.b.y)
                return true;
        }
        return false;
    }
}

