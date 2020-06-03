package serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortInvalidPortException;

/**
 * Serial connection adapter
 * 
 * @author lukasklinger
 *
 */
public class SerialTerminal {
	InputStream in;
	OutputStream out;
	SerialPort serialPort;
	byte[] readBuffer = new byte[2048];

	public SerialTerminal() {
		super();
	}

	/**
	 * This method establishes a serial connection.
	 * 
	 * @param portName String name of the port (Unix e.g. /dev.tty.usbmodem, for
	 *                 Windows e.g. COM1)
	 * @return boolean true if connected, false otherwise
	 * @throws @link{SerialPortInvalidPortException} if serial not connected or port
	 *                                               not recognized
	 */
	public boolean connect(String portName, int baudRate) throws SerialPortInvalidPortException {
		if (!Objects.isNull(portName) && !portName.isEmpty()) {
			System.out.println("Searching for port " + portName);
			serialPort = SerialPort.getCommPort(portName);
		} else {
			System.err.println("No port path given. Trying to auto-connect.");
			SerialPort[] ports = SerialPort.getCommPorts();

			if (ports.length == 1) {
				System.out.println("Found one port. Connecting...");
				serialPort = ports[0];
			} else {
				throw new SerialPortInvalidPortException(
						"Cannot determine serial port.");
			}
		}

		serialPort.setComPortParameters(baudRate, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);

		// Open connection
		if (serialPort.openPort()) {
			in = serialPort.getInputStream();
			out = serialPort.getOutputStream();
			
			return true;
		} else {
			return false;
		}
	}

	/**
	 * To close the serial port after code finishes
	 */
	public void close() {
		serialPort.closePort();
	}

	/**
	 * Send String to serial port
	 * 
	 * @param input String to be sent
	 */
	public void sendDataToSerial(String input) {
		try {
			input = input + "\r\n";
			this.out.write(input.getBytes());
			this.out.flush();
			this.out.close();

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println(e);
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Polling data from serial port
	 * 
	 * @return String output from serial connection
	 */
	public String receiveDataFromSerial() {
		String line = new String();

		try {
			// read data from serial port until a line end is detected.
			while (!line.contains("\n")) {
				while(serialPort.bytesAvailable() > 0) {
					in.read(readBuffer, 0, 1);
					line += new String(readBuffer, 0, 1);
					
					if(line.contains("\n")) break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return line.trim().intern();
	}
	
	/**
	 * Add listener to serial port.
	 * 
	 * @param listener {@link SerialPortDataListener}
	 */
	public void addDataListener(SerialPortDataListener listener) {
		serialPort.addDataListener(listener);
	}
}
