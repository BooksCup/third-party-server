package com.bc.third.party.server.utils;


import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.printer.Template1FormatData;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 表格表单
 *
 * @author zhou
 */
public class TextForm {

    /**
     * 左边距
     */
    protected int paddingL = 1;

    /**
     * 右边距
     */
    protected int paddingR = 1;

    /**
     * 标题
     */
    protected List<String> title = new ArrayList<>();

    /**
     * 数据
     */
    protected List<List<String>> datas = new ArrayList<>();

    /**
     * 最大列数
     */
    protected int maxCol = 0;

    /**
     * 每个单元格最大字符数
     */
    protected int colMaxLength = 8;

    /**
     * 表格组成符号
     */
    protected char separator = '.';

    private TextForm() {
    }

    public static TextFormBulider bulider() {
        return new TextFormBulider(new TextForm());
    }


    /**
     *
     */
    public Template1FormatData getTableList() {
        Template1FormatData template1FormatData = new Template1FormatData();

        List<String> dataList = new ArrayList<>();
        List<List<String>> formData = new ArrayList<>();
        formData.addAll(datas);
        Map<Integer, Integer> colMaxLengthMap = colMaxLength(formData);
        for (int i = 0; i < formData.size(); i++) {
            List<String> row = formData.get(i);
            for (int j = 0; j < row.size(); j++) {
                Formatter formatter = new Formatter();
                String str = row.get(j);
                if (str.length() > colMaxLength) {
                    str = str.substring(0, colMaxLength);
                }
                int chineseNum = getChineseNum(str);
                Integer maxLength = colMaxLengthMap.get(j);
                String val = formatter.format("%-" + (maxLength - chineseNum) + "s", str).toString();
                row.set(j, val);
            }
        }
        Map<Integer, Integer> colMinBlankLengthMap = colMinBlankLength(formData);
        for (int i = 0; i < formData.size(); i++) {
            List<String> row = formData.get(i);
            for (int j = 0; j < row.size(); j++) {
                String val = row.get(j);
                Integer minBlankLength = colMinBlankLengthMap.get(j);
                val = val.substring(0, val.length() - minBlankLength);
                row.set(j, val);
            }
        }
        String line = "";
        List<String> rows = new ArrayList<>();
        for (List<String> strings : formData) {
            String pL = StringUtils.repeat(" ", paddingL);
            String pR = StringUtils.repeat(" ", paddingR);
            String row = separator + pL + String.join(pL + separator + pR, strings) + pR + separator;
            if (line.length() < row.length()) {
                line = StringUtils.repeat("-", row.length());
            }
            rows.add(row);
        }
        for (String row : rows) {
            dataList.add(row);
        }

        if (!CollectionUtils.isEmpty(dataList)) {
            template1FormatData.setEmptyData(dataList.get(dataList.size() - 1));
            dataList.remove(dataList.size() - 1);
        }

        template1FormatData.setDataList(dataList);
        template1FormatData.setSeparator(line);

        return template1FormatData;
    }

    /**
     * 找到每一列最大的长度
     *
     * @param formData
     * @return
     */
    private Map<Integer, Integer> colMaxLength(List<List<String>> formData) {
        Map<Integer, Integer> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        for (int i = 0; i < formData.size(); i++) {
            int col = 0;
            List<String> strings = formData.get(i);
            while (strings.size() > col) {
                String val = strings.get(col);
                if (val.length() > colMaxLength) {
                    val = val.substring(0, colMaxLength);
                    strings.set(col, val);
                }
                int length = val.getBytes().length;
                Integer integer = map.get(col);
                if (integer == null) {
                    map.put(col, length);
                } else {
                    if (integer < length) {
                        map.put(col, length);
                    }
                }
                col++;
            }
        }
        return map;
    }

    /**
     * 找到每一列从右开始最小的空格长度
     *
     * @param formData
     * @return
     */
    private Map<Integer, Integer> colMinBlankLength(List<List<String>> formData) {
        Map<Integer, Integer> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        for (int i = 0; i < formData.size(); i++) {
            int col = 0;
            List<String> strings = formData.get(i);
            while (strings.size() > col) {
                String val = strings.get(col);
                int length = 0;
                for (int i1 = val.length() - 1; i1 >= 0; i1--) {
                    if (val.charAt(i1) == ' ') {
                        length++;
                    } else {
                        break;
                    }
                }
                Integer integer = map.get(col);
                if (integer == null) {
                    map.put(col, length);
                } else {
                    if (integer > length) {
                        map.put(col, length);
                    }
                }
                col++;
            }
        }
        return map;
    }

    /**
     * 获取中文数量
     *
     * @param val
     * @return
     */
    private int getChineseNum(String val) {
        if (val == null) {
            val = "null";
        }
        String regex = "[\u4e00-\u9fa5|。|，]";
        ArrayList<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(val);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        int size = list.size();
        return size;
    }
}
