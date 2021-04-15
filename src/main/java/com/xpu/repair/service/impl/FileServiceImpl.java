package com.xpu.repair.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.xpu.repair.service.FileService;
import com.xpu.repair.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file) {

        String uploadUrl = null;

        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        try {
            //判断oss实例是否存在，如果不存在则创建，如果存在则获取
            OSSClient ossClient = new OSSClient(endPoint,accessKeyId,accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)){
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }

            //获取上传文件流
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String fileName = file.getOriginalFilename();
            //在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //把文件按照日期进行分类，构建日期路径: xpu-repair/2021/04/14
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            fileName = datePath+"/"+uuid+fileName;

            //调用oss方法实现上传
            ossClient.putObject(bucketName,fileName,inputStream);

            //关闭ossClient
            ossClient.shutdown();;

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            uploadUrl = "https://"+bucketName+"."+endPoint+"/"+fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadUrl;
    }
}
