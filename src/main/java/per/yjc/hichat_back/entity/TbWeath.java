package per.yjc.hichat_back.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * (TbWeath)实体类
 *
 * @author makejava
 * @since 2020-06-10 19:55:07
 */
@Data
public class TbWeath implements Serializable {
    private static final long serialVersionUID = -97126351491849525L;
    
    private Integer id;
    /**
     *用户id
     */
    private Integer userId;
    /**
     *操作事项id
     */
    private Integer matterId;
    /**
     *变化数值
     */
    private Integer changValue;
    /**
     *操作时间
     */
    private Date operatingTime;


}