package com.koa.coremodule.image.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.image.exception.FileDeleteException;
import com.koa.coremodule.image.exception.FileExtensionException;
import com.koa.coremodule.image.exception.FileUploadException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    /**
     * file upload
     */
    public String uploadFile(MultipartFile multipartFile) {

        validateFileExists(multipartFile);

        String fileName = createFileName(multipartFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new FileUploadException(Error.FILE_UPLOAD_FAIL);
        }

        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    /**
     * 파일 존재 여부 체크 메서드
     */
    private void validateFileExists(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new FileUploadException(Error.FILE_UPLOAD_FAIL);
        }
    }

    /**
     * 파일 삭제 메서드
     */
    public void deleteFile(String fileUrl) {
        if (fileUrl == null) return;
        try {
            amazonS3.deleteObject(bucketName, fileUrl);
        } catch (AmazonServiceException e) {
            throw new FileDeleteException(Error.FILE_DELETE_FAIL);
        }
    }

    /**
     * 파일 업로드 시에 파일명을 난수화하는 메서드
     */
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    /**
     * 파일 확장자 가져오는 메서드
     */
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new FileExtensionException(Error.WRONG_FILE_FORMAT);
        }
    }

}
