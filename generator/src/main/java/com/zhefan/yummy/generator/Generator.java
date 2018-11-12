package com.zhefan.yummy.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年10月30日
 *
 */
public class Generator {

	static String[] generatorTableName = new String[] { "t_dishes", "t_dishes_class", "t_gerent", "t_shop", "t_user",
			"t_weixin_business", "t_area", "t_menu", "t_order", "t_order_detail", "t_role", "t_role_menu", "t_table", 
			"t_table_type" };

	public static void main(String[] args) {
		
		File file = new File("");
		AutoGenerator mpg = new AutoGenerator();

		GlobalConfig globalConfig = new GlobalConfig();
		globalConfig.setOutputDir(file.getAbsolutePath() + "/src/main/resources/")
		.setFileOverride(true)
		.setActiveRecord(true)
		.setEnableCache(false)
		.setBaseResultMap(true)
		.setBaseColumnList(true)
		.setAuthor("ReverirNight@Foxmail.com")
		.setMapperName("%sDAO")
		.setXmlName("%sMapper")
		.setServiceName("%sService")
		.setServiceImplName("%sServiceImpl");
		mpg.setGlobalConfig(globalConfig);

		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL)
		.setTypeConvert(new MySqlTypeConvert() {
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				return super.processTypeConvert(fieldType);
			}
		})
		.setDriverName("com.mysql.cj.jdbc.Driver")
		.setUsername("root")
		.setPassword("1234")
		.setUrl("jdbc:mysql://localhost:3306/yummy?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
		mpg.setDataSource(dataSourceConfig);

		List<TableFill> tableFillList = new ArrayList<>();
		tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setDbColumnUnderline(true)
		.setTablePrefix(new String[] {"t_"})
		.setNaming(NamingStrategy.underline_to_camel)
		.setInclude(generatorTableName)
		.setSuperEntityColumns(new String[] {})
		.setTableFillList(tableFillList)
		.setSuperServiceClass("com.zhefan.yummy.base.BaseService")
		.setSuperServiceImplClass("com.zhefan.yummy.base.BaseServiceImpl")
		.setSuperControllerClass("com.zhefan.yummy.base.BaseController")
		.setEntityLombokModel(true);
		mpg.setStrategy(strategyConfig);
		
		PackageConfig packageConfig = new PackageConfig();
		packageConfig.setParent("com.zhefan.yummy")
		.setEntity("entity")
		.setMapper("dao")
		.setService("service")
		.setXml("mapper")
		.setController("controller");
		mpg.setPackageInfo(packageConfig);
		
		InjectionConfig injectionConfig = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
			}
		};
		mpg.setCfg(injectionConfig);
		mpg.execute();
	}
}
