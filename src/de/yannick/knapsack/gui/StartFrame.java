package de.yannick.knapsack.gui;

import de.yannick.knapsack.KnapsackSolver;
import de.yannick.knapsack.Main;
import de.yannick.knapsack.gui.builder.impl.JButtonBuilder;
import de.yannick.knapsack.gui.builder.impl.JLabelBuilder;
import de.yannick.knapsack.gui.builder.impl.JListBuilder;
import de.yannick.knapsack.gui.builder.impl.JTextFieldBuilder;
import de.yannick.knapsack.models.ItemStorage;
import de.yannick.knapsack.models.Knapsack;
import de.yannick.knapsack.utils.Util;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartFrame extends JFrame {

    private ItemStorage[] itemStorages;
    private KnapsackSolver knapsackSolver;
    private JPanel contentList, solutionList, totalValuePanel, totalWeightPanel;

    public StartFrame() {
        initBeforeStart();
        initFrame();
        initPanels();
        resetFrame();
    }

    private void initBeforeStart() {
        this.itemStorages = Util.generateRandomItems(Main.getSettings().getAmountOfItems());
    }

    private void initFrame() {
        this.setTitle("Knapsack Problem Solver");
        this.setSize(750 + 200, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
    }

    private void initPanels() {
        JPanel settings = new JButtonBuilder(280, 500, 100, 110).setTitle("Einstellungen").addMouseAdapter(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        SwingUtilities.invokeLater(() -> {
                            new SettingsFrame();
                        });
                    }
                }).createPanel();

        JPanel button = new JButtonBuilder(400, 500, 100, 100).setTitle("Start").addMouseAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                start();
                System.out.println("start");
            }
        }).createPanel();

        JPanel reset = new JButtonBuilder(500, 500, 100, 100).setTitle("Reset").addMouseAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetFrame();
                System.out.println("reset");
            }
        }).createPanel();

        JPanel availableText = new JLabelBuilder(350, 10, 30, 150).setText("Verfügbare Objekte").createPanel();
        JPanel chooseText = new JLabelBuilder(350, 210, 30, 160).setText("Ausgewählte Objekte").createPanel();

        this.contentList = new JListBuilder(180, 50, 100, 500).createPanel();
        this.solutionList = new JListBuilder(180, 250, 100, 500).createPanel();

        this.totalValuePanel = new JTextFieldBuilder(280, 380, 0, 0).setText("0").setUsePreferredSize(
                true).createPanel();
        this.totalWeightPanel = new JTextFieldBuilder(420, 380, 0, 0).setText("0").setUsePreferredSize(
                true).createPanel();

        JPanel totalValueText = new JLabelBuilder(230, 420, 30, 150).setText("Max Wert").createPanel();
        JPanel totalWeightText = new JLabelBuilder(380, 420, 30, 150).setText("Max Gewicht").createPanel();


        this.add(totalValueText);
        this.add(totalWeightText);
        this.add(availableText);
        this.add(chooseText);
        this.add(contentList);
        this.add(solutionList);
        this.add(totalValuePanel);
        this.add(totalWeightPanel);
        this.add(settings);
        this.add(button);
        this.add(reset);
    }

    private void resetFrame() {
        ((JList) this.contentList.getComponents()[0]).setListData(new String[100]);
        ((JList) this.solutionList.getComponents()[0]).setListData(new String[100]);
        ((JTextField) this.totalWeightPanel.getComponents()[0]).setText("0");
        ((JTextField) this.totalValuePanel.getComponents()[0]).setText("0");


        this.itemStorages = Util.generateRandomItems(Main.getSettings().getAmountOfItems());

        String[] content = new String[this.itemStorages.length];

        for (int i = 0; i < this.itemStorages.length; i++) {
            content[i] = this.itemStorages[i].getName();
        }

        ((JList) this.contentList.getComponents()[0]).setListData(content);

        this.setVisible(false);
        this.setVisible(true);
    }

    public void start() {
        Knapsack knapsack = new Knapsack(itemStorages, Main.getSettings().getCapacity());

        this.knapsackSolver = new KnapsackSolver();
        this.knapsackSolver.init(knapsack, Main.getSettings().getSolverType());
        System.out.println("init with " + Main.getSettings().getSolverType().getName());


        this.knapsackSolver.solve();

        String[] solution = new String[this.knapsackSolver.getBestSolutions().size()];

        for (int i = 0; i < this.knapsackSolver.getBestSolutions().size(); i++)
            solution[i] = this.knapsackSolver.getBestSolutions().get(i).getName();

        ((JList) this.solutionList.getComponents()[0]).setListData(solution);

        ((JTextField) this.totalWeightPanel.getComponents()[0]).setText(
                String.valueOf(this.knapsackSolver.getTotalWeight()));
        ((JTextField) this.totalValuePanel.getComponents()[0]).setText(
                String.valueOf(this.knapsackSolver.getTotalValue()));
    }


}
