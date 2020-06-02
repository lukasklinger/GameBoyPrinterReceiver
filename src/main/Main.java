package main;

import printer.PrinterEmulator;
import serial.DataListener;
import serial.SerialTerminal;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		PrinterEmulator emulator = new PrinterEmulator();
		SerialTerminal connection = new SerialTerminal();
		DataListener listener = new DataListener(emulator, connection);
		
		if(connection.connect(null, 115200)) System.out.println("Connected.");
		
		connection.addDataListener(listener);
		
		while(true) {
			Thread.sleep(100000);
		}
	}

}
