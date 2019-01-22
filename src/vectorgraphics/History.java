package vectorgraphics;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Stack;

public class History implements Serializable, Iterable<Drawable> {
    Stack<Drawable> painted = new Stack<>();
    Stack<Drawable> undone = new Stack<>();

    public void add(Drawable drawable) {
        painted.push(drawable);
        undone.clear();
    }

    public void undo() {
        undone.push(painted.pop());
    }

    public void redo() {
        painted.push(undone.pop());
    }

    public void clear() {
        for (Drawable d: painted)
            undone.push(d);
        painted.clear();
    }

    @Override
    public Iterator<Drawable> iterator() {
        return new HistoryIterator(this);
    }
}
