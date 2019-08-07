package com.chat.web.common.util;

import com.bove.commons.lang.RDateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class FileUtil {
    /***保存文件*/
    public static String saveFile(MultipartFile file, String path) throws Exception {
        String filepath="";
        if (!file.isEmpty()&&!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            filepath = path + RDateUtils.toString(RDateUtils.getCurrentDate(), "yyyyMMddHHmmss") + "_1." + suffix;
            file.transferTo(new File(filepath));
        }
        return filepath;
    }
    /**
     * 将图片转base64
     * @param imagepath
     * @return
     */
    public static String getBase64StrByImagePath(String imagepath) throws Exception{
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imagepath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return URLEncoder.encode(encoder.encode(data).replaceAll("\r\n", ""),"UTF-8");
    }


    /**
     * 通过读取文件并获取其width及height的方式，来判断判断当前文件是否图片，这是一种非常简单的方式。
     * @param imageFile
     * @return
     */
    public static boolean isImage(File imageFile) {
        if (!imageFile.exists()) {
            return false;
        }
        Image img = null;
        try {
            img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }

    public static boolean isImage(MultipartFile imageFile) {
        if (imageFile==null) {
            return false;
        }
        Image img = null;
        try {
            img = ImageIO.read(imageFile.getInputStream());
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }




    /**
     * 根据文件后缀名判断 文件是否是视频文件
     * @param fileName 文件名
     * @return 是否是视频文件
     */
    public static boolean isVedioFile(String fileName){
        String reg = "(mp4|MP4|flv|avi|rm|rmvb|wmv)";
        Pattern p = Pattern.compile(reg);
        boolean boo = p.matcher(fileName).find();
        return boo;
    }
}
