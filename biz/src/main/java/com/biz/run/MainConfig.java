package com.biz.run;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 *  demo 仅表达最为粗浅的 jfinal 用法，更为有价的实用的企业级用法 详见 JFinal 俱乐:
 * http://jfinal.com/club
 * 
 * API引导式配
 */
public class MainConfig extends JFinalConfig {

	/**
	 * 运行 main 方法可以启动项目
	 */
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/");
	}

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		me.setDevMode(true);
	}

	public void configRoute(Routes me) {
		me.add("/", IndexController.class); // 第三个参数为该Controller的视图存放路// 第三个参数省略时默认与第个参数相同，在此即为 "/blog"
	}

	public void configEngine(Engine me) {
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		PropKit.use("jFinalConfig.txt");
		String databaseURL = PropKit.get("jdbcUrl");
		String username = PropKit.get("user");
		String password = PropKit.get("password");
		String databaseType = PropKit.get("databaseType");
		DruidPlugin dp = new DruidPlugin(databaseURL, username, password);
		dp.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		WallConfig config = new WallConfig();
		config.setFunctionCheck(false); // 支持数据库函
		wall.setConfig(config);
		wall.setDbType(databaseType);
		dp.addFilter(wall);
		me.add(dp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		me.add(arp);
		arp.setShowSql(true);
	}

	/**
	 * 配置全局拦截
	 */
	public void configInterceptor(Interceptors me) {

	}

	/**
	 * 配置处理
	 */
	public void configHandler(Handlers me) {

	}
}
