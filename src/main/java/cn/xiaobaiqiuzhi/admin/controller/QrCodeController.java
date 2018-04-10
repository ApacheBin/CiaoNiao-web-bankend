package cn.xiaobaiqiuzhi.admin.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Chy
 * @Description: 生成登录二维码
 * @Date: Created at 22:43 2018/3/8
 */
@Controller
public class QrCodeController {

    @RequestMapping("")
    public String enCode(HttpServletRequest request, Model model) throws WriterException, IOException {

        String uuid = UUID.randomUUID().toString().replaceAll("-", ""); //生成uuid

        String content = uuid;
        String filePath =  request.getServletContext().getRealPath("/") + "assets/images";
        String fileName = "qrcode.png";

        int width = 400; // 图像宽度
        int height = 400; // 图像高度
        String format = "png";// 图像类型

        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
        Path path = FileSystems.getDefault().getPath(filePath, fileName);

        MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像

        model.addAttribute("uuid", uuid);

        return "QrCode";
    }
}