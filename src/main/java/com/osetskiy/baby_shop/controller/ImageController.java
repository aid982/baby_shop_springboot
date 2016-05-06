package com.osetskiy.baby_shop.controller;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osetskiy.baby_shop.REPO.ProductRepository;
import com.osetskiy.baby_shop.model.Product;

@Controller
public class ImageController {
	@Autowired
	ServletContext context;
	ProductRepository productRepository;

	@Autowired
	public ImageController(ProductRepository productRepository) {
		this.productRepository = productRepository;

	}

	public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();

		double scaleX = (double) width / imageWidth;
		double scaleY = (double) height / imageHeight;
		AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
		AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BICUBIC);

		return bilinearScaleOp.filter(image, new BufferedImage(width, height, image.getType()));
	}

	@RequestMapping(value = "/prodIMG/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage(@PathVariable("id") String productId, @RequestParam Integer size)
			throws IOException {
		InputStream in = context.getResourceAsStream("/resources/images/_" + productId + "_large.jpg");
		BufferedImage image = ImageIO.read(in);
		BufferedImage imageSacled = getScaledImage(image, size, size);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(imageSacled, "jpg", baos);
		return baos.toByteArray();

	}

	@RequestMapping(value = "/productImage/{id}/{size}")
	public String getImage2(@PathVariable("id") String productId, @PathVariable("size") String size) {
		if(size.equals("normal")){
			size ="";			
		} else
		{
			size ="_"+size;			
		}
		return "redirect:/resources/images/_" + productId + size + ".jpg";

	}

	@RequestMapping(value = "/prodIMG_post", method = RequestMethod.POST)
	public void putImage() throws IOException {

		List<Product> productList = (List<Product>) productRepository.findAll();
		for (Product prod : productList) {
			File imageLarge = new File(context.getRealPath("/resources/images/_" + prod.getId() + "_large.jpg"));
			if (imageLarge.exists()) {
				File imageSmall = new File(context.getRealPath("/resources/images/_" + prod.getId() + "_small.jpg"));
				File image = new File(context.getRealPath("/resources/images/_" + prod.getId() + ".jpg"));
				if (!image.exists()) {
					CreateScaledImages(imageLarge, image, 470, 470);
				}

				if (!imageSmall.exists()) {
					CreateScaledImages(imageLarge, imageSmall, 180, 180);
				}
			}
		}

	}

	public void CreateScaledImages(File fileSource, File fileOutPut, Integer sizeX, Integer sizeY) throws IOException {
		FileInputStream fi = new FileInputStream(fileSource);
		BufferedImage image = ImageIO.read(fi);
		if (image != null) {
			BufferedImage imageSacled = getScaledImage(image, sizeX, sizeY);
			FileOutputStream fo = new FileOutputStream(fileOutPut);
			ImageIO.write(imageSacled, "jpg", fo);
			fo.flush();
			fo.close();
		}

	}

}
