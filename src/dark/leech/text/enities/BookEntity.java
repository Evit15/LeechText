/*    */ package dark.leech.text.enities;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BookEntity
/*    */   implements Serializable
/*    */ {
/*    */   @SerializedName("book_id")
/* 10 */   private String id = "";
/*    */ 
/*    */   
/*    */   @SerializedName("name")
/*    */   private String name;
/*    */   
/*    */   @SerializedName("author")
/*    */   private String author;
/*    */   
/*    */   @SerializedName("cover")
/*    */   private String cover;
/*    */   
/*    */   @SerializedName("url")
/*    */   private String url;
/*    */   
/*    */   @SerializedName("introduce")
/*    */   private String introduce;
/*    */   
/*    */   @SerializedName("web_source")
/*    */   private String webSource;
/*    */   
/*    */   @SerializedName("detail")
/*    */   private String detail;
/*    */ 
/*    */   
/*    */   public String getName() {
/* 36 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 40 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getAuthor() {
/* 44 */     return this.author;
/*    */   }
/*    */   
/*    */   public void setAuthor(String author) {
/* 48 */     this.author = author;
/*    */   }
/*    */   
/*    */   public String getCover() {
/* 52 */     return this.cover;
/*    */   }
/*    */   
/*    */   public void setCover(String cover) {
/* 56 */     this.cover = cover;
/*    */   }
/*    */   
/*    */   public String getUrl() {
/* 60 */     return this.url;
/*    */   }
/*    */   
/*    */   public void setUrl(String url) {
/* 64 */     this.url = url;
/*    */   }
/*    */   
/*    */   public String getIntroduce() {
/* 68 */     return this.introduce;
/*    */   }
/*    */   
/*    */   public void setIntroduce(String introduce) {
/* 72 */     this.introduce = introduce;
/*    */   }
/*    */   
/*    */   public String getWebSource() {
/* 76 */     return this.webSource;
/*    */   }
/*    */   
/*    */   public void setWebSource(String webSource) {
/* 80 */     this.webSource = webSource;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 84 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 88 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getDetail() {
/* 92 */     return this.detail;
/*    */   }
/*    */   
/*    */   public void setDetail(String detail) {
/* 96 */     this.detail = detail;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\enities\BookEntity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */