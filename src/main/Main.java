package main;

import printer.PrinterEmulator;
import serial.DataListener;
import serial.SerialTerminal;

public class Main {

	public static void main(String[] args) {
		PrinterEmulator emulator = new PrinterEmulator();
		SerialTerminal connection = new SerialTerminal();
		DataListener listener = new DataListener(emulator, connection);
		
		if(args.length != 1) {
			System.out.println("No serial port given as parameter. Will try to autoconnect");
		}
		
		if(connection.connect(args[0], 115200)) System.out.println("Connected.");
		
		connection.addDataListener(listener);
		
		System.out.println("Waiting for images. Use [CTRL] + [C] to stop.");
		
		while(true) {
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				// Nothing to do here but try again.
			}
		}
	}

}
