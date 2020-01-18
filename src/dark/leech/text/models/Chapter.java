/*     */ package dark.leech.text.models;
/*     */ 
/*     */ public class Chapter
/*     */   implements Cloneable {
/*     */   private String url;
/*     */   private String partName;
/*     */   private String chapName;
/*     */   private boolean completed;
/*     */   private boolean error;
/*     */   private boolean empty;
/*     */   private boolean imageChapter;
/*     */   private String id;
/*     */   private boolean purchase;
			private boolean downloadImgSucess = true;
/*     */   
/*     */   public Chapter() {
/*  16 */     this("");
/*     */   }
/*     */   
/*     */   public Chapter(String url) {
/*  20 */     this(url, "");
/*     */   }
/*     */   
/*     */   public Chapter(String url, String name) {
/*  24 */     this(url, -1, "", name);
/*     */   }
/*     */   
/*     */   public Chapter(String url, int id, String partName, String chapName) {
/*  28 */     this(url, id, partName, chapName, false, false);
/*     */   }
/*     */   
/*     */   public Chapter(String url, int id, String partName, String chapName, boolean completed, boolean error) {
/*  32 */     this.url = url;
/*  33 */     this.id = "C" + Integer.toString(id);
/*  34 */     this.partName = partName;
/*  35 */     this.chapName = chapName;
/*  36 */     this.error = error;
/*  37 */     this.completed = completed;
/*     */   }
/*     */   
/*     */   public Chapter(String url, int id, String chapName) {
/*  41 */     this(url, id, (String)null, chapName);
/*     */   }
/*     */   
/*     */   public Chapter(String url, String id, String partName, String chapName) {
/*  45 */     this.url = url;
/*  46 */     this.partName = partName;
/*  47 */     this.chapName = chapName;
/*  48 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  52 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  56 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getUrl() {
/*  60 */     return this.url;
/*     */   }
/*     */   
/*     */   public void setUrl(String url) {
/*  64 */     this.url = url;
/*     */   }
/*     */   
/*     */   public String getChapName() {
/*  68 */     if (this.chapName == null) this.chapName = ""; 
/*  69 */     return this.chapName;
/*     */   }
/*     */   
/*     */   public void setChapName(String chapName) {
/*  73 */     this.chapName = chapName;
/*     */   }
/*     */   
/*     */   public String getPartName() {
/*  77 */     if (this.partName == null) this.partName = ""; 
/*  78 */     return this.partName;
/*     */   }
/*     */   
/*     */   public void setPartName(String partName) {
/*  82 */     this.partName = partName;
/*     */   }
/*     */   
/*     */   public boolean isError() {
/*  86 */     return this.error;
/*     */   }
/*     */   
/*     */   public void setError(boolean error) {
/*  90 */     this.error = error;
/*     */   }
/*     */   
/*     */   public boolean isCompleted() {
/*  94 */     return this.completed;
/*     */   }
/*     */   
/*     */   public void setCompleted(boolean completed) {
/*  98 */     this.completed = completed;
/*     */   }
/*     */   
/*     */   public boolean isImageChapter() {
/* 102 */     return this.imageChapter;
/*     */   }
/*     */   
/*     */   public void setImageChapter(boolean imageChapter) {
/* 106 */     this.imageChapter = imageChapter;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/* 110 */     this.id = "C" + Integer.toString(id);
/*     */   }
/*     */   
/*     */   public boolean isPurchase() {
/* 114 */     return this.purchase;
/*     */   }
/*     */   
/*     */   public void setPurchase(boolean purchase) {
/* 118 */     this.purchase = purchase;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 122 */     return this.empty;
/*     */   }
/*     */   
/*     */   public void setEmpty(boolean empty) {
/* 126 */     this.empty = empty;
/*     */   }
/*     */
public boolean isDownloadImgSucess() {
	return downloadImgSucess;
}
public void setDownloadImgSucess(boolean downloadImgSucess) {
	this.downloadImgSucess = downloadImgSucess;
} }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\models\Chapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */