/*    */ package dark.leech.text.ui.material;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.EventQueue;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.Window;
/*    */ import java.awt.image.BufferedImage;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JPopupMenu;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.border.Border;
/*    */ import javax.swing.border.LineBorder;
/*    */ 
/*    */ public class JMPopupMenu extends JPopupMenu {
/*    */   private static final int OFFSET = 4;
/*    */   
/*    */   public boolean isOpaque() {
/* 21 */     return false;
/*    */   }
/*    */   private BufferedImage shadow; private Border border;
/*    */   
/*    */   public void updateUI() {
/* 26 */     setBorder((Border)null);
/* 27 */     super.updateUI();
/* 28 */     this.border = null;
/* 29 */     setBorder(new LineBorder(ColorUtils.THEME_COLOR.brighter()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void paintComponent(Graphics g) {
/* 35 */     Graphics2D g2 = (Graphics2D)g.create();
/* 36 */     g2.drawImage(this.shadow, 0, 0, this);
/* 37 */     g2.setPaint(getBackground());
/* 38 */     g2.fillRect(0, 0, getWidth() - 4, getHeight() - 4);
/* 39 */     g2.dispose();
/*    */   }
/*    */ 
/*    */   
/*    */   public void show(Component c, int x, int y) {
/* 44 */     if (this.border == null) {
/* 45 */       Border inner = getBorder();
/* 46 */       Border outer = BorderFactory.createEmptyBorder(0, 0, 4, 4);
/* 47 */       this.border = BorderFactory.createCompoundBorder(outer, inner);
/*    */     } 
/* 49 */     setBorder(this.border);
/* 50 */     Dimension d = getPreferredSize();
/* 51 */     int w = d.width;
/* 52 */     int h = d.height;
/* 53 */     if (this.shadow == null || this.shadow.getWidth() != w || this.shadow.getHeight() != h) {
/* 54 */       this.shadow = new BufferedImage(w, h, 2);
/* 55 */       Graphics2D g2 = this.shadow.createGraphics();
/* 56 */       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*    */       
/* 58 */       g2.setComposite(AlphaComposite.getInstance(3, 0.2F));
/* 59 */       g2.setPaint(Color.BLACK);
/* 60 */       for (int i = 0; i < 4; i++) {
/* 61 */         g2.fillRoundRect(4, 4, w - 4 - 4 + i, h - 4 - 4 + i, 4, 4);
/*    */       }
/*    */       
/* 64 */       g2.dispose();
/*    */     } 
/* 66 */     EventQueue.invokeLater(new Runnable()
/*    */         {
/*    */           public void run() {
/* 69 */             Window pop = SwingUtilities.getWindowAncestor(JMPopupMenu.this);
/* 70 */             if (pop instanceof javax.swing.JWindow) {
/* 71 */               pop.setBackground(new Color(0, true));
/*    */             }
/*    */           }
/*    */         });
/* 75 */     super.show(c, x, y);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\JMPopupMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */