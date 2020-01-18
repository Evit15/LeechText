/*     */ package dark.leech.text.util;
/*     */ 
/*     */ import dark.leech.text.models.Trash;
/*     */ import dark.leech.text.ui.notification.Toast;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SettingUtils
/*     */ {
/*     */   public static int MAX_CONN;
/*     */   public static int TIMEOUT;
/*     */   public static int RE_CONN;
/*     */   public static int DELAY;
/*     */   public static String USER_AGENT;
/*     */   public static boolean IS_DROP_SELECTED;
/*     */   public static boolean IS_HTML_SELECTED;
/*     */   public static boolean IS_TXT_SELECTED;
/*     */   public static boolean IS_CSS_SELECTED;
/*     */   public static String DROP_SYNTAX;
/*     */   public static String HTML_SYNTAX;
/*     */   public static String TXT_SYNTAX;
/*     */   public static String CSS_SYNTAX;
/*     */   public static List<Trash> TRASH;
/*     */   public static String WORKPATH;
/*     */   public static String KINDLEGEN;
/*     */   public static String CALIBRE;
/*     */   public static Color THEME_COLOR;
/*     */   
/*     */   public static void doLoad() {
/*  45 */     doDefault();
/*  46 */     String json = FileUtils.file2string(AppUtils.curDir + "/tools/setting.json");
/*  47 */     if (json != null)
/*  48 */       (new SettingUtils()).doLoad(new JSONObject(json), false); 
/*     */   }
/*     */   
/*     */   private void doLoad(JSONObject object, boolean IS_DEFAULT) {
/*  52 */     JSONObject connection = object.getJSONObject("connection");
/*  53 */     MAX_CONN = connection.getInt("num_conn");
/*  54 */     RE_CONN = connection.getInt("re_conn");
/*  55 */     DELAY = connection.getInt("delay");
/*  56 */     TIMEOUT = connection.getInt("time_out");
/*  57 */     USER_AGENT = connection.getString("user_agent");
/*     */     
/*  59 */     JSONObject style = object.getJSONObject("style");
/*  60 */     IS_CSS_SELECTED = style.getJSONObject("css").getBoolean("checked");
/*  61 */     if (IS_CSS_SELECTED || IS_DEFAULT)
/*  62 */       CSS_SYNTAX = style.getJSONObject("css").getString("value"); 
/*  63 */     IS_HTML_SELECTED = style.getJSONObject("html").getBoolean("checked");
/*  64 */     if (IS_HTML_SELECTED || IS_DEFAULT)
/*  65 */       HTML_SYNTAX = style.getJSONObject("html").getString("value"); 
/*  66 */     IS_TXT_SELECTED = style.getJSONObject("txt").getBoolean("checked");
/*  67 */     if (IS_TXT_SELECTED || IS_DEFAULT)
/*  68 */       TXT_SYNTAX = style.getJSONObject("txt").getString("value"); 
/*  69 */     IS_DROP_SELECTED = style.getJSONObject("dropcaps").getBoolean("checked");
/*  70 */     if (IS_DROP_SELECTED || IS_DEFAULT) {
/*  71 */       DROP_SYNTAX = style.getJSONObject("dropcaps").getString("value");
/*     */     }
/*  73 */     JSONObject other = object.getJSONObject("other");
/*  74 */     WORKPATH = other.getString("workspace");
/*  75 */     if (WORKPATH.length() == 0) WORKPATH = AppUtils.curDir; 
/*  76 */     CALIBRE = other.getString("calibre");
/*  77 */     KINDLEGEN = other.getString("kindlegen");
/*  78 */     List<Trash> trash = new ArrayList<>();
/*  79 */     JSONArray trashArr = other.getJSONArray("trash");
/*  80 */     for (int i = 0; i < trashArr.length(); i++) {
/*  81 */       JSONObject obj = trashArr.getJSONObject(i);
/*  82 */       trash.add(new Trash(obj.getString("src"), obj.getString("to"), obj.getString("tip"), obj.getBoolean("replace")));
/*     */     } 
/*  84 */     TRASH = trash;
/*  85 */     String color = other.getString("theme_color");
/*  86 */     if (color != null && 
/*  87 */       color.length() > 0)
/*  88 */       THEME_COLOR = Color.decode(color); 
/*     */   }
/*     */   
/*     */   public static void doSave() {
/*  92 */     JSONObject stt = new JSONObject();
/*  93 */     JSONObject connection = new JSONObject();
/*  94 */     connection.put("num_conn", MAX_CONN);
/*  95 */     connection.put("re_conn", RE_CONN);
/*  96 */     connection.put("time_out", TIMEOUT);
/*  97 */     connection.put("delay", DELAY);
/*  98 */     connection.put("user_agent", USER_AGENT);
/*  99 */     stt.put("connection", connection);
/*     */     
/* 101 */     JSONObject style = new JSONObject();
/* 102 */     style.put("dropcaps", getObject(IS_DROP_SELECTED, DROP_SYNTAX));
/* 103 */     style.put("html", getObject(IS_HTML_SELECTED, HTML_SYNTAX));
/* 104 */     style.put("txt", getObject(IS_TXT_SELECTED, TXT_SYNTAX));
/* 105 */     style.put("css", getObject(IS_CSS_SELECTED, CSS_SYNTAX));
/* 106 */     stt.put("style", style);
/*     */     
/* 108 */     JSONObject other = new JSONObject();
/* 109 */     other.put("workspace", WORKPATH);
/* 110 */     other.put("calibre", CALIBRE);
/* 111 */     other.put("kindlegen", KINDLEGEN);
/* 112 */     other.put("theme_color", getHexColor(THEME_COLOR));
/* 113 */     other.put("trash", new JSONArray(TRASH));
/* 114 */     stt.put("other", other);
/*     */     
/* 116 */     FileUtils.string2file(stt.toString(), AppUtils.curDir + "/tools/setting.json");
/* 117 */     Toast.Build()
/* 118 */       .font(FontUtils.TITLE_NORMAL)
/* 119 */       .content("Đã lưu cài đặt!")
/* 120 */       .open();
/*     */   }
/*     */   
/*     */   private static String getHexColor(Color color) {
/* 124 */     String hex = Integer.toHexString(color.getRGB() & 0xFFFFFF);
/* 125 */     if (hex.length() < 6) {
/* 126 */       hex = "0" + hex;
/*     */     }
/* 128 */     hex = "#" + hex;
/* 129 */     return hex;
/*     */   }
/*     */   
/*     */   public static void doDefault() {
/* 133 */     (new SettingUtils()).doLoad(new JSONObject(FileUtils.stream2string("/dark/leech/res/setting.json")), true);
/*     */   }
/*     */   
/*     */   private static JSONObject getObject(boolean b, String value) {
/* 137 */     JSONObject object = new JSONObject();
/* 138 */     object.put("checked", new Boolean(b));
/* 139 */     object.put("value", value);
/* 140 */     return object;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\SettingUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */