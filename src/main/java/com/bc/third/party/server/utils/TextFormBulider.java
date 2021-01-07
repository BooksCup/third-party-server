package com.bc.third.party.server.utils;

import java.util.ArrayList;
import java.util.List;

public class TextFormBulider {
    private TextForm textForm;

    protected TextFormBulider(TextForm textForm) {
        this.textForm = textForm;
    }

    public TextFormBulider title(String... titles) {
        if (textForm.maxCol < titles.length) {
            textForm.maxCol = titles.length;
        }
        for (String title : titles) {
            if (title == null) {
                title = "null";
            }
            textForm.title.add(title);
        }
        return this;
    }

    public TextFormBulider paddingL(int paddingL) {
        textForm.paddingL = paddingL;
        return this;
    }

    public TextFormBulider paddingR(int paddingR) {
        textForm.paddingR = paddingR;
        return this;
    }

    public TextFormBulider separator(char separator) {
        textForm.separator = separator;
        return this;
    }

    public TextFormBulider colMaxLength(int colMaxLength) {
        textForm.colMaxLength = colMaxLength;
        return this;
    }

    public TextFormBulider addRow(String... cols) {
        if (textForm.maxCol < cols.length) {
            textForm.maxCol = cols.length;
        }
        List<String> list = new ArrayList<>(cols.length);
        for (String col : cols) {
            if (col == null) {
                col = "null";
            }
            list.add(col);
        }
        textForm.datas.add(list);
        return this;
    }

    public TextForm finish() {
        int titleSize = textForm.title.size();
        if (titleSize < textForm.maxCol) {
            for (int i = 0; i < textForm.maxCol - titleSize; i++) {
                textForm.title.add(null);
            }
        }
        for (List<String> data : textForm.datas) {
            int dataSize = data.size();
            if (dataSize < textForm.maxCol) {
                for (int i = 0; i < textForm.maxCol - dataSize; i++) {
                    data.add(null);
                }
            }
        }
        return textForm;
    }

//    public static void main(String[] args) {
//        Map<String, List<String>> resultMap = TextForm.bulider()
//                .title("面料号FABRIC#", "E1863")//设置标题
//                .addRow("款号STYLE", "D4183")//添加行
//                .addRow("订单号PO#", "S21121")//添加行
//                .addRow("颜色号COL#", "BLACK#148黑色")//添加行
//                .addRow("缸号LOT#", "201120111DF")//添加行
//                .addRow("卷号ROLL#", "1")//添加行
//                .addRow("公斤KG", "30.8KG")//添加行
//                .addRow("米数METER", "59.5M")//添加行
//                .addRow("备注REMARK", "泰安欣怡制衣")//添加行
//                .addRow(" ", " ")//添加行
//                .colMaxLength(7)//设置单元格最大数据长度
//                .separator('|')//设置表格由什么符号构成
//                .paddingR(2)//右边距
//                .paddingL(2)//左边距
//                .finish()//完成
//                .getTableList();//打印}
//        List<String> tableList = resultMap.get("tableList");
//        List<String> separatorList = resultMap.get("separatorList");
//
//        for (String table : tableList) {
//            System.out.println(table);
//        }
//
//        System.out.println(separatorList.get(0));
//    }
}
