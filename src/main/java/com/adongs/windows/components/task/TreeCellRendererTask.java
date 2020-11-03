package com.adongs.windows.components.task;

import com.adongs.event.ConstructMouseListener;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/30 11:32 上午
 * @modified By
 */
public class TreeCellRendererTask implements TreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
          if (value instanceof FolderTreeNode){
              FolderTreeNode folderTreeNode = (FolderTreeNode)value;
              JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
              final JLabel icon = new JLabel(folderTreeNode.getIcon());
              icon.setToolTipText(folderTreeNode.getTitle());
              jPanel.add(icon);
              final JLabel name = new JLabel(folderTreeNode.getName());
              jPanel.setToolTipText(folderTreeNode.getTitle());
              jPanel.add(name);
              return jPanel;
          }
         if (value instanceof TaskTreeNode){
             TaskTreeNode taskTreeNode = (TaskTreeNode)value;
             JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
             final JLabel status = new JLabel(taskTreeNode.getStatus());
             jPanel.setToolTipText(taskTreeNode.getTitle());
             jPanel.add(status);
             final JLabel name = new JLabel(taskTreeNode.getName());
             jPanel.add(name);
             final Icon releaseIcon = taskTreeNode.getReleaseIcon();
             if (releaseIcon!=null){
                 jPanel.add(new JLabel(releaseIcon));
             }
             return jPanel;
         }
         return null;
    }
}
