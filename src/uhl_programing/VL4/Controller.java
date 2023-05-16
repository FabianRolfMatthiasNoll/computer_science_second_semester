package uhl_programing.VL4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getInputX().setText("");
                view.getInputY().setText("");
                view.getInputX().setEnabled(true);
                view.getInputY().setEnabled(true);
            }
        });

        view.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textX = view.getInputX().getText();
                String textY = view.getInputY().getText();

                if (textX.isEmpty() && textY.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please make an input.");
                    return;
                }

                try {
                    if (!textX.isEmpty()) {
                        double x = Double.parseDouble(textX);
                        model.setX(x);
                        view.getInputY().setText(String.valueOf(2 * model.getX()));
                        view.getInputX().setEnabled(false);
                    } else {
                        double y = Double.parseDouble(textY);

                        if (y % 2 != 0) {
                            JOptionPane.showMessageDialog(null, "Please enter an even number.");
                            return;
                        }

                        model.setY(y);
                        view.getInputX().setText(String.valueOf(2 / model.getY()));
                        view.getInputY().setEnabled(false);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a number.");
                }
            }
        });
    }
}
