package serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import printer.PrinterEmulator;

public class DataListener implements SerialPortDataListener{
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
