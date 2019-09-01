package org.djanz.fileserver.service

import org.djanz.fileserver.model.rest.FileInfo

interface FileService {

    fun allFiles(): List<FileInfo>
    fun allFilesWithTag(tag: String): List<FileInfo>
    fun setFileMetaData(info: FileInfo)
}