package per.yjc.hichat_back.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * (TbPlan)实体类
 *
 * @author makejava
 * @since 2020-06-10 19:55:02
 */
@Data
public class TbPlan implements Serializable {
    private static final long serialVersionUID = -58802595828551757L;
    
    private Integer planId;
    /**
     *用户id
     */
    private Integer userId;
    /**
     *当天计划数
     */
    private Integer todayPlanNum;
    /**
     *时间
     */
    private Object time;
    /**
     *计划内容
     */
    private String planContent;


}