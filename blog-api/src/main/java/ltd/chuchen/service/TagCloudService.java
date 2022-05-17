package ltd.chuchen.service;

import ltd.chuchen.model.vo.TagCloudView;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/17
 * @Description 标签云服务层接口
 */
public interface TagCloudService {

    boolean addTagCloud(TagCloudView tagCloudView);

    /**
     * 获得所有的标签云
     * @return 标签云信息
     */
    List<TagCloudView> getAllTagCloud();

    /**
     * 通过id 修改标签云信息
     * @param tagCloudView 标签云
     * @return 是否修改成功
     */
    boolean updateTagCloudById(TagCloudView tagCloudView);

    /**
     * 通过id 删除标签云
     * @param id 标签云 id
     * @return 是否删除成功
     */
    boolean deleteTagCloudById(Long id);

}
