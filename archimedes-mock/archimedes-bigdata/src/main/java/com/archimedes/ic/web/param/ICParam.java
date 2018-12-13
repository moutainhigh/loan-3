package com.archimedes.ic.web.param;

import javax.validation.constraints.NotEmpty;

public class ICParam {
	@NotEmpty(message = "请求证件号类型不允许为空")
	private String certtype;
	@NotEmpty(message = "请求身份证号不允许为空")
	private String certno;
	public String getCerttype() {
		return certtype;
	}
	public void setCerttype(String certtype) {
		this.certtype = certtype;
	}
	public String getCertno() {
		return certno;
	}
	public void setCertno(String certno) {
		this.certno = certno;
	}

	
}
