package com.zhefan.yummy.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.THotel;
import com.zhefan.yummy.param.HotelParameter.HotelQuery;
import com.zhefan.yummy.service.THotelService;
import com.zhefan.yummy.util.QrCodeUtil;
import com.zhefan.yummy.vo.HotelVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 酒店管理-新增酒店
 *
 * @author Reverien9@gmail.com
 * 2017年10月25日 下午12:03:49
 */
@Api(tags = "酒店管理-新增酒店")
@RestController
@RequestMapping("/back/hotel")
public class HotelController extends BaseController {

    @Autowired
    private THotelService tHotelService;

    @ApiOperation(value = "酒店列表", notes = "酒店列表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<HotelVo>> hotelR(HotelQuery hpage, HttpSession session) {
//        Integer busid = getLoginUserId(session);
//        Page<HotelVo> page = tHotelService.queryHotelHome(busid, hpage);
        return ResponseDTO.createSuccess();
    }

    @ApiOperation(value = "删除 酒店", notes = "删除 酒店")
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO roomCategoryD(@RequestBody @ApiParam("酒店ID 数组") List<Integer> ids, HttpSession session) {
//        Integer busid = getLoginUserId(session);
        Wrapper<THotel> wrapper = new EntityWrapper<>();
        wrapper.in("id", ids);
        THotel h = new THotel();
        h.setUpdatedAt(new Date());
//        h.setUpdatedBy(busid);
        tHotelService.update(h, wrapper);
        return ResponseDTO.createSuccess();
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
			e.printStackTrace();
		}
    }
    
}
