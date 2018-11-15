package ua.kiev.prog.spring.sample0;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Car car = ctx.getBean("car", Car.class);
        Car car1 = ctx.getBean("car", Car.class);
        System.out.println(car.hashCode());
        System.out.println(car1.hashCode());
    }
}
