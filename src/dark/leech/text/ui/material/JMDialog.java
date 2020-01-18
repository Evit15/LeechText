/*     */ package dark.leech.text.ui.material;
import dark.leech.text.image.GaussianBlurFilter;
/*     */ import dark.leech.text.listeners.BlurListener;
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.ui.Animation;
/*     */ import dark.leech.text.ui.main.App;
/*     */ import dark.leech.text.util.AppUtils;
/*     */ import dark.leech.text.util.GraphicsUtils;

import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
import java.awt.LayoutManager;
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
import java.awt.Toolkit;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
/*     */ import java.awt.image.RescaleOp;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ public abstract class JMDialog extends JDialog implements BlurListener {
/*     */   private Point pointLocation;
/*  23 */   private float alpha = 1.0F; private BlurListener blurListener; protected Container container; private BufferedImage blurBuffer;
/*     */   private BufferedImage backBuffer;
/*     */   private ChangeListener changeListener;
/*     */   private Color borderColor;
/*     */   
/*     */   public JMDialog() {
/*  29 */     super(App.getMain());
/*  30 */     this.blurListener = (BlurListener)App.getMain();
/*     */   }
/*     */   
/*     */   public JMDialog(JMDialog owner) {
/*  34 */     super(owner);
/*  35 */     this.blurListener = owner;
/*     */   }
/*     */   
/*     */   protected void onCreate() {
/*  39 */     this.container = getContentPane();
/*  40 */     this.container.setLayout((LayoutManager)null);
/*  41 */     this.container.setBackground(Color.WHITE);
/*  42 */     setModal(true);
/*  43 */     setResizable(false);
/*  44 */     setUndecorated(true);
/*  45 */     if (GraphicsUtils.TRANSLUCENT_SUPPORT)
/*  46 */       setOpacity(0.0F); 
/*  47 */     setTitle("LeechText");
/*  48 */     setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/dark/leech/res/icon.png")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void open() {
/*  54 */     (new Thread(new Runnable()
/*     */         {
/*     */           public void run() {
/*  57 */             JMDialog.this.blurListener.setBlur(true);
/*  58 */             JMDialog.this.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, JMDialog.this.getWidth(), JMDialog.this.getHeight(), 5.0D, 5.0D));
/*  59 */             JMDialog.this.pointLocation = AppUtils.getLocation();
/*  60 */             int X = JMDialog.this.pointLocation.x + 195 - JMDialog.this.getWidth() / 2;
/*  61 */             X = (X < 10) ? 10 : X;
/*  62 */             X = (X + JMDialog.this.getWidth() > AppUtils.width) ? (AppUtils.width - JMDialog.this.getWidth() - 10) : X;
/*  63 */             int Y = JMDialog.this.pointLocation.y + 300 - JMDialog.this.getHeight() / 2;
/*  64 */             Y = (Y + JMDialog.this.getHeight() > AppUtils.height) ? (AppUtils.height - JMDialog.this.getHeight() - 10) : Y;
/*  65 */             Y = (Y < 10) ? 10 : Y;
/*  66 */             JMDialog.this.setLocation(X, Y);
/*  67 */             Animation.fadeIn(JMDialog.this);
/*  68 */             JMDialog.this.setVisible(true);
/*     */           }
/*  70 */         })).start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void runOnUiThread(Runnable runnable) {
/*  76 */     (new Thread(runnable)).start();
/*     */   }
/*     */   
/*     */   public void close() {
/*  80 */     this.blurListener.setBlur(false);
/*  81 */     if (this.changeListener != null) this.changeListener.doChanger(); 
/*  82 */     Animation.fadeOut(this);
/*     */   }
/*     */   
/*     */   public void setBorderColor(Color color) {
/*  86 */     this.borderColor = color;
/*     */   }
/*     */   
/*     */   public void setChangeListener(ChangeListener changeListener) {
/*  90 */     this.changeListener = changeListener;
/*     */   }
/*     */   
/*     */   public void setBlurListener(BlurListener blurListener) {
/*  94 */     this.blurListener = blurListener;
/*     */   }
/*     */   
/*     */   public void setBlur(boolean blur) {
/*  98 */     if (blur) { createBlur(); }
/*  99 */     else { this.blurBuffer = null; }
/* 100 */      repaint();
/*     */   }
/*     */   
/*     */   private void createBlur() {
/* 104 */     JRootPane root = SwingUtilities.getRootPane(this);
/* 105 */     this.blurBuffer = GraphicsUtils.createCompatibleImage(getWidth(), getHeight());
/* 106 */     Graphics2D g2 = this.blurBuffer.createGraphics();
/* 107 */     root.paint(g2);
/* 108 */     g2.dispose();
/*     */     
/* 110 */     this.backBuffer = this.blurBuffer;
/* 111 */     this.blurBuffer = GraphicsUtils.createThumbnailFast(this.blurBuffer, getWidth() / 2);
/* 112 */     this.blurBuffer = (new GaussianBlurFilter(3)).filter(this.blurBuffer, null);
/* 113 */     RescaleOp op = new RescaleOp(0.8F, 0.0F, null);
/* 114 */     this.blurBuffer = op.filter(this.blurBuffer, (BufferedImage)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/* 119 */     super.paint(g);
/* 120 */     Graphics2D g2 = (Graphics2D)g.create();
/* 121 */     if (isVisible() && this.blurBuffer != null) {
/* 122 */       g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 123 */       g2.drawImage(this.backBuffer, 0, 0, (ImageObserver)null);
/* 124 */       g2.setComposite(AlphaComposite.SrcOver.derive(this.alpha));
/* 125 */       g2.drawImage(this.blurBuffer, 0, 0, getWidth(), getHeight(), null);
/*     */     } 
/* 127 */     if (this.borderColor != null) {
/* 128 */       g2.setColor(this.borderColor);
/* 129 */       g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 5, 5);
/*     */     } 
/* 131 */     g2.dispose();
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\JMDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */