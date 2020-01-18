/*    */ package dark.leech.text.ui.main.plugin;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import dark.leech.text.enities.PluginEntity;
/*    */ import dark.leech.text.plugin.PluginManager;
/*    */ import dark.leech.text.ui.PanelTitle;
/*    */ import dark.leech.text.ui.material.JMDialog;
/*    */ import dark.leech.text.ui.material.JMScrollPane;
/*    */ import dark.leech.text.util.AppUtils;
/*    */ import dark.leech.text.util.FileUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.GridBagConstraints;
/*    */ import java.awt.GridBagLayout;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class PluginUI
/*    */   extends JMDialog {
/*    */   private PanelTitle pnTitle;
/*    */   private JPanel pnList;
/*    */   private GridBagConstraints gbc;
/*    */   
/*    */   public PluginUI() {
/* 26 */     onCreate();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onCreate() {
/* 31 */     super.onCreate();
/* 32 */     this.pnTitle = new PanelTitle();
/* 33 */     this.pnList = new JPanel(new GridBagLayout());
/*    */     
/* 35 */     this.pnTitle.setText("Plugins");
/* 36 */     this.pnTitle.addCloseListener(new ActionListener()
/*    */         {
/*    */           public void actionPerformed(ActionEvent e) {
/* 39 */             (new Thread(new Runnable()
/*    */                 {
/*    */                   public void run() {
/* 42 */                     for (PluginEntity pl : PluginManager.getManager().list())
/*    */                     {
/*    */                       
/* 45 */                       String path = AppUtils.curDir + "/tools/plugins/" + pl.getUuid() + ".plugin";
/*    */                       
/* 47 */                       FileUtils.string2file((new Gson()).toJson(pl), path);
/*    */                     }
/*    */                   
/*    */                   }
/* 51 */                 })).start();
/*    */             
/* 53 */             PluginUI.this.close();
/*    */           }
/*    */         });
/* 56 */     this.container.add((Component)this.pnTitle);
/* 57 */     this.pnTitle.setBounds(0, 0, 380, 45);
/*    */     
/* 59 */     this.pnList.setBackground(Color.WHITE);
/* 60 */     GridBagConstraints gi = new GridBagConstraints();
/* 61 */     gi.gridwidth = 0;
/* 62 */     gi.weightx = 1.0D;
/* 63 */     gi.weighty = 1.0D;
/* 64 */     JMScrollPane scrollPane = new JMScrollPane(this.pnList);
/*    */     
/* 66 */     JPanel demo = new JPanel();
/* 67 */     demo.setBackground(Color.WHITE);
/* 68 */     this.pnList.add(demo, gi);
/* 69 */     scrollPane.setBorder(null);
/* 70 */     scrollPane.getVerticalScrollBar().setUnitIncrement(20);
/* 71 */     this.container.add((Component)scrollPane);
/* 72 */     scrollPane.setBounds(0, 45, 380, 350);
/*    */ 
/*    */     
/* 75 */     this.gbc = new GridBagConstraints();
/* 76 */     this.gbc.gridwidth = 0;
/* 77 */     this.gbc.weightx = 1.0D;
/* 78 */     this.gbc.fill = 2;
/* 79 */     runOnUiThread(new Runnable()
/*    */         {
/*    */           public void run() {
/* 82 */             for (PluginEntity pluginGetter : PluginManager.getManager().list()) {
/* 83 */               PluginUI.this.addItem(pluginGetter);
/*    */             }
/*    */           }
/*    */         });
/* 87 */     setSize(380, 400);
/*    */   }
/*    */ 
/*    */   
/*    */   private void addItem(PluginEntity pluginGetter) {
/* 92 */     PluginItem pluginItem = new PluginItem(pluginGetter);
/* 93 */     this.pnList.add((Component)pluginItem, this.gbc, 0);
/* 94 */     validate();
/* 95 */     repaint();
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\plugin\PluginUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */