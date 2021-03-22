package per.yjc.hichat_back.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * (TbUser)实体类
 *
 * @author makejava
 * @since 2020-06-10 19:55:02
 */
@Data
public class TbUser implements Serializable {
    private static final long serialVersionUID = -39800777302526236L;
    
    private Integer userId;
    /**
     *头像
     */
    private String picture;
    /**
     *账号
     */
    private String account;
    /**
     *密码
     */
    private String password;
    /**
     *用户状态
     */
    private Integer userStatus;
    /**
     *昵称
     */
    private String petName;
    /**
     *邮箱
     */
    private String mailbox;
    /**
     *目标院校
     */
    private String targetColleges;
    /**
     *总财富值
     */
    private Integer allWeath;
    /**
     *邮箱验证码
     */
    private String mailVerification;


}