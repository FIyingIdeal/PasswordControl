package com.flyingideal.spring;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.util.HtmlUtils;

import java.util.Map;

/**
 * @author yanchao
 * @date 2018/1/26 9:51
 * @reference https://www.ibm.com/developerworks/cn/java/j-lo-spring-utils2/
 * @reference http://rensanning.iteye.com/blog/1547845
 */
public class HtmlUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(HtmlUtilsTest.class);

    @Test
    public void test() {
        String specialStr = "<div id=\"testDiv\">test1;test2</div>";
        // 使用Spring提供的工具类进行Html的转义
        String str1 = HtmlUtils.htmlEscape(specialStr);         // 转换为HTML转义字符表示
        System.out.println("htmlEscape : " + str1);
        String str2 = HtmlUtils.htmlEscapeDecimal(specialStr);  // 转换为数据转义表示
        System.out.println("htmlEscapeDecimal : " + str2);
        String str3 = HtmlUtils.htmlEscapeHex(specialStr);      // 转换为十六进制数据转义表示
        System.out.println("htmlEscapeHex : " + str3);

        System.out.println("htmlEscape htmlUnescape : " + HtmlUtils.htmlUnescape(str1));
        System.out.println("htmlEscapeDecimal htmlUnescape : " + HtmlUtils.htmlUnescape(str2));
        System.out.println("htmlEscapeHex htmlUnescape : " + HtmlUtils.htmlUnescape(str3));

        // commons-lang3也提供了类似的工具类，但功能要比spring提供的丰富一些，包括了对XML、CSV、Java等的转义
        String str4 = StringEscapeUtils.escapeHtml4(specialStr);
        System.out.println(str4);
    }


}
