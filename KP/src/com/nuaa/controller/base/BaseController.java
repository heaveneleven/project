package com.nuaa.controller.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.nuaa.utils.JsonParse;


public class BaseController {
	protected void writeJson(HttpServletResponse response, Object obj) {
		this.writeJson(response, obj,"utf-8");
	}

	protected void writeJson(HttpServletResponse response, Object obj,
			String charset) {
		String json = JsonParse.getJson(obj);
		response.setContentType("text/html;charset=" + charset);
		response.setDateHeader("Expires", -10);
		PrintWriter out = null;
		try{
			out=response.getWriter();
			out.println(json);
			out.flush();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(out != null)
				out.close();
		}
	}
}
