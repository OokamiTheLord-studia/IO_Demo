package tk.arktech;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZmienHaslo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField textField1;
    private JLabel label;
    private String pesel;

    private boolean validateInput(JTextField textField)
    {
        var v = textField.getInputVerifier();
        return v.verify(textField);
    }

    public ZmienHaslo(String imie, String nazwisko, String pesel) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        label.setText("<html><center>Czy na pewno chcesz zmienic haslo uzytkownika " + imie +" " + nazwisko + "?</center></html>");
        this.pesel = pesel;

        textField1.setInputVerifier(new InputVerifier() {

            public boolean verify(JComponent input) {

                String textToVerify = ((JTextField) input).getText();

                Pattern p = Pattern.compile("[a-z0-9A-Z]{3,}");
                Matcher m = p.matcher(textToVerify);
                if (m.matches()) {
//                    setComponentValue(textToVerify);
                    return true;
                }
                else {
                    return false;
                }

            }

            @Override
            public boolean shouldYieldFocus(JComponent source, JComponent target) {
                return true;
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        if(validateInput(textField1)) {
            WeaponSystem.changePassword(Main.curuser, pesel, textField1.getText());
            dispose();
        }
        else
        {
            IncorrectField.main(new String[]{"Nowe Haslo"});
        }


    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ZmienHaslo dialog = new ZmienHaslo(args[0], args[1], args[2]);
        dialog.pack();
        dialog.setVisible(true);
//        System.exit(0);
    }
}
