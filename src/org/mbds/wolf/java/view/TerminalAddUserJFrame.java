package org.mbds.wolf.java.view;

import org.mbds.wolf.java.SeqlReaderTester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by BENATHMANE on 26/01/2016.
 */

public class TerminalAddUserJFrame extends JFrame implements ActionListener {
    public static String title = "meCoin Terminal";

    // form
    private JPanel mainPanel = new JPanel();

    private JTextField idAccountX = new JTextField();
    private JLabel idAccountY = new JLabel("Id Account : ");

    private JTextField loginX = new JTextField();
    private JLabel loginY = new JLabel("Login : ");

    private JTextField passwordX = new JTextField();
    private JLabel passwordY = new JLabel("Password : ");

    private JTextField balanceX = new JTextField();
    private JLabel balanceY = new JLabel("balance : ");

    private JButton btnInsert = new JButton("Insert");

    private JButton btnBack = new JButton("Back");

    private JButton btnExit = new JButton("Exit");

    // query value
    private String valueId = "";
    private String valueLogin = "";
    private String valuePassword = "";
    private String valueBalance = "";

    private SeqlReaderTester seqlReaderTester;

    private String seqlIdCompte = "";
    private String seqlLogin = "";
    private String seqlPassword = "";
    private String seqlBalance = "";

    private static SeqlReaderTester.MyMessagesObservable myMessages = null;


    public TerminalAddUserJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        add(mainPanel);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{30, 30, 30, 30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{30, 0, 50, 30, 0, 30, 30, 30, 30, 30, 30};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
        mainPanel.setLayout(gridBagLayout);

        // id_user
        GridBagConstraints gridBagConstraintsIdY = new GridBagConstraints();
        gridBagConstraintsIdY.gridwidth = 200;
        gridBagConstraintsIdY.insets = new Insets(0, 0, -60, 550);
        gridBagConstraintsIdY.gridx = 16;
        gridBagConstraintsIdY.gridy = 1;
        mainPanel.add(idAccountY, gridBagConstraintsIdY);

        GridBagConstraints gridBagConstraintsIdX = new GridBagConstraints();
        gridBagConstraintsIdX.gridwidth = 300;
        gridBagConstraintsIdX.insets = new Insets(0, 30, 30, 250);
        gridBagConstraintsIdX.fill = GridBagConstraints.BOTH;
        gridBagConstraintsIdX.gridx = 100;
        gridBagConstraintsIdX.gridy = 3;
        mainPanel.add(idAccountX, gridBagConstraintsIdX);

        // Login
        GridBagConstraints gridBagConstraintsLoginY = new GridBagConstraints();
        gridBagConstraintsLoginY.gridwidth = 200;
        gridBagConstraintsLoginY.insets = new Insets(0, 0, -160, 550);
        gridBagConstraintsLoginY.gridx = 16;
        gridBagConstraintsLoginY.gridy = (int) 1.5;
        mainPanel.add(loginY, gridBagConstraintsLoginY);

        GridBagConstraints gridBagConstraintsLoginX = new GridBagConstraints();
        gridBagConstraintsLoginX.gridwidth = 300;
        gridBagConstraintsLoginX.insets = new Insets(0, 30, 30, 250);
        gridBagConstraintsLoginX.fill = GridBagConstraints.BOTH;
        gridBagConstraintsLoginX.gridx = 16;
        gridBagConstraintsLoginX.gridy = 5;
        mainPanel.add(loginX, gridBagConstraintsLoginX);

        // Password
        GridBagConstraints gridBagConstraintsPasswordY = new GridBagConstraints();
        gridBagConstraintsPasswordY.gridwidth = 200;
        gridBagConstraintsPasswordY.insets = new Insets(0, 0, -260, 550);
        gridBagConstraintsPasswordY.gridx = 16;
        gridBagConstraintsPasswordY.gridy = (int) 1.7;
        mainPanel.add(passwordY, gridBagConstraintsPasswordY);

        GridBagConstraints gridBagConstraintsPasswordX = new GridBagConstraints();
        gridBagConstraintsPasswordX.gridwidth = 300;
        gridBagConstraintsPasswordX.insets = new Insets(0, 30, 30, 250);
        gridBagConstraintsPasswordX.fill = GridBagConstraints.BOTH;
        gridBagConstraintsPasswordX.gridx = 16;
        gridBagConstraintsPasswordX.gridy = 6;
        mainPanel.add(passwordX, gridBagConstraintsPasswordX);

       //balance
        GridBagConstraints gridBagConstraintsBalanceY = new GridBagConstraints();
        gridBagConstraintsBalanceY.gridwidth = 200;
        gridBagConstraintsBalanceY.insets = new Insets(0, 0, -360, 550);
        gridBagConstraintsBalanceY.gridx = 16;
        gridBagConstraintsBalanceY.gridy = (int) 1.9;
        mainPanel.add(balanceY, gridBagConstraintsBalanceY);

       GridBagConstraints gridBagConstraintsBalanceX = new GridBagConstraints();
        gridBagConstraintsBalanceX.gridwidth = 200;
        gridBagConstraintsBalanceX.insets = new Insets(0, 30, 30, 250);
        gridBagConstraintsBalanceX.fill = GridBagConstraints.BOTH;
        gridBagConstraintsBalanceX.gridx = 16;
        gridBagConstraintsBalanceX.gridy = 7;
        mainPanel.add(balanceX, gridBagConstraintsBalanceX);

        // button insert
        GridBagConstraints gridBagConstraintsButtonInsert = new GridBagConstraints();
        gridBagConstraintsButtonInsert.gridwidth = 200;
        gridBagConstraintsButtonInsert.insets = new Insets(0, 0, -460, 350);
        gridBagConstraintsButtonInsert.gridx = 16;
        gridBagConstraintsButtonInsert.gridy = 3;
        mainPanel.add(btnInsert, gridBagConstraintsButtonInsert);

        //back
        GridBagConstraints gridBagConstraintsButtonBack = new GridBagConstraints();
        gridBagConstraintsButtonBack.gridwidth = 200;
        gridBagConstraintsButtonBack.insets = new Insets(0, 0, -460, 200);
        gridBagConstraintsButtonBack.gridx = 16;
        gridBagConstraintsButtonBack.gridy = 3;
        mainPanel.add(btnBack, gridBagConstraintsButtonBack);

        //back
        GridBagConstraints gridBagConstraintsButtonExit = new GridBagConstraints();
        gridBagConstraintsButtonExit.gridwidth = 200;
        gridBagConstraintsButtonExit.insets = new Insets(0, 0, -460, 50);
        gridBagConstraintsButtonExit.gridx = 16;
        gridBagConstraintsButtonExit.gridy = 3;
        mainPanel.add(btnExit, gridBagConstraintsButtonExit);

        btnInsert.addActionListener(this);

        this.setContentPane(mainPanel);
        btnExit.addActionListener(this);
        btnBack.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == btnInsert) {
            valueId = idAccountX.getText();
            valueLogin = loginX.getText();
            valuePassword = passwordX.getText();
            valueBalance = balanceX.getText();

            //seql = "insert into wolf_hce (idCompte, login, password, balance) values(" + valueId + valueLogin + valuePassword + valueBalance + ")";

            seqlIdCompte = "insert into wolf_hce (idCompte) values(" + valueId + ")";
            seqlLogin = "insert into wolf_hce (login) values(" + valueLogin + ")";
            seqlPassword = "insert into wolf_hce (password) values(" + valuePassword + ")";
            seqlBalance = "insert into wolf_hce (balance) values(" + valueBalance + ")";

            if (seqlIdCompte != null && seqlLogin != null && seqlPassword != null && seqlBalance != null && !seqlIdCompte.isEmpty() && !seqlLogin.isEmpty() && !seqlPassword.isEmpty() && !seqlBalance.isEmpty()) {
                Thread thread = new Thread() {
                    public void run() {
                        JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_PLACE_NFC_DEVICE);
                        JOptionPane.showMessageDialog(null, seqlIdCompte);
                        JOptionPane.showMessageDialog(null, seqlLogin);
                        JOptionPane.showMessageDialog(null, seqlPassword);
                        JOptionPane.showMessageDialog(null, seqlBalance);
                        if (SeqlReaderTester.execute(seqlIdCompte, 0) && SeqlReaderTester.execute(seqlLogin, 0) && SeqlReaderTester.execute(seqlPassword, 0) && SeqlReaderTester.execute(seqlBalance, 0)) {

                            idAccountX.setText("");
                            idAccountX.invalidate();

                            loginX.setText("");
                            loginX.invalidate();

                            passwordX.setText("");
                            passwordX.invalidate();

                            balanceX.setText("");
                            balanceX.invalidate();
                        }
                    }
                };
                thread.start();
            } else {
                JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_ERR_BAD_REQUEST);
            }
        } else if (event.getSource() == btnBack) {
            this.dispose();
            SeqlTesterJFrame seqlTesterJFrame = new SeqlTesterJFrame(myMessages);
        } else if (event.getSource() == btnExit) {
            System.exit(0);
        }
    }


}

