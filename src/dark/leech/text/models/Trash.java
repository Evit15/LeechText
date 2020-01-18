/*    */ package dark.leech.text.models;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Trash
/*    */ {
/*    */   private String src;
/*    */   private String to;
/*    */   private String tip;
/*    */   private boolean replace;
/*    */   
/*    */   public Trash() {
/* 13 */     this("", "", "", true);
/*    */   }
/*    */   
/*    */   public Trash(String src, String to, String tip, boolean replace) {
/* 17 */     this.src = src;
/* 18 */     this.to = to;
/* 19 */     this.tip = tip;
/* 20 */     this.replace = replace;
/*    */   }
/*    */   
/*    */   public boolean isReplace() {
/* 24 */     return this.replace;
/*    */   }
/*    */   
/*    */   public void setReplace(boolean replace) {
/* 28 */     this.replace = replace;
/*    */   }
/*    */   
/*    */   public String getSrc() {
/* 32 */     return this.src;
/*    */   }
/*    */   
/*    */   public void setSrc(String src) {
/* 36 */     this.src = src;
/*    */   }
/*    */   
/*    */   public String getTo() {
/* 40 */     if (this.to == null) this.to = ""; 
/* 41 */     return this.to;
/*    */   }
/*    */   
/*    */   public void setTo(String to) {
/* 45 */     this.to = to;
/*    */   }
/*    */   
/*    */   public String getTip() {
/* 49 */     return this.tip;
/*    */   }
/*    */   
/*    */   public void setTip(String tip) {
/* 53 */     this.tip = tip;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\models\Trash.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */