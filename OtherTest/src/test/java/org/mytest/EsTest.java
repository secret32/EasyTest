package org.mytest;

import org.junit.Test;
import org.mytest.es.FileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class EsTest extends SpringBootBaseTest {

    @Autowired
    private FileService fileService;

    @Test
    public void testIndex() {
        File file = new File("C:\\sec\\git\\bak\\fdc\\fdc-admin\\src\\test\\java");
        fileService.index(file);
    }

    @Test
    public void testFindAll() {
        fileService.findAll().forEach(System.out::println);
    }

    @Test
    public void testFindByName() {
        fileService.findByName("DictControllerTest.java").forEach(System.out::println);
    }

}
