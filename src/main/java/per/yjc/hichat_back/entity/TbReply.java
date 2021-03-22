package per.yjc.hichat_back.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * (TbReply)实体类
 *
 * @author makejava
 * @since 2020-06-10 19:55:02
 */
@Data
public class TbReply implements Serializable {
    private static final long serialVersionUID = 330468429684118589L;
    
    private Integer replyId;
    /**
     *用户id
     */
    private Integer userId;
    /**
     *内容
     */
    private String content;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *标题
     */
    private String title;
    /**
     *贴子id
     */
    private Integer invitationId;
    /**
     *点赞数
     */
    private Integer praisePoints;


}