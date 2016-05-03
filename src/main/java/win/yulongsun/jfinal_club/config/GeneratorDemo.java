package win.yulongsun.jfinal_club.config;

import javax.sql.DataSource;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * GeneratorDemo
 */
public class GeneratorDemo {
	
//	public static DataSource getDataSource() {
////		Prop p = PropKit.use("config.txt");
//		DruidPlugin druidPlugin = new DruidPlugin("jdbc:mysql://120.27.117.76:3306/jfinal_club","root", "root");
//		druidPlugin.start();
//		return druidPlugin.getDataSource();
//	}
	public static DataSource getDataSource() {
//		Prop p = PropKit.use("config.txt");
		C3p0Plugin c3p0Plugin = new C3p0Plugin("jdbc:mysql://120.27.117.76:3306/jfinal_club","root", "root");
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}

	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "win.yulongsun.jfinal_club.model.base";
		// base model 文件保存路径
		String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/win/yulongsun/jfinal_club/model/base";
		
		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "win.yulongsun.jfinal_club.model";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = PathKit.getWebRootPath() + "/src/main/java/win/yulongsun/jfinal_club/model/";
		
		// 创建生成器
		Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		// 设置数据库方言
		gernerator.setDialect(new MysqlDialect());
		// 添加不需要生成的表名
//		gernerator.addExcludedTable("adv");
		// 设置是否在 Model 中生成 dao 对象
		gernerator.setGenerateDaoInModel(true);
		// 设置是否生成字典文件
		gernerator.setGenerateDataDictionary(false);
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
//		gernerator.setRemovedTableNamePrefixes("t_");
		// 生成
		gernerator.generate();
	}
}




