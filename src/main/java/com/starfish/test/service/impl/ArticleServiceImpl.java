package com.starfish.test.service.impl;

import com.google.common.collect.EvictingQueue;
import com.starfish.test.entity.ArticleEntity;
import com.starfish.test.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 * ArticleServiceImpl
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2020-09-13
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    /**
     * 大小固定的队列，只保存最后N个元素
     * reference <a href="https://blog.csdn.net/liuxiao723846/article/details/81782507">参考文档</a>
     */
    Queue<ArticleEntity> messageQueue = EvictingQueue.create(10);

    @Override
    public void add(ArticleEntity articleEntity) {
        // TODO 写入数据库
        articleEntity.setCreateTime(new Date());

        // 缓存到队列
        messageQueue.add(articleEntity);
    }

    @Override
    public List<ArticleEntity> getLatestArticle() {
        return new ArrayList<>(messageQueue);
    }

}
