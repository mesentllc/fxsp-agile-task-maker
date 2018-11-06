/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fedex.smartpost.utilities;

import com.fedex.smartpost.utilities.service.AGMCardService;
import com.fedex.smartpost.utilities.service.CardService;
import com.fedex.smartpost.utilities.service.EpicCardService;
import com.fedex.smartpost.utilities.service.FeatureCardService;
import com.fedex.smartpost.utilities.service.V1CardService;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class MakeCards extends JFrame {
	private static final long serialVersionUID = 1L;

	public MakeCards() {
		initComponents();
		rbGroupOutput.add(rbPrint);
		rbGroupOutput.add(rbPDF);
		rbInputSource.add(rbVersion1);
		rbInputSource.add(rbAGM);
		rbInputSource.add(rbFeature);
		rbInputSource.add(rbEpic);
		btnProcess.setEnabled(false);
		txtOutputFile.setEnabled(false);
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		rbGroupOutput = new javax.swing.ButtonGroup();
		rbInputSource = new javax.swing.ButtonGroup();
		appTitle = new javax.swing.JLabel();
		lblExcelFile = new javax.swing.JLabel();
		txtExcelFile = new javax.swing.JTextField();
		btnExcel = new javax.swing.JButton();
		rbPrint = new javax.swing.JRadioButton();
		rbPDF = new javax.swing.JRadioButton();
		txtOutputFile = new javax.swing.JTextField();
		btnProcess = new javax.swing.JButton();
		rbVersion1 = new javax.swing.JRadioButton();
		rbAGM = new javax.swing.JRadioButton();
		rbFeature = new javax.swing.JRadioButton();
		rbEpic = new javax.swing.JRadioButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		appTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		appTitle.setText("Make Agile Cards Processor");

		lblExcelFile.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		lblExcelFile.setText("Excel Spreadsheet:");

		txtExcelFile.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtExcelFileKeyReleased(evt);
			}
		});

		btnExcel.setText("...");
		btnExcel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExcelActionPerformed(evt);
			}
		});

		rbPrint.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		rbPrint.setSelected(true);
		rbPrint.setText("Print");
		rbPrint.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				rbPrintFocusGained(evt);
			}
		});

		rbPDF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		rbPDF.setText("Create PDF");
		rbPDF.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				rbPDFFocusGained(evt);
			}
		});

		txtOutputFile.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtOutputFileKeyReleased(evt);
			}
		});

		btnProcess.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		btnProcess.setText("Process");
		btnProcess.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnProcessActionPerformed(evt);
			}
		});

		rbVersion1.setText("VersionOne");

		rbAGM.setSelected(true);
		rbAGM.setText("AGM");

		rbFeature.setText("Features");

		rbEpic.setText("Epic");
		rbEpic.setActionCommand("rbEpic");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
																.addComponent(rbVersion1)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(rbAGM)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(rbFeature)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(rbEpic))
														.addComponent(appTitle, javax.swing.GroupLayout.Alignment.LEADING))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
												.addComponent(btnProcess))
										.addGroup(layout.createSequentialGroup()
												.addComponent(rbPrint)
												.addGap(20, 20, 20)
												.addComponent(rbPDF)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(txtOutputFile))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addComponent(lblExcelFile)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(txtExcelFile)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(17, 17, 17))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(appTitle)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblExcelFile)
										.addComponent(txtExcelFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(btnExcel))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(rbPrint)
										.addComponent(rbPDF)
										.addComponent(txtOutputFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(btnProcess)
										.addComponent(rbVersion1)
										.addComponent(rbAGM)
										.addComponent(rbFeature)
										.addComponent(rbEpic))
								.addContainerGap(16, Short.MAX_VALUE))
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnExcelActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Excel Files", "xls", "xlsx"));
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			txtExcelFile.setText(selectedFile.getAbsolutePath());
			if (rbPDF.isSelected()) {
				btnProcess.setEnabled((txtExcelFile.getText().trim().length() > 0) && (txtOutputFile.getText().trim().length() > 0));
			}
			else {
				btnProcess.setEnabled(txtExcelFile.getText().trim().length() > 0);
			}
		}
	}//GEN-LAST:event_btnExcelActionPerformed

	private void rbPrintFocusGained(FocusEvent evt) {//GEN-FIRST:event_rbPrintFocusGained
		txtOutputFile.setEnabled(false);
		btnProcess.setEnabled(txtExcelFile.getText().trim().length() > 0);
	}//GEN-LAST:event_rbPrintFocusGained

	private void rbPDFFocusGained(FocusEvent evt) {//GEN-FIRST:event_rbPDFFocusGained
		txtOutputFile.setEnabled(true);
		btnProcess.setEnabled((txtExcelFile.getText().trim().length() > 0) && (txtOutputFile.getText().trim().length() > 0));
	}//GEN-LAST:event_rbPDFFocusGained

	private void txtExcelFileKeyReleased(KeyEvent evt) {//GEN-FIRST:event_txtExcelFileKeyReleased
		if (rbPDF.isSelected()) {
			btnProcess.setEnabled((txtExcelFile.getText().trim().length() > 0) && (txtOutputFile.getText().trim().length() > 0));
		}
		else {
			btnProcess.setEnabled(txtExcelFile.getText().trim().length() > 0);
		}
	}//GEN-LAST:event_txtExcelFileKeyReleased

	private void txtOutputFileKeyReleased(KeyEvent evt) {//GEN-FIRST:event_txtOutputFileKeyReleased
		btnProcess.setEnabled((txtExcelFile.getText().trim().length() > 0) && (txtOutputFile.getText().trim().length() > 0));
	}//GEN-LAST:event_txtOutputFileKeyReleased

	private void btnProcessActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
		CardService cardService = null;
		if (rbVersion1.isSelected()) {
			cardService = new V1CardService();
		}
		if (rbAGM.isSelected()) {
			cardService = new AGMCardService(this);
		}
		if (rbFeature.isSelected()) {
			cardService = new FeatureCardService();
		}
		if (rbEpic.isSelected()) {
			cardService = new EpicCardService();
		}
		try {
			cardService.process(txtExcelFile.getText(), txtOutputFile.getText());
		}
		catch (Exception e) {
			JOptionPane.showConfirmDialog(this, e, "Exception Occurred", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}
		System.exit(0);
	}//GEN-LAST:event_btnProcessActionPerformed

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MakeCards().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel appTitle;
	private javax.swing.JButton btnExcel;
	private javax.swing.JButton btnProcess;
	private javax.swing.JLabel lblExcelFile;
	private javax.swing.JRadioButton rbAGM;
	private javax.swing.JRadioButton rbEpic;
	private javax.swing.JRadioButton rbFeature;
	private javax.swing.ButtonGroup rbGroupOutput;
	private javax.swing.ButtonGroup rbInputSource;
	private javax.swing.JRadioButton rbPDF;
	private javax.swing.JRadioButton rbPrint;
	private javax.swing.JRadioButton rbVersion1;
	private javax.swing.JTextField txtExcelFile;
	private javax.swing.JTextField txtOutputFile;
	// End of variables declaration//GEN-END:variables
}
