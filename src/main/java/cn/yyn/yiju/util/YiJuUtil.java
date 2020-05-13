package cn.yyn.yiju.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;

/**
 * @Author Administrator
 * @Date 2020/5/11 16:34
 **/
public class YiJuUtil {
    public static void main(String[] args) {

        //图片上传
        String local = "";
        File file = new File(local);
        String upload = "999.jpg";
        upload(file,upload);

    }
    public static void upload(File file,String upload){
        //准备数据建立连接七牛云
        //密匙
        final String AK = "18oFNtGFNjmHdgBinnprbFto-ay4qe7lCUgh6-gv";
        final String SK = "KK0x9-IJH__FKNVOHZLDaoZu5fMHtFo7ajH7Gwy0";
        //工作空间
        final String BUCKET = "yyn001";

        //2.图片上传
        Auth auth = Auth.create(AK, SK);
        String token = auth.uploadToken(BUCKET);
        //绑定大区
        Configuration cfg = new Configuration(Zone.zone1());

        UploadManager uploadManager = new UploadManager(cfg);
        try {
            uploadManager.put(file, upload, token);
        } catch (QiniuException e) {
            e.printStackTrace();
        }

    }

}

