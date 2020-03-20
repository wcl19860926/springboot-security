package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 编写一个接口用于测试踢出用户：
 */
public class kickoffController {

    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * sessionRegistry.getAllPrincipals(); 获取所有 principal 信息
     * 通过 principal.getUsername 是否等于输入值，获取到指定用户的 principal
     * sessionRegistry.getAllSessions(principal, false)获取该 principal 上的所有 session
     * 通过 sessionInformation.expireNow() 使得 session 过期
     * ————————————————
     * 版权声明：本文为CSDN博主「Jitwxs」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/yuanlaijike/article/details/84638745
     *
     * @param username
     * @return
     */

    @GetMapping("/kick")
    @ResponseBody
    public String removeUserSessionByUsername(@RequestParam String username) {
        int count = 0;

        // 获取session中所有的用户信息
        List<Object> users = sessionRegistry.getAllPrincipals();
        for (Object principal : users) {
            if (principal instanceof User) {
                String principalName = ((User) principal).getUsername();
                if (principalName.equals(username)) {
                    // 参数二：是否包含过期的Session
                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                    if (null != sessionsInfo && sessionsInfo.size() > 0) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            sessionInformation.expireNow();
                            count++;
                        }
                    }
                }
            }
        }
        return "操作成功，清理session共" + count + "个";
    }
}


