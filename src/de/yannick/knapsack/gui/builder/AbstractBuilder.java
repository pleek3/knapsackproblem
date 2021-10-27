package de.yannick.knapsack.gui.builder;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBuilder {

    private final List<MouseAdapter> mouseAdapterList = new ArrayList<>();

    public AbstractBuilder addMouseAdapter(MouseAdapter adapter) {
        this.mouseAdapterList.add(adapter);
        return this;
    }

    public List<MouseAdapter> getMouseAdapterList() {
        return mouseAdapterList;
    }

    public abstract JPanel createPanel();

}
