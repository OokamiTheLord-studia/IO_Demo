package tk.arktech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainprogram extends JFrame{


    private JPanel panel1;
    private JButton uzytkownicyButton;
    private JButton bronieButton;
    private JButton koncesjeButton;
    private JButton kradzioneButton;
    private JButton eksportButton;
    private JButton grupyButton;
    private JLabel hello;
    private JButton archiwumButton;
    private JButton sledzenieButton;

    public mainprogram() {
        super("Manager Broni v0.1");
        hello.setText("Witaj " + Main.curuser.getImie() + " " + Main.curuser.getNazwisko());
        bronieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Demo.main(null);
            }
        });
        koncesjeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Demo.main(null);
            }
        });
        kradzioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Demo.main(null);
            }
        });
        eksportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Demo.main(null);
            }
        });
        uzytkownicyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Uzytkownicy.main(null);
            }
        });
        archiwumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Demo.main(null);
            }
        });
        sledzenieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Demo.main(null);
            }
        });
        grupyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Demo.main(null);
            }
        });
    }

    public static void main() {
//        before.dispose();
//        JFrame frame = new JFrame("mainprogram");
        JFrame frame = new mainprogram();
        frame.setContentPane(((mainprogram) frame).panel1);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
