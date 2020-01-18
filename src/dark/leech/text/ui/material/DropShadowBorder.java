/*     */ package dark.leech.text.ui.material;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.awt.image.ConvolveOp;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.Kernel;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.AbstractBorder;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DropShadowBorder
/*     */   extends AbstractBorder
/*     */   implements Border
/*     */ {
/*     */   private enum Position
/*     */   {
/*  35 */     TOP, TOP_LEFT, LEFT, BOTTOM_LEFT,
/*  36 */     BOTTOM, BOTTOM_RIGHT, RIGHT, TOP_RIGHT;
/*     */   }
/*  38 */   private static final Map<Integer, Map<Position, BufferedImage>> CACHE = new HashMap<>();
/*     */   
/*     */   private Color lineColor;
/*     */   
/*     */   private int lineWidth;
/*     */   private int shadowSize;
/*     */   private float shadowOpacity;
/*     */   private int cornerSize;
/*     */   private boolean showTopShadow;
/*     */   private boolean showLeftShadow;
/*     */   private boolean showBottomShadow;
/*     */   private boolean showRightShadow;
/*     */   
/*     */   public DropShadowBorder() {
/*  52 */     this(UIManager.getColor("Control"), 1, 5);
/*     */   }
/*     */   
/*     */   public DropShadowBorder(Color lineColor, int lineWidth, int shadowSize) {
/*  56 */     this(lineColor, lineWidth, shadowSize, 0.5F, 12, false, false, true, true);
/*     */   }
/*     */   
/*     */   public DropShadowBorder(Color lineColor, int lineWidth, boolean showLeftShadow) {
/*  60 */     this(lineColor, lineWidth, 5, 0.5F, 12, false, showLeftShadow, true, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DropShadowBorder(Color lineColor, int lineWidth, int shadowSize, float shadowOpacity, int cornerSize, boolean showTopShadow, boolean showLeftShadow, boolean showBottomShadow, boolean showRightShadow) {
/*  66 */     this.lineColor = lineColor;
/*  67 */     this.lineWidth = lineWidth;
/*  68 */     this.shadowSize = shadowSize;
/*  69 */     this.shadowOpacity = shadowOpacity;
/*  70 */     this.cornerSize = cornerSize;
/*  71 */     this.showTopShadow = showTopShadow;
/*  72 */     this.showLeftShadow = showLeftShadow;
/*  73 */     this.showBottomShadow = showBottomShadow;
/*  74 */     this.showRightShadow = showRightShadow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintBorder(Component c, Graphics graphics, int x, int y, int width, int height) {
/*  85 */     Map<Position, BufferedImage> images = getImages(null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     Graphics2D g2 = (Graphics2D)graphics;
/*  94 */     g2.setColor(this.lineColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     Point topLeftShadowPoint = null;
/* 104 */     if (this.showLeftShadow || this.showTopShadow) {
/* 105 */       topLeftShadowPoint = new Point();
/* 106 */       if (this.showLeftShadow && !this.showTopShadow) {
/* 107 */         topLeftShadowPoint.setLocation(x, y + this.shadowSize);
/* 108 */       } else if (this.showLeftShadow && this.showTopShadow) {
/* 109 */         topLeftShadowPoint.setLocation(x, y);
/* 110 */       } else if (!this.showLeftShadow && this.showTopShadow) {
/* 111 */         topLeftShadowPoint.setLocation(x + this.shadowSize, y);
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     Point bottomLeftShadowPoint = null;
/* 116 */     if (this.showLeftShadow || this.showBottomShadow) {
/* 117 */       bottomLeftShadowPoint = new Point();
/* 118 */       if (this.showLeftShadow && !this.showBottomShadow) {
/* 119 */         bottomLeftShadowPoint.setLocation(x, y + height - this.shadowSize - this.shadowSize);
/* 120 */       } else if (this.showLeftShadow && this.showBottomShadow) {
/* 121 */         bottomLeftShadowPoint.setLocation(x, y + height - this.shadowSize);
/* 122 */       } else if (!this.showLeftShadow && this.showBottomShadow) {
/* 123 */         bottomLeftShadowPoint.setLocation(x + this.shadowSize, y + height - this.shadowSize);
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     Point bottomRightShadowPoint = null;
/* 128 */     if (this.showRightShadow || this.showBottomShadow) {
/* 129 */       bottomRightShadowPoint = new Point();
/* 130 */       if (this.showRightShadow && !this.showBottomShadow) {
/* 131 */         bottomRightShadowPoint.setLocation(x + width - this.shadowSize, y + height - this.shadowSize - this.shadowSize);
/* 132 */       } else if (this.showRightShadow && this.showBottomShadow) {
/* 133 */         bottomRightShadowPoint.setLocation(x + width - this.shadowSize, y + height - this.shadowSize);
/* 134 */       } else if (!this.showRightShadow && this.showBottomShadow) {
/* 135 */         bottomRightShadowPoint.setLocation(x + width - this.shadowSize - this.shadowSize, y + height - this.shadowSize);
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     Point topRightShadowPoint = null;
/* 140 */     if (this.showRightShadow || this.showTopShadow) {
/* 141 */       topRightShadowPoint = new Point();
/* 142 */       if (this.showRightShadow && !this.showTopShadow) {
/* 143 */         topRightShadowPoint.setLocation(x + width - this.shadowSize, y + this.shadowSize);
/* 144 */       } else if (this.showRightShadow && this.showTopShadow) {
/* 145 */         topRightShadowPoint.setLocation(x + width - this.shadowSize, y);
/* 146 */       } else if (!this.showRightShadow && this.showTopShadow) {
/* 147 */         topRightShadowPoint.setLocation(x + width - this.shadowSize - this.shadowSize, y);
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     if (this.showLeftShadow) {
/* 152 */       Rectangle leftShadowRect = new Rectangle(x, (int)(topLeftShadowPoint.getY() + this.shadowSize), this.shadowSize, (int)(bottomLeftShadowPoint.getY() - topLeftShadowPoint.getY() - this.shadowSize));
/* 153 */       g2.drawImage(((BufferedImage)images.get(Position.LEFT)).getScaledInstance(leftShadowRect.width, leftShadowRect.height, 2), leftShadowRect.x, leftShadowRect.y, (ImageObserver)null);
/*     */     } 
/*     */     
/* 156 */     if (this.showBottomShadow) {
/* 157 */       Rectangle bottomShadowRect = new Rectangle((int)(bottomLeftShadowPoint.getX() + this.shadowSize), y + height - this.shadowSize, (int)(bottomRightShadowPoint.getX() - bottomLeftShadowPoint.getX() - this.shadowSize), this.shadowSize);
/* 158 */       g2.drawImage(((BufferedImage)images.get(Position.BOTTOM)).getScaledInstance(bottomShadowRect.width, bottomShadowRect.height, 2), bottomShadowRect.x, bottomShadowRect.y, (ImageObserver)null);
/*     */     } 
/*     */     
/* 161 */     if (this.showRightShadow) {
/* 162 */       Rectangle rightShadowRect = new Rectangle(x + width - this.shadowSize, (int)(topRightShadowPoint.getY() + this.shadowSize), this.shadowSize, (int)(bottomRightShadowPoint.getY() - topRightShadowPoint.getY() - this.shadowSize));
/* 163 */       g2.drawImage(((BufferedImage)images.get(Position.RIGHT)).getScaledInstance(rightShadowRect.width, rightShadowRect.height, 2), rightShadowRect.x, rightShadowRect.y, (ImageObserver)null);
/*     */     } 
/*     */     
/* 166 */     if (this.showTopShadow) {
/* 167 */       Rectangle topShadowRect = new Rectangle((int)topLeftShadowPoint.getX() + this.shadowSize, y, (int)(topRightShadowPoint.getX() - topLeftShadowPoint.getX() - this.shadowSize), this.shadowSize);
/* 168 */       g2.drawImage(((BufferedImage)images.get(Position.TOP)).getScaledInstance(topShadowRect.width, topShadowRect.height, 2), topShadowRect.x, topShadowRect.y, (ImageObserver)null);
/*     */     } 
/*     */     
/* 171 */     if (this.showLeftShadow || this.showTopShadow) {
/* 172 */       g2.drawImage(images.get(Position.TOP_LEFT), (BufferedImageOp)null, (int)topLeftShadowPoint.getX(), (int)topLeftShadowPoint.getY());
/*     */     }
/* 174 */     if (this.showLeftShadow || this.showBottomShadow) {
/* 175 */       g2.drawImage(images.get(Position.BOTTOM_LEFT), (BufferedImageOp)null, (int)bottomLeftShadowPoint.getX(), (int)bottomLeftShadowPoint.getY());
/*     */     }
/* 177 */     if (this.showRightShadow || this.showBottomShadow) {
/* 178 */       g2.drawImage(images.get(Position.BOTTOM_RIGHT), (BufferedImageOp)null, (int)bottomRightShadowPoint.getX(), (int)bottomRightShadowPoint.getY());
/*     */     }
/* 180 */     if (this.showRightShadow || this.showTopShadow) {
/* 181 */       g2.drawImage(images.get(Position.TOP_RIGHT), (BufferedImageOp)null, (int)topRightShadowPoint.getX(), (int)topRightShadowPoint.getY());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<Position, BufferedImage> getImages(Graphics2D g2) {
/* 188 */     Map<Position, BufferedImage> images = CACHE.get(Integer.valueOf(this.shadowSize));
/* 189 */     if (images == null) {
/* 190 */       images = new HashMap<>();
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
/* 206 */       int rectWidth = this.cornerSize + 1;
/* 207 */       RoundRectangle2D rect = new RoundRectangle2D.Double(0.0D, 0.0D, rectWidth, rectWidth, this.cornerSize, this.cornerSize);
/* 208 */       int imageWidth = rectWidth + this.shadowSize * 2;
/* 209 */       BufferedImage image = new BufferedImage(imageWidth, imageWidth, 2);
/* 210 */       Graphics2D buffer = (Graphics2D)image.getGraphics();
/* 211 */       buffer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/* 212 */       buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 213 */       buffer.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/* 214 */       buffer.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
/* 215 */       buffer.setColor(new Color(0.0F, 0.0F, 0.0F, this.shadowOpacity));
/* 216 */       buffer.translate(this.shadowSize, this.shadowSize);
/* 217 */       buffer.fill(rect);
/* 218 */       float blurry = 1.0F / (this.shadowSize * this.shadowSize);
/* 219 */       float[] blurKernel = new float[this.shadowSize * this.shadowSize];
/* 220 */       for (int i = 0; i < blurKernel.length; i++) {
/* 221 */         blurKernel[i] = blurry;
/*     */       }
/* 223 */       ConvolveOp blur = new ConvolveOp(new Kernel(this.shadowSize, this.shadowSize, blurKernel));
/* 224 */       BufferedImage targetImage = new BufferedImage(imageWidth, imageWidth, 2);
/* 225 */       ((Graphics2D)targetImage.getGraphics()).drawImage(image, blur, -(this.shadowSize / 2), -(this.shadowSize / 2));
/*     */       
/* 227 */       int x = 1;
/* 228 */       int y = 1;
/* 229 */       int w = this.shadowSize;
/* 230 */       int h = this.shadowSize;
/* 231 */       images.put(Position.TOP_LEFT, targetImage.getSubimage(x, y, w, h));
/* 232 */       x = 1;
/* 233 */       y = h;
/* 234 */       w = this.shadowSize;
/* 235 */       h = 1;
/* 236 */       images.put(Position.LEFT, targetImage.getSubimage(x, y, w, h));
/* 237 */       x = 1;
/* 238 */       y = rectWidth;
/* 239 */       w = this.shadowSize;
/* 240 */       h = this.shadowSize;
/* 241 */       images.put(Position.BOTTOM_LEFT, targetImage.getSubimage(x, y, w, h));
/* 242 */       x = this.cornerSize + 1;
/* 243 */       y = rectWidth;
/* 244 */       w = 1;
/* 245 */       h = this.shadowSize;
/* 246 */       images.put(Position.BOTTOM, targetImage.getSubimage(x, y, w, h));
/* 247 */       x = rectWidth;
/* 248 */       y = x;
/* 249 */       w = this.shadowSize;
/* 250 */       h = this.shadowSize;
/* 251 */       images.put(Position.BOTTOM_RIGHT, targetImage.getSubimage(x, y, w, h));
/* 252 */       x = rectWidth;
/* 253 */       y = this.cornerSize + 1;
/* 254 */       w = this.shadowSize;
/* 255 */       h = 1;
/* 256 */       images.put(Position.RIGHT, targetImage.getSubimage(x, y, w, h));
/* 257 */       x = rectWidth;
/* 258 */       y = 1;
/* 259 */       w = this.shadowSize;
/* 260 */       h = this.shadowSize;
/* 261 */       images.put(Position.TOP_RIGHT, targetImage.getSubimage(x, y, w, h));
/* 262 */       x = this.shadowSize;
/* 263 */       y = 1;
/* 264 */       w = 1;
/* 265 */       h = this.shadowSize;
/* 266 */       images.put(Position.TOP, targetImage.getSubimage(x, y, w, h));
/*     */       
/* 268 */       buffer.dispose();
/* 269 */       image.flush();
/*     */     } 
/* 271 */     return images;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Insets getBorderInsets(Component c) {
/* 278 */     int top = 4 + (this.showTopShadow ? (this.lineWidth + this.shadowSize) : this.lineWidth);
/* 279 */     int left = 4 + (this.showLeftShadow ? (this.lineWidth + this.shadowSize) : this.lineWidth);
/* 280 */     int bottom = 4 + (this.showBottomShadow ? (this.lineWidth + this.shadowSize) : this.lineWidth);
/* 281 */     int right = 4 + (this.showRightShadow ? (this.lineWidth + this.shadowSize) : this.lineWidth);
/*     */     
/* 283 */     return new Insets(top, left, bottom, right);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBorderOpaque() {
/* 290 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isShowTopShadow() {
/* 294 */     return this.showTopShadow;
/*     */   }
/*     */   
/*     */   public boolean isShowLeftShadow() {
/* 298 */     return this.showLeftShadow;
/*     */   }
/*     */   
/*     */   public boolean isShowRightShadow() {
/* 302 */     return this.showRightShadow;
/*     */   }
/*     */   
/*     */   public boolean isShowBottomShadow() {
/* 306 */     return this.showBottomShadow;
/*     */   }
/*     */   
/*     */   public int getLineWidth() {
/* 310 */     return this.lineWidth;
/*     */   }
/*     */   
/*     */   public Color getLineColor() {
/* 314 */     return this.lineColor;
/*     */   }
/*     */   
/*     */   public int getShadowSize() {
/* 318 */     return this.shadowSize;
/*     */   }
/*     */   
/*     */   public float getShadowOpacity() {
/* 322 */     return this.shadowOpacity;
/*     */   }
/*     */   
/*     */   public int getCornerSize() {
/* 326 */     return this.cornerSize;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\DropShadowBorder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */