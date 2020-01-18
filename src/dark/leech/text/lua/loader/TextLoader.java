/*    */ package dark.leech.text.lua.loader;
/*    */ 
/*    */ import dark.leech.text.enities.PluginEntity;
/*    */ import dark.leech.text.lua.api.LuaScriptEngine;
/*    */ import dark.leech.text.util.TextUtils;
/*    */ import org.luaj.vm2.Globals;
/*    */ import org.luaj.vm2.LuaError;
/*    */ import org.luaj.vm2.LuaValue;
/*    */ 
/*    */ public class TextLoader
/*    */ {
/*    */   private PluginEntity plugin;
/*    */   
/*    */   public static TextLoader with(PluginEntity plugin) {
/* 15 */     return new TextLoader(plugin);
/*    */   }
/*    */   
/*    */   private TextLoader(PluginEntity plugin) {
/* 19 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */   public String load(String url) {
/* 23 */     if (!TextUtils.isEmpty(this.plugin.getChapGetter())) {
/* 24 */       LuaValue chuck; Globals globals = LuaScriptEngine.getInstance().getGlobals();
/*    */       
/*    */       try {
/* 27 */         chuck = globals.load(this.plugin.getChapGetter());
/* 28 */       } catch (LuaError error) {
/* 29 */         error.printStackTrace();
/* 30 */         return null;
/*    */       } 
/*    */       try {
/* 33 */         LuaValue result = chuck.call((LuaValue)LuaValue.valueOf(url));
/* 34 */         if (result != null && !result.isnil()) {
/* 35 */           if (result instanceof org.luaj.vm2.LuaTable)
/*    */           {
/* 37 */             return result.get("content").tojstring(); } 
/* 38 */           return result.tojstring();
/*    */         } 
/* 40 */       } catch (LuaError error) {
/* 41 */         error.printStackTrace();
/* 42 */         return null;
/*    */       } 
/*    */     } 
/* 45 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\loader\TextLoader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */