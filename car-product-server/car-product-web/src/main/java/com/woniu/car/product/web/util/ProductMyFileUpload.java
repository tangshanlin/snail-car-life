package com.woniu.car.product.web.util;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class ProductMyFileUpload {
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
