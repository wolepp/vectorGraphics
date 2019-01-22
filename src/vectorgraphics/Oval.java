package vectorgraphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Oval implements Drawable {

    private double x, y;
    private double width, height;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokeOval(x, y, width, height);
    }
}
