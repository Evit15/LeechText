/*    */ package dark.leech.text.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CookiesUtils {
/*  7 */   private static Map<String, String> COOKIES = new HashMap<>();
/*    */ 
/*    */   
/*    */   public static String getCookies(String url) {
/* 11 */     return COOKIES.get(parseKey(url));
/*    */   }
/*    */   
/*    */   public static void put(String url, String cookies) {
/* 15 */     COOKIES.put(parseKey(url), cookies);
/*    */   }
/*    */   
/*    */   public static void remove(String url) {
/* 19 */     COOKIES.remove(parseKey(url));
/*    */   }
/*    */   
/*    */   private static String parseKey(String url) {
/* 23 */     return RegexUtils.find(url, "https{0,1}://(.*?)/", 1);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\CookiesUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */