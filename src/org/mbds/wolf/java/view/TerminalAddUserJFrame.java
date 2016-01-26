package org.mbds.wolf.java.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by BENATHMANE on 26/01/2016.
 */
public class TerminalAddUserJFrame extends JFrame {
    public static String title = "meCoin Terminal";
    private JPanel mainPanel = new JPanel();

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

        this.setVisible(true);
    }
}
