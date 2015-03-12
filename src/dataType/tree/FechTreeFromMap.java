package dataType.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 构建树形菜单结构
 * 
 *
 * @author 陈超
 *
 * @version $Revision$
 *
 * @since 2015-3-12
 */
public class FechTreeFromMap {

	public static void main(String[] args) {
		Gson gson = new GsonBuilder().create();
		
		Map<String,List<TreeEntity>> treeNodeCollections = new HashMap<String,List<TreeEntity>>();//二级及以下
		//======================pid=====id
		List<TreeEntity> list;
		{	
			list = new LinkedList<TreeEntity>();
			list.add(returnTreeENode("B","二"));
			treeNodeCollections.put("A", list);
		}
		{
			list = new LinkedList<TreeEntity>();
			list.add(returnTreeENode("C","三"));
			treeNodeCollections.put("B", list);
		}
		{
			list = new LinkedList<TreeEntity>();
			list.add(returnTreeENode("D","四"));
			list.add(returnTreeENode("E","五"));
			list.add(returnTreeENode("F","六"));
			list.add(returnTreeENode("D","七"));
			treeNodeCollections.put("C", list);
		}
		
		for(String key : treeNodeCollections.keySet()){
			System.out.println(key+"\t...\t"+treeNodeCollections.get(key));
		}
		System.out.println("===============================================================");
		System.out.println(gson.toJson(fillTree(returnTreeENode("A","一"),treeNodeCollections)));
	}

	private static TreeEntity returnTreeENode(String id,String name){
		TreeEntity tree = new TreeEntity();
		tree.setId(id);
		tree.setName(name);
		return tree;
	}
	/**
	 * 
	 * 功能描述：递归填充树
	 *
	 * @param treeNode
	 * @param treeNodeCollections
	 * @return
	 *
	 * @author 陈超
	 *
	 * @since 2015-3-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static TreeEntity fillTree(
				TreeEntity treeNode, 
				Map<String,List<TreeEntity>> treeNodeCollections
			){
		//取当前元素的ID
		String theid = (String) treeNode.getId();
		//如果子元素池中存在当前元素的子元素
		if(treeNodeCollections.containsKey(theid)){
			//将子元素塞进当前元素
			treeNode.setSons(treeNodeCollections.get(theid));
			//遍历子元素，给子元素填充它的子元素
			for(TreeEntity sonTree : treeNode.getSons()){
				fillTree(sonTree,treeNodeCollections);
			}
		}
		
		return treeNode;
	}
	
}

