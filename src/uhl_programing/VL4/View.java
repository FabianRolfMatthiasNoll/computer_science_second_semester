package uhl_programing.VL4;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private JTextField inputX;
    private JTextField inputY;
    private JButton resetButton;
    private JButton okButton;

    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(3, 2));

        JLabel labelX = new JLabel("Input X");
        JLabel labelY = new JLabel("Input Y");

        inputX = new JTextField();
        inputY = new JTextField();

        resetButton = new JButton("Reset");
        okButton = new JButton("OK");

        add(labelX);
        add(inputX);
        add(labelY);
        add(inputY);
        add(resetButton);
        add(okButton);
    }

    public JTextField getInputX() {
        return inputX;
    }

    public JTextField getInputY() {
        return inputY;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getOkButton() {
        return okButton;
    }
}
