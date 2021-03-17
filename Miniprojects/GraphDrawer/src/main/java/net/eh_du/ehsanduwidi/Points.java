package net.eh_du.ehsanduwidi;

public class Points {
    private double angle;
    private double x;
    private double y;
    private String name;

    Points(double angle,  String name){

        this.angle= angle;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public void setX(double coordinate, double radius)
    {
        this.x = coordinate+radius*Math.sin(Math.toRadians(getAngle()));
    }

    public double getY() {
        return y;
    }

    public void setY(double coordinate , double radius) {

        this.y = coordinate-radius*Math.cos(Math.toRadians(getAngle()));
    }

    public double getAngle() {
        return angle; }

    @Override
    public String toString() {
        return String.format("Point %s Angel %.2f",name,angle);
    }
}
