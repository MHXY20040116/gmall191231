package utils;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PmsUploadUtil {

    public static String uploadImage(MultipartFile multipartFile) {
        String imgUrl = "http://192.168.28.128";
        // 配置fdfs全局链接地址，获取配置文件路径
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();
        try {
            ClientGlobal.init(tracker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TrackerClient trackerClient = new TrackerClient();
        // 获取TrackerServer实例
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 利用获取TrackerServer的实例获取Storage链接客户端
        StorageClient storageClient = new StorageClient(trackerServer,null);
        try {
            // 获取上传图片的二进制对象
            byte[] bytes = multipartFile.getBytes();
            // 获取文件后缀名
            String originalFilename = multipartFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String[] uploadInfos = storageClient.upload_file(bytes,extName,null);
            for (String uploadInfo : uploadInfos) {
                imgUrl = imgUrl + "/" + uploadInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imgUrl;
    }

}
