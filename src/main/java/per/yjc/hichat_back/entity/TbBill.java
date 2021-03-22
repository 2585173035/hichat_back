package per.yjc.hichat_back.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * (TbBill)实体类
 *
 * @author makejava
 * @since 2020-06-10 19:55:01
 */
@Data
public class TbBill implements Serializable {
    private static final long serialVersionUID = -94974459964541137L;
    /**
     *订单id
     */
    private String billId;
    /**
     *用户id
     */
    private Integer userId;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *金额
     */
    private Double money;


}