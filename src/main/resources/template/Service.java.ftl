package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import java.util.List;
/**
 * ${table.comment!}
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     *  获取列表
     *
     * @param currPage 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    Object listFor${entity}(int currPage, int pageSize);

    /**
     * 新增记录
     *
     * @param ${entity} ${table.name}
     * @return
     */
    Object insertFor${entity}(${entity} ${entity});

    /**
     * 修改记录
     *
     * @param ${entity} ${table.name}
     * @return
     */
    Object updateBy${entity}Id(${entity} ${entity});

<#list table.fields as field>
<#if field.keyFlag>
    /**
     * 获取单个id信息
     *
     * @param ${field.propertyName} ${field.comment}
     * @return
     */
    Object infoById(${field.propertyType} ${field.propertyName});

    /**
     * 批量删除记录
     *
     * @param ${field.propertyName}s ${field.comment}
     * @return
     */
    Object deleteBatchByIs(List<${field.propertyType}> ${field.propertyName}s);
</#if>
</#list>

}
</#if>
