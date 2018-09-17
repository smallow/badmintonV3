package com.stylefeng.guns.modular.club.controller;

import com.github.pagehelper.PageHelper;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.page.PageReq;
import com.stylefeng.guns.modular.club.dao.GroupDao;
import com.stylefeng.guns.modular.club.model.Group;
import com.stylefeng.guns.modular.club.warpper.GroupWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 俱乐部管理控制器
 *
 * @author fengshuonan
 * @Date 2018-09-16 21:45:55
 */
@Controller
@RequestMapping("/group")
public class GroupController extends BaseController {

    private String PREFIX = "/club/group/";
    @Autowired
    private GroupDao groupDao;

    /**
     * 跳转到俱乐部管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "group.html";
    }

    /**
     * 跳转到添加俱乐部管理
     */
    @RequestMapping("/group_add")
    public String groupAdd() {
        return PREFIX + "group_add.html";
    }

    /**
     * 跳转到修改俱乐部管理
     */
    @RequestMapping("/group_update/{groupId}")
    public String groupUpdate(@PathVariable Integer groupId, Model model) {
        return PREFIX + "group_edit.html";
    }

    /**
     * 获取俱乐部管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer state,
                       String masterPhone,
                       String groupName,
                       String masterName) {
//        Example example = new Example(Group.class);
////        Example.Criteria criteria = example.createCriteria();
////        if (!StringUtils.isEmpty(name))
////            criteria.andLike("name", "%" + name + "%");
////        if (!StringUtils.isEmpty(masterPhone))
////            criteria.andEqualTo("masterPhone", masterPhone);
////        if (!StringUtils.isEmpty(masterName))
////            criteria.andLike("masterName", "%" + masterName + "%");
////        if (state != null)
////            criteria.andEqualTo("state", state);

        PageReq params = defaultPage();
        PageHelper.offsetPage(params.getOffset(), params.getLimit());
        Map<String,Object> param=new HashMap<>();
        param.put("name",groupName);
        param.put("masterPhone",masterPhone);
        param.put("masterName",masterName);
        param.put("state",state);
        List<Map<String, Object>> groups = groupDao.list(param);
        return super.warpObject(new GroupWarpper(groups));
    }

    /**
     * 新增俱乐部管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Group group, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断是否重复
//        Group theUser = groupDao.get(user.getAccount());
//        if (theUser != null) {
//            throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
//        }
        group.setCreateDate(new Date());
        group.setState(0);
        groupDao.insert(group);
        return SUCCESS_TIP;
    }

    /**
     * 删除俱乐部管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改俱乐部管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 俱乐部管理详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
