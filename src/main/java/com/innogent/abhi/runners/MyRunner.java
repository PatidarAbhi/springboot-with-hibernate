package com.innogent.abhi.runners;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {
	
	public MyRunner() {
		// TODO Auto-generated constructor stub
		System.out.println("MyRunner.MyRunner()1");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("runner"+args);
	}

}
