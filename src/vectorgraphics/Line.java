package vectorgraphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line implements Drawable {

    private double x1, y1, x2, y2;

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokeLine(x1, y1, x2, y2);
    }
}
