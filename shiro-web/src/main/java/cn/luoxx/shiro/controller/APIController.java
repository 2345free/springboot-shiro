package cn.luoxx.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import cn.luoxx.shiro.utils.JsonUtils;

@Controller
//@CrossOrigin
@RequestMapping("/api")
public class APIController extends BaseController{
	
	@RequestMapping("")
	@CrossOrigin
	@ResponseBody
	public JSONObject index(){
		JSONObject result=JsonUtils.getSuccessJson("请求成功");
		result.put("name", "骆晓啸");
		result.put("sex", "男");
		result.put("age", "25");
		result.put("job", "JAVA开发工程师");
		return result;
	}

}
