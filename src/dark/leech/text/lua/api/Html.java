/*    */ package dark.leech.text.lua.api;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.net.URLEncoder;
/*    */ import org.jsoup.Jsoup;
/*    */ import org.jsoup.nodes.Element;
/*    */ import org.jsoup.select.Elements;
/*    */ import org.luaj.vm2.LuaTable;
/*    */ import org.luaj.vm2.LuaValue;
/*    */ import org.luaj.vm2.lib.jse.CoerceJavaToLua;
/*    */ 
/*    */ 
/*    */ public class Html
/*    */ {
/*    */   public LuaValue remove(Object input, LuaTable tags) {
/* 16 */     LuaValue[] keys = tags.keys();
/* 17 */     if (input instanceof Element) {
/* 18 */       for (LuaValue key : keys) {
/* 19 */         ((Element)input).select(tags.get(key).tojstring()).remove();
/*    */       }
/* 21 */     } else if (input instanceof Elements) {
/* 22 */       for (LuaValue key : keys) {
/* 23 */         ((Elements)input).select(tags.get(key).tojstring()).remove();
/*    */       }
/*    */     } 
/* 26 */     return CoerceJavaToLua.coerce(input);
/*    */   }
/*    */   
/*    */   public LuaValue url_encode(Object url, Object charset) {
/*    */     try {
/* 31 */       return (LuaValue)LuaValue.valueOf(URLEncoder.encode(url.toString(), charset.toString()));
/* 32 */     } catch (UnsupportedEncodingException e) {
/* 33 */       return LuaValue.NIL;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public LuaValue parse(Object object) {
/* 39 */     return CoerceJavaToLua.coerce(Jsoup.parse(object.toString()));
/*    */   }
/*    */   
/*    */   public LuaValue url_encode(Object url) {
/* 43 */     return url_encode(url, "UTF-8");
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\Html.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */