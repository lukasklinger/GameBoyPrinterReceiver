package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import uk.co.silentsoftware.codec.Codec;
import uk.co.silentsoftware.codec.constants.IndexedPalette;
import uk.co.silentsoftware.codec.image.ImageCodec;

public class ImageConverter {
	private IndexedPalette palette = new IndexedPalette();
	private Codec decoder = new ImageCodec(palette, 160, 144);
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH-mm-ss_dd-MM-yyyy");

	public void convertImage(byte[] bytes) {
		System.out.println("Converting image.");
		BufferedImage image = decodeImage(bytes);
		saveImage(image);
	}

	private BufferedImage decodeImage(byte[] bytes) {
		return decoder.decode(bytes);
	}

	private void saveImage(BufferedImage image) {
		String fileName = "image_";
		
		LocalDateTime now = LocalDateTime.now();
		fileName += dtf.format(now) + ".png";

		System.out.println("Saving image as " + fileName);
		File f = new File(fileName);

		try {
			ImageIO.write(image, "PNG", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
