package org.lab409.controller;

import org.lab409.entity.Blackboard;
import org.lab409.entity.ResponseMessage;
import org.lab409.service.BlackboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/*
** created by jiao on 2018/10/26
 */
@RestController
public class BlackboardController {

    @Autowired
    BlackboardService blackboardService;

    //向 forum_blackboard 表中添加数据（存黑板报）
    @RequestMapping(path="blackboard/save",method = RequestMethod.POST)
    public ResponseMessage saveBlackboard(@RequestBody Blackboard blackboard){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration enu =
                request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            System.out.println(paraName + ": " +
                    request.getParameter(paraName)); }


            if(blackboardService.saveBlackboard(blackboard)) {
                System.out.println("HELLO WORLD");
                return new ResponseMessage<Blackboard>(null).success();
            }
        return new ResponseMessage<Blackboard>(null).error(202,"can't save");
    }

}
