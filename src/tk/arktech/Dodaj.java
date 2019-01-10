package tk.arktech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dodaj extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField imieTextField1;
    private JTextField nazwiskoTextField2;
    private JTextField mailTextField3;
    private JPasswordField passwordField1;
    private JTextField loginTextField4;
    private JTextField telefonTextField5;
    private JComboBox<String> comboBox1;
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
    private JButton dodajButton;
    private JTextField peselTextField1;

    private boolean validateInput(JTextField textField)
    {
        var v = textField.getInputVerifier();
        return v.verify(textField);
    }

    private int wyliczPozwolenia()
    {
        int t = 0;


        if(rejestracjaUzytkownikowCheckBox.isSelected()) t|=(0b10000000000000000000>>0);
        if(usuwanieUzytkownikowCheckBox.isSelected()) t|=(0b10000000000000000000>>1);
        if(resetowanieHaselCheckBox.isSelected()) t|=(0b10000000000000000000>>2);
        if(czasoweBlokowanieFunkcjonalnosciCheckBox.isSelected()) t|=(0b10000000000000000000>>3);
        if(czasoweBlokowanieUprawnienCheckBox.isSelected()) t|=(0b10000000000000000000>>4);
        if(zmianaUprawnienCheckBox.isSelected()) t|=(0b10000000000000000000>>5);
        if(wydaniePozwoleniaCheckBox.isSelected()) t|=(0b10000000000000000000>>6);
        if(wydanieKoncesjiCheckBox.isSelected()) t|=(0b10000000000000000000>>7);
        if(sprawdzaniePozwoleniaCheckBox.isSelected()) t|=(0b10000000000000000000>>8);
        if(rejestracjaBroniCheckBox.isSelected()) t|=(0b10000000000000000000>>9);
        if(wyswietlanieWszystkichPozwolenCheckBox.isSelected()) t|=(0b10000000000000000000>>10);
        if(wyswietlanieSpisuBroniCheckBox.isSelected()) t|=(0b10000000000000000000>>11);
        if(dodanieModeluBroniCheckBox.isSelected()) t|=(0b10000000000000000000>>12);
        if(dodanieEgzemplarzaBroniCheckBox.isSelected()) t|=(0b10000000000000000000>>13);
        if(narodzinyBroniCheckBox.isSelected()) t|=(0b10000000000000000000>>14);
        if(modyfikacjaHistoriiBroniCheckBox.isSelected()) t|=(0b10000000000000000000>>15);
        if(zniszczenieBroniCheckBox.isSelected()) t|=(0b10000000000000000000>>16);
        if(przegladanieArchiwumCheckBox.isSelected()) t|=(0b10000000000000000000>>17);
        if(sledzenieBroniCheckBox.isSelected()) t|=(0b10000000000000000000>>18);
        if(eksportCheckBox.isSelected()) t|=(0b10000000000000000000>>19);

        return t;
    }

    public Dodaj() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        peselTextField1.setInputVerifier(new InputVerifier() {

            public boolean verify(JComponent input) {

                String textToVerify = ((JTextField) input).getText();

                Pattern p = Pattern.compile("[0-9]{11}");
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

        imieTextField1.setInputVerifier(new InputVerifier() {

            public boolean verify(JComponent input) {

                String textToVerify = ((JTextField) input).getText();

                Pattern p = Pattern.compile("[A-ZĘÓĄŚŁŻŹĆŃ][a-zęóąśłżźćń]*");
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

        nazwiskoTextField2.setInputVerifier(new InputVerifier() {

            public boolean verify(JComponent input) {

                String textToVerify = ((JTextField) input).getText();

                Pattern p = Pattern.compile("[A-ZĘÓĄŚŁŻŹĆŃ][a-zęóąśłżźćń]*");
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

        mailTextField3.setInputVerifier(new InputVerifier() {

            public boolean verify(JComponent input) {

                String textToVerify = ((JTextField) input).getText();

                Pattern p = Pattern.compile("[a-z]*@[a-z]*[.][a-z]*");
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

        loginTextField4.setInputVerifier(new InputVerifier() {

            public boolean verify(JComponent input) {

                String textToVerify = ((JTextField) input).getText();

                Pattern p = Pattern.compile("[a-z0-9]{3,}");
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

        passwordField1.setInputVerifier(new InputVerifier() {

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

//        telefonTextField5
        telefonTextField5.setInputVerifier(new InputVerifier() {

            public boolean verify(JComponent input) {

                String textToVerify = ((JTextField) input).getText();

                Pattern p = Pattern.compile("[+][0-9]{11}");
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

        var sql = SQL.getInstance();
        var array = sql.getAllGroups();
        array.forEach(
                (String x) -> comboBox1.addItem(x)
        );
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = "";
                if(!validateInput(peselTextField1)) s = s + "PESEL ";
                if(!validateInput(imieTextField1)) s = s + "Imie ";
                if(!validateInput(nazwiskoTextField2)) s = s + "Nazwisko ";
                if(!validateInput(mailTextField3)) s = s + "Mail ";
                if(!validateInput(loginTextField4)) s = s + "Login ";
                if(!validateInput(passwordField1)) s = s + "Haslo ";
                if(!validateInput(telefonTextField5)) s = s + "Telefon ";

                if(s.equals(""))
                {
                    var sql = SQL.getInstance();

                    String nazwaGrupy = (String)comboBox1.getSelectedItem();

                    int idGrupy = sql.getGroupIdByName(nazwaGrupy);

                    sql.adduser(peselTextField1.getText(), imieTextField1.getText(), nazwiskoTextField2.getText(), mailTextField3.getText(), loginTextField4.getText(), passwordField1.getText(), telefonTextField5.getText(), wyliczPozwolenia(), pozwolenieNaBronCheckBox.isSelected(), idGrupy);
                }
                else
                {
                    IncorrectField.main(new String[]{s});
                }

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

    private void createUIComponents() {
    }
}
