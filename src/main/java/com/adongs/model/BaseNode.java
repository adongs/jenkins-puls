package com.adongs.model;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/11/6 4:35 下午
 * @modified By
 */
public abstract class BaseNode extends DefaultMutableTreeNode {

    public abstract Component draw();
}
