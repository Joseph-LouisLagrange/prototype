package com.darwin.prototype.doj.sys;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * 权限表达式
 */
@Data
@Embeddable
@RequiredArgsConstructor(staticName = "of")
public class PermissionExpression implements Serializable {

    public static final String ALL_ACTION = "*";

    public static final String ALL_TARGET_ID = "*";

    @NonNull
    @Column(name = "target_type",unique = true,updatable = false,columnDefinition = "VARCHAR(128)")
    private String targetType;          // 域类型

    @NonNull
    @Column(name = "action",unique = true,updatable = false,columnDefinition = "VARCHAR(128)")
    private String action;              // 动作

    @NonNull
    @Column(name = "target_id",unique = true,updatable = false,columnDefinition = "VARCHAR(128)")
    private String targetID;      // 域对象 ID

    public PermissionExpression() {

    }

    /**
     * <h3>解析表达式</h3>
     * <p>Example:</p>
     * <ol>
     *     <li>Apple:delete:*                    删除任意的 Apple (不能使用)</li>
     *     <li>com.example.Person:update:*       更新任意的 Person</li>
     *     <li>com.example.Dog:read:1            查看 ID 为 1 的 Dog</li>
     * </ol>
     * <b>推荐使用全限定类名来表示 targetType</b>
     * @param  expression shiro 权限表达式
     * @return PermissionExpression 解析后的权限表达式对象
     */
    public static PermissionExpression parse(String expression){
        String[] temp = expression.split(":");
        Assert.isTrue(temp.length == 3,"Expression cannot be parsed");
        return of(temp[0],temp[1],temp[2]);
    }


    public boolean access(String targetType,String action,String targetID){
        return true;
    }

}
