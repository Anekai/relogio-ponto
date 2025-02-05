/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.ibm.icu.util.Calendar;
import configuration.ParamConfig;
import entities.Funcionario;
import configuration.SpringConfig;
import framework.Decriptador;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import services.EstadoService;
import services.FuncionarioService;

/**
 *
 * @author alexa
 */
public class TelaLogin extends javax.swing.JFrame {
    
    private Funcionario funcionarioLogado;

    public TelaLogin() {
        initComponents();
        SpringConfig.context.getBean(EstadoService.class).find();
        this.setLocationRelativeTo(null);
    }
    
    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLogin = new javax.swing.JPanel();
        labelLogin = new javax.swing.JLabel();
        fieldLogin = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        buttonLogin = new javax.swing.JButton();
        fieldSenha = new javax.swing.JPasswordField();
        labelLoginErro = new javax.swing.JLabel();
        labelValidade = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cinemax");

        panelLogin.setBorder(javax.swing.BorderFactory.createTitledBorder("Entre"));
        panelLogin.setPreferredSize(new java.awt.Dimension(400, 180));

        labelLogin.setText("Login:");

        labelSenha.setText("Senha:");

        buttonLogin.setText("Login");
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });

        labelLoginErro.setForeground(java.awt.Color.red);
        labelLoginErro.setText("          ");

        labelValidade.setForeground(java.awt.Color.red);
        labelValidade.setText("             ");

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLogin)
                            .addComponent(labelSenha))
                        .addGap(10, 10, 10)
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLoginLayout.createSequentialGroup()
                                .addComponent(labelLoginErro)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(fieldSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addComponent(fieldLogin)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonLogin))
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addComponent(labelValidade)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelValidade)
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLogin)
                    .addComponent(fieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSenha)
                    .addComponent(fieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoginErro)
                .addGap(9, 9, 9)
                .addComponent(buttonLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        buttonLogin.setEnabled(false);
        

        if ( fieldLogin.getText().equals("") || fieldSenha.getPassword().length < 1 ) {
            labelLoginErro.setText("Informe login e senha");
            labelLoginErro.setVisible(true);
        } else {
            FuncionarioService usuarioService = SpringConfig.context.getBean(FuncionarioService.class);
            String senha = new String(fieldSenha.getPassword());

            try {
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                //Add password bytes to digest
                md.update(senha.getBytes());
                //Get the hash's bytes
                byte[] bytes = md.digest();
                //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< bytes.length ;i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                senha = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            
        //    funcionarioLogado = usuarioService.findById(1);
            
        //    funcionarioLogado.setSenha(senha);
            
        //    usuarioService.update(funcionarioLogado);
            
            funcionarioLogado = usuarioService.loginUsuario(fieldLogin.getText(), senha);
           
            if ( funcionarioLogado != null ){
                labelLoginErro.setText("");
                labelLoginErro.setVisible(false);
                
                ParamConfig config = new ParamConfig(funcionarioLogado);
                
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        TelaLogin.this.setVisible(false);
                        TelaInicial telaInicial = new TelaInicial();
                            
                        telaInicial.setVisible(true);
                        }
                });
            } else {
                labelLoginErro.setText("Usuário ou senha incorretos.");
                labelLoginErro.setVisible(true);
            }
        }
        
        buttonLogin.setEnabled(true);
    }//GEN-LAST:event_buttonLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogin;
    private javax.swing.JTextField fieldLogin;
    private javax.swing.JPasswordField fieldSenha;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelLoginErro;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelValidade;
    private javax.swing.JPanel panelLogin;
    // End of variables declaration//GEN-END:variables
}
