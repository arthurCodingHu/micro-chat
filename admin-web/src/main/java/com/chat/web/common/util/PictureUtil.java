package com.chat.web.common.util;

import com.chat.web.common.annotations.PassToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @author arlen
 */
@Controller
@RequestMapping("/pictureUtil")
public class PictureUtil {
    /**
     * 显示图片【通过传图片路径显示图片】
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    @PassToken
    @RequestMapping(value = "/showPicByUrl.do")
    public void showPicByUrl(String Url, HttpServletResponse response) throws Exception {
        InputStream in = null;
        OutputStream out = null;
        // 设置浏览器缓存头
        response.setDateHeader("Last-Modified", new Date().getTime());
        response.setDateHeader("Expires", new Date().getTime() + 3600 * 1000);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "Pragma");
        response.setContentType("image/jpeg");
        try {
//			AttachFile attachFile = commonService.selectByCode(new AttachFile(), picId);
            in = new FileInputStream(Url);
            out = response.getOutputStream();
//			StreamUtils.copy(in, out);
            FileCopyUtils.copy(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                in.close();
            if (out != null)
                out.flush();
            if (out != null)
                out.close();
        }
    }


}
