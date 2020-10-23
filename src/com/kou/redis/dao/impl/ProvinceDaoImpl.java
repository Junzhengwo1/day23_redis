package com.kou.redis.dao.impl;

import com.kou.redis.dao.ProvinceDao;
import com.kou.redis.domain.Province;
import com.kou.redis.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author JIAJUN KOU
 */
public class ProvinceDaoImpl implements ProvinceDao {

    /**
     *
     */
    JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        //1.定义sql

        String sql="select * from province";
        //2.执行sql
        List<Province> list = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));

        //3.



        return list;
    }
}
