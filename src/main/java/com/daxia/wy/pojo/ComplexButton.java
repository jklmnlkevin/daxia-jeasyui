package com.daxia.wy.pojo;
/**
 * 复杂按钮（有子按钮的）
 * @author zzc
 * @email zzc6055@gmail.com
 * @version 1.0
 * @since 2014-1-23
 */
public class ComplexButton extends Button {  
    private Button[] sub_button;  
  
    public Button[] getSub_button() {  
        return sub_button;  
    }  
  
    public void setSub_button(Button[] sub_button) {  
        this.sub_button = sub_button;  
    }  
}  