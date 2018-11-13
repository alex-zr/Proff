package ua.kyiv.prog.spring.sample1;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-config.xml");
        try {
            Car car = ctx.getBean("car", Car.class);
            System.out.println(car.toString());
        } finally {
            ctx.close();
        }
    }
}
