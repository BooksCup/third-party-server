package com.bc.third.party.server.entity.printer;

import java.util.List;

/**
 * 格式化数据(模板1)
 *
 * @author zhou
 */
public class Template1FormatData {

    private String separator;
    private String emptyData;
    private List<String> dataList;

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getEmptyData() {
        return emptyData;
    }

    public void setEmptyData(String emptyData) {
        this.emptyData = emptyData;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

}
