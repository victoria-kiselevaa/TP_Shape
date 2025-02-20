package model;

import javafx.scene.canvas.GraphicsContext;

public class TextDecor extends ShapeDecorator{
    private Shape shape;

    public TextDecor(double x, double y) {
        super(x, y);
    }

    @Override
    public void setShape(Shape shape) {
        this.shape=shape;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (shape != null) {
            shape.drawText(gc);
        }
    }

    @Override
    public void drawBorder(GraphicsContext gc) {
    }

    @Override
    public void drawText(GraphicsContext gc) {
    }
}
