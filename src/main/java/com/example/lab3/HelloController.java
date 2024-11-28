package com.example.lab3;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Canvas canvas;
    public ColorPicker color;
    public TextField textF=null;
    public ListView listview;
    ShapeFactory shapeFactory = new ShapeFactory();
    Shape shape=null;
    GraphicsContext gr;
    private boolean isDragging = false;
    private double dragOffsetX = 0;
    private double dragOffsetY = 0;
    private MemoSelect memoSelect = new MemoSelect();
    private Memento temp = null;
    private ObservableList<Shape> items;

    public void onMouseClick(MouseEvent mouseEvent) {

        gr.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        if(Integer.parseInt(textF.getText())!=0){
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            shape=shapeFactory.createShape(Integer.parseInt(textF.getText()),x,y);
            shape.setX(x);
            shape.setY(y);
            shape.setColor(color.getValue());
            shape.draw(gr);
        }else {
            drawShape(mouseEvent);
        }
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
        Rectangle rectangle=new Rectangle(100,100,  100, 50);
        Line line = new Line(100,100,200,45);
        Hexagon hexagon = new Hexagon(100,100);
        items = FXCollections.observableArrayList(rectangle, line, hexagon);
        listview.setItems(items);
        listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void drawShape(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        int index = listview.getSelectionModel().getSelectedIndex(); //получение индекса выбора из списка
        Shape shape = (Shape) items.get(index).clone();// создание копии фигуры
        shape.setColor(color.getValue());// установка цвета заполнения фигуры по значению элемента управления colorPicker
        shape.setX(x);
        shape.setY(y);
        shape.draw(gr);// рисование копии фигуры в точке, полученной из события MouseEvent x
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