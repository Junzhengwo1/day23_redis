package com.kou.redis.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kou.redis.domain.Province;
import com.kou.redis.service.ProvinceService;
import com.kou.redis.service.impl.ProvinceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author dell
 */
@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        //1.调用Service查询
//        ProvinceService service=new ProvinceServiceImpl();
//        List<Province> list = service.findAll();
//        //2.序列化
//        ObjectMapper mapper=new ObjectMapper();
//        String json = mapper.writeValueAsString(list);

        ProvinceService service=new ProvinceServiceImpl();
        String json = service.findAllJson();
        System.out.println(json);
        //3.响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
