package org.mytest.es.repo;

import org.mytest.es.entity.IndexedFile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface FileRepository extends ElasticsearchRepository<IndexedFile, String> {

    public List<IndexedFile> findByName(String name);

    public List<IndexedFile> findByContent(String content);

}
