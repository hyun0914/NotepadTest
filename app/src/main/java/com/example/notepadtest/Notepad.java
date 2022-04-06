package com.example.notepadtest;

public class Notepad {

    private Integer no;
    private String title;
    private String content;

    public Notepad(Integer no, String title, String content){
        this.no = no;
        this.title = title;
        this.content = content;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent() {
        this.content = content;
    }

}
