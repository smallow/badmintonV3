package com.stylefeng.guns.modular.club.controller;

import com.stylefeng.guns.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 充值记录控制器
 *
 * @author fengshuonan
 * @Date 2018-09-18 17:28:21
 */
@Controller
@RequestMapping("/payrecord")
public class PayrecordController extends BaseController {

    private String PREFIX = "/club/payrecord/";

    /**
     * 跳转到充值记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "payrecord.html";
    }

    /**
     * 跳转到添加充值记录
     */
    @RequestMapping("/payrecord_add")
    public String payrecordAdd() {
        return PREFIX + "payrecord_add.html";
    }

    /**
     * 跳转到修改充值记录
     */
    @RequestMapping("/payrecord_update/{payrecordId}")
    public String payrecordUpdate(@PathVariable Integer payrecordId, Model model) {
        return PREFIX + "payrecord_edit.html";
    }

    /**
     * 获取充值记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return null;
    }

    /**
     * 新增充值记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }

    /**
     * 删除充值记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改充值记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 充值记录详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
