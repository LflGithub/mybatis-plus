package ${package.Controller};

import com.example.mybatisplus.common.annotation.RequestJson;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
 import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
 import ${superControllerClassPackage};
</#if>

import java.util.List;


/**
 * ${table.comment!} 控制类
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired(required = false)
    private ${table.serviceName} ${table.serviceName};

    /**
     *  获取列表
     *
     * @param currPage 当前页数
     * @param pageSize 每页记录数
     * @return
     * @author ${author}
     **/
    @RequestMapping(value = "/listFor${entity}", method = RequestMethod.POST)
    public Object listFor${entity}(@RequestJson(required = true) int currPage,@RequestJson(required = true) int pageSize){
        return ${table.serviceName}.listFor${entity}(currPage, pageSize);
    }

    /**
     * 新增记录
     *
     * @param  ${entity} ${table.comment}
     * @return
     * @author ${author}
     */
    @RequestMapping(value = "/insertFor${entity}", method = RequestMethod.POST)
    public Object insertFor${entity}(@RequestBody ${entity} ${entity}){
        return ${table.serviceName}.insertFor${entity}(${entity});
    }

    /**
     * 修改记录
     *
     * @param  ${entity} ${table.comment}
     * @return
     * @author ${author}
     */
    @RequestMapping(value = "/updateBy${entity}Id", method = RequestMethod.POST)
    public Object updateBy${entity}Id(@RequestBody ${entity} ${entity}){
        return ${table.serviceName}.updateBy${entity}Id(${entity});
    }

      <#list table.fields as field>
        <#if field.keyFlag>
    /**
     * 获取单个id信息
     *
     * @param ${field.propertyName} ${field.comment}
     * @return
     * @author ${author}
     */
    @RequestMapping(value = "infoById", method = RequestMethod.POST)
    public Object infoById(@RequestJson(required = true) ${field.propertyType} ${field.propertyName}){
        return ${table.serviceName}.infoById(${field.propertyName});
    }

    /**
     * 批量删除记录
     *
     * @param ${field.propertyName}s ${field.comment}
     * @return
     * @author ${author}
     */
    @RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
    public Object deleteBatchByIds(@RequestJson(required = true) List<${field.propertyType}> ${field.propertyName}s){
        return ${table.serviceName}.deleteBatchByIs(${field.propertyName}s);
    }
        </#if>
      </#list>

}
</#if>

