package org.example;

@SpringBootApplication
@MapperScan("com.example.demo.mapper") // MyBatis 扫描
public class MainSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
