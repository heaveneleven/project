package com.nuaa.controller.customer.transfer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 中转业务
 * @author heaven
 *
 */
@Controller
@RequestMapping("customer/transfer_business")
public class TransferController {
	/**根据树id返回相应视图*/
	@RequestMapping("/show")
	public String toPage(int id){
		if(id==1){/**返回提交中转订单页面*/
			return "customer/transfer/submit_order";
		}else{/**返回我的中转订单页面*/
			return "customer/transfer/my_order";
		}
	}
	
}
