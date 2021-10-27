package de.yannick.knapsack.gui;

import de.yannick.knapsack.KnapsackSolver;
import de.yannick.knapsack.Main;
import de.yannick.knapsack.gui.builder.impl.JButtonBuilder;
import de.yannick.knapsack.gui.builder.impl.JComboBoxBuilder;
import de.yannick.knapsack.gui.builder.impl.JLabelBuilder;
import de.yannick.knapsack.gui.builder.impl.JTextFieldBuilder;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingsFrame extends JFrame {

    private JPanel algorithmPanel, capacityPanel, amountOfItemsPanel;

    public SettingsFrame() {
        initFrame();
        initDefaultSettings();
        initPanels();
    }

    private void initFrame() {
        this.setTitle("Knapsack Problem Solver - Settings");
        this.setSize(750, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
    }

    private void initDefaultSettings() {
        this.algorithmPanel = new JComboBoxBuilder(20, 50, 100, 200).setContent(
                new String[]{"Rekursiver Algorithmus", "Dynamischer Algorithmus"}).createPanel();
        ((JComboBox) this.algorithmPanel.getComponents()[0]).setSelectedIndex(
                (Main.getSettings().getSolverType().ordinal()));


        this.capacityPanel = new JTextFieldBuilder(451, 20, 20, 200).setText(" ").setEditable(true).createPanel();
        ((JTextField) this.capacityPanel.getComponents()[0]).setText(String.valueOf(Main.getSettings().getCapacity()));

        this.amountOfItemsPanel = new JTextFieldBuilder(451, 100, 20, 200).setText(" ").setEditable(true).createPanel();
        ((JTextField) this.amountOfItemsPanel.getComponents()[0]).setText(
                String.valueOf(Main.getSettings().getAmountOfItems()));

        this.add(algorithmPanel);
        this.add(amountOfItemsPanel);
        this.add(capacityPanel);
    }

    private void initPanels() {
        JPanel algorithmText = new JLabelBuilder(50, 20, 100, 100).setText("Sotieralgorithmus").createPanel();
        JPanel capacityText = new JLabelBuilder(400, 20, 50, 50).setText("Kapazität").createPanel();
        JPanel amountOfItemsText = new JLabelBuilder(355, 100, 50, 105).setText("Anzahl Objekte").createPanel();


        JPanel save = new JButtonBuilder(300, 320, 100, 100).setTitle("Speichern").addMouseAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!saveSettings())
                    return;
                dispose();
            }
        }).createPanel();


        this.add(algorithmText);
        this.add(capacityText);
        this.add(amountOfItemsText);
        this.add(save);
    }


    private boolean saveSettings() {
        KnapsackSolver.SolverType type = KnapsackSolver.SolverType.getTypeByName(
                String.valueOf(((JComboBox) this.algorithmPanel.getComponents()[0]).getSelectedItem()));

        if (type == null) {
            JOptionPane.showMessageDialog(this,
                    "Der Algorithmus den du gewählt hast ist nicht verfügbar. \nBitte versuche es erneut!",
                    "Fehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int capacity;
        int amountOfItems;

        try {
            capacity = Integer.parseInt(((JTextField) this.capacityPanel.getComponents()[0]).getText());
            amountOfItems = Integer.parseInt(((JTextField) this.amountOfItemsPanel.getComponents()[0]).getText());

            if (capacity <= 0 || amountOfItems <= 0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Deine Angabe ist fehlerhaft. \nBitte beachte, dass alle Angaben größer als 0 sein müssen.",
                    "Fehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        Main.getSettings().setSolverType(type);
        Main.getSettings().setAmountOfItems(amountOfItems);
        Main.getSettings().setCapacity(capacity);
        return true;
    }


}
