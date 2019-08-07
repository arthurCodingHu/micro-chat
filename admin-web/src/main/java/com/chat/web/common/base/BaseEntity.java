package com.chat.web.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author franky
 * @description
 * @date 2019-04-17
 */
@Data
public class BaseEntity implements Serializable {
    @TableId(value = "code",type = IdType.UUID)
    private String code;
}
