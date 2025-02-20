package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ShapeFactory {
    private Map<Integer, BiFunction<Double, Double, Shape>> shapeMap;

    public ShapeFactory() {
        shapeMap = new HashMap<>();
        shapeMap.put(1, (x, y) -> new Line(20,10, 50, 45));
        shapeMap.put(2, (x, y) -> new Rectangle(20,20,  50, 30));
        shapeMap.put(3, (x, y) -> new Hexagon(30,30));
    }

    public Shape createShape(int num, double x, double y) {
        BiFunction<Double, Double, Shape> shapeCreator = shapeMap.get(num);
        if (shapeCreator != null) {
            return shapeCreator.apply(x, y);
        } else {
            return null;
        }
    }
}
