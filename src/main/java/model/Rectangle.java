package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double x, double y, Color color, double width, double height) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }
}
