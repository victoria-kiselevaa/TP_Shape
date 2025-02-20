package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Hexagon extends Shape {

    public Hexagon(double x, double y){
        super(x,y);
        this.color=getColor();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        double radius = Math.min(x, y) * 0.8;
        gc.beginPath();
        for (int i = 0; i < 6; i++) {
            double angle = Math.PI / 2 + i * 2 * Math.PI / 6;
            double x1 = x + radius * Math.cos(angle);
            double y1 = y + radius * Math.sin(angle);
            if (i == 0) {
                gc.moveTo(x1, y1);
            } else {
                gc.lineTo(x1, y1);
            }
        }
        gc.closePath();
        gc.fill();
    }
    @Override
    public String toString() {
        return "Шестиугольник";
    }

    @Override
    public void drawBorder(GraphicsContext gc) {
        gc.setStroke(Color.YELLOW);
        double radius = Math.min(x, y) * 0.8;
        gc.beginPath();
        for (int i = 0; i < 6; i++) {
            double angle = Math.PI / 2 + i * 2 * Math.PI / 6;
            double x1 = x + radius * Math.cos(angle);
            double y1 = y + radius * Math.sin(angle);
            if (i == 0) {
                gc.moveTo(x1, y1);
            } else {
                gc.lineTo(x1, y1);
            }
        }
        gc.closePath();
        gc.stroke();

    }

    @Override
    public void drawText(GraphicsContext gc) {
        gc.fillText("Шестиугольник",x,y);
    }

}
