package de.yannick.knapsack.gui.builder.impl;

import de.yannick.knapsack.gui.builder.AbstractBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JComboBoxBuilder extends AbstractBuilder {

    private final int height;
    private final int width;
    private final int locationX;
    private final int locationY;

    private String[] content = new String[100];

    public JComboBoxBuilder() {
        this.height = 100;
        this.width = 100;
        this.locationX = 100;
        this.locationY = 100;
    }

    public JComboBoxBuilder(int x, int y, int height, int width) {
        this.height = height;
        this.width = width;
        this.locationX = x;
        this.locationY = y;
    }

    public JComboBoxBuilder setContent(List<String> list) {
        this.content = new String[list.size()];
        for (String string : list)
            this.content[list.indexOf(string)] = string;

        return this;
    }

    public JComboBoxBuilder setContent(String[] strings) {
        this.content = strings;
        return this;
    }

    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();

        panel.setSize(new Dimension(this.width, this.height));
        panel.setLocation(new Point(this.locationX, this.locationY));
        panel.add(new JComboBox(this.content));
        return panel;
    }
}
