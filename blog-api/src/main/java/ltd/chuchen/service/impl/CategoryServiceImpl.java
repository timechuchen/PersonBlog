package ltd.chuchen.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.Category;
import ltd.chuchen.mapper.CategoryMapper;
import ltd.chuchen.model.dto.BlogViewListInfo;
import ltd.chuchen.service.CategoryService;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author 初晨
 * @Date 2022/3/15
 * @Description 分类服务层
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisUtil redisUtil;

    @PostConstruct
    private void init() {
        updateCategoryListOfRedis();
    }

    @Override
    public List<Category> getCategoryList() {
        String redisKey = RedisKeyConstant.CATEGORY_LIST;
        Object o = redisUtil.get(redisKey);
        if(o == null) {
            return updateCategoryListOfRedis();
        }
        List<Category> categoryList = JSON.parseArray(JSON.toJSONString(o),Category.class);

        if(categoryList != null) {
            return categoryList;
        }
        //redis没有缓存，从数据库查询，并添加缓存
        return updateCategoryListOfRedis();
    }


    @Override
    public List<Category> getCategoryNameList() {
        return null;
    }

    @Override
    public Integer saveCategory(Category category) {
        if(this.getCategoryByName(category.getCategoryName()) != null) return 1;
        else if(categoryMapper.insert(category) == 1) {
            updateCategoryListOfRedis();
            return 0;
        }else {
            return 2;
        }
    }

    @Override
    public Category getCategoryById(Long id) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return categoryMapper.selectOne(queryWrapper);
    }

    @Override
    public Category getCategoryByName(String name) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",name);
        return categoryMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean deleteCategoryById(Long id) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        int delete = categoryMapper.delete(queryWrapper);
        if(delete == 1) {
            updateCategoryListOfRedis();
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateCategory(Category category) {
        int i = categoryMapper.updateById(category);
        if(i == 1) {
            updateCategoryListOfRedis();
            return true;
        }
        return false;
    }

    /**
     * 更新 redis 中的分类信息
     */
    protected  List<Category> updateCategoryListOfRedis() {
        String redisKey = RedisKeyConstant.CATEGORY_LIST;
        List<Category> categoryList = categoryMapper.selectAll();
        redisUtil.set(redisKey,categoryList);
        return categoryList;
    }
}
