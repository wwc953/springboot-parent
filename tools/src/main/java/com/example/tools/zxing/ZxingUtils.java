package com.example.tools.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ZxingUtils {


    public static String getImage(String contents) throws WriterException, IOException {
        Map set = new HashMap<>();
        set.put(EncodeHintType.CHARACTER_SET, "utf-8");

        QRCodeWriter qr = new QRCodeWriter();
        //生成二维码
        BitMatrix encode = qr.encode(contents, BarcodeFormat.QR_CODE, 300, 300, set);
        //去除白边
        BitMatrix deleteWhite = deleteWhite(encode);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //图片缓存
        BufferedImage image = toBufferedImage(deleteWhite);
        //图片写输出流
        ImageIO.write(image, "png", out);
        byte[] array = out.toByteArray();
        String encodeToString = Base64.getEncoder().encodeToString(array);
        return "data:image/png;base64," + encodeToString;
    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, matrix.get(i, j) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    private static BitMatrix deleteWhite(BitMatrix encode) {
        int[] rec = encode.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHight = rec[3] + 1;
        BitMatrix bitMatrix = new BitMatrix(resWidth, resHight);
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHight; j++) {
                if (encode.get(i + rec[0], j + rec[1])) {
                    bitMatrix.set(i, j);
                }
            }
        }
        return bitMatrix;
    }
}
