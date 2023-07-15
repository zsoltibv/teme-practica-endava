package com.endava.model;

public class Task {

  private final int id;
  private final String title;
  private final String content;

  public Task(int id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  @Override
  public String toString(){
    return "Task{id=" + id + ", title=" + title + ", content=" + content + "}";
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }
}
