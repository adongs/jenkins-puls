package com.adongs.windows.components;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JPanel;

/**
 * 动态进度条
 * @author yudong
 * @version 1.0
 * @date 2020/11/5 2:24 下午
 * @modified By
 */
public class CircleProgressBar extends JPanel {


    public CircleProgressBar(int value) {
        this.value = value;
        this.maxValue = 100;
        this.minValue = 0;
        this.indeterminate = false;
    }


    private final static AtomicInteger DYNAMIC_FRAME = new AtomicInteger(90);
    /**
     * 进度条值
     */
    private int value;
    /**
     * 最大值
     */
    private int maxValue;
    /**
     * 最小值
     */
    private int minValue;

    /**
     * 显示内容
     */
    private String content;

    /**
     * 不确定
     */
    private boolean indeterminate;
    /**
     * 进度条颜色
     */
    private Color progressBarColor  =  Color.DARK_GRAY;
    /**
     * 进度条背景颜色
     */
    private Color backgroundColor = Color.GRAY;

    private float progressBarSize = 0.2f;


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (indeterminate){
            paintIndeterminate(g);
        }else{
            paintDeterminate(g);
        }
    }


    private void paintIndeterminate(Graphics g){
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x=0,y=0,width=0,height=0;
        float stroke=0;
        final Dimension size = getSize();
        if (size.width>size.height){
            x = (size.width-size.height)/2;
            y = 0;
            width = height = size.height;
            stroke = size.height*progressBarSize;
        }else{
            y = (size.height-size.width)/2;
            x = 0;
            width = height = size.width;
            stroke = size.width*progressBarSize;
        }
        graphics2d.setStroke(new BasicStroke(stroke));
        graphics2d.setColor(backgroundColor);
        graphics2d.drawArc(x,y,width,height,0, 360);
        graphics2d.setColor(progressBarColor);
        graphics2d.drawArc(x,y,width,height,DYNAMIC_FRAME.get(),-90);
    }

    private void paintDeterminate(Graphics g){
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x=0,y=0,width=0,height=0,fontSize=0;
        float stroke=0;
        final Dimension size = getSize();
        if (size.width>size.height){
            x = (size.width-size.height)/2;
            y = 0;
            width = height = size.height;
            stroke = size.height*progressBarSize;
            fontSize = size.height/3;
        }else{
            x = 0;
            y = (size.height-size.width)/2;
            width = height = size.width;
            stroke = size.width*progressBarSize;
            fontSize = size.width/3;
        }
        graphics2d.setStroke(new BasicStroke(stroke));
        graphics2d.setColor(backgroundColor);
        graphics2d.drawArc(x,y,width,height,0, 360);
        graphics2d.setColor(progressBarColor);
        graphics2d.drawArc(x,y,width,height,90, -(int) (360 * ((value * 1.0) / (maxValue-minValue))));
        graphics2d.setFont(new Font("黑体", Font.BOLD,fontSize));
        FontMetrics fontMetrics = graphics2d.getFontMetrics();
        String string = StringUtils.isEmpty(content)?value + "%":content;
        int digitalWidth=fontMetrics.stringWidth(string),digitalAscent = fontMetrics.getAscent();
        graphics2d.setColor(Color.black);
        graphics2d.drawString(string,getWidth()/2-digitalWidth/2,getHeight()/2+digitalAscent/2);
    }

    public void setIndeterminate(boolean indeterminate) {
        this.indeterminate = indeterminate;
    }

    /**
     * 下一帧
     */
    public static void nextFrame(){
        DYNAMIC_FRAME.set(DYNAMIC_FRAME.get()-10);
    }

}
