/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditAction;
import com.delmar.sys.model.Page;
import com.delmar.sys.service.PageService;

/**
 * @author 刘大磊 2015年1月15日 上午10:59:38
 */
public class PageAction extends CoreEditAction {

	private Page page=new Page();
	@Autowired
	private PageService pageService;
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}
	public String save()
	{
		boolean isnew= page.getId() == null;
		id=pageService.save(page);
		page.setId(id);
		if(isnew)
		{
			this.getIdList().add(id);
		}
		addActionMessage("保存页面信息成功！");
		return edit();
	}
	@Override
	public String delete() {
		pageService.deleteByPrimaryKey(page.getId());
		return list();
	}

	@Override
	public void deleteList(Integer[] ids) {
		pageService.deletePages(ids);

	}

	@Override
	public void editForm() {
		if(id==null||id==0)
		{
			page=new Page();
		}
		else
		{
			page=pageService.selectByPrimaryKey(id);
		}

	}

	@Override
	public Integer getModelId() {
		
		return page.getId();
	}

	@Override
	public String getModuleName() {
		
		return "page";
	}

	@Override
	public List search() {
		Map<String,Object> param=new HashedMap();
		param.put("searchString",getSearchWhere());
		return this.pageService.selectByExample(param);
	}
}
