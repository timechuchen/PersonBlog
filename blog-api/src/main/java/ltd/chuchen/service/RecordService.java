package ltd.chuchen.service;

import ltd.chuchen.entity.Record;

import java.util.List;

public interface RecordService {

     Boolean addRecord();

     Boolean updateRecord(Long id);

     Boolean deleteRecord(Long id);

     List<Record> selectRecord();

     Record selectRecordOne(Long id);
}
