package ucb.judge.ujfileuploader.api


import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import ucb.judge.ujfileuploader.bl.FilesBl
import ucb.judge.ujfileuploader.dto.ResponseDto

@RestController
@RequestMapping("/api/v1/files")
class FilesController constructor(
    private val filesBl: FilesBl
){

    @PostMapping
    fun uploadFile(@RequestParam("file") file: MultipartFile, @RequestParam("bucket") bucket: String): ResponseEntity<ResponseDto<Long>>{
        val id = filesBl.uploadFile(file, bucket)
        return ResponseEntity.ok(ResponseDto(data = id, message = "File uploaded successfully", successful = true))
    }
}