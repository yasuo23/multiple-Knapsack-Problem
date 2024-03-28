import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class app extends JFrame {
    private JComboBox<String> algorithmComboBox;
    private JSpinner knapsackSpinner;
    private JSpinner weightSpinnerMin;
    private JSpinner weightSpinnerMax;
    private JTextArea outputTextArea;

    public app() {
        setTitle("Knapsack Problem Solver");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Algorithm selection
        JLabel algorithmLabel = new JLabel("Algorithm:");
        algorithmComboBox = new JComboBox<>();
        algorithmComboBox.addItem("DFS");
        algorithmComboBox.addItem("Dynamic Programming");
        panel.add(algorithmLabel);
        panel.add(algorithmComboBox);

        // Number of knapsacks selection
        JLabel knapsackLabel = new JLabel("Number of Knapsacks:");
        SpinnerModel knapsackModel = new SpinnerNumberModel(1, 1, 10, 1);
        knapsackSpinner = new JSpinner(knapsackModel);
        panel.add(knapsackLabel);
        panel.add(knapsackSpinner);

        // Weight interval selection
        JLabel weightLabel = new JLabel("Weight Interval:");
        JPanel weightPanel = new JPanel();
        weightPanel.setLayout(new GridLayout(1, 4));
        weightSpinnerMin = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        weightSpinnerMax = new JSpinner(new SpinnerNumberModel(10, 1, 100, 1));
        weightPanel.add(weightSpinnerMin);
        weightPanel.add(new JLabel("to"));
        weightPanel.add(weightSpinnerMax);
        weightPanel.add(new JLabel("(inclusive)"));
        panel.add(weightLabel);
        panel.add(weightPanel);

        // Button to solve
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveKnapsackProblem();
            }
        });
        panel.add(new JLabel());
        panel.add(solveButton);

        // Output area
        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane);
        panel.add(new JLabel());

        add(panel);
    }

    private void solveKnapsackProblem() {
        String algorithm = (String) algorithmComboBox.getSelectedItem();
        int numKnapsacks = (int) knapsackSpinner.getValue();
        int weightMin = (int) weightSpinnerMin.getValue();
        int weightMax = (int) weightSpinnerMax.getValue();

        // Solve the knapsack problem based on selected algorithm and parameters
        List<Knapsack> knapsacks = solveKnapsack(algorithm, numKnapsacks, weightMin, weightMax);

        // Display the knapsacks with their objects
        outputTextArea.setText("");
        for (int i = 0; i < knapsacks.size(); i++) {
            Knapsack knapsack = knapsacks.get(i);
            outputTextArea.append("Knapsack " + (i + 1) + ":\n");
            outputTextArea.append(knapsack.toString() + "\n");
        }
    }

    private List<Knapsack> solveKnapsack(String algorithm, int numKnapsacks, int weightMin, int weightMax) {
        // Implement your knapsack solving algorithm here
        // This is just a placeholder
        List<Knapsack> knapsacks = new ArrayList<>();
        for (int i = 0; i < numKnapsacks; i++) {
            int weight = (int) (Math.random() * (weightMax - weightMin + 1)) + weightMin;
            Knapsack knapsack = new Knapsack(weight);
            knapsacks.add(knapsack);
        }
        return knapsacks;
    }


    }


class Knapsack {
    private int weight;
    private List<Object> objects;

    public Knapsack(int weight) {
        this.weight = weight;
        this.objects = new ArrayList<>();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void addObject(Object object) {
        objects.add(object);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Weight: ").append(weight).append("\n");
        sb.append("Objects: ").append(objects.toString()).append("\n");
        return sb.toString();
    }
}
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//public class app extends JFrame {
//    private JComboBox<String> algorithmComboBox;
//    private JSpinner knapsackSpinner;
//    private JSpinner weightSpinnerMin;
//    private JSpinner weightSpinnerMax;
//    private JTextArea outputTextArea;
//
//    public app() {
//        setTitle("Knapsack Problem Solver");
//        setSize(600, 400);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
//        setLocationRelativeTo(null); // Center the window
//
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//        mainPanel.setBackground(Color.decode("#303030")); // Dark background color
//
//        JPanel inputPanel = new JPanel();
//        inputPanel.setLayout(new GridLayout(5, 2, 10, 10)); // Grid layout with gaps
//        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding
//        inputPanel.setBackground(Color.decode("#424242")); // Darker background color
//
//        // Algorithm selection
//        JLabel algorithmLabel = new JLabel("Algorithm:");
//        algorithmLabel.setForeground(Color.WHITE); // Text color
//        algorithmComboBox = new JComboBox<>();
//        algorithmComboBox.addItem("DFS");
//        algorithmComboBox.addItem("Dynamic Programming");
//        inputPanel.add(algorithmLabel);
//        inputPanel.add(algorithmComboBox);
//
//        // Number of knapsacks selection
//        JLabel knapsackLabel = new JLabel("Number of Knapsacks:");
//        knapsackLabel.setForeground(Color.WHITE); // Text color
//        SpinnerModel knapsackModel = new SpinnerNumberModel(1, 1, 10, 1);
//        knapsackSpinner = new JSpinner(knapsackModel);
//        inputPanel.add(knapsackLabel);
//        inputPanel.add(knapsackSpinner);
//
//        // Weight interval selection
//        JLabel weightLabel = new JLabel("Weight Interval:");
//        weightLabel.setForeground(Color.WHITE); // Text color
//        JPanel weightPanel = new JPanel();
//        weightPanel.setLayout(new GridLayout(1, 4));
//        weightPanel.setBackground(Color.decode("#424242")); // Darker background color
//        weightSpinnerMin = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
//        weightSpinnerMax = new JSpinner(new SpinnerNumberModel(10, 1, 100, 1));
//        weightPanel.add(weightSpinnerMin);
//        weightPanel.add(new JLabel("to"));
//        weightPanel.add(weightSpinnerMax);
//        weightPanel.add(new JLabel("(inclusive)"));
//        inputPanel.add(weightLabel);
//        inputPanel.add(weightPanel);
//
//        // Button to solve
//        JButton solveButton = new JButton("Solve");
//        solveButton.setBackground(Color.decode("#FFA000")); // Orange color
//        solveButton.setForeground(Color.WHITE); // Text color
//        solveButton.setFocusPainted(false); // Remove focus border
//        solveButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                solveKnapsackProblem();
//            }
//        });
//        inputPanel.add(new JLabel());
//        inputPanel.add(solveButton);
//
//        mainPanel.add(inputPanel, BorderLayout.NORTH);
//
//        // Output area
//        outputTextArea = new JTextArea();
//        outputTextArea.setEditable(false); // Disable editing
//        outputTextArea.setBackground(Color.decode("#212121")); // Dark background color
//        outputTextArea.setForeground(Color.WHITE); // Text color
//        outputTextArea.setMargin(new Insets(10, 10, 10, 10)); // Margin
//        JScrollPane scrollPane = new JScrollPane(outputTextArea);
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
//
//        add(mainPanel);
//    }
//
//    private void solveKnapsackProblem() {
//        String algorithm = (String) algorithmComboBox.getSelectedItem();
//        int numKnapsacks = (int) knapsackSpinner.getValue();
//        int weightMin = (int) weightSpinnerMin.getValue();
//        int weightMax = (int) weightSpinnerMax.getValue();
//
//        // Solve the knapsack problem based on selected algorithm and parameters
//        List<Knapsack> knapsacks = solveKnapsack(algorithm, numKnapsacks, weightMin, weightMax);
//
//        // Display the knapsacks with their objects
//        outputTextArea.setText("");
//        for (int i = 0; i < knapsacks.size(); i++) {
//            Knapsack knapsack = knapsacks.get(i);
//            outputTextArea.append("Knapsack " + (i + 1) + ":\n");
//            outputTextArea.append(knapsack.toString() + "\n");
//        }
//    }
//
//    private List<Knapsack> solveKnapsack(String algorithm, int numKnapsacks, int weightMin, int weightMax) {
//        // Implement your knapsack solving algorithm here
//        // This is just a placeholder
//        List<Knapsack> knapsacks = new ArrayList<>();
//        for (int i = 0; i < numKnapsacks; i++) {
//            int weight = (int) (Math.random() * (weightMax - weightMin + 1)) + weightMin;
//            Knapsack knapsack = new Knapsack(weight);
//            knapsacks.add(knapsack);
//        }
//        return knapsacks;
//    }
//
//
//}
//
//
//class Knapsack {
//    private int weight;
//    private List<Object> objects;
//
//    public Knapsack(int weight) {
//        this.weight = weight;
//        this.objects = new ArrayList<>();
//    }
//
//    public int getWeight() {
//        return weight;
//    }
//
//    public void setWeight(int weight) {
//        this.weight = weight;
//    }
//
//    public List<Object> getObjects() {
//        return objects;
//    }
//
//    public void addObject(Object object) {
//        objects.add(object);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Weight: ").append(weight).append("\n");
//        sb.append("Objects: ").append(objects.toString()).append("\n");
//        return sb.toString();
//    }
//}
