package com.troila.lw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo.InstanceStatus;

/**
 * 健康檢查處理器
 * 其主要作用，是修改eureka服務器端關於當前服務提供者的狀態
 * 
 * 與MyHealthIndicator類似，貌似也是使用的裝飾模式。
 * @author liwei
 *
 */
@Component
public class HealthCheckHandler implements com.netflix.appinfo.HealthCheckHandler {

	@Autowired
	private MyHealthIndicator healthIndicator;
	
	/**
	 * 獲取當前服務提供者的狀態
	 * 注意返回的是InstanceStatus類型的UP或DOWN。
	 * 方法會自動修改eureka服務端針對當前服務提供者的可見狀態。
	 * 也就是讓別的服務看到的這個服務的狀態。
	 */
	@Override
	public InstanceStatus getStatus(InstanceStatus arg0) {
		
		Status status = healthIndicator.health().getStatus();
		if(status.equals(Status.UP)) {
			return InstanceStatus.UP;
		}else
			return InstanceStatus.DOWN;
	}
}
