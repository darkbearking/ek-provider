package com.troila.lw;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.client.http.HttpRequest;

@RestController
public class ProviderController {

	@RequestMapping(value = "/call/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Police call(@PathVariable Integer id ,HttpServletRequest request) {
		Police p = new Police();
		p.setId(id);
		p.setName("dark");
		p.setMessage(request.getRequestURI().toString()+"=="+request.getLocalAddr()+"--"+request.getLocalPort());
		return p;
	}
	
	public static boolean DB_STATUS = true;
	
	/**
	 * 模擬修改數據庫狀態
	 * 其目的是模擬一個以下的場景，那就是：
	 * 當前eureka服務提供者本身的狀態是ok的，但是當前服務提供者連接的數據庫已掛，
	 * 因此我們應該認為當前這個服務提供者也是不能正常提供服務的
	 * 所以就人為地把當前服務提供者的狀態修改為down了。
	 * @param db_status
	 */
	@RequestMapping(value = "/db/{db_status}" ,method = RequestMethod.GET)
	public void setDB(@PathVariable boolean db_status) {
		this.DB_STATUS  = db_status;
	}
}
