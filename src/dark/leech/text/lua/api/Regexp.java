/*    */ package dark.leech.text.lua.api;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import org.luaj.vm2.LuaTable;
/*    */ import org.luaj.vm2.LuaValue;
/*    */ import org.luaj.vm2.lib.jse.CoerceJavaToLua;
/*    */ 
/*    */ public class Regexp
/*    */ {
/*    */   public LuaValue find(Object str, Object regex) {
/* 12 */     return find(str, regex, Integer.valueOf(1));
/*    */   }
/*    */   
/*    */   public LuaValue find(Object source, Object regexp, Object group) {
/* 16 */     String str = source.toString();
/* 17 */     String regex = regexp.toString();
/* 18 */     int groupInt = 1;
/*    */     try {
/* 20 */       if (group instanceof Integer)
/* 21 */       { groupInt = ((Integer)group).intValue(); }
/*    */       else
/* 23 */       { groupInt = Integer.parseInt(group.toString()); } 
/* 24 */     } catch (Exception exception) {}
/*    */     
/*    */     try {
/* 27 */       Pattern r = Pattern.compile(regex, 8);
/* 28 */       Matcher m = r.matcher(str);
/* 29 */       if (m.find()) {
/* 30 */         return CoerceJavaToLua.coerce(m.group(groupInt));
/*    */       }
/* 32 */       return CoerceJavaToLua.coerce("");
/*    */     }
/* 34 */     catch (Exception e) {
/* 35 */       return CoerceJavaToLua.coerce("");
/*    */     } 
/*    */   }
/*    */   
/*    */   public LuaValue find_last(Object source, Object regexp) {
/* 40 */     return find_last(source, regexp, Integer.valueOf(1));
/*    */   }
/*    */   
/*    */   public LuaValue find_last(Object source, Object regexp, Object group) {
/* 44 */     String str = source.toString();
/* 45 */     String regex = regexp.toString();
/* 46 */     int groupInt = 1;
/*    */     try {
/* 48 */       if (group instanceof Integer)
/* 49 */       { groupInt = ((Integer)group).intValue(); }
/*    */       else
/* 51 */       { groupInt = Integer.parseInt(group.toString()); } 
/* 52 */     } catch (Exception exception) {}
/*    */     
/*    */     try {
/* 55 */       Pattern r = Pattern.compile(regex, 8);
/* 56 */       Matcher m = r.matcher(str);
/* 57 */       String result = null;
/* 58 */       while (m.find()) {
/* 59 */         result = m.group(groupInt);
/*    */       }
/* 61 */       return CoerceJavaToLua.coerce(result);
/* 62 */     } catch (Exception e) {
/* 63 */       return CoerceJavaToLua.coerce("");
/*    */     } 
/*    */   }
/*    */   
/*    */   public LuaValue find_all(Object source, Object regexp, Object group) {
/* 68 */     String str = source.toString();
/* 69 */     String regex = regexp.toString();
/* 70 */     int groupInt = 1;
/*    */     try {
/* 72 */       if (group instanceof Integer)
/* 73 */       { groupInt = ((Integer)group).intValue(); }
/*    */       else
/* 75 */       { groupInt = Integer.parseInt(group.toString()); } 
/* 76 */     } catch (Exception exception) {}
/*    */ 
/*    */     
/* 79 */     LuaTable table = new LuaTable();
/*    */     
/*    */     try {
/* 82 */       Pattern r = Pattern.compile(regex, 8);
/* 83 */       Matcher m = r.matcher(str);
/* 84 */       while (m.find()) {
/* 85 */         table.add((LuaValue)LuaValue.valueOf(m.group(groupInt)));
/*    */       }
/* 87 */       return (LuaValue)table;
/* 88 */     } catch (Exception e) {
/* 89 */       return LuaValue.NIL;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\Regexp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */