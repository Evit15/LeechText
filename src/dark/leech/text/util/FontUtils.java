/*    */ package dark.leech.text.util;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class FontUtils
/*    */ {
/*    */   public static Font iconFont(float size) {
/*    */     try {
/* 11 */       InputStream in = FontUtils.class.getResourceAsStream("/dark/leech/res/font/icon.ttf");
/* 12 */       return Font.createFont(0, in).deriveFont(size);
/* 13 */     } catch (IOException|java.awt.FontFormatException e) {
/* 14 */       e.printStackTrace();
/*    */       
/* 16 */       return null;
/*    */     } 
/*    */   }
/*    */   private static Font titleFont(float size) {
/*    */     try {
/* 21 */       InputStream in = FontUtils.class.getResourceAsStream("/dark/leech/res/font/title.ttf");
/* 22 */       return Font.createFont(0, in).deriveFont(size);
/* 23 */     } catch (IOException|java.awt.FontFormatException iOException) {
/*    */       
/* 25 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Font textFont(float size, int type) {
/*    */     try {
/* 31 */       InputStream in = FontUtils.class.getResourceAsStream("/dark/leech/res/font/" + ((type == 1) ? "textbold.ttf" : "textregular.ttf"));
/*    */       
/* 33 */       return Font.createFont(0, in).deriveFont(size);
/* 34 */     } catch (IOException|java.awt.FontFormatException iOException) {
/*    */       
/* 36 */       return null;
/*    */     } 
/*    */   }
/*    */   public static Font codeFont(float size) {
/*    */     try {
/* 41 */       InputStream in = FontUtils.class.getResourceAsStream("/dark/leech/res/font/code.ttf");
/* 42 */       return Font.createFont(0, in).deriveFont(size);
/* 43 */     } catch (IOException|java.awt.FontFormatException iOException) {
/*    */ 
/*    */       
/* 46 */       return null;
/*    */     } 
/* 48 */   } public static final Font TITLE_BIG = titleFont(30.0F);
/* 49 */   public static final Font TITLE_NORMAL = titleFont(24.0F);
/* 50 */   public static final Font TITLE_THIN = titleFont(20.0F);
/* 51 */   public static final Font ICON_NORMAL = iconFont(23.0F);
/* 52 */   public static final Font TEXT_NORMAL = textFont(14.0F, 0);
/* 53 */   public static final Font TEXT_BOLD = textFont(16.0F, 1);
/* 54 */   public static final Font TEXT_THIN = textFont(12.0F, 0);
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\FontUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */