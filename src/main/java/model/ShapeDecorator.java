package model;

import javafx.scene.canvas.GraphicsContext;

public abstract class ShapeDecorator extends Shape{
    public ShapeDecorator(double x, double y) {
        super(x, y);
    }
    public abstract void setShape(Shape shape);
    public abstract void draw(GraphicsContext gc);
}
