package per.yjc.hichat_back.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * (TbTag)实体类
 *
 * @author makejava
 * @since 2020-06-10 19:55:02
 */
@Data
public class TbTag implements Serializable {
    private static final long serialVersionUID = -56169716209628149L;
    
    private Integer tagId;
    /**
     *标签名称
     */
    private String name;


}