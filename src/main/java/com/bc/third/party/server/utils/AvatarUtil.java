package com.bc.third.party.server.utils;

import com.bc.third.party.server.entity.config.OssConfig;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * 头像工具类
 *
 * @author zhou
 */
public class AvatarUtil {

    private static final int TEXT_SIZE = 34;
    private static final int OFFSET = 4;

    /**
     * 生成头像并上传至oss
     *
     * @param name
     * @param width
     * @param height
     * @param cornerRadius
     * @param ossConfig
     * @return
     * @throws IOException
     */
    public static String generateImageAndUploadOss(String name, int width,
                                                   int height, int cornerRadius, OssConfig ossConfig) throws IOException {
        byte[] bytes = generateImg(name, width, height, cornerRadius);
        // 取得当前上传文件的文件名称
        String fileName = CommonUtil.generateId() + ".png";
        // 上传到oss
        String result = OssUtil.uploadFile(bytes, fileName, ossConfig);
        if (StringUtils.isNotEmpty(result)) {
            fileName = ossConfig.getDomain() + fileName;
        }
        return fileName;
    }

    /**
     * 绘制字体头像 生成二进制数组
     * 如果是英文名，只显示首字母大写
     * 如果是中文名，只显示最后两个字
     *
     * @param name         放到图片中的名称
     * @param width        图片宽度
     * @param height       图片高度
     * @param cornerRadius 锐化值，锐化值越大，生成的图片趋于圆
     * @throws IOException
     */
    public static byte[] generateImg(String name, int width, int height, int cornerRadius)
            throws IOException {
        BufferedImage rounded = commonGenerateImg(name, width, height, 0);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(rounded, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 绘制字体头像
     * 如果是英文名，只显示首字母大写
     * 如果是中文名，只显示最后两个字
     *
     * @param name         放到图片中的名称
     * @param width        图片宽度
     * @param height       图片高度
     * @param cornerRadius 锐化值，锐化值越大，生成的图片趋于圆
     * @return
     */
    public static BufferedImage commonGenerateImg(String name, int width, int height, int cornerRadius) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setBackground(getRandomColor());
        g2.clearRect(0, 0, width, height);
        Font font = null;
        if (name.length() >= 4) {
            font = new Font("微软雅黑", Font.PLAIN, 35);
            g2.setFont(font);

            FontMetrics fm = g2.getFontMetrics();
            // 画出水印
            String[] words = new String[2];

            words[0] = String.valueOf(name.charAt(0)) + String.valueOf(name.charAt(1));
            words[1] = String.valueOf(name.charAt(2)) + String.valueOf(name.charAt(3));
            g2.drawString(words[0], (width - fm.stringWidth(words[0])) / 2, height / 2 - OFFSET);
            g2.drawString(words[1], (width - fm.stringWidth(words[1])) / 2, height / 2 + TEXT_SIZE - OFFSET);
        }
        if (name.length() == 3) {
            font = new Font("微软雅黑", Font.PLAIN, 35);
            g2.setFont(font);

            FontMetrics fm = g2.getFontMetrics();
            // 画出水印
            String[] words = new String[2];
            words[0] = String.valueOf(name.charAt(0)) + String.valueOf(name.charAt(1));
            words[1] = String.valueOf(name.charAt(2));
            g2.drawString(words[0], (width - fm.stringWidth(words[0])) / 2, height / 2 - OFFSET);
            g2.drawString(words[1], (width - TEXT_SIZE) / 2, height / 2 + TEXT_SIZE - OFFSET);
        }
        if (name.length() == 2) {
            font = new Font("微软雅黑", Font.PLAIN, 35);
            g2.setFont(font);

            FontMetrics fm = g2.getFontMetrics();
            // 画出水印
            g2.drawString(name, (width - fm.stringWidth(name)) / 2, height / 2 + TEXT_SIZE / 2 - OFFSET);
        }
        if (name.length() == 1) {
            font = new Font("微软雅黑", Font.PLAIN, 20);
            g2.setFont(font);

            FontMetrics fm = g2.getFontMetrics();
            //画出水印
            g2.drawString(name, (width - fm.stringWidth(name)) / 2, (height / 2 + 5));
        }
        BufferedImage rounded = makeRoundedCorner(bi, cornerRadius);
        return rounded;
    }

    /**
     * 获得随机颜色
     *
     * @return
     */
    private static Color getRandomColor() {
//        String[] beautifulColors =
//                new String[]{"232,221,203", "205,179,128", "3,101,100", "3,54,73", "3,22,52", "237,222,139",
//                        "251,178,23", "96,143,159", "1,77,103", "254,67,101", "252,157,154", "249,205,173",
//                        "200,200,169", "131,175,155", "229,187,129", "161,23,21", "34,8,7", "118,77,57",
//                        "17,63,61", "60,79,57", "95,92,51", "179,214,110", "248,147,29", "227,160,93",
//                        "178,190,126", "114,111,238", "56,13,49", "89,61,67", "250,218,141", "3,38,58",
//                        "179,168,150", "222,125,44", "20,68,106", "130,57,53", "137,190,178", "201,186,131",
//                        "222,211,140", "222,156,83", "23,44,60", "39,72,98", "153,80,84", "217,104,49",
//                        "230,179,61", "174,221,129", "107,194,53", "6,128,67", "38,157,128", "178,200,187",
//                        "69,137,148", "117,121,71", "114,83,52", "87,105,60", "82,75,46", "171,92,37",
//                        "100,107,48", "98,65,24", "54,37,17", "137,157,192", "250,227,113", "29,131,8",
//                        "220,87,18", "29,191,151", "35,235,185", "213,26,33", "160,191,124", "101,147,74",
//                        "64,116,52", "255,150,128", "255,94,72", "38,188,213", "167,220,224", "1,165,175",
//                        "179,214,110", "248,147,29", "230,155,3", "209,73,78", "62,188,202", "224,160,158",
//                        "161,47,47", "0,90,171", "107,194,53", "174,221,129", "6,128,67", "38,157,128",
//                        "201,138,131", "220,162,151", "137,157,192", "175,215,237", "92,167,186",
//                        "255,66,93", "147,224,255", "247,68,97", "185,227,217"};
        String[] colors = new String[]{
                "124,180,216",
                "94,207,244",
                "191,156,221",
                "209,175,157",
        };
        int len = colors.length;
        Random random = new Random();
        String[] color = colors[random.nextInt(len)].split(",");
        return new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]),
                Integer.parseInt(color[2]));
    }


    /**
     * 图片做圆角处理
     *
     * @param image
     * @param cornerRadius
     * @return
     */
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    }

}
