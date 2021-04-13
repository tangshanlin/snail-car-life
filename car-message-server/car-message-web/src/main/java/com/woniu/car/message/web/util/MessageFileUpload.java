package com.woniu.car.message.web.util;


/**
 * @Author Lints
 * @Date 2021/4/7/007 13:31
 * @Description 文件上传的类
 * @Since version-1.0
 */
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.UUID;


/**
 * @Author Lints
 * @Date 2021/4/7/007 13:14
 * @Description  提供文件上传路径返回类
 * @Param
 * @Return
 * @Since version-1.0
 */

@Component
public class MessageFileUpload {

    @Resource
    private  MinioClient minioClient;

    @Value("${minio.endpoint}")
    private  String endpoint;
    @Value("${minio.bucketName}")
    private  String bucketName;

    public  ArrayList<String> upload(MultipartFile[] file){
        // 获取文件名
        ArrayList<String>  images=new ArrayList<String>();
        if(!ObjectUtils.isEmpty(file)&&file.length>0){
            for (int i=0;i<file.length;i++){
                String fileName = file[i].getOriginalFilename();
                // 获取文件的后缀名,比如图片的jpeg,png
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                // 文件上传后的路径
                fileName = "P"+ UUID.randomUUID() + suffixName;
                try {
                    PutObjectArgs objectArgs = PutObjectArgs.builder().object(fileName)
                            .bucket(bucketName)
                            .contentType(file[i].getContentType())
                            .stream(file[i].getInputStream(),file[i].getSize(),-1).build();
                    minioClient.putObject(objectArgs);
                    images.add(endpoint+"/"+bucketName+"/"+fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return images;
    }






}
