package com.nuaa.controller.customer.overseas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 海外业务
 * @author heaven
 *
 */
@Controller
@RequestMapping("customer/overseas_business")
public class OverseasController {
	/**根据树id返回相应视图*/
	@RequestMapping("/show")
	public String toPage(int id){
		if(id==1){/**返回提交备货发货订单页面*/
			return "customer/overseas/submit_order";
		}else if(id==2){/**提交本地配货订单页面*/
			return "customer/overseas/submit_local_order";
		}else if(id==3){/**返回我的库存页面*/
			return "customer/overseas/repertory";
		}else if(id==4){/**返回我的本地配送车*/
			return "customer/overseas/local_delivery";
		}else{/**返回我的本地配货订单*/
			return "customer/overseas/local_order";
		}
	}
	
}
