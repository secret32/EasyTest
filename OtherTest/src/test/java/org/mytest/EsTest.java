package org.mytest;

import org.junit.Test;
import org.mytest.es.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class EsTest extends SpringBootBaseTest {

    @Autowired
    private FileService fileService;

    @Test
    public void testIndex() {
        File file = new File("E:\\sec");
        fileService.index(file);
    }

    @Test
    public void testFindAll() {
        fileService.findAll().forEach(System.out::println);
    }

    @Test
    public void testFindByName() {
//        fileService.findByName("month").forEach(System.out::println);
        fileService.findByName("V求").forEach(System.out::println);
    }

    @Test
    public void testFindByContent() {
        fileService.findByContent("后台").forEach(System.out::println);
    }

    @Test
    public void testDeleteAll() {
        fileService.deleteAllIndex();
    }

}
