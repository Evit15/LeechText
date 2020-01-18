/*    */ package dark.leech.text.ui.button;
/*    */ 
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import javax.swing.JButton;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CircleButton
/*    */   extends JButton
/*    */ {
/*    */   public CircleButton(String text, float fs) {
/* 14 */     setText(text);
/* 15 */     setForeground(Color.WHITE);
/* 16 */     setFont(FontUtils.ICON_NORMAL.deriveFont(1, fs));
/* 17 */     setHorizontalAlignment(0);
/* 18 */     setVerticalAlignment(0);
/* 19 */     setFocusable(false);
/*    */   }
/*    */   public CircleButton(String text) {
/* 22 */     setText(text);
/* 23 */     setForeground(Color.WHITE);
/* 24 */     setFont(FontUtils.ICON_NORMAL);
/* 25 */     setHorizontalAlignment(0);
/* 26 */     setVerticalAlignment(0);
/* 27 */     setFocusable(false);
/*    */   }
/*    */   
/*    */   public void setBounds(int x, int y, int width, int height) {
/* 31 */     super.setBounds(x, y, width, height);
/* 32 */     Color bc = ColorUtils.BUTTON_CLICK;
/* 33 */     Color background = new Color(bc.getRed(), bc.getGreen(), bc.getBlue(), 100);
/* 34 */     ButtonUI styledButton = new ButtonUI(true);
/* 35 */     styledButton.setRolloverBackground(background);
/* 36 */     setUI(styledButton);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\button\CircleButton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */