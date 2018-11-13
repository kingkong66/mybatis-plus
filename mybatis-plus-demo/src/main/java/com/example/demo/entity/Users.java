package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zac.jin
 * @since 2018-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("USERS")
public class Users extends Model<Users> {
    @TableId("ID")
    private Long id;

    @TableField("USERNAME")
    private String username;

    @TableField("PASSWORD")
    private String password;

    @TableField("PHONE_NO")
    private String phoneNo;

    @TableField("ID_NO")
    private String idNo;

    @TableField("CHANNEL")
    private String channel;

    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @TableField("WECHAT_OPENID")
    private String wechatOpenid;

    @TableField("BIND_WECHAT_TIME")
    private LocalDateTime bindWechatTime;

    @TableField("DEVICE_ID")
    private Long deviceId;

    @TableField("IMEI")
    private String imei;

    @TableField("IMSI")
    private String imsi;

    @TableField("LOCAL_PHONE_NO")
    private String localPhoneNo;

    @TableField("SUBMIT_MODULES_STATE")
    private String submitModulesState;

    @TableField("IS_CLOSED")
    private Long isClosed;

    @TableField("WHICH_STEP")
    private Long whichStep;

    @TableField("FIRST_STEP_MODULE")
    private Long firstStepModule;

    @TableField("DEVICE_TOKEN")
    private String deviceToken;

    @TableField("MARKET_SCORE")
    private Long marketScore;

    @TableField("IS_REPORT_LOCATION")
    private Long isReportLocation;

    @TableField("PLATFORM")
    private String platform;

    @TableField("CREDIT_CEILING")
    private Long creditCeiling;

    @TableField("CURRENT_CREDIT_LOAN")
    private Long currentCreditLoan;

    @TableField("REVIEWED_AT")
    private LocalDateTime reviewedAt;

    @TableField("FAILED_REASON")
    private String failedReason;

    @TableField("LOOP_MONEY")
    private Long loopMoney;

    @TableField("IS_DELETED")
    private Long isDeleted;

    @TableField("SHOULD_REPAY_DAY")
    private Long shouldRepayDay;

    @TableField("TRLC_USERNAME")
    private String trlcUsername;

    @TableField("BI_COUNT")
    private Long biCount;

    @TableField("TL_USER_ID")
    private String tlUserId;

    @TableField("INVITATION_ID")
    private Long invitationId;

    @TableField("SUB_CHANNEL_ID")
    private Long subChannelId;

    /**
     * 登录名（唯一1）
     */
    @TableField("LOGIN_NAME")
    private String loginName;

    @TableField("PROFILE_PHASE")
    private Long profilePhase;

    @TableField("PAY_PASSWORD_STATUS")
    private Double payPasswordStatus;

    @TableField("ADDRESS")
    private String address;

    @TableField("MAXENT_ID")
    private String maxentId;

    @TableField("APPLICATION")
    private String application;

    @TableField("PRODUCT")
    private String product;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
