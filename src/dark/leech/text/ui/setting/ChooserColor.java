/*     */ package dark.leech.text.ui.setting;
/*     */ 
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ChooserColor
/*     */   extends JMDialog
/*     */ {
/*  71 */   private static final Color DARK_THEME = new Color(38, 50, 56);
/*  72 */   private static final Color RED_THEME = new Color(244, 67, 54);
/*  73 */   private static final Color INDIGO_THEME = new Color(63, 81, 181);
/*  74 */   private static final Color BLUE_THEME = new Color(33, 150, 243);
/*  75 */   private static final Color PURPLE_THEME = new Color(156, 39, 176);
/*  76 */   private static final Color DEEP_PURPLE_THEME = new Color(103, 58, 183);
/*  77 */   private static final Color TEAL_THEME = new Color(0, 150, 136);
/*  78 */   private static final Color GREEN_THEME = new Color(76, 175, 80);
/*  79 */   private static final Color BROWN_THEME = new Color(121, 85, 72);
/*     */   private Color color;
/*     */   
/*     */   public ChooserColor(Color color) {
/*  83 */     this.color = color;
/*  84 */     onCreate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  89 */     super.onCreate();
/*  90 */     this.container.setLayout(new FlowLayout());
/*  91 */     this.container.add(new ColorPane(DARK_THEME));
/*  92 */     this.container.add(new ColorPane(RED_THEME));
/*  93 */     this.container.add(new ColorPane(INDIGO_THEME));
/*  94 */     this.container.add(new ColorPane(BLUE_THEME));
/*  95 */     this.container.add(new ColorPane(PURPLE_THEME));
/*  96 */     this.container.add(new ColorPane(DEEP_PURPLE_THEME));
/*  97 */     this.container.add(new ColorPane(TEAL_THEME));
/*  98 */     this.container.add(new ColorPane(GREEN_THEME));
/*  99 */     this.container.add(new ColorPane(BROWN_THEME));
/* 100 */     setSize(160, 160);
/*     */   }
/*     */   
/*     */   public Color getChooserColor() {
/* 104 */     return this.color;
/*     */   }
/*     */   
/*     */   public class ColorPane extends JPanel {
/*     */     public ColorPane(Color color) {
/* 109 */       setPreferredSize(new Dimension(45, 45));
/* 110 */       addMouseListener(new MouseAdapter()
/*     */           {
/*     */             public void mouseClicked(MouseEvent e) {
/* 113 */               ChooserColor.ColorPane.this.choose();
/*     */             }
/*     */           });
/* 116 */       setBackground(color);
/*     */     }
/*     */     
/*     */     private void choose() {
/* 120 */       ChooserColor.this.color = getBackground();
/* 121 */       ChooserColor.this.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\setting\ChooserColor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */