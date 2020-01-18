/*     */ package dark.leech.text.action.export;
/*     */ 
/*     */ import dark.leech.text.action.Log;
/*     */ import dark.leech.text.listeners.ProgressListener;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.ui.notification.Alert;
/*     */ import dark.leech.text.util.FileUtils;
/*     */ import dark.leech.text.util.RegexUtils;
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import dark.leech.text.util.ZipUtils;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import org.zeroturnaround.zip.ZipUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ebook
/*     */ {
/*     */   private Properties properties;
/*     */   private boolean autoSplit;
/*     */   private String compressLevel;
/*     */   
/*     */   public Ebook(Properties properties) {
/*  26 */     this.properties = properties;
/*     */   }
/*     */   private String tool; private boolean includeImg; private int type; private ProgressListener progressListener;
/*     */   public void setData(int type, String tool, String compressLevel, boolean autoSplit, boolean includeImg) {
/*  30 */     this.type = type;
/*  31 */     this.tool = tool;
/*  32 */     this.compressLevel = compressLevel;
/*  33 */     this.autoSplit = autoSplit;
/*  34 */     this.includeImg = includeImg;
/*     */   }
/*     */   
/*     */   public void export() {
/*  38 */     switch (this.type) {
/*     */       case 0:
/*  40 */         if (this.tool.equals("Calibre")) {
/*  41 */           this.tool = SettingUtils.CALIBRE;
/*  42 */           if (!checkTool(this.tool)) {
/*  43 */             error(1);
/*     */             return;
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/*  49 */         if (this.tool.equals("Calibre")) {
/*  50 */           this.tool = SettingUtils.CALIBRE;
/*  51 */           if (!checkTool(this.tool)) {
/*  52 */             error(1); return;
/*     */           } 
/*     */           break;
/*     */         } 
/*  56 */         this.tool = SettingUtils.KINDLEGEN;
/*  57 */         if (!checkTool(this.tool)) {
/*  58 */           error(2);
/*     */           return;
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/*  64 */         this.tool = SettingUtils.CALIBRE;
/*  65 */         if (!checkTool(this.tool)) {
/*  66 */           error(1);
/*     */           return;
/*     */         } 
/*     */         break;
/*     */       case 3:
/*  71 */         this.tool = SettingUtils.CALIBRE;
/*  72 */         if (!checkTool(this.tool)) {
/*  73 */           error(1);
/*     */           return;
/*     */         } 
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/*  80 */     FileUtils.string2file(SettingUtils.CSS_SYNTAX, this.properties.getSavePath() + "/data/stylesheet.css");
/*  81 */     this.progressListener.setProgress(0, "[1/3]Xuất Text...");
/*  82 */     Text text = new Text(this.properties, 0, false, false, 0);
/*     */     
/*  84 */     text.addProgressListener(this.progressListener);
/*  85 */     text.export();
/*  86 */     this.progressListener.setProgress(10, "[2/3]Tạo mục lục...");
/*  87 */     ToC toC = new ToC(this.properties);
/*  88 */     toC.setAutoSplit(this.autoSplit);
/*  89 */     toC.setIncludeImg(this.includeImg);
/*  90 */     toC.mkToC();
/*  91 */     this.progressListener.setProgress(14, "[3/3]Tạo Ebook...");
/*  92 */     switch (this.type) {
/*     */       case 0:
/*  94 */         exportEpub(this.tool);
/*     */         break;
/*     */       case 1:
/*  97 */         exportMobi(this.tool);
/*     */         break;
/*     */       case 2:
/* 100 */         exportAzw3();
/*     */         break;
/*     */       case 3:
/* 103 */         exportPdf();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void error(int stt) {
/* 111 */     if (stt == 1)
/* 112 */     { Alert.show("Đường dẫn Calibre (ebook-convert.exe) không hợp lệ!\nXem lại thiết lập trong cài đặt!"); }
/* 113 */     else { Alert.show("Đường dẫn Kindlegen (Kindlegen.exe) không hợp lệ!\nXem lại thiết lập trong cài đặt!"); }
/*     */   
/*     */   }
/*     */   private void exportEpub(String tool) {
/* 117 */     if (tool.equals("Mặc định")) { try {
/* 118 */         exportEpub();
/* 119 */       } catch (Exception exception) {} }
/*     */     else
/*     */     
/* 122 */     { String fileName = this.properties.getName() + " - " + this.properties.getAuthor() + ".epub";
/* 123 */       fileName = fileName.replaceAll("[:/\\?\\*]", "");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 128 */       String cmd = tinyCmd(tool) + " " + tinyCmd(this.properties.getSavePath() + "/data/content.opf") + " " + tinyCmd(this.properties.getSavePath() + "/out/" + fileName);
/* 129 */       runCmd(cmd); }
/*     */   
/*     */   }
/*     */   
/*     */   private void exportMobi(String tool) {
/* 134 */     String cmd = "";
/* 135 */     if (tool.equals(SettingUtils.CALIBRE)) {
/* 136 */       tool = SettingUtils.CALIBRE;
/* 137 */       String fileName = this.properties.getName() + " - " + this.properties.getAuthor() + ".mobi";
/* 138 */       fileName = fileName.replaceAll("[:/\\?\\*]", "");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       cmd = tinyCmd(tool) + " " + tinyCmd(this.properties.getSavePath() + "/data/content.opf") + " " + tinyCmd(this.properties.getSavePath() + "/out/" + fileName) + " --mobi-file-type=" + this.compressLevel + " --no-inline-toc --share-not-sync";
/*     */ 
/*     */       
/* 146 */       runCmd(cmd);
/*     */     } else {
/* 148 */       tool = SettingUtils.KINDLEGEN;
/* 149 */       String fileName = this.properties.getName() + " - " + this.properties.getAuthor() + ".mobi";
/* 150 */       fileName = fileName.replaceAll("[:/\\?\\*]", "");
/*     */ 
/*     */       
/* 153 */       cmd = tinyCmd(tool) + " " + tinyCmd(this.properties.getSavePath() + "/data/content.opf") + " " + this.compressLevel;
/*     */ 
/*     */       
/* 156 */       runCmd(cmd);
/* 157 */       FileUtils.cutFile(this.properties.getSavePath() + "/data/content.mobi", this.properties.getSavePath() + "/out/" + fileName);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void exportEpub() throws Exception {
/* 162 */     InputStream in = getClass().getResourceAsStream("/dark/leech/res/untitled.epub");
/* 163 */     String fileName = this.properties.getName() + " - " + this.properties.getAuthor() + ".epub";
/* 164 */     fileName = fileName.replaceAll("[:/\\?\\*]", "");
/* 165 */     fileName = this.properties.getSavePath() + "/out/" + fileName;
/* 166 */     FileUtils.byte2file(FileUtils.stream2byte(in), fileName);
/* 167 */     ZipUtil.setDefaultCompressionLevel(Integer.parseInt(this.compressLevel));
/* 168 */     ZipUtils.addFolders(fileName, this.properties.getSavePath() + "/data/Text", "Text");
/* 169 */     this.progressListener.setProgress(75, "(3/3)Tạo Ebook...");
/* 170 */     ZipUtils.addFile(fileName, this.properties.getSavePath() + "/data/content.opf");
/* 171 */     ZipUtils.addFile(fileName, this.properties.getSavePath() + "/data/toc.ncx");
/* 172 */     ZipUtils.addFile(fileName, this.properties.getSavePath() + "/data/stylesheet.css");
/* 173 */     ZipUtils.addFile(fileName, this.properties.getSavePath() + "/data/cover.jpg");
/* 174 */     if (this.includeImg)
/* 175 */       ZipUtils.addFolders(fileName, this.properties.getSavePath() + "/data/Images"); 
/* 176 */     this.progressListener.setProgress(100, "Hoàn tất!");
/*     */   }
/*     */ 
/*     */   
/*     */   private void exportAzw3() {
/* 181 */     this.tool = SettingUtils.CALIBRE;
/* 182 */     String fileName = this.properties.getName() + " - " + this.properties.getAuthor() + ".azw3";
/* 183 */     fileName = fileName.replaceAll("[:/\\?\\*]", "");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     String cmd = tinyCmd(this.tool) + " " + tinyCmd(this.properties.getSavePath() + "/data/content.opf") + " " + tinyCmd(this.properties.getSavePath() + "/out/" + fileName) + " --share-not-sync --no-inline-toc";
/*     */     
/* 190 */     runCmd(cmd);
/*     */   }
/*     */   
/*     */   private void exportPdf() {
/* 194 */     this.tool = SettingUtils.CALIBRE;
/* 195 */     String fileName = this.properties.getName() + " - " + this.properties.getAuthor() + ".pdf";
/* 196 */     fileName = fileName.replaceAll("[:/\\?\\*]", "");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 201 */     String cmd = tinyCmd(this.tool) + " " + tinyCmd(this.properties.getSavePath() + "/data/content.opf") + " " + tinyCmd(this.properties.getSavePath() + "/out/" + fileName) + " --paper-size=" + this.compressLevel;
/*     */     
/* 203 */     runCmd(cmd);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createToc() {
/* 208 */     ToC tableOfContent = new ToC(this.properties);
/* 209 */     tableOfContent.setAutoSplit(this.autoSplit);
/* 210 */     tableOfContent.setIncludeImg(this.includeImg);
/* 211 */     tableOfContent.mkToC();
/*     */   }
/*     */   
/*     */   private boolean checkTool(String tool) {
/* 215 */     boolean b = Tool(tool);
/* 216 */     if (b)
/* 217 */       createToc(); 
/* 218 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean Tool(String tool) {
/* 223 */     if (tool == null) return false; 
/* 224 */     if (tool.length() < 2) return false; 
/* 225 */     File file = new File(tool);
/* 226 */     if (!file.exists()) return false; 
/* 227 */     if (file.isDirectory()) return false; 
/* 228 */     if (!tool.endsWith(".exe")) return false; 
/* 229 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void runCmd(String cmd) {
/*     */     try {
/* 235 */       Process p = Runtime.getRuntime().exec(cmd);
/* 236 */       InputStream s = p.getInputStream();
/*     */ 
/*     */       
/* 239 */       BufferedReader in = new BufferedReader(new InputStreamReader(s, "UTF-8"));
/*     */       
/* 241 */       int percent = 0; String temp;
/* 242 */       while ((temp = in.readLine()) != null) {
/* 243 */         String pe = RegexUtils.find(temp, "(^\\d+)%", 1);
/* 244 */         if (pe != null)
/* 245 */           percent = Integer.parseInt(pe); 
/* 246 */         this.progressListener.setProgress(percent, "[3/3]" + temp.replaceAll("[\n\r]", " "));
/*     */       } 
/* 248 */       this.progressListener.setProgress(100, "Hoàn tất!");
/*     */     }
/* 250 */     catch (Exception e) {
/* 251 */       e.printStackTrace();
/* 252 */       Log.add(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String tinyCmd(String cmd) {
/* 257 */     if (cmd.indexOf(" ") != -1)
/* 258 */       cmd = "\"" + cmd + "\""; 
/* 259 */     return FileUtils.validate(cmd);
/*     */   }
/*     */   
/*     */   public void addProgressListener(ProgressListener progressListener) {
/* 263 */     this.progressListener = progressListener;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\action\export\Ebook.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */