package org.mbds.wolf.java.view;

import org.mbds.wolf.java.SeqlReaderTester;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;

public class TerminalJFrame extends JFrame {
    private JPanel container = new JPanel();

    //Tableau stockant les éléments à afficher dans la calculatrice
    String[] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/", "Créditer (-)", "Débiter (+)"};

    //Un bouton par élément à afficher
    JButton[] tab_button = new JButton[tab_string.length];
    private JLabel ecran = new JLabel();
    private Dimension dim = new Dimension(50, 40);
    private Dimension dim2 = new Dimension(50, 31);
    private Dimension dim3 = new Dimension(100, 31);
    private double chiffre1;
    private boolean clicOperateur = false, update = false;
    private String operateur = "";
    private String seql = "";
    private String val = "";
    private int counter = 0;

    public TerminalJFrame() {
        this.setSize(500, 500);
        this.setTitle("meCoin");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //On initialise le conteneur avec tous les composants
        initComposant();

        //On ajoute le conteneur
        this.setContentPane(container);
        this.setVisible(true);
    }

    private void initComposant() {
        //On définit la police d'écriture à utiliser
        Font police = new Font("Arial", Font.BOLD, 25);
        ecran = new JLabel("0");
        ecran.setFont(police);
        ecran.setSize(500, 500);

        //On aligne les informations à droite dans le JLabel
        ecran.setHorizontalAlignment(JLabel.CENTER);
        ecran.setPreferredSize(new Dimension(220, 20));
        JPanel operateur = new JPanel();
        operateur.setPreferredSize(new Dimension(55, 225));
        JPanel debcre = new JPanel();
        debcre.setPreferredSize(new Dimension(450, 225));
        JPanel chiffre = new JPanel();
        chiffre.setPreferredSize(new Dimension(165, 225));
        JPanel panEcran = new JPanel();
        panEcran.setPreferredSize(new Dimension(450, 50));

        //On parcourt le tableau initialisé
        //afin de créer nos boutons
        for (int i = 0; i < tab_string.length; i++) {
            tab_button[i] = new JButton(tab_string[i]);
            tab_button[i].setPreferredSize(dim);

            switch (i) {
                //Pour chaque élément situé à la fin du tableau
                //et qui n'est pas un chiffre
                //on définit le comportement à avoir grâce à un listener
                case 11:
                    tab_button[i].addActionListener(new EgalListener());
                    chiffre.add(tab_button[i]);
                    break;
                case 12:
                    tab_button[i].setForeground(Color.red);
                    tab_button[i].addActionListener(new ResetListener());
                    operateur.add(tab_button[i]);
                    break;
                case 13:
                    tab_button[i].addActionListener(new PlusListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 14:
                    tab_button[i].addActionListener(new MoinsListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 15:
                    tab_button[i].addActionListener(new MultiListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 16:
                    tab_button[i].addActionListener(new DivListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 17:
                    tab_button[i].setForeground(Color.green);
                    tab_button[i].addActionListener(new CreditListener());
                    tab_button[i].setPreferredSize(dim3);
                    debcre.add(tab_button[i]);
                    break;
                case 18:
                    tab_button[i].setForeground(Color.red);
                    tab_button[i].addActionListener(new DebitListener());
                    tab_button[i].setPreferredSize(dim3);
                    debcre.add(tab_button[i]);
                    break;

                default:
                    //Par défaut, ce sont les premiers éléments du tableau
                    //donc des chiffres, on affecte alors le bon listener
                    chiffre.add(tab_button[i]);
                    tab_button[i].addActionListener(new ChiffreListener());
                    break;
            }
        }
        panEcran.add(ecran);
        panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
        container.add(panEcran, BorderLayout.NORTH);
        container.add(chiffre, BorderLayout.CENTER);
        container.add(operateur, BorderLayout.EAST);
        container.add(debcre);
    }

    //Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné
    private void calcul() {
        if (operateur.equals("+")) {
            chiffre1 = chiffre1 +
                    Double.valueOf(ecran.getText());
            ecran.setText(String.valueOf(chiffre1));
        }
        if (operateur.equals("-")) {
            chiffre1 = chiffre1 -
                    Double.valueOf(ecran.getText());
            ecran.setText(String.valueOf(chiffre1));
        }
        if (operateur.equals("*")) {
            chiffre1 = chiffre1 *
                    Double.valueOf(ecran.getText());
            ecran.setText(String.valueOf(chiffre1));
        }
        if (operateur.equals("/")) {
            try {
                chiffre1 = chiffre1 /
                        Double.valueOf(ecran.getText());
                ecran.setText(String.valueOf(chiffre1));
            } catch (ArithmeticException e) {
                ecran.setText("0");
            }
        }
    }

    //Listener utilisé pour les chiffres
    //Permet de stocker les chiffres et de les afficher
    class ChiffreListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //On affiche le chiffre additionnel dans le label
            String str = ((JButton) e.getSource()).getText();
            if (str.contains(".")) {
                tab_button[10].setEnabled(false);
            }
            if (update) {
                update = false;
                tab_button[10].setEnabled(true);
            } else {
                if (!ecran.getText().equals("0")) {
                    str = ecran.getText() + str;
                }
            }
            ecran.setText(str);
        }
    }

    //Listener affecté au bouton =
    class EgalListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            calcul();
            update = true;
            clicOperateur = false;
        }
    }

    //Listener affecté au bouton +
    class PlusListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            actionOperator();
            operateur = "+";
            update = true;
        }
    }

    private void actionOperator() {
        if (clicOperateur) {
            calcul();
            ecran.setText(String.valueOf(chiffre1));
        } else {
            chiffre1 = Double.valueOf(ecran.getText());
            clicOperateur = true;
        }
    }

    //Listener affecté au bouton -
    class MoinsListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            actionOperator();
            operateur = "-";
            update = true;
        }
    }

    //Listener affecté au bouton *
    class MultiListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            actionOperator();
            operateur = "*";
            update = true;
        }
    }

    //Listener affecté au bouton /
    class DivListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            actionOperator();
            operateur = "/";
            update = true;
        }
    }

    //Listener affecté au bouton de remise à zéro
    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            clicOperateur = false;
            update = true;
            chiffre1 = 0;
            operateur = "";
            tab_button[10].setEnabled(true);
            ecran.setText("");
        }
    }

    //Listener affecté au bouton Debit
    class DebitListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_DEPOSER_TELEPHONE);
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
            val += ecran.getText();
            seql += "update emcoin_hce SET amounts = montant + " + val + ", terminal_id = 1, timestamp = " + timestamp;
            if (seql != null && !seql.isEmpty() && val != null && !val.isEmpty()) {
                Thread t = new Thread() {

                    public void run() {
                        JOptionPane.showMessageDialog(null, seql);
                        if (SeqlReaderTester.execute(seql, counter++))

                        {
                            System.out.println("Exécuter ...");
                        }
                    }

                };
                t.start();
            }
            seql = "";
            val = "";
            clicOperateur = false;
            update = true;
            chiffre1 = 0;
            operateur = "";
            tab_button[10].setEnabled(true);
            ecran.setText("");
            System.out.println("DebitListener");
        }
    }

    class CreditListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            JOptionPane.showMessageDialog(null, SeqlReaderTester.MSG_DEPOSER_TELEPHONE);
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
            val += ecran.getText();
            seql += "update emcoin_hce SET amounts = montant - " + val + ", terminal_id = 1, timestamp = " + timestamp;
            if (seql != null && !seql.isEmpty() && val != null && !val.isEmpty()) {
                Thread t = new Thread() {

                    public void run() {
                        JOptionPane.showMessageDialog(null, seql);
                        if (SeqlReaderTester.execute(seql, counter++))

                        {
                            System.out.println("Exécuter ...");
                        }
                    }

                };
                t.start();
            }
            seql = "";
            val = "";
            clicOperateur = false;
            update = true;
            chiffre1 = 0;
            operateur = "";
            tab_button[10].setEnabled(true);
            ecran.setText("");
            System.out.println("CreditListener");
        }
    }
}