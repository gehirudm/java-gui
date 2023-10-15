package components;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputWithLabel extends JPanel{
    private final JLabel inputLabel = new javax.swing.JLabel();
    private final JTextField inputField = new javax.swing.JTextField();
    private final String name;
    private String validationErrorText = null;
    private String validationRegex = null;

    public InputWithLabel(String label) {
        this.name = label;
        setPreferredSize(new java.awt.Dimension(380, 30));
        setLayout(new java.awt.BorderLayout());

        inputLabel.setText(label);
        inputLabel.setPreferredSize(new java.awt.Dimension(100, 16));

        add(inputLabel, java.awt.BorderLayout.LINE_START);
        add(inputField, java.awt.BorderLayout.CENTER);
    }

    public InputWithLabel setValidator(String regex, String errorText) {
        validationErrorText = errorText;
        validationRegex = regex;

        inputField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                System.out.println("Validation on " + name);
                return isInputValid();
            }
        });

        return this;
    }

    public boolean isInputValid() {
        if (inputField == null || validationRegex == null) return false;
        return inputField.getText().matches(validationRegex);
    }

    public String getValidationErrorText() {
        return validationErrorText;
    }

    public String getText() {
        return inputField.getText();
    }

    public InputWithLabel setText(String value) {
        inputField.setText(value);
        return this;
    }

    public String getName() {
        return name;
    }
}
