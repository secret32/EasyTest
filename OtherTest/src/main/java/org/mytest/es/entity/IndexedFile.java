package org.mytest.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Mapping
@Document(indexName = "diskfiles", type = "file", replicas = 0)
@Data
public class IndexedFile {

    @Id
    @Field(index = false)
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Keyword),
            otherFields = {
                @InnerField(suffix = "ik", type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
            }
    )
    private String name;

    @Field(type = FieldType.Keyword)
    private String dir;

    @Field(type = FieldType.Integer)
    private Integer type;

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String content;

    @Field(type = FieldType.Keyword)
    private String md5;

}
