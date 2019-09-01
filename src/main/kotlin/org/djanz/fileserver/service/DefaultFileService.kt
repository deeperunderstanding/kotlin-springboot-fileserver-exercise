package org.djanz.fileserver.service

import org.djanz.fileserver.model.db.FileMetadata
import org.djanz.fileserver.model.db.Tag
import org.djanz.fileserver.model.rest.FileInfo
import org.djanz.fileserver.storage.FileRepository
import org.djanz.fileserver.storage.FileStorage
import org.djanz.fileserver.storage.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class DefaultFileService @Autowired constructor(
        val fileStorage: FileStorage,
        val tagRepo: TagRepository,
        val fileRepo: FileRepository) : FileService {

    override fun allFiles(): List<FileInfo> {
        return fileStorage.listAllFiles().map { it.toString() }.toList().map { fileName ->
            FileInfo(fileName = fileName,
                    tags = fileRepo.findByFileName(fileName)?.tags?.toList()?.map { it.name } ?: listOf())
        }
    }

    override fun allFilesWithTag(tag: String): List<FileInfo> {
        return fileRepo.findAllByTags_Name(tag).map { metadata ->
            FileInfo(metadata.fileName, metadata.tags.map { it.name })
        }
    }

    override fun setFileMetaData(info: FileInfo) {
        val fileEntity = fileRepo.findByFileName(info.fileName) ?: FileMetadata(
                fileName = info.fileName)

        fileEntity.tags = HashSet(persistantTags(info.tags) + fileEntity.tags)

        fileRepo.save(fileEntity)
    }

    fun persistantTags(tags: List<String>): List<Tag> {
        val storedTags = tagRepo.findByNameIn(tags)
        val newTags = storedTags.map { it.name }
                .let { existing -> tags.filter { !existing.contains(it) } }
                .map { tagRepo.save(Tag(name = it)) }

        return storedTags + newTags
    }


}
