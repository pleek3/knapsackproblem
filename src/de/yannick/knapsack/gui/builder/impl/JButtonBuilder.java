package de.yannick.knapsack.gui.builder.impl;

import de.yannick.knapsack.gui.builder.AbstractBuilder;

import javax.swing.*;
import java.awt.*;

public class JButtonBuilder extends AbstractBuilder {

    private final int height;
    private final int width;
    private final int locationX;
    private final int locationY;

    private String title = "";

    public JButtonBuilder() {
        this.height = 100;
        this.width = 100;
        this.locationX = 100;
        this.locationY = 100;
    }

    public JButtonBuilder(int x, int y, int height, int width) {
        this.height = height;
        this.width = width;
        this.locationX = x;
        this.locationY = y;
    }

    public JButtonBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(this.width, this.height));
        panel.setLocation(new Point(this.locationX, this.locationY));

        JButton button = new JButton(this.title);

        if (!getMouseAdapterList().isEmpty())
            getMouseAdapterList().forEach(button::addMouseListener);

        panel.add(button);
        return panel;
    }
}
