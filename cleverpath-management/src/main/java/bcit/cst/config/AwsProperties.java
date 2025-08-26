package bcit.cst.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// AwsProperties.java
@Data
@Component
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {
    private String region;
    private S3 s3 = new S3();

    @Data
    public static class S3 {
        private String bucket;
        private String prefix;
        private int presignExpirationMinutes;
    }
}
