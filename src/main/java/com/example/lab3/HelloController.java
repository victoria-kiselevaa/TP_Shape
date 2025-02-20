package com.example.lab3;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Canvas canvas;
    public Canvas canvas2;
    public ColorPicker color;
    public TextField textF=null;
    public CheckBox checkBorder;
    public CheckBox checkText;
    public Button ButReturn;
    public Canvas canvas3;
    ShapeFactory shapeFactory = new ShapeFactory();
    Shape shape=null;
    GraphicsContext gr;
    GraphicsContext gs;
    GraphicsContext gp;
    private boolean isDragging = false;
    private double dragOffsetX = 0;
    private double dragOffsetY = 0;
    private MemoSelect memoSelect = new MemoSelect();
    private Memento temp = null;
    double x;
    double y;
    @FXML
    private ListView<Shape> listView=null;

    ObservableList<Shape> items;
    public void onMouseClick(MouseEvent mouseEvent) {

        gr.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        if(Integer.parseInt(textF.getText())!=0){
            x = mouseEvent.getX();
            y = mouseEvent.getY();
            shape=shapeFactory.createShape(Integer.parseInt(textF.getText()),x,y);
            shape.setX(x);
            shape.setY(y);
            shape.setColor(color.getValue());
            shape.draw(gr);
            dragOffsetX = mouseEvent.getX() - shape.getX();
            dragOffsetY = mouseEvent.getY() - shape.getY();
            newMemento();
        }else {
            drawShape(mouseEvent);
        }

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
        gs=canvas2.getGraphicsContext2D();
        gp=canvas3.getGraphicsContext2D();
        items = FXCollections.observableArrayList();

        int[] shapeTypes = {1, 2, 3}; // 1 - Line, 2 - Rectangle, 3 - Hexagon

        for (int shapeType : shapeTypes) {
            items.add(shapeFactory.createShape(shapeType, 20, 20));
        }

        // Установка элементов в ListView
        listView.setItems(items);

        listView.setCellFactory(new Callback<ListView<Shape>, ListCell<Shape>>() {
            @Override
            public ListCell<Shape> call(ListView<Shape> list) {
                return new ShapeCell();
            }
        });
    }

    public void drawShape(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();

        int index = listView.getSelectionModel().getSelectedIndex(); //получение индекса выбора из списка
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

    public void TextCh(ActionEvent actionEvent) {
        int index = listView.getSelectionModel().getSelectedIndex();
        Shape originalShape = (Shape) items.get(index).clone(); // создаем копию
        originalShape.setColor(color.getValue());
        originalShape.setPosition(x, y);

        // Создаем копию фигуры
        Shape decoratedShape = originalShape;

        if (checkText.isSelected()) {
            TextDecor textDecorator = new TextDecor(x,y);
            textDecorator.setShape(originalShape);
            decoratedShape = textDecorator;
            decoratedShape.draw(gp);
        }else gp.clearRect(0,0,canvas3.getWidth(),canvas3.getHeight());
    }

    public void BordrerCh(ActionEvent actionEvent) {
        int index = listView.getSelectionModel().getSelectedIndex();
        Shape originalShape = (Shape) items.get(index).clone(); // создаем копию
        originalShape.setColor(color.getValue());
        originalShape.setPosition(x, y);

        // Создаем копию фигуры
        Shape decoratedShape = originalShape;

        // Проверяем, добавлен ли контур
        if (checkBorder.isSelected()) {
            BorderDecor borderDecorator = new BorderDecor(x,y);
            borderDecorator.setShape(originalShape);
            decoratedShape = borderDecorator;
            decoratedShape.draw(gs);
        }else gs.clearRect(0,0,canvas2.getWidth(),canvas2.getHeight());

    }
}