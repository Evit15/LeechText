/*    */ package dark.leech.text.models;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Pager
/*    */ {
/*    */   private String url;
/*    */   private String name;
/*    */   private List<Chapter> chapter;
/*    */   private String id;
/*    */   private boolean completed;
/*    */   
/*    */   public Pager(String url, int id) {
/* 17 */     this.url = url;
/* 18 */     this.id = "Q" + Integer.toString(id);
/*    */   }
/*    */   
/*    */   public Pager(String url) {
/* 22 */     this.url = url;
/*    */   }
/*    */   
/*    */   public String getUrl() {
/* 26 */     return this.url;
/*    */   }
/*    */   
/*    */   public void setUrl(String url) {
/* 30 */     this.url = url;
/*    */   }
/*    */   
/*    */   public List<Chapter> getChapter() {
/* 34 */     return this.chapter;
/*    */   }
/*    */   
/*    */   public void setChapter(List<Chapter> chapter) {
/* 38 */     this.chapter = chapter;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 42 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 46 */     this.id = id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 50 */     this.id = "P" + Integer.toString(id);
/*    */   }
/*    */   
/*    */   public String getName() {
/* 54 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 58 */     this.name = name;
/*    */   }
/*    */   
/*    */   public boolean isCompleted() {
/* 62 */     return this.completed;
/*    */   }
/*    */   
/*    */   public void setCompleted(boolean completed) {
/* 66 */     this.completed = completed;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\models\Pager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */