package data.vo;

import org.springframework.web.multipart.MultipartFile;

public class ParamVO {
	private MultipartFile f;

	public MultipartFile getF() {
		return f;
	}

	public void setF(MultipartFile f) {
		this.f = f;
	}	
}
