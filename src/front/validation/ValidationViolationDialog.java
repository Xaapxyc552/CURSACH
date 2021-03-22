package front.validation;

import javax.swing.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.Vector;

public class ValidationViolationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JList<ConstraintViolation> violationsList;

    public ValidationViolationDialog(Collection<ConstraintViolation> violations) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        violationsList.setListData(new Vector<>(violations));

        buttonOK.addActionListener(e -> onOK());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
    }

    private void onOK() {
        dispose();
    }
}
