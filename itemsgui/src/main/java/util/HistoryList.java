package util;

import java.util.ArrayList;
import java.util.List;

public class HistoryList<T> {
    private ArrayList<ArrayList<T>> historyStack = new ArrayList<>();;
    private int historyHead = 0;

    public HistoryList() {
        historyStack.add(new ArrayList<T>());
    }

    public HistoryList(List<T> items) {
        historyStack.add(new ArrayList<T>(items));
    }

    public HistoryList<T> addMany(List<T> items) {
        var newList = new ArrayList<T>(historyStack.get(historyHead));

        for (T t : items) {
            newList.add(t);
        }

        historyStack.add(newList);
        historyHead++;

        return this;
    }

    public HistoryList<T> add(T item) {
        var newList = new ArrayList<T>(historyStack.get(historyHead));

        newList.add(item);

        historyStack.add(newList);
        historyHead++;
        return this;
    }

    public HistoryList<T> removeMany(List<T> items) {
        var newList = new ArrayList<T>(historyStack.get(historyHead));

        for (T t : items) {
            newList.remove(t);
        }

        historyStack.add(newList);
        historyHead++;
        return this;
    }

    public HistoryList<T> remove(T item) {
        var newList = new ArrayList<T>(historyStack.get(historyHead));

        newList.remove(item);

        historyStack.add(newList);
        historyHead++;
        return this;
    }

    public int size() {
        return historyStack.get(historyHead).size();
    }

    public boolean isEmpty() {
        return historyStack.get(historyHead).isEmpty();
    }

    public HistoryList<T> undo() {
        if (historyHead < (historyStack.get(historyHead).size() - 1)) {
            historyHead++;
        }
        return this;
    }

    public HistoryList<T> redo() {
        if (historyHead > 0) {
            historyHead--;
        }
        return this;
    }
}
