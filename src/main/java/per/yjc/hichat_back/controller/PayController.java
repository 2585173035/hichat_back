package per.yjc.hichat_back.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.all.models.announcementAndPay.common.CommonUtils;
import com.all.models.announcementAndPay.common.MyContent;
import com.all.models.announcementAndPay.common.Result;
import com.all.models.announcementAndPay.pojo.Bill;
import com.all.models.announcementAndPay.pojo.User;
import com.all.models.announcementAndPay.service.impl.PayServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author:YJC
 * @Description:
 * @Date:Create in 21:24 2020/6/8
 */
@RequestMapping("/api/pay")
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class PayController {

    @Autowired
    PayServiceImpl payService;
    @Autowired
    MyContent myContent;
    @Value("${Alipay.notify_url.htmlBack}")
    String htmlBack;
    @Value("${Alipay.notify_url.payBack}")
    String payBack;
    @Value("${Alipay.app_id}")
    String APP_ID;
    @Value("${Alipay.SELLER_ID}")
    String SELLER_ID;
    @Value("${Alipay.alipay_public_key}")
    private String alipay_public_key;

    /**
     * @Description:   获取用户支付记录
     * @Param:  [id, pageNum, pageSize]
     * @return:  com.all.models.announcementAndPay.common.Result
     * @Author: YJC
     * @Date:  2020/6/9
     */
    @RequestMapping(value = "/getBills",method = RequestMethod.GET)
    public Result getBills(@RequestParam(value = "id",required = true)Integer id,
                           @RequestParam(value = "pageNum",required = true) int pageNum,
                           @RequestParam(value = "pageSize",required = true) int pageSize){
        PageInfo<Bill> list = payService.getList(id,pageNum,pageSize);
        return Result.ok().put("list",list);
    }

    /**
     * @Description:   获取全部记录
     * @Param:  [pageNum, pageSize]
     * @return:  com.all.models.announcementAndPay.common.Result
     * @Author: YJC
     * @Date:  2020/6/12
     */
    @RequestMapping(value = "/getAllBills",method = RequestMethod.GET)
    public Result getAllBills(@RequestParam(value = "pageNum",required = true) int pageNum,
                           @RequestParam(value = "pageSize",required = true) int pageSize){
        PageInfo<Bill> list = payService.getBillList(pageNum,pageSize);
        return Result.ok().put("list",list);
    }

    /**
     * @Description:   获取用的财富值
     * @Param:  [id]
     * @return:  com.all.models.announcementAndPay.common.Result
     * @Author: YJC
     * @Date:  2020/6/10
     */
    @RequestMapping("/getWealth")
    Result getWealth(@RequestParam(value = "id",required = true)Integer id){
        User user = payService.getUserInfo(id);
        return Result.ok().put("user",user);
    }
    /**
     * @Description:   支付
     * @Param:  [bill]
     * @return:  java.lang.String
     * @Author: YJC
     * @Date:  2020/6/9
     */
    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public String pay(Bill bill)throws AlipayApiException{
        return payService.thirdPay(bill,payBack,htmlBack);
    }
    /**
     * @Description:   回调
     * @Param:  [request]
     * @return:  void
     * @Author: YJC
     * @Date:  2020/6/9
     */
    @RequestMapping("/callBack")
    public void callBack(HttpServletRequest request) throws AlipayApiException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipay_public_key, "utf-8", "RSA2"); //调用SDK验证签名
        boolean out_trade_no_flag = false;
        boolean total_amount_flag = false;
        boolean seller_id_flag = false;
        boolean app_id_flag = false;
        boolean allTrue = false;
        if(signVerified) {//验证成功

            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no"));
            //实际收到金额
            String receipt_amount = new String(request.getParameter("receipt_amount"));
            //交易状态
            String trade_status = new String(request.getParameter("trade_status"));
            //卖家支付宝用户号
            String seller_id = new String(request.getParameter("seller_id"));
            //支付宝分配给开发者的应用 ID
            String app_id = new String(request.getParameter("app_id"));
            //买家付款时间
            String gmt_payment = new String(request.getParameter("gmt_payment"));
            //核对Redis中的数据
            Bill bill=(Bill)myContent.get(out_trade_no);
            DecimalFormat df1 = new DecimalFormat("0.00");

            String fee = df1.format(bill.getMoney());
            if (fee!=null){
                out_trade_no_flag = true;
                System.out.println(fee+"-----"+receipt_amount);
                if (receipt_amount.equals(fee)){
                    total_amount_flag = true;
                }
            }
            if (seller_id.equals(SELLER_ID)){
                seller_id_flag = true;
            }
            if (app_id.equals(APP_ID)){
                app_id_flag = true;
            }
            ArrayList<Boolean> booleans = new ArrayList<>();
            booleans.add(out_trade_no_flag);
            booleans.add(total_amount_flag);
            booleans.add(seller_id_flag);
            booleans.add(app_id_flag);
            System.out.println(booleans);
            allTrue = CommonUtils.isAllTrue(booleans);
            if (trade_status.equals("TRADE_SUCCESS")&&allTrue){//支付成功后的操作写这里面
                myContent.remove(out_trade_no);
                System.out.println("进入");
                payService.insert(bill);
            }
            System.out.println("success");
        }else {//验证失败
            System.out.println("fail");
        }

    }


}
