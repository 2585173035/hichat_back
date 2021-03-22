package per.yjc.hichat_back.mapper;

import com.all.models.announcementAndPay.entity.TbUser;
import com.all.models.announcementAndPay.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Author:YJC
 * @Description:
 * @Date:Create in 22:34 2020/6/9
 */
public interface PayMapper {
    /**
     * @Description:   插入交易记录
     * @Param:  [bill]
     * @return:  java.lang.Integer
     * @Author: YJC
     * @Date:  2020/6/10
     */
    Integer insert(@Param("bill") Bill bill);
    /**
     * @Description:   根据用户id获取交易记录
     * @Param:  [userId]
     * @return:  java.util.ArrayList<com.all.models.announcementAndPay.pojo.Bill>
     * @Author: YJC
     * @Date:  2020/6/10
     */
    ArrayList<Bill> getList(@Param("userId") Integer userId);

    /**
     * @Description:   获取全部交易记录记录
     * @Param:  [userId]
     * @return:  java.util.ArrayList<com.all.models.announcementAndPay.pojo.Bill>
     * @Author: YJC
     * @Date:  2020/6/12
     */
    ArrayList<Bill> getBillList();
    /**
     * @Description:   获取用户基本信息
     * @Param:  [id]
     * @return:  com.all.entity.TbUser
     * @Author: YJC
     * @Date:  2020/6/10
     */
    TbUser getUserById(@Param("id") Integer id);
    /**
     * @Description:   支付成功后对用户进行财富值充值
     * @Param:  [value, id]
     * @return:  java.lang.Integer
     * @Author: YJC
     * @Date:  2020/6/10
     */
    Integer charge(@Param("value") Integer value, @Param("id") Integer id);

    /**
     * @Description:   财富值记录表插入
     * @Param:  [user_id, matter_id, chang_value, operating_time]
     * @return:  java.lang.Integer
     * @Author: YJC
     * @Date:  2020/6/15
     */
    public Integer addWealth(@Param("user_id") Integer user_id,
                             @Param("matter_id") Integer matter_id,
                             @Param("chang_value") Integer chang_value,
                             @Param("operating_time") Date operating_time);

}
