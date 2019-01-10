package tk.arktech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dodaj extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JCheckBox rejestracjaUzytkownikowCheckBox;
    private JCheckBox usuwanieUzytkownikowCheckBox;
    private JCheckBox resetowanieHaselCheckBox;
    private JCheckBox czasoweBlokowanieFunkcjonalnosciCheckBox;
    private JCheckBox czasoweBlokowanieUprawnienCheckBox;
    private JCheckBox zmianaUprawnienCheckBox;
    private JCheckBox wydaniePozwoleniaCheckBox;
    private JCheckBox wydanieKoncesjiCheckBox;
    private JCheckBox sprawdzaniePozwoleniaCheckBox;
    private JCheckBox rejestracjaBroniCheckBox;
    private JCheckBox wyswietlanieWszystkichPozwolenCheckBox;
    private JCheckBox wyswietlanieSpisuBroniCheckBox;
    private JCheckBox dodanieModeluBroniCheckBox;
    private JCheckBox dodanieEgzemplarzaBroniCheckBox;
    private JCheckBox narodzinyBroniCheckBox;
    private JCheckBox modyfikacjaHistoriiBroniCheckBox;
    private JCheckBox zniszczenieBroniCheckBox;
    private JCheckBox przegladanieArchiwumCheckBox;
    private JCheckBox sledzenieBroniCheckBox;
    private JCheckBox eksportCheckBox;
    private JCheckBox pozwolenieNaBronCheckBox;

    public Dodaj() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        Dodaj dialog = new Dodaj();
        dialog.pack();
        dialog.setVisible(true);
//        System.exit(0);
    }
}
