package tk.arktech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JTextField Tlogin;
    private JPanel panel1;
//    private JTextField Thaslo;
    private JButton logujButton;
    private JPasswordField Thaslo;

    public abstract class alistener implements ActionListener
    {
        JFrame frame;

        alistener(JFrame f)
        {
            frame = f;
        }
    }

    public Login() {
        super("Login - Manager Broni v0.1");


        logujButton.addActionListener(new alistener/*ActionListener*/(this) {

            @Override
            public void actionPerformed(ActionEvent e) {
                var sql = SQL.getInstance();
                var usr = sql.login(Tlogin.getText(), Thaslo.getText());
                if(usr!=null)
                {
                    Main.curuser = usr;
                    frame.dispose();


                    mainprogram.main();
//                    dispose();



                }
                else
                {
                    blednehaslo.main(null);
                }
            }
        });

    }

    public static void main(String[] args) {
//        JFrame frame = new JFrame("Login");
        JFrame frame = new Login();

        frame.setContentPane(((Login) frame).panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
