/*    */ package dark.leech.text.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SyntaxUtils
/*    */ {
/* 10 */   private static final String[] dau = new String[] { "̀â", "ấ", "ậ", "ẩ", "ẫ", "à", "á", "ạ", "ả", "ã", "â", "ằ", "ắ", "ặ", "ẳ", "ẵ", "ă", "ề", "ế", "ệ", "ể", "ễ", "è", "é", "ẹ", "ẻ", "ẽ", "ê", "ì", "í", "ị", "ỉ", "ĩ", "ồ", "ố", "ộ", "ổ", "ỗ", "ò", "ó", "ọ", "ỏ", "õ", "ô", "ờ", "ớ", "ợ", "ở", "ỡ", "ơ", "ù", "ú", "ụ", "ủ", "ũ", "ừ", "ứ", "ự", "ử", "ữ", "ư", "ỳ", "ý", "ỵ", "ỷ", "ỹ", "đ", "Ầ", "Ấ", "Ậ", "Ẩ", "Ẫ", "À", "Á", "Ạ", "Ả", "Ã", "Â", "Ằ", "Ắ", "Ặ", "Ẳ", "Ẵ", "Ă", "Ề", "Ế", "Ệ", "Ể", "Ễ", "È", "É", "Ẹ", "Ẻ", "Ẽ", "Ê", "Ì", "Í", "Ị", "Ỉ", "Ĩ", "Ồ", "Ố", "Ộ", "Ổ", "Ỗ", "Ò", "Ó", "Ọ", "Ỏ", "Õ", "Ô", "Ờ", "Ớ", "Ợ", "Ở", "Ỡ", "Ơ", "Ù", "Ú", "Ụ", "Ủ", "Ũ", "Ừ", "Ứ", "Ự", "Ử", "Ữ", "Ư", "Ỳ", "Ý", "Ỵ", "Ỷ", "Ỹ", "Đ" };
/* 11 */   private static final String[] src = new String[] { "ầ", "ấ", "ậ", "ẩ", "ẫ", "à", "á", "ạ", "ả", "ã", "â", "ằ", "ắ", "ặ", "ẳ", "ẵ", "ă", "ề", "ế", "ệ", "ể", "ễ", "è", "é", "ẹ", "ẻ", "ẽ", "ê", "ì", "í", "ị", "ỉ", "ĩ", "ồ", "ố", "ộ", "ổ", "ỗ", "ò", "ó", "ọ", "ỏ", "õ", "ô", "ờ", "ớ", "ợ", "ở", "ỡ", "ơ", "ù", "ú", "ụ", "ủ", "ũ", "ừ", "ứ", "ự", "ử", "ữ", "ư", "ỳ", "ý", "ỵ", "ỷ", "ỹ", "đ", "Ầ", "Ấ", "Ậ", "Ẩ", "Ẫ", "À", "Á", "Ạ", "Ả", "Ã", "Â", "Ằ", "Ắ", "Ặ", "Ẳ", "Ẵ", "Ă", "Ề", "Ế", "Ệ", "Ể", "Ễ", "È", "É", "Ẹ", "Ẻ", "Ẽ", "Ê", "Ì", "Í", "Ị", "Ỉ", "Ĩ", "Ồ", "Ố", "Ộ", "Ổ", "Ỗ", "Ò", "Ó", "Ọ", "Ỏ", "Õ", "Ô", "Ờ", "Ớ", "Ợ", "Ở", "Ỡ", "Ơ", "Ù", "Ú", "Ụ", "Ủ", "Ũ", "Ừ", "Ứ", "Ự", "Ử", "Ữ", "Ư", "Ỳ", "Ý", "Ỵ", "Ỷ", "Ỹ", "Đ" };
/* 12 */   private static final String[] xoa = new String[] { "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "i", "i", "i", "i", "i", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "u", "u", "u", "u", "u", "u", "u", "u", "u", "u", "u", "y", "y", "y", "y", "y", "d", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "I", "I", "I", "I", "I", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "U", "U", "U", "U", "U", "U", "U", "U", "U", "U", "U", "Y", "Y", "Y", "Y", "Y", "D" };
/*    */   
/*    */   public static String[] REPLACE_FROM;
/*    */   public static String[] REPLACE_TO;
/*    */   public static String CHAP_NAME;
/*    */   public static String PART_NAME;
/*    */   
/*    */   public static String covertString(String source) {
/* 20 */     for (int i = 0; i < src.length; i++)
/* 21 */       source = source.replace(dau[i], src[i]); 
/* 22 */     return source;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String xoaDau(String source) {
/* 27 */     source = covertString(source);
/* 28 */     for (int i = 0; i < src.length; i++)
/* 29 */       source = source.replace(src[i], xoa[i]); 
/* 30 */     return source;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String fixName(String source) {
/* 35 */     if (source == null) return null;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     source = covertString(source).trim().replaceAll(":+", ":").replaceAll("\\.+", ".").replaceAll(" 0{1,2}(\\d+)", " $1");
/*    */     
/* 42 */     return source;
/*    */   }
/*    */   
/*    */   public static String Optimize(String src) {
/* 46 */     if (src == null) return src; 
/* 47 */     if (REPLACE_FROM != null)
/* 48 */       for (int i = 0; i < REPLACE_FROM.length; i++) {
/* 49 */         src = src.replaceAll(REPLACE_FROM[i], REPLACE_TO[i]);
/*    */       } 
/* 51 */     return src;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\SyntaxUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */