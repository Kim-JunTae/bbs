package com.ict.board;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ict.Util.FileUploadUtil;
import data.vo.ParamVO;
import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;

@Controller
public class WriteController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	private BbsDAO bbsDao;
	
	//"/upload"
	private String f_path;
	
	public void setF_path(String f_path) {
		this.f_path = f_path;
	}

	@RequestMapping(value="/write.inc", method = {RequestMethod.GET})
	public String write() {
		return "write";
	}//end write()
	
	@RequestMapping(value="/write.inc", method = {RequestMethod.POST})
	public String write(BbsVO vo) {
		//ModelAndView mv = new ModelAndView();
		MultipartFile mf = vo.getFile();
		
		if(mf.getSize() > 0 && mf.getOriginalFilename().trim().length() > 0){
			String r_path = application.getRealPath("/upload");
			String path = application.getRealPath(f_path);
			String fileName = mf.getOriginalFilename();
			fileName = FileUploadUtil.checkSameFileName(fileName, path);
			try {
				mf.transferTo(new File(path, fileName));
			}catch(Exception e) {
				e.printStackTrace();
			}
			vo.setFilename(fileName);
		}//end if
		
		//cnt 얻기
		vo.setCnt("0");
		
		//ip 얻기
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		//status 얻기
		vo.setStatus("0");
		
		//DB에 저장
		boolean chk = bbsDao.insertBoard(vo);
		
		//mv.addObject("chk", chk);
		//mv.setViewName("list.inc");
		//다시 list 목록을 가져오는 Controller로 가야됨
		//return mv;
		
		return "list";
	}//end write()
	
	//ResponseBody란?
	@RequestMapping(value="/saveImage.inc", method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, String> saveImg(ParamVO pvo){
		Map<String, String> map = new Hashtable<String, String>();
		
		//파일을 서버에 저장
		MultipartFile mf = pvo.getF(); 
		
		//전역변수 설정
		String filename = null;
		
		//파일이 존재한다면
		if(mf.getSize() > 0 && mf.getOriginalFilename().trim().length() > 0) {
			//파일을 저정할 위치를 절대경로화시킨다.
			String r_path = application.getRealPath("/editor_img");
			
			filename = mf.getOriginalFilename();
			filename = FileUploadUtil.checkSameFileName(filename, r_path);
			
			try {
				mf.transferTo(new File(r_path, filename));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}//end if
		
		//현재 서버 경로
		String path = request.getContextPath();
		
		map.put("path", path+"/editor_img");
		map.put("filename", filename);
		
		//비동기식 결과 반환
		return map;
	}//end saveImg()
}
