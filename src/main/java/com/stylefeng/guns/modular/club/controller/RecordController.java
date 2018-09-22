package com.stylefeng.guns.modular.club.controller;

import com.github.pagehelper.PageHelper;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.page.PageReq;
import com.stylefeng.guns.modular.club.dao.CourtDao;
import com.stylefeng.guns.modular.club.dao.PriceDao;
import com.stylefeng.guns.modular.club.dao.RecordDao;
import com.stylefeng.guns.modular.club.warpper.GroupWarpper;
import com.stylefeng.guns.modular.club.warpper.RecordWarpper;
import org.apache.commons.collections.iterators.ObjectArrayIterator;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.deploy.perf.DeployPerfUtil.put;
import static org.apache.coyote.http11.Constants.a;

/**
 * 消费记录控制器
 *
 * @author fengshuonan
 * @Date 2018-09-18 09:40:18
 */
@Controller
@RequestMapping("/record")
public class RecordController extends BaseController {

    private String PREFIX = "/club/record/";
    @Autowired
    private RecordDao recordDao;
    @Autowired
    private PriceDao priceDao;
    @Autowired
    private CourtDao courtDao;

    /**
     * 跳转到消费记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "record.html";
    }

    /**
     * 跳转到添加消费记录
     */
    @RequestMapping("/record_add")
    public String recordAdd() {
        return PREFIX + "record_add.html";
    }

    /**
     * 跳转到修改消费记录
     */
    @RequestMapping("/record_update/{recordId}")
    public String recordUpdate(@PathVariable Integer recordId, Model model) {
        return PREFIX + "record_edit.html";
    }

    /**
     * 获取消费记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String groupName,String startTime,String endTime,String status) {
        PageReq params = defaultPage();
        PageHelper.offsetPage(params.getOffset(), params.getLimit());
        Map<String,Object> param=new HashMap<>();
        param.put("groupName",groupName);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
//        param.put("status",status);
        List<Map<String, Object>> records = recordDao.list(param);
        return super.warpObject(new RecordWarpper(records));
    }

    /**
     * 预定信息详情 ---返回的json浏览器中有乱码
     * @param  - 预定日期 - 2018-09-21
     * @return
     */
    @RequestMapping(value = "/bookInfo")
    @ResponseBody
    public Object getBookInfo(){
        Map<String,Object> param=new HashMap<>();
        param.put("bookDate","2018-09-21");
        Map<String,Object> reMap = new HashMap<String,Object>();
        reMap.put("bookCourtInfo",recordDao.bookList(param));//已预定的场地信息
        reMap.put("timePriceInfo",priceDao.list());//所有时间的价格信息
        reMap.put("courtInfo",courtDao.list(new HashMap<String,Object>(){{put("available",1);}}));//所有场地信息
        return reMap;
    }

    /**
     * 新增消费记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }

    /**
     * 删除消费记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改消费记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 消费记录详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
