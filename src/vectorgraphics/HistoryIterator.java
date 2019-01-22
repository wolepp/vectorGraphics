package vectorgraphics;

import java.util.Iterator;
import java.util.Stack;

public class HistoryIterator implements Iterator<Drawable> {
    
    History history;
    Stack<Drawable> stacked = new Stack<>();

    public HistoryIterator(History history) {
        this.history = history;
        while (!this.history.painted.empty()) {
            Drawable d = this.history.painted.pop();
            stacked.push(d);
        }
    }

    @Override
    public boolean hasNext() {
        return !stacked.empty();
    }

    @Override
    public Drawable next() {
        Drawable d = stacked.pop();
        history.painted.push(d);
        return d;
    }
}
