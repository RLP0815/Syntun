package com.syntun.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.MeetingRoom;
import com.syntun.service.MeetingRoomService;

import net.sf.json.JSONArray;

/**
 * 
 */
@Controller
@RequestMapping(value = "/meetingRoom")
public class MeetingRoomController {
	
    @Resource(name = "meetingRoomService")
    MeetingRoomService meetingRoomService;
    
	
    /**
     * 查询预约会议室
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/queryAppointment", produces = "application/json;charset=UTF-8")
	public Map queryAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("code", "500");
		params.put("msg", "失败!");
		
		//第几会议室
		String meetingroom = request.getParameter("meetingroom");
		//会议预定时间
		String meetingroomday = request.getParameter("meetingroomday");
		if(StringUtils.isBlank(meetingroomday)){
			Date date = new Date();
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			meetingroomday = dateFormat.format(date);
		}
		if(StringUtils.isNotBlank(meetingroomday)){
			params.put("meetingroom", meetingroom);
			params.put("meetingroomday", meetingroomday);
			DataSourceContextHolder.setDbType("ds_mop");
			List<MeetingRoom> meetingInfo = meetingRoomService.find(params);
			if(meetingInfo.size()>0){
				params.put("meetingInfo", meetingInfo);
				params.put("code", "200");
				params.put("msg", "成功!");
			}
		}
	    return params;
	}
    /**
     * 预约会议室
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/appointment", produces = "application/json;charset=UTF-8")
	public Map appointment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("code", "500");
		params.put("msg", "失败!");
		
    	HttpSession session = request.getSession();
		String uid = session.getAttribute("userid").toString(); 
		String uname = session.getAttribute("userName").toString(); 
		if(StringUtils.isNotBlank(uid)){
			//第几会议室
			String meetingroom = request.getParameter("meetingroom");
			//会议预定时间
			String meetingroomday = request.getParameter("meetingroomday");
			//会议预定时间段id
			String meetingRoomInfo = request.getParameter("meetingRoomInfo");
			//会议类型
			String meetingType = request.getParameter("meetingType");
			
			JSONArray jsonArray = JSONArray.fromObject(meetingRoomInfo);//把String转换为json 
			List<MeetingRoom> list = JSONArray.toList(jsonArray,MeetingRoom.class);
			if(list.size()>0){
				for(MeetingRoom l:list){
					MeetingRoom m = new MeetingRoom();
					m.setUserid(uid);
					m.setUsername(uname);
					m.setDayid(l.getDayid());
					m.setMeetingroom(meetingroom);
					m.setMeetingroomday(meetingroomday);
					m.setMeetingtype(meetingType);
					l.setUsername(uname);
					l.setMeetingtype(meetingType);
					DataSourceContextHolder.setDbType("ds_mop");
					int i = meetingRoomService.insert(m);
					if(i>0){
						params.put("code", "200");
						params.put("msg", "成功!");
					}
				}
				params.put("meetingInfo", list);
			}
		}
	    return params;
	}
    
    /**
     * 取消预约会议室
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/cancelAppointment", produces = "application/json;charset=UTF-8")
	public Map cancelAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("code", "500");
		params.put("msg", "失败!");
		
    	HttpSession session = request.getSession();
		String uid = session.getAttribute("userid").toString(); 
		String uname = session.getAttribute("userName").toString(); 
		if(StringUtils.isNotBlank(uid)){
			//第几会议室
			String meetingroom = request.getParameter("meetingroom");
			//会议预定时间
			String meetingroomday = request.getParameter("meetingroomday");
			//会议预定时间段id
			String meetingRoomInfo = request.getParameter("meetingRoomInfo");
			
			JSONArray jsonArray = JSONArray.fromObject(meetingRoomInfo);//把String转换为json 
			List<MeetingRoom> list = JSONArray.toList(jsonArray,MeetingRoom.class);
			if(list.size()>0){
				for(MeetingRoom l:list){
					MeetingRoom m = new MeetingRoom();
					m.setUserid(uid);
					m.setUsername(uname);
					m.setDayid(l.getDayid());
					m.setMeetingroom(meetingroom);
					m.setMeetingroomday(meetingroomday);
					DataSourceContextHolder.setDbType("ds_mop");
					int i = meetingRoomService.delete(m);
					if(i>0){
						params.put("code", "200");
						params.put("msg", "成功!");
					}
				}
				params.put("meetingInfo", list);
			}
		}
	    return params;
	}
   
}
