package com.spring.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	private final static String HOME_PAGE = "homepage";
	private final static String REQ_HOME_PAGE = "/" + HOME_PAGE;
	private final static String REDIRECT_HOME_PAGE = "redirect:" +	REQ_HOME_PAGE;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewHomeDefault(ModelMap model) {

		return REDIRECT_HOME_PAGE;
	}

	@RequestMapping(value = REQ_HOME_PAGE, method = RequestMethod.GET)
	public String viewHome(ModelMap model) {
		model.addAttribute("message", "");
		model.addAttribute("hostname", getHostname());
		logger.info("At home ");

		return HOME_PAGE;
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public RedirectView handleException(HttpServletRequest request, HttpServletResponse response, Exception sre) {
		logger.error("Error message ", sre);
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(REQ_HOME_PAGE);
		FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
		if (outputFlashMap != null) {
			outputFlashMap.put("css", "danger");
			outputFlashMap.put("msg", "Something went wrong,please check logs for more info");

		} else {
			logger.error("flash map is null");
		}

		return redirectView;
	}

	private String getHostname() {

		InetAddress ip;
		String hostname = "";
		try {
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();
			logger.info("Your current IP address : " + ip);
			logger.info("Your current Hostname : " + hostname);
			hostname = String.format("Current hostname ,%s [%s]", hostname, ip);

		} catch (UnknownHostException e) {

			e.printStackTrace();
		}

		return hostname;
	}

}
