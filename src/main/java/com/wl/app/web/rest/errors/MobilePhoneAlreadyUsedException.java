package com.wl.app.web.rest.errors;

public class MobilePhoneAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public MobilePhoneAlreadyUsedException() {
        super(ErrorConstants.MOBILE_PHONE_ALREADY_USED_TYPE, "Mobile Phone is already in use!", "userManagement", "phoneexists");
    }
}
