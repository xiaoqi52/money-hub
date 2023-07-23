package com.money.project.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.money.framework.aspectj.lang.annotation.Excel;
import com.money.framework.aspectj.lang.annotation.Excel.ColumnType;
import com.money.framework.web.domain.BaseEntity;


public class SysPost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "postId", cellType = ColumnType.NUMERIC)
    private Long postId;

    @Excel(name = "postCode")
    private String postCode;

    @Excel(name = "postName")
    private String postName;

    @Excel(name = "postSort")
    private Integer postSort;

    @Excel(name = "status", readConverterExp = "0=YES,1=NO")
    private String status;

    private boolean flag = false;

    public Long getPostId()
    {
        return postId;
    }

    public void setPostId(Long postId)
    {
        this.postId = postId;
    }

    @NotBlank(message = "Job code cannot be empty")
    @Size(min = 0, max = 64, message = "Job code length cannot exceed 64 characters")
    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    @NotBlank(message = "Job code length cannot exceed 64 characters")
    @Size(min = 0, max = 50, message = "Job title length cannot exceed 50 characters")
    public String getPostName()
    {
        return postName;
    }

    public void setPostName(String postName)
    {
        this.postName = postName;
    }

    @NotNull(message = "Display order cannot be empty")
    public Integer getPostSort()
    {
        return postSort;
    }

    public void setPostSort(Integer postSort)
    {
        this.postSort = postSort;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("postId", getPostId())
            .append("postCode", getPostCode())
            .append("postName", getPostName())
            .append("postSort", getPostSort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
