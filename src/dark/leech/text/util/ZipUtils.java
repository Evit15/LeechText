/*    */ package dark.leech.text.util;
/*    */ 
/*    */ import dark.leech.text.action.Log;
/*    */ import java.io.File;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.util.ArrayList;
/*    */ import org.zeroturnaround.zip.FileSource;
/*    */ import org.zeroturnaround.zip.ZipEntrySource;
/*    */ import org.zeroturnaround.zip.ZipUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ZipUtils
/*    */ {
/*    */   public static void addFile(String zip, String file) {
/* 20 */     addFile(new File(FileUtils.validate(zip)), new File(FileUtils.validate(file)));
/*    */   }
/*    */   
/*    */   public static void addFile(File zip, File file) {
/* 24 */     addFile(zip, file, "");
/*    */   }
/*    */   
/*    */   public static void addFile(File zip, File file, String path) {
/* 28 */     if (!file.exists())
/* 29 */       return;  ZipUtil.addEntry(zip, ((path.length() == 0) ? "" : (path + "/")) + file.getName(), file);
/*    */   }
/*    */   
/*    */   public static void addFolders(String zip, String dir) {
/* 33 */     addFolders(zip, dir, "");
/*    */   }
/*    */   
/*    */   public static void addFolders(String zip, String dir, String path) {
/* 37 */     addFolders(new File(FileUtils.validate(zip)), new File(FileUtils.validate(dir)), path);
/*    */   }
/*    */   
/*    */   public static void addFolders(File zip, File dir) {
/* 41 */     addFolders(zip, dir, "");
/*    */   }
/*    */   
/*    */   public static void addFolders(File zip, File dir, String path) {
/* 45 */     ArrayList<ZipEntrySource> listEntry = new ArrayList<>();
/* 46 */     getListEntrySource(listEntry, dir, path);
/* 47 */     ZipUtil.addEntries(zip, listEntry.<ZipEntrySource>toArray(new ZipEntrySource[listEntry.size()]));
/*    */   }
/*    */   
/*    */   private static void getListEntrySource(ArrayList<ZipEntrySource> listEntrySources, File dir, String path) {
/* 51 */     File[] listFile = dir.listFiles();
/* 52 */     for (File f : listFile) {
/* 53 */       if (f.isDirectory()) {
/* 54 */         getListEntrySource(listEntrySources, f, path + "/" + f.getName());
/*    */       } else {
/* 56 */         listEntrySources.add(new FileSource(path + "/" + f.getName(), f));
/*    */       } 
/*    */     } 
/*    */   }
/*    */   public static byte[] readInZipAsByte(File zipfile, String filepath) {
/* 61 */     return ZipUtil.unpackEntry(zipfile, filepath);
/*    */   }
/*    */   
/*    */   public static String readInZipAsString(File zipfile, String filepath) {
/*    */     try {
/* 66 */       return new String(readInZipAsByte(zipfile, filepath), "UTF-8");
/* 67 */     } catch (UnsupportedEncodingException e) {
/* 68 */       Log.add(e.toString());
/* 69 */       return "";
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\ZipUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */