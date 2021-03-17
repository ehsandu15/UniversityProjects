package net.eh_du.ehsanduwidi;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


public class Arrows extends Path{
    private static final double defaultHeadSize = 5.0;
    public Arrows(double startX, double startY, double endX, double endY, double HeadSize){
        super();
        strokeProperty().bind(fillProperty());
        setFill(Color.BLACK);
        getElements().add(new MoveTo(startX, startY));
        getElements().add(new LineTo(endX, endY));
        double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * HeadSize + endX;
        double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * HeadSize + endY;
        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * HeadSize + endX;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * HeadSize + endY;

        getElements().add(new LineTo(x1, y1));
        getElements().add(new LineTo(x2, y2));
        getElements().add(new LineTo(endX, endY));
    }

    public Arrows(double startX, double startY, double endX, double endY){
        this(startX, startY, endX, endY, defaultHeadSize);
    }
}
