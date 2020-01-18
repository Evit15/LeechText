/*    */ package dark.leech.text.util;
/*    */ 
/*    */ import java.awt.GraphicsDevice;
/*    */ import java.awt.GraphicsEnvironment;
/*    */ import java.awt.Point;
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AppUtils
/*    */ {
/*    */   public static final String VERSION = "2019.07.22";
/*    */   public static final String TIME = "00:00";
/* 17 */   public static String curDir = System.getProperty("user.dir");
/* 18 */   public static String cacheDir = curDir;
/* 19 */   public static final String SEPARATOR = System.getProperty("file.separator");
/* 20 */   public static Point LOCATION = new Point();
/* 21 */   private static final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
/* 22 */   public static final int width = gd.getDisplayMode().getWidth();
/* 23 */   public static final int height = gd.getDisplayMode().getHeight();
/*    */ 
/*    */   
/*    */   public static void doLoad() {
/*    */     try {
/* 28 */       if (curDir.endsWith(SEPARATOR)) curDir = curDir.substring(0, curDir.length() - 1); 
/* 29 */       JSONObject json = new JSONObject(FileUtils.stream2string("/dark/leech/res/syntax.json"));
/* 30 */       JSONObject find = json.getJSONObject("find");
/* 31 */       SyntaxUtils.CHAP_NAME = find.getString("chap");
/* 32 */       SyntaxUtils.PART_NAME = find.getString("part");
/* 33 */       JSONArray optimize = json.getJSONArray("optimize");
/* 34 */       String[] REPLACE_FROM = new String[optimize.length()];
/* 35 */       String[] REPLACE_TO = new String[optimize.length()];
/* 36 */       for (int i = 0; i < optimize.length(); i++) {
/* 37 */         REPLACE_FROM[i] = optimize.getJSONObject(i).getString("replace");
/* 38 */         REPLACE_TO[i] = optimize.getJSONObject(i).getString("to");
/*    */       } 
/* 40 */       SyntaxUtils.REPLACE_FROM = REPLACE_FROM;
/* 41 */       SyntaxUtils.REPLACE_TO = REPLACE_TO;
/*    */     }
/* 43 */     catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getX() {
/* 48 */     return LOCATION.x;
/*    */   }
/*    */   
/*    */   public static int getY() {
/* 52 */     return LOCATION.y;
/*    */   }
/*    */   
/*    */   public static Point getLocation() {
/* 56 */     return LOCATION;
/*    */   }
/*    */   
/*    */   public static void pause(int milliseconds) {
/*    */     try {
/* 61 */       Thread.sleep(milliseconds);
/* 62 */     } catch (InterruptedException interruptedException) {}
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\AppUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */