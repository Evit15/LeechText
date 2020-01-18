/*    */ package dark.leech.text.lua.api;
/*    */ 
/*    */ import dark.leech.text.util.CookiesUtils;
/*    */ 
/*    */ public class CookieManager {
/*    */   public static CookieManager getInstance() {
/*  7 */     return new CookieManager();
/*    */   }
/*    */   
/*    */   public void setCookie(String url, String cookie) {
/* 11 */     CookiesUtils.put(url, cookie);
/*    */   }
/*    */   
/*    */   public String getCookie(String url) {
/* 15 */     return CookiesUtils.getCookies(url);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\CookieManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */