package uhl_programing.VL3;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Calculator {
    private JFrame frame;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculator window = new Calculator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Calculator() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 250, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setFont(textField.getFont().deriveFont(Font.BOLD, 24));
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));
        frame.add(panel, BorderLayout.CENTER);

        String[] buttonNames = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "C", "+",
                "=", "", "", ""};

        for (String buttonName : buttonNames) {
            JButton button = new JButton(buttonName);
            button.setFont(button.getFont().deriveFont(Font.BOLD, 18));
            panel.add(button);

            if (buttonName.equals("")) {
                button.setEnabled(false);
                continue;
            }

            button.addActionListener((ActionEvent e) -> {
                String buttonText = e.getActionCommand();

                if (buttonText.equals("C")) {
                    textField.setText("");
                } else if (buttonText.equals("=")) {
                    try {
                        Expression exp = new ExpressionBuilder(textField.getText()).build();
                        double result = exp.evaluate();
                        textField.setText(Double.toString(result));
                    } catch (Exception ex) {
                        textField.setText("Error: Invalid expression");
                    }
                } else {
                    textField.setText(textField.getText() + buttonText);
                }
            });
        }

        frame.pack();
    }
}
