package chz.cloud.websocket.chat.service.impl;

import chz.cloud.websocket.chat.entities.LogPojo;
import chz.cloud.websocket.chat.service.LogService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/09/22/16:15
 * @Description:
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<LogPojo> selectPage(Integer page, Integer rows) {
        //小于当前时间15分钟的时间对象
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -15);
        //查询最近15分钟内的日志。
        Query query = new NativeSearchQuery(QueryBuilders.rangeQuery("@timestamp").gte(calendar.getTime()));
        query.setPageable(PageRequest.of(page - 1, rows));
        SearchHits<LogPojo> search = elasticsearchRestTemplate.search(query, LogPojo.class);
        List<LogPojo> list = new ArrayList<>();
        for (SearchHit<LogPojo> searchHit : search.getSearchHits()) {
            list.add(searchHit.getContent());
        }
        return list;
    }
}
