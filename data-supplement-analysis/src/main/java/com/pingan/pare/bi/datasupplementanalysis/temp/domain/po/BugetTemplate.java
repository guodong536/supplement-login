package com.pingan.pare.bi.datasupplementanalysis.temp.domain.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "excel_template")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BugetTemplate {
    @Field
    private String id;

    @Field
    private String name;

    @Field
    private Integer age;

    @Field
    private String tempData;
}
