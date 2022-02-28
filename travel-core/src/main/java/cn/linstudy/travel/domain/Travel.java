package cn.linstudy.travel.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 游记表
 *
 * 
 * @since 2021-04-17 14:02:37
 * @description 
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("travel")
public class Travel extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int STATE_NORMAL = 0;  //草稿

    public static final int STATE_WAITING = 1;  //待发布(审核中)

    public static final int STATE_RELEASE = 2;  //审核通过

    public static final int STATE_REJECT = 3;  //拒绝

    public static final int STATE_DOWN = -1;  //下架

    public static final int ISPUBLIC_NO = 0;

    public static final int ISPUBLIC_YES = 1;

    private Long destId;  //目的地

    private String destName;  //目的地

    private Long authorId;  //作者id

    @TableField(exist = false)

    private UserInfo author;

    private String title;  //标题

    private String summary;//概要

    private String coverUrl; //封面

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date travelTime; //旅游时间

    private Integer avgConsume; //人均消费

    private Integer day;  //旅游天数

    private Integer person;  //和谁旅游

    private LocalDateTime createTime; //创建时间

    private Date releaseTime; //发布时间

    private LocalDateTime lastUpdateTime; //最新更新时间内

    private Integer ispublic=ISPUBLIC_NO; //是否发布

    private Integer viewnum;  //点击/阅读数

    private Integer replynum; //回复数

    private Integer favornum;//收藏数

    private Integer sharenum;//分享数

    private Integer thumbsupnum;//点赞数

    private Integer state = STATE_NORMAL;//游记状态

    @TableField(exist = false)

    private TravelContent content;  //游记内容

    public String getStateDisplay(){

        if (state ==STATE_NORMAL ){

            return "编辑中";

        }else if(state ==STATE_WAITING ){

            return "待发布";

        }else if(state ==STATE_RELEASE ){

            return "已发布";

        }else if(state ==STATE_REJECT ){

            return "已拒绝";

        } else if (state == STATE_DOWN) {
            return "下架";
        }
        return "";

    }
}