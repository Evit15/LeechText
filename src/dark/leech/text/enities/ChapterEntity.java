/*    */ package dark.leech.text.enities;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ public class ChapterEntity {
/*    */   @SerializedName("id")
/*    */   private int id;
/*    */   @SerializedName("chapter_name")
/*    */   private String name;
/*    */   @SerializedName("chapter_url")
/*    */   private String url;
/*    */   
/*    */   public int getId() {
/* 14 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 18 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 22 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 26 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getUrl() {
/* 30 */     return this.url;
/*    */   }
/*    */   
/*    */   public void setUrl(String url) {
/* 34 */     this.url = url;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\enities\ChapterEntity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */