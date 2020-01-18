/*    */ package dark.leech.text.util;
/*    */ 
/*    */ import java.awt.Color;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColorUtils
/*    */ {
/* 12 */   public static final Color THEME_COLOR = SettingUtils.THEME_COLOR;
/* 13 */   public static final Color STATUS_BAR = new Color(getValue(THEME_COLOR.getRed() - 20), getValue(THEME_COLOR.getGreen() - 20), getValue(THEME_COLOR.getBlue() - 15));
/* 14 */   public static final Color BUTTON_TEXT = THEME_COLOR;
/* 15 */   public static final Color BUTTON_CLICK = BUTTON_TEXT.brighter();
/*    */   
/*    */   private static int getValue(int i) {
/* 18 */     return (i > 0) ? i : 0;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\ColorUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */