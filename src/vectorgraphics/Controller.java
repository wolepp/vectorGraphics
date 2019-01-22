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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Controller {

    private History history = new History();
    private FileManager fileManager = new FileManager();

    private Color color;
    private double x1, y1, x2, y2;
    private Drawable currentDrawable;

    @FXML
    private Label filePathLabel;
    @FXML
    private Label currentDrawableLabel;
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

    @FXML
    private void setLine(ActionEvent actionEvent) {
        currentDrawable = new Line();
        currentDrawableLabel.setText("Line");
    }

    @FXML
    private void setRectangle(ActionEvent actionEvent) {
        currentDrawable = new Rectangle();
        currentDrawableLabel.setText("Rectangle");
    }

    @FXML
    private void setOval(ActionEvent actionEvent) {
        currentDrawable = new Oval();
        currentDrawableLabel.setText("Oval");
    }

    @FXML
    private void setColor(ActionEvent actionEvent) {
        color = colorPicker.getValue();
    }

    @FXML
    private void mousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
        x2 = x1;
        y2 = y1;
    }

    @FXML
    private void mouseMoves(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        gc.setGlobalBlendMode(BlendMode.DIFFERENCE);
        gc.setStroke(Color.WHITE);
        rect(gc);
        x2 = x;
        y2 = y;
        rect(gc);
    }

    private void rect(GraphicsContext gc) {
        double x = x1;
        double y = y1;
        double w = x2 - x1;
        double h = y2 - y1;

        if (w < 0) {
            x = x2;
            w = -w;
        }

        if (h < 0) {
            y = y2;
            h = -h;
        }

        gc.strokeRect(x + 0.5, y + 0.5, w, h);
    }

    @FXML
    private void mouseReleased(MouseEvent mouseEvent) {
        rect(gc);
        gc.setFill(color);
        gc.setStroke(color);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        setDims(currentDrawable);
        draw(currentDrawable);
    }

    private void correctDims() {
        if (x2 < x1) {
            double temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if (y2 < y1) {
            double temp = y2;
            y2 = y1;
            y1 = temp;
        }
    }

    private void setDims(Drawable drawable) {
        if (drawable instanceof Line) {
            ((Line) drawable).setX1(x1);
            ((Line) drawable).setY1(y1);
            ((Line) drawable).setX2(x2);
            ((Line) drawable).setY2(y2);
        } else if (drawable instanceof Rectangle) {
            correctDims();
            ((Rectangle) drawable).setX(x1);
            ((Rectangle) drawable).setY(y1);
            ((Rectangle) drawable).setWidth(x2 - x1);
            ((Rectangle) drawable).setHeight(y2 - y1);
        } else if (drawable instanceof Oval) {
            correctDims();
            ((Oval) drawable).setX(x1);
            ((Oval) drawable).setY(y1);
            ((Oval) drawable).setWidth(x2 - x1);
            ((Oval) drawable).setHeight(y2 - y1);
        }
    }

    private void draw(Drawable drawable) {
        drawable.setColor(color);
        history.add(drawable);
        drawable.draw(gc);
    }

    public void clear(ActionEvent actionEvent) {
        history.clear();
        clear(gc);
    }

    public void openFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Plik graficzny", "*.pnvp")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"/pictures"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        clear(actionEvent);
        try {
            history = fileManager.loadFrom(selectedFile);
            drawHistory(history);
            filePathLabel.setText(selectedFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Nie można odczytać pliku");
        } catch (ClassNotFoundException e) {
            System.out.println("Niepoprawna zawartość pliku");
        }
    }

    private void drawHistory(History history) {
        for (Drawable d: history)
            d.draw(gc);
    }

    public void newFile(ActionEvent actionEvent) {
        history = new History();
        clear(gc);
    }

    public void saveFile(ActionEvent actionEvent) {

    }

    public void saveFileAs(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Zapisz plik jako");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Plik graficzny", "pnvp")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"/pictures"));
        fileChooser.setInitialFileName(".pnvp");
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            fileManager.saveTo(file, history);
        }
    }

    public void undo(ActionEvent actionEvent) {
        history.undo();
        drawHistory(history);
    }
}
