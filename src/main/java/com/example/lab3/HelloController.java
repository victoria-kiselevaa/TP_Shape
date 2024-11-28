package com.example.lab3;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Canvas canvas;
    public ColorPicker color;
    public TextField textF;
    ShapeFactory shapeFactory = new ShapeFactory();
    Shape shape=null;
    GraphicsContext gr;
    private boolean isDragging = false;
    private double dragOffsetX = 0;
    private double dragOffsetY = 0;
    private MemoSelect memoSelect = new MemoSelect();
    private Memento temp = null;

    public void onMouseClick(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        gr.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        shape=shapeFactory.createShape(Integer.parseInt(textF.getText()),x,y);
        shape.setX(x);
        shape.setY(y);
        shape.setColor(color.getValue());
        shape.draw(gr);

        dragOffsetX = mouseEvent.getX() - shape.getX();
        dragOffsetY = mouseEvent.getY() - shape.getY();

        newMemento();

    }

    public void OnClick(ActionEvent actionEvent) {
        if(shape!=null){
            shape.setColor(color.getValue());
            shape.draw(gr);
            newMemento();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gr = canvas.getGraphicsContext2D();
    }


    public void MouseDragged(MouseEvent mouseEvent) {
        if (shape != null) {
            isDragging = true;
            double newX = mouseEvent.getX() - dragOffsetX;
            double newY = mouseEvent.getY() - dragOffsetY;
            shape.setPosition(newX, newY);
            gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            shape.draw(gr);
        }
    }

    public void MouseReleased(MouseEvent mouseEvent) {
        if (isDragging) {
            isDragging = false;
            double newX = mouseEvent.getX() - dragOffsetX;
            double newY = mouseEvent.getY() - dragOffsetY;
            shape.setPosition(newX, newY);
            gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            shape.draw(gr);
            newMemento();
        }
    }

    public void ButReturnClick(ActionEvent actionEvent) {
        if (!memoSelect.mementoList.isEmpty()) {
            shape = memoSelect.poll().getState();
            gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            shape.draw(gr);
        }else {
            newMemento();
        }
    }

    public void newMemento(){
        temp = new Memento(shape);
        memoSelect.push(temp);
    }
}