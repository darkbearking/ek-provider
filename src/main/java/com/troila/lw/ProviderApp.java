package com.troila.lw;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 當前類中，我們添加了actuator包，目的是保證自己真正的健康
 * 如果僅僅當前服務本身可用，但是服務連接數據庫或者服務調用外圍接口不暢通的時候，是不能算真正健康的。
 * @author liwei
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderApp {

	/**
	 * 需要說明的是，發送心跳的是接入eureka的工程，而非eureka服務器本身。
	 * 當前工程接入ek-server，因此我們需要在當前工程的配置文件中
	 * 定義	eureka.instance.lease-renewal-interval-in-seconds: 5  
	 * 5代表5秒
	 * @param args
	 */
	public static void main(String[] args) {
//		若啟動方式不是通過手動輸入啟動端口的時候，需要把這裡注釋掉。同時放開application.yml配置文件的server.port對因部分
//		new SpringApplicationBuilder(ProviderApp.class).web(true).run(args);
	
//		下面對應的啟動方式是手動輸入啟動端口號，同時需要將application.yml配置文件中對應的server.port屬性注釋掉
		Scanner scan = new Scanner(System.in);
		String port = scan.nextLine();
		//下面這裡可以通過自己指定properties，作用相當於在yml配置文件中添加相同的內容
		new SpringApplicationBuilder(ProviderApp.class).properties("server.port=" + port).run(args);
	}

}
