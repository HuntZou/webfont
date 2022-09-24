package com.huntzou.webfont;

import com.huntzou.webfont.tools.sfnttool.SfntTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;

@Component
public class CropFontService {
    private final Logger logger = LoggerFactory.getLogger(CropFontService.class);


    @Value("${font_family_home}")
    private String fontFamilyHome;

    /**
     * 用于精简原始字体库文件
     *
     * @param cropText       需要保留的字符
     * @param fontFamily     使用哪种字体
     * @param outputFontType 输出字体类型，当前支持 mtx/eot/woff
     */
    public File crop(String cropText, String fontFamily, String outputFontType) throws Exception {
        SfntTool sfntTool = new SfntTool();
        // 文字裁剪配置
        for (Field declaredField : sfntTool.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            try {
                switch (declaredField.getName()) {
                    case "subsetString":
                        declaredField.set(sfntTool, cropText.trim());
                        break;
                    case "woff":
                        declaredField.set(sfntTool, "woff".equals(outputFontType));
                        break;
                    case "eot":
                        declaredField.set(sfntTool, "eot".equals(outputFontType));
                        break;
                    case "mtx":
                        declaredField.set(sfntTool, "mtx".equals(outputFontType));
                        break;
                }
            } catch (IllegalAccessException ex) {
                logger.error("exception occurred at crop config inject", ex);
                throw new Exception("exception occurred at crop config inject");
            }
            declaredField.setAccessible(false);
        }

        File fontFile = new File(fontFamilyHome + "/" + fontFamily);
        File croppedFontFile = File.createTempFile("crop", outputFontType);
        sfntTool.subsetFontFile(fontFile, croppedFontFile, 1);
        return croppedFontFile;
    }

}