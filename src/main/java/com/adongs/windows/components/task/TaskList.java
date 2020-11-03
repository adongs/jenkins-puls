package com.adongs.windows.components.task;

import com.adongs.JenkinsClient;
import com.adongs.api.ViewInfo;
import com.adongs.event.TreeReleaseMouseListener;
import com.adongs.manager.JenkinsClientManager;
import com.adongs.model.View;
import com.adongs.windows.components.action.UpdateData;
import com.intellij.ui.TreeSpeedSearch;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 任务列表组件
 * @author yudong
 * @version 1.0
 * @date 2020/9/30 10:55 上午
 * @modified By
 */
public class TaskList extends JTree implements UpdateData {
    private final static String  DEFAULT_VIEW_TYPE = "global";
    private final Set<String> expandedNames = new HashSet<>();
    private String selectedName = "";

    private String viewType = DEFAULT_VIEW_TYPE;


    public TaskList() {
        init();
    }

    public TaskList(@NotNull String type){
        viewType = type;
        init();
    }


    private void init(){
        setCellRenderer(new TreeCellRendererTask());
        setRootVisible(false);
        setAlignmentX(0);
        setAlignmentY(0);
        new TreeSpeedSearch(this);
        addMouseListener(new TreeReleaseMouseListener());
        addTreeExpansionListener(new TreeExpansionListener(){
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                expandedNames.add(event.getPath().toString());
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                expandedNames.remove(event.getPath().toString());
            }
        });
        addTreeSelectionListener(new TreeSelectionListener(){
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                selectedName = e.getPath().toString();
            }
        });
        update();
    }

    /**
     * 刷新数据
     */
   public void update(){
       final DefaultMutableTreeNode  rootNode= new DefaultMutableTreeNode("");
       final DefaultTreeModel defaultTreeModel = new DefaultTreeModel(rootNode, false);
       final JenkinsClient jenkinsClient = JenkinsClientManager.get();
       if (jenkinsClient!=null){
           final ViewInfo viewInfo = jenkinsClient.getView();
           final List<View> global = DEFAULT_VIEW_TYPE.equals(viewType)?viewInfo.global():viewInfo.myView();
           for (View view : global) {
              final FolderTreeNode folderTreeNode = new FolderTreeNode(view.getName(),view.getJobs().size()+"个任务");
               view.getJobs().forEach(j->folderTreeNode.add(new TaskTreeNode(j)));
               defaultTreeModel.insertNodeInto(folderTreeNode,rootNode,rootNode.getChildCount());
           }
       }
       this.setModel(defaultTreeModel);
       restoreNodeView();
   }

    /**
     * 恢复节点视图
     */
   public void restoreNodeView(){
      final TreeNode root = (TreeNode) getModel().getRoot();
       if (!expandedNames.isEmpty()){
           if (root.getChildCount()>0){
               for (Enumeration e = root.children(); e.hasMoreElements();) {
                   DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
                   final TreePath treePath = new TreePath(node.getPath());
                   final String newPath = treePath.toString();
                   if (expandedNames.contains(newPath)){
                       expandPath(treePath);
                   }
               }
           }
     }
       restoreSelected(root);
   }

    /**
     * 恢复选中节点
     * @param node
     */
  public void restoreSelected(TreeNode node){
      if (!StringUtils.isEmpty(selectedName)){
          if (node.getChildCount()>0){
              for (Enumeration e = node.children(); e.hasMoreElements();) {
                  DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) e.nextElement();
                  final TreePath treePath = new TreePath(treeNode.getPath());
                  final String newPath = treePath.toString();
                  if (selectedName.equals(treePath.toString())){
                      setSelectionPath(treePath);
                      scrollPathToVisible(treePath);
                      return;
                  }else{
                      restoreSelected(treeNode);
                  }
              }
          }
      }
  }



}
