package com.example.mybatisplus.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 代码生成器
 *
 * @author by LFL
 * @date 2019/12/30.
 */
public class CodeGenerator {


    public static void main(String[] args) {
        executeCreate();
    }

    /**
     * 代码生成
     */
    private static void executeCreate() {
        //用来获取Mybatis-Plus.properties文件的配置信息
        final ResourceBundle rb = ResourceBundle.getBundle("mybatis-plus");

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(rb.getString("OutputDir"));
        gc.setAuthor(rb.getString("author"));
        //xml文件，ResultMap
        gc.setBaseResultMap(true);
        //xml文件，开启SQL语句,columList
        gc.setBaseColumnList(true);
        // AUTO(0, "数据库ID自增"), INPUT(1, "用户输入ID")
        gc.setIdType(IdType.INPUT);
        //是否打开输出目录
        gc.setOpen(false);
        gc.setDateType(DateType.ONLY_DATE);
        //实体属性 Swagger2 注解
        //gc.setSwagger2(true);

        //自定义生成的类的命名方式
        gc.setMapperName("%sMapper");
        //gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        //设置时间
        //dsc.setDbType(DbType.MYSQL);
        dsc.setUrl(rb.getString("url"));
        dsc.setDriverName(rb.getString("driver"));
        dsc.setUsername(rb.getString("userName"));
        dsc.setPassword(rb.getString("password"));
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(scanner("模块名"));
        packageConfig.setParent(rb.getString("parent"));
        packageConfig.setMapper("mapper");
        mpg.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        //自定义配置 此处模板引擎是 freemarker
        String templatePath = "/template/Mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return rb.getString("OutputDirXml") + packageConfig.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        //自定义输出文件
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig tc = new TemplateConfig();
        //配置自定义输出模板,为空或者null时，该文件不生成
        //指定自定义模板路径，注意不要带上.ftl/.vm后缀, 会根据使用的模板引擎自动识别
        tc.setController("template/Controller.java");
        tc.setMapper("template/Mapper.java");
        tc.setService("template/Service.java");
        tc.setServiceImpl("template/ServiceImpl.java");
        tc.setEntity("template/Entity.java");
        //tc.setXml("template/Mapper.xml");
        //前面已经配置生成
        tc.setXml(null);

        // 模板 相关配置
        mpg.setTemplate(tc);
        // 策略配置,即数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略，未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        //【实体】是否为lombok模型
        strategy.setEntityLombokModel(true);
        //生成 @RestController控制器
        strategy.setRestControllerStyle(true);

        // 自定义 mapper 父类
        strategy.setSuperMapperClass(rb.getString("SuperMapper"));
        // 自定义 service 父类
        strategy.setSuperServiceClass(rb.getString("SuperService"));
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass(rb.getString("SuperServiceImpl"));
        // 自定义 controller 父类
        // strategy.setSuperControllerClass(rb.getString("SuperController"));

        //需要生成的表
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageConfig.getModuleName() + "_");
        //数据库表配置
        mpg.setStrategy(strategy);
        //模板引擎 默认 Veloctiy
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //生成代码
        mpg.execute();
    }

    /**
     * 读取控制台内容
     *
     * @param tip 输入
     * @return
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
