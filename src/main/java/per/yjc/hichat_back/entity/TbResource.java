package per.yjc.hichat_back.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * (TbResource)实体类
 *
 * @author makejava
 * @since 2020-06-10 19:55:02
 */
@Data
public class TbResource implements Serializable {
    private static final long serialVersionUID = 859030118152566913L;
    
    private Integer resourceId;
    
    private Integer userId;
    
    private Integer wealthValue;
    
    private String title;
    
    private String describe;
    
    private Integer downloadNum;
    
    private Integer pageViews;
    
    private String url;
    
    private Object time;
    
    private Integer tagId;


}