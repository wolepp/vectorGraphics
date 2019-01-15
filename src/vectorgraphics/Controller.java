package vectorgraphics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Controller {

    private Shape choosenShape;
    private Color color;

    @FXML
    private Label filePathLabel;
    @FXML
    private Label choosenModeLabel;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;

    private GraphicsContext gc;

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        clear(gc);
    }

    private void clear(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void setLine(ActionEvent actionEvent) {
        choosenShape = new Line();
    }

    public void setRectangle(ActionEvent actionEvent) {
        choosenShape = new Rectangle();
    }

    public void setEllipse(ActionEvent actionEvent) {
        choosenShape = new Ellipse();
    }

    public void setColor(ActionEvent actionEvent) {
        color = colorPicker.getValue();
    }

    public void mousePressed(MouseEvent mouseEvent) {
        choosenShape.setFill(color);

    }

    public void mouseMoves(MouseEvent mouseEvent) {
    }

    public void mouseReleased(MouseEvent mouseEvent) {
    }
}
