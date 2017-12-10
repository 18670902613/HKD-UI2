package com.biz.portal.router;

import com.biz.portal.action.ServicesAction;
import com.jfinal.config.Routes;

public class PortalHome extends Routes {

	@Override
	public void config() {
		// http://localhost/home/artical 访问ArticalController.index方法
		// http://localhost/home/artical/getArtical 访问ArticalController.getArtical方法
		setBaseViewPath("/home");
		add("/home", ServicesAction.class);
	}

}
