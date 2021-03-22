package per.yjc.hichat_back.service;

import com.all.models.announcementAndPay.common.RRException;
import com.all.models.announcementAndPay.pojo.Announcement;
import com.all.models.announcementAndPay.pojo.Image;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:YJC
 * @Description:
 * @Date:Create in 21:22 2020/6/8
 */
public interface AnnouncementService {
    /**
     * @Description:   添加公告
     * @Param:  [ann]
     * @return:  java.lang.Boolean
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Boolean add(Announcement ann)throws RRException;
    /**
     * @Description:   更新公告
     * @Param:  [ann]
     * @return:  java.lang.Boolean
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Boolean update(Announcement ann)throws RRException;
    /**
     * @Description:   删除公告
     * @Param:  [ann]
     * @return:  java.lang.Boolean
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Boolean delete(Integer id)throws RRException;
    /**
     * @Description:   删除公告
     * @Param:  [ann]
     * @return:  com.github.pagehelper.PageInfo<com.all.models.announcementAndPay.pojo.Announcement>
     * @Author: YJC
     * @Date:  2020/6/9
     */
    PageInfo<Announcement> getList(int pageNum, int pageSize);
    /**
     * @Description:   获取公告列表
     * @Param:  [title]
     * @return:  com.github.pagehelper.PageInfo<com.all.models.announcementAndPay.pojo.Announcement>
     * @Author: YJC
     * @Date:  2020/6/9
     */
    PageInfo<Announcement> getByTitle(String title, int pageNum, int pageSize);
    /**
     * @Description:   获取公告内容
     * @Param:  [id]
     * @return:  com.all.models.announcementAndPay.pojo.Announcement
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Announcement getDetail(Integer id)throws RRException;
    /**
     * @Description:   公告点赞数加一
     * @Param:  [id]
     * @return:  java.lang.Boolean
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Boolean praise(Integer id, Integer userId)throws RRException;
    /**
     * @Description:   访问数+1
     * @Param:  [id]
     * @return:  java.lang.Boolean
     * @Author: YJC
     * @Date:  2020/6/9
     */
    Boolean access(Integer id)throws RRException;

    /**
     * @Description:   保存文件
     * @Param:  [file]
     * @return:  java.lang.Boolean
     * @Author: YJC
     * @Date:  2020/6/13
     */
    Image upload(MultipartFile file)throws java.io.IOException;
}
