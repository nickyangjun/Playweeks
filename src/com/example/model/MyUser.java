package com.example.model;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {
	
	private boolean sex;
    private String nickname;

    public boolean getSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return this.nickname;
    }

    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

}
