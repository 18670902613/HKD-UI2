package com.biz.sa.router;

import com.biz.sa.action.OrgAction;
import com.biz.sa.action.PersonAction;
import com.jfinal.config.Routes;

public class SaRouter extends Routes {
	@Override
	public void config() {
		setBaseViewPath("/");
		add("/org", OrgAction.class);
		add("/person", PersonAction.class);
	}

}
