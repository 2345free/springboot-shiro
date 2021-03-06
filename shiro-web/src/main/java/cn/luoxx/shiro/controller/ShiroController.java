package cn.luoxx.shiro.controller;

import cn.luoxx.shiro.config.ShiroCasConfig;
import cn.luoxx.shiro.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * @author luoxiaoxiao
 */
@Slf4j
@Controller
public class ShiroController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        // return "login";
        return "redirect:" + ShiroCasConfig.loginUrl;
    }

    /**
     * 登录成功后的回调
     */
    @RequestMapping(value = "/logined", method = RequestMethod.GET)
    public String logined() {
        return "redirect:/user";
    }

    // @RequestMapping(value="/login",method=RequestMethod.POST)
    // public String login(@Valid User user,BindingResult
    // bindingResult,RedirectAttributes redirectAttributes){
    //
    // if(bindingResult.hasErrors()){
    // return "login";
    // }
    //
    // String username = user.getUsername();
    // UsernamePasswordToken token = new
    // UsernamePasswordToken(user.getUsername(), user.getPassword());
    // //获取当前的Subject
    // Subject currentUser = SecurityUtils.getSubject();
    // try {
    // //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
    // //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
    // //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
    // log.info("对用户[" + username + "]进行登录验证..验证开始");
    // currentUser.login(token);
    // log.info("对用户[" + username + "]进行登录验证..验证通过");
    // }catch(UnknownAccountException uae){
    // log.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
    // redirectAttributes.addFlashAttribute("message", "未知账户");
    // }catch(IncorrectCredentialsException ice){
    // log.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
    // redirectAttributes.addFlashAttribute("message", "密码不正确");
    // }catch(LockedAccountException lae){
    // log.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
    // redirectAttributes.addFlashAttribute("message", "账户已锁定");
    // }catch(ExcessiveAttemptsException eae){
    // log.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
    // redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
    // }catch(AuthenticationException ae){
    // //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
    // log.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
    // ae.printStackTrace();
    // redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
    // }
    // //验证是否登录成功
    // if(currentUser.isAuthenticated()){
    // log.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
    // return "redirect:/user";
    // }else{
    // token.clear();
    // return "redirect:/login";
    // }
    // }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        log.info("------没有权限-------");
        return "403";
    }

    @RequestMapping("/user")
    public String getUserList(Map<String, Object> model) {
        model.put("userList", this.userDao.getList());
        return "user";
    }

    @RequestMapping("/user/edit/{userid}")
    public String getUserList(Model model, @PathVariable int userid) {
        log.info("------进入用户信息修改-------");
        model.addAttribute("userList", this.userDao.getList());
        return "user_edit";
    }
}
