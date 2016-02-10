package org.mbds.wolf.java.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import org.mbds.wolf.java.SeqlReaderTester;

public class SeqlTesterJFrame extends JFrame implements ActionListener, Observer {
    public static String title = "meCoin Terminal";
    private TerminalAddUserJFrame tayjf;
    private static final long serialVersionUID = 1L;
    private JTextField txtInput = new JTextField();
    private JButton btnDebit = new JButton("Debit");
    private JButton btnCredit = new JButton("Credit");
    private JButton btnExit = new JButton("Exit");
    private JButton btnTCredit = new JButton("TestCredit");
    private JButton btnTDebit = new JButton("TestDebit");
    private JTextField jtPLibelle = new JTextField();
    private JLabel jlPLibelle = new JLabel("Libelle : ");
    private JButton btn0 = new JButton("0");
    private JButton btn1 = new JButton("1");
    private JButton btn2 = new JButton("2");
    private JButton btn3 = new JButton("3");
    private JButton btn4 = new JButton("4");
    private JButton btn5 = new JButton("5");
    private JButton btn6 = new JButton("6");
    private JButton btn7 = new JButton("7");
    private JButton btn8 = new JButton("8");
    private JButton btn9 = new JButton("9");
    private JButton btnvirgule = new JButton(",");
    private JButton btnClear = new JButton("C");
    private JButton btnUser = new JButton("G-User");
    private JPanel mainPanel = new JPanel();
    private String seql = "";
    private String seqlBalance = "";
    private String seqlBalanceUpdate = "";
    private String seqlTStamp = "";
    private String seqlLibelle = "";
    private String val = "";
    private String vallibelle = "";
    private String cdobs;
    private int counter = 0;
    private long blc;
    private long diff;
    private long somme = 0;
    private SeqlReaderTester.MyMessagesObservable obs = null;


    public SeqlTesterJFrame(SeqlReaderTester.MyMessagesObservable obs) {
        super();
        if (obs != null) {
            this.obs = obs;
            obs.addObserver(this);
        }
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        add(mainPanel);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{30, 30, 30, 30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{30, 0, 50, 30, 0, 30, 30, 30, 30, 30, 30};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
        mainPanel.setLayout(gridBagLayout);

        GridBagConstraints gbc_txtInput = new GridBagConstraints();
        gbc_txtInput.gridwidth = 19;
        gbc_txtInput.gridheight = 2;
        gbc_txtInput.insets = new Insets(0, 0, 5, 0);
        gbc_txtInput.fill = GridBagConstraints.BOTH;
        gbc_txtInput.gridx = 0;
        gbc_txtInput.gridy = 1;
        mainPanel.add(txtInput, gbc_txtInput);

        GridBagConstraints gbc_btnDebit = new GridBagConstraints();
        btnDebit.setForeground(Color.red);
        gbc_btnDebit.gridwidth = 3;
        gbc_btnDebit.insets = new Insets(0, 0, 50, 5);
        gbc_btnDebit.gridx = 16;
        gbc_btnDebit.gridy = 3;
        mainPanel.add(btnDebit, gbc_btnDebit);

        GridBagConstraints gbc_btnCredit = new GridBagConstraints();
        btnCredit.setForeground(Color.green);
        gbc_btnCredit.gridwidth = 3;
        gbc_btnCredit.insets = new Insets(0, 140, 50, 5);
        gbc_btnCredit.gridx = 16;
        gbc_btnCredit.gridy = 3;
        mainPanel.add(btnCredit, gbc_btnCredit);

        GridBagConstraints gbc_btnExit = new GridBagConstraints();
        btnExit.setForeground(Color.orange);
        gbc_btnExit.insets = new Insets(0, 250, 0, 5);
        gbc_btnExit.gridx = 18;
        gbc_btnExit.gridy = 10;
        mainPanel.add(btnExit, gbc_btnExit);

        GridBagConstraints gbc_btnTCredit = new GridBagConstraints();
        gbc_btnTCredit.insets = new Insets(0, 250, 0, 250);
        gbc_btnTCredit.gridx = 18;
        gbc_btnTCredit.gridy = 10;
        mainPanel.add(btnTCredit, gbc_btnTCredit);

        GridBagConstraints gbc_btnTDebit = new GridBagConstraints();
        gbc_btnTDebit.insets = new Insets(0, 250, 0, 480);
        gbc_btnTDebit.gridx = 18;
        gbc_btnTDebit.gridy = 10;
        mainPanel.add(btnTDebit, gbc_btnTDebit);

        GridBagConstraints gbc_btnUser = new GridBagConstraints();
        gbc_btnUser.insets = new Insets(0, 50, 0, 480);
        gbc_btnUser.gridx = 18;
        gbc_btnUser.gridy = 10;
        mainPanel.add(btnUser, gbc_btnUser);

        GridBagConstraints gbc_jlPLibelle = new GridBagConstraints();
        gbc_jlPLibelle.gridwidth = 200;
        gbc_jlPLibelle.insets = new Insets(0, 0, -500, 480);
        gbc_jlPLibelle.gridx = 18;
        gbc_jlPLibelle.gridy = 3;
        mainPanel.add(jlPLibelle, gbc_jlPLibelle);

        GridBagConstraints gbc_jtPLibelle = new GridBagConstraints();
        gbc_jtPLibelle.gridwidth = 15;
        gbc_jtPLibelle.gridheight = -6;
        gbc_jtPLibelle.insets = new Insets(-20, 0, 5, 0);
        gbc_jtPLibelle.fill = GridBagConstraints.BOTH;
        gbc_jtPLibelle.gridx = 5;
        gbc_jtPLibelle.gridy = 9;
        mainPanel.add(jtPLibelle, gbc_jtPLibelle);
        /** **/
        GridBagConstraints gbc_btn9 = new GridBagConstraints();
        gbc_btn9.gridwidth = 3;
        gbc_btn9.insets = new Insets(0, 0, -60, 60);
        gbc_btn9.gridx = 16;
        gbc_btn9.gridy = 3;
        mainPanel.add(btn9, gbc_btn9);

        GridBagConstraints gbc_btn8 = new GridBagConstraints();
        gbc_btn8.gridwidth = 3;
        gbc_btn8.insets = new Insets(0, 0, -60, 200);
        gbc_btn8.gridx = 16;
        gbc_btn8.gridy = 3;
        mainPanel.add(btn8, gbc_btn8);

        GridBagConstraints gbc_btn7 = new GridBagConstraints();
        gbc_btn7.gridwidth = 3;
        gbc_btn7.insets = new Insets(0, 0, -60, 350);
        gbc_btn7.gridx = 16;
        gbc_btn7.gridy = 3;
        mainPanel.add(btn7, gbc_btn7);

        GridBagConstraints gbc_btn6 = new GridBagConstraints();
        gbc_btn6.gridwidth = 3;
        gbc_btn6.insets = new Insets(0, 0, -160, 60);
        gbc_btn6.gridx = 16;
        gbc_btn6.gridy = 3;
        mainPanel.add(btn6, gbc_btn6);

        GridBagConstraints gbc_btn5 = new GridBagConstraints();
        gbc_btn5.gridwidth = 3;
        gbc_btn5.insets = new Insets(0, 0, -160, 200);
        gbc_btn5.gridx = 16;
        gbc_btn5.gridy = 3;
        mainPanel.add(btn5, gbc_btn5);

        GridBagConstraints gbc_btn4 = new GridBagConstraints();
        gbc_btn4.gridwidth = 3;
        gbc_btn4.insets = new Insets(0, 0, -160, 350);
        gbc_btn4.gridx = 16;
        gbc_btn4.gridy = 3;
        mainPanel.add(btn4, gbc_btn4);

        GridBagConstraints gbc_btn3 = new GridBagConstraints();
        gbc_btn3.gridwidth = 3;
        gbc_btn3.insets = new Insets(0, 0, -260, 60);
        gbc_btn3.gridx = 16;
        gbc_btn3.gridy = 3;
        mainPanel.add(btn3, gbc_btn3);

        GridBagConstraints gbc_btn2 = new GridBagConstraints();
        gbc_btn2.gridwidth = 3;
        gbc_btn2.insets = new Insets(0, 0, -260, 200);
        gbc_btn2.gridx = 16;
        gbc_btn2.gridy = 3;
        mainPanel.add(btn2, gbc_btn2);

        GridBagConstraints gbc_btn1 = new GridBagConstraints();
        gbc_btn1.gridwidth = 3;
        gbc_btn1.insets = new Insets(0, 0, -260, 350);
        gbc_btn1.gridx = 16;
        gbc_btn1.gridy = 3;
        mainPanel.add(btn1, gbc_btn1);

        GridBagConstraints gbc_btnClear = new GridBagConstraints();
        btnClear.setForeground(Color.blue);
        gbc_btnClear.gridwidth = 3;
        gbc_btnClear.insets = new Insets(0, 0, -360, 60);
        gbc_btnClear.gridx = 16;
        gbc_btnClear.gridy = 3;
        mainPanel.add(btnClear, gbc_btnClear);

        GridBagConstraints gbc_btn0 = new GridBagConstraints();
        gbc_btn0.gridwidth = 3;
        gbc_btn0.insets = new Insets(0, 0, -360, 200);
        gbc_btn0.gridx = 16;
        gbc_btn0.gridy = 3;
        mainPanel.add(btn0, gbc_btn0);

        GridBagConstraints gbc_btnv = new GridBagConstraints();
        gbc_btnv.gridwidth = 3;
        gbc_btnv.insets = new Insets(0, 0, -360, 350);
        gbc_btnv.gridx = 16;
        gbc_btnv.gridy = 3;
        mainPanel.add(btnvirgule, gbc_btnv);
        /** **/
        btnClear.addActionListener(this);
        btnDebit.addActionListener(this);
        btnCredit.addActionListener(this);
        btnExit.addActionListener(this);
        btn0.addActionListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btnvirgule.addActionListener(this);
        btnTCredit.addActionListener(this);
        btnTDebit.addActionListener(this);
        btnUser.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnCredit) {
            val = txtInput.getText();
            seqlBalance = "select balance from wolf_hce";
            vallibelle = jtPLibelle.getText();
            if (seqlBalance != null && !seqlBalance.isEmpty()) {
                Thread t = new Thread() {
                    public void run() {
                        JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_PLACE_NFC_DEVICE);
                        cdobs = "balance";
                        if (SeqlReaderTester.execute(seqlBalance, counter++)) {
                            long l = Long.parseLong(val);
                            somme = blc + l;
                            seql = "insert into wolf_hce (debit) values(" + val + ")";
                            seqlBalanceUpdate = "Update from wolf_hce (balance) values(" + somme + ")";
                            seqlLibelle = "insert into wolf_hce (libelleDebit) values(" + vallibelle + ")";
                            Date aujourdhui = new Date();
                            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy ' ' HH:mm:ss");
                            seqlTStamp = "insert into wolf_hce (timestampDebit) values(" + formater.format(aujourdhui) + ")";
                            if (seql != null && !seql.isEmpty()) {
                                Thread t = new Thread() {
                                    public void run() {
                                        if (SeqlReaderTester.execute(seqlLibelle, 0)) {
                                            txtInput.setText("");
                                            txtInput.invalidate();
                                        }
                                        if (SeqlReaderTester.execute(seqlTStamp, 0)) {
                                        }
                                        if (SeqlReaderTester.execute(seql, 0)) {
                                            txtInput.setText("");
                                            txtInput.invalidate();
                                        }
                                        if (SeqlReaderTester.execute(seqlBalanceUpdate, 0)) {
                                        }
                                    }
                                };
                                t.start();
                            }
                        }
                    }
                };
                t.start();

            } else {
                JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_ERR_BAD_REQUEST);
            }
        } else if (event.getSource() == btnDebit) {
            val = txtInput.getText();
            seqlBalance = "select balance from wolf_hce";
            vallibelle = jtPLibelle.getText();
            if (seqlBalance != null && !seqlBalance.isEmpty()) {
                Thread t = new Thread() {
                    public void run() {
                        JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_PLACE_NFC_DEVICE);
                        cdobs = "balance";
                        if (SeqlReaderTester.execute(seqlBalance, counter++)) {
                            long l = Long.parseLong(val);
                            diff = blc - l;
                            if (diff >= 0) {
                                seql = "insert into wolf_hce (credit) values(" + val + ")";
                                seqlBalanceUpdate = "Update from wolf_hce (balance) values(" + diff + ")";
                                seqlLibelle = "insert into wolf_hce (libelleCredit) values(" + vallibelle + ")";
                                Date aujourdhui = new Date();
                                SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy ' ' HH:mm:ss");
                                seqlTStamp = "insert into wolf_hce (timestampCredit) values(" + formater.format(aujourdhui) + ")";
                                if (seql != null && !seql.isEmpty()) {
                                    Thread t = new Thread() {
                                        public void run() {
                                            if (SeqlReaderTester.execute(seqlLibelle, 0)) {
                                                txtInput.setText("");
                                                txtInput.invalidate();
                                            }
                                            if (SeqlReaderTester.execute(seqlTStamp, 0)) {
                                            }
                                            if (SeqlReaderTester.execute(seql, 0)) {
                                                txtInput.setText("");
                                                txtInput.invalidate();
                                            }
                                            if (SeqlReaderTester.execute(seqlBalanceUpdate, 0)) {
                                            }
                                        }
                                    };
                                    t.start();
                                }
                            } else if (diff < 0) {
                                JOptionPane.showMessageDialog(null, "Balance insuffisant");
                            }
                        }
                    }
                };
                t.start();
            }
            /*seql = "insert into wolf_hce (credit) values(" + val + ")";
			if (seql != null && !seql.isEmpty()) {
				Thread t = new Thread() {
					public void run() {
						JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_PLACE_NFC_DEVICE);
						JOptionPane.showMessageDialog(null, seql);
						if (SeqlReaderTester.execute(seql, 0)) {
							txtInput.setText("");
							txtInput.invalidate();
						}
					}
				};
				t.start();
			}*/
            else {
                JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_ERR_BAD_REQUEST);
            }
        } else if (event.getSource() == btnTCredit) {
            seql = "select credit from wolf_hce";
            //seql = "Delete credit from wolf_hce";
            if (seql != null && !seql.isEmpty()) {
                Thread t = new Thread() {
                    public void run() {
                        JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_PLACE_NFC_DEVICE);
                        JOptionPane.showMessageDialog(null, seql);
                        cdobs = "credit";
                        if (SeqlReaderTester.execute(seql, counter++)) {
							/*txtInput.setText(SeqlReaderTester.STR_INFO + System.getProperty("line.separator"));
							txtInput.setText(seql+System.getProperty("line.separator"));
							txtInput.invalidate();*/
                        }
                    }
                };
                t.start();
            } else {
                JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_ERR_BAD_REQUEST);
            }
        } else if (event.getSource() == btnTDebit) {
            seql = "select debit from wolf_hce";
            //seql = "Delete debit from wolf_hce";
            if (seql != null && !seql.isEmpty()) {
                Thread t = new Thread() {
                    public void run() {
                        JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_PLACE_NFC_DEVICE);
                        JOptionPane.showMessageDialog(null, seql);
                        cdobs = "debit";
                        if (SeqlReaderTester.execute(seql, counter++)) {
							/*txtInput.setText(SeqlReaderTester.STR_INFO + System.getProperty("line.separator"));
							txtInput.setText(seql+System.getProperty("line.separator"));
							txtInput.invalidate();*/
                        }
                    }
                };
                t.start();
            } else {
                JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_ERR_BAD_REQUEST);
            }
        } else if (event.getSource() == btn0) {
            String inputtxtvalue = txtInput.getText();
            if (inputtxtvalue.contains(",")) {
                btnvirgule.setEnabled(false);
            }
            String str = txtInput.getText() + "0";
            txtInput.setText(str);
        } else if (event.getSource() == btn1) {
            String inputtxtvalue = txtInput.getText();
            if (inputtxtvalue.contains(",")) {
                btnvirgule.setEnabled(false);
            }
            String str = txtInput.getText() + "1";
            txtInput.setText(str);
        } else if (event.getSource() == btn2) {
            String inputtxtvalue = txtInput.getText();
            if (inputtxtvalue.contains(",")) {
                btnvirgule.setEnabled(false);
            }
            String str = txtInput.getText() + "2";
            txtInput.setText(str);
        } else if (event.getSource() == btn3) {
            String inputtxtvalue = txtInput.getText();
            if (inputtxtvalue.contains(",")) {
                btnvirgule.setEnabled(false);
            }
            String str = txtInput.getText() + "3";
            txtInput.setText(str);
        } else if (event.getSource() == btn4) {
            String inputtxtvalue = txtInput.getText();
            if (inputtxtvalue.contains(",")) {
                btnvirgule.setEnabled(false);
            }
            String str = txtInput.getText() + "4";
            txtInput.setText(str);
        } else if (event.getSource() == btn5) {
            String inputtxtvalue = txtInput.getText();
            if (inputtxtvalue.contains(",")) {
                btnvirgule.setEnabled(false);
            }
            String str = txtInput.getText() + "5";
            txtInput.setText(str);
        } else if (event.getSource() == btn6) {
            String inputtxtvalue = txtInput.getText();
            if (inputtxtvalue.contains(",")) {
                btnvirgule.setEnabled(false);
            }
            String str = txtInput.getText() + "6";
            txtInput.setText(str);
        } else if (event.getSource() == btn7) {
            String inputtxtvalue = txtInput.getText();
            if (inputtxtvalue.contains(",")) {
                btnvirgule.setEnabled(false);
            }
            String str = txtInput.getText() + "7";
            txtInput.setText(str);
        } else if (event.getSource() == btn8) {
            String inputtxtvalue = txtInput.getText();
            if (inputtxtvalue.contains(",")) {
                btnvirgule.setEnabled(false);
            }
            String str = txtInput.getText() + "8";
            txtInput.setText(str);
        } else if (event.getSource() == btn9) {
            String inputtxtvalue = txtInput.getText();
            if (inputtxtvalue.contains(",")) {
                btnvirgule.setEnabled(false);
            }
            String str = txtInput.getText() + "9";
            txtInput.setText(str);
        } else if (event.getSource() == btnvirgule) {
            String inputtxtvalue = txtInput.getText();
            if (inputtxtvalue.contains(",")) {
                btnvirgule.setEnabled(false);
            }
            String str = txtInput.getText() + ",";
            txtInput.setText(str);
        } else if (event.getSource() == btnClear) {
            txtInput.setText("");
            btnvirgule.setEnabled(true);
        } else if (event.getSource() == btnUser) {
            this.dispose();
            tayjf = new TerminalAddUserJFrame();
        } else if (event.getSource() == btnExit) {
            System.exit(0);
        }
    }

    @Override
    public synchronized void update(Observable obs, Object obj) {
        if (obs instanceof SeqlReaderTester.MyMessagesObservable) {
            try {
                Map<String, String> map = (Map<String, String>) obj;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String val = entry.getValue();
                    //if(key.equals(cdobs)) {
                    if (key.equals("credit") || key.equals("debit")) {
                        txtInput.setText(key + ": " + val + System.getProperty("line.separator"));
                    } else if (key.equals("balance")) {
                        //txtInput.setText(val + System.getProperty("line.separator"));
                        blc = Long.parseLong(val);
                        //JOptionPane.showMessageDialog(null, blc);
                    }
                }
                txtInput.invalidate();
                map.clear();
            } catch (ClassCastException e) {

            }
        }

    }
}
