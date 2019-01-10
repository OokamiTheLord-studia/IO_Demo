package tk.arktech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Uzytkownicy extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTable table1;
    private JButton dodajButton;
    private JButton kasujButton;
    private JButton zmienHasloButton;
    private JButton edytujButton;
    private JButton blokadyButton;
    private javax.swing.table.DefaultTableModel model1;



//    public class tabelka extends JTable
//    {
//        public boolean isCellEditable(int row, int column)
//        {
//            return false;
//        }
//
//    }

    public Uzytkownicy() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        model1 = new javax.swing.table.DefaultTableModel(
                new String [] {
                        "Imie", "Nazwisko", "Mail", "Login", "Telefon", "Pozwolenia", "Pozwolenie na bron", "Grupa"
                }
                ,0
        );


        table1.setModel(model1);
        if(Main.curuser.hasPermission(Permission.WWP))
        {
            var sql = SQL.getInstance();
            ArrayList<String[]> array = sql.getAllUsers();

            array.forEach(
                    (String[] x) -> model1.addRow(x)
            );

        }
        else
        {
            NiewystarczajaceUprawnienia.main(null);
        }
        table1.changeSelection(0,0,false,false);

        edytujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Demo.main(null);
            }
        });
        blokadyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Demo.main(null);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
        Uzytkownicy dialog = new Uzytkownicy();
        dialog.pack();
        dialog.setVisible(true);
//        System.exit(0);
    }

    private void createUIComponents() {
        table1 = new JTable() {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }

            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);

                try {
                    tip = getValueAt(rowIndex, colIndex).toString();
                } catch (RuntimeException e1) {
                    //catch null pointer exception if mouse is over an empty line
                }

                return tip;
            }
        };
    }
}
