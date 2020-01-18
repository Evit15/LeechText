/*     */ package dark.leech.text.ui.main.export.config;
/*     */ import dark.leech.text.action.Config;
/*     */ import dark.leech.text.listeners.BlurListener;
/*     */ import dark.leech.text.listeners.ChangeListener;
import dark.leech.text.listeners.TableListener;
/*     */ import dark.leech.text.models.Chapter;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.ui.PanelTitle;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.button.CircleButton;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMMenuItem;
/*     */ import dark.leech.text.ui.material.JMPopupMenu;
/*     */ import dark.leech.text.ui.material.JMProgressBar;
/*     */ import dark.leech.text.ui.material.JMScrollPane;
/*     */ import dark.leech.text.ui.material.JMTable;
/*     */ import dark.leech.text.ui.material.JMTextField;
/*     */ import dark.leech.text.util.ColorUtils;
/*     */ import dark.leech.text.util.FileUtils;
import dark.leech.text.util.FontUtils;

/*     */ import java.awt.Component;
import java.awt.Desktop;
/*     */ import java.awt.Font;
import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.Clipboard;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.File;
import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.border.LineBorder;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ class ListUI extends JMDialog implements TableListener, ActionListener, KeyListener {
/*     */   private List<Chapter> chapList;
/*  39 */   private String[] nameButton = new String[] { "Auto Fix", "Tải ảnh", "Tải Lại", "Optimize" }; private JMTable tableList; private DefaultTableModel tableModel; private JMPopupMenu popupMenu;
/*     */   private JMPopupMenu popupAction;
/*     */   private String name;
/*     */   private BasicButton bt3;
/*     */   private JMProgressBar progressBar;
/*     */   private int action;
/*     */   private String path;
/*     */   private BasicButton btOk;
/*     */   private BasicButton btCancel;
/*     */   private JMMenuItem menuEdit;
/*     */   private JMMenuItem menuGoto;
/*     */   private JMMenuItem menuCopy;
/*     */   private JMMenuItem menuDelete;
/*     */   private JMMenuItem menuDeletes;
/*     */   private JMMenuItem menuMerge;
/*     */   private CircleButton btSearch;
/*     */   private ArrayList<Integer> idList;
/*     */   private Properties properties;
/*     */   
/*     */   public ListUI(List<Chapter> chapList, String name) {
/*  59 */     this.chapList = chapList;
/*  60 */     this.name = name;
/*  61 */     setSize(380, 430);
/*  62 */     runOnUiThread(new Runnable()
/*     */         {
/*     */           public void run() {
/*  65 */             ListUI.this.onCreate();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public ListUI(List<Chapter> chapList, String name, String path) {
/*  71 */     this.chapList = chapList;
/*  72 */     this.name = name;
/*  73 */     this.path = path;
/*  74 */     setSize(380, 430);
/*  75 */     runOnUiThread(new Runnable()
/*     */         {
/*     */           public void run() {
/*  78 */             ListUI.this.onCreate();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  85 */     super.onCreate();
/*  86 */     this.idList = new ArrayList<>();
/*  87 */     this.tableList = new JMTable(this.chapList);
/*  88 */     for (int i = 0; i < this.chapList.size(); i++)
/*  89 */       this.idList.add(Integer.valueOf(i)); 
/*  90 */     PanelTitle pnTitle = new PanelTitle();
/*  91 */     JMScrollPane scrollPane1 = new JMScrollPane();
/*  92 */     this.tableList = new JMTable(this.chapList);
/*  93 */     this.tableModel = (DefaultTableModel)this.tableList.getModel();
/*  94 */     this.btCancel = new BasicButton();
/*  95 */     this.btOk = new BasicButton();
/*  96 */     this.bt3 = new BasicButton();
/*  97 */     this.progressBar = new JMProgressBar();
/*  98 */     this.popupMenu = new JMPopupMenu();
/*  99 */     this.popupAction = new JMPopupMenu();
/* 100 */     this.menuEdit = new JMMenuItem("Sửa");
/* 101 */     this.menuGoto = new JMMenuItem("Đi tới");
/* 102 */     this.menuCopy = new JMMenuItem("Copy Link");
/* 103 */     this.menuDelete = new JMMenuItem("Xóa");
/* 104 */     this.menuDeletes = new JMMenuItem("Xóa");
/* 105 */     this.menuMerge = new JMMenuItem("Gộp");
/* 106 */     this.btSearch = new CircleButton("", 25.0F);
/*     */ 
/*     */     
/* 109 */     pnTitle.setText(this.name);
/* 110 */     pnTitle.addCloseListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 113 */             ListUI.this.close();
/*     */           }
/*     */         });
/*     */     
/* 117 */     pnTitle.add((Component)this.btSearch);
/* 118 */     this.btSearch.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 121 */             FindAndReplace findAndReplace = new FindAndReplace(ListUI.this.tableList);
/* 122 */             findAndReplace.setBlurListener((BlurListener)ListUI.this);
/* 123 */             findAndReplace.open();
/*     */           }
/*     */         });
/* 126 */     this.btSearch.setBounds(290, 5, 35, 35);
/* 127 */     this.container.add((Component)pnTitle);
/* 128 */     pnTitle.setBounds(0, 0, 380, 45);
/*     */ 
/*     */     
/* 131 */     scrollPane1.setViewportView((Component)this.tableList);
/* 132 */     scrollPane1.getVerticalScrollBar().setUnitIncrement(20);
/* 133 */     this.container.add((Component)scrollPane1);
/* 134 */     scrollPane1.setBounds(0, 45, 380, 335);
/*     */ 
/*     */     
/* 137 */     this.btCancel.setText("HỦY");
/* 138 */     this.btCancel.addActionListener(this);
/* 139 */     this.container.add((Component)this.btCancel);
/* 140 */     this.btCancel.setBounds(300, 390, 75, 30);
/*     */ 
/*     */     
/* 143 */     this.btOk.setText("OK");
/* 144 */     this.btOk.addActionListener(this);
/* 145 */     this.container.add((Component)this.btOk);
/* 146 */     this.btOk.setBounds(200, 390, 75, 30);
/*     */ 
/*     */     
/* 149 */     this.bt3.setText(this.nameButton[this.action]);
/* 150 */     this.bt3.addActionListener(this);
/* 151 */     this.container.add((Component)this.bt3);
/* 152 */     this.bt3.setBounds(10, 390, 160, 30);
/* 153 */     this.progressBar.setPercent(0);
/* 154 */     this.container.add((Component)this.progressBar);
/* 155 */     this.progressBar.setBounds(10, 390, 160, 30);
/* 156 */     this.progressBar.setVisible(false);
/*     */     
/* 158 */     this.menuMerge.addActionListener(this);
/* 159 */     this.menuDelete.addActionListener(this);
/* 160 */     this.menuDeletes.addActionListener(this);
/* 161 */     this.popupAction.add((JMenuItem)this.menuDeletes);
/* 162 */     this.popupAction.add((JMenuItem)this.menuMerge);
/*     */     
/* 164 */     this.menuGoto.addActionListener(this);
/* 165 */     this.menuCopy.addActionListener(this);
/* 166 */     this.menuEdit.addActionListener(this);
/* 167 */     this.popupMenu.add((JMenuItem)this.menuEdit);
/* 168 */     this.popupMenu.add((JMenuItem)this.menuDelete);
/* 169 */     this.popupMenu.add((JMenuItem)this.menuCopy);
/* 170 */     this.popupMenu.add((JMenuItem)this.menuGoto);
/* 171 */     this.popupMenu.setBorder(new LineBorder(ColorUtils.THEME_COLOR.brighter()));
/* 172 */     this.popupAction.setBorder(new LineBorder(ColorUtils.THEME_COLOR.brighter()));
/*     */     
/* 174 */     this.tableList.addMouseListener(new MouseAdapter() {
/*     */           private void showIfPopupTrigger(MouseEvent mouseEvent) {
/* 176 */             if (mouseEvent.isPopupTrigger()) {
/* 177 */               if (ListUI.this.tableList.getSelectedRowCount() == 1) {
/* 178 */                 ListUI.this.popupMenu.show(mouseEvent.getComponent(), mouseEvent
/* 179 */                     .getX(), mouseEvent.getY());
/* 180 */               } else if (ListUI.this.tableList.getSelectedRowCount() > 1) {
/* 181 */                 ListUI.this.popupAction.show(mouseEvent.getComponent(), mouseEvent
/* 182 */                     .getX(), mouseEvent.getY());
/*     */               } 
/*     */             }
/*     */           }
/*     */           
/*     */           public void mousePressed(MouseEvent mouseEvent) {
/* 188 */             showIfPopupTrigger(mouseEvent);
/*     */           }
/*     */           
/*     */           public void mouseReleased(MouseEvent mouseEvent) {
/* 192 */             showIfPopupTrigger(mouseEvent);
/*     */           }
/*     */         });
/* 195 */     this.tableList.addKeyListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAction(int action) {
/* 200 */     this.action = action;
/*     */   }
/*     */   
/*     */   private void doAction() {
/* 204 */     this.bt3.setVisible(false);
/* 205 */     this.progressBar.setVisible(true);
/* 206 */     runOnUiThread(new Runnable()
/*     */         {
/*     */           public void run() {
/* 209 */             switch (ListUI.this.action) {
/*     */               case 0:
/* 211 */                 ListUI.this.fixName();
/*     */                 break;
/*     */               case 1:
/* 214 */                 ListUI.this.downImg();
/*     */                 break;
/*     */               case 2:
/* 217 */                 ListUI.this.fixError();
/*     */                 break;
/*     */               case 3:
/* 220 */                 ListUI.this.Optimize();
/*     */                 break;
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void doEdit() {
/* 228 */     final int row = this.tableList.getSelectedRow();
/* 229 */     final Chapter chapter = this.chapList.get(((Integer)this.idList.get(row)).intValue());
/* 230 */     Edit edit = new Edit(chapter);
/* 231 */     edit.setBlurListener((BlurListener)this);
/* 232 */     edit.setChangeListener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/* 235 */             ListUI.this.tableList.setValueAt(chapter.getPartName(), row, 1);
/* 236 */             ListUI.this.tableList.setValueAt(chapter.getChapName(), row, 2);
/*     */           }
/*     */         });
/* 239 */     edit.open();
/*     */   }
/*     */ 
/*     */   
/*     */   private void doGoto() {
/* 244 */     int row = this.tableList.getSelectedRow();
/* 245 */     Chapter chapter = this.chapList.get(((Integer)this.idList.get(row)).intValue());
/*     */     try {
/* 247 */       Desktop.getDesktop().browse((new URL(chapter.getUrl())).toURI());
/* 248 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private void doCopy() {
/* 253 */     int row = this.tableList.getSelectedRow();
/* 254 */     Chapter chapter = this.chapList.get(((Integer)this.idList.get(row)).intValue());
/* 255 */     StringSelection stringSelection = new StringSelection(chapter.getUrl());
/* 256 */     Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 257 */     clpbrd.setContents(stringSelection, null);
/*     */   }
/*     */   
/*     */   private void doDelete() {
/* 261 */     int[] rows = this.tableList.getSelectedRows();
/* 262 */     for (int i = rows.length - 1; i >= 0; i--) {
/* 263 */       this.tableModel.removeRow(rows[i]);
/* 264 */       this.idList.remove(rows[i]);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doMerge() {
/* 269 */     int[] rows = this.tableList.getSelectedRows();
/* 270 */     File file = new File(FileUtils.validate(this.path + "/raw/" + ((Chapter)this.chapList.get(((Integer)this.idList.get(rows[0])).intValue())).getId() + ".txt"));
/* 271 */     for (int i = 1; i < rows.length; i++) {
/* 272 */       FileUtils.add2file(new File(FileUtils.validate(this.path + "/raw/" + ((Chapter)this.chapList.get(((Integer)this.idList.get(rows[i])).intValue())).getId() + ".txt")), file);
/* 273 */       this.tableModel.removeRow(rows[rows.length - i]);
/* 274 */       this.idList.remove(rows[rows.length - i]);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doSave() {
/* 279 */     int vt = this.idList.size() - 1;
/* 280 */     if (this.tableList.getCellEditor() != null) this.tableList.getCellEditor().stopCellEditing(); 
/* 281 */     for (int i = this.chapList.size() - 1; i >= 0; i--) {
/* 282 */       if (i == ((Integer)this.idList.get(vt)).intValue())
/* 283 */       { ((Chapter)this.chapList.get(i)).setPartName((String)this.tableList.getValueAt(vt, 1));
/* 284 */         ((Chapter)this.chapList.get(i)).setChapName((String)this.tableList.getValueAt(vt, 2));
/* 285 */         vt--; }
/* 286 */       else { this.chapList.remove(i); }
/*     */     
/* 288 */     }  close();
/*     */   }
/*     */   
/*     */   private void fixName() {
/* 292 */     Config config = new Config(this.chapList);
/* 293 */     config.addTableListener(this);
/* 294 */     config.autoFixName();
/*     */   }
/*     */   
/*     */   private void downImg() {
/* 298 */     Config config = new Config(this.chapList);
/* 299 */     config.addTableListener(this);
/* 300 */     config.setPath(this.path);
/* 301 */     config.downloadImg();
/*     */   }
/*     */   
/*     */   private void fixError() {
/* 305 */     Config config = new Config(this.chapList);
/* 306 */     config.setBlurListener((BlurListener)this);
/* 307 */     config.addTableListener(this);
/* 308 */     config.downloadChap(this.properties);
/*     */   }
/*     */   
/*     */   public void setProperties(Properties properties) {
/* 312 */     this.properties = properties;
/*     */   }
/*     */   
/*     */   private void Optimize() {
/* 316 */     Config config = new Config(this.chapList);
/* 317 */     config.addTableListener(this);
/* 318 */     config.Optimize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 323 */     if (e.getSource() == this.menuCopy)
/* 324 */       doCopy(); 
/* 325 */     if (e.getSource() == this.menuEdit)
/* 326 */       doEdit(); 
/* 327 */     if (e.getSource() == this.menuGoto)
/* 328 */       doGoto(); 
/* 329 */     if (e.getSource() == this.menuDeletes || e.getSource() == this.menuDelete)
/* 330 */       doDelete(); 
/* 331 */     if (e.getSource() == this.menuMerge)
/* 332 */       doMerge(); 
/* 333 */     if (e.getSource() == this.bt3)
/* 334 */       doAction(); 
/* 335 */     if (e.getSource() == this.btOk)
/* 336 */       doSave(); 
/* 337 */     if (e.getSource() == this.btCancel) {
/* 338 */       close();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyTyped(KeyEvent e) {}
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent e) {
/* 348 */     if (e.getKeyCode() == 72 && (e.getModifiers() & 0x2) != 0) {
/* 349 */       FindAndReplace findAndReplace = new FindAndReplace(this.tableList);
/* 350 */       findAndReplace.setBlurListener((BlurListener)this);
/* 351 */       findAndReplace.open();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent e) {}
/*     */   
/*     */   private class Edit
/*     */     extends JMDialog
/*     */   {
/*     */     private PanelTitle pnTitle;
/*     */     private JMTextField tfPart;
/*     */     private JMTextField tfChap;
/*     */     private JMScrollPane scrollPane1;
/*     */     private JTextArea taText;
/*     */     private JLabel lbPart;
/*     */     private JLabel lbChap;
/*     */     private BasicButton btOk;
/*     */     private BasicButton btCancel;
/*     */     private Chapter chapter;
/*     */     
/*     */     public Edit(Chapter chapter) {
/* 373 */       this.chapter = chapter;
/* 374 */       onCreate();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void onCreate() {
/* 379 */       super.onCreate();
/* 380 */       this.pnTitle = new PanelTitle();
/* 381 */       this.tfPart = new JMTextField();
/* 382 */       this.tfChap = new JMTextField();
/* 383 */       this.scrollPane1 = new JMScrollPane();
/* 384 */       this.taText = new JTextArea();
/* 385 */       this.lbPart = new JLabel();
/* 386 */       this.lbChap = new JLabel();
/* 387 */       this.btOk = new BasicButton();
/* 388 */       this.btCancel = new BasicButton();
/*     */ 
/*     */       
/* 391 */       this.pnTitle.setText(this.chapter.getChapName());
/* 392 */       this.pnTitle.addCloseListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent e) {
/* 395 */               ListUI.Edit.this.close();
/*     */             }
/*     */           });
/* 398 */       this.container.add((Component)this.pnTitle);
/* 399 */       this.pnTitle.setBounds(0, 0, 310, 45);
/* 400 */       this.container.add((Component)this.tfPart);
/* 401 */       this.tfPart.setText(this.chapter.getPartName());
/* 402 */       this.tfPart.setBounds(80, 55, 220, 30);
/* 403 */       this.container.add((Component)this.tfChap);
/* 404 */       this.tfChap.setBounds(80, 95, 220, 30);
/* 405 */       this.tfChap.setText(this.chapter.getChapName());
/*     */ 
/*     */       
/* 408 */       this.scrollPane1.setViewportView(this.taText);
/* 409 */       this.taText.setLineWrap(true);
/* 410 */       this.taText.setWrapStyleWord(true);
/* 411 */       Font font = FontUtils.codeFont(12.0F);
/* 412 */       String text = FileUtils.file2string(ListUI.this.path + "/raw/" + this.chapter.getId() + ".txt");
/* 413 */       if (text != null && 
/* 414 */         font.canDisplayUpTo(text) == -1)
/* 415 */         this.taText.setFont(font); 
/* 416 */       this.taText.setText(text);
/* 417 */       this.taText.setForeground(ColorUtils.THEME_COLOR);
/* 418 */       this.container.add((Component)this.scrollPane1);
/* 419 */       this.scrollPane1.setBounds(10, 135, 290, 220);
/*     */ 
/*     */       
/* 422 */       this.lbPart.setText("Quyển");
/* 423 */       this.container.add(this.lbPart);
/* 424 */       this.lbPart.setBounds(5, 55, 70, 30);
/*     */ 
/*     */       
/* 427 */       this.lbChap.setText("Chương");
/* 428 */       this.container.add(this.lbChap);
/* 429 */       this.lbChap.setBounds(5, 95, 70, 30);
/* 430 */       this.btOk.setText("OK");
/* 431 */       this.container.add((Component)this.btOk);
/* 432 */       this.btOk.setBounds(125, 360, 75, 30);
/* 433 */       this.btOk.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent e) {
/* 436 */               ListUI.Edit.this.doClick();
/*     */             }
/*     */           });
/* 439 */       this.btCancel.setText("HỦY");
/* 440 */       this.container.add((Component)this.btCancel);
/* 441 */       this.btCancel.setBounds(225, 360, 75, 30);
/* 442 */       this.btCancel.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent e) {
/* 445 */               ListUI.Edit.this.close();
/*     */             }
/*     */           });
/* 448 */       setSize(310, 400);
/*     */     }
/*     */     
/*     */     private void doClick() {
/* 452 */       this.chapter.setPartName(this.tfPart.getText());
/* 453 */       this.chapter.setChapName(this.tfChap.getText());
/* 454 */       FileUtils.string2file(this.taText.getText(), ListUI.this.path + "/raw/" + this.chapter.getId() + ".txt");
/* 455 */       close();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateData(int row, Chapter chapter) {
/* 462 */     if (this.tableList.getCellEditor() != null) this.tableList.getCellEditor().stopCellEditing();
			  if(chapter.isDownloadImgSucess()) {
				  this.tableModel.setValueAt("Ok", row, 0);
			  }else {
				  this.tableModel.setValueAt("Fail", row, 0);
			  }
/* 463 */     
/* 464 */     this.tableModel.setValueAt(chapter.getPartName(), row, 1);
/* 465 */     this.tableModel.setValueAt(chapter.getChapName(), row, 2);
/* 466 */     this.tableModel.fireTableCellUpdated(row, 0);
/* 467 */     this.tableModel.fireTableCellUpdated(row, 1);
/* 468 */     this.tableModel.fireTableCellUpdated(row, 2);
/* 469 */     this.progressBar.setPercent((row + 1) * 100 / this.chapList.size());
/* 470 */     if (row + 1 == this.chapList.size()) this.progressBar.setVisible(false); 
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\export\config\ListUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */