/*     */ package dark.leech.text.enities;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ 
/*     */ public class PluginEntity
/*     */ {
/*     */   @SerializedName("uuid")
/*   8 */   private String uuid = "";
/*     */   
/*     */   @SerializedName("name")
/*     */   private String name;
/*     */   @SerializedName("version")
/*     */   private double version;
/*     */   @SerializedName("url")
/*     */   private String url;
/*     */   @SerializedName("language")
/*     */   private String language;
/*     */   @SerializedName("icon")
/*     */   private String icon;
/*     */   @SerializedName("source")
/*     */   private String source;
/*     */   @SerializedName("regex")
/*     */   private String regex;
/*     */   @SerializedName("author")
/*     */   private String author;
/*     */   @SerializedName("describe")
/*     */   private String describe;
/*     */   @SerializedName("group")
/*     */   private String group;
/*     */   @SerializedName("data")
/*     */   private String data;
/*     */   private boolean supportUpdate;
/*     */   @SerializedName("chap")
/*     */   private String chapGetter;
/*     */   @SerializedName("toc")
/*     */   private String tocGetter;
/*     */   @SerializedName("page")
/*     */   private String pageGetter;
/*     */   @SerializedName("search")
/*     */   private String searchGetter;
/*     */   @SerializedName("detail")
/*     */   private String detailGetter;
/*     */   @SerializedName("checked")
/*     */   private boolean checked;
/*     */   
/*     */   public boolean isChecked() {
/*  47 */     return this.checked;
/*     */   }
/*     */   
/*     */   public void setChecked(boolean checked) {
/*  51 */     this.checked = checked;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUuid() {
/*  59 */     return this.uuid;
/*     */   }
/*     */   
/*     */   public void setUuid(String uuid) {
/*  63 */     this.uuid = uuid;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  67 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  71 */     this.name = name;
/*     */   }
/*     */   
/*     */   public double getVersion() {
/*  75 */     return this.version;
/*     */   }
/*     */   
/*     */   public void setVersion(double version) {
/*  79 */     this.version = version;
/*     */   }
/*     */   
/*     */   public String getUrl() {
/*  83 */     return this.url;
/*     */   }
/*     */   
/*     */   public void setUrl(String url) {
/*  87 */     this.url = url;
/*     */   }
/*     */   
/*     */   public String getLanguage() {
/*  91 */     return this.language;
/*     */   }
/*     */   
/*     */   public void setLanguage(String language) {
/*  95 */     this.language = language;
/*     */   }
/*     */   
/*     */   public String getIcon() {
/*  99 */     return this.icon;
/*     */   }
/*     */   
/*     */   public void setIcon(String icon) {
/* 103 */     this.icon = icon;
/*     */   }
/*     */   
/*     */   public String getSource() {
/* 107 */     return this.source;
/*     */   }
/*     */   
/*     */   public void setSource(String source) {
/* 111 */     this.source = source;
/*     */   }
/*     */   
/*     */   public String getRegex() {
/* 115 */     return this.regex;
/*     */   }
/*     */   
/*     */   public void setRegex(String regex) {
/* 119 */     this.regex = regex;
/*     */   }
/*     */   
/*     */   public String getAuthor() {
/* 123 */     return this.author;
/*     */   }
/*     */   
/*     */   public void setAuthor(String author) {
/* 127 */     this.author = author;
/*     */   }
/*     */   
/*     */   public String getDescribe() {
/* 131 */     return this.describe;
/*     */   }
/*     */   
/*     */   public void setDescribe(String describe) {
/* 135 */     this.describe = describe;
/*     */   }
/*     */   
/*     */   public String getGroup() {
/* 139 */     return this.group;
/*     */   }
/*     */   
/*     */   public void setGroup(String group) {
/* 143 */     this.group = group;
/*     */   }
/*     */   
/*     */   public String getChapGetter() {
/* 147 */     return this.chapGetter;
/*     */   }
/*     */   
/*     */   public void setChapGetter(String chapGetter) {
/* 151 */     this.chapGetter = chapGetter;
/*     */   }
/*     */   
/*     */   public String getTocGetter() {
/* 155 */     return this.tocGetter;
/*     */   }
/*     */   
/*     */   public void setTocGetter(String tocGetter) {
/* 159 */     this.tocGetter = tocGetter;
/*     */   }
/*     */   
/*     */   public String getPageGetter() {
/* 163 */     return this.pageGetter;
/*     */   }
/*     */   
/*     */   public void setPageGetter(String pageGetter) {
/* 167 */     this.pageGetter = pageGetter;
/*     */   }
/*     */   
/*     */   public String getSearchGetter() {
/* 171 */     return this.searchGetter;
/*     */   }
/*     */   
/*     */   public void setSearchGetter(String searchGetter) {
/* 175 */     this.searchGetter = searchGetter;
/*     */   }
/*     */   
/*     */   public String getDetailGetter() {
/* 179 */     return this.detailGetter;
/*     */   }
/*     */   
/*     */   public void setDetailGetter(String detailGetter) {
/* 183 */     this.detailGetter = detailGetter;
/*     */   }
/*     */   
/*     */   public String getData() {
/* 187 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setData(String data) {
/* 191 */     this.data = data;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpdate() {
/* 195 */     return this.supportUpdate;
/*     */   }
/*     */   
/*     */   public void setSupportUpdate(boolean supportUpdate) {
/* 199 */     this.supportUpdate = supportUpdate;
/*     */   }
/*     */   
/*     */   public void apply(PluginEntity entity) {
/* 203 */     this.uuid = entity.uuid;
/* 204 */     this.name = entity.name;
/* 205 */     this.version = entity.version;
/* 206 */     this.url = entity.url;
/* 207 */     this.language = entity.language;
/* 208 */     this.icon = entity.icon;
/* 209 */     this.source = entity.source;
/* 210 */     this.regex = entity.regex;
/* 211 */     this.author = entity.author;
/* 212 */     this.describe = entity.describe;
/* 213 */     this.group = entity.group;
/* 214 */     this.supportUpdate = entity.supportUpdate;
/* 215 */     this.chapGetter = entity.chapGetter;
/* 216 */     this.tocGetter = entity.tocGetter;
/* 217 */     this.pageGetter = entity.pageGetter;
/* 218 */     this.searchGetter = entity.searchGetter;
/* 219 */     this.detailGetter = entity.detailGetter;
/* 220 */     this.checked = true;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\enities\PluginEntity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */