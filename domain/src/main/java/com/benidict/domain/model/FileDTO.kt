package com.benidict.domain.model

data class FileDTO (
    val url: String,
    val details: FileDetailsDTO,
    val fileName: String,
    val contentType: String
)