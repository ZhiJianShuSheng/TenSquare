import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

/**
 * Created by chenglong on 2018-09-21.
 * 启动类
 */
public class BaseApplication {

    public static void main(String[] args){
        SpringApplication.run(BaseApplication.class);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
