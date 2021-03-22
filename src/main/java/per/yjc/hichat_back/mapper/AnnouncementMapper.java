package per.yjc.hichat_back.mapper;

import com.all.models.announcementAndPay.pojo.Announcement;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * @Author:YJC
 * @Description:
 * @Date:Create in 21:21 2020/6/8
 */
public interface AnnouncementMapper {
    /**
     * @Description:   插入一条公告信息
     * @Param:  [ann]
     * @return:  com.all.models.announcementAndPay.pojo.Announcement
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Integer insertOne(@Param("ann") Announcement ann);
    /**
     * @Description:   插入一条关系
     * @Param:  [invitation_id, user_id]
     * @return:  java.lang.Integer
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Integer insertRelation(@Param("invitation_id") Integer invitation_id,
                           @Param("user_id") Integer user_id);
    /**
     * @Description:   修改一条公告
     * @Param:  [ann]
     * @return:  java.lang.Integer
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Integer update(@Param("ann") Announcement ann);
    /**
     * @Description:   删除一条公告
     * @Param:  [id]
     * @return:  java.lang.Integer
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Integer delete(@Param("id") Integer id);
    /**
     * @Description:   获取公告详情
     * @Param:  [id]
     * @return:  com.all.models.announcementAndPay.pojo.Announcement
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Announcement getDetail(@Param("id") Integer id);
    /**
     * @Description:   获取公告List
     * @Param:  []
     * @return:  java.util.ArrayList<com.all.models.announcementAndPay.pojo.Announcement>
     * @Author: YJC
     * @Date:  2020/6/9
     */
    ArrayList<Announcement> getList();
    /**
     * @Description:   根据公告标题获取公告List
     * @Param:  [title]
     * @return:  java.util.ArrayList<com.all.models.announcementAndPay.pojo.Announcement>
     * @Author: YJC
     * @Date:  2020/6/9
     */
    ArrayList<Announcement> getListByTitle(@Param("title") String title);

    /**
     * @Description:   点赞数+1
     * @Param:  [id]
     * @return:  java.lang.Integer
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Integer praise(@Param("id") Integer id);

    /**
     * @Description:   阅读次数+1
     * @Param:  [id]
     * @return:  java.lang.Integer
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Integer access(@Param("id") Integer id);
    
    /**
     * @Description:   获取invitation_user表的中对应记录的条数
     * @Param:  [id, userId]
     * @return:  java.lang.Integer
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Integer isPraise(@Param("id") Integer id,
                     @Param("userId") Integer userId);
    /**
     * @Description:   插入invitation_user表对应记录
     * @Param:  [id, userId]
     * @return:  java.lang.Integer
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Integer praiseRelation(@Param("id") Integer id,
                           @Param("userId") Integer userId);

}
