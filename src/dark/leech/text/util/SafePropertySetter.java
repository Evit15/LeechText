/*     */ package dark.leech.text.util;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import org.jdesktop.core.animation.timing.Animator;
/*     */ import org.jdesktop.core.animation.timing.KeyFrames;
/*     */ import org.jdesktop.core.animation.timing.TimingTarget;
/*     */ import org.jdesktop.core.animation.timing.TimingTargetAdapter;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SafePropertySetter<T>
/*     */   extends TimingTargetAdapter
/*     */ {
/*     */   private final AtomicReference<KeyFrames<T>> keyFrames;
/*     */   private final boolean isToAnimation;
/*     */   private final Getter<T> getter;
/*     */   private final Setter<T> setter;
/*     */   
/*     */   protected SafePropertySetter(KeyFrames<T> keyFrames, boolean isToAnimation, Getter<T> getter, Setter<T> setter) {
/*  21 */     this.keyFrames = new AtomicReference<>(keyFrames);
/*  22 */     this.isToAnimation = isToAnimation;
/*  23 */     this.getter = getter;
/*  24 */     this.setter = setter;
/*     */   }
/*     */   
/*     */   @SafeVarargs
/*     */   public static <T> TimingTarget getTarget(Setter<T> setter, T... values) {
/*  29 */     return (TimingTarget)new SafePropertySetter<>((new KeyFrames.Builder()).addFrames((Object[])values).build(), false, null, setter);
/*     */   }
/*     */   
/*     */   public static <T> TimingTarget getTarget(Setter<T> setter, KeyFrames<T> keyFrames) {
/*  33 */     return (TimingTarget)new SafePropertySetter<>(keyFrames, false, null, setter);
/*     */   }
/*     */   
/*     */   @SafeVarargs
/*     */   public static <T> TimingTarget getTargetTo(Getter<T> getter, Setter<T> setter, T... values) {
/*  38 */     return getTargetTo(getter, setter, (new KeyFrames.Builder(values[0])).addFrames((Object[])values).build());
/*     */   }
/*     */   
/*     */   public static <T> TimingTarget getTargetTo(GetterAndSetter<T> getterAndSetter, T... values) {
/*  42 */     return getTargetTo(getterAndSetter, getterAndSetter, values);
/*     */   }
/*     */   
/*     */   public static <T> TimingTarget getTargetTo(Getter<T> getter, Setter<T> setter, KeyFrames<T> keyFrames) {
/*  46 */     return (TimingTarget)new SafePropertySetter<>(keyFrames, true, getter, setter);
/*     */   }
/*     */   
/*     */   public static <T> TimingTarget getTargetTo(GetterAndSetter<T> getterAndSetter, KeyFrames<T> keyFrames) {
/*  50 */     return getTargetTo(getterAndSetter, getterAndSetter, keyFrames);
/*     */   }
/*     */   
/*     */   public static <U> Property<U> animatableProperty(Component component, U value) {
/*  54 */     return new Property<>(component, value);
/*     */   }
/*     */   
/*     */   public void timingEvent(Animator source, double fraction) {
/*  58 */     this.setter.setValue((T)((KeyFrames)this.keyFrames.get()).getInterpolatedValueAt(fraction));
/*     */   }
/*     */   
/*     */   public void begin(Animator source) {
/*  62 */     if (this.isToAnimation) {
/*  63 */       KeyFrames.Builder<T> builder = new KeyFrames.Builder(this.getter.getValue());
/*  64 */       boolean first = true;
/*  65 */       for (KeyFrames.Frame<T> frame : this.keyFrames.get()) {
/*  66 */         if (first) {
/*  67 */           first = false; continue;
/*     */         } 
/*  69 */         builder.addFrame(frame);
/*     */       } 
/*     */       
/*  72 */       this.keyFrames.set(builder.build());
/*     */     } 
/*     */     
/*  75 */     double fraction = (source.getCurrentDirection() == Animator.Direction.FORWARD) ? 0.0D : 1.0D;
/*  76 */     timingEvent(source, fraction);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Property<T>
/*     */     implements GetterAndSetter<T>
/*     */   {
/*     */     private final Component component;
/*     */ 
/*     */ 
/*     */     
/*     */     private T value;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Property(Component component, T value) {
/*  95 */       this.component = component;
/*  96 */       this.value = value;
/*     */     }
/*     */ 
/*     */     
/*     */     public T getValue() {
/* 101 */       return this.value;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setValue(T newValue) {
/* 106 */       this.value = newValue;
/* 107 */       if (this.component != null)
/* 108 */         this.component.repaint(); 
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface GetterAndSetter<T> extends Getter<T>, Setter<T> {}
/*     */   
/*     */   public static interface Setter<T> {
/*     */     void setValue(T param1T);
/*     */   }
/*     */   
/*     */   public static interface Getter<T> {
/*     */     T getValue();
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\SafePropertySetter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */