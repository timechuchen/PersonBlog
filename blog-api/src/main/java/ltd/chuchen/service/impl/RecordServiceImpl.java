package ltd.chuchen.service.impl;

import ltd.chuchen.entity.Record;
import ltd.chuchen.mapper.RecordMapper;
import ltd.chuchen.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Boolean addRecord() {
        return null;
    }

    @Override
    public Boolean updateRecord(Long id) {
        return null;
    }

    @Override
    public Boolean deleteRecord(Long id) {
        return null;
    }

    @Override
    public List<Record> selectRecord() {
        return null;
    }

    @Override
    public Record selectRecordOne(Long id) {
        return null;
    }
}
