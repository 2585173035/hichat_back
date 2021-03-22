package per.yjc.hichat_back.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * (TbInvitation)实体类
 *
 * @author makejava
 * @since 2020-06-12 10:35:32
 */
@Data
public class TbInvitation implements Serializable {
    private static final long serialVersionUID = -83871798233053310L;
    /**
     *id
     */
    private Integer invitationId;
    /**
     *用户id
     */
    private Integer userId;
    /**
     *标题
     */
    private String title;
    /**
     *内容
     */
    private String content;
    /**
     *类型  1-公告  2-帖子 3-已删除
     */
    private Integer type;
    /**
     *点赞数
     */
    private Integer praisePoints;
    /**
     *浏览数
     */
    private Integer viewPoints;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *重要程度
     */
    private Integer degree;
    
    private String annDegree;


}