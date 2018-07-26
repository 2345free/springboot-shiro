package cn.luoxx.shiro.controller;

import cn.luoxx.shiro.utils.JsonUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

@Controller
// @CrossOrigin
@RequestMapping("/api")
public class APIController extends BaseController {

    @RequestMapping("")
    @CrossOrigin
    @ResponseBody
    public JSONObject index() {
        JSONObject result = JsonUtils.getSuccessJson("请求成功");
        result.put("name", "骆晓啸");
        result.put("sex", "男");
        result.put("age", "25");
        result.put("job", "JAVA开发工程师");
        return result;
    }

    @InitBinder
    public void initBinder(DataBinder binder) {
        // 设置自定义验证规则
        binder.setValidator(new Validator() {

            @Override
            public boolean supports(Class<?> clazz) {
                return false;
            }

            @Override
            public void validate(Object target, Errors errors) {

            }
        });

        // 添加数据类型格式化
        binder.addCustomFormatter(new Formatter<Date>() {

            @Override
            public String print(Date object, Locale locale) {
                return null;
            }

            @Override
            public Date parse(String text, Locale locale) throws ParseException {
                return null;
            }
        });
    }

}
