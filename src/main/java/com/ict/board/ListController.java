package com.ict.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;

@Controller
public class ListController {
	
	@Autowired
	private BbsDAO bbsDao;
	
	//페이징 기법에 필요한 변수들
	int nowPage;
	
	//list 호출 시 목록 가져오기 method=RequestMethod.POST
	@RequestMapping(value="/list.inc", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(String nowPage) throws Exception{
		
		//페이지값 없을 때 초기화
		if(nowPage == null) {
			this.nowPage = 1;
		}
		int begin = 1;
		int end = 10;
		
		BbsVO[] ar = bbsDao.getList(begin, end);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", ar);
		mv.setViewName("list");
		
		return mv;
	}//end list()

}
