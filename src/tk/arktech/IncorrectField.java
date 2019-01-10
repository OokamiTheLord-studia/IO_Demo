package tk.arktech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncorrectField extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel label;

    public IncorrectField(String jakie) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        label.setText("<html><center>Niektore pola sa niepoprawne:<br>" + jakie +"<br>Popraw je i sproboj jeszcze raz</center></html>");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
        IncorrectField dialog = new IncorrectField(args[0]);
        dialog.pack();
        dialog.setVisible(true);
//        System.exit(0);
    }
}
