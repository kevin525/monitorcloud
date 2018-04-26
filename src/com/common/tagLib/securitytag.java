package com.common.tagLib;

import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;


/**
 *@author 作者  李涛
 *@version v1
 *创建时间：2015年9月19日下午2:48:00
 *类说明：
 */
public class securitytag extends RequestContextAwareTag{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 82343226107286349L;
	private String menuCode;//菜单别名与菜单资源相对应
	private String operateCode;//操作名称与操作code相对应
	private String value;//按钮名称
	private String type;//按钮类型
	private String style;//按钮的style
	private String cssClass;//按钮样式
	private String id;//按钮的id
	private String iconCls;//按钮图标
	private String onclickEvent;//
	static final String TYPE_BTN = "button";
	
	
	@Override
	protected int doStartTagInternal() throws Exception {
		JspWriter out = pageContext.getOut();
		if (type == null){
			type = TYPE_BTN;
		}
		if (value == null){
			value = "";
		}
		String tag="";
		if(TYPE_BTN==type){
			tag = "<a href='javascript:void(0)' ";
			tag=tag+" class="+val(cssClass);
			tag=tag+" id="+val(id);
			tag=tag+" onclick="+val(onclickEvent);
			tag = tag +" >" +value + "</a>";
		}else{
			tag = "<a href=\"javascript:void(0)\" ";
			tag=tag+" id=\""+val(id)+"\"";
			tag=tag+" onclick=\""+val(onclickEvent)+"\"";
			tag = tag +" >" +value + "</a>";
		}
		out.write(tag);
		return EVAL_PAGE;// 表示处理完标签后继续执行以下的JSP网页
	}
	
	/**
	 * 将没有设置的值设置为空串
	 * @param val
	 * @return
	 */
	private String val(String val) {
		return val == null ? "" : val;
	}

	

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getOnclickEvent() {
		return onclickEvent;
	}

	public void setOnclickEvent(String onclickEvent) {
		this.onclickEvent = onclickEvent;
	}

	public static String getTypeBtn() {
		return TYPE_BTN;
	}
	
	
	

}
