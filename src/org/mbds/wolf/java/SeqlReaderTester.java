package org.mbds.wolf.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.swing.JOptionPane;

import org.mbds.wolf.exception.ByteArrayToHexaStringException;
import org.mbds.wolf.java.view.KeyboardJFrame;
import org.mbds.wolf.java.view.SeqlTesterJFrame;
import org.mbds.wolf.seql.ISeqlCallBack;
import org.mbds.wolf.seql.ISeqlController;
import org.mbds.wolf.seql.SeqlController;
import org.mbds.wolf.seql.exceptions.APDUError;
import org.mbds.wolf.tools.BytesTool;

public class SeqlReaderTester implements ISeqlCallBack {
	
	public final static String MSG_ERR_WRONG_CONFIGURATION 	= "Problem occured trying to get the applet configuration!";
	public final static String MSG_RESP_PIN_REQUIRED 		= "PIN required!";
	public final static String MSG_ERR_BAD_REQUEST 			= "Bad request: \n";
	public final static String MSG_ERR_BAD_CONFIG_FILE		= "Bad config file!";
	public final static String MSG_ERR_NOT_CONNECTED		= "Not connected!";
	public final static String MSG_ERR_SERVICE_NOT_FOUNT	= "Service not found!";
	public final static String MSG_ERR_CARD_NOT_FOUNT		= "Card not found!";
	public final static String MSG_READER_TIME_OUT 			= "Time out: Card not detected!";
	public final static String MSG_ERR_CMD_UNCOMPLETED 		= "Command was uncompleted....";
	public final static String MSG_PROGRAM_WILL_EXIT 		= "Seql controller error, program will exit";
	public final static String MSG_CMD_SUCCESSFULL 			= "Command was successfull: \n";
	public final static String MSG_ERR_CHANNEL 				= "Card channel is not open...";
	public final static String MSG_ERR_APDU_ERROR 			= "APDU error: \n";
	public final static String MSG_ERR_NO_READER 			= "No reader found!";
	public final static String MSG_ERR_IO_ERROR 			= "IO exception: ";
	public final static String MSG_PLACE_NFC_DEVICE 		= "Please, put the device on the reader...";
	public final static String MSG_INFO_TIME 				= "Nb milli-seconds execution time: ";
	public final static String MSG_INFO_CONNECTED			= "Connected!";
	public final static String MSG_DEPOSER_TELEPHONE 		= "Merci de deposer votre telephone sur le lecteur";

	public final static String STR_TIME 	= "time";
	public final static String STR_ERROR 	= "error";
	public final static String STR_INFO 	= "info";

	private static ISeqlController controller;
	
	public class MyMessagesObservable extends Observable {
		private HashMap<String, String> historical; 
		public MyMessagesObservable() {
			historical = new HashMap<String, String>();
		}
		
		public void clear() {
			historical.clear();
		}
		public void put(String key, String val) {
			historical.put(key, val);
			setChanged();
			notifyObservers(historical);
		}
	}
	
	private static MyMessagesObservable myMessages = null;
	private static SeqlReaderTester instance = null;
	
	public static void main(String args[]) {
		instance = SeqlReaderTester.getInstance();
		try {
			controller = SeqlController.getController(null, SeqlController.CONFIG.PCSC_READER, instance, null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,MSG_ERR_WRONG_CONFIGURATION+
					System.getProperty("line.separator")
					+MSG_PROGRAM_WILL_EXIT); 
			e.printStackTrace();
			System.exit(0);
		}
		SeqlTesterJFrame frame = new SeqlTesterJFrame(myMessages);
		//TerminalJFrame terminalJFrame = new TerminalJFrame();
		//KeyboardJFrame keyboardJFrame = new KeyboardJFrame();
	}
	
	public static SeqlReaderTester getInstance() {
		if (instance==null)
			instance = new SeqlReaderTester();
		return instance;
	}
	
	private SeqlReaderTester() {
		myMessages = new MyMessagesObservable();
	}
	
	public static boolean execute(String command, final int commandId) {
		if (command==null || command.trim().length()==0) 
			return false;
		command = command.trim();
		long time = System.currentTimeMillis();
		try {
			boolean done = controller.execute(command, commandId);
			time = System.currentTimeMillis()-time;
			myMessages.put(STR_TIME, MSG_INFO_TIME+time);
			return done;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			myMessages.put(STR_ERROR, e.getMessage());
		}
		return false;
	}

	@Override
	public void onAPDUError(APDUError arg0) {
		String err = arg0.getMessage();
		try {
			err = BytesTool.byteArrayToHexString(arg0.getApduError());
		} catch (ByteArrayToHexaStringException e) {
			e.printStackTrace();
		}
		myMessages.put(STR_ERROR, MSG_ERR_APDU_ERROR+err);
	}

	@Override
	public void onBadRequest(String arg0) {
		myMessages.put(STR_ERROR, MSG_ERR_BAD_REQUEST+arg0);
	}

	@Override
	public void onConfigError() {
		myMessages.put(STR_ERROR, MSG_ERR_BAD_CONFIG_FILE);
	}

	@Override
	public void onConnected() {
		myMessages.put(STR_INFO, MSG_INFO_CONNECTED);
	}

	@Override
	public void onIOException(String arg0) {
		myMessages.put(STR_ERROR, MSG_ERR_IO_ERROR+arg0);
	}

	@Override
	public void onNoApplet() {
		myMessages.put(STR_ERROR, MSG_ERR_SERVICE_NOT_FOUNT);
	}

	@Override
	public void onNoReader() {
		myMessages.put(STR_ERROR, MSG_ERR_NO_READER);
	}

	@Override
	public void onNoSecureElement() {
		myMessages.put(STR_ERROR, MSG_ERR_CARD_NOT_FOUNT);
	}

	@Override
	public void onNotConnected() {
		myMessages.put(STR_ERROR, MSG_ERR_NOT_CONNECTED);
	}

	@Override
	public void onPINRequired() {
		myMessages.put(STR_INFO, MSG_RESP_PIN_REQUIRED);
	}

	@Override
	public void onResponse(Map<String, Object> map, int commandId) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String str = (String) entry.getValue();
			try {
				byte[] b = BytesTool.hexStringToByteArray(str.replace(" ",  ""));
				str = new String(b);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myMessages.put(entry.getKey(), str);
		}
	}
}
