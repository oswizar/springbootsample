package com.oswizar.springbootsample.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * @date: 2019/7/26 14:27
 * @author: oswizar
 * @description:
 */
public class ZxingUtils {

    private static Logger log = LoggerFactory.getLogger(ZxingUtils.class);

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
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

    private static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    /** 将内容contents生成长宽均为width的图片，图片路径由imgPath指定
     */
    public static File getQRCodeImage(String contents, int width, String imgPath) throws IOException, WriterException {
        return getQRCodeImage(contents, width, width, imgPath);
    }

    /** 将内容contents生成长为width，宽为width的图片，图片路径由imgPath指定
     */
    public static File getQRCodeImage(String contents, int width, int height, String imgPath) throws WriterException, IOException {
            Map<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            File imageFile = new File(imgPath);
            writeToFile(bitMatrix, "png", imageFile);
            return imageFile;
    }
}
