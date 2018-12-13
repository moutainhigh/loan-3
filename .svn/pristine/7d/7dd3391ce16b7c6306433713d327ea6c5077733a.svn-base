package com.archimedes.payment.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.archimedes.payment.web.param.BankParam;
import com.archimedes.payment.web.param.PaymentParam;
import com.archimedes.payment.web.param.VerifyCodeParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/mock/payment")
@Api("MOCK支付平台")
public class PaymentMockServerController {
	
	@ApiOperation(value = "银行卡鉴权-银行卡认证交易", notes = "银行卡认证交易")
	@RequestMapping(value = "/sign",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, String> signApply(BankParam bankParam) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("resultCode", "99999999");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        
        if(StringUtils.isEmpty(bankParam.getOutTradeNo())) {
        	map.put("resultMessage", "请求交易流水号不允许为空");
        	return map;
        }
        if(StringUtils.isEmpty(bankParam.getCardNo())) {
        	map.put("resultMessage", "银行卡信息不允许为空");
    		return map;
        }
        
        if(StringUtils.isEmpty(bankParam.getBankCode())) {
        	map.put("resultMessage", "银行编码不允许为空");
    		return map;
        }
        
        if(StringUtils.isEmpty(bankParam.getHolderName())) {
        	map.put("resultMessage", "真实姓名不允许为空");
    		return map;
        }
        
        
        if(StringUtils.isEmpty(bankParam.getHolderMobile())) {
        	map.put("resultMessage", "银行卡预留手机号不允许为空");
    		return map;
        }
        
        
        String url = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?cardNo="+bankParam.getCardNo()+"&cardBinCheck=true";
        ResponseEntity<Map> result = restTemplate.getForEntity(url, Map.class);
		
        Map<String,Object> resultMap = result.getBody();
       
        if(resultMap.get("validated").toString().equals("true")) {
        	map.put("resultCode", "00000000");
    		map.put("resultMessage", "交易成功");
        }
      
		map.put("resultMessage", "交易失败");
		return map;
	}
	
	@ApiOperation(value = "银行卡鉴权-银行卡认证确认交易", notes = "银行卡认证确认交易")
	@RequestMapping(value = "/confirm",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, String> signConfirm(BankParam bankParam) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("resultCode", "00000000");
		map.put("resultMessage", "交易成功");
		return map;
	}
	
	@ApiOperation(value = "银行卡鉴权-银行卡短信验证", notes = "银行卡短信验证")
	@RequestMapping(value = "/verify",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, String> bankVerify(VerifyCodeParam verifyCodeParam) {
		Map<String, String> map = new HashMap<String, String>();
		if(!StringUtils.isEmpty(verifyCodeParam)&&verifyCodeParam.getVerifyCode().equals("123456")) {
			map.put("resultCode", "00000000");
			map.put("resultMessage", "交易成功");
			return map;
		}
		map.put("resultCode", "99999999");
		map.put("resultMessage", "交易失败,短信验证失败");
		return map;
	}
	
	
	@ApiOperation(value = "交易-代付", notes = "代付")
	@RequestMapping(value = "/defray",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, String> defray(PaymentParam paymentParam) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("resultCode", "00000000");
		map.put("resultMessage", "交易成功");
		return map;
	}
	
	@ApiOperation(value = "交易-快捷支付", notes = "快捷支付")
	@RequestMapping(value = "/quick",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, String> quick(PaymentParam paymentParam) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("resultCode", "00000000");
		map.put("resultMessage", "交易成功");
		return map;
	}
	
}
