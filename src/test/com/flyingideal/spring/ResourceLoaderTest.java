package com.flyingideal.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.*;
import java.util.Arrays;

/**
 * @author yanchao
 * @date 2018/1/24 9:57
 * @reference http://blog.51cto.com/zhaohaibo/1285445
 * @reference http://blog.51cto.com/zhaohaibo/1285449
 * @function Spring使用Ant模式路径通配符加载Resource
 * 注意：Ant路径通配符支持 “?”，"*"，“**”，不包括目录分隔符“/”
 * "?" : 匹配一个字符；
 * "*" : 匹配零或多个字符串；
 * "**" : 匹配路径中的零或多个目录（注意：当**出现在文件名中时是一种特殊情况，如“cn/javass/config-**.xml”将匹配“cn/javass/config-dao.xml”，即把“**”当做两个“*”处理）
 *
 * Spring在加载类路径资源时除了提供前缀“classpath:”来支持加载一个Resource，还提供一个“classpath*：”来加载所有匹配的类路径Resource。
 */
public class ResourceLoaderTest {

    /**
     * classpath：用于加载类路径（包括jar包）中的一个且仅一个Resource，即使匹配多个也只返回一个。
     * 如果需要返回所有匹配的Resource，请考虑使用"classpath*:"前缀
     * @throws IOException
     */
    @Test
    public void testClasspathPrefix() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 加载一个绝对匹配的Resource
        Resource[] resources = resolver.getResources("classpath:ehcache/ehcache.xml");
        Assert.assertEquals(1, resources.length);

        // 加载一个通配符匹配的Resource
        resources = resolver.getResources("classpath:ehcache/*.xml");
        Assert.assertEquals(1, resources.length);

        // spring的jar包中基本上都包含有license.txt，即classpath中包含有多个该名称的文件，但使用classpath:的时候只返回一个
        // 如果想要返回所有匹配的Resource，使用classpath*:
        resources = resolver.getResources("classpath:META-INF/license.txt");
        Assert.assertTrue(resources.length == 1);
    }

    /**
     * classpath* ：用于加载类路径（包括jar包）中的所有匹配的资源。
     * @throws IOException
     */
    @Test
    public void testClasspathAsteriskPrefix() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:META-INF/license.txt");
        Assert.assertTrue(resources.length > 1);
        System.out.println("license.txt length : " + resources.length);

        resources = resolver.getResources("classpath*:META-INF/*.txt");
        Assert.assertTrue(resources.length > 1);
        System.out.println("*.txt length : " + resources.length);
    }


    @Test
    public void testClasspathAsteriskPrefixLimit() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:application-*.yml");
        Arrays.stream(resources).forEach(System.out::println);
        //Assert.assertEquals(1, resources.length);
    }

    @Test
    public void getResource() {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Resource resource = context.getResource("classpath:spring.xml");
        try {
            File file = resource.getFile();
            System.out.println(file.getAbsolutePath());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ClassLoaderTest() {
        // 可以读取到
        InputStream is = ClassLoader.getSystemResourceAsStream("spring.xml");
        if (is == null) {
            System.out.println("File not found!");
            return ;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        reader.lines().forEach(System.out::println);
    }

    @Test
    public void resourceLoaderTestes() throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        // "classpath:path"表示返回ClasspathResource
        Resource classPathResource = loader.getResource("classpath:spring.xml");
        Assert.assertEquals(classPathResource.getClass(), ClassPathResource.class);

        // "http://path"和"file:path"表示返回UrlResource资源
        Resource urlResource = loader.getResource("file:spring.xml");
        System.out.println(urlResource.getFile().getAbsolutePath());
        Assert.assertEquals(urlResource.getClass(), UrlResource.class);

        // 如果不加前缀则需要根据当前上下文来决定，DefaultResourceLoader默认实现可以加载classpath资源
        Resource defaultResource = loader.getResource("spring.xml");
        System.out.println(defaultResource.getFile().getAbsolutePath());
        System.out.println(defaultResource.getClass()); // class org.springframework.core.io.DefaultResourceLoader$ClassPathContextResource
        Assert.assertTrue(defaultResource instanceof ClassPathResource);
    }


}
