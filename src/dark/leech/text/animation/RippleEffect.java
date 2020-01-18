/*     */ package dark.leech.text.animation;
/*     */ import dark.leech.text.util.SafePropertySetter;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.swing.JComponent;
/*     */ import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.Interpolator;
/*     */ import org.jdesktop.core.animation.timing.TimingSource;
/*     */ import org.jdesktop.core.animation.timing.TimingTarget;
/*     */ import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.core.animation.timing.interpolators.AccelerationInterpolator;
/*     */ import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;
/*     */ 
/*     */ public class RippleEffect {
/*  21 */   private final List<RippleAnimation> ripples = new ArrayList<>();
/*     */   private final JComponent target;
/*     */   private final SwingTimerTimingSource timer;
/*     */   
/*     */   private RippleEffect(JComponent component) {
/*  26 */     this.target = component;
/*     */     
/*  28 */     this.timer = new SwingTimerTimingSource();
/*  29 */     this.timer.init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/*  38 */     Graphics2D g2 = (Graphics2D)g;
/*  39 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  40 */     g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/*  41 */     g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*  42 */     for (RippleAnimation rippleAnimation : this.ripples) {
/*  43 */       float rippleOpacity = ((Double)rippleAnimation.rippleOpacity.getValue()).floatValue();
/*  44 */       Point rippleCenter = rippleAnimation.rippleCenter;
/*  45 */       int rippleRadius = ((Integer)rippleAnimation.rippleRadius.getValue()).intValue();
/*     */       
/*  47 */       Color fg = this.target.getForeground();
/*  48 */       g2.setColor(new Color(fg.getRed() / 255.0F, fg.getGreen() / 255.0F, fg.getBlue() / 255.0F, rippleOpacity));
/*  49 */       g2.fillOval(rippleCenter.x - rippleRadius, rippleCenter.y - rippleRadius, 2 * rippleRadius, 2 * rippleRadius);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRipple(Point point, int maxRadius) {
/*  60 */     RippleAnimation ripple = new RippleAnimation(point, maxRadius);
/*  61 */     this.ripples.add(ripple);
/*  62 */     ripple.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RippleEffect applyTo(final JComponent target) {
/*  73 */     final RippleEffect rippleEffect = new RippleEffect(target);
/*  74 */     target.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mousePressed(MouseEvent e) {
/*  77 */             rippleEffect.addRipple(e.getPoint(), target.getWidth());
/*     */           }
/*     */         });
/*  80 */     return rippleEffect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RippleEffect applyFixedTo(final JComponent target) {
/*  91 */     final RippleEffect rippleEffect = new RippleEffect(target);
/*  92 */     target.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mousePressed(MouseEvent e) {
/*  95 */             rippleEffect.addRipple(new Point(24, 24), target.getWidth() / 2);
/*     */           }
/*     */         });
/*  98 */     return rippleEffect;
/*     */   }
/*     */ 
/*     */   
/*     */   public class RippleAnimation
/*     */   {
/*     */     private final Point rippleCenter;
/*     */     
/*     */     private final int maxRadius;
/* 107 */     private final SafePropertySetter.Property<Integer> rippleRadius = SafePropertySetter.animatableProperty(RippleEffect.this.target, Integer.valueOf(25));
/* 108 */     private final SafePropertySetter.Property<Double> rippleOpacity = SafePropertySetter.animatableProperty(RippleEffect.this.target, Double.valueOf(0.0D));
/*     */     
/*     */     private RippleAnimation(Point rippleCenter, int maxRadius) {
/* 111 */       this.rippleCenter = rippleCenter;
/* 112 */       this.maxRadius = maxRadius;
/*     */     }
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
/*     */     void start() {
/* 129 */       Animator rippleAnimator = (new Animator.Builder((TimingSource)RippleEffect.this.timer)).setDuration(1000L, TimeUnit.MILLISECONDS).setEndBehavior(Animator.EndBehavior.HOLD).setInterpolator((Interpolator)new AccelerationInterpolator(0.2D, 0.19D)).addTarget(SafePropertySetter.getTarget((SafePropertySetter.Setter)this.rippleRadius, (Object[])new Integer[] { Integer.valueOf(0), Integer.valueOf(this.maxRadius / 2), Integer.valueOf(this.maxRadius), Integer.valueOf(this.maxRadius) })).addTarget(SafePropertySetter.getTarget((SafePropertySetter.Setter)this.rippleOpacity, (Object[])new Double[] { Double.valueOf(0.0D), Double.valueOf(0.4D), Double.valueOf(0.3D), Double.valueOf(0.0D) })).addTarget((TimingTarget)new TimingTargetAdapter() { public void end(Animator source) { RippleEffect.this.ripples.remove(RippleEffect.RippleAnimation.this); } }).build();
/* 130 */       rippleAnimator.start();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\animation\RippleEffect.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */