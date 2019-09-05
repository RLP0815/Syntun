package com.syntun.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syntun.dao.MeetingRoomDao;
import com.syntun.entity.MeetingRoom;
import com.syntun.service.MeetingRoomService;

@Service("meetingRoomService")
public class MeetingRoomServiceImpl implements MeetingRoomService{
	
    @Resource(name = "meetingRoomDao")
    private MeetingRoomDao meetingRoomDao;

	@Override
	public int insert(MeetingRoom o) {
		return meetingRoomDao.insert(o);
	}

	@Override
	public int delete(MeetingRoom m) {
		return meetingRoomDao.delete(m);
	}

	@Override
	public List<MeetingRoom> find(Map params) {
		return meetingRoomDao.find(params);
	}

	@Override
	public int getTotal(Map params) {
		return meetingRoomDao.getTotal(params);
	}

}
