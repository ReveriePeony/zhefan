package com.zhefan.yummy.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.util.FileUtil;
import com.zhefan.yummy.util.QrCodeUtil;
import com.zhefan.yummy.vo.SocketMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年10月31日
 *
 *
 */
@Slf4j
@Api(tags = "公共")
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

	@PostMapping("/uploadimg")
	public ResponseDTO<String> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Integer id = getGerent(request).getId();
		String filePath = getRealPath("upload/" + id + "/temp/", request);
		String newFileName = System.currentTimeMillis() + ".jpg";
		try {
			FileUtil.saveFile(file.getBytes(), filePath, newFileName);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			return ResponseDTO.error("文件保存失败");
		}
		return ResponseDTO.success("成功", "upload/" + id + "/temp/" + newFileName);
	}

	@ApiOperation(value = "二维码", notes = "二维码")
	@GetMapping(value = "/qrcode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void qrcode(String url, HttpServletResponse response) {
		OutputStream outputStream;
		try {
			outputStream = new BufferedOutputStream(response.getOutputStream());
			BufferedImage bi = QrCodeUtil.encode(url, null, "H", null, outputStream, 500, 500, 1);
			response.setHeader("Content-Disposition",
					"attachment;filename=\"" + URLEncoder.encode("二维码.jpg", "UTF-8") + "\"");
			response.setContentType("application/octet-stream");
			outputStream = new BufferedOutputStream(response.getOutputStream());
			ImageIO.write(bi, "jpg", outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e.getCause());
		}
	}
	
	@ApiOperation(value = "socket.io说明(非请求路径)", 
			notes = "端口: 8888, 参数: clientid-(发送者ID), token-(字符串yummy加上时间按“yyyyMMdd”格式化后MD5加密)")
	@GetMapping(value = "/socket")
	public void socket(@ApiParam("发送消息对象") @RequestBody SocketMessage message) {
	}

}
