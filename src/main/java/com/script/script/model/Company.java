package com.script.script.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Company {
private int id;
private String url;
private String title;
private Timestamp createTime;
private String type;//3
}
