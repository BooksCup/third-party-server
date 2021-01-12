package com.bc.third.party.server.utils;

import com.bc.third.party.server.entity.config.OssConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 文件
 *
 * @author zhou
 */
@Component
public class FileUtil {

    /**
     * 多文件上传
     *
     * @param request 请求
     * @return List<String> 上传到服务器的文件名列表
     */
    public List<String> uploadMultipartFile(HttpServletRequest request, OssConfig ossConfig) {
        List<String> fileNameList = new ArrayList<>();
        // 检测是不是存在上传文件
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        // 判断 request 是否有文件上传,即多部分请求
        if (isMultipart) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                String originFileName = file.getOriginalFilename();
                String prefix = originFileName.substring(originFileName.lastIndexOf(".") + 1);
                if (file != null) {
                    try {
                        // 取得当前上传文件的文件名称
                        String fileName = CommonUtil.generateId() + '.' + prefix;
                        // 上传到oss
                        String result = OssUtil.uploadFile(file, fileName, ossConfig);
                        if (StringUtils.isNotEmpty(result)) {
                            fileName = ossConfig.getDomain() + fileName;
                        }
                        fileNameList.add(fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return fileNameList;
    }

}
