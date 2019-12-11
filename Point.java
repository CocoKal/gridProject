package model;

public class Point {
    private int x;
    private int y;

    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static boolean comparePoints(Point point1, Point point2) {
        if (point1.getX() == point2.getX() && point1.getY() == point2.getY()) return true;
        return false;
    }


}
