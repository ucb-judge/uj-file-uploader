package ucb.judge.ujfileuploader.bl

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ucb.judge.ujfileuploader.dao.S3Object
import ucb.judge.ujfileuploader.dao.repository.S3ObjectRepository
import ucb.judge.ujfileuploader.service.MinioService

@Service
class FilesBl constructor(
    private val minioService: MinioService,
    private val s3ObjectRepository : S3ObjectRepository
) {

    fun uploadFile(file: MultipartFile, bucket: String): Int {
        // upload to minio
        val newFile = minioService.uploadFile(file, bucket)
        // store in database
        val s3Object = S3Object(
            filename = newFile.filename,
            bucket = newFile.bucket,
            contentType = newFile.contentType,
            status = true
        )
        val savedS3Object = s3ObjectRepository.save(s3Object)
        return savedS3Object.id!!
    }
}