package components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DialogWithInputs extends JDialog {
    private JButton confirmBtn;
    private JButton cancelBtn;
    private JPanel inputPanel = new javax.swing.JPanel();
    
    private ArrayList<InputWithLabel> inputs = new ArrayList<InputWithLabel>();
    private JPanel dialogBody = new javax.swing.JPanel();

    public DialogWithInputs(JFrame parent, String dialogTitle, String dialogHeader, Consumer<Map<String, List<InputWithLabel>>> confirmAction) {
        confirmBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        confirmBtn.setText("Confirm");
        cancelBtn.setText("Cancel");

        cancelBtn.addActionListener(e -> {
            for (InputWithLabel inputWithLabel : inputs) {
                inputWithLabel.setText("");
            }
            setVisible(false);
        });

        confirmBtn.addActionListener(e -> {
            // Check if all inputs are valid
            for (InputWithLabel inputWithLabel : inputs) {
                if (!inputWithLabel.isInputValid()) {
                    JOptionPane.showMessageDialog(this, "Invalid value for " + inputWithLabel.getName() + "\n" + inputWithLabel.getValidationErrorText(), "Invalid Input", JOptionPane.OK_CANCEL_OPTION);
                    return;
                }
            }

            var inputData = inputs.stream().collect(Collectors.groupingBy(InputWithLabel::getName));

            confirmAction.accept(inputData);
        });

        var headerLabel = new javax.swing.JLabel();
        headerLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText(dialogHeader);

        setTitle(dialogTitle);
        getContentPane()
                .setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        dialogBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dialogBody.setLayout(new java.awt.BorderLayout());

        inputPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setLayout(new java.awt.GridLayout(4, 0));

        // inputPanel.setPreferredSize(new Dimension(50, 100));

        var buttonContainer = new javax.swing.JPanel();
        buttonContainer.setLayout(new java.awt.GridLayout(1, 0, 10, 0));
        buttonContainer.add(confirmBtn);
        buttonContainer.add(cancelBtn);

        dialogBody.add(headerLabel, java.awt.BorderLayout.PAGE_START);
        dialogBody.add(inputPanel, java.awt.BorderLayout.CENTER);
        dialogBody.add(buttonContainer, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(dialogBody);

        pack();
    }

    public DialogWithInputs setConfirmBtnText(String value) {
        confirmBtn.setText(value);
        return this;
    }

    public DialogWithInputs setCancelBtnText(String value) {
        cancelBtn.setText(value);
        return this;
    }

    public DialogWithInputs addInput(InputWithLabel input) {
        System.out.println(input.getName());
        inputs.add(input);
        inputPanel.add(input);
        inputPanel.setLayout(new java.awt.GridLayout(inputs.size(), 0, 5, 10));
        pack();
        return this;
    }

    public void clearAll() {
        for (InputWithLabel inputWithLabel : inputs) {
            inputWithLabel.setText("");
        }
    }
}
