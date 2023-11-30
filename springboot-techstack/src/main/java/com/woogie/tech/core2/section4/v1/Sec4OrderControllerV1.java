package com.woogie.tech.core2.section4.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping //스프링은 @Controller 또는 @RequestMapping이 있어야 스프링 컨트롤러로 인식
@ResponseBody
public interface Sec4OrderControllerV1 {

	@GetMapping("/v6/request")
	String request(@RequestParam("itemId") String itemId);

	@GetMapping("/v6/no-log")
	String noLog();
}