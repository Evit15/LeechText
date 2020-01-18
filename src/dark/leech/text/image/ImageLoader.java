/*     */ package dark.leech.text.image;
/*     */ 
/*     */ import dark.leech.text.action.Log;
/*     */ import dark.leech.text.ui.CircleWait;
/*     */ import dark.leech.text.util.FileUtils;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JLayer;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingWorker;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ImageLoader
/*     */   extends SwingWorker<InputStream, Void>
/*     */ {
/*     */   private String urlImage;
/*     */   private String pathImage;
/*     */   private InputStream imageStream;
/*     */   private JLabel label;
/*     */   
/*     */   public ImageLoader path(String pathImage) {
/*  54 */     this.pathImage = pathImage;
/*  55 */     return this;
/*     */   }
/*     */   
/*     */   public ImageLoader url(String urlImage) {
/*  59 */     this.urlImage = urlImage;
/*  60 */     return this;
/*     */   }
/*     */   
/*     */   public ImageLoader input(InputStream imageStream) {
/*  64 */     this.imageStream = imageStream;
/*  65 */     return this;
/*     */   }
/*     */   
/*     */   public ImageLoader loadTo(JLabel label) {
/*  69 */     this.label = label;
/*  70 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected InputStream doInBackground() throws Exception {
/*  75 */     if (this.urlImage != null && 
/*  76 */       this.pathImage != null) {
/*  77 */       CircleWait circleWait = new CircleWait(this.label.getPreferredSize());
/*  78 */       JLayer<JPanel> layer = circleWait.getJlayer();
/*  79 */       this.label.add(layer);
/*  80 */       layer.setBounds(0, 0, this.label.getWidth(), this.label.getHeight());
/*  81 */       circleWait.startWait();
/*  82 */       FileUtils.url2file(this.urlImage, this.pathImage);
/*  83 */       circleWait.stopWait();
/*     */     } 
/*     */     
/*  86 */     if (this.pathImage != null && (
/*  87 */       new File(this.pathImage)).exists() && (
/*  88 */       new File(this.pathImage)).isFile()) {
/*  89 */       this.imageStream = new FileInputStream(this.pathImage);
/*     */     }
/*  91 */     if (this.imageStream == null)
/*  92 */       this.imageStream = ImageLoader.class.getResourceAsStream("/dark/leech/res/img/nocover.png"); 
/*  93 */     return this.imageStream;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void done() {
/*     */     try {
/* 100 */       setImage(get());
/* 101 */     } catch (Exception e) {
/* 102 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setImage(InputStream in) {
/*     */     try {
/* 109 */       BufferedImage image = ImageIO.read(in);
/* 110 */       int x = (this.label.getSize()).width;
/* 111 */       int y = (this.label.getSize()).height;
/* 112 */       int ix = image.getWidth();
/* 113 */       int iy = image.getHeight();
/* 114 */       int dx = 0;
/* 115 */       int dy = 0;
/* 116 */       if (x / y > ix / iy) {
/* 117 */         dy = y;
/* 118 */         dx = dy * ix / iy;
/*     */       } else {
/* 120 */         dx = x;
/* 121 */         dy = dx * iy / ix;
/*     */       } 
/* 123 */       ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, 4));
/*     */       
/* 125 */       this.label.setIcon(icon);
/* 126 */     } catch (Exception ex) {
/* 127 */       Log.add(ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\image\ImageLoader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */