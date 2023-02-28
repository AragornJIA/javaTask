// 习题1: 该项目由 Spring Initializr 自动创建
package guang.springtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
@SpringBootApplication
@Validated
public class SpringtaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringtaskApplication.class, args);
    }

    // 系统首页
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    // 任务2: 添加一个响应方法（action），路径为`/hello`，输出为“hello”
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    // 任务3: 添加一个响应方法，路径为`/hello-x`，接收参数`n`，`n`格式为整数，作用是控制输出“hello”的行数，如`/hello-x?n=3`时，响应为3行“hello”
    // 修改`/hello-x`：对参数 n 进行校验，如果 n 不是整数，应给出报错；当 n > 10 时，只输出 10 行 hello；
    @RequestMapping("/hello-x")
    public String hello_x(@RequestParam(defaultValue = "3") String n) {
        try {
            // 校验是否整型, 如果不是就返回提示
            // 如果输入的 n >10, 就输出 10 行
            int times = Math.min(10, Integer.parseInt(n));
            var result = new StringBuilder();
            for (int i = 0; i < times; i++) {
                result.append("hello").append("<br>");
            }
            return result.toString();
        } catch (NumberFormatException e) {
            return "参数 n 必须是整型.";
        }
    }

    @RequestMapping("/insert")
    public String insert(@RequestParam String name) {
        String generated_id = "";
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://10.1.1.77:3306/javatask", "root", "root")) {
            PreparedStatement stmt = con.prepareStatement("insert into test(name) values(?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                generated_id = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
        return generated_id;
    }
}
