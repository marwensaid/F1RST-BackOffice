package org.mbds.wolf.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by BENATHMANE on 26/01/2016.
 */

public class TerminalAddUserJFrame extends JFrame implements ActionListener {
    public static String title = "meCoin Terminal";

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
        gridBagConstraintsIdY.insets = new Insets(0, 0, 300, 480);
        gridBagConstraintsIdY.gridx = 16;
        gridBagConstraintsIdY.gridy = 3;
        mainPanel.add(idAccountY, gridBagConstraintsIdY);

        GridBagConstraints gridBagConstraintsIdX = new GridBagConstraints();
        gridBagConstraintsIdX.gridwidth = 50;
        gridBagConstraintsIdX.gridheight = 1;
        gridBagConstraintsIdX.insets = new Insets(0, 120, 300, -200);
        gridBagConstraintsIdX.fill = GridBagConstraints.BOTH;
        gridBagConstraintsIdX.gridx = 16;
        gridBagConstraintsIdX.gridy = 3;
        mainPanel.add(idAccountX, gridBagConstraintsIdX);

        // Login
        GridBagConstraints gridBagConstraintsLoginY = new GridBagConstraints();
        gridBagConstraintsLoginY.gridwidth = 200;
        gridBagConstraintsLoginY.insets = new Insets(0, 0, 300, 480);
        gridBagConstraintsLoginY.gridx = 16;
        gridBagConstraintsLoginY.gridy = 3;
        mainPanel.add(loginY, gridBagConstraintsLoginY);

        GridBagConstraints gridBagConstraintsLoginX = new GridBagConstraints();
        gridBagConstraintsLoginX.gridwidth = 50;
        gridBagConstraintsLoginX.gridheight = 1;
        gridBagConstraintsLoginX.insets = new Insets(0, 120, 300, -200);
        gridBagConstraintsLoginX.fill = GridBagConstraints.BOTH;
        gridBagConstraintsLoginX.gridx = 16;
        gridBagConstraintsLoginX.gridy = 3;
        mainPanel.add(loginX, gridBagConstraintsLoginX);

        // Password
        GridBagConstraints gridBagConstraintsPasswordY = new GridBagConstraints();
        gridBagConstraintsPasswordY.gridwidth = 200;
        gridBagConstraintsPasswordY.insets = new Insets(0, 0, 150, 480);
        gridBagConstraintsPasswordY.gridx = 16;
        gridBagConstraintsPasswordY.gridy = 3;
        mainPanel.add(passwordY, gridBagConstraintsPasswordY);

        GridBagConstraints gridBagConstraintsPasswordX = new GridBagConstraints();
        gridBagConstraintsPasswordX.gridwidth = 190;
        gridBagConstraintsPasswordX.gridheight = 10;
        gridBagConstraintsPasswordX.insets = new Insets(80, 120, 370, -200);
        gridBagConstraintsPasswordX.fill = GridBagConstraints.BOTH;
        gridBagConstraintsPasswordX.gridx = 16;
        gridBagConstraintsPasswordX.gridy = 3;
        mainPanel.add(passwordX, gridBagConstraintsPasswordX);

        //balance
        GridBagConstraints gridBagConstraintsBalanceY = new GridBagConstraints();
        gridBagConstraintsBalanceY.gridwidth = 200;
        gridBagConstraintsBalanceY.insets = new Insets(0, 0, 300, 480);
        gridBagConstraintsBalanceY.gridx = 16;
        gridBagConstraintsBalanceY.gridy = 3;
        mainPanel.add(balanceY, gridBagConstraintsBalanceY);

        GridBagConstraints gridBagConstraintsBalanceX = new GridBagConstraints();
        gridBagConstraintsBalanceX.gridwidth = 50;
        gridBagConstraintsBalanceX.gridheight = 1;
        gridBagConstraintsBalanceX.insets = new Insets(0, 120, 300, -200);
        gridBagConstraintsBalanceX.fill = GridBagConstraints.BOTH;
        gridBagConstraintsBalanceX.gridx = 16;
        gridBagConstraintsBalanceX.gridy = 3;
        mainPanel.add(balanceX, gridBagConstraintsBalanceX);

        // button insert
        GridBagConstraints gridBagConstraintsButtonInsert = new GridBagConstraints();
        gridBagConstraintsButtonInsert.gridwidth = 200;
        gridBagConstraintsButtonInsert.insets = new Insets(0, 0, 0, 480);
        gridBagConstraintsButtonInsert.gridx = 16;
        gridBagConstraintsButtonInsert.gridy = 3;
        mainPanel.add(btnInsert, gridBagConstraintsButtonInsert);

        //back
        GridBagConstraints gridBagConstraintsButtonBack = new GridBagConstraints();
        gridBagConstraintsButtonBack.gridwidth = 200;
        gridBagConstraintsButtonBack.insets = new Insets(0, 0, 0, 280);
        gridBagConstraintsButtonBack.gridx = 16;
        gridBagConstraintsButtonBack.gridy = 3;
        mainPanel.add(btnBack, gridBagConstraintsButtonBack);

        btnInsert.addActionListener(this);

        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }
}

