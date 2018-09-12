package com.zhidisoft.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单树节点对应的实体类
 */
public class TreeNode {
	private Integer id ;// ：节点的 id，它对于加载远程数据很重要。
	private String text;//：要显示的节点文本。
	private String state; // ：节点状态，'open' 或 'closed'，默认是 'open'。当设置为 'closed' 时，该节点有子节点，并且将从远程站点加载它们。
	private boolean checked; //：指示节点是否被选中。
	private Map attributes = new HashMap(); // 给一个节点添加的自定义属性。
	private List<TreeNode> children = new ArrayList<TreeNode>(); //：定义了一些子节点的节点数组。
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Map getAttributes() {
		return attributes;
	}
	public void setAttributes(Map attributes) {
		this.attributes = attributes;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", text=" + text + ", state=" + state + ", checked=" + checked + ", attributes="
				+ attributes + ", children=" + children + "]";
	}
	
}
