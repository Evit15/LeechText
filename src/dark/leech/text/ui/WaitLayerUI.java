/*     */ package dark.leech.text.ui;
/*     */ 
/*     */ import dark.leech.text.util.ColorUtils;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLayer;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.plaf.LayerUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class WaitLayerUI
/*     */   extends LayerUI<JPanel>
/*     */   implements ActionListener
/*     */ {
/*     */   private boolean mIsRunning;
/*     */   private boolean mIsFadingOut;
/*     */   private Timer mTimer;
/*     */   private int mAngle;
/*     */   private int mFadeCount;
/*  46 */   private int mFadeLimit = 15;
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/*  50 */     int w = c.getWidth();
/*  51 */     int h = c.getHeight();
/*     */ 
/*     */     
/*  54 */     super.paint(g, c);
/*     */     
/*  56 */     if (!this.mIsRunning) {
/*     */       return;
/*     */     }
/*     */     
/*  60 */     Graphics2D g2 = (Graphics2D)g.create();
/*     */     
/*  62 */     float fade = this.mFadeCount / this.mFadeLimit;
/*     */     
/*  64 */     Composite urComposite = g2.getComposite();
/*     */     
/*  66 */     g2.setComposite(AlphaComposite.getInstance(3, (0.5F * fade < 0.0F) ? 0.0F : (0.5F * fade)));
/*     */     
/*  68 */     g2.setColor(new Color(0, 0, 0, 30));
/*  69 */     g2.fillRect(0, 0, w, h);
/*  70 */     g2.setComposite(urComposite);
/*     */ 
/*     */     
/*  73 */     int s = Math.min(w, h) / 5;
/*  74 */     int cx = w / 2;
/*  75 */     int cy = h / 2;
/*  76 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/*  78 */     g2.setStroke(new BasicStroke((s / 4), 1, 1));
/*     */     
/*  80 */     g2.setPaint(ColorUtils.THEME_COLOR);
/*  81 */     g2.rotate(Math.PI * this.mAngle / 180.0D, cx, cy);
/*  82 */     for (int i = 0; i < 12; i++) {
/*  83 */       float scale = (11.0F - i) / 11.0F;
/*  84 */       g2.drawLine(cx + s, cy, cx + s * 2, cy);
/*  85 */       g2.rotate(-0.5235987755982988D, cx, cy);
/*  86 */       g2.setComposite(AlphaComposite.getInstance(3, (scale * fade < 0.0F) ? 0.0F : (scale * fade)));
/*     */     } 
/*     */ 
/*     */     
/*  90 */     g2.dispose();
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*  94 */     if (this.mIsRunning) {
/*  95 */       firePropertyChange("tick", Integer.valueOf(0), Integer.valueOf(1));
/*  96 */       this.mAngle += 3;
/*  97 */       if (this.mAngle >= 360) {
/*  98 */         this.mAngle = 0;
/*     */       }
/* 100 */       if (this.mIsFadingOut) {
/* 101 */         if (--this.mFadeCount == 0) {
/* 102 */           this.mIsRunning = false;
/* 103 */           this.mTimer.stop();
/*     */         } 
/* 105 */       } else if (this.mFadeCount < this.mFadeLimit) {
/* 106 */         this.mFadeCount++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void start() {
/* 112 */     if (this.mIsRunning) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 117 */     this.mIsRunning = true;
/* 118 */     this.mIsFadingOut = false;
/* 119 */     this.mFadeCount = 0;
/* 120 */     int fps = 30;
/* 121 */     int tick = 1000 / fps;
/* 122 */     this.mTimer = new Timer(tick, this);
/* 123 */     this.mTimer.start();
/*     */   }
/*     */   
/*     */   public void stop() {
/* 127 */     this.mIsFadingOut = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyPropertyChange(PropertyChangeEvent pce, JLayer l) {
/* 132 */     if ("tick".equals(pce.getPropertyName()))
/* 133 */       l.repaint(); 
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\WaitLayerUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */