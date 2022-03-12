package ltd.chuchen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ltd.chuchen.entity.Record;
import ltd.chuchen.mapper.RecordMapper;
import ltd.chuchen.model.dto.RecordInfo;
import ltd.chuchen.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Boolean saveMoment(RecordInfo recordInfo) {
        Record record = new Record();
        record.setTitle(recordInfo.getTitle());
        record.setContent(recordInfo.getContent());
        record.setPublished(recordInfo.getPublished());
        int insert = recordMapper.insert(record);
        return insert > 0;
    }

    @Override
    public IPage<Record> selectPage(Page<Record> page) {
        return recordMapper.selectPage(page,null);
    }

    @Override
    public List<Record> selectAll() {
        return recordMapper.selectAll();
    }

    @Override
    public Boolean updateMomentPublishedById(Long id, Boolean published) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        System.out.println(id);
        Record record = recordMapper.selectOne(queryWrapper);
        record.setPublished(published);
        int i = recordMapper.updateById(record);
        return i > 0;
    }

    @Override
    public boolean deleteRecordById(Long id) {
        int i = recordMapper.deleteById(id);
        return i > 0;
    }

    @Override
    public Record getRecordById(Long id) {
        return recordMapper.selectById(id);
    }

    @Override
    public boolean updateRecord(Record record) {
        int i = recordMapper.updateById(record);
        return i > 0;
    }
}
