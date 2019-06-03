package com.pingan.pare.bi.common.domain;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Administrator
 *
 */
@Data
public class PageInfo<T> {

	public static final String REQUEST_ATTRIBUTE="PAGE_INFO_JSON_ATTRIBUTE";

	private int current=1;
	
	private int pageSize=10;
	
	private boolean isAsc=true;
	
	private String orderBy="ID";



	public PageInfo() {
		super();
	}

	public PageInfo(int current, int pageSize) {
		super();
		this.current = current;
		this.pageSize = pageSize;
	}

	public PageInfo(int current, int pageSize, boolean isAsc, String orderBy) {
		this.current = current;
		this.pageSize = pageSize;
		this.isAsc = isAsc;
		if(!StringUtils.isEmpty(orderBy)){
			this.orderBy = orderBy;
		}
	}

	public Page<T> getPage(){
		Page<T> page=new Page<T>(this.current,this.pageSize,this.orderBy,this.isAsc);
	    return page;
	}
}
