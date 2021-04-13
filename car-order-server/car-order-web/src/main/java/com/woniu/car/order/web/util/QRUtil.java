package com.woniu.car.order.web.util;

import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;



public class QRUtil {
	/**
	   *    二维码添加logo
	 * @param matrixImage   源二维码图片
	 * @param logoFile      logo图片
	 * @param url           logo图片的网络地址
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage LogoMatrix(BufferedImage matrixImage, File logoFile,String url) throws IOException {
		//读取二维码图片，并构建绘图对象
		Graphics2D g2 = matrixImage.createGraphics();

		int matrixWidth = matrixImage.getWidth();
		int matrixHeigh = matrixImage.getHeight();
	
		BufferedImage logo = null;
		
		//读取Logo图片
		if(url == null) logo = ImageIO.read(logoFile);
		else logo = ImageIO.read(new URL(url));

		// 开始绘制图片
		g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);// 绘制
		BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		// 设置笔画对象
		g2.setStroke(stroke);
		// 指定弧度的圆角矩形
		RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2,
				matrixWidth / 5, matrixHeigh / 5, 20, 20);
		g2.setColor(Color.white);
		// 绘制圆弧矩形
		g2.draw(round);

		//设置logo 有一道灰色边框
		BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		//设置笔画对象
		g2.setStroke(stroke2);
		RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeigh / 5 * 2 + 2,
				matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 20, 20);
		g2.setColor(new Color(128, 128, 128));
		//绘制圆弧矩形
		g2.draw(round2);

		g2.dispose();
		matrixImage.flush();
		return matrixImage;
	}

	/**
	   *    生成二维码
	 * @param content     二维码内容
	 * @param logFile  二维码logo图片
	 * @param format   二维码图片类型，例如"gif"
	 * @return         二维码输入流
	 * @throws IOException    
	 * @throws WriterException
	 */
	public static InputStream createQR(String content,String format,File logFile) throws IOException, WriterException {
		int width = 200; 
		int height = 200; 

		Map<EncodeHintType, Object> hints = new HashMap<>();
		//内容编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		//指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		//设置二维码边的空度
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
		
		//设置logo
		BufferedImage bufferedImage = LogoMatrix(MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig),
				logFile,null);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, format, baos);
		InputStream ist = new ByteArrayInputStream(baos.toByteArray());
		
		//ImageIO.write(bufferedImage, format, new File("D:\\zxing1.gif"));// 输出带logo图片
		return ist;
	}
	
	/**
	   *    生成二维码
	 * @param body     二维码内容
	 * @param url      二维码logo图片网络地址
	 * @param type     二维码图片类型，例如"gif"
	 * @return         二维码输入流
	 * @throws IOException    
	 * @throws WriterException
	 */
	public static InputStream createQR(String body,String type,String url) throws IOException, WriterException {
		//二维码内容
		String content = body;
		int width = 200; 
		int height = 200; 
		//图像类型
		String format = type;
		Map<EncodeHintType, Object> hints = new HashMap<>();
		//内容编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		//指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		//设置二维码边的空度
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
		
		//设置logo
		BufferedImage bufferedImage = LogoMatrix(MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig),
				null,url);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, format, baos);
		InputStream ist = new ByteArrayInputStream(baos.toByteArray());
		
		//ImageIO.write(bufferedImage, format, new File("D:\\zxing1.gif"));// 输出带logo图片
		return ist;
	}
	
	
	/**
	   *   识别二维码 
	 * @param file  需要识别的二维码
	 * @throws IOException
	 * @throws NotFoundException
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static String QRReader(File file) throws IOException, NotFoundException {
        MultiFormatReader formatReader = new MultiFormatReader();
        //读取指定的二维码文件
        BufferedImage bufferedImage =ImageIO.read(file);
        BinaryBitmap binaryBitmap= new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
        //定义二维码参数
        Map hints= new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        Result result = formatReader.decode(binaryBitmap, hints);
        //得到相关的二维码信息
        //String type = result.getBarcodeFormat().toString();
        String content = result.getText();
        bufferedImage.flush();
        return content;
    }
}
