package ltd.chuchen.service;

import ltd.chuchen.model.dto.BlogInfo;
import ltd.chuchen.model.dto.BlogViewListInfo;
import ltd.chuchen.model.dto.BlogVisibility;
import ltd.chuchen.model.vo.BlogDetail;
import ltd.chuchen.model.vo.BlogInfos;

import java.util.List;
import java.util.Map;

/**
 * @Author chuchen
 * @Date 2022/3/16
 * @Description 博客服务层
 */
public interface BlogService {

    Boolean deleteBlogById(Long id);

    Integer saveBlog(BlogInfo blog,String type);

    Boolean saveBlogTag(Long blogId, Long tagId);

    Boolean updateBlogRecommendById(Long blogId, Boolean recommend);

    Boolean updateBlogVisibilityById(Long blogId, BlogVisibility blogVisibility);

    Boolean updateBlogTopById(Long blogId, Boolean top);

    Boolean updateViews(Long blogId, Integer views);

    BlogInfos getBlogById(Long id);

    BlogDetail getBlogByIdAndIsPublished(Long id);

    String getBlogPassword(Long blogId);

    Boolean updateBlog(BlogInfo blog);

    Integer countBlogByIsPublished();

    Integer countBlogByCategoryId(Long categoryId);

    Integer countBlogByTagId(Long tagId);

    Boolean getCommentEnabledByBlogId(Long blogId);

    Boolean getPublishedByBlogId(Long blogId);

    Map<String, Object> getCategoryAndTag();

    Map<String, Object> getBlogListInfo();

    List<BlogViewListInfo> getBlogViewList();

    List<BlogViewListInfo> getRecommendBlog();

    List<BlogViewListInfo> getBlogByCategory(String category);

    List<BlogViewListInfo> getBlogBysearch(String search);
}
