package ltd.chuchen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ltd.chuchen.entity.Category;
import ltd.chuchen.mapper.CategoryMapper;
import ltd.chuchen.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Category> getCategoryNameList() {
        return null;
    }

    @Override
    public Integer saveCategory(Category category) {
        if(this.getCategoryByName(category.getCategoryName()) != null) return 1;
        else if(categoryMapper.insert(category) == 1) {
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
        return delete == 1;
    }

    @Override
    public Boolean updateCategory(Category category) {
        int i = categoryMapper.updateById(category);
        return i == 1;
    }
}
