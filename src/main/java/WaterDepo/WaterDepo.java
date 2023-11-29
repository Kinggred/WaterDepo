package WaterDepo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WaterDepo {

  public static void main(String[] args) {
    SpringApplication.run(WaterDepo.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx){
      return args -> {
          System.out.println("Bean inspection!?:");
          String[] beanNames = ctx.getBeanDefinitionNames();
          Arrays.sort(beanNames);
          for (String beanName : beanNames) {
              System.out.println(beanName);
          }
      };
  }
}
