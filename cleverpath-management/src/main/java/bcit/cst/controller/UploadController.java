package bcit.cst.controller;

import bcit.cst.config.AwsProperties;
import bcit.cst.pojo.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.IOException;
import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadController {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final AwsProperties awsProperties;

    /**
     * 上传文件，返回 S3 Key（数据库保存用）
     */
    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("File is empty");
        }
        try {
            // ✅ 生成对象 key（保存到数据库的就是这个）
            String key = awsProperties.getS3().getPrefix() + file.getOriginalFilename();

            // 上传到 S3（默认私有）
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(awsProperties.getS3().getBucket())
                    .key(key)
                    .contentType(file.getContentType())
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            log.info("Uploaded file to S3: {}/{}", awsProperties.getS3().getBucket(), key);

            // ✅ 上传成功后生成预签名 URL

            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(awsProperties.getS3().getBucket())
                    .key(key)
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(awsProperties.getS3().getPresignExpirationMinutes())) // 有效期 15 分钟
                    .getObjectRequest(getObjectRequest)
                    .build();

            String presignedUrl = s3Presigner.presignGetObject(presignRequest).url().toString();

            // ⚠️ 返回预签名 URL，前端 <img> 可以直接用
            return Result.success(presignedUrl);


        } catch (IOException e) {
            log.error("Upload failed", e);
            return Result.error("Upload failed: " + e.getMessage());
        }
    }
}
