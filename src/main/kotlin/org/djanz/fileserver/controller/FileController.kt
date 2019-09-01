package org.djanz.fileserver.controller

import org.djanz.fileserver.model.rest.FileInfo
import org.djanz.fileserver.service.FileService
import org.djanz.fileserver.storage.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
class FileController @Autowired constructor(val tagRepo: TagRepository,
                                            val fileService : FileService) {

    @PostMapping("/file")
    fun uploadFile(@RequestParam("file") file: MultipartFile) =
            fileService.storeFile(file)

    @GetMapping("/file/{name}")
    fun downloadFile(@PathVariable("name") fileName: String): ResponseEntity<Resource> =
            fileService.loadFile(fileName).let { file ->
                ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.filename + "\"")
                        .body(file)
            }


    @GetMapping("/files")
    fun filelist(): List<FileInfo> = fileService.allFiles()

    @GetMapping("/files/{tag}")
    fun filesByTag(@PathVariable("tag") tag: String): List<FileInfo> =
            fileService.allFilesWithTag(tag)


    @GetMapping("/files/tags")
    fun getAllTags(): List<String> = tagRepo.findAll().map { it.name }.toList()


    @PostMapping("/file/tags")
    fun setFileMetadata(@RequestBody fileInfo: FileInfo) =
            fileService.setFileMetaData(fileInfo)




}