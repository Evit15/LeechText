/*    */ package dark.leech.text.util;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegexUtils
/*    */ {
/*    */   public static synchronized String find(String src, String regex, int group) {
/*    */     try {
/* 15 */       Pattern r = Pattern.compile(regex, 8);
/* 16 */       Matcher m = r.matcher(src);
/* 17 */       if (m.find()) {
/* 18 */         return m.group(group);
/*    */       }
/* 20 */       return null;
/* 21 */     } catch (Exception e) {
/* 22 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\RegexUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */