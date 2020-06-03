package printer;

import image.ImageConverter;

public class PrinterEmulator {
	private ImageConverter converter = new ImageConverter();
	private boolean receivingImage = false;
	private String currImage = "";

	public void receiveChunk(String chunk) {
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
