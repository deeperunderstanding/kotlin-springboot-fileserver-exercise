package org.djanz.fileserver.storage

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
class LocalFileStorage : FileStorage {

    private final val storageLocation: Path = Paths.get("files")

    init {
        if (!Files.exists(storageLocation)) {
            Files.createDirectory(storageLocation)
        }
    }

    override fun store(file: MultipartFile) {
        Files.copy(file.inputStream, this.storageLocation.resolve(file.originalFilename))
    }

    override fun loadFile(filename: String): Resource {
        val file = storageLocation.resolve(filename)
        val resource = UrlResource(file.toUri())

        if (resource.exists() || resource.isReadable) {
            return resource
        } else {
            throw RuntimeException("Unable to load file $filename")
        }
    }

    override fun listAllFiles(): Stream<Path> {
        return Files.walk(this.storageLocation, 1)
                .filter { Files.isRegularFile(it) }
                .map { this.storageLocation.relativize(it) }
    }
}