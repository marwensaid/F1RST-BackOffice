package org.mbds.wolf.java.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.mbds.wolf.java.SeqlReaderTester;
import org.mbds.wolf.java.SeqlReaderTester.MyMessagesObservable;

public class SeqlTesterJFrame extends JFrame implements ActionListener, Observer {
	public static String title = "SEQL Reader Tester";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JLabel lblInput = new JLabel("Enter SEQL instruction:");  
    private JLabel lblOutput = new JLabel("Log trace:");  
    private JTextField txtInput = new JTextField(); 
    private JTextArea txtOutput = new JTextArea(); 
    private JButton btnExecute = new JButton("Execute"); 
    private JButton btnExit = new JButton("Exit"); 
    private JButton btnSave = new JButton("Save"); 
    private JButton btnClear = new JButton("Clear"); 
    private JPanel mainPanel = new JPanel();
    private ScrollPane pnlScroll = new ScrollPane();
    private int counter = 0;
    private String seql="";
    private MyMessagesObservable obs = null;


	public SeqlTesterJFrame(MyMessagesObservable obs) {
		super();
		if (obs!=null) {
			this.obs = obs;
			obs.addObserver(this);
		}
		init();
	}
//	public void setObs(MyMessagesObservable obs) {
//		if (obs!=null) {
//			if (this.obs!=null)
//				this.obs.deleteObserver(this);
//			obs.addObserver(this);
//			this.obs = obs;
//		}
//	}
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(title);
		setSize(500,500);
		setResizable(true);
		setLocationRelativeTo(null);
		add(mainPanel);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 30, 30, 30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {30, 0, 50, 30, 0, 30, 30, 30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		mainPanel.setLayout(gridBagLayout);
		pnlScroll.add(txtOutput);
		txtOutput.setEditable(false);
		GridBagConstraints gbc_lblInput = new GridBagConstraints();
		gbc_lblInput.gridwidth = 19;
		gbc_lblInput.anchor = GridBagConstraints.WEST;
		gbc_lblInput.insets = new Insets(0, 0, 5, 0);
		gbc_lblInput.gridx = 0;
		gbc_lblInput.gridy = 0;
		mainPanel.add(lblInput, gbc_lblInput);
		GridBagConstraints gbc_txtInput = new GridBagConstraints();
		gbc_txtInput.gridwidth = 19;
		gbc_txtInput.gridheight = 2;
		gbc_txtInput.insets = new Insets(0, 0, 5, 0);
		gbc_txtInput.fill = GridBagConstraints.BOTH;
		gbc_txtInput.gridx = 0;
		gbc_txtInput.gridy = 1;
		mainPanel.add(txtInput, gbc_txtInput);
		GridBagConstraints gbc_btnExecute = new GridBagConstraints();
		gbc_btnExecute.gridwidth = 3;
		gbc_btnExecute.insets = new Insets(0, 0, 0, 5);
		gbc_btnExecute.gridx = 16;
		gbc_btnExecute.gridy = 3;
		mainPanel.add(btnExecute, gbc_btnExecute);
		GridBagConstraints gbc_lblOutput = new GridBagConstraints();
		gbc_lblOutput.gridwidth = 19;
		gbc_lblOutput.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutput.gridx = 0;
		gbc_lblOutput.anchor = GridBagConstraints.WEST;
		gbc_lblOutput.gridy = 4;
		mainPanel.add(lblOutput, gbc_lblOutput);
		GridBagConstraints gbc_pnlScroll = new GridBagConstraints();
		gbc_pnlScroll.insets = new Insets(0, 0, 5, 0);
		gbc_pnlScroll.gridheight = 4;
		gbc_pnlScroll.gridwidth = 19;
		gbc_pnlScroll.fill = GridBagConstraints.BOTH;
		gbc_pnlScroll.gridx = 0;
		gbc_pnlScroll.gridy = 5;
		mainPanel.add(pnlScroll, gbc_pnlScroll);
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.gridwidth = 3;
		gbc_btnClear.insets = new Insets(0, 0, 0, 5);
		gbc_btnClear.gridx = 11;
		gbc_btnClear.gridy = 10;
		mainPanel.add(btnClear, gbc_btnClear);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridwidth = 3;
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 14;
		gbc_btnSave.gridy = 10;
		mainPanel.add(btnSave, gbc_btnSave);
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnSave.gridwidth = 3;
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.gridx = 17;
		gbc_btnExit.gridy = 10;
		mainPanel.add(btnExit, gbc_btnExit);
		btnClear.addActionListener(this);
		btnSave.addActionListener(this);
		btnExecute.addActionListener(this);
		btnExit.addActionListener(this);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource()==btnExecute) {
			seql = txtInput.getText();
			if (seql!=null && !seql.isEmpty()) {
			    Thread t = new Thread() {
			        public void run() {
						JOptionPane.showMessageDialog(null,SeqlReaderTester.MSG_PLACE_NFC_DEVICE); 
					    txtOutput.append(SeqlReaderTester.STR_INFO+System.getProperty("line.separator"));
					    txtOutput.append(seql+System.getProperty("line.separator"));
					    txtOutput.invalidate();      
						if (SeqlReaderTester.execute(seql, counter++)) {
						    txtInput.setText("");
						    txtInput.invalidate();
						}
			          }
			        };
			        t.start();

			} else {
				JOptionPane.showMessageDialog(null,SeqlReaderTester.MSG_ERR_BAD_REQUEST); 
			}
		} else if (event.getSource()==btnClear) {
			obs.clear();
		    txtOutput.setText("");
		    txtOutput.invalidate();
		} else if (event.getSource()==btnExit) {
			System.exit(0);
		} else if (event.getSource()==btnSave) {
			JFileChooser saver = new JFileChooser("./");  
	        int returnVal = saver.showSaveDialog(this);  
	        File file = saver.getSelectedFile();  
	        BufferedWriter writer = null;  
	        if (returnVal == JFileChooser.APPROVE_OPTION){  
	            try {  
		            writer = new BufferedWriter( new FileWriter( file.getAbsolutePath()+".txt"));  
		            writer.write( txtOutput.getText());  
		            writer.close( );  
		            JOptionPane.showMessageDialog(this, "Log trace was saved!",  
		                        "Success!", JOptionPane.INFORMATION_MESSAGE);  
	            } catch (IOException e) {  
		            JOptionPane.showMessageDialog(this, "The Text could not be Saved!",  
		                        "Error!", JOptionPane.INFORMATION_MESSAGE);  
	            }  
	        }  
		}
		
	}
	@Override
	public synchronized void update(Observable obs, Object obj) {
		if (obs instanceof MyMessagesObservable)  {
			try {
				Map<String, String> map = (Map<String, String>) obj;
				for (Map.Entry<String, String> entry : map.entrySet()) {
				    String key = entry.getKey();
				    String val = entry.getValue();
				    txtOutput.append(key+": "+val+System.getProperty("line.separator"));
				}
			    txtOutput.invalidate();
			    map.clear();
			} catch (ClassCastException e) {
				
			}
		}
		
	}
}
