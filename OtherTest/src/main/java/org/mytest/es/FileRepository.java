package org.mytest.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface FileRepository extends ElasticsearchRepository<IndexedFile, String> {

    public List<IndexedFile> findByName(String name);

}
