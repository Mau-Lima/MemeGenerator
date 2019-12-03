package model;

public class Rectangle {
    Coordinate a, b;

    Rectangle(Coordinate a, Coordinate b) {

        this.a = a;
        this.b = b;
        assert(getWidth()>=0 && getHeight()>=0);
    }

    int getArea(){ //TODO Look at this
        double deltax = b.x-a.x;
        double deltay = b.y-a.y;
        return (int) Math.abs(deltax*deltay);
    }

    int getWidth(){
        return b.x-a.x;
    }
    int getHeight(){
        return b.y-a.y;
    }

    @Override
    public String toString(){
        return "["+a+","+b+"]";
    }
}
