package com.ding.controller.File;

import cn.hutool.core.img.FontUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerResponse;
import com.ding.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName FileController
 * @Description 文件上传
 * @Author Ding557
 * @Date 2022/5/31 19:56
 * @Version 1.0
 **/
//@CrossOrigin   //跨域访问
@RestController
@RequestMapping("/files")
public class FileController {
    @Value("${server.port}")
    private String port;
    private static final String ip = "http://localhost";
    //文件上传
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        //获取上传文件名称
        String originalFilename = file.getOriginalFilename();
        //路径
        String uuid = IdUtil.fastSimpleUUID();
        String properties = System.getProperty("user.dir")+"/src/main/resources/file/"+uuid+"_"+originalFilename;
        //流
        FileUtil.writeBytes(file.getBytes(),properties);

        return Result.success(ip+":"+port+"/files/"+uuid); // 返回URL
    }
    //文件下载
    @GetMapping("/{flag}")
    public void getFiles(HttpServletResponse response, @PathVariable String flag) {
        OutputStream os; //新建一个输出流对象
        String basePath = System.getProperty("user.dir") + "/src/main/resources/file/";
        //获取所有文件名称
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");  // 找到跟参数一致的文件
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);  // 通过文件的路径读取文件字节流
                os = response.getOutputStream();   // 通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }

    
}
