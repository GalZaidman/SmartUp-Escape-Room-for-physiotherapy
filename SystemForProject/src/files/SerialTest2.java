package files;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;

public class SerialTest2 implements SerialPortEventListener {
	/** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { "/dev/tty.usbserial-A9007UX1", // Mac
																				// OS
																				// X
			"/dev/ttyACM0", // Raspberry Pi
			"/dev/ttyUSB0", // Linux
			"COM4" // Windows
	};
	/**
	 * A BufferedReader which will be fed by a InputStreamReader converting the
	 * bytes into characters making the displayed results codepage independent
	 */
	public static BufferedReader input;
	/** The output stream to the port */
	public static OutputStream output;
	/** Milliseconds to block while waiting for port open */
	public static final int TIME_OUT = 1000;
	/** Default bits per second for COM port. */
	public static final int DATA_RATE = 9600;
	
	public SerialPort serialPort;

	public String inputLine;
	public int SerialEventNum;
	
	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		SerialEventNum=0;
		// First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port. This will prevent
	 * port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	/*public synchronized void serialEvent(SerialPortEvent oEvent) { //to get an input from arduino
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = input.readLine();
				System.out.println(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}
	*/
	
		public synchronized void serialEvent(SerialPortEvent oEvent) {
	
	        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
	            try {
	                inputLine=null;
	                if (input.ready()) {
	                    inputLine = input.readLine();
	                    SerialEventNum++;
	    				System.out.println(inputLine+" "+SerialEventNum);
	    				
	                }
	            } catch (Exception e) {
	                System.err.println(e.toString());
	            }
	        }       
	    }
	
// send an output to arduino
	public static synchronized void writeData(String data) { 
		System.out.println("Sent: " + data);
		byte[] dd=new byte[100];
		dd=data.getBytes();
		for(int i=0 ; i<dd.length ; i++){
			dd[i]-=48;
		}
		try {
			output.write(dd);
		} catch (Exception e) {
			System.out.println("could not write to port");
		}
	}


}
