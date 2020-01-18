/*     */ package dark.leech.text.action;
/*     */ 
/*     */ import dark.leech.text.enities.PluginEntity;
/*     */ import dark.leech.text.get.ChapExecute;
/*     */ import dark.leech.text.listeners.BlurListener;
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.listeners.TableListener;
/*     */ import dark.leech.text.models.Chapter;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.plugin.PluginManager;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.util.AppUtils;
/*     */ import dark.leech.text.util.FileUtils;
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ public class Config {
/*     */   private List<Chapter> chapList;
/*     */   private String path;
/*     */   private TableListener tableListener;
/*     */   private int index;
/*     */   private PluginEntity pluginGetter;
/*     */   private BlurListener blurListener;
/*  32 */   private int errorCount = 0;
/*     */   
/*     */   public Config(List<Chapter> chapList) {
/*  35 */     this.chapList = chapList;
/*     */   }
/*     */   
/*     */   public void setPath(String path) {
/*  39 */     this.path = path;
/*     */   }
/*     */   
/*     */   public List<Chapter> checkError() {
/*  43 */     ArrayList<Chapter> imgErr = new ArrayList<>();
/*  44 */     for (int i = 0; i < this.chapList.size(); i++) {
/*  45 */       if (((Chapter)this.chapList.get(i)).isError())
/*  46 */         imgErr.add(this.chapList.get(i)); 
/*  47 */     }  return imgErr;
/*     */   }
/*     */   
/*     */   public List<Chapter> checkImg() {
/*  51 */     ArrayList<Chapter> imgList = new ArrayList<>();
/*  52 */     for (int i = 0; i < this.chapList.size(); i++) {
/*  53 */       if (((Chapter)this.chapList.get(i)).isImageChapter())
/*  54 */         imgList.add(this.chapList.get(i)); 
/*     */     } 
/*  56 */     return imgList;
/*     */   }
/*     */   
/*     */   public ArrayList<Chapter> checkName() {
/*  60 */     ArrayList<Chapter> nameList = new ArrayList<>();
/*  61 */     for (Chapter c : this.chapList) {
/*  62 */       if (findMatchs(c.getChapName(), "^(Ch..ng|H.i) \\d+([\\+\\.-]\\d+|)") != 1) {
/*  63 */         nameList.add(c); continue;
/*  64 */       }  if (findMatchs(c.getChapName(), "Ch..ng \\d+") > 1)
/*  65 */         nameList.add(c); 
/*  66 */     }  return nameList;
/*     */   }
/*     */   
/*     */   private void splitPartName(Chapter chapter) throws Exception {
/*  70 */     if (chapter.getPartName().length() > 1)
/*  71 */       return;  String regex = "((Quy.n |Q.|Q)\\d+\\s*[:-](.*?)*)\\s*(([Cc]h..ng|Hồi)\\s+\\d+)";
/*  72 */     if (findMatchs(chapter.getChapName(), "(Quy.n |Q\\.|Q)\\d+([\\+\\.-]\\d+|)") == 1) {
/*  73 */       String partName = splitMatchs(chapter.getChapName(), regex, 1).replaceAll("\\s*[:-]\\s*$", "");
/*  74 */       String chapName = chapter.getChapName();
/*  75 */       chapName = chapName.replace(partName, "").replaceAll("^\\s*[:-]\\s*", "");
/*  76 */       partName = partName.replaceAll("Q\\.|Q(\\d+)", "Quyển $1");
/*  77 */       chapter.setChapName(chapName);
/*  78 */       chapter.setPartName(partName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void autoFixName() {
/*  83 */     for (int i = 0; i < this.chapList.size(); i++) {
/*     */       try {
/*  85 */         Chapter chapter = new Chapter(((Chapter)this.chapList.get(i)).getUrl(), ((Chapter)this.chapList.get(i)).getId(), ((Chapter)this.chapList.get(i)).getPartName(), ((Chapter)this.chapList.get(i)).getChapName());
/*  86 */         splitPartName(chapter);
/*  87 */         chapter.setChapName(fixName(chapter.getChapName()));
/*  88 */         chapter.setPartName(fixName(chapter.getPartName()));
/*  89 */         this.tableListener.updateData(i, chapter);
/*  90 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String fixName(String name) {
/*  96 */     if (name == null) return ""; 
/*  97 */     if (name.length() == 0) return name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     name = name.replaceAll("Chương \\d+\\s*[:-]\\s*(Chương \\d+.*?$)", "$1").replaceAll("^([hH]ồi|[đĐ]ệ) (\\d+)", "Chương $1").replaceAll("(\\d+) [Cc]h..ng", "Chương $1").replaceAll("\\s+", " ").replaceAll("Chương (\\d+)\\s*[-\\+:]\\s*(\\d+)", "Chương $1+$2").replaceAll("(Chương \\d+)\\s*[;:-]+\\s*", "$1: ").replaceAll("(Chương \\d+\\+\\d+)\\s*[;:-]+\\s*", "$1: ");
/* 105 */     return name;
/*     */   }
/*     */   
/*     */   private String upperFirst(String name) {
/* 109 */     name = name.toLowerCase();
/* 110 */     char[] ch = name.toCharArray();
/* 111 */     ch[0] = Character.toUpperCase(ch[0]);
/* 112 */     for (int i = 1; i < ch.length; i++) {
/* 113 */       if (Character.toLowerCase(ch[i - 1]) == Character.toUpperCase(ch[i - 1]))
/* 114 */         ch[i] = Character.toUpperCase(ch[i]); 
/*     */     } 
/* 116 */     return new String(ch);
/*     */   }
/*     */   
/*     */   public void Optimize() {
/* 120 */     for (int i = 0; i < this.chapList.size(); i++) {
/*     */       try {
/* 122 */         Chapter chapter = new Chapter(((Chapter)this.chapList.get(i)).getUrl(), ((Chapter)this.chapList.get(i)).getId(), ((Chapter)this.chapList.get(i)).getPartName(), ((Chapter)this.chapList.get(i)).getChapName());
/* 123 */         chapter.setChapName(Optimize(chapter.getChapName()));
/* 124 */         chapter.setPartName(Optimize(chapter.getPartName()));
/* 125 */         this.tableListener.updateData(i, chapter);
/* 126 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String Optimize(String name) {
/* 132 */     if (name.length() == 0) return name; 
/* 133 */     name = upperFirst(name);
/* 134 */     name = fixName(name);
/* 135 */     return name;
/*     */   }
/*     */   
/*     */   public void downloadChap(Properties properties) {
/* 139 */     this.pluginGetter = PluginManager.getManager().get(properties.getUrl());
/* 140 */     this.errorCount = 0;
/* 141 */     this.index = 0;
/* 142 */     download(properties);
/*     */   }
/*     */   
/*     */   private synchronized void download(final Properties properties) {
/* 146 */     AppUtils.pause(SettingUtils.DELAY);
/* 147 */     final Chapter chapter = this.chapList.get(this.index);
/* 148 */     if (!chapter.isError()) {
/* 149 */       this.tableListener.updateData(this.index, this.chapList.get(this.index));
/* 150 */       this.index++;
/* 151 */       download(properties);
/*     */       return;
/*     */     } 
/* 154 */     (new ChapExecute())
/* 155 */       .plugin(this.pluginGetter)
/* 156 */       .listener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/* 159 */             if (chapter.isError()) {
/* 160 */               Config.this.errorCount++;
/*     */             } else {
/* 162 */               chapter.setError(false);
/* 163 */               Config.this.tableListener.updateData(Config.this.index, chapter);
/*     */             } 
/* 165 */             Config.this.index++;
/* 166 */             if (Config.this.index >= Config.this.chapList.size()) {
/* 167 */               History.getHistory().save(properties);
/* 168 */               if (Config.this.errorCount > 0) {
/* 169 */                 final Config.ConfirmDialog dialog = new Config.ConfirmDialog();
/* 170 */                 dialog.setConfirmListener(new Config.ConfirmListener()
/*     */                     {
/*     */                       public void confirm() {
/* 173 */                         Config.this.downloadChap(properties);
/* 174 */                         dialog.close();
/*     */                       }
/*     */ 
/*     */                       
/*     */                       public void cancel() {
/* 179 */                         dialog.close();
/*     */                       }
/*     */                     });
/* 182 */                 dialog.open();
/*     */               } 
/*     */               return;
/*     */             } 
/* 186 */             Config.this.download(properties);
/*     */           }
/* 189 */         }).charset(properties.getCharset())
/* 190 */       .path(properties.getSavePath())
/* 191 */       .applyTo(chapter)
/* 192 */       .execute();
/*     */   }
/*     */   
/*     */   public void downloadImg() {
/* 196 */     for (int i = 0; i < this.chapList.size(); i++) {
/*     */       try {
/* 198 */         downloadImg(this.chapList.get(i));
/* 199 */         this.tableListener.updateData(i, this.chapList.get(i));
/* 200 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void downloadImg(Chapter chapter) {
/* 206 */     String text = FileUtils.file2string(this.path + "/raw/" + chapter.getId() + ".txt");
/* 207 */     ArrayList<String> imgList = new ArrayList<>();
/* 208 */     Pattern r = Pattern.compile("<img.*?src=\"(.*?)\"", 8);
/* 209 */     Matcher m = r.matcher(text);
/* 210 */     while (m.find())
/* 211 */       imgList.add(m.group(1)); 
/* 212 */     for (int i = 0; i < imgList.size(); i++) {
/* 213 */       String imgPath = imgList.get(i);
/* 214 */       if (imgPath.startsWith("http")) {
/* 215 */         String img = imgPath.substring(imgPath.lastIndexOf("."), imgPath.length()).toLowerCase();
/* 216 */         text = text.replace(imgPath, "../Images/" + chapter.getId() + "_" + Integer.toString(i) + img).replace("\">", "\"/>");
/* 217 */         img = this.path + "/data/Images/" + chapter.getId() + "_" + Integer.toString(i) + img;
/* 218 */         FileUtils.url2file(imgList.get(i), img);
/*     */       } 
/* 220 */     }  FileUtils.string2file(text, this.path + "/raw/" + chapter.getId() + ".txt");
/*     */   }
/*     */   
/*     */   private int findMatchs(String src, String regex) {
/* 224 */     if (src == null) return 0; 
/* 225 */     Pattern r = Pattern.compile(regex);
/* 226 */     Matcher m = r.matcher(src);
/* 227 */     int match = 0;
/* 228 */     while (m.find()) {
/* 229 */       match++;
/*     */     }
/* 231 */     return match;
/*     */   }
/*     */   
/*     */   private String splitMatchs(String src, String regex, int group) {
/* 235 */     if (src == null) return ""; 
/* 236 */     Pattern r = Pattern.compile(regex);
/* 237 */     Matcher m = r.matcher(src);
/* 238 */     String math = "";
/* 239 */     if (m.find())
/* 240 */       math = m.group(group); 
/* 241 */     return math;
/*     */   }
/*     */   
/*     */   public void setBlurListener(BlurListener blurListener) {
/* 245 */     this.blurListener = blurListener;
/*     */   }
/*     */   
/*     */   public void addTableListener(TableListener tableListener) {
/* 249 */     this.tableListener = tableListener;
/*     */   }
/*     */   
/*     */   public static interface ConfirmListener {
/*     */     void confirm();
/*     */     
/*     */     void cancel();
/*     */   }
/*     */   
/*     */   class ConfirmDialog extends JMDialog {
/*     */     private BasicButton btConfirm;
/*     */     private BasicButton btCancel;
/*     */     private Config.ConfirmListener confirmListener;
/*     */     
/*     */     public ConfirmDialog() {
/* 264 */       setSize(300, 150);
/* 265 */       runOnUiThread(new Runnable()
/*     */           {
/*     */             public void run() {
/* 268 */               Config.ConfirmDialog.this.onCreate();
/*     */             }
/*     */           });
/*     */     }
/*     */ 
/*     */     
/*     */     protected void onCreate() {
/* 275 */       super.onCreate();
/* 276 */       this.btConfirm = new BasicButton();
/* 277 */       this.btCancel = new BasicButton();
/* 278 */       JLabel label = new JLabel("Còn " + Config.this.errorCount + " chương lỗi");
/* 279 */       JLabel label1 = new JLabel("Tải tiếp các chương lỗi???");
/*     */       
/* 281 */       this.btConfirm.setText("XÁC NHẬN");
/* 282 */       this.btCancel.setText("HỦY");
/*     */       
/* 284 */       this.container.add(label);
/* 285 */       label.setBounds(50, 10, 200, 30);
/* 286 */       this.container.add(label1);
/* 287 */       label1.setBounds(30, 50, 200, 30);
/* 288 */       this.container.add((Component)this.btConfirm);
/* 289 */       this.btConfirm.setBounds(10, 100, 100, 35);
/* 290 */       this.container.add((Component)this.btCancel);
/* 291 */       this.btCancel.setBounds(190, 100, 100, 35);
/*     */       
/* 293 */       this.btConfirm.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent e) {
/* 296 */               if (Config.ConfirmDialog.this.confirmListener != null)
/* 297 */                 Config.ConfirmDialog.this.confirmListener.confirm(); 
/* 298 */               Config.ConfirmDialog.this.close();
/*     */             }
/*     */           });
/* 301 */       this.btCancel.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent e) {
/* 304 */               if (Config.ConfirmDialog.this.confirmListener != null)
/* 305 */                 Config.ConfirmDialog.this.confirmListener.cancel(); 
/* 306 */               Config.ConfirmDialog.this.close();
/*     */             }
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setConfirmListener(Config.ConfirmListener confirmListener) {
/* 314 */       this.confirmListener = confirmListener;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\action\Config.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */