/*     */ package dark.leech.text.lua.api;
/*     */ 
/*     */ import org.luaj.vm2.LuaBoolean;
/*     */ import org.luaj.vm2.LuaNumber;
/*     */ import org.luaj.vm2.LuaTable;
/*     */ import org.luaj.vm2.LuaValue;
/*     */ 
/*     */ public class Text
/*     */ {
/*     */   public static LuaValue index_of(Object str, Object s) {
/*  11 */     return index_of(str, s, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public static LuaValue last_index_of(Object str, Object s) {
/*  15 */     return last_index_of(str, s, Integer.valueOf(str.toString().length()));
/*     */   }
/*     */   
/*     */   public static LuaValue index_of(Object str, Object s, Object start) {
/*  19 */     if (str == null) return LuaValue.NIL; 
/*  20 */     int index = 0;
/*  21 */     if (start instanceof Number) {
/*  22 */       index = ((Number)start).intValue();
/*  23 */     } else if (start instanceof LuaNumber) {
/*  24 */       index = ((LuaNumber)start).toint();
/*     */     } else {
/*     */       try {
/*  27 */         index = str.toString().indexOf(start.toString());
/*  28 */       } catch (Exception exception) {}
/*     */     } 
/*     */     
/*  31 */     return (LuaValue)LuaValue.valueOf(str.toString().indexOf(s.toString(), index));
/*     */   }
/*     */   
/*     */   public static LuaValue last_index_of(Object str, Object s, Object start) {
/*  35 */     if (str == null) return LuaValue.NIL; 
/*  36 */     int index = 0;
/*  37 */     if (start instanceof Number) {
/*  38 */       index = ((Number)start).intValue();
/*  39 */     } else if (start instanceof LuaNumber) {
/*  40 */       index = ((LuaNumber)start).toint();
/*     */     } else {
/*     */       try {
/*  43 */         index = str.toString().indexOf(start.toString());
/*  44 */       } catch (Exception exception) {}
/*     */     } 
/*     */     
/*  47 */     return (LuaValue)LuaValue.valueOf(str.toString().lastIndexOf(s.toString(), index));
/*     */   }
/*     */ 
/*     */   
/*     */   public static LuaValue sub(Object str, Object s, Object e) {
/*  52 */     String source = str.toString();
/*  53 */     int start = Num.to_int(s, Integer.valueOf(0)).toint();
/*  54 */     int end = Num.to_int(e, Integer.valueOf(0)).toint();
/*  55 */     if (!is_empty(source) && 
/*  56 */       start != -1 && end != -1 && start < end && end <= source.length()) {
/*  57 */       return (LuaValue)LuaValue.valueOf(source.substring(start, end));
/*     */     }
/*     */     
/*  60 */     return LuaValue.NIL;
/*     */   }
/*     */   
/*     */   public static LuaValue sub_first(Object str, Object i) {
/*  64 */     String source = str.toString();
/*  65 */     int index = Num.to_int(i, Integer.valueOf(0)).toint();
/*  66 */     if (!is_empty(source) && 
/*  67 */       index != -1 && index < source.length()) {
/*  68 */       return (LuaValue)LuaValue.valueOf(source.substring(0, index));
/*     */     }
/*     */     
/*  71 */     return LuaValue.NIL;
/*     */   }
/*     */   
/*     */   public static LuaValue sub_last(Object str, Object i) {
/*  75 */     String source = str.toString();
/*  76 */     int index = Num.to_int(i, Integer.valueOf(0)).toint();
/*  77 */     if (!is_empty(source) && 
/*  78 */       index != -1 && index < source.length()) {
/*  79 */       return (LuaValue)LuaValue.valueOf(source.substring(index));
/*     */     }
/*     */     
/*  82 */     return LuaValue.NIL;
/*     */   }
/*     */ 
/*     */   
/*     */   public static LuaValue sub_between(Object str, Object och1, Object och2) {
/*  87 */     String source = str.toString();
/*  88 */     String ch1 = och1.toString();
/*  89 */     String ch2 = och2.toString();
/*  90 */     if (!is_empty(source)) {
/*  91 */       int index1 = source.indexOf(ch1);
/*  92 */       if (index1 != -1) {
/*  93 */         index1 += ch1.length();
/*  94 */         int index2 = source.indexOf(ch2, index1);
/*  95 */         if (index2 > index1) {
/*  96 */           return (LuaValue)LuaValue.valueOf(source.substring(index1, index2));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 101 */     return LuaValue.NIL;
/*     */   }
/*     */   
/*     */   public static LuaValue split(Object text, Object regex) {
/* 105 */     if (is_empty(text)) return LuaValue.NIL; 
/* 106 */     LuaTable table = new LuaTable();
/* 107 */     String[] spl = text.toString().split(regex.toString());
/* 108 */     for (int i = 0; i < spl.length; i++) {
/* 109 */       table.set(i + 1, (LuaValue)LuaValue.valueOf(spl[i]));
/*     */     }
/*     */     
/* 112 */     if (table.length() == 0) return LuaValue.NIL; 
/* 113 */     return (LuaValue)table;
/*     */   }
/*     */   
/*     */   public static LuaValue trim(Object source) {
/* 117 */     return (LuaValue)LuaValue.valueOf(source.toString().trim());
/*     */   }
/*     */   
/*     */   public static boolean is_empty(Object source) {
/* 121 */     if (source == null) return true; 
/* 122 */     if (source instanceof LuaValue && (
/* 123 */       (LuaValue)source).isnil()) return true;
/*     */     
/* 125 */     return (source.toString().length() == 0);
/*     */   }
/*     */   
/*     */   public static LuaValue contains(Object obj, Object value) {
/* 129 */     return (LuaValue)LuaValue.valueOf(obj.toString().contains(value.toString()));
/*     */   }
/*     */   
/*     */   public static LuaValue replace(Object str, Object regex, Object e) {
/* 133 */     if (str != null)
/* 134 */       return (LuaValue)LuaValue.valueOf(str.toString().replaceAll(regex.toString(), e.toString())); 
/* 135 */     return LuaValue.NIL;
/*     */   }
/*     */   
/*     */   public static LuaValue remove(Object str, Object replace) {
/* 139 */     return remove(str, replace, Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public static LuaValue remove(Object str, Object replace, Object regex) {
/* 143 */     String text = str.toString();
/* 144 */     boolean usingRegex = true;
/* 145 */     if (regex instanceof Boolean) {
/* 146 */       usingRegex = ((Boolean)regex).booleanValue();
/* 147 */     } else if (regex instanceof LuaBoolean) {
/* 148 */       usingRegex = ((LuaBoolean)regex).booleanValue();
/*     */     } 
/*     */     
/* 151 */     if (replace instanceof LuaTable)
/* 152 */     { LuaTable table = (LuaTable)replace;
/* 153 */       LuaValue[] keys = table.keys();
/* 154 */       for (LuaValue value : keys) {
/* 155 */         if (usingRegex)
/* 156 */         { text = text.replaceAll(table.get(value).tojstring(), ""); }
/* 157 */         else { text = text.replace(table.get(value).tojstring(), ""); }
/*     */       
/*     */       }  }
/* 160 */     else if (usingRegex)
/* 161 */     { text = text.replaceAll(replace.toString(), ""); }
/* 162 */     else { text = text.replace(replace.toString(), ""); }
/*     */     
/* 164 */     return (LuaValue)LuaValue.valueOf(text);
/*     */   }
/*     */   
/*     */   public static LuaValue json_table(Object object) {
/* 168 */     if (object instanceof LuaTable) return (LuaValue)object; 
/* 169 */     return Json.to_table(object);
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\Text.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */