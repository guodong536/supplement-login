package com.pingan.pare.bi.datasupplementanalysis.temp.domain.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("collecion1")
public class UserDemo {

    @Id
    private String id;

    @Field
    private String age;
    @Field
    private String name;
}
