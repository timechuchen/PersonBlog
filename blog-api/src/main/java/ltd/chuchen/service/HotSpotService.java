package ltd.chuchen.service;

import ltd.chuchen.entity.HotSpot;
import ltd.chuchen.model.dto.HotTagInfo;

import java.util.List;

public interface HotSpotService {

    boolean updateHotSpot(List<HotTagInfo> typeMap);

    List<HotSpot> getAllHotSpot();

    List<HotTagInfo> getHotTageInfo();
}
