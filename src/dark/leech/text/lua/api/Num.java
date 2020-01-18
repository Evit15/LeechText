/*    */ package dark.leech.text.lua.api;
/*    */ 
/*    */ import org.luaj.vm2.LuaValue;
/*    */ import org.luaj.vm2.lib.jse.CoerceJavaToLua;
/*    */ 
/*    */ public class Num {
/*    */   public static LuaValue to_int(Object text, Object def) {
/*    */     try {
/*  9 */       return (LuaValue)LuaValue.valueOf((int)Double.parseDouble(text.toString()));
/* 10 */     } catch (Exception e) {
/* 11 */       return CoerceJavaToLua.coerce(def);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static LuaValue to_double(Object text, Object def) {
/*    */     try {
/* 17 */       return (LuaValue)LuaValue.valueOf(Double.parseDouble(text.toString()));
/* 18 */     } catch (Exception e) {
/* 19 */       return CoerceJavaToLua.coerce(def);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static LuaValue to_float(Object text, Object def) {
/*    */     try {
/* 25 */       return (LuaValue)LuaValue.valueOf(Float.parseFloat(text.toString()));
/* 26 */     } catch (Exception e) {
/* 27 */       return CoerceJavaToLua.coerce(def);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static LuaValue to_long(Object text, Object def) {
/*    */     try {
/* 33 */       return (LuaValue)LuaValue.valueOf(Long.parseLong(text.toString()));
/* 34 */     } catch (Exception e) {
/* 35 */       return CoerceJavaToLua.coerce(def);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\Num.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */