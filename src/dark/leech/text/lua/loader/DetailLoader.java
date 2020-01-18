/*    */ package dark.leech.text.lua.loader;
/*    */ 
/*    */ import dark.leech.text.enities.BookEntity;
/*    */ import dark.leech.text.enities.PluginEntity;
/*    */ import dark.leech.text.lua.api.LuaScriptEngine;
/*    */ import dark.leech.text.util.TextUtils;
/*    */ import org.luaj.vm2.Globals;
/*    */ import org.luaj.vm2.LuaError;
/*    */ import org.luaj.vm2.LuaValue;
/*    */ 
/*    */ public class DetailLoader
/*    */ {
/*    */   private PluginEntity plugin;
/*    */   
/*    */   public static DetailLoader with(PluginEntity plugin) {
/* 16 */     return new DetailLoader(plugin);
/*    */   }
/*    */   
/*    */   private DetailLoader(PluginEntity plugin) {
/* 20 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */   public BookEntity load(String url) {
/* 24 */     if (!TextUtils.isEmpty(this.plugin.getDetailGetter())) {
/* 25 */       LuaValue chuck, value; BookEntity entity = new BookEntity();
/* 26 */       Globals globals = LuaScriptEngine.getInstance().getGlobals();
/*    */       
/*    */       try {
/* 29 */         chuck = globals.load(this.plugin.getDetailGetter());
/* 30 */       } catch (LuaError e) {
/* 31 */         e.printStackTrace();
/* 32 */         return null;
/*    */       } 
/*    */       
/*    */       try {
/* 36 */         value = chuck.call((LuaValue)LuaValue.valueOf(url));
/* 37 */       } catch (LuaError error) {
/* 38 */         error.printStackTrace();
/* 39 */         return null;
/*    */       } 
/* 41 */       if (value == null || value.isnil()) return null; 
/* 42 */       if (!value.get("name").isnil())
/* 43 */         entity.setName(value.get("name").tojstring()); 
/* 44 */       if (!value.get("url").isnil() && !TextUtils.isEmpty(value.get("url").tojstring())) {
/* 45 */         entity.setUrl(value.get("url").tojstring());
/*    */       } else {
/* 47 */         entity.setUrl(url);
/* 48 */       }  if (!value.get("detail").isnil()) {
/* 49 */         String html = value.get("detail").tojstring();
/* 50 */         entity.setDetail(html.trim().replaceAll("\n+", "\n"));
/*    */       } 
/* 52 */       if (!value.get("description").isnil())
/* 53 */         entity.setIntroduce(value.get("description").tojstring()); 
/* 54 */       if (!value.get("cover").isnil())
/* 55 */         entity.setCover(value.get("cover").tojstring()); 
/* 56 */       if (!value.get("author").isnil())
/* 57 */         entity.setAuthor(value.get("author").tojstring()); 
/* 58 */       entity.setWebSource(this.plugin.getName());
/* 59 */       return entity;
/*    */     } 
/* 61 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\loader\DetailLoader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */