/**
 *
 */
package com.sitv.skyshop.tbataobao.check.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sitv.skyshop.tbataobao.utils.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2018年3月26日
 */
@Slf4j
@Controller
@RequestMapping("/kaptcha")
public class KaptchaController {

	@Autowired
	private DefaultKaptcha defaultKaptcha;

	@RequestMapping("/create")
	public void create(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 生产验证码字符串并保存到session中
			String createText = defaultKaptcha.createText();
			request.getSession().setAttribute(Constants.SESSION_VERIFY_CODE_KEY, createText);

			// 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
			BufferedImage challenge = defaultKaptcha.createImage(createText);

			ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
			ImageIO.write(challenge, "jpg", jpegOutputStream);

			// 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");

			ServletOutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(jpegOutputStream.toByteArray());
			responseOutputStream.flush();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException ex) {
				log.error(ex.getMessage(), ex);
			}
			return;
		}
	}
}
