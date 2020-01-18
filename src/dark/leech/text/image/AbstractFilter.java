/*    */ package dark.leech.text.image;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.BufferedImageOp;
/*    */ import java.awt.image.ColorModel;
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
/*    */ 
/*    */ public abstract class AbstractFilter
/*    */   extends AbstractBean
/*    */   implements BufferedImageOp
/*    */ {
/*    */   public abstract BufferedImage filter(BufferedImage paramBufferedImage1, BufferedImage paramBufferedImage2);
/*    */   
/*    */   public Rectangle2D getBounds2D(BufferedImage src) {
/* 62 */     return new Rectangle(0, 0, src.getWidth(), src.getHeight());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM) {
/* 71 */     if (destCM == null) {
/* 72 */       destCM = src.getColorModel();
/*    */     }
/*    */     
/* 75 */     return new BufferedImage(destCM, destCM
/* 76 */         .createCompatibleWritableRaster(src
/* 77 */           .getWidth(), src.getHeight()), destCM
/* 78 */         .isAlphaPremultiplied(), null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Point2D getPoint2D(Point2D srcPt, Point2D dstPt) {
/* 86 */     return (Point2D)srcPt.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderingHints getRenderingHints() {
/* 94 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\image\AbstractFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */