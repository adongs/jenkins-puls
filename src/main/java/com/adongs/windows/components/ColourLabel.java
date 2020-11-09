package com.adongs.windows.components;

import org.apache.commons.lang3.StringUtils;

import javax.swing.JPanel;
import java.awt.*;


/**
 * @author yudong
 * @version 1.0
 * @date 2020/11/6 11:35 上午
 * @modified By
 */
public class ColourLabel extends JPanel {

    private String content;

    private Color backgroundColor;

    public ColourLabel(String content, Color backgroundColor) {
        this.content = content;
        this.backgroundColor = backgroundColor;
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x=0,y=0,width=0,height=0,fontSize=0,arcWidth=0,arcHeight=0;
        final Dimension size = getSize();
        width  = size.width;
        height = size.height;
        if (size.width>size.height){
            fontSize = size.height/2;
            arcWidth = size.height;
            arcHeight = size.height;
        }else{
            fontSize = size.width/2;
            arcWidth = size.width;
            arcHeight = size.width;
        }
        graphics2d.setColor(backgroundColor);
        graphics2d.fillRoundRect(x,y,width,height,arcWidth, arcHeight);
        graphics2d.setFont(new Font("黑体", Font.BOLD,fontSize));
        FontMetrics fontMetrics = graphics2d.getFontMetrics();
        int digitalWidth=fontMetrics.stringWidth(content),digitalAscent = fontMetrics.getAscent();
        graphics2d.setColor(Color.black);
        graphics2d.drawString(content,getWidth()/2-digitalWidth/2,getHeight()/2+digitalAscent/2);
    }
}
