package taskCar;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-config.xml");
        try {
            Car car = ctx.getBean("car", Car.class);
            System.out.println(car);

        } finally {
            ctx.close();
        }
    }
}

//Car{model='Reno', year=2010, Driver{name='Petro', age=55}}
