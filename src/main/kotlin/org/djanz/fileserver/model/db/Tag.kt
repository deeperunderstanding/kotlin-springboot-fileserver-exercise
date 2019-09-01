package org.djanz.fileserver.model.db

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Tag(
        @Id @GeneratedValue var id: Long? = null,

        @Column(unique = true)
        var name: String
)
