/*     */ package dark.leech.text.action.export;
/*     */ 
/*     */ import dark.leech.text.action.Log;
/*     */ import dark.leech.text.listeners.ProgressListener;
/*     */ import dark.leech.text.models.Chapter;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.models.Trash;
/*     */ import dark.leech.text.util.FileUtils;
/*     */ import dark.leech.text.util.RegexUtils;
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import dark.leech.text.util.SyntaxUtils;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Text
/*     */ {
/*     */   private Properties properties;
/*     */   private int type;
/*     */   private boolean makeToc;
/*     */   private boolean includeCss;
/*     */   private int tach;
/*     */   private ProgressListener progressListener;
/*     */   private String syntax;
/*     */   private String charset;
/*     */   private List<Chapter> chapList;
/*     */   
/*     */   public Text(Properties properties, int type, boolean makeToc, boolean includeCss, int tach) {
/*  29 */     this.properties = properties;
/*  30 */     this.type = type;
/*  31 */     this.makeToc = makeToc;
/*  32 */     this.includeCss = includeCss;
/*  33 */     this.tach = tach;
/*  34 */     this.charset = properties.getCharset();
/*     */   }
/*     */ 
/*     */   
/*     */   public void export() {
/*  39 */     if (this.type == 0) {
/*  40 */       this.syntax = SettingUtils.HTML_SYNTAX.replaceAll("[Uu][Tt][Ff]-8", this.charset);
/*  41 */       exportTach(".html");
/*     */     } else {
/*  43 */       this.syntax = SettingUtils.TXT_SYNTAX;
/*  44 */       exportTach(".txt");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void exportTach(String duoi) {
/*  51 */     this.chapList = this.properties.getChapList();
/*  52 */     StringBuffer gop = new StringBuffer();
/*     */ 
/*     */     
/*  55 */     if (this.tach == 1 && 
/*  56 */       this.type == 0) {
/*  57 */       String head = this.syntax;
/*  58 */       head = head.replaceAll("<title>.*?</title>", "<title>" + this.properties.getName() + "</title>");
/*  59 */       head = head.replaceAll("(?s)(.*?<body.*?>).*", "$1");
/*  60 */       if (this.includeCss)
/*  61 */         head = head.replace("</head>", "<style>\n" + SettingUtils.CSS_SYNTAX + "\n</style>\n</head>"); 
/*  62 */       gop.append(head);
/*     */     } 
/*     */     
/*  65 */     if (this.properties.isAddGt()) {
/*  66 */       Chapter gt = new Chapter();
/*  67 */       gt.setChapName("Giới Thiệu");
/*  68 */       gt.setId("gioithieu");
/*  69 */       String text = "";
/*     */       try {
/*  71 */         text = chapterReplace(gt, false);
/*  72 */       } catch (Exception e) {
/*  73 */         Log.add(e);
/*     */       } 
/*  75 */       if (this.tach == 0) {
/*  76 */         String savePath = this.properties.getSavePath() + "/data/Text/" + gt.getId() + duoi;
/*  77 */         FileUtils.string2file(text, savePath, this.charset);
/*     */       } else {
/*  79 */         if (this.type == 0)
/*  80 */         { text = text.replaceAll("(?s).*?<body.*?>(.*?)</body>.*", "$1"); }
/*  81 */         else { text = text + "\n\n"; }
/*  82 */          gop.append(text);
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     if (this.makeToc && this.type == 0) {
/*  87 */       String toc = "\n<h4>Mục lục</h4>\n";
/*  88 */       if (this.tach == 0) {
/*  89 */         if (this.properties.isAddGt())
/*  90 */           toc = toc + "<div class=\"lv2\"><a href=\"../Text/gioithieu.html\">Giới Thiệu</a></div>\n"; 
/*  91 */         for (Chapter ch : this.chapList)
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*  96 */           toc = toc + "<div class=\"lv2\"><a href=\"../Text/" + ch.getId() + ".html\">" + ((ch.getPartName().length() == 0) ? "" : (ch.getPartName() + " - ")) + ch.getChapName() + "</a></div>\n";
/*     */         }
/*  98 */         String head = this.syntax;
/*  99 */         head = head.replaceAll("<title>.*?</title>", "<title>" + this.properties.getName() + "</title>");
/* 100 */         head = head.replaceAll("(?s)(.*?<body.*?>).*", "$1");
/* 101 */         toc = head + toc + "</body>\n</html>";
/* 102 */         FileUtils.string2file(toc, this.properties.getSavePath() + "/data/Text/mucluc.html", this.charset);
/*     */       } else {
/*     */         
/* 105 */         if (this.properties.isAddGt())
/* 106 */           toc = toc + "<div class=\"lv2\"><a href=\"#gioithieu\">Giới Thiệu</a></div>\n"; 
/* 107 */         for (Chapter ch : this.chapList) {
/* 108 */           toc = toc + "<div class=\"lv2\"><a href=\"#" + ch.getId() + "\">" + ((ch.getPartName().length() == 0) ? "" : (ch.getPartName() + " - ")) + ch.getChapName() + "</a></div>\n";
/*     */         }
/* 110 */         gop.append(toc);
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     int value = 0;
/*     */     
/* 116 */     for (Chapter ch : this.chapList) {
/* 117 */       if (ch.isCompleted()) {
/* 118 */         String text = "";
/*     */         try {
/* 120 */           text = chapterReplace(ch);
/* 121 */         } catch (Exception exception) {}
/*     */         
/* 123 */         if (this.tach == 0) {
/* 124 */           String savePath = this.properties.getSavePath() + "/data/Text/" + ch.getId() + duoi;
/* 125 */           FileUtils.string2file(text, savePath, this.charset);
/*     */         } else {
/* 127 */           if (this.type == 0)
/* 128 */           { text = text.replaceAll("(?s).*?<body.*?>(.*?)</body>.*", "$1"); }
/* 129 */           else { text = text + "\n\n"; }
/* 130 */            gop.append(text);
/*     */         } 
/* 132 */         value++;
/* 133 */         if (this.progressListener != null)
/* 134 */           this.progressListener.setProgress(value * 100 / this.properties.getSize(), "[2/3]Xuất text..."); 
/*     */       } 
/*     */     } 
/* 137 */     if (this.tach == 1) {
/* 138 */       if (this.type == 0)
/* 139 */         gop.append("</body>\n</html>"); 
/* 140 */       FileUtils.string2file(gop.toString(), this.properties.getSavePath() + "/out/text" + duoi, this.charset);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String clearText(String text) {
/* 146 */     for (Trash tr : SettingUtils.TRASH) {
/* 147 */       if (tr.isReplace()) {
/*     */ 
/*     */ 
/*     */         
/* 151 */         String src = tr.getSrc().replace("\\n", "\n").replace("\\r", "\r").replace("\\t", "\t");
/*     */ 
/*     */ 
/*     */         
/* 155 */         String to = tr.getTo().replace("\\n", "\n").replace("\\r", "\r").replace("\\t", "\t");
/* 156 */         text = text.replaceAll(src, to);
/*     */       } 
/* 158 */     }  return SyntaxUtils.covertString(text);
/*     */   }
/*     */   
/*     */   private String chapterReplace(Chapter chapter) throws Exception {
/* 162 */     return chapterReplace(chapter, true);
/*     */   }
/*     */   
/*     */   private String chapterReplace(Chapter chapter, boolean pp) throws Exception {
/* 166 */     String nd = FileUtils.file2string(this.properties.getSavePath() + "/raw/" + chapter.getId() + ".txt", this.charset);
/* 167 */     nd = clearText(nd);
/* 168 */     String text = this.syntax;
/*     */     
/* 170 */     text = replaceString(text, this.properties.getName(), "\\[1\\]", "NAME");
/* 171 */     text = replaceString(text, this.properties.getAuthor(), "\\[2\\]", "AUTHOR");
/* 172 */     text = replaceString(text, chapter.getPartName(), "\\[3\\]", "NAME_PART");
/* 173 */     text = replaceString(text, chapter.getChapName(), "\\[4\\]", "NAME_CHAP");
/* 174 */     text = text.replace("[ID]", chapter.getId());
/*     */     
/* 176 */     String firstTag = RegexUtils.find(this.syntax, "\\[5\\](.*)\\[PARAGRAPH\\](.*)\\[5\\]", 1);
/* 177 */     String lastTag = RegexUtils.find(this.syntax, "\\[5\\](.*)\\[PARAGRAPH\\](.*)\\[5\\]", 2);
/* 178 */     nd = replaceDrop(nd);
/* 179 */     if (pp) {
/* 180 */       nd = nd.replace("\n", lastTag + "\n" + firstTag);
/* 181 */       nd = firstTag + nd + lastTag;
/*     */     } 
/* 183 */     text = text.replace(RegexUtils.find(text, "(\\[5\\].*\\[PARAGRAPH\\].*\\[5\\])", 1), nd);
/*     */     
/* 185 */     return text;
/*     */   }
/*     */   
/*     */   private String replaceString(String src, String replace, String tag, String key) {
/* 189 */     if (replace == null || replace.length() < 2) {
/* 190 */       return src.replaceAll("\\s*" + tag + ".*?" + tag, "");
/*     */     }
/* 192 */     src = src.replaceAll(tag + "(.*?\\[" + key + "\\].*?)" + tag, "$1").replace("[" + key + "]", replace);
/* 193 */     return src;
/*     */   }
/*     */   
/*     */   private String replaceDrop(String text) {
/* 197 */     if (text.length() == 0) return text; 
/* 198 */     char drop = text.charAt(0);
/*     */     
/* 200 */     if (SettingUtils.IS_DROP_SELECTED && this.type == 0 && 
/* 201 */       drop != Character.toLowerCase(drop)) {
/* 202 */       String newt = SettingUtils.DROP_SYNTAX.replace("[DROP]", String.valueOf(drop));
/* 203 */       text = newt + text.substring(1);
/*     */     } 
/* 205 */     return text;
/*     */   }
/*     */   
/*     */   public void addProgressListener(ProgressListener progressListener) {
/* 209 */     this.progressListener = progressListener;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\action\export\Text.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */