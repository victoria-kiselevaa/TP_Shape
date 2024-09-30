package com.example.lab3;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Hexagon;
import model.Line;
import model.Rectangle;

public class HelloController {
    public Canvas canvas;
    public ColorPicker color;
    public TextField textF;

    public void onMouseClick(MouseEvent mouseEvent) {
        GraphicsContext gr = canvas.getGraphicsContext2D();
        gr.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        String name=textF.getText();
        if(name.equals("Прямоугольник")){
            Rectangle rectangle = new Rectangle(mouseEvent.getX(), mouseEvent.getY(), color.getValue(),100, 50);
            rectangle.draw(gr);
        } else if (name.equals("Линия")) {
            Line line=new Line(mouseEvent.getX(), mouseEvent.getY(),color.getValue(),200,45);
            line.draw(gr);
        } else if (name.equals("Шестиугольник")) {
            Hexagon hexagon=new Hexagon(mouseEvent.getX(), mouseEvent.getY(),color.getValue());
            hexagon.draw(gr);
        }
    }
}