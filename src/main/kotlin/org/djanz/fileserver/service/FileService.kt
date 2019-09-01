package org.djanz.fileserver.service

import org.djanz.fileserver.model.rest.FileInfo
import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile

interface FileService {

    fun storeFile(file: MultipartFile)
    fun loadFile(fileName: String): Resource
    fun allFiles(): List<FileInfo>
    fun allFilesWithTag(tag: String): List<FileInfo>
    fun setFileMetaData(info: FileInfo)
}