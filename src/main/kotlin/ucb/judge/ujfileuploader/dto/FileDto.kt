package ucb.judge.ujfileuploader.dto

data class FileDto(
    val s3ObjectId: Long = 0,
    val contentType: String = "",
    val bucket: String = "",
    val filename: String = "",
    val status: Boolean = true
)
