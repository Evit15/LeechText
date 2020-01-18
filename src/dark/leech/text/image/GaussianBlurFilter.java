/*     */ package dark.leech.text.image;
/*     */ 
/*     */ import dark.leech.text.util.GraphicsUtils;
/*     */ import java.awt.image.BufferedImage;
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
/*     */ public class GaussianBlurFilter
/*     */   extends AbstractFilter
/*     */ {
/*     */   private final int radius;
/*     */   
/*     */   public GaussianBlurFilter() {
/*  48 */     this(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GaussianBlurFilter(int radius) {
/*  58 */     if (radius < 1) {
/*  59 */       radius = 1;
/*     */     }
/*     */     
/*  62 */     this.radius = radius;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRadius() {
/*  71 */     return this.radius;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage filter(BufferedImage src, BufferedImage dst) {
/*  79 */     int width = src.getWidth();
/*  80 */     int height = src.getHeight();
/*     */     
/*  82 */     if (dst == null) {
/*  83 */       dst = createCompatibleDestImage(src, null);
/*     */     }
/*     */     
/*  86 */     int[] srcPixels = new int[width * height];
/*  87 */     int[] dstPixels = new int[width * height];
/*     */     
/*  89 */     float[] kernel = createGaussianKernel(this.radius);
/*     */     
/*  91 */     GraphicsUtils.getPixels(src, 0, 0, width, height, srcPixels);
/*     */     
/*  93 */     blur(srcPixels, dstPixels, width, height, kernel, this.radius);
/*     */     
/*  95 */     blur(dstPixels, srcPixels, height, width, kernel, this.radius);
/*     */     
/*  97 */     GraphicsUtils.setPixels(dst, 0, 0, width, height, srcPixels);
/*     */     
/*  99 */     return dst;
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
/*     */ 
/*     */ 
/*     */   
/*     */   static void blur(int[] srcPixels, int[] dstPixels, int width, int height, float[] kernel, int radius) {
/* 130 */     for (int y = 0; y < height; y++) {
/* 131 */       int index = y;
/* 132 */       int offset = y * width;
/*     */       
/* 134 */       for (int x = 0; x < width; x++) {
/* 135 */         float b = 0.0F, g = b, r = g, a = r;
/*     */         
/* 137 */         for (int i = -radius; i <= radius; i++) {
/* 138 */           int subOffset = x + i;
/* 139 */           if (subOffset < 0 || subOffset >= width) {
/* 140 */             subOffset = (x + width) % width;
/*     */           }
/*     */           
/* 143 */           int pixel = srcPixels[offset + subOffset];
/* 144 */           float blurFactor = kernel[radius + i];
/*     */           
/* 146 */           a += blurFactor * (pixel >> 24 & 0xFF);
/* 147 */           r += blurFactor * (pixel >> 16 & 0xFF);
/* 148 */           g += blurFactor * (pixel >> 8 & 0xFF);
/* 149 */           b += blurFactor * (pixel & 0xFF);
/*     */         } 
/*     */         
/* 152 */         int ca = (int)(a + 0.5F);
/* 153 */         int cr = (int)(r + 0.5F);
/* 154 */         int cg = (int)(g + 0.5F);
/* 155 */         int cb = (int)(b + 0.5F);
/*     */         
/* 157 */         dstPixels[index] = ((ca > 255) ? 255 : ca) << 24 | ((cr > 255) ? 255 : cr) << 16 | ((cg > 255) ? 255 : cg) << 8 | ((cb > 255) ? 255 : cb);
/*     */ 
/*     */ 
/*     */         
/* 161 */         index += height;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static float[] createGaussianKernel(int radius) {
/* 167 */     if (radius < 1) {
/* 168 */       throw new IllegalArgumentException("Radius must be >= 1");
/*     */     }
/*     */     
/* 171 */     float[] data = new float[radius * 2 + 1];
/*     */     
/* 173 */     float sigma = radius / 3.0F;
/* 174 */     float twoSigmaSquare = 2.0F * sigma * sigma;
/* 175 */     float sigmaRoot = (float)Math.sqrt(twoSigmaSquare * Math.PI);
/* 176 */     float total = 0.0F;
/*     */     int i;
/* 178 */     for (i = -radius; i <= radius; i++) {
/* 179 */       float distance = (i * i);
/* 180 */       int index = i + radius;
/* 181 */       data[index] = (float)Math.exp((-distance / twoSigmaSquare)) / sigmaRoot;
/* 182 */       total += data[index];
/*     */     } 
/*     */     
/* 185 */     for (i = 0; i < data.length; i++) {
/* 186 */       data[i] = data[i] / total;
/*     */     }
/*     */     
/* 189 */     return data;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\image\GaussianBlurFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */