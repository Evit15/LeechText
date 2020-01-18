/*    */ package dark.leech.text.image;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageLabel
/*    */   extends JLabel
/*    */ {
/*    */   private String urlImage;
/*    */   private String pathImage;
/*    */   private InputStream imageStream;
/*    */   
/*    */   public ImageLabel path(String pathImage) {
/* 24 */     this.pathImage = pathImage;
/* 25 */     return this;
/*    */   }
/*    */   
/*    */   public ImageLabel url(String urlImage) {
/* 29 */     this.urlImage = urlImage;
/* 30 */     return this;
/*    */   }
/*    */   
/*    */   public ImageLabel input(InputStream imageStream) {
/* 34 */     this.imageStream = imageStream;
/* 35 */     return this;
/*    */   }
/*    */   
/*    */   public void load() {
/* 39 */     (new ImageLoader()).url(this.urlImage)
/* 40 */       .path(this.pathImage)
/* 41 */       .input(this.imageStream)
/* 42 */       .loadTo(this)
/* 43 */       .execute();
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\image\ImageLabel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */