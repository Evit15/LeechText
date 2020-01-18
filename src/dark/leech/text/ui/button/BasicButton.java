/*    */ package dark.leech.text.ui.button;
/*    */ 
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import javax.swing.JButton;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BasicButton
/*    */   extends JButton
/*    */ {
/*    */   public BasicButton() {
/* 14 */     setFont(FontUtils.TEXT_BOLD);
/* 15 */     setHorizontalAlignment(0);
/* 16 */     setVerticalAlignment(0);
/* 17 */     setBackground(Color.WHITE);
/* 18 */     setForeground(ColorUtils.BUTTON_TEXT);
/* 19 */     setFocusable(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBounds(int x, int y, int width, int height) {
/* 25 */     super.setBounds(x, y, width, height);
/* 26 */     ButtonUI btUi = new ButtonUI();
/* 27 */     btUi.setRolloverBackground(new Color(235, 235, 235));
/* 28 */     setUI(btUi);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\button\BasicButton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */