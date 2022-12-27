package com.wyh;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

public class Qiniuyun {
    @Test
    //上传文件
    public void UpFile() throws QiniuException {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "CITBxetcgDyK8Gt47Dgd0r_phobab1Rl-Uba0o8g";
        String secretKey = "KkqQ1lXVY38d9y1TIye4FRfICCf_Ob68YpvWDCLe";
        String bucket = "wangyihuan";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "D:\\Administrator\\Pictures\\Camera Roll\\IMG_3044(20221202-083653).JPG";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "MyGirlFriend";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    @Test
    //删除文件
    public void DeleteFile() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释

        String accessKey = "CITBxetcgDyK8Gt47Dgd0r_phobab1Rl-Uba0o8g";
        String secretKey = "KkqQ1lXVY38d9y1TIye4FRfICCf_Ob68YpvWDCLe";
        String bucket = "wangyihuan";

        String key = "HealthImages-2";

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
