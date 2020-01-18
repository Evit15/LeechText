/*     */ package dark.leech.text.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ public class FileUtils
/*     */ {
/*     */   public static void init() {
/*  14 */     mkdir(validate(AppUtils.curDir + "/tools/plugins"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void mkdir(String dir) {
/*  19 */     File file = new File(validate(dir));
/*  20 */     if (file.exists() && 
/*  21 */       file.isDirectory())
/*     */       return; 
/*  23 */     String[] path = dir.split(Pattern.quote(AppUtils.SEPARATOR));
/*  24 */     if (path.length == 1)
/*     */       return; 
/*  26 */     dir = path[0];
/*  27 */     for (int i = 1; i < path.length; i++) {
/*  28 */       dir = dir + AppUtils.SEPARATOR + path[i];
/*  29 */       file = new File(dir);
/*  30 */       if (!file.exists())
/*  31 */         file.mkdir(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String file2string(String path, String charset) {
/*     */     try {
/*  37 */       return new String(file2byte(path), charset);
/*  38 */     } catch (Exception e) {
/*  39 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String file2string(String path) {
/*  44 */     return file2string(path, "UTF-8");
/*     */   }
/*     */   
/*     */   public static byte[] stream2byte(InputStream in) {
/*     */     try {
/*  49 */       byte[] bytes = new byte[in.available()];
/*  50 */       in.read(bytes);
/*  51 */       return bytes;
/*  52 */     } catch (Exception e) {
/*  53 */       e.printStackTrace();
/*  54 */       return null;
/*     */     } finally {
/*     */       try {
/*  57 */         if (in != null)
/*  58 */           in.close(); 
/*  59 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String stream2string(String path) {
/*  65 */     InputStream in = null;
/*     */     try {
/*  67 */       in = FileUtils.class.getResourceAsStream(path);
/*  68 */       byte[] bytes = new byte[in.available()];
/*  69 */       in.read(bytes);
/*  70 */       return new String(bytes, "UTF-8");
/*  71 */     } catch (Exception e) {
/*  72 */       e.printStackTrace();
/*  73 */       return "";
/*     */     } finally {
/*     */       try {
/*  76 */         if (in != null)
/*  77 */           in.close(); 
/*  78 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] file2byte(File file) {
/*  84 */     FileInputStream fi = null;
/*     */     try {
/*  86 */       if (!file.exists())
/*  87 */         return null; 
/*  88 */       fi = new FileInputStream(file);
/*  89 */       byte[] b = new byte[(int)file.length()];
/*  90 */       fi.read(b);
/*  91 */       return b;
/*  92 */     } catch (Exception e) {
/*  93 */       e.printStackTrace();
/*  94 */       return null;
/*     */     } finally {
/*     */       try {
/*  97 */         if (fi != null) fi.close(); 
/*  98 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] file2byte(String path) {
/* 104 */     return file2byte(new File(validate(path)));
/*     */   }
/*     */   
/*     */   public static void string2file(String source, String savepath) {
/* 108 */     string2file(source, savepath, "UTF-8");
/*     */   }
/*     */   
/*     */   public static void string2file(String src, String svp, String charset) {
/*     */     try {
/* 113 */       byte2file(src.getBytes(charset), svp);
/* 114 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public static synchronized void byte2file(byte[] source, String savepath) {
/* 119 */     FileOutputStream fo = null;
/*     */     try {
/* 121 */       File f = new File(validate(savepath));
/* 122 */       if (!f.exists())
/* 123 */         f.createNewFile(); 
/* 124 */       fo = new FileOutputStream(f);
/* 125 */       fo.write(source);
/* 126 */     } catch (Exception e) {
/* 127 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 130 */         if (fo != null) fo.close(); 
/* 131 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void add2file(String str, File file, String charset) {
/* 137 */     FileOutputStream fo = null;
/*     */     try {
/* 139 */       fo = new FileOutputStream(file, true);
/* 140 */       fo.write(str.getBytes(charset));
/* 141 */     } catch (Exception e) {
/* 142 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 145 */         if (fo != null) fo.close(); 
/* 146 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void add2file(String str, File file) {
/* 152 */     add2file(str, file, "UTF-8");
/*     */   }
/*     */   
/*     */   public static void add2file(File from, File src) {
/* 156 */     FileOutputStream fo = null;
/*     */     try {
/* 158 */       fo = new FileOutputStream(src, true);
/* 159 */       fo.write("\n".getBytes());
/* 160 */       fo.write(file2byte(from));
/* 161 */     } catch (Exception e) {
/* 162 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 165 */         if (fo != null) fo.close(); 
/* 166 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void copyFile(String from, String to) {
/* 172 */     byte2file(file2byte(from), to);
/*     */   }
/*     */   
/*     */   public static void cutFile(String from, String to) {
/* 176 */     copyFile(from, to);
/* 177 */     deleteFile(from);
/*     */   }
/*     */   
/*     */   public static void url2file(String url, String savePath) {
/*     */     try {
/* 182 */       byte2file(Http.connect(url)
/* 183 */           .execute()
/* 184 */           .bodyAsBytes(), savePath);
/* 185 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public static void deleteFile(String path) {
/*     */     try {
/* 191 */       File f = new File(validate(path));
/* 192 */       if (f.exists())
/* 193 */         f.delete(); 
/* 194 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public static synchronized String validate(String path) {
/* 199 */     return path.replace("/", AppUtils.SEPARATOR);
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\FileUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */