package per.yjc.hichat_back.service.impl;

import com.all.models.announcementAndPay.common.RRException;
import com.all.models.announcementAndPay.mapper.AnnouncementMapper;
import com.all.models.announcementAndPay.pojo.Announcement;
import com.all.models.announcementAndPay.pojo.Image;
import com.all.models.announcementAndPay.service.AnnouncementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @Author:YJC
 * @Description:
 * @Date:Create in 21:22 2020/6/8
 */
@Service()
@Transactional(rollbackFor = Exception.class)
public class AnnouncementServiceImpl implements AnnouncementService {


    @Autowired
    AnnouncementMapper announcementMapper;
    @Value("${mypath}")
    String savePath;
    @Value("${http}")
    String serverPath;


    /**
     * @param ann
     * @Description: 添加公告
     * @Param: [ann]
     * @return: java.lang.Boolean
     * @Author: YJC
     * @Date: 2020/6/9
     */
    @Override
    public Boolean add(Announcement ann) throws RRException{
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        ann.setCreateTime(timeStamp);
        Integer flag = announcementMapper.insertOne(ann);
        if (flag!=1){
            throw new RRException("添加失败");
        }
        return true;
    }

    /**
     * @param ann
     * @Description: 更新公告
     * @Param: [ann]
     * @return: java.lang.Boolean
     * @Author: YJC
     * @Date: 2020/6/9
     */
    @Override
    public Boolean update(Announcement ann) throws RRException{
        Integer flag = announcementMapper.update(ann);
        if (flag!=1){
            throw new RRException("修改失败");
        }
        return true;
    }

    /**
     * @param id
     * @Description: 删除公告
     * @Param: [ann]
     * @return: java.lang.Boolean
     * @Author: YJC
     * @Date: 2020/6/9
     */
    @Override
    public Boolean delete(Integer id) throws RRException{
        Integer flag = announcementMapper.delete(id);
        if (flag!=1){
            throw new RRException("删除失败");
        }
        return true;
    }

    /**
     * @Description:   获取公告List
     * @Param:  [pageNum, pageSize]
     * @return:  com.github.pagehelper.PageInfo<com.all.models.announcementAndPay.pojo.Announcement>
     * @Author: YJC
     * @Date:  2020/6/9
     */
    @Override
    public PageInfo<Announcement> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ArrayList<Announcement> list = announcementMapper.getList();
        PageInfo<Announcement> info = new PageInfo<Announcement>(list);
        return info;
    }

    /**
     * @param title
     * @Description: 获取公告列表
     * @Param: [title]
     * @return: com.github.pagehelper.PageInfo<com.all.models.announcementAndPay.pojo.Announcement>
     * @Author: YJC
     * @Date: 2020/6/9
     */
    @Override
    public PageInfo<Announcement> getByTitle(String title,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ArrayList<Announcement> list = announcementMapper.getListByTitle(title);
        PageInfo<Announcement> info = new PageInfo<Announcement>(list);
        return info;
    }

    /**
     * @param id
     * @Description: 获取公告内容
     * @Param: [id]
     * @return: com.all.models.announcementAndPay.pojo.Announcement
     * @Author: YJC
     * @Date: 2020/6/9
     */
    @Override
    public Announcement getDetail(Integer id) throws RRException{
        Announcement announcement = announcementMapper.getDetail(id);
        if (announcement==null){
            throw new RRException("查看的公告不存在");
        }
        this.access(id);
        return announcement;
    }

    /**
     * @param id
     * @Description: 公告点赞数加一
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: YJC
     * @Date: 2020/6/9
     */
    @Override
    public Boolean praise(Integer id,Integer userId) throws RRException{
        //先判断是否已经点赞
        Integer praiseNum = announcementMapper.isPraise(id, userId);
        if (praiseNum>0){
            throw new RRException("不能重复点赞");
        }
        Integer flag2 = announcementMapper.insertRelation(id, userId);
        if (flag2!=1){
            throw new RRException("点赞失败");
        }
        Integer flag = announcementMapper.praise(id);
        if (flag!=1){
            throw new RRException("点赞失败");
        }
        return true;
    }

    /**
     * @param id
     * @Description: 访问数+1
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: YJC
     * @Date: 2020/6/9
     */
    @Override
    public Boolean access(Integer id) throws RRException{
        Integer flag = announcementMapper.access(id);
        if (flag!=1){
            throw new RRException("访问记录错误");
        }
        return true;
    }

    /**
     * @param file
     * @Description: 保存文件
     * @Param: [file]
     * @return: java.lang.Boolean
     * @Author: YJC
     * @Date: 2020/6/13
     */
    @Override
    public Image upload(MultipartFile file) throws UnsupportedEncodingException,IOException {
        Image image = new Image();
        String fileName = file.getOriginalFilename();
        image.setTitle(fileName);
        String fSaveName = UUID.randomUUID()+file.getOriginalFilename().substring(
                file.getOriginalFilename().lastIndexOf("."));
        File newFile = new File(savePath+fSaveName);
        File file_path = new File(savePath);
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        basePath= URLDecoder.decode(basePath,"utf-8");
        if  (!file_path.exists()  && !file_path .isDirectory())
        {
            System.out.println("//文件夹不存在");
            boolean mkdirs = file_path.mkdirs();
            System.out.println("//文件夹自动创建结果："+mkdirs);
//            file.transferTo(newFile);
            Thumbnails.of(file.getInputStream())
                    .scale(1f)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"image/logo.png")), 1f)
                    .outputQuality(0.5f)
                    .toFile(newFile);

        } else
        {

//            file.transferTo(newFile);

            Thumbnails.of(file.getInputStream())
                    .scale(1f)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"image/logo.png")), 1f)
                    .outputQuality(0.5f)
                    .toFile(newFile);

        }
        image.setSrc(serverPath+"/img/"+fSaveName);
        return image;
    }
}
