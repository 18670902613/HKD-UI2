package com.biz.router.portal;

import com.jfinal.config.Routes;

public class PortalHome extends Routes {

	@Override
	public void config() {
		// http://localhost/home/artical 访问ArticalController.index方法
		// http://localhost/home/artical/getArtical 访问ArticalController.getArtical方法
		setBaseViewPath("/home/article");
//		add("/home/article", ArticleController.class);
	}

}
