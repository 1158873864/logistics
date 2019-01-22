package com.wl.app.web.rest.vm;

import javax.validation.constraints.Size;

public class RUserVM {
	
    @Size(min = 11, max = 15)
    private String mobilePhone;

    
    @Size(min = 6, max = 6)
    private String vcode;
    
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

}
