package com.biz.sa.router;

import com.biz.sa.action.OrgAction;
import com.jfinal.config.Routes;

public class OrgRouter extends Routes {
	@Override
	public void config() {
		setBaseViewPath("/org");
		add("/org", OrgAction.class);
	}

}
