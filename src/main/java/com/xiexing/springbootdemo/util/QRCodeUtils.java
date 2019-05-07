package com.xiexing.springbootdemo.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;


public class QRCodeUtils {
    public static BufferedImage cut(byte[] bais,Rectangle rt) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bais);
        Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader) iterator.next();
        ImageInputStream iis = null;
        ImageReadParam param;
        BufferedImage bufferedImage = null;
        try {
            iis = ImageIO.createImageInputStream(inputStream);
            reader.setInput(iis, true);
            param = reader.getDefaultReadParam();
            param.setSourceRegion(rt);
            bufferedImage = reader.read(0, param);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.dispose();

            try {
                if(iis != null) {
                    iis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bufferedImage;
    }
}