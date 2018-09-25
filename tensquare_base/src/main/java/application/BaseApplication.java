package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

/**
 * Created by chenglong on 2018-09-21.
 * 启动类
 */
@SpringBootApplication
public class BaseApplication {

    /**
    * @Description: main
    * @Param: [args]
    * @return: void
    */
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    /**
    * @Description: idWorker
    * @Param: []
    * @return: utils.IdWorker
    */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
