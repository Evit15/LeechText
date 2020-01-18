/*     */ package dark.leech.text.ui.main.export.config;
/*     */ 
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.ui.PanelTitle;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMCheckBox;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMProgressBar;
/*     */ import dark.leech.text.ui.material.JMTable;
/*     */ import dark.leech.text.ui.material.JMTextField;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ 
/*     */ class FindAndReplace
/*     */   extends JMDialog
/*     */ {
/*     */   private JMTable table;
/*     */   private JMTextField tfFind;
/*     */   private JMTextField tfReplace;
/*     */   
/*     */   public FindAndReplace(JMTable table) {
/*  27 */     this.table = table;
/*  28 */     setSize(290, 270);
/*  29 */     onCreate();
/*     */   }
/*     */   private JMCheckBox cbRegex; private JMCheckBox cbCase; private BasicButton btReplace; private JMProgressBar progressBar;
/*     */   
/*     */   protected void onCreate() {
/*  34 */     super.onCreate();
/*  35 */     setBorderColor(Color.LIGHT_GRAY);
/*  36 */     PanelTitle pnTitle = new PanelTitle();
/*  37 */     JLabel lbFind = new JLabel();
/*  38 */     this.tfFind = new JMTextField();
/*  39 */     JLabel lbReplace = new JLabel();
/*  40 */     this.tfReplace = new JMTextField();
/*  41 */     this.cbRegex = new JMCheckBox("Regex", true);
/*  42 */     this.cbCase = new JMCheckBox("Match case", true);
/*  43 */     this.btReplace = new BasicButton();
/*  44 */     this.progressBar = new JMProgressBar();
/*     */     
/*  46 */     pnTitle.setText("Thay thế");
/*  47 */     pnTitle.addCloseListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  50 */             FindAndReplace.this.close();
/*     */           }
/*     */         });
/*  53 */     this.container.add((Component)pnTitle);
/*  54 */     pnTitle.setBounds(0, 0, 290, 45);
/*  55 */     this.container.add((Component)this.tfFind);
/*  56 */     this.tfFind.setBounds(25, 70, 250, 35);
/*  57 */     this.container.add((Component)this.tfReplace);
/*  58 */     this.tfReplace.setBounds(25, 130, 250, 35);
/*     */ 
/*     */     
/*  61 */     lbFind.setText("Tìm");
/*  62 */     this.container.add(lbFind);
/*  63 */     lbFind.setBounds(10, 45, 65, 25);
/*     */ 
/*     */     
/*  66 */     lbReplace.setText("Thay thế");
/*  67 */     this.container.add(lbReplace);
/*  68 */     lbReplace.setBounds(10, 105, 70, 25);
/*     */ 
/*     */     
/*  71 */     this.container.add((Component)this.cbRegex);
/*  72 */     this.cbRegex.setChangeListener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/*  75 */             FindAndReplace.this.cbCase.setVisible(FindAndReplace.this.cbRegex.isChecked());
/*     */           }
/*     */         });
/*  78 */     this.cbRegex.setBounds(25, 170, 90, 30);
/*     */ 
/*     */     
/*  81 */     this.container.add((Component)this.cbCase);
/*  82 */     this.cbCase.setVisible(this.cbRegex.isChecked());
/*  83 */     this.cbCase.setBounds(25, 200, 100, 30);
/*     */ 
/*     */     
/*  86 */     this.btReplace.setText("Thay thế");
/*  87 */     this.btReplace.setBackground(Color.LIGHT_GRAY);
/*  88 */     this.btReplace.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  91 */             FindAndReplace.this.runOnUiThread(new Runnable()
/*     */                 {
/*     */                   public void run() {
/*  94 */                     FindAndReplace.this.btReplace.setEnabled(false);
/*  95 */                     FindAndReplace.this.replace();
/*  96 */                     FindAndReplace.this.btReplace.setEnabled(true);
/*     */                   }
/*     */                 });
/*     */           }
/*     */         });
/* 101 */     this.container.add((Component)this.btReplace);
/* 102 */     this.btReplace.setBounds(150, 170, 125, 60);
/*     */ 
/*     */     
/* 105 */     this.container.add((Component)this.progressBar);
/* 106 */     this.progressBar.setVisible(false);
/* 107 */     this.progressBar.setBounds(30, 230, 250, 19);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void replace() {
/* 113 */     String tFind = this.tfFind.getText();
/* 114 */     String tReplace = this.tfReplace.getText();
/* 115 */     DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
/* 116 */     this.progressBar.setVisible(true);
/* 117 */     if (this.table.getCellEditor() != null) this.table.getCellEditor().stopCellEditing(); 
/* 118 */     for (int i = 0; i < this.table.getRowCount(); i++) {
/* 119 */       for (int j = 1; j < 3; j++) {
/* 120 */         String text = (String)tableModel.getValueAt(i, j);
/* 121 */         if (text != null && 
/* 122 */           text.length() != 0)
/* 123 */           if (this.cbRegex.isChecked()) {
/* 124 */             text = text.replaceAll(this.cbCase.isChecked() ? "(?i)" : ("" + tFind), tReplace);
/*     */           } else {
/* 126 */             text = text.replace(tFind, tReplace);
/* 127 */           }   tableModel.setValueAt(text, i, j);
/* 128 */         tableModel.fireTableCellUpdated(i, j);
/*     */       } 
/* 130 */       this.progressBar.setPercent(i * 100 / this.table.getRowCount());
/*     */     } 
/* 132 */     this.progressBar.setVisible(false);
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\export\config\FindAndReplace.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */