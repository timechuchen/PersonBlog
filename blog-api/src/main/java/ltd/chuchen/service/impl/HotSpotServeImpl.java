package ltd.chuchen.service.impl;

import lombok.experimental.Accessors;
import ltd.chuchen.entity.HotSpot;
import ltd.chuchen.mapper.HotSpotMapper;
import ltd.chuchen.model.dto.HotTagInfo;
import ltd.chuchen.service.HotSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Accessors(chain = true)  //链式写法
public class HotSpotServeImpl implements HotSpotService {

    @Autowired
    private HotSpotMapper hotSpotMapper;

    @Override
    public boolean updateHotSpot(List<HotTagInfo> hots) {
        hotSpotMapper.deleteHotSpot();
        for(HotTagInfo hot : hots){
            HotSpot hotSpot = new HotSpot();
            hotSpot.setTitle(hot.getTitle());
            hotSpot.setUrl(hot.getUrl());
            hotSpot.setSubject(hot.getSubject());
            hotSpot.setHits(hot.getHits());
            hotSpot.setColor(hot.getColor());
            hotSpotMapper.insert(hotSpot);
        }
        return true;
    }

    @Override
    public List<HotSpot> getAllHotSpot() {
       return hotSpotMapper.selectAll();
    }

    @Override
    public List<HotTagInfo> getHotTageInfo() {
        List<HotSpot> hotSpots = hotSpotMapper.selectAll();
        List<HotTagInfo> hotTagInfos = new LinkedList<>();
        for(HotSpot hotSpot : hotSpots){
            HotTagInfo hotTagInfo = new HotTagInfo();
            hotTagInfo.setTitle(hotSpot.getTitle());
            hotTagInfo.setUrl(hotSpot.getUrl());
            hotTagInfo.setSubject(hotSpot.getSubject());
            hotTagInfo.setHits(hotSpot.getHits());
            hotTagInfo.setColor(hotSpot.getColor());
            hotTagInfos.add(hotTagInfo);
        }
        return hotTagInfos;
    }
}
