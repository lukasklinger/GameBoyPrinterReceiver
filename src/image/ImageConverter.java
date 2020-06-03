package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.imageio.ImageIO;

import uk.co.silentsoftware.codec.Codec;
import uk.co.silentsoftware.codec.constants.IndexedPalette;
import uk.co.silentsoftware.codec.image.ImageCodec;

/**
 * This class decodes and saves an image in 2BPP format as a PNG to disk. To
 * decode the image, is uses https://github.com/KodeMunkie/gameboycameralib
 * 
 * @author lukasklinger
 *
 */
public class ImageConverter {
	private IndexedPalette palette = new IndexedPalette();
	private Codec decoder = new ImageCodec(palette, 160, 144);
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH-mm-ss_dd-MM-yyyy");

	/**
	 * This method decodes an array of bytes in GameBoy 2BPP format and saves this
	 * data as a PNG image (160 by 144 pixels).
	 * 
	 * @param bytes Non-{@code NULL} array of bytes in 2BPP format
	 */
	public void convertImage(byte[] bytes) {
		if (Objects.isNull(bytes) || bytes.length > 1) {
			System.out.println("Cannot convert empty image.");
		} else {
			System.out.println("Converting image.");
			BufferedImage image = decodeImage(bytes);
			saveImage(image);
		}
	}

	private BufferedImage decodeImage(byte[] bytes) {
		return decoder.decode(bytes);
	}

	// Saves image to storage (same folder as .jar executable)
	private void saveImage(BufferedImage image) {
		String fileName = "image_";

		LocalDateTime now = LocalDateTime.now();
		fileName += dtf.format(now) + ".png";

		System.out.println("Saving image as " + fileName);
		File f = new File(fileName);

		try {
			ImageIO.write(image, "PNG", f);
		} catch (IOException e) {
			System.out.println("Could not write image to disk. Have you checked folder permissions?");
			e.printStackTrace();
		}
	}
}
