package org.mytest.es.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.mytest.es.entity.IndexedFile;
import org.mytest.es.repo.FileRepository;
import org.mytest.es.service.FileService;
import org.mytest.es.util.FileSuffixType;
import org.mytest.es.util.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public void index(File file) {
        if (file.isDirectory()) {
            indexDir(file);
            File[] children = file.listFiles();
            if (children != null) {
                Arrays.stream(children).parallel().forEach(this::index);
            }
        } else {
            indexSingleFile(file);
        }
    }

    private void indexDir(File file) {
        File absoluteFile = file.getAbsoluteFile();
        String filePath = absoluteFile.toString();
        String filename = file.getName();
        String id = DigestUtils.md5Hex(filePath);
        if (!fileRepository.existsById(id)) {
            IndexedFile indexedFile = new IndexedFile();
            indexedFile.setId(id);
            indexedFile.setName(filename);
            indexedFile.setDir(absoluteFile.getParent());
            indexedFile.setType(FileType.DIR.ordinal());
            fileRepository.index(indexedFile);
        }
        log.info("Dir[{}] indexed", filePath);
    }

    private void indexSingleFile(File file) {
        File absoluteFile = file.getAbsoluteFile();
        String filePath = absoluteFile.toString();
        String filename = file.getName();
        String id = DigestUtils.md5Hex(filePath);

        IndexedFile indexedFile = new IndexedFile();
        indexedFile.setId(id);
        indexedFile.setName(filename);
        indexedFile.setDir(absoluteFile.getParent());
        indexedFile.setType(FileType.FILE.ordinal());
        boolean hasIndexed = false;
        long indexContentMaxSize = 1024 * 1024 * 1024;
        if (FileSuffixType.needIndexContent(filename) && file.length() < indexContentMaxSize) {
            indexedFile = fileRepository.findById(id).orElse(indexedFile);
            String expectedMd5 = indexedFile.getMd5();
            String actualMd5 = null;
            try {
                actualMd5 = DigestUtils.md5Hex(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                log.error("Index file[{}] error", filePath, e);
                return;
            }
            if (actualMd5.equals(expectedMd5)) {
                log.info("File[{}] has been indexed in the past", filename);
                hasIndexed = true;
            } else {
                indexedFile.setMd5(actualMd5);
                try {
                    String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                    indexedFile.setContent(content);
                } catch (IOException e) {
                    log.error("Index file[{}] error", filePath, e);
                    return;
                }
            }
        }
        if (!hasIndexed) {
            fileRepository.index(indexedFile);
        }
        log.info("File[{}] indexed", filePath);
    }

    @Override
    public List<IndexedFile> findAll() {
        Iterable<IndexedFile> it = fileRepository.findAll();
        List<IndexedFile> list = new ArrayList<>();
        it.forEach(list::add);
        return list;
    }

    @Override
    public List<IndexedFile> findByName(String name) {
        return fileRepository.findByName(name);
    }

    @Override
    public List<IndexedFile> findByContent(String content) {
        return fileRepository.findByContent(content);
    }

    @Override
    public void deleteAllIndex() {
        fileRepository.deleteAll();
    }
}
