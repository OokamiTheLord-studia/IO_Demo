package tk.arktech;

import javax.swing.*;

public class mainprogram {


    private JPanel panel1;

    public static void main() {
//        before.dispose();
        JFrame frame = new JFrame("mainprogram");
        frame.setContentPane(new mainprogram().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
