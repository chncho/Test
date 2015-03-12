package dataType.tree;

import java.util.List;

public class TreeEntity{
	private String id;
	private String name;
	private List<TreeEntity> sons;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TreeEntity> getSons() {
		return sons;
	}
	public void setSons(List<TreeEntity> sons) {
		this.sons = sons;
	}
}