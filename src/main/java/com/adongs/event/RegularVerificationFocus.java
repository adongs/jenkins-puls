package main.src.com.adongs.event;


import com.adongs.manager.WindowManager;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

/**
 * @author yudong
 * @date  2020/9/29 4:01 下午
 * @version 1.0
 * @modified By
 */
public abstract class RegularVerificationFocus implements FocusListener {

    private final Pattern pattern;
    private final String message;
    private final JLabel showError;

    public RegularVerificationFocus(String regular,String message,JLabel showError) {
        pattern = Pattern.compile(regular);
        this.message = message;
        this.showError = showError;
    }

    @Override
    public void focusGained(FocusEvent e) {
        final JTextField textField = (JTextField)e.getComponent();
        final String text = textField.getText();
        if (!pattern.matcher(text).matches()){
            showError.setText(message);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {}
}
