package com.study.day03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AClass {

	@Autowired
	private BClass bClass;
}
