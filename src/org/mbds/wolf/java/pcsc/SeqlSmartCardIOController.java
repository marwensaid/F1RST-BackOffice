package org.mbds.wolf.java.pcsc;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

import org.mbds.wolf.exception.ByteArrayToHexaStringException;
import org.mbds.wolf.exception.WolfException;
import org.mbds.wolf.seql.APDUModel;
import org.mbds.wolf.seql.APDUResponse;
import org.mbds.wolf.seql.AppletModel;
import org.mbds.wolf.seql.ISeqlCallBack;
import org.mbds.wolf.seql.ISeqlController;
import org.mbds.wolf.seql.SeqlController.SE_TYPE;
import org.mbds.wolf.seql.SeqlMetadata;
import org.mbds.wolf.seql.SeqlParser;
import org.mbds.wolf.seql.SeqlWords;
import org.mbds.wolf.seql.exceptions.APDUError;
import org.mbds.wolf.seql.exceptions.BadRequestException;
import org.mbds.wolf.seql.exceptions.ChannelNotOpenException;
import org.mbds.wolf.seql.exceptions.NoReaderException;
import org.mbds.wolf.seql.exceptions.NoSecureElementException;
import org.mbds.wolf.tools.BytesTool;

/**
 * 
 * @author alesas
 *
 */
public class SeqlSmartCardIOController implements ISeqlController 
{
	public final static String DEFAULT_CONFIG_FILE_PATH = "/org/mbds/wolf/java/resources/config.xml";

	public final static String MSG_INFO_CARD_DISCONNECTED = "Card reader disconnected...";

	public final static int CMD_HELLO = -1;
	private static final String MSG_READER_TIME_OUT = "Time out!";

	private File configFile;
	private ISeqlCallBack callback;
	private SE_TYPE seType;
	
	private static CardChannel channel = null;
	private static Card card = null;

	/**
	 * Constructor SeqlOmapiController
	 * @param context
	 * @param callback
	 */
	public SeqlSmartCardIOController(Object context, ISeqlCallBack callback) 
	{
		setCallback(callback);
		setSeType(SE_TYPE.SE_READER);
		initService();
	}
	
	/**
	 * setSeType
	 * @param seType
	 */
	private void setSeType(SE_TYPE seType) 
	{
		this.seType = seType;
	}
	
	/**
	 * getSeType
	 * @return SE_TYPE
	 */
	@Override
	public SE_TYPE getSeType() 
	{
		return seType;
	}

	/**
	 * sayHello (SELECT AID APDU ISO 7816-4)
	 * @param appletName
	 * @param commandId
	 * @return boolean
	 */
	@Override
	public boolean sayHello(String appletName, int commandHelloId) 
	{
		try 
		{
			AppletModel model = null;
			try 
			{
				model = SeqlMetadata.getInstance(configFile).getAppletByAlias(appletName);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			if (model==null)
			{
				callback.onNoApplet();
			}

			channel = openChannel(model);

			byte[] apdu = null;
			APDUResponse resp = new APDUResponse();
			try {
				//select the applet
				apdu = APDUModel.getSelectAidApdu(BytesTool.hexStringToByteArray(model.getAID()));
				apdu = sendCommand(apdu, channel);
				resp.setResponse(apdu);
				return APDUModel.isResponseSucceded(resp.getStatusWord());
			} catch (Exception e) {
				e.printStackTrace();
				callback.onNoApplet();
			}
		} 
		catch (NoSuchElementException e) 
		{
			callback.onNoApplet();
		} 
		catch (IOException e) 
		{
			callback.onNoSecureElement();
		} 
		catch (NoReaderException e) 
		{
			callback.onNoReader();
		} 
		catch (NoSecureElementException e) 
		{
			callback.onNoSecureElement();
		} 
		catch (BadRequestException e) 
		{
			callback.onBadRequest(e.getMessage());
		}
		catch (Exception e) 
		{
			callback.onNotConnected();
		}
		return false;
	}

	/**
	 * initService
	 * @return boolean
	 */
	@Override
	public boolean initService() 
	{
		URL url = ClassLoader.class.getResource(DEFAULT_CONFIG_FILE_PATH);
		try 
		{
			configFile = new File(url.toURI());
		} 
		catch(Exception e) 
		{
			configFile = new File(url.getPath());
		}
		return true;
	}

	/**
	 * disconnect
	 */
	public static void disconnect() {
		try {
			channel.close();
			channel = null;
			card.disconnect(false);
			card = null;
			System.out.println(MSG_INFO_CARD_DISCONNECTED);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}


	/**
	 * getCallback
	 * @return ISeqlCallBack
	 */
	public ISeqlCallBack getCallback() 
	{
		return callback;
	}

	/**
	 * setCallback
	 * @param ISeqlCallBack
	 */
	@Override
	public void setCallback(ISeqlCallBack callback) 
	{
		this.callback = callback;
	}

	/**
	 * execute
	 * @param command
	 * @param commandId
	 * @return boolean
	 */
	@Override
	public boolean execute(final String command, final int commandId)
	{
		//new Thread(){
			//public void run(){
		Map<String, Object> maps = null;
		try {

	        maps = SeqlParser.parseSeql(command, configFile);

			if (maps == null)
				throw new BadRequestException("Command not supported!");
			return executeCommand(maps, commandId);

		} catch (BadRequestException ex) {
			ex.printStackTrace();
			if (callback!=null)
				callback.onBadRequest(ex.getMessage());
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			if (callback!=null)
				callback.onNoApplet();
		} catch (NoSecureElementException ex) {
			ex.printStackTrace();
			if (callback!=null)
				callback.onNoSecureElement();
		} catch (NoReaderException ex) {
			ex.printStackTrace();
			if (callback!=null)
				callback.onNoReader();
		} catch (ChannelNotOpenException e) {
			e.printStackTrace();
			if (callback!=null)
				callback.onNotConnected();
		} catch (IOException e) {
			e.printStackTrace();
			if (callback!=null)
				callback.onIOException(e.getMessage());
		} catch (Exception e) {
			if (callback!=null)
				callback.onBadRequest(e.getMessage());
		}
			//}
		//}.start();
		return false;
	}


	/**
	 * executeCommand
	 * @param map of APDU commands (Structure: Applet<-Instructions<-Fields<-Commands)
	 * @param commandId
	 * @return boolean
	 * @throws Exception
	 */
	boolean executeCommand(Map<String, Object> maps, int commandId) throws Exception
	{
		AppletModel applet = null;
		Object a = maps.get(SeqlWords.STR_APPLET);

		if (a instanceof AppletModel) 
		{
			applet = (AppletModel) a;
		}
		
		if (applet == null) 
		{
			callback.onNoApplet();
			return false;
		}
				
		try
		{
			if (!sayHello(applet.getAlias(), CMD_HELLO))
			{
				throw new ChannelNotOpenException();
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		
		byte[][] commands = (byte[][]) maps.get(SeqlWords.STR_COMMAND);
		String[] fields = (String[]) maps.get(SeqlWords.STR_FIELD);
		
		Map<String, Object> response = new HashMap<String, Object>();
		int idxField = 0;
		boolean success = false;
		for (byte[] command : commands) 
		{
			byte []rep = null;
			rep = sendCommand(command, channel);
			
			if (!APDUModel.isResponseSucceded(rep)) 
			{
				if (APDUModel.isPinRequired(rep)) 
				{
					if (callback!=null)
						callback.onPINRequired();
				} 
				else 
				{
					APDUError error = new APDUError("Wrong APDU response", APDUModel.getResponseStatusWord(rep));
					if (callback!=null)
						callback.onAPDUError(error);
				}
			} 
			else 
			{
				String r = APDUModel.getResponseBody(rep);
				response.put(fields[idxField], r);
				// Repeat fields loop for each instruction sequence
				idxField = (idxField == fields.length? 0: idxField + 1);
				success = true;
			}

			if (!success) 
			{
				//TODO: rollback all transactions!!!
				break;
			}
		}
		if (success) 
		{
			//System.out.println("Response sending...........");
			if (callback!=null)
				callback.onResponse(response, commandId);
		}
		disconnect();
		return success;
	}

	/**
	 * sendCommand
	 * @param command
	 * @return byte[] response
	 * @throws Exception
	 */
	public static byte[] sendCommand(byte[] cmd, CardChannel channel) throws Exception {
		if (channel==null)
		{
			throw new ChannelNotOpenException();
		}
		byte []rep = null;
		ResponseAPDU r = null;
		try {
			CommandAPDU apdu = new CommandAPDU(cmd);
			try {
				System.out.println("APDU Command: " + BytesTool.byteArrayToHexString(apdu.getBytes()));
			} catch (ByteArrayToHexaStringException e) {
				throw e;
			}
			r = channel.transmit(apdu); 
			rep = r.getBytes();
			try {
				System.out.println("APDU Response: " + BytesTool.byteArrayToHexString(rep));
			} catch (ByteArrayToHexaStringException e) {
				throw e;
			}
			return rep;
		} catch (CardException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * isServiceConnected
	 * @return boolean
	 */
	@Override
	public boolean isServiceConnected() 
	{
		return channel!=null;
	}


	/**
	 * openChannel
	 * @param applet
	 * @return Channel
	 * @throws Exception
	 */
	private CardChannel openChannel(AppletModel applet) throws Exception 
	{
		if (channel!=null)
		{
			try
			{
				channel.close();
			}
			catch (Exception e)
			{
				//
			}
			channel = null;
		}
		if (card!=null)
		{
			try
			{
				card.disconnect(true);
			}
			catch (Exception e)
			{
				//
			}
			card = null;
		}
		TerminalFactory factory = TerminalFactory.getDefault();
		CardTerminals cardterminals = factory.terminals();
		try {
			List<CardTerminal> terminals = cardterminals.list();
			System.out.println("Terminals: " + terminals);
			for (CardTerminal terminal: terminals)
			{
				terminal.waitForCardPresent(1000);
				if (terminal.isCardPresent()) {
		            System.out.println(terminal.getName()+": Card present!");
					card = terminal.connect("*");
					channel = card.getBasicChannel();
					return channel;
		        }
			}
			throw new WolfException(MSG_READER_TIME_OUT);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * setConfigFile
	 */
	@Override
	public void setConfigFile(File configFile) 
	{
		this.configFile = configFile;
	}
}