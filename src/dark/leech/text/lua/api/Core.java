/*    */ package dark.leech.text.lua.api;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.zip.GZIPInputStream;
/*    */ import org.luaj.vm2.LuaTable;
/*    */ import org.luaj.vm2.LuaValue;
/*    */ 
/*    */ 
/*    */ public class Core
/*    */ {
/*    */   public static LuaValue decode_gzip(byte[] data) {
/*    */     try {
/* 15 */       ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
/* 16 */       GZIPInputStream gzIn = new GZIPInputStream(byteIn);
/* 17 */       ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
/*    */       
/* 19 */       int res = 0;
/* 20 */       byte[] buf = new byte[1024];
/* 21 */       while (res >= 0) {
/* 22 */         res = gzIn.read(buf, 0, buf.length);
/* 23 */         if (res > 0) {
/* 24 */           byteOut.write(buf, 0, res);
/*    */         }
/*    */       } 
/* 27 */       return (LuaValue)LuaValue.valueOf(new String(byteOut.toByteArray(), Charset.forName("UTF-8")));
/* 28 */     } catch (Exception e) {
/* 29 */       return (LuaValue)LuaValue.valueOf("");
/*    */     } 
/*    */   }
/*    */   
/*    */   public static LuaValue new_chapter(Object name, Object url, Object host) {
/* 34 */     LuaTable value = new LuaTable();
/* 35 */     value.set("name", (LuaValue)LuaValue.valueOf(name.toString()));
/* 36 */     value.set("url", merge_url(host, url));
/* 37 */     return (LuaValue)value;
/*    */   }
/*    */   
/*    */   public static LuaValue new_chapter(Object name, Object url) {
/* 41 */     return new_chapter(name, url, "");
/*    */   }
/*    */   
/*    */   public static LuaValue new_search(Object name, Object url, Object cover, Object host) {
/* 45 */     LuaTable value = new LuaTable();
/* 46 */     value.set("name", (LuaValue)LuaValue.valueOf(name.toString()));
/* 47 */     value.set("url", merge_url(host, url));
/* 48 */     value.set("cover", merge_url(host, cover));
/* 49 */     return (LuaValue)value;
/*    */   }
/*    */   
/*    */   public static LuaValue new_search(Object name, Object url, Object cover) {
/* 53 */     return new_search(name, cover, url, "");
/*    */   }
/*    */   
/*    */   public static LuaValue new_search(Object name, Object url) {
/* 57 */     return new_search(name, url);
/*    */   }
/*    */ 
/*    */   
/*    */   public static LuaValue merge_url(Object host, Object url) {
/* 62 */     String newUrl = url.toString().trim();
/* 63 */     if (newUrl.length() == 0) return LuaValue.NIL; 
/* 64 */     String hostName = host.toString().trim();
/* 65 */     if (!newUrl.startsWith("http") && !hostName.isEmpty()) {
/* 66 */       if (newUrl.startsWith("/"))
/* 67 */         newUrl = newUrl.substring(1); 
/* 68 */       if (!hostName.endsWith("/"))
/* 69 */         hostName = hostName + "/"; 
/* 70 */       newUrl = hostName + newUrl;
/*    */     } 
/* 72 */     return (LuaValue)LuaValue.valueOf(newUrl);
/*    */   }
/*    */   
/*    */   public static LuaValue create_login(Object url) {
/* 76 */     return (LuaValue)LuaValue.valueOf("00#" + url);
/*    */   }
/*    */ 
/*    */   
/*    */   public static LuaValue create_confirm(Object url) {
/* 81 */     return (LuaValue)LuaValue.valueOf("1#" + url.toString());
/*    */   }
/*    */   
/*    */   public static LuaValue create_capcha(Object url) {
/* 85 */     return (LuaValue)LuaValue.valueOf("2#" + url.toString());
/*    */   }
/*    */   
/*    */   public static LuaValue create_purchase(Object url) {
/* 89 */     return (LuaValue)LuaValue.valueOf("3#" + url.toString());
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\Core.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */