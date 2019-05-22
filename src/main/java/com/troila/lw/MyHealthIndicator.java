package com.troila.lw;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * 這裡作為一個實現了springboot下的健康度監控的類
 * 其作用是重載並提供了獲取當前服務健康度的功能
 * 但是重點在於，一定要記得加上@Component註解
 * 以便令當前類作為一個配置文件被spring加載
 * 
 * 當前實現了一個接口，然後就可以做一些事情
 * 感覺像裝飾模式（子類覆蓋父類功能）
 * 
 * @author liwei
 *
 */
@Component
public class MyHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		//這裡模擬檢測數據庫連接狀態
		if(ProviderController.DB_STATUS) {
			return new Health.Builder(Status.UP).build();
		}else {
			return new Health.Builder(Status.DOWN).build();
		}
	}
}
