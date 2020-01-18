/*     */ package dark.leech.text.get;
/*     */ 
/*     */ import dark.leech.text.action.Log;
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.models.Chapter;
/*     */ import dark.leech.text.models.Pager;
/*     */ import dark.leech.text.models.Post;
/*     */ import dark.leech.text.util.FileUtils;
/*     */ import dark.leech.text.util.SyntaxUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.SwingWorker;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PageExecute
/*     */   extends SwingWorker<ArrayList<Post>, Void>
/*     */ {
/*     */   private PageGetter pageGetter;
/*     */   private ChangeListener changeListener;
/*     */   private Pager pager;
/*  22 */   private String chset = "utf-8";
/*     */ 
/*     */ 
/*     */   
/*     */   private String savepath;
/*     */ 
/*     */ 
/*     */   
/*     */   protected ArrayList<Post> doInBackground() throws Exception {
/*  31 */     return this.pageGetter.getter(this.pager.getUrl());
/*     */   }
/*     */   
/*     */   public PageExecute clazz(Class<PageGetter> cl) {
/*     */     try {
/*  36 */       this.pageGetter = cl.newInstance();
/*  37 */     } catch (InstantiationException e) {
/*  38 */       Log.add(e);
/*  39 */     } catch (IllegalAccessException e) {
/*  40 */       Log.add(e);
/*     */     } 
/*  42 */     return this;
/*     */   }
/*     */   
/*     */   public PageExecute listener(ChangeListener changeListener) {
/*  46 */     this.changeListener = changeListener;
/*  47 */     return this;
/*     */   }
/*     */   
/*     */   public PageExecute path(String savepath) {
/*  51 */     this.savepath = savepath;
/*  52 */     return this;
/*     */   }
/*     */   
/*     */   public PageExecute charset(String chset) {
/*  56 */     this.chset = chset;
/*  57 */     return this;
/*     */   }
/*     */   
/*     */   public PageExecute applyTo(Pager pager) {
/*  61 */     this.pager = pager;
/*  62 */     return this;
/*     */   }
/*     */   
/*     */   private void write(String text, String ID) {
/*  66 */     FileUtils.string2file(Optimize(text), this.savepath + "/raw/" + ID + ".txt", this.chset);
/*     */   }
/*     */ 
/*     */   
/*     */   public void done() {
/*     */     try {
/*  72 */       List<Post> posts = get();
/*  73 */       List<Chapter> chapters = new ArrayList<>();
/*  74 */       for (int i = 0; i < posts.size(); i++) {
/*  75 */         Chapter chapter = new Chapter();
/*  76 */         Post post = posts.get(i);
/*  77 */         String text = post.getText();
/*  78 */         String id = this.pager.getId() + "C" + Integer.toString(i);
/*  79 */         if (text == null) {
/*  80 */           chapter.setError(true);
/*  81 */         } else if (text.length() < 1000) {
/*  82 */           if ((text.split("<img ")).length > 0)
/*  83 */           { chapter.setImageChapter(true); }
/*  84 */           else { chapter.setEmpty(true); }
/*     */         
/*  86 */         }  chapter.setUrl(this.pager.getUrl());
/*  87 */         chapter.setChapName(post.getChapName());
/*  88 */         chapter.setPartName(post.getPartName());
/*  89 */         chapter.setCompleted(true);
/*  90 */         chapter.setId(id);
/*  91 */         chapters.add(chapter);
/*  92 */         if (!chapter.isEmpty() || !chapter.isError()) {
/*  93 */           write(text, id);
/*     */         }
/*     */       } 
/*  96 */       this.pager.setChapter(chapters);
/*  97 */     } catch (Exception e) {
/*  98 */       Log.add(e);
/*  99 */       this.pager.setChapter(new ArrayList());
/*     */     } 
/* 101 */     this.changeListener.doChanger();
/*     */   }
/*     */ 
/*     */   
/*     */   private String Optimize(String src) {
/* 106 */     if (SyntaxUtils.REPLACE_FROM != null)
/* 107 */       for (int i = 0; i < SyntaxUtils.REPLACE_FROM.length; i++)
/* 108 */         src = src.replaceAll(SyntaxUtils.REPLACE_FROM[i], SyntaxUtils.REPLACE_TO[i]);  
/* 109 */     return src;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\get\PageExecute.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */