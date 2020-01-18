/*    */ package dark.leech.text.lua.api;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.luaj.vm2.LuaTable;
/*    */ import org.luaj.vm2.LuaValue;
/*    */ import org.luaj.vm2.lib.jse.CoerceJavaToLua;
/*    */ 
/*    */ 
/*    */ public class Json
/*    */ {
/*    */   public static LuaValue to_table(Object object) {
/* 15 */     if (object instanceof JSONObject)
/* 16 */       return json2table((JSONObject)object); 
/* 17 */     if (object instanceof JSONArray)
/* 18 */       return array2table((JSONArray)object); 
/* 19 */     if (object instanceof String)
/* 20 */       return string2table((String)object); 
/* 21 */     return string2table(object.toString());
/*    */   }
/*    */   private static LuaValue json2table(JSONObject obj) {
/*    */     LuaTable luaTable =null;
/* 25 */     LuaValue result = LuaValue.NIL;
/* 26 */     if (obj != null) {
/* 27 */       luaTable = new LuaTable();
/* 28 */       if (obj.length() > 0) {
/* 29 */         Iterator<String> iter = obj.keys();
/* 30 */         while (iter.hasNext()) {
/* 31 */           String key = iter.next();
/* 32 */           Object value = obj.opt(key);
/* 33 */           luaTable.set(key, value(value));
/*    */         } 
/*    */       } 
/*    */     } 
/* 37 */     return (LuaValue)luaTable;
/*    */   }
/*    */   
/*    */   private static LuaValue string2table(String jsonString) {
/* 41 */     LuaValue luaTable = LuaValue.NIL;
/*    */     try {
/* 43 */       luaTable = json2table(new JSONObject(jsonString));
/* 44 */     } catch (Exception e) {
/*    */       try {
/* 46 */         luaTable = array2table(new JSONArray(jsonString));
/* 47 */       } catch (JSONException jSONException) {}
/*    */     } 
/*    */     
/* 50 */     return luaTable;
/*    */   }
/*    */   private static LuaValue array2table(JSONArray obj) {
/*    */     LuaTable luaTable = null;
/* 54 */     LuaValue result = LuaValue.NIL;
/*    */     
/* 56 */     if (obj != null) {
/* 57 */       luaTable = new LuaTable();
/* 58 */       if (obj.length() > 0) {
/* 59 */         for (int i = 0; i < obj.length(); i++) {
/* 60 */           int key = i + 1;
/* 61 */           Object value = obj.opt(i);
/* 62 */           luaTable.set(key, value(value));
/*    */         } 
/*    */       }
/*    */     } 
/* 66 */     return (LuaValue)luaTable;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static LuaValue value(Object value) {
/* 76 */     if (value instanceof String)
/* 77 */       return (LuaValue)LuaValue.valueOf((String)value); 
/* 78 */     if (value instanceof Integer)
/* 79 */       return (LuaValue)LuaValue.valueOf(((Integer)value).intValue()); 
/* 80 */     if (value instanceof Long)
/* 81 */       return (LuaValue)LuaValue.valueOf(((Long)value).longValue()); 
/* 82 */     if (value instanceof Double)
/* 83 */       return (LuaValue)LuaValue.valueOf(((Double)value).doubleValue()); 
/* 84 */     if (value instanceof Boolean)
/* 85 */       return (LuaValue)LuaValue.valueOf(((Boolean)value).booleanValue()); 
/* 86 */     if (value instanceof JSONObject)
/* 87 */       return json2table((JSONObject)value); 
/* 88 */     if (value instanceof JSONArray) {
/* 89 */       return array2table((JSONArray)value);
/*    */     }
/* 91 */     return CoerceJavaToLua.coerce(value);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\Json.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */