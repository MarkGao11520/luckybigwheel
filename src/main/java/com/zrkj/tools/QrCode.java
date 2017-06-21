package com.zrkj.tools;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/4/10.
 */
public class QrCode {
    public static String genQRImage(String folderName, String imageName, String content) throws Exception{
        //String filePath = System.getProperty("twtwebapp.root");
        String fileName = imageName + ".png";
        Logger log = Logger.getLogger(QrCode.class);
            // 检查是否存在imageQR目录，不存在则先创建
            File file = new File(folderName);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
            }

            folderName = file.getAbsolutePath();

            int width = 200; // 图像宽度
            int height = 200; // 图像高度
            String format = "png";// 图像类型

            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
            Path path = FileSystems.getDefault().getPath(folderName, fileName);
            MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像
            log.info("二维码已经生成," + path);
            fileName = path.toString();
        return fileName;
    }

//    public static void main(String[] args) {
//        genQRImage("/Users/gaowenfeng/desktop","aaa","http://www.baidu.com");
//    }

    public static   String getIp(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
