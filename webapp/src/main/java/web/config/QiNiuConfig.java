package web.config;

import com.mchange.v1.db.sql.ConnectionUtils;
import com.qiniu.common.Zone;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jca.cci.connection.ConnectionFactoryUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 七牛云配置
 *
 * @author simon
 * @create 2018-08-15 10:44
 **/
@Slf4j
@Data
public class QiNiuConfig {
    private String FILENAME = "qiniu.properties";
    private String accessKey;
    private String secretKey;
    private String bucket;
    private Zone zone;
    private String domainOfBucket;
    private long expireInSeconds;

    private static QiNiuConfig instance = new QiNiuConfig();

    private QiNiuConfig(){
        Properties prop = new Properties();
        String File_PATH = this.getClass().getClassLoader().getResource(FILENAME).getPath();
        File file = new File(File_PATH);
        try {
            FileInputStream in = new FileInputStream(file);
            prop.load(in);
            accessKey = prop.getProperty("qiniu.access-key");
            secretKey = prop.getProperty("qiniu.secret-key");
            bucket = prop.getProperty("qiniu.bucket");
            domainOfBucket = prop.getProperty("qiniu.domain-of-bucket");
            expireInSeconds = Long.parseLong(prop.getProperty("qiniu.expire-in-seconds"));
            String zoneName = prop.getProperty("qiniu.zone");
            if(zoneName.equals("zone0")){
                zone = Zone.zone0();
            }else if(zoneName.equals("zone1")){
                zone = Zone.zone1();
            }else if(zoneName.equals("zone2")){
                zone = Zone.zone2();
            }else if(zoneName.equals("zoneNa0")){
                zone = Zone.zoneNa0();
            }else if(zoneName.equals("zoneAs0")){
                zone = Zone.zoneAs0();
            }else{
                throw new Exception("Zone对象配置错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public Zone getZone() {
        return zone;
    }

    public String getDomainOfBucket() {
        return domainOfBucket;
    }

    public long getExpireInSeconds() {
        return expireInSeconds;
    }

    public static QiNiuConfig getInstance(){
        return instance;
    }
    public static void main(String[] args) {
        System.out.println(QiNiuConfig.getInstance());
    }
}