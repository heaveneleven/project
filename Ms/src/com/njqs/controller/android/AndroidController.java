package com.njqs.controller.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njqs.controller.base.BaseController;
import com.njqs.service.android.inter.AndroidServiceInter;
import com.njqs.utils.FilePath;

/**
 * 为手机端另外添加的特定Controller
 * */
@Controller
@RequestMapping("/android")
public class AndroidController extends BaseController {
	@Resource(name = "androidService")
	private AndroidServiceInter androidService;

	@RequestMapping("/message/query/chose.do")
	public void getOrigiMsg(HttpServletResponse response) {
		this.writeJson(response, androidService.getOrigMsg());
	}

	@RequestMapping("/file/download.do")
	public void getAPKFile(HttpServletResponse response) {
		// 获取项目路径
		String projectName = FilePath.PROJECT_NAME;
		String javaPath = this.getClass()
				.getResource(this.getClass().getSimpleName() + ".class")
				.toString();
		javaPath = javaPath.substring(6, javaPath.length());
		int index = javaPath.indexOf(projectName);
		javaPath = javaPath.substring(0, index);
		String filepath = javaPath + projectName
				+ "android/WaterAndRainConditions.apk";
		System.out.println("path:" + filepath);
		File file = new File(filepath);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		byte[] b = new byte[1024];
		int len = 0;
		try {
			inputStream = new FileInputStream(file);
			outputStream = response.getOutputStream();
			response.setContentType("application/force-download");
			String filename = file.getName();
			filename = "WaterAndRainConditions.apk";
			response.addHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			response.setContentLength((int) file.length());
			while ((len = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
