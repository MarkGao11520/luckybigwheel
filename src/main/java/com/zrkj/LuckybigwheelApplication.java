package com.zrkj;

import com.alibaba.fastjson.JSON;
import com.zrkj.mapper.PrizeMapper;
import com.zrkj.mapper.UserMapper;
import com.zrkj.pojo.Prize;
import com.zrkj.pojo.User;
import com.zrkj.service.IPrizeService;
import com.zrkj.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LuckybigwheelApplication {
	private static final Logger logger = Logger.getLogger(LuckybigwheelApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LuckybigwheelApplication.class, args);
	}
}
