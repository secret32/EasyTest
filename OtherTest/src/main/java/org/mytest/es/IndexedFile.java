package org.mytest.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "disk", type = "file", replicas = 0)
@Data
public class IndexedFile {

    @Id
    @Field
    private String id;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private String type;

    @Field
    private Object content;

    @Field
    private String author;

    @Field
    private String md5;

    @Field
    private String modifier;

    @Field
    private String modTime;

}
