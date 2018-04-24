package com.spring.controllers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.DatabaseMetaData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	private final static String HOME_PAGE = "homepage";
	private final static String REQ_HOME_PAGE = "/" + HOME_PAGE;
	private final static String REDIRECT_HOME_PAGE = "redirect:" + REQ_HOME_PAGE;

	@Autowired
	DataSource datasource;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewHomeDefault(ModelMap model) {

		return REDIRECT_HOME_PAGE;
	}

	@RequestMapping(value = REQ_HOME_PAGE, method = RequestMethod.GET)
	public String viewHome(ModelMap model) {

		DatabaseMetaData metadata;
		String url = "";
		String exception = "";
		String db_host = "";

		try {
			db_host = System.getenv("db_host");
			logger.info("db_host from env :: " + db_host);

			isHostReachable(db_host);

			metadata = datasource.getConnection().getMetaData();
			url = metadata.getURL();

		} catch (Exception e) {

			logger.error("" + e);
			exception = e.getMessage();
		}

		model.addAttribute("db_url", url);
		model.addAttribute("db_exception", exception);
		model.addAttribute("message", "");
		model.addAttribute("hostname", getHostname());

		logger.info("At home ");

		return HOME_PAGE;
	}

	@RequestMapping(value = "/ping", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody String pingHostname(
			@RequestParam(value = "hostname", required = true) String hostname)
			throws UnknownHostException, IOException {

		return isHostReachable(hostname) ? "Success" : "Not Reachable";
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
			hostname = ip.toString();
			logger.info(hostname);

		} catch (UnknownHostException e) {

			e.printStackTrace();
		}

		return hostname;
	}

	private boolean isHostReachable(String ipAddress) {
		boolean reachable = false;
		if (ipAddress != null && !ipAddress.isEmpty()) {
			logger.info("Sending Ping Request to " + ipAddress);
			InetAddress geek;
			try {
				geek = InetAddress.getByName(ipAddress);
				if (geek.isReachable(5000)) {
					logger.info("Host :" + ipAddress + " is reachable");
					reachable = true;
				}
			} catch (UnknownHostException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}			

		}

		return reachable;
	}
	
	

}
