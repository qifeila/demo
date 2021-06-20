package goct.query.demo.controller;

import goct.query.demo.mapper.oracleGoct.SelfHelpMapper;
import goct.query.demo.model.SelfHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelpController {
    @Autowired
    SelfHelpMapper selfHelpMapper;

    //业务咨询帮助页
    @RequestMapping(value = "/selfHelp")
    public ModelAndView selfHelp(ModelMap modelMap) {
        return new ModelAndView("help/selfHelp", modelMap);
    }

    //帮助信息模糊查询
    @ResponseBody
    @RequestMapping(value = "/getHelpByKey")
    public List<SelfHelp> getHelpByKey(@RequestParam("keyWord") String keyWord) {
        List list = new ArrayList();
        if (!StringUtils.isEmpty(keyWord)) {
             list = selfHelpMapper.getHelpByKey(keyWord);
        }
        return list;
    }
}
