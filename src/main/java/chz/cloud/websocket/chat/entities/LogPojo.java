package chz.cloud.websocket.chat.entities;

import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/09/22/16:02
 * @Description:
 */
@Data
@Document(indexName = "test_log",shards = 1,replicas = 1)
public class LogPojo {
    @Id
    private String id;
    @Field(type=FieldType.Integer)
    private Integer port;
    @Field(type=FieldType.Text)
    private String message;
    @Field(type = FieldType.Keyword,name = "@version")
    private String version;
    //默认时间转换能力为none,不转换,不转换用String能接,Date得转换后才能接
    @Field(type = FieldType.Date,name = "@timestamp",format = DateFormat.date_time)
    private Date timestamp;
    @Field(type = FieldType.Keyword)
    private String host;
}
