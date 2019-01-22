package org.mytest.es;

import java.io.File;
import java.util.List;

public interface FileService {

    public void index(File file);

    public List<IndexedFile> findAll();

    public List<IndexedFile> findByName(String name);

    public void deleteAllIndex();

}
