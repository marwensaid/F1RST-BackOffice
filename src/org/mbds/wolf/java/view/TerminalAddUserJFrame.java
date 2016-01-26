package org.mbds.wolf.java.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by BENATHMANE on 26/01/2016.
 */
public class TerminalAddUserJFrame extends JFrame {
    private JPanel container = new JPanel();
    private JComboBox combo = new JComboBox();
    private JLabel label = new JLabel("Une ComboBox");

    public TerminalAddUserJFrame() {
        this.setTitle("Animation");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        combo.setPreferredSize(new Dimension(100, 20));

        JPanel top = new JPanel();
        top.add(label);
        top.add(combo);
        container.add(top, BorderLayout.NORTH);
        this.setContentPane(container);
        this.setVisible(true);
    }
}
