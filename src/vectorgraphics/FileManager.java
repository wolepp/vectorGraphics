package vectorgraphics;

import java.io.*;

public class FileManager {

    public void saveTo(File file, History history) {
        try(
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))
        ) {
            output.writeObject(history);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public History loadFrom(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
        return (History) input.readObject();
    }
}
