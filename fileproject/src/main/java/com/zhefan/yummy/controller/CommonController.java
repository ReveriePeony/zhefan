package com.zhefan.yummy.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.util.FileUtil;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年11月28日
 *
 */
@Slf4j
@Api(tags = "文件接口")
@RestController
@RequestMapping("/file")
public class CommonController extends BaseController {
	
	@Value("${sever-param.host}")
	private String host;
	
	@Value("${sever-param.port}")
	private String port;

	@PostMapping("/uploadimg")
	public ResponseDTO<JSONObject> uploadImg(@RequestParam("file") MultipartFile file, @RequestParam("id") String id,
			HttpServletRequest request) {
		String filePath = getRealPath("upload/" + id + "/temp/", request);
		String newFileName = System.currentTimeMillis() + ".jpg";
		try {
			FileUtil.saveFile(file.getBytes(), filePath, newFileName);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			return ResponseDTO.error("文件保存失败");
		}
		JSONObject json = new JSONObject();
		json.put("host", host + ":" + port + "/");
		json.put("addr", "upload/" + id + "/temp/" + newFileName);
		return ResponseDTO.success("成功", json);
	}
	
	@PostMapping("/saveimg")
	public ResponseDTO<String> saveImg(@RequestParam("startFilePath") String startFilePath,
			@RequestParam("endFilePath") String endFilePath, HttpServletRequest request) {
		String realPath = getRealPath("", request);
		boolean renameToFile = FileUtil.renameToFile(realPath + startFilePath, realPath + endFilePath);
		if(!renameToFile) return ResponseDTO.error("移动文件失败");
		return ResponseDTO.success("成功");
	}
	
	@PostMapping("/delimg")
	public ResponseDTO<String> delImg(@RequestParam("id") String id, HttpServletRequest request) {
		String realPath = getRealPath("upload/" + id + "/temp/", request);
		File targetFile = new File(realPath);
		FileUtil.deleteFile(targetFile);
		return ResponseDTO.success("成功");
	}
	
}
