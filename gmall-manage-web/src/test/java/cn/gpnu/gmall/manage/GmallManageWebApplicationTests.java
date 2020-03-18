package cn.gpnu.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GmallManageWebApplicationTests {

    @Test
    void contextLoads() throws IOException, MyException {
//        String file = this.getClass().getResource("/tracker.conf").getFile();
        String tracker = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();
        ClientGlobal.init(tracker);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer,null);
        String[] uploadInfos = storageClient.upload_file("d://haha.jpg", "jpg", null);

        String url = "http://192.168.28.128";
        for (String uploadInfo : uploadInfos) {
            url = url + "/" + uploadInfo;
        }
        System.out.println(url);
    }

}
