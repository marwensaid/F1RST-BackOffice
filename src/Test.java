import java.util.List;

import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

import org.mbds.wolf.exception.WolfException;
import org.mbds.wolf.tools.BytesTool;


public class Test {

	private static Card card = null;
	private static CardChannel channel = null;
	public static byte[] myCmd = { (byte) 0xFF, (byte) 0xCA, 0x00, 0x00, 0x00};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		executeCommand(myCmd);
	}
	private static void executeCommand(byte[] apdu) {

		try {
			if (openConnection()) {
				byte []rep = null;
				ATR atr = card.getATR() ;
				rep = atr.getBytes() ;
				try {
					javax.swing.JOptionPane.showMessageDialog(null,"ATR: "+ BytesTool.byteArrayToHexString(rep));
				} catch (org.mbds.wolf.exception.ByteArrayToHexaStringException e1) {
					e1.printStackTrace();
				}
				if (channel!=null) {
					org.mbds.wolf.seql.APDUResponse resp = new org.mbds.wolf.seql.APDUResponse();
					try {
						javax.swing.JOptionPane.showMessageDialog(null,"Send: "+ BytesTool.byteArrayToHexString(apdu));
						apdu = sendCommand(apdu, channel);
						if (apdu!=null)
							javax.swing.JOptionPane.showMessageDialog(null,"Res: "+ BytesTool.byteArrayToHexString(apdu));
						resp.setResponse(apdu);
					} catch (WolfException e) {
						e.printStackTrace();
					}
					disconnect();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean openConnection() {
		TerminalFactory factory = TerminalFactory.getDefault();
		CardTerminals cardterminals = factory.terminals();
		card = null;
		try {
			List<CardTerminal> terminals = cardterminals.list();
			System.out.println("Terminals: " + terminals);
			CardTerminal terminal = cardterminals.getTerminal(terminals.get(0).getName());
			terminal.waitForCardPresent(20000);
			System.out.println("Card detected!");
			card = terminal.connect("*");
			channel = card.getBasicChannel();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	public static void disconnect() {
		try {
			channel.close();
			card.disconnect(false);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	public static byte[] sendCommand(byte[] cmd, CardChannel channel) throws Exception {
		byte []rep = null;
		ResponseAPDU r = null;
		try {
			CommandAPDU apdu = new CommandAPDU(cmd);
			try {
				System.out.println("APDU Command: " + BytesTool.byteArrayToHexString(apdu.getBytes()));
			} catch (org.mbds.wolf.exception.ByteArrayToHexaStringException e) {
				e.printStackTrace();
			}
			r = channel.transmit(apdu); 
			rep = r.getBytes();
			try {
				System.out.println("APDU Response: " + BytesTool.byteArrayToHexString(rep));
			} catch (org.mbds.wolf.exception.ByteArrayToHexaStringException e) {
				e.printStackTrace();
			}
			return rep;
		} catch (CardException e) {
			e.printStackTrace();
		}
		return null;
	}

}
