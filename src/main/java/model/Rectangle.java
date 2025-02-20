package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height) {
        super(x, y);
        this.color=getColor();
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }
    @Override
    public String toString() {
        return "Прямоугольник";
    }

    @Override
    public void drawBorder(GraphicsContext gc) {
        gc.setStroke(Color.YELLOW);
        gc.strokeRect(x,y,width,height);
    }
    @Override
    public void drawText(GraphicsContext gc) {
        gc.fillText("Прямоугольник",x,y);
    }
}
