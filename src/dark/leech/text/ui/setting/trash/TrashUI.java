/*     */ package dark.leech.text.ui.setting.trash;
/*     */ 
/*     */ import dark.leech.text.listeners.BlurListener;
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.listeners.RemoveListener;
/*     */ import dark.leech.text.models.Trash;
/*     */ import dark.leech.text.ui.PanelTitle;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMScrollPane;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ class TrashUI extends JMDialog implements RemoveListener {
/*  22 */   public int numTrash = 0;
/*     */   private List<Trash> trash;
/*     */   private BasicButton add;
/*     */   private BasicButton ok;
/*     */   private BasicButton cancel;
/*     */   private JPanel body;
/*     */   private GridBagConstraints gbc;
/*     */   private boolean done;
/*     */   
/*     */   public TrashUI(List<Trash> trash) {
/*  32 */     this.done = false;
/*  33 */     this.numTrash = 0;
/*  34 */     this.trash = trash;
/*  35 */     onCreate();
/*  36 */     runOnUiThread(new Runnable()
/*     */         {
/*     */           public void run() {
/*  39 */             TrashUI.this.load();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  47 */     super.onCreate();
/*  48 */     this.add = new BasicButton();
/*  49 */     this.ok = new BasicButton();
/*  50 */     this.cancel = new BasicButton();
/*  51 */     this.body = new JPanel(new GridBagLayout());
/*  52 */     this.body.setBackground(Color.white);
/*  53 */     PanelTitle pnTitle = new PanelTitle();
/*     */ 
/*     */     
/*  56 */     pnTitle.setText("Lọc rác");
/*  57 */     pnTitle.addCloseListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  60 */             TrashUI.this.close();
/*     */           }
/*     */         });
/*  63 */     pnTitle.setBounds(0, 0, 330, 45);
/*  64 */     this.container.add((Component)pnTitle);
/*  65 */     this.body.setBackground(Color.white);
/*  66 */     GridBagConstraints gi = new GridBagConstraints();
/*  67 */     gi.gridwidth = 0;
/*  68 */     gi.weightx = 1.0D;
/*  69 */     gi.weighty = 1.0D;
/*  70 */     JMScrollPane scrollPane = new JMScrollPane(this.body);
/*     */     
/*  72 */     JPanel demo = new JPanel();
/*  73 */     demo.setBackground(Color.WHITE);
/*  74 */     this.body.add(demo, gi);
/*  75 */     scrollPane.setBorder(null);
/*  76 */     scrollPane.getVerticalScrollBar().setUnitIncrement(20);
/*  77 */     this.container.add((Component)scrollPane);
/*  78 */     scrollPane.setBounds(0, 45, 327, 270);
/*     */     
/*  80 */     this.add.setText("THÊM");
/*  81 */     this.add.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  84 */             TrashUI.this.addItem();
/*     */           }
/*     */         });
/*  87 */     this.container.add((Component)this.add);
/*  88 */     this.add.setBounds(10, 320, 100, 30);
/*     */     
/*  90 */     this.ok.setText("OK");
/*  91 */     this.ok.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  94 */             TrashUI.this.done = true;
/*  95 */             TrashUI.this.close();
/*     */           }
/*     */         });
/*  98 */     this.container.add((Component)this.ok);
/*  99 */     this.ok.setBounds(170, 320, 70, 30);
/*     */     
/* 101 */     this.cancel.setText("HỦY");
/* 102 */     this.cancel.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 105 */             TrashUI.this.close();
/*     */           }
/*     */         });
/* 108 */     this.container.add((Component)this.cancel);
/* 109 */     this.cancel.setBounds(250, 320, 70, 30);
/* 110 */     this.gbc = new GridBagConstraints();
/* 111 */     this.gbc.gridwidth = 0;
/* 112 */     this.gbc.weightx = 1.0D;
/* 113 */     this.gbc.fill = 2;
/* 114 */     setSize(330, 370);
/*     */   }
/*     */   
/*     */   public void load() {
/* 118 */     for (int i = 0; i < this.trash.size(); i++)
/* 119 */       addItem(this.trash.get(i)); 
/*     */   }
/*     */   
/*     */   public List<Trash> getTrash() {
/* 123 */     this.trash = new ArrayList<>();
/* 124 */     for (int i = 0; i < this.body.getComponentCount() - 1; i++)
/* 125 */       this.trash.add(((TrashItem)this.body.getComponent(i)).getTrash()); 
/* 126 */     return this.trash;
/*     */   }
/*     */   
/*     */   private void addItem() {
/* 130 */     final TrashItemIDialog trashItemDialog = new TrashItemIDialog();
/* 131 */     trashItemDialog.setBlurListener((BlurListener)this);
/* 132 */     trashItemDialog.setChangeListener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/* 135 */             TrashUI.this.addItem(trashItemDialog.getTrash());
/*     */           }
/*     */         });
/* 138 */     trashItemDialog.open();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addItem(Trash trash) {
/* 144 */     TrashItem tr = new TrashItem(trash);
/* 145 */     tr.addBlurListener((BlurListener)this);
/* 146 */     tr.addRemoveListener(this);
/* 147 */     this.body.add((Component)tr, this.gbc, this.numTrash);
/* 148 */     validate();
/* 149 */     repaint();
/* 150 */     this.numTrash++;
/*     */   }
/*     */   
/*     */   public void removeComponent(Component comp) {
/* 154 */     this.body.remove(comp);
/* 155 */     this.body.updateUI();
/* 156 */     this.numTrash--;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\setting\trash\TrashUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */