package com.money.project.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.money.common.utils.poi.ExcelUtil;
import com.money.framework.aspectj.lang.annotation.Log;
import com.money.framework.aspectj.lang.enums.BusinessType;
import com.money.framework.web.controller.BaseController;
import com.money.framework.web.domain.AjaxResult;
import com.money.framework.web.page.TableDataInfo;
import com.money.project.system.domain.SysPost;
import com.money.project.system.service.ISysPostService;


@RestController
@RequestMapping("/system/post")
public class SysPostController extends BaseController
{
    @Autowired
    private ISysPostService postService;


    @PreAuthorize("@ss.hasPermi('system:post:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPost post)
    {
        startPage();
        List<SysPost> list = postService.selectPostList(post);
        return getDataTable(list);
    }
    
    @Log(title = "job management", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:post:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPost post)
    {
        List<SysPost> list = postService.selectPostList(post);
        ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
        util.exportExcel(response, list, "Job data");
    }


    @PreAuthorize("@ss.hasPermi('system:post:query')")
    @GetMapping(value = "/{postId}")
    public AjaxResult getInfo(@PathVariable Long postId)
    {
        return success(postService.selectPostById(postId));
    }


    @PreAuthorize("@ss.hasPermi('system:post:add')")
    @Log(title = "job management", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysPost post)
    {
        if (!postService.checkPostNameUnique(post))
        {
            return error("new jobs'" + post.getPostName() + "'Failed, job title already exists");
        }
        else if (!postService.checkPostCodeUnique(post))
        {
            return error("new jobs'" + post.getPostName() + "'Failed, job code already exists");
        }
        post.setCreateBy(getUsername());
        return toAjax(postService.insertPost(post));
    }


    @PreAuthorize("@ss.hasPermi('system:post:edit')")
    @Log(title = "job management", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysPost post)
    {
        if (!postService.checkPostNameUnique(post))
        {
            return error("modify position'" + post.getPostName() + "'Failed, job title already exists");
        }
        else if (!postService.checkPostCodeUnique(post))
        {
            return error("modify position'" + post.getPostName() + "'Failed, job code already exists");
        }
        post.setUpdateBy(getUsername());
        return toAjax(postService.updatePost(post));
    }


    @PreAuthorize("@ss.hasPermi('system:post:remove')")
    @Log(title = "job management", businessType = BusinessType.DELETE)
    @DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds)
    {
        return toAjax(postService.deletePostByIds(postIds));
    }


    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<SysPost> posts = postService.selectPostAll();
        return success(posts);
    }
}
