package org.mytest.es;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository repository;

    @Override
    public void index(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                Arrays.stream(children).forEach(this::index);
            }
        } else {
            indexSingleFile(file);
        }
    }

    private void indexSingleFile(File file) {
        String filePath = file.getAbsoluteFile().toString();
        IndexedFile indexedFile = new IndexedFile();
        indexedFile.setId(DigestUtils.md5Hex(filePath));
        indexedFile.setName(file.getName());
        indexedFile.setAuthor("test");
        indexedFile.setDescription("");
        indexedFile.setType("file");
        indexedFile.setModifier("test");
        try {
            indexedFile.setMd5(DigestUtils.md5Hex(Files.readAllBytes(file.toPath())));
        } catch (IOException e) {
            log.error("read file [{}] error", file.getAbsoluteFile().toString(), e);
        }
        repository.index(indexedFile);
        System.out.println(file.getName() + " indexed");
    }

    @Override
    public List<IndexedFile> findAll() {
        Iterable<IndexedFile> it = repository.findAll();
        List<IndexedFile> list = new ArrayList<>();
        it.forEach(list::add);
        return list;
    }

    @Override
    public List<IndexedFile> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void deleteAllIndex() {
        repository.deleteAll();
    }
}
