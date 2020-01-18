/*    */ package dark.leech.text.ui.button;
/*    */ 
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import javax.swing.JButton;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CloseButton
/*    */   extends JButton
/*    */ {
/*    */   public CloseButton() {
/* 15 */     setText("");
/* 16 */     setForeground(Color.WHITE);
/* 17 */     setFont(FontUtils.ICON_NORMAL);
/* 18 */     setHorizontalAlignment(0);
/* 19 */     setVerticalAlignment(0);
/* 20 */     setFocusable(false);
/*    */   }
/*    */   
/*    */   public void setBounds(int x, int y, int width, int height) {
/* 24 */     super.setBounds(x, y, width, height);
/* 25 */     ButtonUI styledButton = new ButtonUI(true);
/* 26 */     Color bc = ColorUtils.BUTTON_CLICK;
/* 27 */     Color rolloverBackground = new Color(bc.getRed(), bc.getGreen(), bc.getBlue(), 200);
/* 28 */     styledButton.setRolloverBackground(rolloverBackground);
/* 29 */     styledButton.setPressedBackground(rolloverBackground);
/* 30 */     setUI(styledButton);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\button\CloseButton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */