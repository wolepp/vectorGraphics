package vectorgraphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

public interface Drawable extends Serializable  {
    void draw(GraphicsContext gc);
    void setColor(Color color);
}
