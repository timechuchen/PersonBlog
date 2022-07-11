package ltd.chuchen.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.Blog;
import ltd.chuchen.entity.Category;
import ltd.chuchen.entity.Tag;
import ltd.chuchen.mapper.BlogMapper;
import ltd.chuchen.model.dto.BlogInfo;
import ltd.chuchen.model.dto.BlogViewListInfo;
import ltd.chuchen.model.dto.BlogVisibility;
import ltd.chuchen.model.vo.BlogDetail;
import ltd.chuchen.model.vo.BlogInfos;
import ltd.chuchen.model.vo.BolgListInfo;
import ltd.chuchen.service.BlogService;
import ltd.chuchen.service.CategoryService;
import ltd.chuchen.service.CommentService;
import ltd.chuchen.service.TagService;
import ltd.chuchen.utils.RedisUtil;
import ltd.chuchen.utils.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author chuchen
 * @Date 2022/3/16
 * @Description 博客服务层实现类
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @PostConstruct
    private void init() {
        updateRedisOfBlogViewList();
        Boolean aBoolean = updateDataOfElasticSearch();
        if(aBoolean) {
            System.out.println("ok");
        }else {
            System.out.println("error");
        }
    }

    @Override
    public Boolean deleteBlogById(Long id) {
        blogMapper.deleteTag(id);
        commentService.deleteCommentByBlogId(id);
        int i = blogMapper.deleteById(id);
        if(i == 1) {
            //更新 redis 缓存数据
            updateRedisOfBlogViewList();
            updateBlogListInfo();
            deleteBlogByElasticSearch(id);
            return true;
        }
        return false;
    }

    /**
     *保存博客的服务层
     * @param blog 前台传送回来的博客信息
     * @return 1--添加成功
     *         0--参数有误
     *         2--分类不能为空
     *         5--该分类不存在
     *         6--标签不正确
     *         7--标签映射失败
     *         8--服务器错误
     */
    @Override
    public Integer saveBlog(BlogInfo blog,String type) {
        System.out.println(blog);
        Blog b = new Blog();
        //验证普通字段
        if (StringUtils.isEmpty(blog.getTitle(), blog.getFirstPicture(), blog.getContent(), blog.getDescription())
                || blog.getWords() == null || blog.getWords() < 0) {
            return 0;
        }

        //处理分类
        String cate = blog.getCate();
        if("".equals(cate)) {
            return 2;
        }else {
            Category categoryByName = categoryService.getCategoryByName(cate);
            if(categoryByName == null) return 5;
            b.setCategoryId(categoryByName.getId());
        }

        b.setTitle(blog.getTitle())
                .setDescription(blog.getDescription())
                .setContent(blog.getContent())
                .setFirstPicture(blog.getFirstPicture())
                .setPublished(blog.getPublished())
                .setRecommend(blog.getRecommend())
                .setAppreciation(blog.getAppreciation())
                .setCommentEnabled(blog.getCommentEnabled())
                .setTop(blog.getTop())
                .setViews(blog.getViews())
                .setWords(blog.getWords())
                .setReadTime(blog.getReadTime())
                .setPassword(blog.getPassword());

        int insert;
        if("save".equals(type)) {
            insert = blogMapper.insert(b);

            QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("title",blog.getTitle());
            Long blogId = blogMapper.selectOne(queryWrapper).getId();

            addDataOfElasticSearch(new BlogViewListInfo()
                    .setBlogId(b.getId())
                    .setBlogTitle(b.getTitle())
                    .setBlogPic(b.getFirstPicture())
                    .setBlogTage(tagService.getTagListByBlogId(b.getId()))
                    .setUpdateTime(b.getUpdateTime())
                    .setComment(commentService.getCommentCountByBlogId(b.getId()))
                    .setDescription(b.getDescription())
                    .setPublished(b.getPublished())
                    .setPassword(b.getPassword()));

            //处理标签
            List<String> tagList = blog.getTagList();
            for(String t : tagList) {
                Tag tagByName = tagService.getTagByName(t);
                if(tagByName == null) return 6;
                blogMapper.saveBlogTag(blogId, tagByName.getId());
            }
        }else {
            b.setId(blog.getId());
            insert = blogMapper.updateById(b);

            //更新 ElasticSearch 中的信息
            updateSingleDataOfElasticSearch(new BlogViewListInfo()
                    .setBlogId(b.getId())
                    .setBlogTitle(b.getTitle())
                    .setBlogPic(b.getFirstPicture())
                    .setBlogTage(tagService.getTagListByBlogId(b.getId()))
                    .setUpdateTime(b.getUpdateTime())
                    .setComment(commentService.getCommentCountByBlogId(b.getId()))
                    .setDescription(b.getDescription())
                    .setPublished(b.getPublished())
                    .setPassword(b.getPassword()));

            //处理标签
            blogMapper.deleteTag(b.getId());
            List<String> tagList = blog.getTagList();
            for(String t : tagList) {
                Tag tagByName = tagService.getTagByName(t);
                if(tagByName == null) return 6;
                Integer integer = blogMapper.saveBlogTag(b.getId(), tagByName.getId());
                if(integer != 1) return 7;
            }
        }
        if(insert == 1) {
            //更新 redis 缓存数据
            updateRedisOfBlogViewList();
            updateBlogListInfo();
            return 1;
        }else {
            return 8;
        }
    }

    @Override
    public Boolean saveBlogTag(Long blogId, Long tagId) {
        return null;
    }

    @Override
    public Boolean updateBlogRecommendById(Long blogId, Boolean recommend) {
        int update = blogMapper.updateRecommendById(blogId,recommend);
        if(update == 1) {
            //更新 redis 缓存数据
            updateRedisOfBlogViewList();

            updateBlogListInfo();
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateBlogVisibilityById(Long blogId, BlogVisibility blogVisibility) {
        System.out.println(blogVisibility);
        int update = blogMapper.updateVisibilityById(blogId, blogVisibility.getRecommend(),blogVisibility.getAppreciation(),blogVisibility.getPublished(),blogVisibility.getCommentEnabled(),blogVisibility.getTop(),blogVisibility.getPassword());
        if(update == 1) {
            //更新 redis 缓存数据
            updateRedisOfBlogViewList();
            updateBlogListInfo();
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateBlogTopById(Long blogId, Boolean top) {
        int update = blogMapper.updateTopById(blogId,top);
        if(update == 1) {
            //更新 redis 缓存数据
            updateRedisOfBlogViewList();
            updateBlogListInfo();
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateViews(Long blogId, Integer views) {
        int update = blogMapper.updateViewsById(blogId, views);
        if(update == 1) {
            //更新 redis 缓存数据
            updateRedisOfBlogViewList();
            updateBlogListInfo();
            return true;
        }
        return false;
    }

    @Override
    public BlogInfos getBlogById(Long id) {
        Blog blog = blogMapper.selectById(id);
        List<Tag> tags = tagService.getTagListByBlogId(id);
        BlogInfos blogInfos = new BlogInfos();
        blogInfos.setId(id)
                .setTitle(blog.getTitle())
                .setFirstPicture(blog.getFirstPicture())
                .setDescription(blog.getDescription())
                .setContent(blog.getContent())
                .setCategory(categoryService.getCategoryById(blog.getCategoryId()))
                .setTags(tags)
                .setWords(blog.getWords())
                .setReadTime(blog.getReadTime())
                .setViews(blog.getViews())
                .setAppreciation(blog.getAppreciation())
                .setRecommend(blog.getRecommend())
                .setCommentEnabled(blog.getCommentEnabled())
                .setTop(blog.getTop())
                .setPublished(blog.getPublished())
                .setPassword(blog.getPassword());
        return blogInfos;
    }

    @Override
    public BlogDetail getBlogByIdAndIsPublished(Long id) {
        return null;
    }

    @Override
    public String getBlogPassword(Long blogId) {
        return blogMapper.selectById(blogId).getPassword();
    }

    @Override
    public Boolean updateBlog(BlogInfo blog) {
        return null;
    }

    @Override
    public Integer countBlogByIsPublished() {
        return null;
    }

    @Override
    public Integer countBlogByCategoryId(Long categoryId) {
        return null;
    }

    @Override
    public Integer countBlogByTagId(Long tagId) {
        return null;
    }

    @Override
    public Boolean getCommentEnabledByBlogId(Long blogId) {
        return null;
    }

    @Override
    public Boolean getPublishedByBlogId(Long blogId) {
        return null;
    }

    @Override
    public Map<String, Object> getCategoryAndTag() {
        Map<String, Object> map = new HashMap<>();
        List<Category> categoryList = categoryService.getCategoryList();
        List<Tag> tagList = tagService.getTagList();
        map.put("categories",categoryList);
        map.put("tags",tagList);
        return map;
    }

    @Override
    public Map<String, Object> getBlogListInfo() {
        String redisKey = RedisKeyConstant.ADMIN_BLOG_INFO_LIST;
        ObjectMapper mapper = new ObjectMapper();

        Object o = redisUtil.get(redisKey);
        if(o == null) {
            return updateBlogListInfo();
        }
        String json = null;
        HashMap<String, Object> blogListInfosByRedis = null;
        try {
            json = mapper.writeValueAsString(o);
            blogListInfosByRedis = mapper.readValue(json, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(blogListInfosByRedis != null) {
            return blogListInfosByRedis;
        }
        return updateBlogListInfo();
    }

    @Override
    public List<BlogViewListInfo> getBlogViewList() {
        String redisKey = RedisKeyConstant.HOME_BLOG_INFO_LIST;
        Object o = redisUtil.get(redisKey);
        if(o == null) {
            return updateRedisOfBlogViewList();
        }
        List<BlogViewListInfo> blogViewListInfosByRedis = JSON.parseArray(JSON.toJSONString(o),BlogViewListInfo.class);

        if(blogViewListInfosByRedis != null) {
            return blogViewListInfosByRedis;
        }
        //redis没有缓存，从数据库查询，并添加缓存
        return updateRedisOfBlogViewList();
    }

    /**
     * 获取推荐文章信息
     * @return 推荐文章信息
     */
    @Override
    public List<BlogViewListInfo> getRecommendBlog() {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("recommend",true);
        List<Blog> blogs = blogMapper.selectList(queryWrapper);
        List<BlogViewListInfo> blogViewListInfos  = new ArrayList<>();
        blogs.forEach((o)->{
            blogViewListInfos.add(new BlogViewListInfo()
                    .setBlogId(o.getId())
                    .setBlogPic(o.getFirstPicture())
                    .setBlogTitle(o.getTitle())
                    .setDescription(o.getDescription()));
        });
        return blogViewListInfos;
    }

    @Override
    public List<BlogViewListInfo> getBlogByCategory(String category) {
        Category categoryByName = categoryService.getCategoryByName(category);
        if(categoryByName == null) {
            return null;
        }
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        List<BlogViewListInfo> blogViewListInfos = new ArrayList<>();
        queryWrapper.eq("category_id",categoryByName.getId());
        List<Blog> blogs = blogMapper.selectList(queryWrapper);
        blogs.forEach((b)->{
            blogViewListInfos.add(new BlogViewListInfo()
                    .setBlogId(b.getId())
                    .setBlogTitle(b.getTitle())
                    .setBlogPic(b.getFirstPicture())
                    .setBlogTage(tagService.getTagListByBlogId(b.getId()))
                    .setUpdateTime(b.getUpdateTime())
                    .setComment(commentService.getCommentCountByBlogId(b.getId()))
                    .setDescription(b.getDescription())
                    .setPublished(b.getPublished())
                    .setPassword(b.getPassword()));
        });
        return blogViewListInfos;
    }

    @Override
    public List<BlogViewListInfo> getBlogBySearch(String search) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        List<BlogViewListInfo> blogViewListInfos = new ArrayList<>();
        queryWrapper.like("title",search);
        List<Blog> blogs = blogMapper.selectList(queryWrapper);
        if(blogs == null) return null;
        blogs.forEach((b)->{
            blogViewListInfos.add(new BlogViewListInfo()
                    .setBlogId(b.getId())
                    .setBlogTitle(b.getTitle())
                    .setBlogPic(b.getFirstPicture())
                    .setBlogTage(tagService.getTagListByBlogId(b.getId()))
                    .setUpdateTime(b.getUpdateTime())
                    .setComment(commentService.getCommentCountByBlogId(b.getId()))
                    .setDescription(b.getDescription())
                    .setPublished(b.getPublished())
                    .setPassword(b.getPassword()));
        });
        return blogViewListInfos;
    }

    @Override
    public List<BlogViewListInfo> getBlogByElasticSearch(String search) {
        //条件搜索
        SearchRequest searchRequest = new SearchRequest("cc_blog_info");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //精准匹配关键字
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("blogTitle", search);
        sourceBuilder.query(matchQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("blogTitle");
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");

        sourceBuilder.highlighter(highlightBuilder);

        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResult = null;
        try {
            searchResult = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //解析结果
        ArrayList<BlogViewListInfo> list = new ArrayList<>();
        for(SearchHit documentFields : searchResult.getHits()) {
            //解析高亮的字段
            Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
            HighlightField title = highlightFields.get("blogTitle");
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();

            //将原来的字段换成高亮的字段
            if(title != null) {
                Text[] fragments = title.fragments();
                String newTitle = "";
                for(Text text : fragments) {
                    newTitle += text;
                }
                sourceAsMap.put("blogTitle",newTitle); //高亮字段替换为原来的内容即可
            }


            list.add(JSONObject.parseObject(JSONObject.toJSONString(sourceAsMap),BlogViewListInfo.class));
        }

        return list;
    }


    /**
     * 更新 redis 中的后台博客列表信息：直接从数据库中查询到数据，将redis中对用的 key 的 value 更新
     */
    protected Map<String,Object> updateBlogListInfo() {
        String redisKey = RedisKeyConstant.ADMIN_BLOG_INFO_LIST;
        Map<String,Object> map = new HashMap<>();

        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        wrapper.select("id", "title", "category_id", "recommend","password", "top","create_time", "update_time","published","appreciation","comment_enabled");
        List<Blog> blogs = blogMapper.selectList(wrapper);
        List<BolgListInfo> bolgListInfos = new LinkedList<>();
        for (Blog b : blogs) {
            bolgListInfos.add(
                    new BolgListInfo()
                            .setId(b.getId())
                            .setTitle(b.getTitle())
                            .setPassword(b.getPassword())
                            .setPublished(b.getPublished())
                            .setCategory(categoryService.getCategoryById(b.getCategoryId()).getCategoryName())
                            .setRecommend(b.getRecommend())
                            .setTop(b.getTop())
                            .setAppreciation(b.getAppreciation())
                            .setCommentEnabled(b.getCommentEnabled())
                            .setCreateTime(b.getCreateTime())
                            .setUpdateTime(b.getUpdateTime())
            );
        }
        map.put("blogListInfo",bolgListInfos);
        List<Category> categories = categoryService.getCategoryList();
        map.put("categories",categories);
        redisUtil.set(redisKey,map);
        return map;
    }

    /**
     * 更新 redis 中的博客列表信息：直接从数据库中查询到数据，将redis中对用的 key 的 value 更新
     */
    protected List<BlogViewListInfo> updateRedisOfBlogViewList() {
        String redisKey = RedisKeyConstant.HOME_BLOG_INFO_LIST;
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        wrapper.select("id", "title", "first_picture", "update_time","views", "description","published", "password");
        List<Blog> blogs = blogMapper.selectList(wrapper);
        List<BlogViewListInfo> blogViewListInfos = new LinkedList<>();
        for(Blog b : blogs) {
            BlogViewListInfo blogViewListInfo = new BlogViewListInfo();
            blogViewListInfo
                    .setBlogId(b.getId())
                    .setBlogTitle(b.getTitle())
                    .setBlogPic(b.getFirstPicture())
                    .setBlogTage(tagService.getTagListByBlogId(b.getId()))
                    .setUpdateTime(b.getUpdateTime())
                    .setComment(commentService.getCommentCountByBlogId(b.getId()))
                    .setDescription(b.getDescription())
                    .setPublished(b.getPublished())
                    .setPassword(b.getPassword());
            blogViewListInfos.add(blogViewListInfo);
        }
        redisUtil.set(redisKey,blogViewListInfos);
        return blogViewListInfos;
    }

    // 向 ElasticSearch 更新单个数据库信息
    protected Boolean updateSingleDataOfElasticSearch(BlogViewListInfo blogViewListInfo) {
        UpdateRequest updateRequest = new UpdateRequest("cc_blog_info", blogViewListInfo.getBlogId()+"");
        updateRequest.timeout("1s");
        updateRequest.doc(JSON.toJSONString(blogViewListInfo),XContentType.JSON);
        UpdateResponse updateResponse = null;
        try {
            updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert updateResponse != null;
        RestStatus status = updateResponse.status();
        return status.getStatus() == 200;
    }
    // 向 ElasticSearch 更新数据库信息
    protected Boolean updateDataOfElasticSearch() {
        List<BlogViewListInfo> blogViewListInfos = updateRedisOfBlogViewList();
        //将查询到的数据放入到 ES 中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");

        for (BlogViewListInfo blogViewListInfo : blogViewListInfos) {
            IndexRequest request = new IndexRequest("cc_blog_info");
            request.id(blogViewListInfo.getBlogId()+"");
            bulkRequest.add(request.source(JSON.toJSONString(blogViewListInfo), XContentType.JSON));
        }

        BulkResponse bulk;
        try {
            bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return !bulk.hasFailures();
    }

    //向 ElasticSearch 中插入数据
    protected Boolean addDataOfElasticSearch(BlogViewListInfo blogViewListInfo) {
        IndexRequest request = new IndexRequest("cc_blog_info");
        //设置一些规则
        request.id(blogViewListInfo.getBlogId()+"");
        request.timeout(TimeValue.timeValueSeconds(3));

        //将我们的数据放入请求 json
        request.source(JSON.toJSONString(blogViewListInfo), XContentType.JSON);

        //客户端发送请求
        IndexResponse response = null;
        try {
            response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.status().getStatus() == 200;
    }

    //删除 ElasticSearch 中数据
    private Boolean deleteBlogByElasticSearch(Long id) {
        DeleteRequest deleteRequest = new DeleteRequest("cc_blog_info",id+"");
        deleteRequest.timeout("1s");
        DeleteResponse delete = null;
        try {
            delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert delete != null;
        return delete.status().getStatus() == 200;
    }
}



