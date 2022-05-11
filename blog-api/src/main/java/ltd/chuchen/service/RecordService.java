package ltd.chuchen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ltd.chuchen.entity.Record;
import ltd.chuchen.model.dto.RecordInfo;

import java.util.List;
import java.util.Map;

public interface RecordService {

     Boolean saveMoment(RecordInfo recordInfo);

     IPage<Record> selectPage(Page<Record> page);

     List<Record> selectAll();

     Boolean updateMomentPublishedById(Long id, Boolean published);

     boolean deleteRecordById(Long id);

     Record getRecordById(Long id);

     boolean updateRecord(Record record);

    List<Record> selectAllByDesc();

     void updateLikes(String commentId);

    boolean updateLikesToMySql();

    Map<String,Integer> getLikesOfRecord();
}
