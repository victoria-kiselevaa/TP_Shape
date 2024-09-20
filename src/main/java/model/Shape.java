package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

abstract class Shape {
    protected double x;
    protected double y;
    protected Color color;
    public Shape(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color=color;
    }

    abstract void draw(GraphicsContext gc);
}
