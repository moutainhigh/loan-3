package com.archimedes.ic.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.archimedes.ic.domain.BlackwhiteList;
import com.archimedes.ic.domain.BlackwhiteListKey;
import com.archimedes.ic.mapper.BlackwhiteListMapper;
import com.archimedes.ic.web.param.ICParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//600-10000 平均3000
@Controller
@RequestMapping(value = "/mock/ic")
@Api("MOCK支付平台")
public class ICMockServerController {

	@Autowired
	private BlackwhiteListMapper blackwhiteListMapper;
	private int max = 10000;
	private int min = 500;
	@ApiOperation(value = "智能授信-授信提交", notes = "授信提交")
	@RequestMapping(value = "/submit",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, String> submit(ICParam param) {
		Random rand = new Random();
		Map<String, String> map = new HashMap<String, String>();
		BlackwhiteListKey blackwhiteListKey = new BlackwhiteListKey();
		blackwhiteListKey.setCertNo(param.getCertno());
		blackwhiteListKey.setCertType(param.getCerttype());
		BlackwhiteList blackwhiteList = blackwhiteListMapper.selectByPrimaryKey(blackwhiteListKey);
		if(!StringUtils.isEmpty(blackwhiteList)) {
			if(!StringUtils.isEmpty(blackwhiteList.getListType())) {
				if(blackwhiteList.getListType().equals("0")) {
					map.put("resultCode", "00000000");
					map.put("resultMessage", "授信成功");
					if(min>=max) {
						min = 500;
					}
					int randNumber = rand.nextInt(max - min + 1) + min;
					min = min + rand.nextInt(1000 - 500 + 1) + 500;
					map.put("amount",String.valueOf(randNumber));
					return map;
				}else {
					map.put("status", "99999999");
					map.put("message", "授信失败,黑名单用户.");
					return map;
				}
			}
			
		}
		
		map.put("status", "00000000");
		map.put("message", "交易成功");
		int randNumber = rand.nextInt(3500 - 3000 + 1) + 3000;
		map.put("amount",String.valueOf(randNumber));
		return map;
	}
	
	@ApiOperation(value = "智能授信-授信查询", notes = "授信查询")
	@RequestMapping(value = "/query",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, String> query(ICParam param) {
		return this.submit(param);
	}
	
}
