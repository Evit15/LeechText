/*     */ package dark.leech.text.ui.material;
import dark.leech.text.util.ColorUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import dark.leech.text.util.SafePropertySetter;
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

/*     */ import javax.swing.JComponent;
import javax.swing.JLabel;
/*     */ import javax.swing.JTextField;
import javax.swing.border.Border;
/*     */ import javax.swing.text.DefaultCaret;
/*     */ import net.java.balloontip.BalloonTip;
/*     */ import net.java.balloontip.styles.BalloonTipStyle;
/*     */ import net.java.balloontip.styles.MinimalBalloonStyle;
import net.java.balloontip.utils.TimingUtils;

/*     */ import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.Interpolator;
/*     */ import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.core.animation.timing.interpolators.SplineInterpolator;
/*     */ import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;
/*     */ 
/*     */ public class JMTextField extends JTextField {
/*  23 */   private Line line = new Line(this);
/*     */   
/*     */   public JMTextField() {
/*  26 */     setBorder((Border)null);
/*  27 */     setCaret(new DefaultCaret()
/*     */         {
/*     */           protected synchronized void damage(Rectangle r) {
/*  30 */             repaint();
/*     */           }
/*     */         });
/*  33 */     getCaret().setBlinkRate(500);
/*     */   }
/*     */   
/*     */   public void addError(String err) {
/*  37 */     Color color = ColorUtils.THEME_COLOR;
/*  38 */     MinimalBalloonStyle minimalBalloonStyle = new MinimalBalloonStyle(new Color(color.getRed(), color.getGreen(), color.getBlue(), 200), 5);
/*  39 */     BalloonTip balloonTip = new BalloonTip(this, new JLabel("<html><font color=\"#ffffff\">" + err + "</font></html>"), (BalloonTipStyle)minimalBalloonStyle, BalloonTip.Orientation.LEFT_ABOVE, BalloonTip.AttachLocation.ALIGNED, 30, 10, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     TimingUtils.showTimedBalloon(balloonTip, 3000);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String s) {
/*  54 */     if (s != null && 
/*  55 */       s.length() != 0 && 
/*  56 */       FontUtils.TEXT_NORMAL.canDisplayUpTo(s) == -1)
/*  57 */       setFont(FontUtils.TEXT_NORMAL); 
/*  58 */     super.setText(s);
/*  59 */     this.line.update();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processFocusEvent(FocusEvent e) {
/*  64 */     super.processFocusEvent(e);
/*  65 */     this.line.update();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processKeyEvent(KeyEvent e) {
/*  70 */     super.processKeyEvent(e);
/*  71 */     this.line.update();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/*  76 */     Graphics2D g2 = (Graphics2D)g;
/*  77 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  78 */     g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*     */     
/*  80 */     super.paintComponent(g);
/*     */     
/*  82 */     g2.setColor(Color.LIGHT_GRAY);
/*  83 */     g2.fillRect(0, getHeight() - 1, getWidth(), 1);
/*     */     
/*  85 */     g2.setColor(SettingUtils.THEME_COLOR);
/*  86 */     g2.fillRect((int)((getWidth() - this.line.getWidth()) / 2.0D), getHeight() - 3, (int)this.line.getWidth(), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public class Line
/*     */   {
/*     */     private final SwingTimerTimingSource timer;
/*     */     
/*     */     private final JComponent target;
/*     */     private Animator animator;
/*     */     private SafePropertySetter.Property<Double> width;
/*     */     
/*     */     Line(JComponent target) {
/*  99 */       this.target = target;
/* 100 */       this.timer = new SwingTimerTimingSource();
/* 101 */       this.timer.init();
/* 102 */       this.width = SafePropertySetter.animatableProperty(target, Double.valueOf(0.0D));
/*     */     }
/*     */     
/*     */     void update() {
/* 106 */       if (this.animator != null) {
/* 107 */         this.animator.stop();
/*     */       }
/* 109 */       this
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 114 */         .animator = (new Animator.Builder((TimingSource)this.timer)).setDuration(200L, TimeUnit.MILLISECONDS).setEndBehavior(Animator.EndBehavior.HOLD).setInterpolator((Interpolator)new SplineInterpolator(0.4D, 0.0D, 0.2D, 1.0D)).addTarget(SafePropertySetter.getTarget((SafePropertySetter.Setter)this.width, (Object[])new Double[] { (Double)this.width.getValue(), Double.valueOf(this.target.isFocusOwner() ? (this.target.getWidth() + 1.0D) : 0.0D) })).build();
/* 115 */       this.animator.start();
/*     */     }
/*     */     
/*     */     public double getWidth() {
/* 119 */       return ((Double)this.width.getValue()).doubleValue();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\JMTextField.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */