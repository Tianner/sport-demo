package com.sunwoda.babasport.common.fastDFS;

import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

/**
 * FastDFS工具类
 * Created by Administrator on 2017/8/16.
 */
public class FastDFSTools {
    public static final String ROOT_PATH="http://192.168.57.104:8888/";
    /**
     * 将页面上传文件的字节码和文件名，传输存储到分布式文件系统上，并返回存储全路径
     * @param bytes
     * @param originalFilename
     * @return
     * @throws Exception
     */
    public static String uploadFile(byte[] bytes, String originalFilename) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("fdfs_client.conf");
        ClientGlobal.init(classPathResource.getClassLoader().getResource("fdfs_client.conf").getPath());
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer connection = trackerClient.getConnection();
        StorageClient1 client1 = new StorageClient1(connection,null);
        String extension = FilenameUtils.getExtension(originalFilename);
        return client1.upload_file1(bytes,extension,null);
    }
}

