package org.mytest.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mytest.mp.entity.User;
import org.mytest.mp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectPage() {
        IPage<User> userPage = new Page<>(1, 5);
        userPage.setPages(1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//    queryWrapper.select("name", "age");
        queryWrapper.likeLeft("email", "test");
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
