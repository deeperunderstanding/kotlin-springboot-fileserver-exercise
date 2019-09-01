package org.djanz.fileserver.storage

import org.djanz.fileserver.model.db.Tag
import org.springframework.data.repository.CrudRepository

interface TagRepository : CrudRepository<Tag, Long> {
    fun findByNameIn(names : List<String>): List<Tag>
}

