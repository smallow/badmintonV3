package com.stylefeng.guns.modular.club.controller;

import com.github.pagehelper.PageHelper;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.page.PageReq;
import com.stylefeng.guns.modular.club.dao.PayrecordDao;
import com.stylefeng.guns.modular.club.model.PayRecord;
import com.stylefeng.guns.modular.club.vo.PayRecordVO;
import com.stylefeng.guns.modular.club.warpper.GroupWarpper;
import com.stylefeng.guns.modular.club.warpper.PayRecordWarpper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 充值记录控制器
 *
 * @author fengshuonan
 * @Date 2018-09-18 17:28:21
 */
@Controller
@RequestMapping("/payrecord")
public class PayrecordController extends BaseController {

    @Autowired
    private PayrecordDao payrecordDao;

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
    public Object list(Integer clubId) {
        PageReq params = defaultPage();
        PageHelper.offsetPage(params.getOffset(), params.getLimit());
        if (clubId != null) {
            Map<String, Object> param = new HashMap<>();
            param.put("clubId", clubId);
            Example example = new Example(PayRecord.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("clubId", clubId);
            List<PayRecord> list = payrecordDao.selectByExample(example);
            // return list.stream().map(e -> convert(e)).collect(Collectors.toList());

            return super.warpObject(new PayRecordWarpper(list.stream().map(e -> convert2(e)).collect(Collectors.toList())));
        }
        return null;

    }

    private PayRecordVO convert(PayRecord record) {
        PayRecordVO payRecordVO = new PayRecordVO();
        BeanUtils.copyProperties(record, payRecordVO);
        return payRecordVO;
    }

    private Map<String, Object> convert2(PayRecord record) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",record.getId());
        map.put("clubId",record.getClubId());
        map.put("payPersonName",record.getPayPersonName());
        map.put("payPersonPhone",record.getPayPersonPhone());
        return map;
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
