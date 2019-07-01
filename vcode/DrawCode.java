package com.cgmcomm.utils.vcode;

import com.alibaba.fastjson.JSON;
import com.cgmcomm.cgmClient.config.DrawCodeConfig;
import com.cgmcomm.cgmClient.utils.LogUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.math.RandomUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @auther Macro
 * @date 2019/6/20 16:12
 * @Description 拼图验证码生成
 */
public class DrawCode {

    private int tailoring_w = DrawCodeConfig.INSTANCE.getTailoringW(); //小图的宽
    private int tailoring_h = DrawCodeConfig.INSTANCE.getTailoringH(); //小图的高

    private int location_x = 0; //随机X位置
    private int location_y = 0; //随机Y位置

    //生成图片名称
    private String picSuffix = ".png";

    //拼图小方块网络访问地址
    private static  String imgUrl = DrawCodeConfig.INSTANCE.getImgUrl();
    //拼图小方块临时存放位置
    private static String imgPath = DrawCodeConfig.INSTANCE.getImgPath();
    //拼图图库位置
    private static String sourceImgPath = DrawCodeConfig.INSTANCE.getSourceImgPath();

    private static final int shadowWidth = 4; //阴影宽度
    private static final int lightHeightWidth = 5; //图片边缘亮色（黄色）宽度。

    private static final int arc = 10; //圆弧直径


    public DrawCode() {
    }

    /**
     * 创建拼图
     *
     * @param havingfilename 当前拼图原始图片名
     * @return 返回原始图片，裁剪后的大图片和移动小图片，以及y轴偏移量
     * @throws IOException
     */
    public Map<String, String> create(String havingfilename){
        try {
            //本地原始图片路径,
            File file = new File(sourceImgPath);
            String[] list = file.list();
            String filename;
            //获取随机图片， 每次获取到的图片与已有的图片要不同。
            while (true) {
                int randowval = RandomUtils.nextInt(list.length);
                filename = list[randowval];
                if (!filename.equals(havingfilename)) {
                    break;
                }
            }
            File sourceFile = new File(sourceImgPath + File.separator + filename);
            //从原始图片中随机截取小图，同时处理背景大图
            //result={"smallImgName":"small_12_55_3def184ad8f4755f.png",//小拼图
                //"bigImgName":"big_12_55_3def184ad8f4755f.png", //大拼图
                // "sourceImgName":"12.png", //原始图片
                // "location_y":"55"  //小拼图y轴偏移量}
            Map<String, String> result = createImg(sourceFile, filename);
            //将x 轴位置作为验证码(业务代码中存储以后需要清空，不需要返回前端)
            result.put("location_x",String.valueOf(location_x));
            System.out.println("result="+ JSON.toJSONString(result));
            return result;
        }catch (Exception e){
            LogUtils.getLogger().error("[API] 生成拼图create方法异常===》ERROR:{}",e);
            return null;
        }
    }

    /**
     * 对图片进行裁剪
     *
     * @param file 图片
     * @param x    裁剪图左上方X位置
     * @param y    裁剪图左上方Y位置
     * @param w    裁剪的宽
     * @param h    裁剪的宽
     * @return 裁剪之后的图片Buffered
     * @throws IOException
     */
    private static BufferedImage cutImg(File file, int x, int y, int w, int h){
        BufferedImage bufferedImage = null;
        ImageInputStream in = null;
        try {
            Iterator iterator = ImageIO.getImageReadersByFormatName("png");
            ImageReader render = (ImageReader) iterator.next();
            in = ImageIO.createImageInputStream(new FileInputStream(file));
            render.setInput(in, true);
            ImageReadParam param = render.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, w, h);
            param.setSourceRegion(rect);
            bufferedImage = render.read(0, param);
        }catch (Exception e){
            LogUtils.getLogger().error("[API] 生成拼图验证码，裁剪图片BufferedImage方法异常===》ERROR:{}",e);
        }finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LogUtils.getLogger().error("[API] 生成拼图验证码，裁剪图片BufferedImage方法异常===》ERROR:{}",e);
                }
            }
        }
        return bufferedImage;
    }

    private String createBigImg(BufferedImage smllImage, File file, String filename) throws IOException {
        //创建一个灰度化图层， 将生成的小图，覆盖到该图层，使其灰度化，用于作为一个水印图
        String bigImgName = randomImgName("big_" + filename.replaceAll(".png", "") + "_");
        //如果大图不存在，那么就创建
        File bigfile = new File(imgPath + File.separator + bigImgName);
        if (!bigfile.exists()) {
            //将灰度化之后的图片，整合到原有图片上
            BufferedImage bigImg = addWatermark(file, smllImage, 0.6F);
            ImageIO.write(bigImg, "png", bigfile);
        }
        return bigImgName;
    }

    /**
     * 添加水印
     * @param file
     * @param smallImage
     */
    private BufferedImage addWatermark(File file, BufferedImage smallImage, float alpha) throws IOException {
        BufferedImage source = ImageIO.read(file);
        Graphics2D graphics2D = source.createGraphics();
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
        graphics2D.drawImage(smallImage, location_x, location_y, null);
        graphics2D.dispose(); //释放
        return source;
    }

    private String randomImgName(String suf) {
        //按照坐标位生成图片
        return suf + location_y + "_" + DigestUtils.md5Hex(String.valueOf(location_x)).substring(0, 16) + picSuffix;
    }

    private Map<String, String> createImg(File file, String filename){
        try {
            BufferedImage sourceBuff = ImageIO.read(file);
            int width = sourceBuff.getWidth();
            int height = sourceBuff.getHeight();
            //生成随机x，y
            Random random = new Random();
            //X轴距离右端tailoring_w 以上）  Y轴距离底部tailoring_y以上
            this.location_x = random.nextInt(width - tailoring_w * 2) + tailoring_w;
            this.location_y = random.nextInt(height - tailoring_h);
            //裁剪小图
            BufferedImage sourceSmall = cutImg(file, location_x, location_y, tailoring_w, tailoring_h);
            //创建shape区域
            List<Shape> shapes = createSmallShape();
            Shape area = shapes.get(0);
            Shape bigarea = shapes.get(1);
            //创建图层用于处理小图的阴影
            BufferedImage bfm1 = new BufferedImage(tailoring_w, tailoring_h, BufferedImage.TYPE_INT_ARGB);
            //创建图层用于处理大图的凹槽
            BufferedImage bfm2 = new BufferedImage(tailoring_w, tailoring_h, BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < tailoring_w; i++) {
                for (int j = 0; j < tailoring_h; j++) {
                    if (area.contains(i, j)) {
                        bfm1.setRGB(i, j, sourceSmall.getRGB(i, j));
                    }
                    if (bigarea.contains(i, j)) {
                        bfm2.setRGB(i, j, Color.black.getRGB());
                    }
                }
            }
            //处理图片的边缘高亮及其阴影效果
            BufferedImage resultImgBuff = dealLightAndShadow(bfm1, area);
            //生成随机名称
            String smallFileName = randomImgName("small_" + filename.replaceAll(".png", "") + "_");
            File smallfile = new File(imgPath + File.separator + smallFileName);
            if (!smallfile.exists()) { //因为根据坐标点生成小图，如果已经存在，那么就不需要重复创建，直接使用
                ImageIO.write(resultImgBuff, "png", smallfile);
            }
            Map<String, String> result = new HashMap<String, String>();
            result.put("smallImgName", imgUrl+File.separator+smallFileName);
            //将灰色图当做水印印到原图上
            String bigImgName = createBigImg(bfm2, new File(sourceImgPath + File.separator + filename), filename);
            result.put("bigImgName", imgUrl+File.separator+bigImgName);
            result.put("location_y", String.valueOf(location_y));
            result.put("sourceImgName", filename);
            return result;
        }catch (Exception e){
            LogUtils.getLogger().error("[API] 生成拼图验证码，createImg方法异常===》ERROR:{}",e);
            return null;
        }
    }

    private java.util.List<Shape> createSmallShape() {
        //处理小图，在4个方向上 随机找到2个方向添加凸出
        int face1 = RandomUtils.nextInt(3); //凸出1
        int face2; //凸出2
        //使凸出1 与 凸出2不在同一个方向
        while (true) {
            face2 = RandomUtils.nextInt(3);
            if (face1 != face2) {
                break;
            }
        }
        //生成随机区域值， （10-20）之间
        int position1 = RandomUtils.nextInt((tailoring_h - arc * 2) / 2) + (tailoring_h - arc * 2)/2;
        Shape shape1 = createShape(face1, 0, position1);
        Shape bigshape1 = createShape(face1, 2, position1);

        //生成中间正方体Shape, (具体边界+弧半径 = x坐标位)
        Shape centre = new Rectangle2D.Float(arc, arc, tailoring_w - 2 * 10, tailoring_h - 2 * 10);
        int position2 = RandomUtils.nextInt((tailoring_h - arc * 2) / 2) + (tailoring_h - arc * 2)/2;
        Shape shape2 = createShape(face2, 0, position2);

        //因为后边图形需要生成阴影， 所以生成的小图shape + 阴影宽度 = 灰度化的背景小图shape（即大图上的凹槽）
        Shape bigshape2 = createShape(face2, shadowWidth / 2, position2);
        Shape bigcentre = new Rectangle2D.Float(10 - shadowWidth / 2, 10 - shadowWidth / 2, 30 + shadowWidth, 30 + shadowWidth);

        //合并Shape
        Area area = new Area(centre);
        area.add(new Area(shape1));
        area.add(new Area(shape2));
        //合并大Shape
        Area bigarea = new Area(bigcentre);
        bigarea.add(new Area(bigshape1));
        bigarea.add(new Area(bigshape2));
        List<Shape> list = new ArrayList<Shape>();
        list.add(area);
        list.add(bigarea);
        return list;
    }


    //处理小图的边缘灯光及其阴影效果
    private BufferedImage dealLightAndShadow(BufferedImage bfm, Shape shape) throws IOException {
        //创建新的透明图层，该图层用于边缘化阴影， 将生成的小图合并到该图上
        BufferedImage buffimg = ((Graphics2D) bfm.getGraphics()).getDeviceConfiguration().createCompatibleImage(50, 50, Transparency.TRANSLUCENT);
        Graphics2D graphics2D = buffimg.createGraphics();
        Graphics2D g2 = (Graphics2D) bfm.getGraphics();
        //原有小图，边缘亮色处理
        paintBorderGlow(g2, lightHeightWidth, shape);
        //新图层添加阴影
        paintBorderShadow(graphics2D, shadowWidth, shape);
        graphics2D.drawImage(bfm, 0, 0, null);
        return buffimg;
    }

    /**
     * 处理阴影
     * @param g2
     * @param shadowWidth
     * @param clipShape
     */
    private void paintBorderShadow(Graphics2D g2, int shadowWidth, Shape clipShape) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int sw = shadowWidth * 2;
        for (int i = sw; i >= 2; i -= 2) {
            float pct = (float) (sw - i) / (sw - 1);
            //pct<03. 用于去掉阴影边缘白边，  pct>0.8用于去掉过深的色彩， 如果使用Color.lightGray. 可去掉pct>0.8
            if (pct < 0.3 || pct > 0.8) {
                continue;
            }
            g2.setColor(getMixedColor(new Color(54, 54, 54), pct, Color.WHITE, 1.0f - pct));
            g2.setStroke(new BasicStroke(i));
            g2.draw(clipShape);
        }
    }

    private static final Color clrGlowInnerHi = new Color(253, 239, 175, 148);
    private static final Color clrGlowInnerLo = new Color(255, 209, 0);
    private static final Color clrGlowOuterHi = new Color(253, 239, 175, 124);
    private static final Color clrGlowOuterLo = new Color(255, 179, 0);

    /**
     * 处理边缘亮色
     * @param g2
     * @param glowWidth
     * @param clipShape
     */
    public void paintBorderGlow(Graphics2D g2, int glowWidth, Shape clipShape) {
        int gw = glowWidth * 2;
        for (int i = gw; i >= 2; i -= 2) {
            float pct = (float) (gw - i) / (gw - 1);
            Color mixHi = getMixedColor(clrGlowInnerHi, pct, clrGlowOuterHi, 1.0f - pct);
            Color mixLo = getMixedColor(clrGlowInnerLo, pct, clrGlowOuterLo, 1.0f - pct);
            g2.setPaint(new GradientPaint(0.0f, 35 * 0.25f, mixHi, 0.0f, 35, mixLo));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, pct));
            g2.setStroke(new BasicStroke(i));
            g2.draw(clipShape);
        }
    }

    private static Color getMixedColor(Color c1, float pct1, Color c2, float pct2) {
        float[] clr1 = c1.getComponents(null);
        float[] clr2 = c2.getComponents(null);
        for (int i = 0; i < clr1.length; i++) {
            clr1[i] = (clr1[i] * pct1) + (clr2[i] * pct2);
        }
        return new Color(clr1[0], clr1[1], clr1[2], clr1[3]);
    }

    //创建圆形区域, 半径为5  type , 0：上方，1：右方 2：下方，3：左方
    private Shape createShape(int type, int size, int position) {
        Arc2D.Float d;
        if (type == 0) {
            //上
            d = new Arc2D.Float(position, 5, 10 + size, 10 + size, 0, 190, Arc2D.CHORD);
        } else if (type == 1) {
            //右
            d = new Arc2D.Float(35, position, 10 + size, 10 + size, 270, 190, Arc2D.CHORD);
        } else if (type == 2) {
            //下
            d = new Arc2D.Float(position, 35, 10 + size, 10 + size, 180, 190, Arc2D.CHORD);
        } else if (type == 3) {
            //左
            d = new Arc2D.Float(5, position, 10 + size, 10 + size, 90, 190, Arc2D.CHORD);
        } else {
            d = new Arc2D.Float(5, position, 10 + size, 10 + size, 90, 190, Arc2D.CHORD);
        }
        return d;
    }

}
