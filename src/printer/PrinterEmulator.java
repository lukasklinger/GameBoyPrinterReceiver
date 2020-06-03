package printer;

import java.util.Objects;

import image.ImageConverter;

/**
 * Receives data from a GameBoy Printer Emulator (see
 * https://github.com/mofosyne/arduino-gameboy-printer-emulator) and saves the
 * image to disk.
 * 
 * @author lukasklinger
 *
 */
public class PrinterEmulator {
	private ImageConverter converter = new ImageConverter();
	private boolean receivingImage = false;
	private String currImage = "";

	/**
	 * This method receives data from a GameBoy Printer Emulator. (see
	 * https://github.com/mofosyne/arduino-gameboy-printer-emulator) Once a print
	 * has finished, it will automatically invoke the {@link ImageConverter} to save
	 * the image to disk.
	 * 
	 * @param chunk {@link String} of data
	 */
	public void receiveChunk(String chunk) {
		if (Objects.isNull(chunk) || chunk.isEmpty())
			return;
		if (chunk.contains("\"command\":\"INIT\""))
			startImage();
		if (chunk.contains("\"command\":\"PRNT\""))
			finishImage();
		if (!chunk.startsWith("#") && !chunk.startsWith("!"))
			addData(chunk);
	}

	private void startImage() {
		receivingImage = true;
		System.out.println("Starting transfer.");
	}

	private void finishImage() {
		System.out.println("Transfer done.");

		convertImage();

		receivingImage = false;
		currImage = "";
	}

	private void convertImage() {
		byte[] bytes = convertToByteArray(currImage);
		converter.convertImage(bytes);
	}

	private void addData(String data) {
		if (receivingImage)
			currImage += data + " ";
	}

	private byte[] convertToByteArray(String data) {
		String[] byteStrings = data.split(" ");
		byte[] bytes = new byte[byteStrings.length];

		for (int i = 0; i < byteStrings.length; i++) {
			bytes[i] = (byte) ((Character.digit(byteStrings[i].charAt(0), 16) << 4)
					+ Character.digit(byteStrings[i].charAt(1), 16));
		}

		return bytes;
	}
}
