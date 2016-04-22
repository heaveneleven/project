package com.nuaa.controller.customer.change;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 换货业务
 * @author heaven
 *
 */
@Controller
@RequestMapping("customer/change_business")
public class ChangeController {
	/**根据树id返回相应视图*/
	@RequestMapping("/show")
	public String toPage(int id){
		if(id==1){/**返回提交换货订单页面*/
			return "customer/change/submit_order";
		}else if(id==2){/**返回我的换货订单页面*/
			return "customer/change/my_order";
		}else{/**返回提交补充材料页面*/
			return "customer/change/submit_supple";
		}
	}
	
}
