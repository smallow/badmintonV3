package com.stylefeng.guns.modular.club.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.page.PageReq;
import com.stylefeng.guns.modular.club.dao.CourtDao;
import com.stylefeng.guns.modular.club.dao.PriceDao;
import com.stylefeng.guns.modular.club.dao.RecordDao;
import com.stylefeng.guns.modular.club.model.BookCourtRecord;
import com.stylefeng.guns.modular.club.model.Record;
import com.stylefeng.guns.modular.club.service.IRecordService;
import com.stylefeng.guns.modular.club.warpper.RecordWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private IRecordService recordService;

    public static final String CODE_SUCCEED = "1"; //代码 1-成功
    public static final String CODE_FAIL = "2"; //代码 2-失败
    public static final String MAPKEY_RETURN_CODE = "code";//返回结果的mapKey CODE_SUCCEED CODE_FAIL
    public static final String MAPKEY_RETURN_MSG = "msg";//返回结果的mapKey
    public static final Integer AVAILABLE_COURT = 1;//场地-可用
    public static final Integer BOOKMODE_ONLINE = 0;//预定方式-网上
    public static final Integer STATE_BOOKED = 0;//预定记录状态-已预定（默认状态）

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
    public Object list(String groupName,String startTime,String endTime,String state) {
        PageReq params = defaultPage();
        PageHelper.offsetPage(params.getOffset(), params.getLimit());
        Map<String,Object> param=new HashMap<>();
        param.put("groupName",groupName);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        param.put("state",state);
        List<Map<String, Object>> records = recordDao.list(param);
        return super.warpObject(new RecordWarpper(records));
    }

    /**
     * 预定信息详情
     * @param bookDate - 预定日期 - 2018-09-21
     * @return
     */
    @RequestMapping(value = "/api/bookInfo",produces="text/html;charset=UTF-8")
    @ResponseBody
    public Object getBookInfo(String bookDate){
        Map<String,Object> param=new HashMap<>();
        param.put("bookDate",bookDate);
        Map<String,Object> reMap = new HashMap();
        String code;
        try {
            reMap.put("bookCourtInfo",recordDao.bookList(param));//已预定的场地信息
            reMap.put("timePriceInfo",priceDao.list());//所有时间的价格信息
            reMap.put("courtInfo",courtDao.list(new HashMap<String,Object>(){{put("available",AVAILABLE_COURT);}}));//所有可用的场地信息
            code = CODE_SUCCEED;
        } catch (Exception e) {
            e.printStackTrace();
            code = CODE_FAIL;
            reMap.put(MAPKEY_RETURN_MSG,"程序错误");
        }
        reMap.put(MAPKEY_RETURN_CODE,code);
        return reMap;
    }

    /**
     * 预定场地
     * @param bookInfo
     * @return
     */
    @RequestMapping(value = "/api/saveBookInfo",produces="text/html;charset=UTF-8")
    @ResponseBody
    public Object saveBookInfo(String bookInfo){
//        String bookInfo = "{\"bookDate\":\"2018-09-26\",\"cost\":\"240\",\"bookList\":[{\"courtId\":3,\"timePriceId\":\"1\"},{\"courtId\":3,\"timePriceId\":\"2\"},{\"courtId\":12,\"timePriceId\":\"3\"},{\"courtId\":12,\"timePriceId\":\"5\"}]}";
        Map<String,Object> reMap = new HashMap();
        String code;
        try {
            JSONObject jsonObj = JSON.parseObject(bookInfo);
            String bookDate = jsonObj.getString("bookDate");
            String cost = jsonObj.getString("cost");
            JSONArray arr = jsonObj.getJSONArray("bookList");
            Map<String,Object> bookedMap = new HashMap();
            for (Object booked:arr) {
                JSONObject bookedJson = JSONObject.parseObject(booked.toString());
                String courtId = bookedJson.getString("courtId");
                String timePriceId = bookedJson.getString("timePriceId");
                //查询是否已被预定
                Map<String,Object> param=new HashMap<>();
                param.put("bookDate",bookDate);
                param.put("courtId",courtId);
                param.put("timeId",timePriceId);
                List<Map<String, Object>> list = recordDao.bookList(param);
                if (list!=null && list.size()>0){
                    reMap.put(MAPKEY_RETURN_CODE,CODE_FAIL);
                    reMap.put(MAPKEY_RETURN_MSG,"请勿重复提交");
                    return reMap;
                }
                bookedMap.put(courtId,bookedMap.get(courtId)==null ? timePriceId : bookedMap.get(courtId)+","+timePriceId);
            }
            BookCourtRecord bcr = new BookCourtRecord();
            bcr.setGroupId(11);
            bcr.setBookPersonName("测试24");
            bcr.setBookPersonPhone("15812341124");
            bcr.setBookDate((new SimpleDateFormat("yyyy-MM-dd")).parse(bookDate));
            bcr.setBookMode(BOOKMODE_ONLINE);
            bcr.setCost(new BigDecimal(cost));
            bcr.setState(STATE_BOOKED);
            recordService.saveBookInfo(bcr,bookedMap);
            code = CODE_SUCCEED;
        }catch (Exception e){
            e.printStackTrace();
            code = CODE_FAIL;
            reMap.put(MAPKEY_RETURN_MSG,"程序错误");
        }
        reMap.put(MAPKEY_RETURN_CODE,code);
        return reMap;
    }

    /**
     * 取消预定
     *
     * @return
     */
    @RequestMapping(value = "/api/cancelBookInfo")
    @ResponseBody
    public Object cancelBook(){

        return null;
    }

    /**
     * 新增消费记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Record record, BindingResult result) {
        System.out.println("===================="+record.getCost()+","+record.getPayMode()+","+record.getCostMode()+","+record.getGroupId());
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
