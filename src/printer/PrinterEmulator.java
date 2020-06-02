package printer;

public class PrinterEmulator {
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
		System.out.println("Starting Transfer.");
	}

	private void finishImage() {
		System.out.println("Transfer done.");

		convertToByteArray(new String(currImage));
		receivingImage = false;
		currImage = "";
	}

	private void addData(String data) {
		if (receivingImage)
			currImage += data + " ";
	}

	private void convertToByteArray(String data) {
		String[] byteStrings = data.split(" ");
		byte[] bytes = new byte[byteStrings.length];

		// Conversion to bytes not working
		for (int i = 0; i < byteStrings.length; i++) {
			bytes[i] = (byte) ();
			
			bytes[i] = (byte) ((Character.digit(byteStrings[i].charAt(0), 16) << 4)
					+ Character.digit(byteStrings[i].charAt(1), 16));
		}

		System.out.println(byteStrings[0]);
	}
}
