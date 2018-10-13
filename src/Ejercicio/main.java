
package Ejercicio;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

public class main extends javax.swing.JFrame {

 
    public main() {
        //definirProductosPrueba();
        leerProductos();
        definirProductosArchivo();
        initComponents();
        setLocationRelativeTo(null);
    }

    //Variables
    private File archivo=new File("Productos.txt");
    private ArrayList<String> Lacteos = new ArrayList<String>();
    private ArrayList<String> Carnes = new ArrayList<String>();
    private ArrayList<String> Verduras = new ArrayList<String>();
    private DefaultListModel miLibro = new DefaultListModel();
    private DefaultListModel Elementos = new DefaultListModel();
    String comboChoice;
    static int TotalCompra=0;
    private ArrayList<String> kdena = new ArrayList<String>();
    private ArrayList<String> Lacteos1 = new ArrayList<String>();
    private ArrayList<String> Carnes1 = new ArrayList<String>();
    private ArrayList<String> Verduras1 = new ArrayList<String>();
    static int aux=0;
    static int aux22=0;
    
    //Variables
  
    
    public void definirProductosPrueba(){
        Lacteos.add("Leche");
        Lacteos.add("Yogurt");
        Lacteos.add("Queso");
        Lacteos.add("Crema");
        Carnes.add("Lomo");
        Carnes.add("Bondiola");
        Carnes.add("Asado");
        Carnes.add("Cuadril");
        Verduras.add("Tomate");
        Verduras.add("Lechuga");
        Verduras.add("Morron");
        Verduras.add("Palta");
        
    }
    
    public boolean validarNumero(){
        boolean verifica=false;
        try {
            int k= Integer.parseInt(Cantidad.getText());
            verifica=true;
        } catch (NumberFormatException e) {}
        if(Cantidad.getText().equals("")) verifica=true;
        return verifica;
    }
    
    
    public void leerProductos(){
       try{
           String  cadena;
            
           FileReader f = new FileReader(archivo);
           BufferedReader b = new BufferedReader(f);
           while((cadena = b.readLine())!=null) {
               kdena.add(cadena);
               
            }
            b.close();  
        }catch(Exception e){}
        
        int marcadorLacteos = kdena.indexOf("LACTEOS");
        int marcadorCarnes = kdena.indexOf("CARNES");
        int marcadorVerduras= kdena.indexOf("VERDURAS");
       
        //añadir lacteos
        for( int i= marcadorLacteos+1;i<marcadorCarnes;i++){
            Lacteos1.add(kdena.get(i));
        }
        //añadir carnes
        for(int i=marcadorCarnes+1;i<marcadorVerduras;i++){
            Carnes1.add(kdena.get(i));
        }
        //añadir verduras
        for(int i=marcadorVerduras+1;i<kdena.size();i++){
            Verduras1.add(kdena.get(i));
        }

       
       
        
        
    }
    
    public void definirProductosArchivo(){
        
        for(int i=0; i<Lacteos1.size();i++){
            String[] aux=Lacteos1.get(i).split(" ");
            Lacteos.add(aux[0]);
        }
        
        for(int i=0; i<Carnes1.size();i++){
            String[] aux=Carnes1.get(i).split(" ");
            Carnes.add(aux[0]);
        }
        
        for(int i=0; i<Verduras1.size();i++){
            String[] aux=Verduras1.get(i).split(" ");
            Verduras.add(aux[0]);
        }
        
    }
   
    public void obtenerPrecio(){
        int precioAux=0;
        int precioInd=0;
        String aux=Compra.getSelectedValue();
        int posiLista=Compra.getSelectedIndex();
        
        String [] aux2=aux.split(":");
        int aux222=Integer.parseInt(aux2[1]);
        if(Integer.parseInt(Cantidad.getText())>aux222){
           JOptionPane.showMessageDialog(null, "No hay suficiente cantidad en este Item.\nDeje el espacio vacio para eliminar el item entero.");
           return; 
        }
        
        int identifier=0;
        int posi=0;
        for(int i=0;i<Carnes.size();i++){
            if(Carnes.get(i).equals(aux2[0])){
                identifier=2;
                posi=i;
            }
        }//fin recorrer carnes
        for(int i=0;i<Lacteos.size();i++){
            if(Lacteos.get(i).equals(aux2[0])){
                identifier=1;
                posi=i;
            }
        }//fin Lacteos
        for(int i=0;i<Verduras.size();i++){
            if(Verduras.get(i).equals(aux2[0])){
                identifier=3;
                posi=i;
            }
        }//fin Verduras
        
        
        
        String []k;
        //es lacteo
        if(identifier==1){
          k=Lacteos1.get(posi).split(" ");
          precioInd=Integer.parseInt(k[1]);
          if(Cantidad.getText().isEmpty()){
            precioAux=precioInd*(Integer.parseInt(aux2[1])); 
            Elementos.remove(Compra.getSelectedIndex());
            Compra.setModel(Elementos);
            TotalCompra=TotalCompra-precioAux;
            return;
        }
          precioAux=precioInd*(Integer.parseInt(Cantidad.getText()));
          Elementos.remove(posiLista);
          int restante=Integer.parseInt(aux2[1])-Integer.parseInt(Cantidad.getText());
          //Elementos.addElement(aux2[0]+":"+restante);
          Elementos.add(posiLista, aux2[0]+":"+restante);
        }
        
        //es carne
        if(identifier==2){
          k=Carnes1.get(posi).split(" ");
          precioInd=Integer.parseInt(k[1]);
          if(Cantidad.getText().isEmpty()){
            precioAux=precioInd*(Integer.parseInt(aux2[1])); 
            Elementos.remove(Compra.getSelectedIndex());
            Compra.setModel(Elementos);
            TotalCompra=TotalCompra-precioAux;
            return;
        }
          precioAux=precioInd*(Integer.parseInt(Cantidad.getText()));
          Elementos.remove(posiLista);
          int restante=Integer.parseInt(aux2[1])-Integer.parseInt(Cantidad.getText());
          //Elementos.addElement(aux2[0]+":"+restante);
          Elementos.add(posiLista, aux2[0]+":"+restante);
        }//fin es carne
        
        //es Verdura
        if(identifier==3){
          k=Verduras1.get(posi).split(" ");
          precioInd=Integer.parseInt(k[1]);
          if(Cantidad.getText().isEmpty()){
            precioAux=precioInd*(Integer.parseInt(aux2[1])); 
            Elementos.remove(Compra.getSelectedIndex());
            Compra.setModel(Elementos);
            TotalCompra=TotalCompra-precioAux;
            return;
        }
          precioAux=precioInd*(Integer.parseInt(Cantidad.getText()));
          Elementos.remove(posiLista);
          int restante=Integer.parseInt(aux2[1])-Integer.parseInt(Cantidad.getText());
          //Elementos.addElement(aux2[0]+":"+restante);
          Elementos.add(posiLista, aux2[0]+":"+restante);
        }
        TotalCompra=TotalCompra-precioAux;
       
        
        //eliminar una determinada cantida de la lista de compra
        if(Integer.parseInt(aux2[1])==(Integer.parseInt(Cantidad.getText())) ) {
            Elementos.remove(Compra.getSelectedIndex());
            Compra.setModel(Elementos);
            TotalCompra=TotalCompra+precioAux;    
        }
      
    }
    
   
    public void mirarCantidad(){
        if((aux22)< Integer.parseInt(Cantidad.getText()) ){
           JOptionPane.showMessageDialog(null, "No hay suficiente cantidad en este Item.\nDeje el espacio vacio para eliminar el item entero.");
           return; 
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Agregar = new javax.swing.JButton();
        Sacar = new javax.swing.JButton();
        Cantidad = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Compra = new javax.swing.JList<>();
        jTextField2 = new javax.swing.JTextField();
        Calcular = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Importe = new javax.swing.JTextField();
        Combo = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        Listado = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Agregar.setText("Agregar");
        Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarActionPerformed(evt);
            }
        });

        Sacar.setText("Deshacer");
        Sacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SacarActionPerformed(evt);
            }
        });

        Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadKeyTyped(evt);
            }
        });

        Compra.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(Compra);

        jTextField2.setText("Total a Pagar :");
        jTextField2.setFocusable(false);

        Calcular.setText("Calcular Total Compra");
        Calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalcularActionPerformed(evt);
            }
        });

        jLabel1.setText("$");

        Importe.setFocusable(false);

        Combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lacteos", "Carnes", "Verduras" }));
        Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboActionPerformed(evt);
            }
        });

        jButton4.setText("Salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        Listado.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { ""};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(Listado);

        jButton1.setText("¿Como lo uso?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Agregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Sacar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Cantidad, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Importe, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(69, 69, 69))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(17, 17, 17))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(203, 203, 203)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(375, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Agregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sacar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Calcular)
                    .addComponent(jLabel1)
                    .addComponent(Importe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(50, 50, 50))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(126, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboActionPerformed
        int value=Combo.getSelectedIndex();
        if(value==0){
            miLibro.removeAllElements();
            //selecciona Lacteos
            for(int i=0;i<Lacteos.size();i++){
                miLibro.addElement(Lacteos.get(i));
                
            }
            Listado.setModel(miLibro);
            
        }//fin seleccion lacteos
        if(value==1){
            //elige carnes
            miLibro.removeAllElements();
              for(int i=0;i<Carnes.size();i++){
                miLibro.addElement(Carnes.get(i));
                
            }
            Listado.setModel(miLibro);
            
        }
        if(value==2){
             miLibro.removeAllElements();
            //selecciona Lacteos
            for(int i=0;i<Verduras.size();i++){
                miLibro.addElement(Verduras.get(i));
                
            }
            Listado.setModel(miLibro);
        }
        
    }//GEN-LAST:event_ComboActionPerformed

    private void CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadKeyTyped
 
    }//GEN-LAST:event_CantidadKeyTyped

    private void CantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadKeyPressed
        
        
    }//GEN-LAST:event_CantidadKeyPressed

    private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarActionPerformed
        validarNumero();
        if(validarNumero()==false){
            JOptionPane.showMessageDialog(null, "No ha ingresado un numero Valido");
            Cantidad.setText("");
            return;
        }
        int amount;
        if(Cantidad.getText().equals("")){
            amount=1;
        }else{
            amount=Integer.parseInt(Cantidad.getText());
        }
        aux=0;
        int posiProducto=Listado.getSelectedIndex();
        int clasiProducto=Combo.getSelectedIndex();
        if(clasiProducto==0){
            Elementos.addElement(Lacteos.get(posiProducto) +":"+amount);
            String[]ek= Lacteos1.get(posiProducto).split(" ");
            aux=Integer.parseInt(ek[1])*amount;
        }
        
        if(clasiProducto==1){
            Elementos.addElement(Carnes.get(posiProducto) +":"+amount);
            String[]ek= Carnes1.get(posiProducto).split(" ");
            aux=Integer.parseInt(ek[1])*amount;
        }
        
        if(clasiProducto==2){
            Elementos.addElement(Verduras.get(posiProducto)+":"+amount);
            String[]ek= Verduras1.get(posiProducto).split(" ");
            aux=Integer.parseInt(ek[1])*amount;
        }
        
        TotalCompra=TotalCompra+aux;
        Compra.setModel(Elementos);
        
    }//GEN-LAST:event_AgregarActionPerformed

    private void SacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SacarActionPerformed
        if(Compra.getSelectedIndex()==(-1)){
            int ultimoAgregado= Elementos.size()-1;
            Elementos.remove(ultimoAgregado);
            TotalCompra=TotalCompra-aux;
            Compra.setModel(Elementos);
        }else{
            obtenerPrecio();
        }
        
        
        
    }//GEN-LAST:event_SacarActionPerformed

    private void CalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalcularActionPerformed
        if(TotalCompra<1000){
            Importe.setBackground(Color.GREEN);
        }else{
            Importe.setBackground(Color.RED);
        }
        Importe.setText(String.valueOf(TotalCompra));
    }//GEN-LAST:event_CalcularActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String msj="1- Seleccione de la lista que tipo de producto quiere\n"
                + "2-Selccione que producto quiere dentro del tipo elegido\n"
                + "3-Ingrese una cantidad y presione agregar\n"
                + "4-Si no ingrea una cantidad, por defecto se agrega 1 unidad\n"
                + "5-Si se equivoca presione el boton para deshacer el ultimo agregado\n"
                + "6-Presione Calcular para ver el importe a pagar\n"
                + "7-Si el importe supera los mil pesos aparecera en rojo, de lo contrario en verde.";
        JOptionPane.showMessageDialog(null, msj);
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregar;
    private javax.swing.JButton Calcular;
    private javax.swing.JTextField Cantidad;
    private javax.swing.JComboBox<String> Combo;
    private javax.swing.JList<String> Compra;
    private javax.swing.JTextField Importe;
    private javax.swing.JList<String> Listado;
    private javax.swing.JButton Sacar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
