/*     */ package dark.leech.text.models;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Properties
/*     */ {
/*     */   private String name;
/*     */   private String author;
/*     */   private String url;
/*     */   private String cover;
/*     */   private List<Chapter> chapList;
/*     */   private List<Pager> pageList;
/*     */   private boolean forum;
/*     */   private int size;
/*     */   private String savePath;
/*     */   private String gioiThieu;
/*     */   private boolean addGt;
/*  21 */   private String charset = "UTF-8";
/*     */   private String[] urlList;
/*     */   
/*     */   public boolean isAddGt() {
/*  25 */     return this.addGt;
/*     */   }
/*     */   
/*     */   public void setAddGt(boolean addGt) {
/*  29 */     this.addGt = addGt;
/*     */   }
/*     */   
/*     */   public String getGioiThieu() {
/*  33 */     return this.gioiThieu;
/*     */   }
/*     */   
/*     */   public void setGioiThieu(String gioiThieu) {
/*  37 */     this.gioiThieu = gioiThieu;
/*     */   }
/*     */   
/*     */   public String getSavePath() {
/*  41 */     return this.savePath;
/*     */   }
/*     */   
/*     */   public void setSavePath(String savePath) {
/*  45 */     this.savePath = savePath;
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  49 */     return this.size;
/*     */   }
/*     */   
/*     */   public void setSize(int size) {
/*  53 */     this.size = size;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  57 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  61 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getAuthor() {
/*  65 */     return this.author;
/*     */   }
/*     */   
/*     */   public void setAuthor(String author) {
/*  69 */     this.author = author;
/*     */   }
/*     */   
/*     */   public String getUrl() {
/*  73 */     return this.url;
/*     */   }
/*     */   
/*     */   public void setUrl(String url) {
/*  77 */     this.url = url;
/*     */   }
/*     */   
/*     */   public String getCover() {
/*  81 */     return this.cover;
/*     */   }
/*     */   
/*     */   public void setCover(String cover) {
/*  85 */     this.cover = cover;
/*     */   }
/*     */   
/*     */   public void setCover(String cover, String page) {
/*  89 */     if (cover == null)
/*  90 */       return;  if (cover.startsWith("http"))
/*  91 */     { this.cover = cover; }
/*  92 */     else { this.cover = page + cover; }
/*     */   
/*     */   }
/*     */   public boolean isForum() {
/*  96 */     return this.forum;
/*     */   }
/*     */   
/*     */   public void setForum(boolean forum) {
/* 100 */     this.forum = forum;
/*     */   }
/*     */   
/*     */   public List<Chapter> getChapList() {
/* 104 */     return this.chapList;
/*     */   }
/*     */   
/*     */   public void setChapList(List<Chapter> chapList) {
/* 108 */     this.chapList = chapList;
/*     */   }
/*     */   
/*     */   public List<Pager> getPageList() {
/* 112 */     return this.pageList;
/*     */   }
/*     */   
/*     */   public void setPageList(List<Pager> pageList) {
/* 116 */     this.pageList = pageList;
/*     */   }
/*     */   
/*     */   public String getCharset() {
/* 120 */     return this.charset;
/*     */   }
/*     */   
/*     */   public void setCharset(String charset) {
/* 124 */     this.charset = charset;
/*     */   }
/*     */   
/*     */   public String[] getUrlList() {
/* 128 */     return this.urlList;
/*     */   }
/*     */   
/*     */   public void setUrlList(String[] urlList) {
/* 132 */     this.urlList = urlList;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\models\Properties.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */