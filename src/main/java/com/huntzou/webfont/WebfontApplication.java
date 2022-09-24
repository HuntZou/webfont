package com.huntzou.webfont;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class WebfontApplication {
    private final Logger logger = LoggerFactory.getLogger(WebfontApplication.class);

    @Resource
    private CropFontService cropFontService;

    @Value("${font_family_home}")
    private String fontFamilyHome;

    public static void main(String[] args) {
        SpringApplication.run(WebfontApplication.class, args);
    }

    @GetMapping("crop")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public ResponseEntity<InputStreamResource> crop(@RequestParam(required = false, name = "crop_text") String cropText,
                                                    @RequestParam(required = false, name = "font_family", defaultValue = "LongCang-Regular.ttf") String fontFamily,
                                                    @RequestParam(required = false, name = "output_font_type", defaultValue = "woff") String outputFontType) {

        logger.info("crop font request, cropText: {}, fontFamily: {}, outputFontType: {}",
                cropText != null ? cropText.substring(0, Math.min(cropText.length(), 10)) : "_", fontFamily, outputFontType);
        try {

            File cropFile = cropFontService.crop(cropText, fontFamily, outputFontType);
            FileSystemResource fileSystemResource = new FileSystemResource(cropFile);

            return ResponseEntity.ok()
                    .contentLength(fileSystemResource.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .header("Content-disposition", "attachment; filename=webfont." + outputFontType)
                    .body(new InputStreamResource(fileSystemResource.getInputStream()));
        } catch (Exception ex) {
            logger.error("exception occurred when cropping font", ex);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("ls")
    public Map<String, Object> fontFamilyLs() throws IOException {

        File file = new File(fontFamilyHome);

        boolean del = false;
        if (file.isFile()) {
            logger.info("invalid font family path, path: {}", file.getAbsolutePath());
            if (!file.delete()) {
                logger.error("fail to delete invalid font family path");
                return new HashMap<String, Object>() {
                    {
                        put("status", "fail");
                        put("msg", "fail to delete invalid file");
                    }
                };
            }
            del = true;
        }
        if (!file.exists() || del) {
            logger.info("font family path un-exists, path: {}", file.getAbsolutePath());
            if (!file.mkdir()) {
                logger.error("fail to create font family path");
                return new HashMap<String, Object>() {
                    {
                        put("status", "fail");
                        put("msg", "fail to create font dir");
                    }
                };
            }
        }

        Set<String> fontFamilies = Optional.ofNullable(file.listFiles())
                .map(files -> Arrays.stream(files).map(File::getName).collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
        logger.info("ls font family request, fontFamilies: {}", fontFamilies);
        return new HashMap<String, Object>() {
            {
                put("status", "success");
                put("font_families", fontFamilies);
            }
        };
    }
}
