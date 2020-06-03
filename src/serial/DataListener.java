package serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import printer.PrinterEmulator;

/**
 * This data listener can be attached to a serial port. Whenever new data is
 * available, it retrieves a full line from the serial port and sends it to
 * {@link PrinterEmulator}.
 * 
 * @author lukasklinger
 *
 */
public class DataListener implements SerialPortDataListener {
	private PrinterEmulator emulator;
	private SerialTerminal connection;

	public DataListener(PrinterEmulator emulator, SerialTerminal connection) {
		this.emulator = emulator;
		this.connection = connection;
	}

	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		emulator.receiveChunk(connection.receiveDataFromSerial());
	}

}
