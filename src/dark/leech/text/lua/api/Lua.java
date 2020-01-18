/*    */ package dark.leech.text.lua.api;
/*    */ 
/*    */ import org.luaj.vm2.LuaTable;
/*    */ import org.luaj.vm2.LuaValue;
/*    */ 
/*    */ public class Lua
/*    */ {
/*    */   public static void forEach(LuaTable table, TableAction action) {
/*  9 */     LuaValue[] keys = table.keys();
/* 10 */     for (LuaValue key : keys)
/* 11 */       action.action(key.tojstring(), table.get(key)); 
/*    */   }
/*    */   
/*    */   public static interface TableAction {
/*    */     void action(String param1String, LuaValue param1LuaValue);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\Lua.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */