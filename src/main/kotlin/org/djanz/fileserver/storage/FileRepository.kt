package org.djanz.fileserver.storage

import org.djanz.fileserver.model.db.FileMetadata
import org.springframework.data.repository.CrudRepository

interface FileRepository : CrudRepository<FileMetadata, Long> {
    fun findByFileName(fileName: String): FileMetadata?
    fun findAllByTags_Name(tag: String): List<FileMetadata>
}

