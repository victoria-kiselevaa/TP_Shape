package com.example.lab3;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import model.Hexagon;
import model.Line;
import model.Rectangle;

public class HelloController {
    public Canvas canvas;
    public ColorPicker color;
    public TextField textF;


    public void onClick(ActionEvent actionEvent) {
        GraphicsContext gr = canvas.getGraphicsContext2D();
        gr.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        String name=textF.getText();
        if(name.equals("Прямоугольник")){
            Rectangle rectangle = new Rectangle(50, 50, color.getValue(),100, 50);
            rectangle.draw(gr);
        } else if (name.equals("Линия")) {
            Line line=new Line(100,100,color.getValue(),200,45);
            line.draw(gr);
        } else if (name.equals("Шестиугольник")) {
            Hexagon hexagon=new Hexagon(100,100,color.getValue());
            hexagon.draw(gr);
        }

    }
}