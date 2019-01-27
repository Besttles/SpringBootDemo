package com.xiaour.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.xiaour.spring.boot.startUpService.StartUpService;

/**
 * 
 * @ClassName Application
 * @author biwh
 * @Date 2017年4月27日 下午5:30:34
 * @version V2.0.0
 */

@SpringBootApplication(exclude = MybatisAutoConfiguration.class)
@ServletComponentScan
@EnableAutoConfiguration
@MapperScan("com.xiaour.spring.boot.mapper")
public class Application  extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

	@Value("${server.port}")
	private int port;//应用的端口
	/**
	 * 启动入口
	 * @param args
	 */
    public static void main(String ... args){
        //预先加载组件
    	try {
    		boolean isStart = StartUpService.chechService();
    		if(isStart) {
    			System.out.println("已经加载组件");
    		}else {
    			System.out.println("未加载组件");
    		}
		} catch (Exception e) {
			e.getMessage();
		}
    	
        SpringApplication.run(Application.class, args);
    }

    /**
     * 自定义端口
     */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(port);
	}

}
