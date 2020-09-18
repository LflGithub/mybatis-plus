package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ${table.comment!} 实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
<#if kotlin>
    open class ${table.serviceImplName} :${table.serviceName} {

    }
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public Object listFor${entity}(int currPage, int pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        //不分页
        if (currPage == 0 && pageSize == 0) {
            return this.list(queryWrapper);
        }
        //分页
        else {
            return this.page(new Page<>(currPage, pageSize), queryWrapper);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object insertFor${entity}(${entity} ${entity}) {
        if (this.save(${entity})) {
            return "新增成功";
        } else {
            return "新增失败";
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object updateBy${entity}Id(${entity} ${entity}) {
        if (this.updateById(${entity})) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

<#list table.fields as field>
<#if field.keyFlag>
    @Override
    public Object infoById(${field.propertyType} ${field.propertyName}) {
        if (${field.propertyName} == null) {
            return "id不能为空";
        } else {
            return this.getById(${field.propertyName});
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object deleteBatchByIs(List<${field.propertyType}> ${field.propertyName}s) {
        if (this.removeByIds(${field.propertyName}s)) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
    </#if>
    </#list>
}
</#if>
