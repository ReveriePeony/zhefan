package com.zhefan.yummy.controller;


import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.service.GerentService;
import com.zhefan.yummy.util.QrCodeUtil;
import com.zhefan.yummy.util.SessionUtil;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 商家(后台)账号 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-10-30
 */
@Slf4j
@RestController
@RequestMapping("/gerent")
public class GerentController extends BaseController {
	
	@Autowired
	private GerentService gerentService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping("login")
	public ResponseDTO login(@RequestParam String name, @RequestParam String password, HttpServletRequest request) {
		Wrapper<Gerent> wrapper = new EntityWrapper<>();
		wrapper.eq("name", name).eq("password", DigestUtils.md5Hex(password).toUpperCase());
		Gerent gerent = gerentService.selectOne(wrapper);
		if(gerent != null) {
			log.debug(gerent.toString());
			SessionUtil.setLoginInfo(request, gerent);
			return ResponseDTO.createSuccess("success");
		}
		return ResponseDTO.createError(ResponseEnums.LOGIN_ERROR);
	}
	
	@ApiOperation(value = "二维码", notes = "二维码")
    @GetMapping(value = "/qrcode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void qrcode(String url, HttpServletResponse response) {
    	OutputStream outputStream;
		try {
			outputStream = new BufferedOutputStream(response.getOutputStream());
			BufferedImage bi = QrCodeUtil.encode(url, null, "H", null, outputStream, 500, 500, 1);
			response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode("二维码.jpg", "UTF-8") + "\"");  
			response.setContentType("application/octet-stream");
			outputStream = new BufferedOutputStream(response.getOutputStream());
			ImageIO.write(bi, "jpg", outputStream);
			outputStream.flush();  
			outputStream.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e.getCause());
		}
    }
}
