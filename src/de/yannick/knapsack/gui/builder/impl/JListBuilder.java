package de.yannick.knapsack.gui.builder.impl;

import de.yannick.knapsack.gui.builder.AbstractBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JListBuilder extends AbstractBuilder {

    private final int height;
    private final int width;
    private final int locationX;
    private final int locationY;

    private String[] content = new String[100];
    private boolean isScrollable = false;

    public JListBuilder() {
        this.height = 100;
        this.width = 100;
        this.locationX = 100;
        this.locationY = 100;
    }

    public JListBuilder(int x, int y, int height, int width) {
        this.height = height;
        this.width = width;
        this.locationX = x;
        this.locationY = y;
    }

    public JListBuilder setContent(List<String> list) {
        this.content = new String[list.size()];
        for (String string : list)
            this.content[list.indexOf(string)] = string;

        return this;
    }

    public JListBuilder setContent(String[] strings) {
        this.content = strings;
        return this;
    }

    public JListBuilder setScrollable(boolean scrollable) {
        this.isScrollable = scrollable;
        return this;
    }


    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();

        panel.setSize(new Dimension(this.width, this.height));
        panel.setLocation(new Point(this.locationX, this.locationY));


        JList<String> jList = new JList<>(this.content);
        jList.setVisibleRowCount(200);
        jList.setPrototypeCellValue(String.format("%100s", ""));

        JScrollPane jScrollPane = null;

        if (isScrollable) {
            jScrollPane = new JScrollPane(jList);
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        }


        panel.add(jList);
        if (isScrollable)
            panel.add(jScrollPane);
        return panel;
    }
}
