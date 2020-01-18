/*     */ package dark.leech.text.ui;
/*     */ 
/*     */ import dark.leech.text.models.Chapter;
/*     */ import dark.leech.text.models.Pager;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMScrollPane;
/*     */ import dark.leech.text.ui.material.JMTable;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListDialog
/*     */   extends JMDialog
/*     */ {
/*     */   private JMScrollPane scPn;
/*     */   private JMTable tbList;
/*     */   private BasicButton btCancel;
/*     */   private BasicButton btOk;
/*     */   private BasicButton btSelect;
/*     */   private PanelTitle pnTitle;
/*     */   private List<Chapter> chapList;
/*     */   private List<Pager> pageList;
/*     */   private String parseList;
/*     */   private boolean selected = true;
/*     */   private boolean forum;
/*     */   
/*     */   public ListDialog(List<Pager> pageList, String parseList, boolean forum) {
/*  39 */     this.pageList = pageList;
/*  40 */     this.parseList = parseList;
/*  41 */     this.forum = forum;
/*  42 */     onCreate();
/*     */   }
/*     */ 
/*     */   
/*     */   public ListDialog(List<Chapter> chapList, String parseList) {
/*  47 */     this.parseList = parseList;
/*  48 */     this.chapList = chapList;
/*  49 */     this.forum = false;
/*  50 */     onCreate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  55 */     super.onCreate();
/*  56 */     this.scPn = new JMScrollPane();
/*  57 */     this.btCancel = new BasicButton();
/*  58 */     this.btOk = new BasicButton();
/*  59 */     this.btSelect = new BasicButton();
/*  60 */     this.pnTitle = new PanelTitle();
/*     */ 
/*     */     
/*  63 */     Object[] columnNames = { "", "Tên chương" };
/*  64 */     Object[][] data = (Object[][])null;
/*  65 */     if (this.forum) {
/*  66 */       data = new Object[this.pageList.size()][2];
/*  67 */       for (int i = 0; i < this.pageList.size(); i++) {
/*  68 */         data[i][0] = Boolean.valueOf(false);
/*  69 */         data[i][1] = ((Pager)this.pageList.get(i)).getName();
/*     */       } 
/*     */     } else {
/*  72 */       data = new Object[this.chapList.size()][2];
/*  73 */       for (int i = 0; i < this.chapList.size(); i++) {
/*  74 */         data[i][0] = Boolean.valueOf(false);
/*  75 */         data[i][1] = ((Chapter)this.chapList.get(i)).getChapName();
/*     */       } 
/*     */     } 
/*  78 */     this.tbList = new JMTable();
/*  79 */     doUpdateData(this.parseList, data);
/*  80 */     this.tbList.setModel(new DefaultTableModel(data, columnNames) {
/*  81 */           Class<?>[] columnTypes = new Class[] { Boolean.class, String.class };
/*     */ 
/*     */           
/*     */           public Class<?> getColumnClass(int columnIndex) {
/*  85 */             return this.columnTypes[columnIndex];
/*     */           }
/*     */         });
/*  88 */     doUpdateData(this.parseList, data);
/*  89 */     TableColumnModel cm = this.tbList.getColumnModel();
/*  90 */     cm.getColumn(0).setMaxWidth(30);
/*  91 */     this.tbList.setTableHeader(null);
/*  92 */     this.scPn.setViewportView((Component)this.tbList);
/*  93 */     this.scPn.getVerticalScrollBar().setUnitIncrement(20);
/*  94 */     this.container.add((Component)this.scPn);
/*  95 */     this.scPn.setBounds(0, 50, 348, 420);
/*     */ 
/*     */     
/*  98 */     this.btCancel.setText("HỦY");
/*  99 */     this.btCancel.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseClicked(MouseEvent e) {
/* 102 */             ListDialog.this.close();
/*     */           }
/*     */         });
/* 105 */     this.container.add((Component)this.btCancel);
/* 106 */     this.btCancel.setBounds(275, 475, 65, 30);
/*     */ 
/*     */     
/* 109 */     this.btOk.setText("OK");
/* 110 */     this.container.add((Component)this.btOk);
/* 111 */     this.btOk.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseClicked(MouseEvent e) {
/* 114 */             ListDialog.this.okClick();
/*     */           }
/*     */         });
/* 117 */     this.btOk.setBounds(210, 475, 60, 30);
/*     */ 
/*     */     
/* 120 */     this.btSelect.setText("BỎ CHỌN");
/* 121 */     this.container.add((Component)this.btSelect);
/* 122 */     this.btSelect.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseClicked(MouseEvent e) {
/* 125 */             ListDialog.this.doSelect();
/*     */           }
/*     */         });
/* 128 */     this.btSelect.setBounds(5, 475, 100, 30);
/*     */ 
/*     */     
/* 131 */     this.pnTitle.setText("Danh sách chương");
/* 132 */     this.pnTitle.addCloseListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 135 */             ListDialog.this.close();
/*     */           }
/*     */         });
/* 138 */     this.container.add(this.pnTitle);
/* 139 */     this.pnTitle.setBounds(0, 0, 350, 45);
/* 140 */     setSize(350, 520);
/*     */   }
/*     */ 
/*     */   
/*     */   private void okClick() {
/* 145 */     ArrayList<Integer> c = new ArrayList<>();
/* 146 */     DefaultTableModel tb = (DefaultTableModel)this.tbList.getModel();
/* 147 */     for (int i = 0; i < this.tbList.getRowCount(); i++) {
/* 148 */       if (this.tbList.getCellEditor() != null) this.tbList.getCellEditor().stopCellEditing(); 
/* 149 */       if (!this.forum) ((Chapter)this.chapList.get(i)).setChapName((String)this.tbList.getValueAt(i, 1)); 
/* 150 */       if (((Boolean)this.tbList.getValueAt(i, 0)).booleanValue()) c.add(Integer.valueOf(i + 1)); 
/*     */     } 
/* 152 */     if (c.size() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 156 */     doParseList(c);
/* 157 */     close();
/*     */   }
/*     */ 
/*     */   
/*     */   private void doSelect() {
/* 162 */     this.selected = !this.selected;
/* 163 */     this.btSelect.setText(this.selected ? "BỎ CHỌN" : "CHỌN");
/* 164 */     if (this.tbList.getSelectedRowCount() != 0) {
/* 165 */       int[] sl = this.tbList.getSelectedRows();
/*     */       
/* 167 */       for (int i = 0; i < sl.length; i++)
/* 168 */         this.tbList.setValueAt(Boolean.valueOf(this.selected), sl[i], 0); 
/*     */     } else {
/* 170 */       for (int i = 0; i < this.tbList.getRowCount(); i++) {
/* 171 */         this.tbList.setValueAt(Boolean.valueOf(this.selected), i, 0);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getParseList() {
/* 177 */     return this.parseList;
/*     */   }
/*     */   
/*     */   public void doUpdateData(String parseList, Object[][] data) {
/* 181 */     parseList = parseList.replaceAll("\\s+", "");
/* 182 */     String[] c = parseList.split(",");
/* 183 */     for (int i = 0; i < c.length; i++) {
/* 184 */       String[] cc = c[i].split("-");
/* 185 */       int j = Integer.parseInt(cc[0]); for (; j <= 
/* 186 */         Integer.parseInt(cc[cc.length - 1]); j++) {
/* 187 */         data[j - 1][0] = Boolean.valueOf(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doParseList(ArrayList<Integer> intMang) {
/* 196 */     this.parseList = "";
/* 197 */     for (int i = 0; i < intMang.size() - 1; i++) {
/* 198 */       if (((Integer)intMang.get(i)).intValue() + 1 == ((Integer)intMang.get(i + 1)).intValue())
/* 199 */       { this.parseList += i2s(((Integer)intMang.get(i)).intValue()) + "-"; }
/*     */       else
/* 201 */       { this.parseList += i2s(((Integer)intMang.get(i)).intValue()) + ","; } 
/* 202 */     }  this.parseList += i2s(((Integer)intMang.get(intMang.size() - 1)).intValue());
/* 203 */     this.parseList = this.parseList.replaceAll("(\\d+-).*?(\\d+(,|$))", "$1$2");
/*     */   }
/*     */ 
/*     */   
/*     */   private String i2s(int i) {
/* 208 */     return Integer.toString(i);
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\ListDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */