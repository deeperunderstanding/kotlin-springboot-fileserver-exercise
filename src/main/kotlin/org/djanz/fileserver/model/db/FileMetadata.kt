package org.djanz.fileserver.model.db

import javax.persistence.*

@Entity
class FileMetadata(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @Column(unique = true)
        var fileName: String,

        @OneToMany
        var tags : MutableSet<Tag> = HashSet()
)