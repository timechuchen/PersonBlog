package ltd.chuchen.service;


import ltd.chuchen.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> getTagList();

    List<Tag> getTagListNotId();

    List<Tag> getTagListByBlogId(Long blogId);

    Integer saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    Boolean deleteTagById(Long id);

    Boolean updateTag(Tag tag);

}
