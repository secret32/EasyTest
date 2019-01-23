package org.mytest.es.service;

import org.mytest.es.entity.IndexedFile;

import java.io.File;
import java.util.List;

public interface FileService {

    public void index(File file);

    public List<IndexedFile> findAll();

    public List<IndexedFile> findByName(String name);

    public List<IndexedFile> findByContent(String content);

    public void deleteAllIndex();

}
