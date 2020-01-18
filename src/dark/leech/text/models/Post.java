/*    */ package dark.leech.text.models;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Post
/*    */ {
/*    */   private String partName;
/*    */   private String chapName;
/*    */   private boolean error;
/*    */   private boolean empty;
/*    */   private boolean imageChapter;
/*    */   private String text;
/*    */   
/*    */   public Post() {
/* 15 */     this.partName = "";
/* 16 */     this.chapName = "";
/* 17 */     this.text = "";
/*    */   }
/*    */   
/*    */   public Post(String partName, String chapName, String text) {
/* 21 */     this.partName = partName;
/* 22 */     this.chapName = chapName;
/* 23 */     this.text = text;
/*    */   }
/*    */   
/*    */   public Post(String chapName, String text) {
/* 27 */     this.chapName = chapName;
/* 28 */     this.text = text;
/*    */   }
/*    */   
/*    */   public String getPartName() {
/* 32 */     return this.partName;
/*    */   }
/*    */   
/*    */   public void setPartName(String partName) {
/* 36 */     this.partName = partName;
/*    */   }
/*    */   
/*    */   public String getChapName() {
/* 40 */     return this.chapName;
/*    */   }
/*    */   
/*    */   public void setChapName(String chapName) {
/* 44 */     this.chapName = chapName;
/*    */   }
/*    */   
/*    */   public boolean isError() {
/* 48 */     return this.error;
/*    */   }
/*    */   
/*    */   public void setError(boolean error) {
/* 52 */     this.error = error;
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 56 */     return this.empty;
/*    */   }
/*    */   
/*    */   public void setEmpty(boolean empty) {
/* 60 */     this.empty = empty;
/*    */   }
/*    */   
/*    */   public boolean isImageChapter() {
/* 64 */     return this.imageChapter;
/*    */   }
/*    */   
/*    */   public void setImageChapter(boolean imageChapter) {
/* 68 */     this.imageChapter = imageChapter;
/*    */   }
/*    */   
/*    */   public String getText() {
/* 72 */     return this.text;
/*    */   }
/*    */   
/*    */   public void setText(String text) {
/* 76 */     this.text = text;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\models\Post.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */