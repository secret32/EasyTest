package org.mytest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Assert;
import org.junit.Test;
import org.mytest.mapper.UserMapper;
import org.mytest.mapper.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyBatisPlusTest extends SpringBootBaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 99);
        List<User> userList = userMapper.selectList(queryWrapper);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectPage() {
        IPage<User> userPage = new Page<>(1, 50);
        userPage.setPages(1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//    queryWrapper.select("name", "age");
//        queryWrapper.likeLeft("email", "test");
        queryWrapper.orderByAsc("case when age=24 then 1 else 2 end", "age", "name");
        userPage = userMapper.selectPage(userPage, queryWrapper);
        System.out.println("pages: " + userPage.getPages());
        userPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void testSelectQueryMapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "T");
        queryWrapper.lambda().gt(User::getAge, 22);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

}
