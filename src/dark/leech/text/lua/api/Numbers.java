/*    */ package dark.leech.text.lua.api;
/*    */ 
/*    */ public class Numbers {
/*    */   public static int toInt(String text, int def) {
/*    */     try {
/*  6 */       return Integer.parseInt(text);
/*  7 */     } catch (Exception e) {
/*  8 */       return def;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static double toDouble(String text, double def) {
/*    */     try {
/* 14 */       return Double.parseDouble(text);
/* 15 */     } catch (Exception e) {
/* 16 */       return def;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static float toFloat(String text, float def) {
/*    */     try {
/* 22 */       return Float.parseFloat(text);
/* 23 */     } catch (Exception e) {
/* 24 */       return def;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static long toLong(String text, long def) {
/*    */     try {
/* 30 */       return Long.parseLong(text);
/* 31 */     } catch (Exception e) {
/* 32 */       return def;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\Numbers.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */