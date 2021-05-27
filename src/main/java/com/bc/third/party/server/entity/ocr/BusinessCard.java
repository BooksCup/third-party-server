package com.bc.third.party.server.entity.ocr;

import java.util.List;

/**
 * 名片
 *
 * @author zhou
 */
public class BusinessCard {

    /**
     * 姓名
     */
    private String name;

    /**
     * 公司列表
     */
    private List<String> company;

    /**
     * 部门列表
     */
    private List<String> department;

    /**
     * 职位列表
     */
    private List<String> title;

    /**
     * 手机列表
     */
    private List<String> tel_cell;

    /**
     * 座机列表
     */
    private List<String> tel_work;

    /**
     * 地址列表
     */
    private List<String> addr;

    /**
     * 邮箱列表
     */
    private List<String> email;

    /**
     * 请求id
     */
    private String request_id;

    /**
     * 是否成功
     */
    private boolean success;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCompany() {
        return company;
    }

    public void setCompany(List<String> company) {
        this.company = company;
    }

    public List<String> getDepartment() {
        return department;
    }

    public void setDepartment(List<String> department) {
        this.department = department;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getTel_cell() {
        return tel_cell;
    }

    public void setTel_cell(List<String> tel_cell) {
        this.tel_cell = tel_cell;
    }

    public List<String> getTel_work() {
        return tel_work;
    }

    public void setTel_work(List<String> tel_work) {
        this.tel_work = tel_work;
    }

    public List<String> getAddr() {
        return addr;
    }

    public void setAddr(List<String> addr) {
        this.addr = addr;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}