package de.yannick.knapsack;

import de.yannick.knapsack.gui.StartFrame;

import javax.swing.*;

public class Main {

    private static Settings settings;

    public static void main(String[] args) {
        load();
    }

    private static void load() {
        settings = new Settings();
        SwingUtilities.invokeLater(StartFrame::new);
    }

    public static Settings getSettings() {
        return settings;
    }
}
