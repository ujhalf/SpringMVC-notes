package com.half.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/9 15:51
 * @Version 1.0
 * @Description
 */
@Controller
@RequestMapping("/file")
public class FileuploadController {
    /*使用原生ServletAPI完成文件上传*/
    @PostMapping("/upload1")
    public String upload1(HttpServletRequest request) throws Exception {
        //设置上传文件的目录
        String path = request.getSession().getServletContext().getRealPath("/upload1/");
        //判断目录是否存在
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem item : fileItems) {
            if (item.isFormField()) {

            } else {
                //是上传文件项
                //获取文件名
                String name = item.getName();
                //为文件生成唯一uuid
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                item.write(new File(path, uuid + name));
                //清除中间过程的临时文件
                item.delete();
            }
        }
        return "success";
    }

    /**
     * 使用SpringMVC完成文件上传
     * 配置文件解析器组件 返回解析后的文件对象
     */
    @PostMapping("/upload2")                     //此处形参需要与form表单中属性名一致
    public String upload2(HttpServletRequest request, MultipartFile upload2) throws Exception {
        //设置上传文件的目录
        String path = request.getSession().getServletContext().getRealPath("/upload1/");
        //判断目录是否存在
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        //是上传文件项
        //获取文件名
        String name = upload2.getOriginalFilename();
        //为文件生成唯一uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        upload2.transferTo(new File(path, uuid + name));
        return "success";
    }
}
