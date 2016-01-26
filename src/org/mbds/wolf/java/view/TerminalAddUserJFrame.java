package org.mbds.wolf.java.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by BENATHMANE on 26/01/2016.
 */
public class TerminalAddUserJFrame extends JFrame {
    public static String title = "meCoin Terminal";
    private JPanel mainPanel = new JPanel();
    private JTextField jtName = new JTextField("BENATHMANE");
    private JLabel jlName = new JLabel("Votre Nom : ");

    public TerminalAddUserJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setSize(500, 500);
        setResizable(true);
        setLocationRelativeTo(null);
        add(mainPanel);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {30, 30, 30, 30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[] {30, 0, 50, 30, 0, 30, 30, 30, 30, 30, 30};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
        mainPanel.setLayout(gridBagLayout);

        GridBagConstraints gbc_jlName = new GridBagConstraints();
        gbc_jlName.gridwidth = 200;
        gbc_jlName.insets = new Insets(0, 0, 300, 480);
        gbc_jlName.gridx = 16;
        gbc_jlName.gridy = 3;
        mainPanel.add(jlName, gbc_jlName);

        GridBagConstraints gbc_jtName = new GridBagConstraints();
        gbc_jtName.gridwidth = 19;
        gbc_jtName.gridheight = 1;
        gbc_jtName.insets = new Insets(0, 80, 300, -200);
        gbc_jtName.fill = GridBagConstraints.BOTH;
        gbc_jtName.gridx = 16;
        gbc_jtName.gridy = 3;
        mainPanel.add(jtName, gbc_jtName);

        this.setContentPane(mainPanel);
        this.setVisible(true);
    }
}
