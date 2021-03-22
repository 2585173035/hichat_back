package per.yjc.hichat_back.controller;

import com.all.models.announcementAndPay.common.RRException;
import com.all.models.announcementAndPay.common.Result;
import com.all.models.announcementAndPay.pojo.Announcement;
import com.all.models.announcementAndPay.pojo.Image;
import com.all.models.announcementAndPay.service.impl.AnnouncementServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author:YJC
 * @Description:
 * @Date:Create in 21:20 2020/6/8
 */

@RequestMapping("/api/ann")
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class AnnouncementController {

    @Autowired
    AnnouncementServiceImpl announcementService;

    /**
     * @Description:   新增公告
     * @Param:  [ann]
     * @return:  com.all.models.announcementAndPay.common.Result
     * @Author: YJC
     * @Date:  2020/6/9
     */
    @RequestMapping(value = "/addAnnouncement",method = RequestMethod.POST)
    public Result addAnnouncement( Announcement ann)throws RRException {
        Boolean flag = announcementService.add(ann);
        if (flag){
            return Result.ok();
        }else {
            return Result.error("添加失败");
        }
    }

    /**
     * @Description:   更新公告
     * @Param:  [ann]
     * @return:  com.all.models.announcementAndPay.common.Result
     * @Author: YJC
     * @Date:  2020/6/9
     */
    @RequestMapping(value = "/updateAnnouncement",method = RequestMethod.POST)
    public Result updateAnnouncement(Announcement ann)throws RRException{
        Boolean flag = announcementService.update(ann);
        if (flag){
            return Result.ok();
        }else {
            return Result.error("更新失败");
        }
    }

    /**
     * @Description:   删除公告
     * @Param:  [ann]
     * @return:  com.all.models.announcementAndPay.common.Result
     * @Author: YJC
     * @Date:  2020/6/9
     */
    @RequestMapping(value = "/deleteAnnouncement",method = RequestMethod.GET)
    public Result deleteAnnouncement(@RequestParam("id")Integer id)throws RRException{
        Boolean flag = announcementService.delete(id);
        if (flag){
            return Result.ok();
        }else {
            return Result.error("删除失败");
        }
    }

    /**
     * @Description:   获取公告List
     * @Param:  [ann]
     * @return:  com.all.models.announcementAndPay.common.Result
     * @Author: YJC
     * @Date:  2020/6/9
     */
    @RequestMapping(value = "/getAnnouncementList",method = RequestMethod.GET)
    public Result getAnnouncementList(@RequestParam(value = "pageNum",required = true) int pageNum,
                                      @RequestParam(value = "pageSize",required = true) int pageSize)throws RRException{
        PageInfo<Announcement> list = announcementService.getList(pageNum,pageSize);
        return Result.ok().put("list",list);
    }

    /**
     * @Description:   通过标题获取公告
     * @Param:  [title]
     * @return:  com.all.models.announcementAndPay.common.Result
     * @Author: YJC
     * @Date:  2020/6/9
     */
    @RequestMapping(value = "/getAnnouncementByTitle",method = RequestMethod.GET)
    public Result getAnnouncementByTitle(@RequestParam(value = "title",required = true) String title,
                                         @RequestParam(value = "pageNum",required = true) int pageNum,
                                         @RequestParam(value = "pageSize",required = true) int pageSize)throws RRException{
        PageInfo<Announcement> list = announcementService.getByTitle(title,pageNum,pageSize);
        return Result.ok().put("list",list);
    }

    /**
     * @Description:   获取公告详情
     * @Param:  [id]
     * @return:  com.all.models.announcementAndPay.common.Result
     * @Author: YJC
     * @Date:  2020/6/9
     */
    @RequestMapping(value = "/getAnnouncementDetail",method = RequestMethod.GET)
    public Result getAnnouncementDetail(@RequestParam("id")Integer id)throws RRException{
        Announcement detail = announcementService.getDetail(id);
        return Result.ok().put("info",detail);
    }

    /**
     * @Description:   公告点赞数加一
     * @Param:  [id]
     * @return:  com.all.models.announcementAndPay.common.Result
     * @Author: YJC
     * @Date:  2020/6/9
     */
    @RequestMapping(value = "/praise",method = RequestMethod.GET)
    public Result praise(@RequestParam("id")Integer id,
                         @RequestParam("userId")Integer userId)throws RRException{
        Boolean flag = announcementService.praise(id, userId);
        if (flag){
            return Result.ok();
        }else {
            return Result.error("点赞失败");
        }
    }

    /**
     * @Description:   上传图片
     * @Param:  [file]
     * @return:  com.all.models.announcementAndPay.common.Result
     * @Author: YJC
     * @Date:  2020/6/13
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result upload(@RequestParam("file") MultipartFile file)throws RRException, java.io.IOException{
        Image data = announcementService.upload(file);
        if (data!=null){
            return Result.ok(0).put("data",data);
        }else {
            return Result.error("图片上传失败");
        }
    }




}
