package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:image.properties")
public class FileServiceImpl implements FileService {

    @Value("${image.localPathDir}")
    private String localPathDir;
    @Value("${image.localUrlPath}")
    private String localUrlPath;
    @Override
    public ImageVO upload(MultipartFile file) throws IOException {

        //校验图片正常
        String filename = file.getOriginalFilename().toLowerCase();
        if(!filename.matches("^.+\\.(jpg|png|gif)$")) return null;
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if(width == 0 || height == 0) return null;
        }catch (Exception e){
            return null;
        }
        //目录结构
        String datePath = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        String localDir = localPathDir + datePath;
        File dirFile = new File(localDir);
        if(!dirFile.exists()) dirFile.mkdirs();
        //文件名
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileType = filename.substring(filename.lastIndexOf("."));
        String realFileName = uuid + fileType;
        //完整路径
        String filePathAll = localDir + realFileName;
        File realFile = new File(filePathAll);
        //上传
        file.transferTo(realFile);

        //网络路径
        String virtualPath = datePath + realFileName;
        String urlPath = localUrlPath + virtualPath;
        return new ImageVO(virtualPath,urlPath,realFileName);
    }

    @Override
    public void fileRemove(String virtualPath) {
        String filePath = localPathDir + virtualPath;
        File file = new File(filePath);
        file.delete();
    }
}
