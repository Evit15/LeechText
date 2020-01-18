/*    */ package dark.leech.text.action;
/*    */ 
/*    */ import dark.leech.text.util.FileUtils;
/*    */ import dark.leech.text.util.SettingUtils;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Arrays;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Log
/*    */ {
/*    */   private static File log;
/*    */   
/*    */   public static void gen() {
/* 20 */     Date todaysDate = new Date();
/* 21 */     DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
/* 22 */     String date = "Logs_" + df.format(todaysDate) + ".txt";
/* 23 */     File dir = new File(FileUtils.validate(SettingUtils.WORKPATH + "/tools/logs"));
/* 24 */     if (!dir.exists())
/* 25 */       dir.mkdir(); 
/* 26 */     log = new File(FileUtils.validate(SettingUtils.WORKPATH + "/tools/logs/" + date));
/* 27 */     if (!log.exists()) {
/*    */       try {
/* 29 */         log.createNewFile();
/* 30 */       } catch (IOException iOException) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public static void add(String logStr) {
/* 35 */     System.out.println(logStr);
/* 36 */     if (log == null) gen(); 
/* 37 */     Date todaysDate = new Date();
/* 38 */     DateFormat df = new SimpleDateFormat("HH:mm:ss");
/* 39 */     FileUtils.add2file("\n----------------" + df.format(todaysDate) + "----------------\n", log);
/* 40 */     FileUtils.add2file(logStr, log);
/*    */   }
/*    */   
/*    */   public static void add(Exception e) {
/* 44 */     e.printStackTrace();
/* 45 */     if (log == null) gen(); 
/* 46 */     Date todaysDate = new Date();
/* 47 */     DateFormat df = new SimpleDateFormat("HH:mm:ss");
/* 48 */     FileUtils.add2file("\n----------------" + df.format(todaysDate) + "----------------\n", log);
/* 49 */     FileUtils.add2file(Arrays.toString((Object[])e.getStackTrace()), log);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\action\Log.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */