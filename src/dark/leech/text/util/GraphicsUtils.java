/*     */ package dark.leech.text.util;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphicsUtils
/*     */ {
/*  85 */   public static final boolean TRANSLUCENT_SUPPORT = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static GraphicsConfiguration getGraphicsConfiguration() {
/*  92 */     return GraphicsEnvironment.getLocalGraphicsEnvironment()
/*  93 */       .getDefaultScreenDevice().getDefaultConfiguration();
/*     */   }
/*     */   
/*     */   private static boolean isHeadless() {
/*  97 */     return GraphicsEnvironment.isHeadless();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage convertToBufferedImage(Image img) {
/* 108 */     BufferedImage buff = createCompatibleTranslucentImage(img
/* 109 */         .getWidth(null), img.getHeight(null));
/* 110 */     Graphics2D g2 = buff.createGraphics();
/*     */     
/*     */     try {
/* 113 */       g2.drawImage(img, 0, 0, (ImageObserver)null);
/*     */     } finally {
/* 115 */       g2.dispose();
/*     */     } 
/*     */     
/* 118 */     return buff;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage createColorModelCompatibleImage(BufferedImage image) {
/* 133 */     ColorModel cm = image.getColorModel();
/* 134 */     return new BufferedImage(cm, cm
/* 135 */         .createCompatibleWritableRaster(image.getWidth(), image
/* 136 */           .getHeight()), cm
/* 137 */         .isAlphaPremultiplied(), null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage createCompatibleImage(BufferedImage image) {
/* 160 */     return createCompatibleImage(image, image.getWidth(), image.getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage createCompatibleImage(BufferedImage image, int width, int height) {
/* 186 */     return isHeadless() ? new BufferedImage(width, height, image
/* 187 */         .getType()) : 
/* 188 */       getGraphicsConfiguration().createCompatibleImage(width, height, image
/* 189 */         .getTransparency());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage createCompatibleImage(int width, int height) {
/* 210 */     return isHeadless() ? new BufferedImage(width, height, 1) : 
/*     */       
/* 212 */       getGraphicsConfiguration().createCompatibleImage(width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage createCompatibleTranslucentImage(int width, int height) {
/* 234 */     return isHeadless() ? new BufferedImage(width, height, 2) : 
/*     */       
/* 236 */       getGraphicsConfiguration().createCompatibleImage(width, height, 3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage loadCompatibleImage(InputStream in) throws IOException {
/* 260 */     BufferedImage image = ImageIO.read(in);
/* 261 */     if (image == null) return null; 
/* 262 */     return toCompatibleImage(image);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage loadCompatibleImage(URL resource) throws IOException {
/* 282 */     BufferedImage image = ImageIO.read(resource);
/* 283 */     return toCompatibleImage(image);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage toCompatibleImage(BufferedImage image) {
/* 304 */     if (isHeadless()) {
/* 305 */       return image;
/*     */     }
/*     */     
/* 308 */     if (image.getColorModel().equals(
/* 309 */         getGraphicsConfiguration().getColorModel())) {
/* 310 */       return image;
/*     */     }
/*     */ 
/*     */     
/* 314 */     BufferedImage compatibleImage = getGraphicsConfiguration().createCompatibleImage(image
/* 315 */         .getWidth(), image.getHeight(), image
/* 316 */         .getTransparency());
/* 317 */     Graphics g = compatibleImage.getGraphics();
/*     */     
/*     */     try {
/* 320 */       g.drawImage(image, 0, 0, null);
/*     */     } finally {
/* 322 */       g.dispose();
/*     */     } 
/*     */     
/* 325 */     return compatibleImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage createThumbnailFast(BufferedImage image, int newSize) {
/* 353 */     int width = image.getWidth();
/* 354 */     int height = image.getHeight();
/*     */     
/* 356 */     if (width > height) {
/* 357 */       if (newSize >= width) {
/* 358 */         throw new IllegalArgumentException("newSize must be lower than the image width");
/*     */       }
/* 360 */       if (newSize <= 0) {
/* 361 */         throw new IllegalArgumentException("newSize must be greater than 0");
/*     */       }
/*     */ 
/*     */       
/* 365 */       float ratio = width / height;
/* 366 */       width = newSize;
/* 367 */       height = (int)(newSize / ratio);
/*     */     } else {
/* 369 */       if (newSize >= height) {
/* 370 */         throw new IllegalArgumentException("newSize must be lower than the image height");
/*     */       }
/* 372 */       if (newSize <= 0) {
/* 373 */         throw new IllegalArgumentException("newSize must be greater than 0");
/*     */       }
/*     */ 
/*     */       
/* 377 */       float ratio = height / width;
/* 378 */       height = newSize;
/* 379 */       width = (int)(newSize / ratio);
/*     */     } 
/*     */     
/* 382 */     BufferedImage temp = createCompatibleImage(image, width, height);
/* 383 */     Graphics2D g2 = temp.createGraphics();
/*     */     
/*     */     try {
/* 386 */       g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/*     */       
/* 388 */       g2.drawImage(image, 0, 0, temp.getWidth(), temp.getHeight(), null);
/*     */     } finally {
/* 390 */       g2.dispose();
/*     */     } 
/*     */     
/* 393 */     return temp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage createThumbnailFast(BufferedImage image, int newWidth, int newHeight) {
/* 420 */     if (newWidth >= image.getWidth() || newHeight >= image
/* 421 */       .getHeight()) {
/* 422 */       throw new IllegalArgumentException("newWidth and newHeight cannot be greater than the image dimensions");
/*     */     }
/*     */     
/* 425 */     if (newWidth <= 0 || newHeight <= 0) {
/* 426 */       throw new IllegalArgumentException("newWidth and newHeight must be greater than 0");
/*     */     }
/*     */ 
/*     */     
/* 430 */     BufferedImage temp = createCompatibleImage(image, newWidth, newHeight);
/* 431 */     Graphics2D g2 = temp.createGraphics();
/*     */     
/*     */     try {
/* 434 */       g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/*     */       
/* 436 */       g2.drawImage(image, 0, 0, temp.getWidth(), temp.getHeight(), null);
/*     */     } finally {
/* 438 */       g2.dispose();
/*     */     } 
/*     */     
/* 441 */     return temp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage createThumbnail(BufferedImage image, int newSize) {
/* 467 */     int width = image.getWidth();
/* 468 */     int height = image.getHeight();
/*     */     
/* 470 */     boolean isTranslucent = (image.getTransparency() != 1);
/* 471 */     boolean isWidthGreater = (width > height);
/*     */     
/* 473 */     if (isWidthGreater) {
/* 474 */       if (newSize >= width) {
/* 475 */         throw new IllegalArgumentException("newSize must be lower than the image width");
/*     */       }
/*     */     }
/* 478 */     else if (newSize >= height) {
/* 479 */       throw new IllegalArgumentException("newSize must be lower than the image height");
/*     */     } 
/*     */ 
/*     */     
/* 483 */     if (newSize <= 0) {
/* 484 */       throw new IllegalArgumentException("newSize must be greater than 0");
/*     */     }
/*     */ 
/*     */     
/* 488 */     float ratioWH = width / height;
/* 489 */     float ratioHW = height / width;
/*     */     
/* 491 */     BufferedImage thumb = image;
/* 492 */     BufferedImage temp = null;
/*     */     
/* 494 */     Graphics2D g2 = null;
/*     */     
/*     */     try {
/* 497 */       int previousWidth = width;
/* 498 */       int previousHeight = height;
/*     */       
/*     */       do {
/* 501 */         if (isWidthGreater) {
/* 502 */           width /= 2;
/* 503 */           if (width < newSize) {
/* 504 */             width = newSize;
/*     */           }
/* 506 */           height = (int)(width / ratioWH);
/*     */         } else {
/* 508 */           height /= 2;
/* 509 */           if (height < newSize) {
/* 510 */             height = newSize;
/*     */           }
/* 512 */           width = (int)(height / ratioHW);
/*     */         } 
/*     */         
/* 515 */         if (temp == null || isTranslucent) {
/* 516 */           if (g2 != null)
/*     */           {
/*     */ 
/*     */             
/* 520 */             g2.dispose();
/*     */           }
/* 522 */           temp = createCompatibleImage(image, width, height);
/* 523 */           g2 = temp.createGraphics();
/* 524 */           g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/*     */         } 
/*     */         
/* 527 */         g2.drawImage(thumb, 0, 0, width, height, 0, 0, previousWidth, previousHeight, null);
/*     */ 
/*     */         
/* 530 */         previousWidth = width;
/* 531 */         previousHeight = height;
/*     */         
/* 533 */         thumb = temp;
/* 534 */       } while (newSize != (isWidthGreater ? width : height));
/*     */     } finally {
/* 536 */       if (g2 != null) {
/* 537 */         g2.dispose();
/*     */       }
/*     */     } 
/*     */     
/* 541 */     if (width != thumb.getWidth() || height != thumb.getHeight()) {
/* 542 */       temp = createCompatibleImage(image, width, height);
/* 543 */       g2 = temp.createGraphics();
/*     */       
/*     */       try {
/* 546 */         g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/*     */         
/* 548 */         g2.drawImage(thumb, 0, 0, width, height, 0, 0, width, height, null);
/*     */       } finally {
/* 550 */         g2.dispose();
/*     */       } 
/*     */       
/* 553 */       thumb = temp;
/*     */     } 
/*     */     
/* 556 */     return thumb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage createThumbnail(BufferedImage image, int newWidth, int newHeight) {
/* 581 */     int width = image.getWidth();
/* 582 */     int height = image.getHeight();
/*     */     
/* 584 */     boolean isTranslucent = (image.getTransparency() != 1);
/*     */     
/* 586 */     if (newWidth >= width || newHeight >= height) {
/* 587 */       throw new IllegalArgumentException("newWidth and newHeight cannot be greater than the image dimensions");
/*     */     }
/*     */     
/* 590 */     if (newWidth <= 0 || newHeight <= 0) {
/* 591 */       throw new IllegalArgumentException("newWidth and newHeight must be greater than 0");
/*     */     }
/*     */ 
/*     */     
/* 595 */     BufferedImage thumb = image;
/* 596 */     BufferedImage temp = null;
/*     */     
/* 598 */     Graphics2D g2 = null;
/*     */     
/*     */     try {
/* 601 */       int previousWidth = width;
/* 602 */       int previousHeight = height;
/*     */       
/*     */       do {
/* 605 */         if (width > newWidth) {
/* 606 */           width /= 2;
/* 607 */           if (width < newWidth) {
/* 608 */             width = newWidth;
/*     */           }
/*     */         } 
/*     */         
/* 612 */         if (height > newHeight) {
/* 613 */           height /= 2;
/* 614 */           if (height < newHeight) {
/* 615 */             height = newHeight;
/*     */           }
/*     */         } 
/*     */         
/* 619 */         if (temp == null || isTranslucent) {
/* 620 */           if (g2 != null)
/*     */           {
/*     */ 
/*     */             
/* 624 */             g2.dispose();
/*     */           }
/* 626 */           temp = createCompatibleImage(image, width, height);
/* 627 */           g2 = temp.createGraphics();
/* 628 */           g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/*     */         } 
/*     */         
/* 631 */         g2.drawImage(thumb, 0, 0, width, height, 0, 0, previousWidth, previousHeight, null);
/*     */ 
/*     */         
/* 634 */         previousWidth = width;
/* 635 */         previousHeight = height;
/*     */         
/* 637 */         thumb = temp;
/* 638 */       } while (width != newWidth || height != newHeight);
/*     */     } finally {
/* 640 */       if (g2 != null) {
/* 641 */         g2.dispose();
/*     */       }
/*     */     } 
/*     */     
/* 645 */     if (width != thumb.getWidth() || height != thumb.getHeight()) {
/* 646 */       temp = createCompatibleImage(image, width, height);
/* 647 */       g2 = temp.createGraphics();
/*     */       
/*     */       try {
/* 650 */         g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/*     */         
/* 652 */         g2.drawImage(thumb, 0, 0, width, height, 0, 0, width, height, null);
/*     */       } finally {
/* 654 */         g2.dispose();
/*     */       } 
/*     */       
/* 657 */       thumb = temp;
/*     */     } 
/*     */     
/* 660 */     return thumb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[] getPixels(BufferedImage img, int x, int y, int w, int h, int[] pixels) {
/* 683 */     if (w == 0 || h == 0) {
/* 684 */       return new int[0];
/*     */     }
/*     */     
/* 687 */     if (pixels == null) {
/* 688 */       pixels = new int[w * h];
/* 689 */     } else if (pixels.length < w * h) {
/* 690 */       throw new IllegalArgumentException("pixels array must have a length >= w*h");
/*     */     } 
/*     */ 
/*     */     
/* 694 */     int imageType = img.getType();
/* 695 */     if (imageType == 2 || imageType == 1) {
/*     */       
/* 697 */       Raster raster = img.getRaster();
/* 698 */       return (int[])raster.getDataElements(x, y, w, h, pixels);
/*     */     } 
/*     */ 
/*     */     
/* 702 */     return img.getRGB(x, y, w, h, pixels, 0, w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setPixels(BufferedImage img, int x, int y, int w, int h, int[] pixels) {
/* 722 */     if (pixels == null || w == 0 || h == 0)
/*     */       return; 
/* 724 */     if (pixels.length < w * h) {
/* 725 */       throw new IllegalArgumentException("pixels array must have a length >= w*h");
/*     */     }
/*     */ 
/*     */     
/* 729 */     int imageType = img.getType();
/* 730 */     if (imageType == 2 || imageType == 1) {
/*     */       
/* 732 */       WritableRaster raster = img.getRaster();
/* 733 */       raster.setDataElements(x, y, w, h, pixels);
/*     */     } else {
/*     */       
/* 736 */       img.setRGB(x, y, w, h, pixels, 0, w);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clear(Image img) {
/* 747 */     Graphics g = img.getGraphics();
/*     */     
/*     */     try {
/* 750 */       if (g instanceof Graphics2D) {
/* 751 */         ((Graphics2D)g).setComposite(AlphaComposite.Clear);
/*     */       } else {
/* 753 */         g.setColor(new Color(0, 0, 0, 0));
/*     */       } 
/*     */       
/* 756 */       g.fillRect(0, 0, img.getWidth(null), img.getHeight(null));
/*     */     } finally {
/* 758 */       g.dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void tileStretchPaint(Graphics g, JComponent comp, BufferedImage img, Insets ins) {
/* 771 */     int left = ins.left;
/* 772 */     int right = ins.right;
/* 773 */     int top = ins.top;
/* 774 */     int bottom = ins.bottom;
/*     */ 
/*     */     
/* 777 */     g.drawImage(img, 0, 0, left, top, 0, 0, left, top, null);
/*     */ 
/*     */ 
/*     */     
/* 781 */     g.drawImage(img, left, 0, comp
/*     */         
/* 783 */         .getWidth() - right, top, left, 0, img
/*     */         
/* 785 */         .getWidth() - right, top, null);
/*     */     
/* 787 */     g.drawImage(img, comp
/* 788 */         .getWidth() - right, 0, comp
/* 789 */         .getWidth(), top, img
/* 790 */         .getWidth() - right, 0, img
/* 791 */         .getWidth(), top, null);
/*     */ 
/*     */ 
/*     */     
/* 795 */     g.drawImage(img, 0, top, left, comp
/*     */         
/* 797 */         .getHeight() - bottom, 0, top, left, img
/*     */         
/* 799 */         .getHeight() - bottom, null);
/*     */ 
/*     */     
/* 802 */     g.drawImage(img, left, top, comp
/*     */         
/* 804 */         .getWidth() - right, comp.getHeight() - bottom, left, top, img
/*     */         
/* 806 */         .getWidth() - right, img.getHeight() - bottom, null);
/*     */ 
/*     */     
/* 809 */     g.drawImage(img, comp
/* 810 */         .getWidth() - right, top, comp
/* 811 */         .getWidth(), comp.getHeight() - bottom, img
/* 812 */         .getWidth() - right, top, img
/* 813 */         .getWidth(), img.getHeight() - bottom, null);
/*     */ 
/*     */ 
/*     */     
/* 817 */     g.drawImage(img, 0, comp
/* 818 */         .getHeight() - bottom, left, comp
/* 819 */         .getHeight(), 0, img
/* 820 */         .getHeight() - bottom, left, img
/* 821 */         .getHeight(), null);
/*     */     
/* 823 */     g.drawImage(img, left, comp
/* 824 */         .getHeight() - bottom, comp
/* 825 */         .getWidth() - right, comp.getHeight(), left, img
/* 826 */         .getHeight() - bottom, img
/* 827 */         .getWidth() - right, img.getHeight(), null);
/*     */     
/* 829 */     g.drawImage(img, comp
/* 830 */         .getWidth() - right, comp.getHeight() - bottom, comp
/* 831 */         .getWidth(), comp.getHeight(), img
/* 832 */         .getWidth() - right, img.getHeight() - bottom, img
/* 833 */         .getWidth(), img.getHeight(), null);
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\GraphicsUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */