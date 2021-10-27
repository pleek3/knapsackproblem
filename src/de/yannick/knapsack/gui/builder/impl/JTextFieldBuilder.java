package de.yannick.knapsack.gui.builder.impl;

import de.yannick.knapsack.gui.builder.AbstractBuilder;

import javax.swing.*;
import java.awt.*;

public class JTextFieldBuilder extends AbstractBuilder {

    private final int height;
    private final int width;
    private final int locationX;
    private final int locationY;

    private String text = "";
    private boolean editable = false;
    private boolean usePreferredSize = false;


    public JTextFieldBuilder() {
        this.height = 100;
        this.width = 100;
        this.locationX = 100;
        this.locationY = 100;
    }

    public JTextFieldBuilder(int x, int y, int height, int width) {
        this.height = height;
        this.width = width;
        this.locationX = x;
        this.locationY = y;
    }

    public JTextFieldBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public JTextFieldBuilder setEditable(boolean editable) {
        this.editable = editable;
        return this;
    }

    public JTextFieldBuilder setUsePreferredSize(boolean usePreferredSize) {
        this.usePreferredSize = usePreferredSize;
        return this;
    }


    @Override
    public JPanel createPanel() {
        JTextField textField = new JTextField(text);
        textField.setEditable(this.editable);
        textField.setColumns(10);

        JPanel panel = new JPanel();
        panel.setSize((this.usePreferredSize ? textField.getPreferredSize() : new Dimension(this.width, this.height)));
        panel.setLocation(new Point(this.locationX, this.locationY));
        panel.add(textField);
        return panel;
    }
}
