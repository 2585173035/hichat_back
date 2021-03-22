package per.yjc.hichat_back.service;

import com.alipay.api.AlipayApiException;
import com.all.models.announcementAndPay.common.RRException;
import com.all.models.announcementAndPay.pojo.Bill;
import com.all.models.announcementAndPay.pojo.User;
import com.github.pagehelper.PageInfo;

/**
 * @Author:YJC
 * @Description:
 * @Date:Create in 21:24 2020/6/8
 */
public interface PayService {

    /**
     * @Description:   获取用户的支付记录
     * @Param:  [userId]
     * @return:  com.github.pagehelper.PageInfo<com.all.models.announcementAndPay.pojo.Bill>
     * @Author: YJC
     * @Date:  2020/6/9
     */
    PageInfo<Bill> getList(Integer userId, Integer pageNum, Integer pageSize);

    /**
     * @Description:   获取全部记录
     * @Param:  [pageNum, pageSize]
     * @return:  com.github.pagehelper.PageInfo<com.all.models.announcementAndPay.pojo.Bill>
     * @Author: YJC
     * @Date:  2020/6/12
     */
    PageInfo<Bill> getBillList(Integer pageNum, Integer pageSize);
    /**
     * @Description:   支付
     * @Param:  [bill]
     * @return:  java.lang.String
     * @Author: YJC
     * @Date:  2020/6/9
     */
    String thirdPay(Bill bill, String notify_url, String return_url)throws AlipayApiException;
    /**
     * @Description:   插入支付记录
     * @Param:  [bill]
     * @return:  java.lang.Boolean
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Boolean insert(Bill bill)throws RRException;

    /**
     * @Description:   获取用户基本信息
     * @Param:  [id]
     * @return:  com.all.models.announcementAndPay.pojo.User
     * @Author: YJC
     * @Date:  2020/6/10
     */
    User getUserInfo(Integer id);
}
