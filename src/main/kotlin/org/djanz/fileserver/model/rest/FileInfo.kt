package org.djanz.fileserver.model.rest

import com.fasterxml.jackson.annotation.JsonProperty

data class FileInfo(
        @JsonProperty
        val fileName: String,

        @JsonProperty
        val tags: List<String>
)
