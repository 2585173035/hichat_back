package per.yjc.hichat_back.service.impl;

import com.alipay.api.AlipayApiException;
import com.all.models.announcementAndPay.common.*;
import com.all.models.announcementAndPay.entity.TbUser;
import com.all.models.announcementAndPay.mapper.PayMapper;
import com.all.models.announcementAndPay.pojo.Bill;
import com.all.models.announcementAndPay.pojo.User;
import com.all.models.announcementAndPay.service.PayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Author:YJC
 * @Description:
 * @Date:Create in 21:25 2020/6/8
 */
@Service
public class PayServiceImpl implements PayService {


    @Autowired
    PayMapper payMapper;
    @Autowired
    MyContent myContent;
    @Autowired
    AlipayUtiles alipayUtiles;
    /**
     * @param userId
     * @param pageNum
     * @param pageSize
     * @Description: 获取用户的支付记录
     * @Param: [userId]
     * @return: com.github.pagehelper.PageInfo<com.all.models.announcementAndPay.pojo.Bill>
     * @Author: YJC
     * @Date: 2020/6/9
     */
    @Override
    public PageInfo<Bill> getList(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ArrayList<Bill> list = payMapper.getList(userId);
        PageInfo<Bill> info = new PageInfo<Bill>(list);
        return info;
    }

    /**
     * @param pageNum
     * @param pageSize
     * @Description: 获取全部记录
     * @Param: [pageNum, pageSize]
     * @return: com.github.pagehelper.PageInfo<com.all.models.announcementAndPay.pojo.Bill>
     * @Author: YJC
     * @Date: 2020/6/12
     */
    @Override
    public PageInfo<Bill> getBillList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ArrayList<Bill> list = payMapper.getBillList();
        PageInfo<Bill> info = new PageInfo<Bill>(list);
        return info;
    }

    /**
     * @param bill
     * @Description: 支付
     * @Param: [bill]
     * @return: java.lang.String
     * @Author: YJC
     * @Date: 2020/6/9
     */
    @Override
    public String thirdPay(Bill bill,String notify_url, String return_url)throws AlipayApiException {
        //生成订单号
        String orderId = TimeUtils.getOrderIdByTime();
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        bill.setBillId(orderId);
        bill.setCreateTime(timeStamp);
        //缓存订单
        myContent.put(orderId,bill);
        return alipayUtiles.aliPay(notify_url,return_url,orderId,bill.getMoney().toString());
    }

    /**
     * @param bill
     * @Description: 插入支付记录
     * @Param: [bill]
     * @return: java.lang.Boolean
     * @Author: YJC
     * @Date: 2020/6/9
     */
    @Override
    public Boolean insert(Bill bill)throws RRException {
        System.out.println("进来了");
        Integer flag = payMapper.insert(bill);
        Integer flag3 = payMapper.charge(bill.getMoney().intValue()*10, bill.getUserId());
        Integer flag2 = payMapper.addWealth(bill.getUserId(), 2, bill.getMoney().intValue()*10, bill.getCreateTime());
        if (flag!=1){
            throw new RRException("插入记录出错");
        }
        if (flag2!=1){
            throw new RRException("插入记录出错");
        }
        if (flag3!=1){
            throw new RRException("插入记录出错");
        }
        return true;
    }

    /**
     * @param id
     * @Description: 获取用户基本信息
     * @Param: [id]
     * @return: com.all.models.announcementAndPay.pojo.User
     * @Author: YJC
     * @Date: 2020/6/10
     */
    @Override
    public User getUserInfo(Integer id) {
        TbUser userById = payMapper.getUserById(id);
        User user = new User();
        BeanUtils.copyProperties(userById,user);
        return user;
    }
}
