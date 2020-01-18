/*    */ package dark.leech.text.lua.api;
/*    */ 
/*    */ import org.luaj.vm2.Globals;
/*    */ import org.luaj.vm2.lib.jse.CoerceJavaToLua;
/*    */ import org.luaj.vm2.lib.jse.JsePlatform;
/*    */ 
/*    */ public class LuaScriptEngine
/*    */ {
/*    */   private static LuaScriptEngine engine;
/*    */   private Globals globals;
/* 11 */   private static final Object mLock = new Object();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static LuaScriptEngine getInstance() {
/* 23 */     return new LuaScriptEngine();
/*    */   }
/*    */   
/*    */   private LuaScriptEngine() {
/* 27 */     this.globals = JsePlatform.standardGlobals();
/* 28 */     this.globals.set("http", CoerceJavaToLua.coerce(new Http()));
/* 29 */     this.globals.set("regexp", CoerceJavaToLua.coerce(new Regexp()));
/* 30 */     this.globals.set("html", CoerceJavaToLua.coerce(new Html()));
/* 31 */     this.globals.set("core", CoerceJavaToLua.coerce(new Core()));
/* 32 */     this.globals.set("num", CoerceJavaToLua.coerce(new Num()));
/* 33 */     this.globals.set("text", CoerceJavaToLua.coerce(new Text()));
/*    */   }
/*    */   
/*    */   public Globals getGlobals() {
/* 37 */     return this.globals;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\LuaScriptEngine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */