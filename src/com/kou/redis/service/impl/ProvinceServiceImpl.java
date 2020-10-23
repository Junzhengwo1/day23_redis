package com.kou.redis.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kou.redis.dao.ProvinceDao;
import com.kou.redis.dao.impl.ProvinceDaoImpl;
import com.kou.redis.domain.Province;
import com.kou.redis.service.ProvinceService;
import com.kou.redis.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author JIAJUN KOU
 */
public class ProvinceServiceImpl implements ProvinceService {

   private ProvinceDao dao=new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    /**
     * 使用redis缓存
     *
     * @return
     */
    @Override
    public String findAllJson() {
       //先从redis中查询数据
        //获取客户端连接
        Jedis jedis= JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");
        //判断 province_json 数据是否为NULL
        if(province_json==null||province_json.length()==0){
            //也就是说redis中没有数据
            System.out.println("redis中没有数据，查询数据库....");
            //从数据中查询
            List<Province> ps = dao.findAll();
            //将list集合序列化json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json= mapper.writeValueAsString(ps);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //将json数据存入redis中
            jedis.set("province",province_json);
            jedis.close();
        }else {
            System.out.println("redis中有数据，请查看缓存");
        }


        return province_json;
    }
}
