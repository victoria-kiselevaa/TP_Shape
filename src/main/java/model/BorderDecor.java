package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BorderDecor extends ShapeDecorator {
    private Shape shape;

    public BorderDecor(double x, double y) {
        super(x, y);
    }

    @Override
    public void setShape(Shape shape) {
        this.shape=shape;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (shape != null) {
            shape.drawBorder(gc);
        }
    }

    @Override
    public void drawBorder(GraphicsContext gc) {
    }

    @Override
    public void drawText(GraphicsContext gc) {
    }

}
