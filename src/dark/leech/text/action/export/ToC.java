/*     */ package dark.leech.text.action.export;
/*     */ 
/*     */ import dark.leech.text.models.Chapter;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.util.FileUtils;
/*     */ import dark.leech.text.util.RegexUtils;
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import java.io.File;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ToC
/*     */ {
/*     */   private int id;
/*     */   private Properties properties;
/*     */   private List<Chapter> chapList;
/*     */   private boolean autoSplit;
/*     */   private StringBuilder toc;
/*     */   private StringBuilder content;
/*     */   private StringBuilder muclucHtml;
/*     */   private List<String> partList;
/*     */   private List<String> namePartList;
/*     */   private boolean includeImg;
/*     */   private String charset;
/*     */   
/*     */   public ToC(Properties properties) {
/*  34 */     this.properties = properties;
/*  35 */     this.chapList = properties.getChapList();
/*  36 */     this.toc = new StringBuilder();
/*  37 */     this.content = new StringBuilder();
/*  38 */     this.muclucHtml = new StringBuilder();
/*  39 */     this.partList = new ArrayList<>();
/*  40 */     this.namePartList = new ArrayList<>();
/*  41 */     this.charset = properties.getCharset();
/*     */   }
/*     */   
/*     */   public void setIncludeImg(boolean includeImg) {
/*  45 */     this.includeImg = includeImg;
/*     */   }
/*     */   
/*     */   public void setAutoSplit(boolean autoSplit) {
/*  49 */     this.autoSplit = autoSplit;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> splitPart() {
/*  54 */     ArrayList<Integer> part = new ArrayList<>();
/*     */     
/*     */     int index;
/*  57 */     for (index = 0; index < this.chapList.size(); index++) {
/*  58 */       if (((Chapter)this.chapList.get(index)).getPartName().length() != 0) {
/*  59 */         part.add(Integer.valueOf(index));
/*     */         break;
/*     */       } 
/*  62 */       if (RegexUtils.find(((Chapter)this.chapList.get(index)).getChapName(), "(Ch..ng\\s*\\d+)", 1).length() != 0) {
/*  63 */         part.add(Integer.valueOf(index));
/*     */         break;
/*     */       } 
/*     */     } 
/*  67 */     int c1 = index + 1;
/*     */ 
/*     */     
/*  70 */     int nextPart = 2;
/*  71 */     for (index = c1; index < this.chapList.size(); index++) {
/*  72 */       if (toInt(RegexUtils.find(((Chapter)this.chapList.get(index)).getChapName(), "(Q|Quy.n\\s*)(\\d+)", 2)) == nextPart) {
/*  73 */         part.add(Integer.valueOf(index));
/*  74 */         nextPart++;
/*     */       } 
/*     */     } 
/*  77 */     if (part.size() > 1) return part;
/*     */ 
/*     */ 
/*     */     
/*  81 */     for (index = c1; index < this.chapList.size(); index++) {
/*  82 */       if (toInt(RegexUtils.find(((Chapter)this.chapList.get(index)).getChapName(), "Ch..ng\\s*(\\d+)", 1)) == 1 && 
/*  83 */         toInt(RegexUtils.find(((Chapter)this.chapList.get(index - 1)).getChapName(), "Ch..ng\\s*(\\d+)", 1)) != 1) {
/*  84 */         part.add(Integer.valueOf(index));
/*     */       }
/*     */     } 
/*  87 */     if (part.size() > 1) return part;
/*     */     
/*  89 */     if (this.chapList.size() < 300) return part; 
/*  90 */     int partNum = 100;
/*  91 */     for (index = c1; index < this.chapList.size(); index++) {
/*  92 */       int i = Math.abs(partNum - toInt(RegexUtils.find(((Chapter)this.chapList.get(index)).getChapName(), "Ch..ng\\s*(\\d+)", 1)));
/*  93 */       if (i >= 0 && i <= 5) {
/*  94 */         int vt = findAround(index, 10, partNum);
/*  95 */         if (vt != -1) {
/*     */           
/*  97 */           index = vt;
/*  98 */           part.add(Integer.valueOf(index));
/*  99 */           partNum += 100;
/*     */         } 
/*     */       } 
/* 102 */     }  return part;
/*     */   }
/*     */ 
/*     */   
/*     */   private int findAround(int point, int range, int value) {
/* 107 */     for (int i = 0; i < range; i++) {
/* 108 */       if (i + point < this.chapList.size() && 
/* 109 */         value < toInt(RegexUtils.find(((Chapter)this.chapList.get(i + point)).getChapName(), "Ch..ng\\s*(\\d+)", 1)))
/* 110 */         return i + point; 
/*     */     } 
/* 112 */     return -1;
/*     */   }
/*     */   
/*     */   private int toInt(String str) {
/*     */     try {
/* 117 */       return Integer.parseInt(str);
/* 118 */     } catch (Exception e) {
/* 119 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mkToC() {
/* 124 */     this.id = 1;
/* 125 */     this.chapList = this.properties.getChapList();
/* 126 */     this.muclucHtml.append("\n<h4>Mục lục</h4>\n");
/* 127 */     if (this.properties.isAddGt()) {
/* 128 */       this.muclucHtml.append("<div class=\"lv2\"><a href=\"../Text/gioithieu.html\">Giới Thiệu</a></div>\n");
/* 129 */       this.toc.append("    <navPoint id=\"gioithieu\" playorder=\"" + Integer.toString(this.id) + "\">\n      <navLabel>\n        <text>Giới Thiệu</text>\n      </navLabel>\n      <content src=\"Text/gioithieu.html\"/>\n</navPoint>\n");
/*     */ 
/*     */ 
/*     */       
/* 133 */       this.content.append("\t<item id=\"gioithieu\" href=\"Text/gioithieu.html\" media-type=\"application/xhtml+xml\"/>\n");
/* 134 */       this.id++;
/*     */     } 
/* 136 */     this.content.append("\t<item id=\"mucluc\" href=\"Text/mucluc.html\" media-type=\"application/xhtml+xml\"/>\n");
/*     */     
/* 138 */     if (!this.autoSplit) {
/* 139 */       makePart(0, this.chapList.size(), "\t");
/* 140 */       saveData();
/*     */       return;
/*     */     } 
/* 143 */     ArrayList<Integer> part = splitPart();
/* 144 */     if (part.size() < 2) {
/* 145 */       makePart(0, this.chapList.size(), "\t");
/* 146 */       saveData();
/*     */       
/*     */       return;
/*     */     } 
/* 150 */     makePart(0, ((Integer)part.get(0)).intValue(), "    ");
/* 151 */     for (int i = 0; i < part.size(); i++) {
/* 152 */       String namePart = ((Chapter)this.chapList.get(((Integer)part.get(i)).intValue())).getPartName();
/* 153 */       if (namePart.length() == 0) {
/* 154 */         namePart = "Chương " + Integer.toString(i * 100 + 1) + "→" + ((i == part.size() - 1) ? "Hết" : String.valueOf((i + 1) * 100));
/*     */       }
/*     */       
/* 157 */       String s = "    <navPoint id=\"nav" + Integer.toString(this.id) + "\" playorder=\"" + Integer.toString(this.id) + "\">\n      <navLabel>\n        <text>" + namePart + "</text>\n      </navLabel>\n      <content src=\"Text/Q" + Integer.toString(i + 1) + ".html\"/>\n";
/* 158 */       this.toc.append(s);
/* 159 */       this.content.append("\t<item id=\"Q" + Integer.toString(i + 1) + "\" href=\"Text/Q" + Integer.toString(i + 1) + ".html\" media-type=\"application/xhtml+xml\"/>\n");
/* 160 */       this.namePartList.add(namePart);
/* 161 */       this.id++;
/* 162 */       if (i == part.size() - 1) {
/* 163 */         makePart(((Integer)part.get(i)).intValue(), this.chapList.size(), "      ");
/*     */       } else {
/* 165 */         makePart(((Integer)part.get(i)).intValue(), ((Integer)part.get(i + 1)).intValue(), "      ");
/* 166 */       }  this.toc.append("    </navPoint>\n");
/*     */     } 
/* 168 */     saveData();
/*     */   }
/*     */   
/*     */   private void makePart(int start, int end, String tab) {
/* 172 */     StringBuilder pa = new StringBuilder();
/* 173 */     for (int i = start; i < end; i++) {
/*     */ 
/*     */       
/* 176 */       String s = tab + "<navPoint id=\"nav" + Integer.toString(this.id) + "\" playorder=\"" + Integer.toString(this.id) + "\">\n" + tab + "  <navLabel>\n" + tab + "    <text>" + ((Chapter)this.chapList.get(i)).getChapName() + "</text>\n" + tab + "  </navLabel>\n" + tab + "  <content src=\"Text/" + ((Chapter)this.chapList.get(i)).getId() + ".html\"/>\n" + tab + "</navPoint>\n";
/*     */       
/* 178 */       pa.append("<div class=\"lv2\"><a href=\"../Text/" + ((Chapter)this.chapList.get(i)).getId() + ".html\">" + ((Chapter)this.chapList.get(i)).getChapName() + "</a></div>\n");
/* 179 */       this.content.append("\t<item id=\"C" + Integer.toString(i) + "\" href=\"Text/" + ((Chapter)this.chapList.get(i)).getId() + ".html\" media-type=\"application/xhtml+xml\"/>\n");
/* 180 */       this.toc.append(s);
/* 181 */       this.id++;
/*     */     } 
/* 183 */     this.partList.add(pa.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   private void saveData() {
/* 188 */     String ncx = FileUtils.stream2string("/dark/leech/res/toc.ncx");
/*     */ 
/*     */     
/* 191 */     ncx = ncx.replace("[NAME]", this.properties.getName()).replace("[AUTHOR]", this.properties.getAuthor()).replace("[NAVPOINT]", new String(this.toc));
/* 192 */     FileUtils.string2file(ncx, this.properties.getSavePath() + "/data/toc.ncx", this.charset);
/*     */     
/* 194 */     String opf = FileUtils.stream2string("/dark/leech/res/content.opf");
/*     */     
/* 196 */     opf = opf.replace("[NAME]", this.properties.getName()).replace("[AUTHOR]", this.properties.getAuthor());
/* 197 */     Date todaysDate = new Date();
/* 198 */     DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
/* 199 */     opf = opf.replace("[DATE]", df.format(todaysDate));
/* 200 */     String manifest = new String(this.content);
/*     */     
/* 202 */     opf = opf.replace("[MANIFEST]", manifest).replace("[IMAGE]", addImg());
/* 203 */     manifest = manifest.replaceAll("<item id=(.*?)\\s*href=.*?/>", "<itemref idref=$1/>");
/* 204 */     opf = opf.replace("[NCX]", manifest);
/* 205 */     FileUtils.string2file(opf, this.properties.getSavePath() + "/data/content.opf", this.charset);
/*     */     
/* 207 */     String head = SettingUtils.HTML_SYNTAX;
/* 208 */     head = head.replaceAll("(?s)(.*?<body.*?>).*", "$1");
/* 209 */     if (!this.autoSplit) {
/* 210 */       this.muclucHtml.append(this.partList.get(0));
/*     */     } else {
/* 212 */       if (this.partList.get(0) != null)
/* 213 */         this.muclucHtml.append(this.partList.get(0)); 
/* 214 */       for (int i = 1; i < this.partList.size(); i++) {
/* 215 */         this.muclucHtml.append("<div class=\"lv2\"><a href=\"../Text/Q" + Integer.toString(i) + ".html\">" + (String)this.namePartList.get(i - 1) + "</a></div>\n");
/* 216 */         String Q = head;
/* 217 */         Q = Q.replaceAll("<title>.*?</title>", "<title>" + (String)this.namePartList.get(i - 1) + "</title>");
/* 218 */         Q = Q + "\n<h4>" + (String)this.namePartList.get(i - 1) + "</h4>\n";
/* 219 */         Q = Q + (String)this.partList.get(i);
/* 220 */         Q = Q + "</body>\n</html>";
/* 221 */         FileUtils.string2file(Q, this.properties.getSavePath() + "/data/Text/Q" + Integer.toString(i) + ".html", this.charset);
/*     */       } 
/*     */     } 
/*     */     
/* 225 */     String mucluc = head.replaceAll("<title>.*?</title>", "<title>Mục lục</title>") + this.muclucHtml.toString() + "</body>\n</html>";
/*     */     
/* 227 */     FileUtils.string2file(mucluc, this.properties.getSavePath() + "/data/Text/mucluc.html", this.charset);
/*     */   }
/*     */   
/*     */   private String addImg() {
/* 231 */     if (!this.includeImg) return "\n"; 
/* 232 */     StringBuilder img = new StringBuilder();
/* 233 */     File file = new File(this.properties.getSavePath() + "/data/Images");
/* 234 */     String[] files = file.list();
/* 235 */     for (String fn : files) {
/* 236 */       if (fn.endsWith(".png"))
/* 237 */         img.append("<item id=\"" + fn + "\" href=\"Images/" + fn + "\" media-type=\"image/png\"/>\n"); 
/* 238 */       if (fn.endsWith(".jpg") || fn.endsWith(".jpeg"))
/* 239 */         img.append("<item id=\"" + fn + "\" href=\"Images/" + fn + "\" media-type=\"image/jpeg\"/>\n"); 
/* 240 */       if (fn.endsWith(".gif"))
/* 241 */         img.append("<item id=\"" + fn + "\" href=\"Images/" + fn + "\" media-type=\"image/gif\"/>\n"); 
/*     */     } 
/* 243 */     return new String(img);
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\action\export\ToC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */