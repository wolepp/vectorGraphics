package vectorgraphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

abstract public class Shape implements Drawable, Serializable {

    transient Color color;
    private double r, g, b;
    public abstract void draw(GraphicsContext gc);

    public void updateColor() {
        this.color = Color.color(r, g, b);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
    }
}
