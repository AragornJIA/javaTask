// 习题1: 该项目由 Spring Initializr Java Support 创建

package guang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class SpringTask {
    public static void main(String[] args) {
        SpringApplication.run(SpringTask.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    // 习题2: 添加一个响应方法（action），路径为`/hello`，输出为“hello”；
    @GetMapping("/hello")
    public String hello() {
        return String.format("Hello!");
    }

}