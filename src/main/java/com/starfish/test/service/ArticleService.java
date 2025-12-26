package com.starfish.test.service;

import com.starfish.test.entity.ArticleEntity;

import java.util.List;

/**
 * ArticleService
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2020-09-13
 */
public interface ArticleService {

    /**
     * 发送一条信息
     *
     * @param articleEntity 文章
     */
    void add(ArticleEntity articleEntity);

    /**
     * 查询最新广场信息
     *
     * @return 结果
     */
    List<ArticleEntity> getLatestArticle();

}
