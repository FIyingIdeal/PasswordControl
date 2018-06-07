package com.flyingideal.spring.spel;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author yanchao
 * @date 2018/5/24 19:54
 */
public class SpelTest {

    private static final Logger logger = LoggerFactory.getLogger(SpelTest.class);

    @Test
    public void SpelExpressionParser() {
        // ExpressionParser负责解析表达式字符串
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'Hello World'");
        logger.info(expression.getValue(String.class));

        // 方法调用
        expression = parser.parseExpression("'Hello world'.concat('!')");
        logger.info(expression.getValue(String.class));

        // 调用bean的getter方法，只需要指定对应属性的名字即可
        expression = parser.parseExpression("'Hello world'.bytes");
        logger.info("{}", expression.getValue(byte[].class));

        expression = parser.parseExpression("'Hello world'.bytes.length");
        logger.info("{}", expression.getValue(int.class));

        class Person {
            private String name;
            private int age;
            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }
        }

        Person person = new Person("testName", 18);
        expression = parser.parseExpression("name");
        EvaluationContext context = new StandardEvaluationContext(person);
        logger.info("{}", expression.getValue(person, String.class));
        logger.info("{}", expression.getValue(context, String.class));
    }
}
