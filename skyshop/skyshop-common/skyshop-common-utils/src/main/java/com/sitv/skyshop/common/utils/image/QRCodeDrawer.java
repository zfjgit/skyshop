package com.sitv.skyshop.common.utils.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public final class QRCodeDrawer {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file) {
		try {
			BufferedImage image = toBufferedImage(matrix);
			if (!ImageIO.write(image, format, file)) {
				throw new RuntimeException("Could not write an image of format " + format + " to " + file);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) {
		try {
			BufferedImage image = toBufferedImage(matrix);
			if (!ImageIO.write(image, format, stream)) {
				throw new RuntimeException("Could not write an image of format " + format);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void drawQRCode(String content, String fileSavePath) {
		try {
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

			Map<EncodeHintType, String> hints = new HashMap<>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 250, 250, hints);

			QRCodeDrawer.writeToFile(bitMatrix, "jpg", new File(fileSavePath));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}