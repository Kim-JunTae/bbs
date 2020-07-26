package mybatis.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import mybatis.vo.BbsVO;

public class BbsDAO {
	@Autowired
	SqlSessionTemplate template;
	
	//게시글 목록 호출
	public BbsVO[] getList(int begin, int end) {
		BbsVO[] ar = null;
		
		//Mapper(bbs.xml)에 넣을 Parameter값
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		
		//Mapper(bbs.xml) 호출
		List<BbsVO> list = template.selectList("bbs.list", map);
		if(list != null && list.size() > 0) {
			ar = new BbsVO[list.size()];
			list.toArray(ar);
		}
		
		return ar;
	}//end getList()
	
	//게시글 등록
	public boolean insertBoard(BbsVO vo) {
		boolean value = false;
		
		int cnt = template.insert("bbs.insertBoard", vo);
		
		if(cnt > 0){
			value = true;
		}
		
		return value;
	}
	
	
}
