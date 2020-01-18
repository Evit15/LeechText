/*    */ package dark.leech.text.ui.button;
/*    */ 
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Cursor;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import javax.swing.AbstractButton;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.border.Border;
/*    */ import javax.swing.plaf.basic.BasicButtonUI;
/*    */ 
/*    */ class ButtonUI extends BasicButtonUI {
/*    */   private boolean round;
/*    */   private Color rolloverBackground;
/*    */   
/*    */   public ButtonUI() {
/* 20 */     this(false);
/*    */   }
/*    */   private Color pressedBackground; private Color defaultBackground; private Dimension size;
/*    */   public ButtonUI(boolean round) {
/* 24 */     Color bc = ColorUtils.BUTTON_CLICK;
/* 25 */     this.rolloverBackground = new Color(bc.getRed(), bc.getGreen(), bc.getBlue());
/* 26 */     this.pressedBackground = new Color(bc.getRed(), bc.getGreen(), bc.getBlue(), 100);
/* 27 */     this.defaultBackground = new Color(bc.getRed(), bc.getGreen(), bc.getBlue(), 0);
/* 28 */     this.round = round;
/*    */   }
/*    */   
/*    */   public void setRound(boolean round) {
/* 32 */     this.round = round;
/*    */   }
/*    */   
/*    */   public void setRolloverBackground(Color rolloverBackground) {
/* 36 */     this.rolloverBackground = rolloverBackground;
/*    */   }
/*    */   
/*    */   public void setPressedBackground(Color pressedBackground) {
/* 40 */     this.pressedBackground = pressedBackground;
/*    */   }
/*    */   
/*    */   public void setDefaultBackground(Color defaultBackground) {
/* 44 */     this.defaultBackground = defaultBackground;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void installUI(JComponent c) {
/* 50 */     super.installUI(c);
/* 51 */     AbstractButton button = (AbstractButton)c;
/* 52 */     this.size = c.getSize();
/* 53 */     button.setOpaque(false);
/* 54 */     button.setBorder((Border)null);
/* 55 */     button.setCursor(new Cursor(12));
/*    */   }
/*    */ 
/*    */   
/*    */   public void paint(Graphics g, JComponent c) {
/* 60 */     AbstractButton b = (AbstractButton)c;
/* 61 */     paintBackground(g, b, b.getModel().isRollover(), b.getModel().isPressed(), b.getModel().isSelected());
/* 62 */     super.paint(g, c);
/*    */   }
/*    */   
/*    */   private void paintBackground(Graphics g, JComponent c, boolean rollover, boolean pressed, boolean clicked) {
/* 66 */     Graphics2D g2 = (Graphics2D)g;
/* 67 */     RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*    */     
/* 69 */     rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/* 70 */     g2.setRenderingHints(rh);
/* 71 */     g2.setColor(this.defaultBackground);
/* 72 */     if (rollover)
/* 73 */       g2.setColor(this.rolloverBackground); 
/* 74 */     if (clicked || pressed)
/* 75 */       g2.setColor(this.pressedBackground); 
/* 76 */     if (this.round) { g.fillOval(this.size.width / 2 - this.size.height / 2, 0, this.size.height, this.size.height); }
/*    */     else
/* 78 */     { g2.fillRoundRect(0, 0, this.size.width, this.size.height, 5, 5); }
/*    */   
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\button\ButtonUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */