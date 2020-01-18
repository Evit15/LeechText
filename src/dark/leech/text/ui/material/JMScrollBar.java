/*    */ package dark.leech.text.ui.material;
/*    */ 
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.RenderingHints;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.plaf.basic.BasicScrollBarUI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class JMScrollBar
/*    */   extends BasicScrollBarUI
/*    */ {
/*    */   private static final int SCROLL_BAR_ALPHA_ROLLOVER = 150;
/*    */   private static final int SCROLL_BAR_ALPHA = 100;
/*    */   private static final int THUMB_BORDER_SIZE = 2;
/*    */   private static final int THUMB_SIZE = 8;
/* 38 */   private static final Color THUMB_COLOR = ColorUtils.THEME_COLOR;
/* 39 */   private final JButton b = new JButton()
/*    */     {
/*    */       public Dimension getPreferredSize()
/*    */       {
/* 43 */         return new Dimension(0, 0);
/*    */       }
/*    */     };
/*    */ 
/*    */ 
/*    */   
/*    */   protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
/* 50 */     int alpha = isThumbRollover() ? 150 : 100;
/* 51 */     int orientation = this.scrollbar.getOrientation();
/* 52 */     int x = thumbBounds.x + 2;
/* 53 */     int y = thumbBounds.y + 2;
/*    */     
/* 55 */     int width = (orientation == 1) ? 8 : (thumbBounds.width - 4);
/* 56 */     width = Math.max(width, 8);
/*    */     
/* 58 */     int height = (orientation == 1) ? (thumbBounds.height - 4) : 8;
/* 59 */     height = Math.max(height, 8);
/*    */ 
/*    */     
/* 62 */     Graphics2D graphics2D = (Graphics2D)g.create();
/* 63 */     graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 64 */     graphics2D.setColor(new Color(THUMB_COLOR.getRed(), THUMB_COLOR.getGreen(), THUMB_COLOR.getBlue(), alpha));
/* 65 */     graphics2D.fillRect(x, y, width, height);
/* 66 */     graphics2D.dispose();
/*    */   }
/*    */ 
/*    */   
/*    */   protected JButton createDecreaseButton(int orientation) {
/* 71 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   protected JButton createIncreaseButton(int orientation) {
/* 76 */     return this.b;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\JMScrollBar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */