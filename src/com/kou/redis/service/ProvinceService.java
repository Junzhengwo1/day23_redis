package com.kou.redis.service;

import com.kou.redis.domain.Province;

import java.util.List;

public interface ProvinceService {
    public List<Province> findAll();

    public String findAllJson();
}
