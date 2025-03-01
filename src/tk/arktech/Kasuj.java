package tk.arktech;

import javax.swing.*;
import java.awt.event.*;

public class Kasuj extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label;
    private String pesel;

    public Kasuj(String imie, String nazwisko, String pesel) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        label.setText("<html><center>Czy na pewno chcesz skasowac uzytkownika " + imie +" " + nazwisko + "?</center></html>");
        this.pesel = pesel;



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
        if(!WeaponSystem.removeUser(Main.curuser, pesel)) NiewystarczajaceUprawnienia.main(null);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Kasuj dialog = new Kasuj(args[0], args[1], args[2]);
        dialog.pack();
        dialog.setVisible(true);
//        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
