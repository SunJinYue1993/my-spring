package com.study.day01.service;

import org.springframework.stereotype.Component;
/**
 * TODO:注意OneService没有setTwoService, 但是TwoService已经注入了!
 * @author SunJY
 * @date 2020/10/1
 */
@Component("oneService")
public class OneService {

	private TwoService twoService;

	public TwoService getTwoService() {
		return twoService;
	}

}
