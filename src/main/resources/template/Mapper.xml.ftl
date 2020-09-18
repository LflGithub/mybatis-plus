<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">
    <#list table.fields as field1>
        <#assign count=field1_index/>
    </#list>

    <#if baseResultMap>
    <!-- 通用实体类映射 -->
    <resultMap type="${package.Entity}.${entity}" id="BaseResultMap">
            <#list table.fields as field>
                <#if field.keyFlag><#--生成主键排在第一位-->
        <result column="${field.name}" property="${field.propertyName}"/>
                </#if>
            </#list>
            <#list table.fields as field>
                <#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}"/>
                </#if>
            </#list>
    </resultMap>
    </#if>

    <#if baseColumnList>
    <!-- 通用查询字段列 -->
    <sql id="Base_Column_List">
        ${table.fieldNames}
    </sql>
    </#if>

</mapper>
