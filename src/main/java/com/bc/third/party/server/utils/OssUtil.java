package com.bc.third.party.server.utils;

import com.bc.third.party.server.entity.config.OssConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

/**
 * OSS工具类
 *
 * @author zhou
 */
public class OssUtil {

    private static final Logger logger = LoggerFactory.getLogger(OssUtil.class);

    public static String uploadFile(MultipartFile file, String key, OssConfig ossConfig) {
        String eTag = "";
        OSSClient client = new OSSClient(ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        try {
            InputStream is = new ByteArrayInputStream(file.getBytes());
            // 上传图片
            PutObjectResult result = client.putObject(ossConfig.getBucketName(), key, is);
            eTag = result.getETag();
        } catch (Exception e) {
            logger.error("OSS错误=>", e);
        } finally {
            // 关闭OSS服务,一定要关闭
            client.shutdown();
        }
        return eTag;
    }

}
