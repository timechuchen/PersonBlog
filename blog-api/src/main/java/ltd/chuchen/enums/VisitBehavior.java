package ltd.chuchen.enums;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description 访问行为枚举类
 */
public enum VisitBehavior {

    UNKNOWN("UNKNOWN", "UNKNOWN"),

    INDEX("访问页面", "首页"),
    ARCHIVE("访问页面", "学无止境"),
    MOMENT("访问页面", "岁言碎语"),
    ABOUT("访问页面", "关于我"),
    MES_BOARD("访问页面","留言板"),

    BLOG("查看博客", ""),
    CATEGORY("查看分类", ""),
    TAG("查看标签", ""),
    SEARCH("搜索博客", ""),
    LIKE_MOMENT("点赞动态", ""),
    CHECK_PASSWORD("查看评论", ""),
    CHECK_LOG("查看日志", ""),
    COMMENT("查看动态","");

    private final String behavior;

    private final String content;

    VisitBehavior(String behavior, String content) {
        this.behavior = behavior;
        this.content = content;
    }

    public String getBehavior() {
        return behavior;
    }

    public String getContent() {
        return content;
    }
}
