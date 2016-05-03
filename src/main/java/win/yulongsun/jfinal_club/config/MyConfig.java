package win.yulongsun.jfinal_club.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.Restful;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import win.yulongsun.jfinal_club.controller.HelloController;
import win.yulongsun.jfinal_club.controller.ManagerController;
import win.yulongsun.jfinal_club.model._MappingKit;

public class MyConfig extends JFinalConfig {
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setViewType(ViewType.JSP);
//        me.setLogFactory(new Log4jLogFactory());
    }

    public void configRoute(Routes me) {
        me.add("/hello", HelloController.class);
        me.add("/api/manager", ManagerController.class);
    }

    public void configPlugin(Plugins me) {
        loadPropertyFile("db_config.txt");
        //数据源
        DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
        me.add(dp);
        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        me.add(arp);
        // 配置方言
        arp.setDialect(new MysqlDialect());
        // 配置Mapper
        _MappingKit.mapping(arp);
        me.add(arp);
        arp.setShowSql(true);
    }

    public void configInterceptor(Interceptors me) {
//        me.add(new Restful());
    }

    public void configHandler(Handlers me) {
    }

    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 80, "/", 5);
    }
}